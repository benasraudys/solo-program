import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.security.Key;

public class ChordPlayer {
    private static final String C = "C.wav";
    private static final String Csharp = "C#.wav";
    private static final String D = "D.wav";
    private static final String Dsharp = "D#.wav";
    private static final String E = "E.wav";
    private static final String F = "F.wav";
    private static final String Fsharp = "F#.wav";
    private static final String G = "G.wav";
    private static final String Gsharp = "G#.wav";
    private static final String A = "A.wav";
    private static final String Asharp = "A#.wav";
    private static final String B = "B.wav";

    private static final String beat1 = "1.wav";
    private static final String beat2 = "2.wav";
    private static final String beat3 = "3.wav";
    private static final String beat4 = "4.wav";

    public static void playChord(Chord chord, int bpm) {
        String chordName = chord.getRootNote();

        String chosenChord = "C.wav";
        switch (chordName) {
            case "C":
                chosenChord = C;
                break;
            case "C#":
                chosenChord = Csharp;
                break;
            case "D":
                chosenChord = D;
                break;
            case "D#":
                chosenChord = Dsharp;
                break;
            case "E":
                chosenChord = E;
                break;
            case "F":
                chosenChord = F;
                break;
            case "F#":
                chosenChord = Fsharp;
                break;
            case "G":
                chosenChord = G;
                break;
            case "G#":
                chosenChord = Gsharp;
                break;
            case "A":
                chosenChord = A;
                break;
            case "A#":
                chosenChord = Asharp;
                break;
            case "B":
                chosenChord = B;
                break;
        }
        File chordSoundFile = new File("bass/" + chosenChord);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(chordSoundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            Keys.playChord(chord, bpm);

            new Thread(() -> playBeats(bpm)).start();


            Thread.sleep(240000 / bpm);

            clip.close();
            audioStream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void playBeats(int bpm) {
        // beats are in drums folder
        File[] beatFiles = {
                new File(beat1), new File(beat2), new File(beat3), new File(beat4)
        };

        for (File beatFile : beatFiles) {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(beatFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                Thread.sleep(60000 / bpm);

                clip.close();
                audioStream.close();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Chord chord = new Chord("C", "major", new String[0]);
        playChord(chord, 120);
    }
}
