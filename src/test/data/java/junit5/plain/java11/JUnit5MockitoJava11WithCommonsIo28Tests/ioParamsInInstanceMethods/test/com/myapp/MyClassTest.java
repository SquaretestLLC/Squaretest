package com.myapp;

import org.apache.commons.io.input.BrokenInputStream;
import org.apache.commons.io.input.BrokenReader;
import org.apache.commons.io.output.BrokenOutputStream;
import org.apache.commons.io.output.BrokenWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testParseInputStream() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = myClassUnderTest.parseInputStream(is);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testParseInputStream_EmptyIs() throws Exception {
        // Setup
        final InputStream is = InputStream.nullInputStream();

        // Run the test
        final JsonDto result = myClassUnderTest.parseInputStream(is);

        // Verify the results
    }

    @Test
    void testParseInputStream_BrokenIs() {
        // Setup
        final InputStream is = new BrokenInputStream();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.parseInputStream(is));
    }

    @Test
    void testParseReader() throws Exception {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = myClassUnderTest.parseReader(reader);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testParseReader_EmptyReader() throws Exception {
        // Setup
        final Reader reader = Reader.nullReader();

        // Run the test
        final JsonDto result = myClassUnderTest.parseReader(reader);

        // Verify the results
    }

    @Test
    void testParseReader_BrokenReader() {
        // Setup
        final Reader reader = new BrokenReader();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.parseReader(reader));
    }

    @Test
    void testSafeParseInputStream1() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testSafeParseInputStream1_EmptyIs() {
        // Setup
        final InputStream is = InputStream.nullInputStream();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream1_BrokenIs() {
        // Setup
        final InputStream is = new BrokenInputStream();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    void testSafeParseReader1() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testSafeParseReader1_EmptyReader() {
        // Setup
        final Reader reader = Reader.nullReader();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader);

        // Verify the results
    }

    @Test
    void testSafeParseReader1_BrokenReader() {
        // Setup
        final Reader reader = new BrokenReader();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is, 0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testSafeParseInputStream2_EmptyIs() {
        // Setup
        final InputStream is = InputStream.nullInputStream();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2_BrokenIs() {
        // Setup
        final InputStream is = new BrokenInputStream();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseReader2() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader, 0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testSafeParseReader2_EmptyReader() {
        // Setup
        final Reader reader = Reader.nullReader();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseReader2_BrokenReader() {
        // Setup
        final Reader reader = new BrokenReader();

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader, 0L);

        // Verify the results
    }

    @Test
    void testWriteToOs() throws Exception {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        myClassUnderTest.writeToOs(jsonDto, outputStream);

        // Verify the results
    }

    @Test
    void testWriteToOs_BrokenOutputStream() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.writeToOs(jsonDto, outputStream));
    }

    @Test
    void testWriteToWriter() throws Exception {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final Writer writer = new StringWriter();

        // Run the test
        myClassUnderTest.writeToWriter(jsonDto, writer);

        // Verify the results
    }

    @Test
    void testWriteToWriter_BrokenWriter() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final Writer writer = new BrokenWriter();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.writeToWriter(jsonDto, writer));
    }

    @Test
    void testSafeWriteToOs() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        myClassUnderTest.safeWriteToOs(jsonDto, outputStream);

        // Verify the results
    }

    @Test
    void testSafeWriteToOs_BrokenOutputStream() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        myClassUnderTest.safeWriteToOs(jsonDto, outputStream);

        // Verify the results
    }

    @Test
    void testSafeWriteToWriter() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final Writer writer = new StringWriter();

        // Run the test
        myClassUnderTest.safeWriteToWriter(jsonDto, writer);

        // Verify the results
    }

    @Test
    void testSafeWriteToWriter_BrokenWriter() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final Writer writer = new BrokenWriter();

        // Run the test
        myClassUnderTest.safeWriteToWriter(jsonDto, writer);

        // Verify the results
    }
}
