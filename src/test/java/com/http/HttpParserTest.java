package test.java.com.http;

import main.java.com.http.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HttpParserTest {

    @Test
    public void testParseValidHttpResponse() {
        String httpResponseData = """
                HTTP/1.1 200 OK\r
                Content-Type: text/html\r
                Content-Length: 123\r
                """;

        HttpResponse httpResponse = HttpParser.parse(httpResponseData);

        Assertions.assertNotNull(httpResponse);
        Assertions.assertEquals("1.1", httpResponse.getHttpStatus().getVersion());
        Assertions.assertEquals(200, httpResponse.getHttpStatus().getStatusCode());

        Assertions.assertEquals("text/html", httpResponse.getHeader("Content-Type").orElse(null));
        Assertions.assertEquals("123", httpResponse.getHeader("Content-Length").orElse(null));

        Assertions.assertEquals(0, httpResponse.getInvalidHeaderCount());
    }

    @Test
    public void testParseEmptyHttpResponse() {
        String httpResponseData = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpParser.parse(httpResponseData));
    }

    @Test
    public void testParseInvalidStatusLine() {
        String httpResponseData = """
                Invalid Status Line\r
                Content-Type: application/json\r
                """;

        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpParser.parse(httpResponseData));
    }

    @Test
    public void testParseInvalidHeaderLine() {
        String httpResponseData = """
                HTTP/1.1 404 Not Found\r
                Invalid Header Line\r
                Content-Length: 10\r
                """;

        HttpResponse httpResponse = HttpParser.parse(httpResponseData);

        Assertions.assertNotNull(httpResponse);
        Assertions.assertEquals(1, httpResponse.getValidHeaderCount());
        Assertions.assertEquals("10", httpResponse.getHeader("Content-Length").orElse(null));

        Assertions.assertEquals(1, httpResponse.getInvalidHeaderCount());
    }
}
