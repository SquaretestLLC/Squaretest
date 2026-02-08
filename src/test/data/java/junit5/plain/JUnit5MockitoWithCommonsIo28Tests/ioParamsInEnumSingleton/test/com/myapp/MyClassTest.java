package com.myapp;

import org.apache.commons.io.input.BrokenInputStream;
import org.apache.commons.io.input.BrokenReader;
import org.apache.commons.io.input.NullInputStream;
import org.apache.commons.io.input.NullReader;
import org.apache.commons.io.output.BrokenOutputStream;
import org.apache.commons.io.output.BrokenWriter;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    @Test
    void testParseInputStream() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = MyClass.INSTANCE.parseInputStream(is);

        // Verify the results
    }

    @Test
    void testParseInputStream_EmptyIs() throws Exception {
        // Setup
        final InputStream is = new NullInputStream();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.parseInputStream(is);

        // Verify the results
    }

    @Test
    void testParseInputStream_BrokenIs() {
        // Setup
        final InputStream is = new BrokenInputStream();

        // Run the test
        assertThrows(IOException.class, () -> MyClass.INSTANCE.parseInputStream(is));
    }

    @Test
    void testParseReader() throws Exception {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = MyClass.INSTANCE.parseReader(reader);

        // Verify the results
    }

    @Test
    void testParseReader_EmptyReader() throws Exception {
        // Setup
        final Reader reader = new NullReader();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.parseReader(reader);

        // Verify the results
    }

    @Test
    void testParseReader_BrokenReader() {
        // Setup
        final Reader reader = new BrokenReader();

        // Run the test
        assertThrows(IOException.class, () -> MyClass.INSTANCE.parseReader(reader));
    }

    @Test
    void testSafeParseInputStream1() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream1_EmptyIs() {
        // Setup
        final InputStream is = new NullInputStream();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream1_BrokenIs() {
        // Setup
        final InputStream is = new BrokenInputStream();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    void testSafeParseReader1() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseReader(reader);

        // Verify the results
    }

    @Test
    void testSafeParseReader1_EmptyReader() {
        // Setup
        final Reader reader = new NullReader();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseReader(reader);

        // Verify the results
    }

    @Test
    void testSafeParseReader1_BrokenReader() {
        // Setup
        final Reader reader = new BrokenReader();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseReader(reader);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2_EmptyIs() {
        // Setup
        final InputStream is = new NullInputStream();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2_BrokenIs() {
        // Setup
        final InputStream is = new BrokenInputStream();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseReader2() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseReader(reader, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseReader2_EmptyReader() {
        // Setup
        final Reader reader = new NullReader();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseReader(reader, 0L);

        // Verify the results
    }

    @Test
    void testSafeParseReader2_BrokenReader() {
        // Setup
        final Reader reader = new BrokenReader();

        // Run the test
        final JsonDto result = MyClass.INSTANCE.safeParseReader(reader, 0L);

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
        MyClass.INSTANCE.writeToOs(jsonDto, outputStream);

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
        assertThrows(IOException.class, () -> MyClass.INSTANCE.writeToOs(jsonDto, outputStream));
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
        MyClass.INSTANCE.writeToWriter(jsonDto, writer);

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
        assertThrows(IOException.class, () -> MyClass.INSTANCE.writeToWriter(jsonDto, writer));
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
        MyClass.INSTANCE.safeWriteToOs(jsonDto, outputStream);

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
        MyClass.INSTANCE.safeWriteToOs(jsonDto, outputStream);

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
        MyClass.INSTANCE.safeWriteToWriter(jsonDto, writer);

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
        MyClass.INSTANCE.safeWriteToWriter(jsonDto, writer);

        // Verify the results
    }

    @Test
    void testCopy() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testCopy_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new NullInputStream();
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testCopy_BrokenInput() {
        // Setup
        final InputStream input = new BrokenInputStream();
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        assertThrows(IOException.class, () -> MyClass.copy(input, output));
    }

    @Test
    void testCopy_BrokenOutput() {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new BrokenOutputStream();

        // Run the test
        assertThrows(IOException.class, () -> MyClass.copy(input, output));
    }
}
