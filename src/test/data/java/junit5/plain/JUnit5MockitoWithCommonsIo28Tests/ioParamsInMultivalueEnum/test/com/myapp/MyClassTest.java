package com.myapp;

import org.apache.commons.io.input.BrokenInputStream;
import org.apache.commons.io.input.NullInputStream;
import org.apache.commons.io.output.BrokenOutputStream;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    @Test
    void testParseInputStream() throws Exception {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.parseInputStream(new ByteArrayInputStream("content".getBytes())));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.parseInputStream(new ByteArrayInputStream("content".getBytes())));
    }

    @Test
    void testParseReader() throws Exception {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.parseReader(new StringReader("content")));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.parseReader(new StringReader("content")));
    }

    @Test
    void testSafeParseInputStream1() {
        assertEquals(new JsonDto(),
                MyClass.INSTANCE1.safeParseInputStream(new ByteArrayInputStream("content".getBytes())));
        assertEquals(new JsonDto(),
                MyClass.INSTANCE2.safeParseInputStream(new ByteArrayInputStream("content".getBytes())));
    }

    @Test
    void testSafeParseReader1() {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.safeParseReader(new StringReader("content")));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.safeParseReader(new StringReader("content")));
    }

    @Test
    void testSafeParseInputStream2() {
        assertEquals(new JsonDto(),
                MyClass.INSTANCE1.safeParseInputStream(new ByteArrayInputStream("content".getBytes()), 0L));
        assertEquals(new JsonDto(),
                MyClass.INSTANCE2.safeParseInputStream(new ByteArrayInputStream("content".getBytes()), 0L));
    }

    @Test
    void testSafeParseReader2() {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.safeParseReader(new StringReader("content"), 0L));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.safeParseReader(new StringReader("content"), 0L));
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
        MyClass.INSTANCE1.writeToOs(jsonDto, outputStream);
        MyClass.INSTANCE2.writeToOs(jsonDto, outputStream);

        // Verify the results
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
        MyClass.INSTANCE1.writeToWriter(jsonDto, writer);
        MyClass.INSTANCE2.writeToWriter(jsonDto, writer);

        // Verify the results
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
        MyClass.INSTANCE1.safeWriteToOs(jsonDto, outputStream);
        MyClass.INSTANCE2.safeWriteToOs(jsonDto, outputStream);

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
        MyClass.INSTANCE1.safeWriteToWriter(jsonDto, writer);
        MyClass.INSTANCE2.safeWriteToWriter(jsonDto, writer);

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
