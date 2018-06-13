package myapp.servlets;

import http.server.request.Request;
import http.server.response.Response;
import http.server.servlet.AbstractServlet;
import myapp.notes.NotesContainer;
import myapp.ResponseBuilder;

import java.io.IOException;

public class NotesListingServlet extends AbstractServlet {

    private final NoteListPageBuilder _noteListPageBuilder;

    public NotesListingServlet() {
        _noteListPageBuilder = new NoteListPageBuilder();
    }

    @Override
    public void service(Request req, Response res) throws IOException {

        var responseBuilder = new ResponseBuilder(res);

        responseBuilder.writeOkResponce(_noteListPageBuilder.buildNotesListPage(
                NotesContainer.getInstance().listNotes()), "text/html");
    }
}
