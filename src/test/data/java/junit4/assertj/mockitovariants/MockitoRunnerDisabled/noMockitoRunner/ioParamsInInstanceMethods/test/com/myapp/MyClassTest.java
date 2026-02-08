package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testParseInputStream() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = myClassUnderTest.parseInputStream(is);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testParseInputStream_EmptyIs() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final JsonDto result = myClassUnderTest.parseInputStream(is);

        // Verify the results
    }

    @Test
    public void testParseInputStream_BrokenIs() {
        // Setup
        final InputStream is = new InputStream() {
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

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.parseInputStream(is)).isInstanceOf(IOException.class);
    }

    @Test
    public void testParseReader() throws Exception {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = myClassUnderTest.parseReader(reader);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testParseReader_EmptyReader() throws Exception {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final JsonDto result = myClassUnderTest.parseReader(reader);

        // Verify the results
    }

    @Test
    public void testParseReader_BrokenReader() {
        // Setup
        final Reader reader = new Reader() {

            private final IOException exception = new IOException("Error");

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public boolean ready() throws IOException {
                throw exception;
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
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

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.parseReader(reader)).isInstanceOf(IOException.class);
    }

    @Test
    public void testSafeParseInputStream1() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testSafeParseInputStream1_EmptyIs() {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    public void testSafeParseInputStream1_BrokenIs() {
        // Setup
        final InputStream is = new InputStream() {
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

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is);

        // Verify the results
    }

    @Test
    public void testSafeParseReader1() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testSafeParseReader1_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader);

        // Verify the results
    }

    @Test
    public void testSafeParseReader1_BrokenReader() {
        // Setup
        final Reader reader = new Reader() {

            private final IOException exception = new IOException("Error");

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public boolean ready() throws IOException {
                throw exception;
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
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

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader);

        // Verify the results
    }

    @Test
    public void testSafeParseInputStream2() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is, 0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testSafeParseInputStream2_EmptyIs() {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    public void testSafeParseInputStream2_BrokenIs() {
        // Setup
        final InputStream is = new InputStream() {
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

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseInputStream(is, 0L);

        // Verify the results
    }

    @Test
    public void testSafeParseReader2() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader, 0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testSafeParseReader2_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader, 0L);

        // Verify the results
    }

    @Test
    public void testSafeParseReader2_BrokenReader() {
        // Setup
        final Reader reader = new Reader() {

            private final IOException exception = new IOException("Error");

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public boolean ready() throws IOException {
                throw exception;
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
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

        // Run the test
        final JsonDto result = myClassUnderTest.safeParseReader(reader, 0L);

        // Verify the results
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
        myClassUnderTest.writeToOs(jsonDto, outputStream);

        // Verify the results
    }

    @Test
    public void testWriteToOs_BrokenOutputStream() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final OutputStream outputStream = new OutputStream() {

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
        assertThatThrownBy(() -> myClassUnderTest.writeToOs(jsonDto, outputStream)).isInstanceOf(IOException.class);
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
        myClassUnderTest.writeToWriter(jsonDto, writer);

        // Verify the results
    }

    @Test
    public void testWriteToWriter_BrokenWriter() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final Writer writer = new Writer() {

            private final IOException exception = new IOException("Error");

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
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
        assertThatThrownBy(() -> myClassUnderTest.writeToWriter(jsonDto, writer)).isInstanceOf(IOException.class);
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
        myClassUnderTest.safeWriteToOs(jsonDto, outputStream);

        // Verify the results
    }

    @Test
    public void testSafeWriteToOs_BrokenOutputStream() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final OutputStream outputStream = new OutputStream() {

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
        myClassUnderTest.safeWriteToOs(jsonDto, outputStream);

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
        myClassUnderTest.safeWriteToWriter(jsonDto, writer);

        // Verify the results
    }

    @Test
    public void testSafeWriteToWriter_BrokenWriter() {
        // Setup
        final JsonDto jsonDto = new JsonDto();
        jsonDto.setName("name");
        jsonDto.setDisplayName("displayName");
        jsonDto.setId(0L);

        final Writer writer = new Writer() {

            private final IOException exception = new IOException("Error");

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
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
        myClassUnderTest.safeWriteToWriter(jsonDto, writer);

        // Verify the results
    }
}
