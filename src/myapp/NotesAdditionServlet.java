package myapp;

import http.server.Method;
import http.server.Request;
import http.server.Response;
import http.server.servlet.*;

import java.io.IOException;

public class NotesAdditionServlet extends AbstractServlet {

    static final String NOTE_PARAM_NAME = "note";

    @Override
    public void service(Request req, Response res) throws IOException {

        if(req.getMethod() != Method.POST) {
            return;
        }

        var note = req.getParameterOrNull(NOTE_PARAM_NAME);
        if(note == null) {
            System.out.println("request body does note have param with name : " + NOTE_PARAM_NAME);
        }

        NotesContainer.getInstance().addNote(note);
    }
}
