package myapp.servlets;

import http.server.Method;
import http.server.request.Request;
import http.server.response.Response;
import http.server.servlet.AbstractServlet;
import myapp.ResponseBuilder;
import myapp.notes.NotesContainer;

import java.io.IOException;

public class NotesDeleteServlet extends AbstractServlet {

    private final NoteListPageBuilder _noteListPageBuilder;

    public NotesDeleteServlet() {
        _noteListPageBuilder = new NoteListPageBuilder();
    }

    @Override
    public void service(Request req, Response res) throws IOException, MissingParameterException {
        if(req.getMethod() != Method.POST) {
            System.out.println("NotesDeleteServlet recieved request with unexpected method : " + req.getMethod());
            return;
        }

        var id = req.getParameterOrNull("note_id");
        if(id == null) {
            throw  new MissingParameterException("id");
        }

        int parsedId = Integer.parseInt(id);
        NotesContainer.getInstance().deleteNote(parsedId);
    }
}
