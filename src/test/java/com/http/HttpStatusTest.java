package test.java.com.http;

import main.java.com.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class HttpStatusTest {

    @Test
    public void testParseValidStatusLine() {

        HttpStatus expetedHttpStatus = new HttpStatus("1.1", 200, "OK");

        String statusLine = "HTTP/1.1 200 OK";
        HttpStatus httpStatus = HttpStatus.parseStatusLine(statusLine);

        Assertions.assertEquals(httpStatus, expetedHttpStatus);
    }

    @Test
    public void testHttpStatusEqual() {

        HttpStatus httpStatus1 = new HttpStatus("1.1", 200, "OK");
        HttpStatus httpStatus2 = new HttpStatus("1.2", 200, "OK");
        HttpStatus httpStatus3 = new HttpStatus("1.1", 201, "Created");
        HttpStatus httpStatus4 = new HttpStatus("1.1", 200, "Invalid");

        Assertions.assertNotEquals(httpStatus1, null);

        Assertions.assertNotEquals(httpStatus1, httpStatus2);
        Assertions.assertNotEquals(httpStatus1, httpStatus3);
        Assertions.assertNotEquals(httpStatus1, httpStatus4);
    }

    @Test
    public void testParseInvalidIncompleteStatusLine() {
        String statusLine = "1.1 500";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatus.parseStatusLine(statusLine));
    }

    @Test
    public void testParseInvalidStatusLine() {
        String statusLine = "1.1 500 Internal Server Error";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatus.parseStatusLine(statusLine));
    }

    @Test
    public void testParseInvalidVersion() {
        String statusLine = "1.1.1 200 OK";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatus.parseStatusLine(statusLine));

        String expectedMessage = "Invalid HTTP Status version: 1.1.1";
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testParseInvalidLongStatusCode() {
        String statusLine = "HTTP/1.1 9999 Invalid Status Code";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatus.parseStatusLine(statusLine));

        String expectedMessage = "Invalid HTTP status code: 9999";
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void testParseInvalidShortStatusCode() {
        String statusLine = "HTTP/1.1 99 Invalid Status Code";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpStatus.parseStatusLine(statusLine));
    }
}