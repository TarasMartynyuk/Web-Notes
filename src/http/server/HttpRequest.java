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
    private final String request;
    private final String uri;
    private final Map<String, String> parameterMap;

    public HttpRequest(InputStream input) {
        //this.input = input;
        this.request = convertInputStreamToString(input);
        this.uri = parseUri(request);
        this.parameterMap = parseParameterMap(request);


    }

    private String convertInputStreamToString(InputStream in) {
        var br = new BufferedReader(
                new InputStreamReader(in));

        var fullRequest = new StringBuilder();

        try {
            while (true) {
                String line = br.readLine();
                if ((line == null) || line.isEmpty()) {
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

//        while (line = request)
        // TODO
        return null;
    }

    @Override
    public String getURI() {
        return uri;
    }

    @Override
    public String getParameter(String name) {
        return parameterMap.get(name);
    }

    @Override
    public Set<String> getParameterNames() {
        return parameterMap.keySet();
    }

    @Override
    public Collection<String> getParameterValues() {
        return parameterMap.values();
    }

    @Override
    public String getRequestAsText() {
        return request;
    }

}
