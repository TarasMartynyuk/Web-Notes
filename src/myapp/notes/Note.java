package myapp.notes;

public class Note {
    private String text;
    private int id;

    private static int freeId;

    public Note(String text) {
        this.text = text;
        this.id = takeNextFreeId();
    }

    public Note(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public int takeNextFreeId() {
        return freeId++;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
