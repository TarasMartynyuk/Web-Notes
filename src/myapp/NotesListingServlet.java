package myapp;

import http.server.Constants;
import http.server.request.Request;
import http.server.response.Response;
import http.server.servlet.AbstractServlet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class NotesListingServlet extends AbstractServlet {

    static final String HEADER_FILENAME = "notes/header.html";

    static final String FOOTER_FILENAME = "notes/footer.html";


    @Override
    public void service(Request req, Response res) throws IOException {

        var responseBuilder = new ResponseBuilder(res);

        responseBuilder.WriteOkResponce(generateListPage(
                NotesContainer.getInstance().listNotes()), "text/html");
    }

    private String generateListPage(Iterable<String> notes) throws IOException {
        var builder = new StringBuilder();

        builder.append(readFileAsUtf8(Paths.get(Constants.WEB_ROOT, HEADER_FILENAME)));

        for(var note : notes) {
            builder.append(generateNoteParagraph(note));
        }

        builder.append(readFileAsUtf8(Paths.get(Constants.WEB_ROOT, FOOTER_FILENAME)));

        return builder.toString();
    }

    private String generateNoteParagraph(String note) {
        return "<p>" + note + "</p>";
    }

    private String readFileAsUtf8(Path path) throws IOException {
        byte[] contents = Files.readAllBytes(path);
        return new String(contents, "UTF-8");
    }
}
