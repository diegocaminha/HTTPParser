package test.java.com.http;

import main.java.com.http.HttpResponse;
import main.java.com.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

public class HttpResponseTest {

    private HttpResponse httpResponse;

    @BeforeEach
    public void setup() {
        HttpStatus httpStatus = new HttpStatus("HTTP/1.1", 200, "OK");
        httpResponse = new HttpResponse(httpStatus);
    }

    @Test
    public void testAddHeader() {
        httpResponse.addHeader("Content-Type", "application/json");
        httpResponse.addHeader("Cache-Control", "no-cache");

        Optional<String> contentTypeHeader = httpResponse.getHeader("Content-Type");
        Optional<String> cacheControlHeader = httpResponse.getHeader("Cache-Control");
        Optional<String> invalidHeader = httpResponse.getHeader("Invalid-Header");

        Assertions.assertEquals("application/json", contentTypeHeader.orElse(null));
        Assertions.assertEquals("no-cache", cacheControlHeader.orElse(null));
        Assertions.assertTrue(invalidHeader.isEmpty());
    }

    @Test
    public void testPrintSummary() {

        httpResponse.addHeader("Content-Type", "application/json");
        httpResponse.addHeader("Cache-Control", "no-cache");
        httpResponse.incrementInvalidHeaders();
        httpResponse.incrementInvalidHeaders();

        // Capture the output to verify later
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        httpResponse.printSummary();

        // Reset the standard output
        System.out.flush();
        System.setOut(originalOut);

        String expectedOutput = "HTTP Version: HTTP/1.1" + System.lineSeparator() +
                "Status: 200" + System.lineSeparator() +
                "Number of valid headers: 2" + System.lineSeparator() +
                "Number of invalid headers: 2" + System.lineSeparator();

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }
}

