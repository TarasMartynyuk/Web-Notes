package myapp;

import http.server.Request;
import http.server.Response;
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

    static final String PAGE_FOOTER = "    </body>\n" +
            "</html>";


    @Override
    public void service(Request req, Response res) throws IOException {

        var responseBuilder = new ResponseBuilder(res);

        responseBuilder.WriteOkResponce(PAGE_HEAD + PAGE_FOOTER, "text/html");
    }

//    private String generateNoteParagraph(String note) {
//
//    }
}
