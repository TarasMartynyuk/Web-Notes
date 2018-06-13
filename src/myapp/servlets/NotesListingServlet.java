package myapp.servlets;

import http.server.Constants;
import http.server.request.Request;
import http.server.response.Response;
import http.server.servlet.AbstractServlet;
import myapp.notes.Note;
import myapp.notes.NotesContainer;
import myapp.ResponseBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NotesListingServlet extends AbstractServlet {

    private final NoteListPageBuilder _noteListPageBuilder;

    public NotesListingServlet() {
        _noteListPageBuilder = new NoteListPageBuilder();
    }

    @Override
    public void service(Request req, Response res) throws IOException {

        var responseBuilder = new ResponseBuilder(res);

        responseBuilder.WriteOkResponce(_noteListPageBuilder.buildNotesListPage(
                NotesContainer.getInstance().listNotes()), "text/html");
    }
}
