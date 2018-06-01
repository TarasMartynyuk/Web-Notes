package http.server;
import java.util.Map;

public class HttpRequestParser {

    public String parseUri(String headersString) {

        if (headersString.isEmpty()) {
            return "";
        }

        int index1 = headersString.indexOf(' ');
        if (index1 != -1) {
            int index2 = headersString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return headersString.substring(index1 + 1, index2);
            }
        }

        return "";
    }

    /**
     * @return 0 if no content length header provided, else header value
     */
    public int parseContentLengthFromHeaders(String headers) {
        int headerStartIndex = headers.indexOf("Content-Length");

        if(headerStartIndex < 0) {
            return 0;
        }
        int offset = 16; // + 2 for : and space

        int headerEndIndex =  headers.indexOf("\r\n", headerStartIndex);

        assert  headerEndIndex > 0;

        var valueStr = headers.substring(headerStartIndex + offset, headerEndIndex);

        assert Integer.parseInt(valueStr) >= 0;
        return Integer.parseInt(valueStr);
    }

    public Map<String, String> parseBody(String body) {

        String line = null;

//        while (line = _headers)
        // TODO
        return null;
    }
}
