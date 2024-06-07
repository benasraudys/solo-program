import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuitarSoloTrainingApp app = new GuitarSoloTrainingApp();
            app.start();
        });
    }
}
