package myapp;

import java.util.*;
import java.util.function.Consumer;

public class NotesContainer{

    private static NotesContainer ourInstance = new NotesContainer();
    private ArrayList<String> _notes;

    public static NotesContainer getInstance() {
        return ourInstance;
    }

    private NotesContainer() {

        _notes = new ArrayList<>();
    }

    public synchronized void addNote(String note) {
        _notes.add(note);
    }

    public synchronized Iterable<String> listNotes() {
        var notesCopy = new ArrayList<String >(size());

        for (int i = 0, last = size() - 1; i < size(); i++) {

            notesCopy.add(i, _notes.get(last - i));
        }
        return notesCopy;
    }
    
    public int size() { return _notes.size(); }

    public void clear() {
        _notes.clear();
    }

    // TODO : is it possible to make deferred iteration thread safe?
//    private Iterator<String> iterator() {


}
