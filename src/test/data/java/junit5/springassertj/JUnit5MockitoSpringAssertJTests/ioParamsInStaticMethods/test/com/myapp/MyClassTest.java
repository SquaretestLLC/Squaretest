package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class MyClassTest {

    @Test
    void testBuffer1() {
        // Setup
        final InputStream inputStream = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final BufferedInputStream result = MyClass.buffer(inputStream);

        // Verify the results
    }

    @Test
    void testBuffer1_EmptyInputStream() {
        // Setup
        final InputStream inputStream = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final BufferedInputStream result = MyClass.buffer(inputStream);

        // Verify the results
    }

    @Test
    void testBuffer1_BrokenInputStream() {
        // Setup
        final InputStream inputStream = new InputStream() {
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
        final BufferedInputStream result = MyClass.buffer(inputStream);

        // Verify the results
    }

    @Test
    void testBuffer2() {
        // Setup
        final InputStream inputStream = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final BufferedInputStream result = MyClass.buffer(inputStream, 0);

        // Verify the results
    }

    @Test
    void testBuffer2_EmptyInputStream() {
        // Setup
        final InputStream inputStream = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final BufferedInputStream result = MyClass.buffer(inputStream, 0);

        // Verify the results
    }

    @Test
    void testBuffer2_BrokenInputStream() {
        // Setup
        final InputStream inputStream = new InputStream() {
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
        final BufferedInputStream result = MyClass.buffer(inputStream, 0);

        // Verify the results
    }

    @Test
    void testBuffer3() {
        // Setup
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final BufferedOutputStream result = MyClass.buffer(outputStream);

        // Verify the results
    }

    @Test
    void testBuffer3_BrokenOutputStream() {
        // Setup
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
        final BufferedOutputStream result = MyClass.buffer(outputStream);

        // Verify the results
    }

    @Test
    void testBuffer4() {
        // Setup
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final BufferedOutputStream result = MyClass.buffer(outputStream, 0);

        // Verify the results
    }

    @Test
    void testBuffer4_BrokenOutputStream() {
        // Setup
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
        final BufferedOutputStream result = MyClass.buffer(outputStream, 0);

        // Verify the results
    }

    @Test
    void testBuffer5() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final BufferedReader result = MyClass.buffer(reader);

        // Verify the results
    }

    @Test
    void testBuffer5_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final BufferedReader result = MyClass.buffer(reader);

        // Verify the results
    }

    @Test
    void testBuffer5_BrokenReader() {
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
        final BufferedReader result = MyClass.buffer(reader);

        // Verify the results
    }

    @Test
    void testBuffer6() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final BufferedReader result = MyClass.buffer(reader, 0);

        // Verify the results
    }

    @Test
    void testBuffer6_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final BufferedReader result = MyClass.buffer(reader, 0);

        // Verify the results
    }

    @Test
    void testBuffer6_BrokenReader() {
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
        final BufferedReader result = MyClass.buffer(reader, 0);

        // Verify the results
    }

    @Test
    void testBuffer7() {
        // Setup
        final Writer writer = new StringWriter();

        // Run the test
        final BufferedWriter result = MyClass.buffer(writer);

        // Verify the results
    }

    @Test
    void testBuffer7_BrokenWriter() {
        // Setup
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
        final BufferedWriter result = MyClass.buffer(writer);

        // Verify the results
    }

    @Test
    void testBuffer8() {
        // Setup
        final Writer writer = new StringWriter();

        // Run the test
        final BufferedWriter result = MyClass.buffer(writer, 0);

        // Verify the results
    }

    @Test
    void testBuffer8_BrokenWriter() {
        // Setup
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
        final BufferedWriter result = MyClass.buffer(writer, 0);

        // Verify the results
    }

    @Test
    void testClose1() throws Exception {
        // Setup
        final Closeable closeable = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.close(closeable);

        // Verify the results
    }

    @Test
    void testClose1_EmptyCloseable() throws Exception {
        // Setup
        final Closeable closeable = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.close(closeable);

        // Verify the results
    }

    @Test
    void testClose1_BrokenCloseable() {
        // Setup
        final Closeable closeable = new InputStream() {
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
        assertThatThrownBy(() -> MyClass.close(closeable)).isInstanceOf(IOException.class);
    }

    @Test
    void testClose2() throws Exception {
        // Setup
        final Closeable closeables = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.close(closeables);

        // Verify the results
    }

    @Test
    void testClose2_EmptyCloseables() throws Exception {
        // Setup
        final Closeable closeables = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.close(closeables);

        // Verify the results
    }

    @Test
    void testClose2_BrokenCloseables() {
        // Setup
        final Closeable closeables = new InputStream() {
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
        assertThatThrownBy(() -> MyClass.close(closeables)).isInstanceOf(IOException.class);
    }

    @Test
    void testClose3() throws Exception {
        // Setup
        final Closeable closeable = new ByteArrayInputStream("content".getBytes());
        final IOConsumer<IOException> consumer = null;

        // Run the test
        MyClass.close(closeable, consumer);

        // Verify the results
    }

    @Test
    void testClose3_EmptyCloseable() throws Exception {
        // Setup
        final Closeable closeable = new ByteArrayInputStream(new byte[]{});
        final IOConsumer<IOException> consumer = null;

        // Run the test
        MyClass.close(closeable, consumer);

        // Verify the results
    }

    @Test
    void testClose3_BrokenCloseable() {
        // Setup
        final Closeable closeable = new InputStream() {
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
        final IOConsumer<IOException> consumer = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.close(closeable, consumer)).isInstanceOf(IOException.class);
    }

    @Test
    void testClose4() {
        // Setup
        final URLConnection conn = null;

        // Run the test
        MyClass.close(conn);

        // Verify the results
    }

    @Test
    void testCloseQuietly1() {
        // Setup
        final Closeable closeable = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.closeQuietly(closeable);

        // Verify the results
    }

    @Test
    void testCloseQuietly1_EmptyCloseable() {
        // Setup
        final Closeable closeable = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.closeQuietly(closeable);

        // Verify the results
    }

    @Test
    void testCloseQuietly1_BrokenCloseable() {
        // Setup
        final Closeable closeable = new InputStream() {
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
        MyClass.closeQuietly(closeable);

        // Verify the results
    }

    @Test
    void testCloseQuietly2() {
        // Setup
        final Closeable closeables = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.closeQuietly(closeables);

        // Verify the results
    }

    @Test
    void testCloseQuietly2_EmptyCloseables() {
        // Setup
        final Closeable closeables = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.closeQuietly(closeables);

        // Verify the results
    }

    @Test
    void testCloseQuietly2_BrokenCloseables() {
        // Setup
        final Closeable closeables = new InputStream() {
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
        MyClass.closeQuietly(closeables);

        // Verify the results
    }

    @Test
    void testCloseQuietly3() {
        // Setup
        final Closeable closeable = new ByteArrayInputStream("content".getBytes());
        final Consumer<IOException> mockConsumer = mock(Consumer.class);

        // Run the test
        MyClass.closeQuietly(closeable, mockConsumer);

        // Verify the results
    }

    @Test
    void testCloseQuietly3_EmptyCloseable() {
        // Setup
        final Closeable closeable = new ByteArrayInputStream(new byte[]{});
        final Consumer<IOException> mockConsumer = mock(Consumer.class);

        // Run the test
        MyClass.closeQuietly(closeable, mockConsumer);

        // Verify the results
    }

    @Test
    void testCloseQuietly3_BrokenCloseable() {
        // Setup
        final Closeable closeable = new InputStream() {
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
        final Consumer<IOException> mockConsumer = mock(Consumer.class);

        // Run the test
        MyClass.closeQuietly(closeable, mockConsumer);

        // Verify the results
    }

    @Test
    void testCloseQuietly4() {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.closeQuietly(input);

        // Verify the results
    }

    @Test
    void testCloseQuietly4_EmptyInput() {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.closeQuietly(input);

        // Verify the results
    }

    @Test
    void testCloseQuietly4_BrokenInput() {
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

        // Run the test
        MyClass.closeQuietly(input);

        // Verify the results
    }

    @Test
    void testCloseQuietly5() {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.closeQuietly(output);

        // Verify the results
    }

    @Test
    void testCloseQuietly5_BrokenOutput() {
        // Setup
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
        MyClass.closeQuietly(output);

        // Verify the results
    }

    @Test
    void testCloseQuietly6() {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        MyClass.closeQuietly(input);

        // Verify the results
    }

    @Test
    void testCloseQuietly6_EmptyInput() {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        MyClass.closeQuietly(input);

        // Verify the results
    }

    @Test
    void testCloseQuietly6_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        MyClass.closeQuietly(input);

        // Verify the results
    }

    @Test
    void testCloseQuietly7() throws Exception {
        // Setup
        final Selector selector = Selector.open();

        // Run the test
        MyClass.closeQuietly(selector);

        // Verify the results
    }

    @Test
    void testCloseQuietly8() throws Exception {
        // Setup
        final ServerSocket serverSocket = new ServerSocket(0, 0, InetAddress.getByName("localhost"));

        // Run the test
        MyClass.closeQuietly(serverSocket);

        // Verify the results
    }

    @Test
    void testCloseQuietly9() throws Exception {
        // Setup
        final Socket socket = new Socket("host", 80);

        // Run the test
        MyClass.closeQuietly(socket);

        // Verify the results
    }

    @Test
    void testCloseQuietly10() {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.closeQuietly(output);

        // Verify the results
    }

    @Test
    void testCloseQuietly10_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        MyClass.closeQuietly(output);

        // Verify the results
    }

    @Test
    void testConsume() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final long result = MyClass.consume(input);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testConsume_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final long result = MyClass.consume(input);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testConsume_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.consume(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testContentEquals1() throws Exception {
        // Setup
        final InputStream input1 = new ByteArrayInputStream("content".getBytes());
        final InputStream input2 = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final boolean result = MyClass.contentEquals(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEquals1_EmptyInput1() throws Exception {
        // Setup
        final InputStream input1 = new ByteArrayInputStream(new byte[]{});
        final InputStream input2 = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final boolean result = MyClass.contentEquals(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEquals1_BrokenInput1() {
        // Setup
        final InputStream input1 = new InputStream() {
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
        final InputStream input2 = new ByteArrayInputStream("content".getBytes());

        // Run the test
        assertThatThrownBy(() -> MyClass.contentEquals(input1, input2)).isInstanceOf(IOException.class);
    }

    @Test
    void testContentEquals1_EmptyInput2() throws Exception {
        // Setup
        final InputStream input1 = new ByteArrayInputStream("content".getBytes());
        final InputStream input2 = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final boolean result = MyClass.contentEquals(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEquals1_BrokenInput2() {
        // Setup
        final InputStream input1 = new ByteArrayInputStream("content".getBytes());
        final InputStream input2 = new InputStream() {
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
        assertThatThrownBy(() -> MyClass.contentEquals(input1, input2)).isInstanceOf(IOException.class);
    }

    @Test
    void testContentEquals2() throws Exception {
        // Setup
        final Reader input1 = new StringReader("content");
        final Reader input2 = new StringReader("content");

        // Run the test
        final boolean result = MyClass.contentEquals(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEquals2_EmptyInput1() throws Exception {
        // Setup
        final Reader input1 = new StringReader("");
        final Reader input2 = new StringReader("content");

        // Run the test
        final boolean result = MyClass.contentEquals(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEquals2_BrokenInput1() {
        // Setup
        final Reader input1 = new Reader() {

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
        final Reader input2 = new StringReader("content");

        // Run the test
        assertThatThrownBy(() -> MyClass.contentEquals(input1, input2)).isInstanceOf(IOException.class);
    }

    @Test
    void testContentEquals2_EmptyInput2() throws Exception {
        // Setup
        final Reader input1 = new StringReader("content");
        final Reader input2 = new StringReader("");

        // Run the test
        final boolean result = MyClass.contentEquals(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEquals2_BrokenInput2() {
        // Setup
        final Reader input1 = new StringReader("content");
        final Reader input2 = new Reader() {

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
        assertThatThrownBy(() -> MyClass.contentEquals(input1, input2)).isInstanceOf(IOException.class);
    }

    @Test
    void testContentEqualsIgnoreEOL() throws Exception {
        // Setup
        final Reader input1 = new StringReader("content");
        final Reader input2 = new StringReader("content");

        // Run the test
        final boolean result = MyClass.contentEqualsIgnoreEOL(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEqualsIgnoreEOL_EmptyInput1() throws Exception {
        // Setup
        final Reader input1 = new StringReader("");
        final Reader input2 = new StringReader("content");

        // Run the test
        final boolean result = MyClass.contentEqualsIgnoreEOL(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEqualsIgnoreEOL_BrokenInput1() {
        // Setup
        final Reader input1 = new Reader() {

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
        final Reader input2 = new StringReader("content");

        // Run the test
        assertThatThrownBy(() -> MyClass.contentEqualsIgnoreEOL(input1, input2)).isInstanceOf(IOException.class);
    }

    @Test
    void testContentEqualsIgnoreEOL_EmptyInput2() throws Exception {
        // Setup
        final Reader input1 = new StringReader("content");
        final Reader input2 = new StringReader("");

        // Run the test
        final boolean result = MyClass.contentEqualsIgnoreEOL(input1, input2);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testContentEqualsIgnoreEOL_BrokenInput2() {
        // Setup
        final Reader input1 = new StringReader("content");
        final Reader input2 = new Reader() {

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
        assertThatThrownBy(() -> MyClass.contentEqualsIgnoreEOL(input1, input2)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testCopy1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testCopy1_BrokenInput() {
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
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy1_BrokenOutput() {
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
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copy(input, output, 0);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopy2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copy(input, output, 0);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopy2_BrokenInput() {
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
        assertThatThrownBy(() -> MyClass.copy(input, output, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy2_BrokenOutput() {
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
        assertThatThrownBy(() -> MyClass.copy(input, output, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy3() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final Writer output = new StringWriter();

        // Run the test
        MyClass.copy(input, output);

        // Verify the results
    }

    @Test
    void testCopy3_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final Writer output = new StringWriter();

        // Run the test
        MyClass.copy(input, output);

        // Verify the results
    }

    @Test
    void testCopy3_BrokenInput() {
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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy3_BrokenOutput() {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy4() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final Writer output = new StringWriter();

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testCopy4_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final Writer output = new StringWriter();

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testCopy4_BrokenInput() {
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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy4_BrokenOutput() {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copy(input, output, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy5() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final Writer output = new StringWriter();

        // Run the test
        MyClass.copy(input, output, "inputCharsetName");

        // Verify the results
    }

    @Test
    void testCopy5_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final Writer output = new StringWriter();

        // Run the test
        MyClass.copy(input, output, "inputCharsetName");

        // Verify the results
    }

    @Test
    void testCopy5_BrokenInput() {
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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output, "inputCharsetName")).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy5_BrokenOutput() {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copy(input, output, "inputCharsetName")).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy6() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Appendable output = new StringBuffer("value");

        // Run the test
        final long result = MyClass.copy(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopy6_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Appendable output = new StringBuffer("value");

        // Run the test
        final long result = MyClass.copy(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopy6_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Appendable output = new StringBuffer("value");

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy7() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Appendable output = new StringBuffer("value");
        final CharBuffer buffer = CharBuffer.allocate(0);

        // Run the test
        final long result = MyClass.copy(input, output, buffer);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopy7_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Appendable output = new StringBuffer("value");
        final CharBuffer buffer = CharBuffer.allocate(0);

        // Run the test
        final long result = MyClass.copy(input, output, buffer);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopy7_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Appendable output = new StringBuffer("value");
        final CharBuffer buffer = CharBuffer.allocate(0);

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output, buffer)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy8() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output);

        // Verify the results
    }

    @Test
    void testCopy8_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output);

        // Verify the results
    }

    @Test
    void testCopy8_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy8_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
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
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy9() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testCopy9_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testCopy9_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy9_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
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
        assertThatThrownBy(() -> MyClass.copy(input, output, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy10() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output, "outputCharsetName");

        // Verify the results
    }

    @Test
    void testCopy10_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output, "outputCharsetName");

        // Verify the results
    }

    @Test
    void testCopy10_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output, "outputCharsetName")).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy10_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
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
        assertThatThrownBy(() -> MyClass.copy(input, output, "outputCharsetName")).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy11() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new StringWriter();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testCopy11_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Writer output = new StringWriter();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testCopy11_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopy11_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copy(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge1_BrokenInput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge1_BrokenOutput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output, "content".getBytes());

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output, "content".getBytes());

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge2_BrokenInput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, "content".getBytes()))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge2_BrokenOutput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, "content".getBytes()))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge3() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge3_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge3_BrokenInput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge3_BrokenOutput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge4() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes());

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge4_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes());

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge4_BrokenInput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes()))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge4_BrokenOutput() {
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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes()))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge5() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge5_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge5_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copyLarge(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge5_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge6() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output, new char[]{'a'});

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge6_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output, new char[]{'a'});

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge6_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, new char[]{'a'})).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge6_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, new char[]{'a'})).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge7() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge7_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge7_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge7_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge8() throws Exception {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L, new char[]{'a'});

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge8_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");
        final Writer output = new StringWriter();

        // Run the test
        final long result = MyClass.copyLarge(input, output, 0L, 0L, new char[]{'a'});

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testCopyLarge8_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        final Writer output = new StringWriter();

        // Run the test
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L, new char[]{'a'}))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testCopyLarge8_BrokenOutput() {
        // Setup
        final Reader input = new StringReader("content");
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.copyLarge(input, output, 0L, 0L, new char[]{'a'}))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testLength1() {
        assertThat(MyClass.length("content".getBytes())).isEqualTo(0);
    }

    @Test
    void testLength2() {
        assertThat(MyClass.length(new char[]{'a'})).isEqualTo(0);
    }

    @Test
    void testLength3() {
        assertThat(MyClass.length("csq")).isEqualTo(0);
    }

    @Test
    void testLength4() {
        assertThat(MyClass.length(new Object[]{"array"})).isEqualTo(0);
    }

    @Test
    void testLineIterator1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final LineIterator result = MyClass.lineIterator(input, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testLineIterator1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final LineIterator result = MyClass.lineIterator(input, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testLineIterator1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.lineIterator(input, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testLineIterator2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final LineIterator result = MyClass.lineIterator(input, "UTF-8");

        // Verify the results
    }

    @Test
    void testLineIterator2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final LineIterator result = MyClass.lineIterator(input, "UTF-8");

        // Verify the results
    }

    @Test
    void testLineIterator2_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.lineIterator(input, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testLineIterator3() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final LineIterator result = MyClass.lineIterator(reader);

        // Verify the results
    }

    @Test
    void testLineIterator3_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final LineIterator result = MyClass.lineIterator(reader);

        // Verify the results
    }

    @Test
    void testLineIterator3_BrokenReader() {
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
        final LineIterator result = MyClass.lineIterator(reader);

        // Verify the results
    }

    @Test
    void testRead1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final int result = MyClass.read(input, "content".getBytes());

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final int result = MyClass.read(input, "content".getBytes());

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.read(input, "content".getBytes())).isInstanceOf(IOException.class);
    }

    @Test
    void testRead2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final int result = MyClass.read(input, "content".getBytes(), 0, 0);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final int result = MyClass.read(input, "content".getBytes(), 0, 0);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead2_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.read(input, "content".getBytes(), 0, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testRead3() throws Exception {
        // Setup
        final ReadableByteChannel input = null;
        final ByteBuffer buffer = ByteBuffer.wrap("content".getBytes());

        // Run the test
        final int result = MyClass.read(input, buffer);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead3_ThrowsIOException() {
        // Setup
        final ReadableByteChannel input = null;
        final ByteBuffer buffer = ByteBuffer.wrap("content".getBytes());

        // Run the test
        assertThatThrownBy(() -> MyClass.read(input, buffer)).isInstanceOf(IOException.class);
    }

    @Test
    void testRead4() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final int result = MyClass.read(input, new char[]{'a'});

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead4_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final int result = MyClass.read(input, new char[]{'a'});

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead4_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.read(input, new char[]{'a'})).isInstanceOf(IOException.class);
    }

    @Test
    void testRead5() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final int result = MyClass.read(input, new char[]{'a'}, 0, 0);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead5_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final int result = MyClass.read(input, new char[]{'a'}, 0, 0);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testRead5_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.read(input, new char[]{'a'}, 0, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadFully1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.readFully(input, "content".getBytes());

        // Verify the results
    }

    @Test
    void testReadFully1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.readFully(input, "content".getBytes());

        // Verify the results
    }

    @Test
    void testReadFully1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.readFully(input, "content".getBytes())).isInstanceOf(IOException.class);
    }

    @Test
    void testReadFully2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.readFully(input, "content".getBytes(), 0, 0);

        // Verify the results
    }

    @Test
    void testReadFully2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.readFully(input, "content".getBytes(), 0, 0);

        // Verify the results
    }

    @Test
    void testReadFully2_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.readFully(input, "content".getBytes(), 0, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadFully3() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final byte[] result = MyClass.readFully(input, 0);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testReadFully3_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final byte[] result = MyClass.readFully(input, 0);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testReadFully3_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.readFully(input, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadFully4() throws Exception {
        // Setup
        final ReadableByteChannel input = null;
        final ByteBuffer buffer = ByteBuffer.wrap("content".getBytes());

        // Run the test
        MyClass.readFully(input, buffer);

        // Verify the results
    }

    @Test
    void testReadFully4_ThrowsIOException() {
        // Setup
        final ReadableByteChannel input = null;
        final ByteBuffer buffer = ByteBuffer.wrap("content".getBytes());

        // Run the test
        assertThatThrownBy(() -> MyClass.readFully(input, buffer)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadFully4_ThrowsEOFException() {
        // Setup
        final ReadableByteChannel input = null;
        final ByteBuffer buffer = ByteBuffer.wrap("content".getBytes());

        // Run the test
        assertThatThrownBy(() -> MyClass.readFully(input, buffer)).isInstanceOf(EOFException.class);
    }

    @Test
    void testReadFully5() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        MyClass.readFully(input, new char[]{'a'});

        // Verify the results
    }

    @Test
    void testReadFully5_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        MyClass.readFully(input, new char[]{'a'});

        // Verify the results
    }

    @Test
    void testReadFully5_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.readFully(input, new char[]{'a'})).isInstanceOf(IOException.class);
    }

    @Test
    void testReadFully6() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        MyClass.readFully(input, new char[]{'a'}, 0, 0);

        // Verify the results
    }

    @Test
    void testReadFully6_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        MyClass.readFully(input, new char[]{'a'}, 0, 0);

        // Verify the results
    }

    @Test
    void testReadFully6_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.readFully(input, new char[]{'a'}, 0, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadLines1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final List<String> result = MyClass.readLines(input);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final List<String> result = MyClass.readLines(input);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.readLines(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadLines2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final List<String> result = MyClass.readLines(input, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final List<String> result = MyClass.readLines(input, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines2_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.readLines(input, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testReadLines3() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final List<String> result = MyClass.readLines(input, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines3_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final List<String> result = MyClass.readLines(input, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines3_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.readLines(input, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testReadLines4() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final List<String> result = MyClass.readLines(input);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines4_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final List<String> result = MyClass.readLines(input);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testReadLines4_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.readLines(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testResourceToByteArray1() throws Exception {
        assertThat(MyClass.resourceToByteArray("name")).isEqualTo("content".getBytes());
        assertThatThrownBy(() -> MyClass.resourceToByteArray("name")).isInstanceOf(IOException.class);
    }

    @Test
    void testResourceToByteArray2() throws Exception {
        // Setup
        final ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        // Run the test
        final byte[] result = MyClass.resourceToByteArray("name", classLoader);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testResourceToByteArray2_ThrowsIOException() {
        // Setup
        final ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        // Run the test
        assertThatThrownBy(() -> MyClass.resourceToByteArray("name", classLoader)).isInstanceOf(IOException.class);
    }

    @Test
    void testResourceToString1() throws Exception {
        assertThat(MyClass.resourceToString("name", StandardCharsets.UTF_8)).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.resourceToString("name", StandardCharsets.UTF_8))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testResourceToString2() throws Exception {
        // Setup
        final ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        // Run the test
        final String result = MyClass.resourceToString("name", StandardCharsets.UTF_8, classLoader);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testResourceToString2_ThrowsIOException() {
        // Setup
        final ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        // Run the test
        assertThatThrownBy(() -> MyClass.resourceToString("name", StandardCharsets.UTF_8, classLoader))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testResourceToURL1() throws Exception {
        assertThat(MyClass.resourceToURL("name")).isEqualTo(new URL("https://example.com/"));
        assertThatThrownBy(() -> MyClass.resourceToURL("name")).isInstanceOf(IOException.class);
    }

    @Test
    void testResourceToURL2() throws Exception {
        // Setup
        final ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        // Run the test
        final URL result = MyClass.resourceToURL("name", classLoader);

        // Verify the results
        assertThat(result).isEqualTo(new URL("https://example.com/"));
    }

    @Test
    void testResourceToURL2_ThrowsIOException() {
        // Setup
        final ClassLoader classLoader = ClassLoader.getPlatformClassLoader();

        // Run the test
        assertThatThrownBy(() -> MyClass.resourceToURL("name", classLoader)).isInstanceOf(IOException.class);
    }

    @Test
    void testSkip1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final long result = MyClass.skip(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testSkip1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final long result = MyClass.skip(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testSkip1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.skip(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testSkip2() throws Exception {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        final long result = MyClass.skip(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testSkip2_ThrowsIOException() {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.skip(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testSkip2_ThrowsIllegalArgumentException() {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.skip(input, 0L)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testSkip3() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final long result = MyClass.skip(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testSkip3_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final long result = MyClass.skip(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testSkip3_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.skip(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testSkipFully1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        MyClass.skipFully(input, 0L);

        // Verify the results
    }

    @Test
    void testSkipFully1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        MyClass.skipFully(input, 0L);

        // Verify the results
    }

    @Test
    void testSkipFully1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.skipFully(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testSkipFully2() throws Exception {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        MyClass.skipFully(input, 0L);

        // Verify the results
    }

    @Test
    void testSkipFully2_ThrowsIOException() {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.skipFully(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testSkipFully2_ThrowsIllegalArgumentException() {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.skipFully(input, 0L)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testSkipFully2_ThrowsEOFException() {
        // Setup
        final ReadableByteChannel input = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.skipFully(input, 0L)).isInstanceOf(EOFException.class);
    }

    @Test
    void testSkipFully3() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        MyClass.skipFully(input, 0L);

        // Verify the results
    }

    @Test
    void testSkipFully3_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        MyClass.skipFully(input, 0L);

        // Verify the results
    }

    @Test
    void testSkipFully3_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.skipFully(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testToBufferedInputStream1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final InputStream result = MyClass.toBufferedInputStream(input);

        // Verify the results
    }

    @Test
    void testToBufferedInputStream1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final InputStream result = MyClass.toBufferedInputStream(input);

        // Verify the results
    }

    @Test
    void testToBufferedInputStream1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toBufferedInputStream(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testToBufferedInputStream2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final InputStream result = MyClass.toBufferedInputStream(input, 0);

        // Verify the results
    }

    @Test
    void testToBufferedInputStream2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final InputStream result = MyClass.toBufferedInputStream(input, 0);

        // Verify the results
    }

    @Test
    void testToBufferedInputStream2_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toBufferedInputStream(input, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testToBufferedReader1() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final BufferedReader result = MyClass.toBufferedReader(reader);

        // Verify the results
    }

    @Test
    void testToBufferedReader1_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final BufferedReader result = MyClass.toBufferedReader(reader);

        // Verify the results
    }

    @Test
    void testToBufferedReader1_BrokenReader() {
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
        final BufferedReader result = MyClass.toBufferedReader(reader);

        // Verify the results
    }

    @Test
    void testToBufferedReader2() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final BufferedReader result = MyClass.toBufferedReader(reader, 0);

        // Verify the results
    }

    @Test
    void testToBufferedReader2_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final BufferedReader result = MyClass.toBufferedReader(reader, 0);

        // Verify the results
    }

    @Test
    void testToBufferedReader2_BrokenReader() {
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
        final BufferedReader result = MyClass.toBufferedReader(reader, 0);

        // Verify the results
    }

    @Test
    void testToByteArray1() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final byte[] result = MyClass.toByteArray(input);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray1_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final byte[] result = MyClass.toByteArray(input);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray1_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toByteArray(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray2() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final byte[] result = MyClass.toByteArray(input, 0);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray2_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final byte[] result = MyClass.toByteArray(input, 0);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray2_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toByteArray(input, 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray3() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final byte[] result = MyClass.toByteArray(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray3_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final byte[] result = MyClass.toByteArray(input, 0L);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray3_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toByteArray(input, 0L)).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray4() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final byte[] result = MyClass.toByteArray(input);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray4_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final byte[] result = MyClass.toByteArray(input);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray4_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.toByteArray(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray5() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final byte[] result = MyClass.toByteArray(input, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray5_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final byte[] result = MyClass.toByteArray(input, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray5_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.toByteArray(input, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray6() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final byte[] result = MyClass.toByteArray(input, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray6_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final byte[] result = MyClass.toByteArray(input, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray6_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.toByteArray(input, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray7() throws Exception {
        assertThat(MyClass.toByteArray("input")).isEqualTo("content".getBytes());
        assertThatThrownBy(() -> MyClass.toByteArray("input")).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toByteArray("input")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToByteArray8() throws Exception {
        assertThat(MyClass.toByteArray(new URI("https://example.com/"))).isEqualTo("content".getBytes());
        assertThatThrownBy(() -> MyClass.toByteArray(new URI("https://example.com/"))).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toByteArray(new URI("https://example.com/")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToByteArray9() throws Exception {
        assertThat(MyClass.toByteArray(new URL("https://example.com/"))).isEqualTo("content".getBytes());
        assertThatThrownBy(() -> MyClass.toByteArray(new URL("https://example.com/"))).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toByteArray(new URL("https://example.com/")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToByteArray10() throws Exception {
        // Setup
        final URLConnection urlConn = null;

        // Run the test
        final byte[] result = MyClass.toByteArray(urlConn);

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testToByteArray10_ThrowsIOException() {
        // Setup
        final URLConnection urlConn = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.toByteArray(urlConn)).isInstanceOf(IOException.class);
    }

    @Test
    void testToByteArray10_ThrowsNullPointerException() {
        // Setup
        final URLConnection urlConn = null;

        // Run the test
        assertThatThrownBy(() -> MyClass.toByteArray(urlConn)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToCharArray1() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final char[] result = MyClass.toCharArray(is);

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray1_EmptyIs() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final char[] result = MyClass.toCharArray(is);

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray1_BrokenIs() {
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
        assertThatThrownBy(() -> MyClass.toCharArray(is)).isInstanceOf(IOException.class);
    }

    @Test
    void testToCharArray2() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final char[] result = MyClass.toCharArray(is, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray2_EmptyIs() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final char[] result = MyClass.toCharArray(is, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray2_BrokenIs() {
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
        assertThatThrownBy(() -> MyClass.toCharArray(is, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testToCharArray3() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final char[] result = MyClass.toCharArray(is, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray3_EmptyIs() throws Exception {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final char[] result = MyClass.toCharArray(is, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray3_BrokenIs() {
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
        assertThatThrownBy(() -> MyClass.toCharArray(is, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testToCharArray4() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final char[] result = MyClass.toCharArray(input);

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray4_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final char[] result = MyClass.toCharArray(input);

        // Verify the results
        assertThat(result).isEqualTo(new char[]{'a'});
    }

    @Test
    void testToCharArray4_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.toCharArray(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testToInputStream1() {
        // Setup
        // Run the test
        final InputStream result = MyClass.toInputStream("input");

        // Verify the results
    }

    @Test
    void testToInputStream2() {
        // Setup
        // Run the test
        final InputStream result = MyClass.toInputStream("input", StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testToInputStream3() throws Exception {
        // Setup
        // Run the test
        final InputStream result = MyClass.toInputStream("input", "UTF-8");

        // Verify the results
    }

    @Test
    void testToInputStream3_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> MyClass.toInputStream("input", "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testToInputStream3_ThrowsUnsupportedCharsetException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> MyClass.toInputStream("input", "UTF-8"))
                .isInstanceOf(UnsupportedCharsetException.class);
    }

    @Test
    void testToInputStream4() {
        // Setup
        // Run the test
        final InputStream result = MyClass.toInputStream("input");

        // Verify the results
    }

    @Test
    void testToInputStream5() {
        // Setup
        // Run the test
        final InputStream result = MyClass.toInputStream("input", StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testToInputStream6() throws Exception {
        // Setup
        // Run the test
        final InputStream result = MyClass.toInputStream("input", "UTF-8");

        // Verify the results
    }

    @Test
    void testToInputStream6_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> MyClass.toInputStream("input", "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testToInputStream6_ThrowsUnsupportedCharsetException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> MyClass.toInputStream("input", "UTF-8"))
                .isInstanceOf(UnsupportedCharsetException.class);
    }

    @Test
    void testToString1() throws Exception {
        assertThat(MyClass.toString("content".getBytes())).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.toString("content".getBytes())).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toString("content".getBytes())).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToString2() throws Exception {
        assertThat(MyClass.toString("content".getBytes(), "UTF-8")).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.toString("content".getBytes(), "UTF-8")).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toString("content".getBytes(), "UTF-8"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToString3() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final String result = MyClass.toString(input);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString3_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final String result = MyClass.toString(input);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString3_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toString(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testToString4() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final String result = MyClass.toString(input, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString4_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final String result = MyClass.toString(input, StandardCharsets.UTF_8);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString4_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toString(input, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testToString5() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final String result = MyClass.toString(input, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString5_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final String result = MyClass.toString(input, "UTF-8");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString5_BrokenInput() {
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

        // Run the test
        assertThatThrownBy(() -> MyClass.toString(input, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testToString6() throws Exception {
        // Setup
        final Reader input = new StringReader("content");

        // Run the test
        final String result = MyClass.toString(input);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString6_EmptyInput() throws Exception {
        // Setup
        final Reader input = new StringReader("");

        // Run the test
        final String result = MyClass.toString(input);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testToString6_BrokenInput() {
        // Setup
        final Reader input = new Reader() {

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
        assertThatThrownBy(() -> MyClass.toString(input)).isInstanceOf(IOException.class);
    }

    @Test
    void testToString7() throws Exception {
        assertThat(MyClass.toString(new URI("https://example.com/"))).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.toString(new URI("https://example.com/"))).isInstanceOf(IOException.class);
    }

    @Test
    void testToString8() throws Exception {
        assertThat(MyClass.toString(new URI("https://example.com/"), StandardCharsets.UTF_8)).isEqualTo("result");
        assertThatThrownBy(
                () -> MyClass.toString(new URI("https://example.com/"), StandardCharsets.UTF_8))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testToString9() throws Exception {
        assertThat(MyClass.toString(new URI("https://example.com/"), "UTF-8")).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.toString(new URI("https://example.com/"), "UTF-8"))
                .isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toString(new URI("https://example.com/"), "UTF-8"))
                .isInstanceOf(UnsupportedCharsetException.class);
    }

    @Test
    void testToString10() throws Exception {
        assertThat(MyClass.toString(new URL("https://example.com/"))).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.toString(new URL("https://example.com/"))).isInstanceOf(IOException.class);
    }

    @Test
    void testToString11() throws Exception {
        assertThat(MyClass.toString(new URL("https://example.com/"), StandardCharsets.UTF_8)).isEqualTo("result");
        assertThatThrownBy(
                () -> MyClass.toString(new URL("https://example.com/"), StandardCharsets.UTF_8))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testToString12() throws Exception {
        assertThat(MyClass.toString(new URL("https://example.com/"), "UTF-8")).isEqualTo("result");
        assertThatThrownBy(() -> MyClass.toString(new URL("https://example.com/"), "UTF-8"))
                .isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.toString(new URL("https://example.com/"), "UTF-8"))
                .isInstanceOf(UnsupportedCharsetException.class);
    }

    @Test
    void testWrite1() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("content".getBytes(), output);

        // Verify the results
    }

    @Test
    void testWrite1_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("content".getBytes(), output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite2() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write("content".getBytes(), output);

        // Verify the results
    }

    @Test
    void testWrite2_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write("content".getBytes(), output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite3() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write("content".getBytes(), output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testWrite3_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write("content".getBytes(), output, StandardCharsets.UTF_8))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testWrite4() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write("content".getBytes(), output, "UTF-8");

        // Verify the results
    }

    @Test
    void testWrite4_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write("content".getBytes(), output, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite5() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write(new char[]{'a'}, output);

        // Verify the results
    }

    @Test
    void testWrite5_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write(new char[]{'a'}, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite6() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write(new char[]{'a'}, output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testWrite6_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write(new char[]{'a'}, output, StandardCharsets.UTF_8))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testWrite7() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write(new char[]{'a'}, output, "UTF-8");

        // Verify the results
    }

    @Test
    void testWrite7_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write(new char[]{'a'}, output, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite8() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write(new char[]{'a'}, output);

        // Verify the results
    }

    @Test
    void testWrite8_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write(new char[]{'a'}, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite9() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("data", output);

        // Verify the results
    }

    @Test
    void testWrite9_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("data", output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite10() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("data", output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testWrite10_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("data", output, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite11() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("data", output, "UTF-8");

        // Verify the results
    }

    @Test
    void testWrite11_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("data", output, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite12() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write("data", output);

        // Verify the results
    }

    @Test
    void testWrite12_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write("data", output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite13() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("data", output);

        // Verify the results
    }

    @Test
    void testWrite13_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("data", output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite14() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("data", output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testWrite14_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("data", output, StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite15() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write("data", output, "UTF-8");

        // Verify the results
    }

    @Test
    void testWrite15_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.write("data", output, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite16() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write("data", output);

        // Verify the results
    }

    @Test
    void testWrite16_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write("data", output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite17() throws Exception {
        // Setup
        final StringBuffer data = new StringBuffer("value");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write(data, output);

        // Verify the results
    }

    @Test
    void testWrite17_BrokenOutput() {
        // Setup
        final StringBuffer data = new StringBuffer("value");
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
        assertThatThrownBy(() -> MyClass.write(data, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite18() throws Exception {
        // Setup
        final StringBuffer data = new StringBuffer("value");
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.write(data, output, "UTF-8");

        // Verify the results
    }

    @Test
    void testWrite18_BrokenOutput() {
        // Setup
        final StringBuffer data = new StringBuffer("value");
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
        assertThatThrownBy(() -> MyClass.write(data, output, "UTF-8")).isInstanceOf(IOException.class);
    }

    @Test
    void testWrite19() throws Exception {
        // Setup
        final StringBuffer data = new StringBuffer("value");
        final Writer output = new StringWriter();

        // Run the test
        MyClass.write(data, output);

        // Verify the results
    }

    @Test
    void testWrite19_BrokenOutput() {
        // Setup
        final StringBuffer data = new StringBuffer("value");
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.write(data, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWriteChunked1() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.writeChunked("content".getBytes(), output);

        // Verify the results
    }

    @Test
    void testWriteChunked1_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.writeChunked("content".getBytes(), output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWriteChunked2() throws Exception {
        // Setup
        final Writer output = new StringWriter();

        // Run the test
        MyClass.writeChunked(new char[]{'a'}, output);

        // Verify the results
    }

    @Test
    void testWriteChunked2_BrokenOutput() {
        // Setup
        final Writer output = new Writer() {

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
        assertThatThrownBy(() -> MyClass.writeChunked(new char[]{'a'}, output)).isInstanceOf(IOException.class);
    }

    @Test
    void testWriteLines1() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.writeLines(Arrays.asList("value"), "lineEnding", output);

        // Verify the results
    }

    @Test
    void testWriteLines1_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.writeLines(Arrays.asList("value"), "lineEnding", output))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testWriteLines2() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.writeLines(Arrays.asList("value"), "lineEnding", output, StandardCharsets.UTF_8);

        // Verify the results
    }

    @Test
    void testWriteLines2_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.writeLines(Arrays.asList("value"), "lineEnding", output,
                StandardCharsets.UTF_8)).isInstanceOf(IOException.class);
    }

    @Test
    void testWriteLines3() throws Exception {
        // Setup
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.writeLines(Arrays.asList("value"), "lineEnding", output, "UTF-8");

        // Verify the results
    }

    @Test
    void testWriteLines3_BrokenOutput() {
        // Setup
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
        assertThatThrownBy(
                () -> MyClass.writeLines(Arrays.asList("value"), "lineEnding", output, "UTF-8"))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testWriteLines4() throws Exception {
        // Setup
        final Writer writer = new StringWriter();

        // Run the test
        MyClass.writeLines(Arrays.asList("value"), "lineEnding", writer);

        // Verify the results
    }

    @Test
    void testWriteLines4_BrokenWriter() {
        // Setup
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
        assertThatThrownBy(() -> MyClass.writeLines(Arrays.asList("value"), "lineEnding", writer))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testWriter() {
        // Setup
        final Appendable appendable = new StringBuffer("value");

        // Run the test
        final Writer result = MyClass.writer(appendable);

        // Verify the results
    }

    @Test
    void testWriter_ThrowsNullPointerException() {
        // Setup
        final Appendable appendable = new StringBuffer("value");

        // Run the test
        assertThatThrownBy(() -> MyClass.writer(appendable)).isInstanceOf(NullPointerException.class);
    }
}
