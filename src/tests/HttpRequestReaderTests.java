import junit.framework.Assert;
import org.junit.Test;

public class HttpRequestReaderTests {

    static final String CONTENT = "note: LONGONELON\\n\\n\\nGONELONGONELONGOE";
    static final int CONTENT_LENGTH = CONTENT.length();

    static final String POST_STRING =
            "POST /servlet/hello HTTP/1.1\n" +
            "Host: localhost:8888" +
            "Connection: keep-alive" +
            "Content-Length: " + CONTENT_LENGTH + "\n" +
            "\n" +
            CONTENT;

    @Test
    void () {
//        ju


//        Assert.
    }
}
