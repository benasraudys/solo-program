public class Chord {
    private String rootNote;
    private String quality;
    private String[] extensions;

    public Chord(String rootNote, String quality, String[] extensions) {
        this.rootNote = rootNote;
        this.quality = quality;
        this.extensions = extensions;
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

    public void setRootNote(String rootNote) {
        this.rootNote = rootNote;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    public String displayChord() {
        StringBuilder chordNotation = new StringBuilder();

        chordNotation.append(rootNote);

        switch (quality.toLowerCase()) {
            case "major":
                chordNotation.append("maj");
                break;
            case "minor":
                chordNotation.append("min");
                break;
            case "diminished":
                chordNotation.append("dim");
                break;
            case "augmented":
                chordNotation.append("aug");
                break;
            default:
                chordNotation.append(quality);
                break;
        }

        if (extensions != null && extensions.length > 0) {
            for (String extension : extensions) {
                chordNotation.append("/");
                chordNotation.append(extension);
            }
        }

        return chordNotation.toString();
    }


    public static void main(String[] args) {
        String[] extensions = {"7", "9"};
        Chord chord = new Chord("C", "major", extensions);
        System.out.println(chord.displayChord());
    }
}
