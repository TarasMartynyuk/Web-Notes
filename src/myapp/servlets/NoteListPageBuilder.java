package myapp.servlets;

import http.server.Constants;
import myapp.notes.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NoteListPageBuilder {

    static final String HEADER_FILENAME = "notes/header.html";
    static final String FOOTER_FILENAME = "notes/footer.html";

    public String buildNotesListPage(Iterable<Note> notes) throws IOException {
        var builder = new StringBuilder();

        builder.append(readFileAsUtf8(Paths.get(Constants.WEB_ROOT, HEADER_FILENAME)));

        for(var note : notes) {
            builder.append(generateNoteParagraph(note));
        }

        builder.append(readFileAsUtf8(Paths.get(Constants.WEB_ROOT, FOOTER_FILENAME)));

        return builder.toString();
    }

    private String generateNoteParagraph(Note note) {
        return "<p id=\"" +note.getId() + "\">"  + note.getText() +
                "         <button class=\"delete\">delete</button></p>";
    }

    private String readFileAsUtf8(Path path) throws IOException {
        byte[] contents = Files.readAllBytes(path);
        return new String(contents, "UTF-8");
    }
}
