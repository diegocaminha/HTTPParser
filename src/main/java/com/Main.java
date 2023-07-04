package main.java.com;

import main.java.com.http.HttpParser;
import main.java.com.http.HttpResponse;

// For more examples of use, look at the unit test files.

/**
 * The Main class represents the entry point of the application. It demonstrates the usage of the HttpParser
 * and HttpResponse classes to parse and process an HTTP response.
 */
public class Main {
    public static void main(String[] args) {
        String[] httpData = new String[]{"""
HTTP/1.0 200 OK\r
cache-control: public\r
content-length: 0\r
content-type: image/svg+xml\r
date: Tue, 22 Jun 2021 22:24:42 GMT\r
""",

                """
HTTP/1.1 302 Found\r
cache-control: public\r
Transfer-encoding: chunked\r
invalid_header\r
date: Tue, 22 Jun 2021 22:24:42 GMT\r
""",

                """
Header1: value1
\r
date: Tue, 22 Jun 2021 22:24:42 GMT\r
content-length: 1337
\r
"""
        };
        for (String http : httpData) {
            try {
                HttpResponse httpResponse = HttpParser.parse(http);
                httpResponse.printSummary();

                // Example of how to get header
                // httpResponse.getHeader("content-type").orElse("No header");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status line");
            }
        }
    }
}