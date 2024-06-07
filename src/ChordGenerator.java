import java.util.Random;

class ChordGenerator {
    private static final String[] CHORDS = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final double RANDOM_CHORD_PROBABILITY = 0;
    private static final double NEXT_CHORD_PROBABILITY = 0.4;
    private static final double PREVIOUS_CHORD_PROBABILITY = 0.2;

    public static Chord generateChord(Chord prevChord) {
        Random random = new Random();
        double randomNumber = random.nextDouble();
        String newChord;
        String prevChordName = prevChord != null ? prevChord.getRootNote() : null;
        String prevChordQuality = prevChord != null ? prevChord.getQuality() : null;
        String[] prevChordExtensions = prevChord != null ? prevChord.getExtensions() : new String[0];

        if (prevChord != null && arrayContains(prevChordExtensions, "7")) {
            if (random.nextBoolean()) {
                // Resolve 5 half steps up
                int prevIndex = findIndex(prevChordName);
                int nextIndex = (prevIndex + 5) % CHORDS.length;
                newChord = CHORDS[nextIndex];
                return new Chord(newChord, "major", new String[0]);
            } else {
                // Resolve 1 half step down to a major with no extensions
                int prevIndex = findIndex(prevChordName);
                int prevPrevIndex = (prevIndex - 1 + CHORDS.length) % CHORDS.length;
                newChord = CHORDS[prevPrevIndex];
                return new Chord(newChord, "major", new String[0]);
            }
        }

        if (randomNumber <= RANDOM_CHORD_PROBABILITY || prevChordName == null) {
            do {
                newChord = CHORDS[random.nextInt(CHORDS.length)];
            } while (newChord.equals(prevChordName));
        } else if (randomNumber <= RANDOM_CHORD_PROBABILITY + NEXT_CHORD_PROBABILITY) {
            int prevIndex = findIndex(prevChordName);
            int nextIndex = (prevIndex + 5) % CHORDS.length;
            newChord = CHORDS[nextIndex];
        } else {
            int prevIndex = findIndex(prevChordName);
            int prevPrevIndex = (prevIndex - 7 + CHORDS.length) % CHORDS.length;
            newChord = CHORDS[prevPrevIndex];
        }

        String chordQuality = "major";
        switch (random.nextInt(4)) {
            case 0:
                chordQuality = "major";
                break;
            case 1:
                chordQuality = "minor";
                break;
            case 2:
                chordQuality = "diminished";
                break;
            case 3:
                chordQuality = "augmented";
                break;
        }

        String[] newChordExtensions = new String[0];
        switch (random.nextInt(4)) {
            case 0:
                newChordExtensions = new String[0];
                break;
            case 1:
                newChordExtensions = new String[]{"7"};
                break;
            case 2:
                newChordExtensions = new String[]{"7", "9"};
                break;
            case 3:
                newChordExtensions = new String[]{"7", "9", "11"};
                break;
        }

        return new Chord(newChord, chordQuality, newChordExtensions);
    }

    private static int findIndex(String chord) {
        for (int i = 0; i < CHORDS.length; i++) {
            if (CHORDS[i].equals(chord)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean arrayContains(String[] array, String value) {
        for (String element : array) {
            if (element.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Chord prevChord = new Chord("C", "major", new String[]{"7"});
        Chord nextChord = generateChord(prevChord);
        System.out.println("Next Chord: " + nextChord.displayChord());
    }
}
