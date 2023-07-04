package main.java.com.http;

import lombok.Getter;
import java.util.Objects;

/**
 * Represents an HTTP header, consisting of a name and a value.
 */
public class HttpHeader {
    @Getter
    private final String name;
    @Getter
    private final String value;

    /**
     * Constructs an HttpHeader object with the specified header name and value.
     *
     * @param headerName  the name of the header
     * @param headerValue the value of the header
     */
    public HttpHeader(String headerName, String headerValue) {
        name = headerName;
        value = headerValue;
    }

    /**
     * Parses a header line and creates an HttpHeader object.
     *
     * @param httpHeaderLine the header line to parse
     * @return the parsed HttpHeader object
     * @throws IllegalArgumentException if the header line is invalid
     */
    public static HttpHeader parseHeaderLine(String httpHeaderLine) throws IllegalArgumentException {
        if (isValidHeader(httpHeaderLine)) {
            int colonIndex = httpHeaderLine.indexOf(": ");
            String headerName = httpHeaderLine.substring(0, colonIndex);
            String headerValue = httpHeaderLine.substring(colonIndex + 2);
            return new HttpHeader(headerName, headerValue);
        }
        throw new IllegalArgumentException("Invalid HTTP Header line: " + httpHeaderLine);
    }

    /**
     * Validates the header line format based on the rules:
     * - A header line consists of a header name, followed by ": ", followed by the header value
     * - A header name can contain any letter, digit, and the character "-"
     * - A header value can contain any visible/printable ASCII character
     *
     * @param header the header line to validate
     * @return {@code true} if the header line is valid, {@code false} otherwise
     */
    public static boolean isValidHeader(String header) {
        String headerPattern = "^[\\p{Alnum}-]+:\\s[\\p{Print}]+$";
        return header.matches(headerPattern);
    }

    /**
     * Checks whether this HttpHeader object is equal to another object.
     *
     * @param o the object to compare
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpHeader that = (HttpHeader) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }
}
