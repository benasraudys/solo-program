import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GuitarSoloTrainingApp {
    private JFrame frame;
    private JLabel displayLabel;
    private JLabel nextChordLabel;
    private boolean trainingRunning = false;
    private final int bpm = 120;

    public void start() {
        frame = new JFrame("Guitar Soloing Training App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);  // Center the window

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        container.add(centerPanel, BorderLayout.CENTER);

        displayLabel = new JLabel("Welcome to guitar soloing training app!", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        displayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(displayLabel);

        nextChordLabel = new JLabel("Next chord: ", SwingConstants.CENTER);
        nextChordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nextChordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createVerticalStrut(10)); // Add space between labels
        centerPanel.add(nextChordLabel);

        JPanel panel = new JPanel();
        JButton startButton = new JButton("Start Training");
        JButton exitButton = new JButton("Exit");
        JLabel bpmLabel = new JLabel("BPM: " + bpm, SwingConstants.CENTER);
        bpmLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bpmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(bpmLabel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!trainingRunning) {
                    trainingRunning = true;
                    displayLabel.setText("Training started");
                    new Thread(() -> startTraining()).start();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(startButton);
        panel.add(exitButton);
        container.add(panel, BorderLayout.NORTH);

        frame.setVisible(true);
    }


    private void startTraining() {
        Chord prevChord = new Chord("C", "major", new String[0]);
        Chord nextChord = ChordGenerator.generateChord(prevChord);
        while (trainingRunning) {
            Chord chord = nextChord;
            prevChord = chord;
            nextChord = ChordGenerator.generateChord(prevChord);
            Chord finalNextChord = nextChord;
            SwingUtilities.invokeLater(() -> {
                displayLabel.setText("Chord: " + chord.displayChord());
                nextChordLabel.setText("Next chord: " + finalNextChord.displayChord());
            });
            ChordPlayer.playChord(chord, bpm);
        }
    }
}