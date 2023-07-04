package main.java.com.http;

// Decision explanation: In a more real scenario, this can be an enum class, and we could reuse a third party library
// here we create our own class with the rules defined in the assignment

import lombok.Getter;

/**
 * Represents an HTTP status, consisting of the HTTP version, status code, and reason phrase.
 * Uses custom validation of the HTTP status.
 */
public class HttpStatus {
    @Getter
    private final String version;
    @Getter
    private final int statusCode;
    private final String reasonPhrase;

    /**
     * Constructs an HTTPStatus object with the specified version, status code, and reason phrase.
     *
     * @param version      the HTTP version
     * @param statusCode   the status code
     * @param reasonPhrase the reason phrase
     */
    public HttpStatus(String version, int statusCode, String reasonPhrase) {
        this.version = version;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Parses a status line and creates an HTTPStatus object.
     *
     * @param httpStatusLine the status line to parse
     * @return the parsed main.java.com.http.HTTPStatus object
     * @throws IllegalArgumentException if the status line is invalid or contains an invalid status code (not 3 digits)
     */
    public static HttpStatus parseStatusLine(String httpStatusLine) throws IllegalArgumentException {
        String[] parts = httpStatusLine.split(" ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid HTTP status line: " + httpStatusLine);
        }

        String version = parseVersion(parts[0]);
        int statusCode = Integer.parseInt(parts[1]);

        // Rule from description: The status code is a 3 digit numeric code
        if (statusCode < 100 || statusCode > 999)
            throw new IllegalArgumentException("Invalid HTTP status code: " + statusCode);
        String reasonPhrase = parts[2];

        return new HttpStatus(version, statusCode, reasonPhrase);
    }

    /**
     * Parses the HTTP version from the status line.
     *
     * @param version the HTTP version string
     * @return the parsed HTTP version
     * @throws IllegalArgumentException if the HTTP version is invalid
     */
    private static String parseVersion(String version) throws IllegalArgumentException {
        if (!isValidHTTPVersion(version))
            throw new IllegalArgumentException("Invalid HTTP Status version: " + version);

        return version.substring(5);
    }

    /**
     * Validates the HTTP version format based on the rule:
     * - The version is the case-sensitive string “HTTP/” followed by a major and
     * minor version (e.g. HTTP/1.1)
     *
     * @param httpVersion the HTTP version string
     * @return {@code true} if the HTTP version is valid, {@code false} otherwise
     */
    private static boolean isValidHTTPVersion(String httpVersion) {
        String versionPattern = "HTTP/\\d+\\.\\d+";
        return httpVersion.matches(versionPattern);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two HTTPStatus objects are considered equal if they have the same version, status code, and reason phrase.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HttpStatus that = (HttpStatus) o;
        return statusCode == that.statusCode &&
                version.equals(that.version) &&
                reasonPhrase.equals(that.reasonPhrase);
    }
}
