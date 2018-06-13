package myapp.servlets;
import http.server.Method;
import http.server.request.Request;
import http.server.response.Response;
import http.server.servlet.AbstractServlet;
import myapp.ResponseBuilder;
import myapp.notes.NotesContainer;

import java.io.IOException;

public class NotesEditServlet extends AbstractServlet {

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
        var intId = Integer.parseInt(id);

        var newText = req.getParameterOrNull("note_text");
        if(newText == null) {
            throw  new MissingParameterException("new_text");
        }

//        System.out.println("changing text of #" +  id   + " to " + newText);

        NotesContainer.getInstance().editNote(intId, newText);

        new ResponseBuilder(res).writeOkResponce("", "text/plain");
    }
}
