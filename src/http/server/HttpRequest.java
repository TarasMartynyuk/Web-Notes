package http.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HttpRequest implements Request {

    //private final InputStream input;
    private final String _headers;
    private final String _uri;
    private final Map<String, String> _parameterMap;

    public HttpRequest(InputStream input) {
        //this.input = input;
        this._headers = convertInputStreamHeadersToString(input);

        int contentLength = parseContentLengthFromHeaders();

        this._uri = parseUri(_headers);
        this._parameterMap = parseParameterMap(_headers);
    }

    private String convertInputStreamHeadersToString(InputStream in) {
        var br = new BufferedReader(
                new InputStreamReader(in));

        var fullRequest = new StringBuilder();

        try {
            String line;
            while (true) {

                line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }

                fullRequest.append(line);
                fullRequest.append("\r\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return fullRequest.toString();
    }

    private String parseUri(String requestString) {

        if (requestString.isEmpty()) {
            return "";
        }

        int index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            int index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }

        return "";
    }

    private Map<String, String> parseParameterMap(String request) {

        String line = null;

//        while (line = _headers)
        // TODO
        return null;
    }

    @Override
    public String getURI() {
        return _uri;
    }

    @Override
    public String getParameter(String name) {
        return _parameterMap.get(name);
    }

    @Override
    public Set<String> getParameterNames() {
        return _parameterMap.keySet();
    }

    @Override
    public Collection<String> getParameterValues() {
        return _parameterMap.values();
    }

    @Override
    public String getHeadersAsText() {
        return _headers;
    }

    /**
     * @return 0 if no content length header provided, else header value
     */
    private int parseContentLengthFromHeaders() {
        int headerStartIndex = _headers.indexOf("Content-Length");

        if(headerStartIndex < 0) {
            return 0;
        }
        int offset = 16; // + 2 for : and space

        int headerEndIndex =  _headers.indexOf("\r\n", headerStartIndex);

        assert  headerEndIndex > 0;

        var valueStr = _headers.substring(headerStartIndex + offset, headerEndIndex);

        return Integer.parseInt(valueStr);
    }
}
