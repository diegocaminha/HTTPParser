package main.java.com.http;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents an HTTP response, consisting of an HTTP status, headers, and additional information.
 */
public class HttpResponse {

    @Getter
    private final HttpStatus httpStatus;
    private final Map<String, String> headers;
    private int invalidHeaderCount = 0;

    /**
     * Constructs an HttpResponse object with the specified HTTP status.
     *
     * @param httpStatus the HTTP status
     */
    public HttpResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        headers = new HashMap<>();
    }

    /**
     * Adds a header to the response.
     *
     * @param name  the name of the header
     * @param value the value of the header
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * Retrieves the value of a header by its name.
     *
     * @param name the name of the header
     * @return an Optional containing the header value, or an empty Optional if the header is not found
     */
    public Optional<String> getHeader(String name) {
        return Optional.ofNullable(headers.get(name));
    }

    /**
     * Increments the count of invalid headers.
     */
    public void incrementInvalidHeaders() {
        invalidHeaderCount++;
    }

    /**
     * Get the count of valid headers.
     */
    public int getValidHeaderCount() {
        return invalidHeaderCount;
    }

    /**
     * Get the count of invalid headers.
     */
    public int getInvalidHeaderCount() {
        return invalidHeaderCount;
    }

    /**
     * Prints a summary of the HTTP response, including the HTTP version, status, number of valid headers,
     * and number of invalid headers.
     */
    public void printSummary() {
        System.out.println("HTTP Version: " + httpStatus.getVersion());
        System.out.println("Status: " + httpStatus.getStatusCode());
        System.out.println("Number of valid headers: " + headers.size());
        System.out.println("Number of invalid headers: " + invalidHeaderCount);
    }


}
