import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Keys {
    private static final String[] NOTES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final String[] NOTE_FILES = {
            "C.wav", "C#.wav", "D.wav", "D#.wav", "E.wav", "F.wav",
            "F#.wav", "G.wav", "G#.wav", "A.wav", "A#.wav", "B.wav"
    };

    public static void playChord(Chord chord, int bpm) {
        String rootNote = chord.getRootNote();
        String quality = chord.getQuality();
        String[] extensions = chord.getExtensions();
        int rootIndex = getNoteIndex(rootNote);

        if (rootIndex == -1) {
            System.err.println("Invalid root note: " + rootNote);
            return;
        }

        int fifthIndex = (rootIndex + 7) % 12;
        int thirdIndex;
        switch (quality.toLowerCase()) {
            case "major":
                thirdIndex = (rootIndex + 4) % 12;
                break;
            case "minor":
                thirdIndex = (rootIndex + 3) % 12;
                break;
            case "diminished":
                thirdIndex = (rootIndex + 3) % 12;
                fifthIndex = (rootIndex + 6) % 12;
                break;
            case "augmented":
                thirdIndex = (rootIndex + 4) % 12;
                fifthIndex = (rootIndex + 8) % 12;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + quality.toLowerCase());
        }

        if (fifthIndex != (rootIndex + 7) % 12) {
            playNoteByIndex(fifthIndex);
        }

        int extensionIndex = 0;
        for (String extension : extensions) {
            switch (extension) {
                case "7" -> extensionIndex = (rootIndex + 10) % 12;
                case "9" -> extensionIndex = (rootIndex + 2) % 12;
                case "9#" -> extensionIndex = (rootIndex + 3) % 12;
                case "11" -> extensionIndex = (rootIndex + 5) % 12;
                case "11#" -> extensionIndex = (rootIndex + 6) % 12;
                case "13" -> extensionIndex = (rootIndex + 9) % 12;
            }
            playNoteByIndex(extensionIndex);
        }

        //playNoteByIndex(rootIndex);
        playNoteByIndex(thirdIndex);
    }

    private static int getNoteIndex(String note) {
        for (int i = 0; i < NOTES.length; i++) {
            if (NOTES[i].equalsIgnoreCase(note)) {
                return i;
            }
        }
        return -1;
    }

    private static void playNoteByIndex(int index) {
        File noteSoundFile = new File("keys/" + NOTE_FILES[index]);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(noteSoundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Chord chord = new Chord("C", "major", new String[0]);
        playChord(chord, 120);
    }
}
