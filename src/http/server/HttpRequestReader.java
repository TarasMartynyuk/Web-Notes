package http.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequestReader {
    private final InputStream _in;

    public HttpRequestReader(InputStream in) {
        _in = in;
    }

    // should be parsed as a map, but i don't need it now
    /**
     * reads the http request's headers into a string
     * stops at the blank line after headers
     *
     * the stream is advanced, so that it now points
     * to the start of next line after the blank line
     */
    public String readHeaders() throws IOException {

        try(var reader = wrapIn()) {
            var fullRequest = new StringBuilder();
                String line;
                while (true) {

                    line = reader.readLine();
                    if (line == null || line.isEmpty()) {
                        break;
                    }

                    fullRequest.append(line);
                    fullRequest.append("\r\n");
                }

            assert fullRequest.toString().contains("HTTP");
            return fullRequest.toString();
        }
    }

    /**
     * the headers must be read from InputStream _in before reading body
     * reads all remaining characters from inputstream
     * @return
     */
    public String readBody() throws IOException {
//        if(contentLength < 0) {
//            throw new IllegalArgumentException("contentLength must be >= 0");
//        }

        var builder = new StringBuilder();
        try(var reader = wrapIn()) {
            char[] charBuffer = new char[1024];
            int bytesRead;

            while ((bytesRead = reader.read(charBuffer)) > 0) {
                builder.append(charBuffer, 0, bytesRead);
            }
        }

        return builder.toString();
    }

    private BufferedReader wrapIn() {
        return new BufferedReader(new InputStreamReader(_in));
    }
}
