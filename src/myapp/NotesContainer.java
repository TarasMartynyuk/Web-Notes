package myapp;

import java.util.*;
import java.util.function.Consumer;

public class NotesContainer implements Iterable<String> {

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

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            int index = _notes.size();

            @Override
            public boolean hasNext() {
                return index != 0;
            }

            @Override
            public String next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }

                return _notes.get(--index);
            }
        };
    }

    public void clear() {
        _notes.clear();
    }
}
