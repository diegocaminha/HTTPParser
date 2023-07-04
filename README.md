# HTTPParser for solving the Broadcom code assignment

## Assumptions and notes
- The solution was broken in different classes to better organize the code as we usually see in production code.
- Tried to follow the parsing rules as close as possible to the description which might not match with real HTTP responses.
- Unit tests should be covering most of the code and can be used as reference of code usage.
- We parse a String per time.
- A few anotations were used to avoid boilerplate code.
- The code is building and runing fine.
- Code was created using IntelliJ and only the source files were uploaded.

## Description
- Write an HTTP parser to parse an HTTP response status line and headers.
- The detailed HTTP message format is described here: [RFC 7230 Section 3](https://datatracker.ietf.org/doc/html/rfc7230#section-3), but for the purpose of this parser, we will assume a simplified format as described below:
  - Each line in the HTTP response should be terminated with a CR LF sequence.
  - The HTTP response will begin with a status line, followed by zero or more header lines.
  - A status line consists of the HTTP version, status code, and reason phrase, each separated by a space.
    - The version is the case-sensitive string "HTTP/" followed by a major and minor version (e.g., HTTP/1.1).
    - The status code is a 3-digit numeric code.
    - The reason phrase is a string describing the status code.
  - A header line consists of a header name, followed by ": ", followed by the header value.
    - A header name can contain any letter, digit, and the character "-".
    - A header value can contain any visible/printable ASCII character.
- The input would be a contiguous buffer of data with the header part of an HTTP response. The parser should extract the HTTP version and status code from the status line and parse each header line to determine if the header is valid according to the rules described above.
- The parser should store the valid headers and provide a way to look up a header value by name.
- The parser should output the HTTP version and status code, as well as the number of valid and invalid headers. If the response does not have a valid status line, then the parser can output an error message and abort parsing.
