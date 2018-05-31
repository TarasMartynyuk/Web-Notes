package http.server;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseBuilder {
    private final PrintWriter _out;

    public ResponseBuilder(Response res) throws IOException {
        this(res.getWriter());
    }

    public ResponseBuilder(PrintWriter writer) {
        _out = writer;
    }

    public void WriteOkResponce(String response) {
        writeOkHeader();
        writeContentHeaders(response.length());
        _out.write(response);
        writeConnectionClosed();
        _out.flush();
    }

    private void writeOkHeader() {
        _out.write("HTTP/1.1 200 OK\r\n");
    }

    private void writeContentHeaders(int contentLength) {
        _out.write("Content-Length: " + contentLength + "\r\n");
        _out.write("Content-Type: text/html\r\n\r\n");
    }

    private void writeConnectionClosed() {
        _out.write("Connection: Closed");
    }
}
