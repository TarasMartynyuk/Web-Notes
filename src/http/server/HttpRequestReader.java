package http.server;

import java.io.*;

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
        // not closing any stream that uses socket input stream here
        var reader = new BufferedReader(new InputStreamReader(_in));
        var fullRequest = new StringBuilder();

        String line;
        while (true) {

            line = reader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }

            fullRequest.append(line);
            fullRequest.append("\r\n");

            System.out.println(line);
            System.out.println("\r\n");
        }

        assert fullRequest.toString().contains("HTTP");
        return fullRequest.toString();
    }

    /**
     * the headers must be read from InputStream _in before reading body
     * reads all remaining characters from inputstream
     *
     * interprets bytes as UTF-8 encoded
     */
    public String readBody(int contentLength) throws IOException {
        if(contentLength < 0) {
            throw new IllegalArgumentException("contentLength must be >= 0");
        }

//        var dataStream = new DataInputStream(_in);
////        var builder = new StringBuilder();
//
//        var bodyBytes = new byte[contentLength];
//        // blocks until bodyBytes is fully read into, throws on EOF
//        dataStream.readFully(bodyBytes);
//
//        return new String (bodyBytes, "UTF-8");

        int c;
        int read=0;
        byte[] buffer=new byte[1024];

        while((c = _in.read(buffer,0,1024))!=-1)
        {
            read+=c;
            //Do something with the readed content into the buffer, for example print it!
            System.out.println(new String(buffer,0,c));
            if(read>=contentLength)
                break;
        }
        return new String (buffer, "UTF-8");

    }
}
