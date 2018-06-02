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

    public void addNote(String note) {
        _notes.add(note);
    }

    public Iterable<String> listNotes() {
        var notesCopy = new ArrayList<String >(size());

        for (int i = 0, last = size() - 1; i < size(); i++) {

            notesCopy.add(i, _notes.get(last - i));
        }
        return notesCopy;
    }

    // TODO : is it possible to make deferred iteration thread safe?
//    private Iterator<String> iterator() {
//        return new Iterator<String>() i{
//
//            int index = _notes.size();
//
//            @Override
//            public boolean hasNext() {
//                return index != 0;
//            }
//
//            @Override
//            public String next() {
//                if(!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//
//                return _notes.get(--index);
//            }
//        };
//    }

    public int size() { return _notes.size(); }

    public void clear() {
        _notes.clear();
    }
}
