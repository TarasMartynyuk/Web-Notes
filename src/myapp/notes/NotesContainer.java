package myapp.notes;

import java.util.*;

public class NotesContainer{

    private static NotesContainer ourInstance = new NotesContainer();
    private ArrayList<Note> _notes;

    public static NotesContainer getInstance() {
        return ourInstance;
    }

    private NotesContainer() {

        _notes = new ArrayList<>();
    }

    public synchronized void addNote(Note note) {
        _notes.add(note);
    }

    public synchronized Iterable<Note> listNotes() {
        var notesCopy = new ArrayList<Note>(size());

        for (int i = 0, last = size() - 1; i < size(); i++) {

            notesCopy.add(i, _notes.get(last - i));
        }
        return notesCopy;
    }

    public int size() { return _notes.size(); }

    public void clear() {
        _notes.clear();
    }
}
