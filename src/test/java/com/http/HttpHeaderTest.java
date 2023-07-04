package test.java.com.http;

import main.java.com.http.HttpHeader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HttpHeaderTest {
    @Test
    public void testParseValidHeaderLine() {
        String headerLine = "Content-Type: application/json";
        HttpHeader header = new HttpHeader("Content-Type", "application/json");
        Assertions.assertEquals(header, HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseHeaderLineWithoutValue() {
        String headerLine = "InvalidHeaderLine";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseEmptyHeaderLine() {
        String headerLine = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseHeaderLineWithInvalidValue() {
        String headerLine = "Header: ";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseHeaderLineWithMultipleValues() {
        String headerLine = "Header: value_part1 part2";
        HttpHeader header = new HttpHeader("Header", "value_part1 part2");
        Assertions.assertEquals(header, HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseHeaderLineWithTabInTheValue() {
        String headerLine = "Header: value1\tvalue2";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseHeaderLineWithLeadingWhitespace() {
        String headerLine = "   Header: value";
        Assertions.assertThrows(IllegalArgumentException.class, () -> HttpHeader.parseHeaderLine(headerLine));
    }

    @Test
    public void testParseHeaderLineWithTrailingWhitespace() {
        String headerLine = "Header: value   ";
        HttpHeader header = new HttpHeader("Header", "value   ");
        Assertions.assertEquals(header, HttpHeader.parseHeaderLine(headerLine));
    }
}