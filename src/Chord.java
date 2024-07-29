public class Chord {
    private String rootNote;
    private String quality;
    private String[] extensions;
    private String rootKey;
    private String rootMode;

    public Chord(String rootNote, String quality, String[] extensions) {
        this.rootNote = rootNote;
        this.quality = quality;
        this.extensions = extensions;
    }

    public Chord(String rootNote, String quality, String[] extensions, String rootKey, String rootMode) {
        this.rootNote = rootNote;
        this.quality = quality;
        this.extensions = extensions;
        this.rootKey = rootKey;
        this.rootMode = rootMode;
    }

    public String getRootNote() {
        return rootNote;
    }

    public String getQuality() {
        return quality;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public String getRootKey() {
        return rootKey;
    }

    public String getRootMode() {
        return rootMode;
    }

    public void setRootNote(String rootNote) {
        this.rootNote = rootNote;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    public void setRootKey(String rootKey) {
        this.rootKey = rootKey;
    }

    public void setRootMode(String rootMode) {
        this.rootMode = rootMode;
    }

    public String displayChord() {
        StringBuilder chordNotation = new StringBuilder();

        if (rootKey != null && !rootKey.isEmpty()) {
            chordNotation.append(rootKey);
            chordNotation.append(rootMode != null && rootMode.equalsIgnoreCase("minor") ? "m" : "");
            chordNotation.append(" ");
        }

        chordNotation.append(rootNote);

        switch (quality.toLowerCase()) {
            case "major":
                chordNotation.append("M");
                break;
            case "minor":
                chordNotation.append("m");
                break;
            case "diminished":
                chordNotation.append("°");
                break;
            case "augmented":
                chordNotation.append("+");
                break;
            default:
                chordNotation.append(quality);
                break;
        }

        boolean firstExtension = true;
        if (extensions != null && extensions.length > 0) {
            for (String extension : extensions) {
                if (firstExtension) {
                    chordNotation.append(" ");
                    firstExtension = false;
                } else {
                    chordNotation.append(", ");
                }
                chordNotation.append(extension);
            }
        }

        return chordNotation.toString();
    }

    public static void main(String[] args) {
        String[] extensions = {"7", "9"};
        Chord chord = new Chord("C", "major", extensions, "A", "minor");
        System.out.println(chord.displayChord());
    }
}
