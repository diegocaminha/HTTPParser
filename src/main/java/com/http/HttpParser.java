package main.java.com.http;

/**
 * A utility class for parsing HTTP responses.
 */
public class HttpParser {
    /**
     * Parses the given data and creates an `HttpResponse` object.
     *
     * @param data the HTTP response data to parse
     * @return the parsed `HttpResponse` object
     * @throws IllegalArgumentException if the data is invalid or cannot be parsed
     */
    public static HttpResponse parse(String data) throws IllegalArgumentException {
        // The assignment asked for \r\n, but we use \R here to be SO independent
        String[] lines = data.split("\\R");

        HttpStatus httpStatus = HttpStatus.parseStatusLine(lines[0]);
        HttpResponse httpResponse = new HttpResponse(httpStatus);
        for (int i = 1; i < lines.length; i++) {
            if (HttpHeader.isValidHeader(lines[i])) {
                HttpHeader httpHeader = HttpHeader.parseHeaderLine(lines[i]);
                httpResponse.addHeader(httpHeader.getName(), httpHeader.getValue());
            } else {
                httpResponse.incrementInvalidHeaders();
            }
        }
        return httpResponse;
    }
}
