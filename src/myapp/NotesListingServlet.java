package myapp;

import http.server.request.Request;
import http.server.response.Response;
import http.server.servlet.AbstractServlet;
import java.io.IOException;

public class NotesListingServlet extends AbstractServlet {

    static final String PAGE_HEAD = "<html>\n" +
            "    <head>\n" +
            "        <title>My Notes</title>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <br>\n" +
            "My notes:\n";

//    static final String PAGE_FOOTER_F


    @Override
    public void service(Request req, Response res) throws IOException {

        var responseBuilder = new ResponseBuilder(res);

        responseBuilder.WriteOkResponce(generateListPage(
                NotesContainer.getInstance().listNotes()), "text/html");
    }

    private String generateListPage(Iterable<String> notes) {
        var builder = new StringBuilder(PAGE_HEAD);

        for(var note : notes) {
            builder.append(generateNoteParagraph(note));
        }

        builder.append(PAGE_FOOTER);
        return builder.toString();
    }

    private String generateNoteParagraph(String note) {
        return "<p>" + note + "</p>";
    }
}
