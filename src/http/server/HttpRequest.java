package http.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HttpRequest implements Request {

    private final String _headers;
    private final String _body;
    private final String _uri;
    private final Map<String, String> _bodyMap;

    public HttpRequest(InputStream input) throws IOException {
        var parser = new HttpRequestParser();
        var reader = new HttpRequestReader(input);

        _headers = reader.readHeaders();

        int contentLength = parser.parseContentLengthFromHeaders(_headers);

        this._uri = parser.parseUri(_headers);

        _body = contentLength == 0 ?
                null : reader.readBody();
        this._bodyMap = parser.parseBody(_headers);
    }

    @Override
    public String getURI() {
        return _uri;
    }

    @Override
    public String getParameter(String name) {
        return _bodyMap.get(name);
    }

    @Override
    public Set<String> getParameterNames() {
        return _bodyMap.keySet();
    }

    @Override
    public Collection<String> getParameterValues() {
        return _bodyMap.values();
    }

    @Override
    public String getRequestAsText() {
        return _headers + "\n" + _body;
    }
}
