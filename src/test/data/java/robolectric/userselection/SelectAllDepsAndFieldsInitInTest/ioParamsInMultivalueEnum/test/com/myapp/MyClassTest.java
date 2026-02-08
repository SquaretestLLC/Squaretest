package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.*;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testParseInputStream() throws Exception {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.parseInputStream(new ByteArrayInputStream("content".getBytes())));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.parseInputStream(new ByteArrayInputStream("content".getBytes())));
    }

    @Test
    public void testParseReader() throws Exception {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.parseReader(new StringReader("content")));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.parseReader(new StringReader("content")));
    }

    @Test
    public void testSafeParseInputStream1() {
        assertEquals(new JsonDto(),
                MyClass.INSTANCE1.safeParseInputStream(new ByteArrayInputStream("content".getBytes())));
        assertEquals(new JsonDto(),
                MyClass.INSTANCE2.safeParseInputStream(new ByteArrayInputStream("content".getBytes())));
    }

    @Test
    public void testSafeParseReader1() {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.safeParseReader(new StringReader("content")));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.safeParseReader(new StringReader("content")));
    }

    @Test
    public void testSafeParseInputStream2() {
        assertEquals(new JsonDto(),
                MyClass.INSTANCE1.safeParseInputStream(new ByteArrayInputStream("content".getBytes()), 0L));
        assertEquals(new JsonDto(),
                MyClass.INSTANCE2.safeParseInputStream(new ByteArrayInputStream("content".getBytes()), 0L));
    }

    @Test
    public void testSafeParseReader2() {
        assertEquals(new JsonDto(), MyClass.INSTANCE1.safeParseReader(new StringReader("content"), 0L));
        assertEquals(new JsonDto(), MyClass.INSTANCE2.safeParseReader(new StringReader("content"), 0L));
    }

    @Test
    public void testWriteToOs() throws Exception {
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
    public void testWriteToWriter() throws Exception {
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
    public void testSafeWriteToOs() {
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
    public void testSafeWriteToWriter() {
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
    public void testCopy() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testCopy_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test(expected = IOException.class)
    public void testCopy_BrokenInput() throws Exception {
        // Setup
        final InputStream input = new InputStream() {
            private final IOException exception = new IOException("Error");

            @Override
            public int read() throws IOException {
                throw exception;
            }

            @Override
            public int available() throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception;
            }

            @Override
            public void close() throws IOException {
                throw exception;
            }
        };
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output);
    }

    @Test(expected = IOException.class)
    public void testCopy_BrokenOutput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new OutputStream() {

            private final IOException exception = new IOException("Error");

            @Override
            public void write(final int b) throws IOException {
                throw exception;
            }

            @Override
            public void flush() throws IOException {
                throw exception;
            }

            @Override
            public void close() throws IOException {
                throw exception;
            }
        };

        // Run the test
        MyClass.copy(input, output);
    }
}
