package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.channels.Selector
import java.nio.charset.StandardCharsets
import java.nio.charset.UnsupportedCharsetException
import java.util.function.Consumer

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.mock

@CompileStatic
class MyClassTest {

    @Test
    void testBuffer1() {
        // Setup
        def inputStream = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.buffer(inputStream)

        // Verify the results
    }

    @Test
    void testBuffer1_EmptyInputStream() {
        // Setup
        def inputStream = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.buffer(inputStream)

        // Verify the results
    }

    @Test
    void testBuffer1_BrokenInputStream() {
        // Setup
        def inputStream = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(inputStream)

        // Verify the results
    }

    @Test
    void testBuffer2() {
        // Setup
        def inputStream = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.buffer(inputStream, 0)

        // Verify the results
    }

    @Test
    void testBuffer2_EmptyInputStream() {
        // Setup
        def inputStream = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.buffer(inputStream, 0)

        // Verify the results
    }

    @Test
    void testBuffer2_BrokenInputStream() {
        // Setup
        def inputStream = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(inputStream, 0)

        // Verify the results
    }

    @Test
    void testBuffer3() {
        // Setup
        def outputStream = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.buffer(outputStream)

        // Verify the results
    }

    @Test
    void testBuffer3_BrokenOutputStream() {
        // Setup
        def outputStream = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(outputStream)

        // Verify the results
    }

    @Test
    void testBuffer4() {
        // Setup
        def outputStream = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.buffer(outputStream, 0)

        // Verify the results
    }

    @Test
    void testBuffer4_BrokenOutputStream() {
        // Setup
        def outputStream = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(outputStream, 0)

        // Verify the results
    }

    @Test
    void testBuffer5() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.buffer(reader)

        // Verify the results
    }

    @Test
    void testBuffer5_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.buffer(reader)

        // Verify the results
    }

    @Test
    void testBuffer5_BrokenReader() {
        // Setup
        def reader = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(reader)

        // Verify the results
    }

    @Test
    void testBuffer6() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.buffer(reader, 0)

        // Verify the results
    }

    @Test
    void testBuffer6_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.buffer(reader, 0)

        // Verify the results
    }

    @Test
    void testBuffer6_BrokenReader() {
        // Setup
        def reader = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(reader, 0)

        // Verify the results
    }

    @Test
    void testBuffer7() {
        // Setup
        def writer = new StringWriter()

        // Run the test
        def result = MyClass.buffer(writer)

        // Verify the results
    }

    @Test
    void testBuffer7_BrokenWriter() {
        // Setup
        def writer = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(writer)

        // Verify the results
    }

    @Test
    void testBuffer8() {
        // Setup
        def writer = new StringWriter()

        // Run the test
        def result = MyClass.buffer(writer, 0)

        // Verify the results
    }

    @Test
    void testBuffer8_BrokenWriter() {
        // Setup
        def writer = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.buffer(writer, 0)

        // Verify the results
    }

    @Test
    void testClose1() {
        // Setup
        def closeable = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.close(closeable)

        // Verify the results
    }

    @Test
    void testClose1_EmptyCloseable() {
        // Setup
        def closeable = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.close(closeable)

        // Verify the results
    }

    @Test
    void testClose1_BrokenCloseable() {
        // Setup
        def closeable = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.close(closeable)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testClose2() {
        // Setup
        def closeables = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.close(closeables)

        // Verify the results
    }

    @Test
    void testClose2_EmptyCloseables() {
        // Setup
        def closeables = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.close(closeables)

        // Verify the results
    }

    @Test
    void testClose2_BrokenCloseables() {
        // Setup
        def closeables = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.close(closeables)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testClose3() {
        // Setup
        def closeable = new ByteArrayInputStream("content".getBytes())
        def consumer = null

        // Run the test
        MyClass.close(closeable, consumer)

        // Verify the results
    }

    @Test
    void testClose3_EmptyCloseable() {
        // Setup
        def closeable = new ByteArrayInputStream(new byte[]{})
        def consumer = null

        // Run the test
        MyClass.close(closeable, consumer)

        // Verify the results
    }

    @Test
    void testClose3_BrokenCloseable() {
        // Setup
        def closeable = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def consumer = null

        // Run the test
        assertThatThrownBy({
            MyClass.close(closeable, consumer)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testClose4() {
        // Setup
        def conn = null

        // Run the test
        MyClass.close(conn)

        // Verify the results
    }

    @Test
    void testCloseQuietly1() {
        // Setup
        def closeable = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.closeQuietly(closeable)

        // Verify the results
    }

    @Test
    void testCloseQuietly1_EmptyCloseable() {
        // Setup
        def closeable = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.closeQuietly(closeable)

        // Verify the results
    }

    @Test
    void testCloseQuietly1_BrokenCloseable() {
        // Setup
        def closeable = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        MyClass.closeQuietly(closeable)

        // Verify the results
    }

    @Test
    void testCloseQuietly2() {
        // Setup
        def closeables = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.closeQuietly(closeables)

        // Verify the results
    }

    @Test
    void testCloseQuietly2_EmptyCloseables() {
        // Setup
        def closeables = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.closeQuietly(closeables)

        // Verify the results
    }

    @Test
    void testCloseQuietly2_BrokenCloseables() {
        // Setup
        def closeables = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        MyClass.closeQuietly(closeables)

        // Verify the results
    }

    @Test
    void testCloseQuietly3() {
        // Setup
        def closeable = new ByteArrayInputStream("content".getBytes())
        def mockConsumer = mock(Consumer.class)

        // Run the test
        MyClass.closeQuietly(closeable, mockConsumer)

        // Verify the results
    }

    @Test
    void testCloseQuietly3_EmptyCloseable() {
        // Setup
        def closeable = new ByteArrayInputStream(new byte[]{})
        def mockConsumer = mock(Consumer.class)

        // Run the test
        MyClass.closeQuietly(closeable, mockConsumer)

        // Verify the results
    }

    @Test
    void testCloseQuietly3_BrokenCloseable() {
        // Setup
        def closeable = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def mockConsumer = mock(Consumer.class)

        // Run the test
        MyClass.closeQuietly(closeable, mockConsumer)

        // Verify the results
    }

    @Test
    void testCloseQuietly4() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.closeQuietly(input)

        // Verify the results
    }

    @Test
    void testCloseQuietly4_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.closeQuietly(input)

        // Verify the results
    }

    @Test
    void testCloseQuietly4_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        MyClass.closeQuietly(input)

        // Verify the results
    }

    @Test
    void testCloseQuietly5() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.closeQuietly(output)

        // Verify the results
    }

    @Test
    void testCloseQuietly5_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        MyClass.closeQuietly(output)

        // Verify the results
    }

    @Test
    void testCloseQuietly6() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        MyClass.closeQuietly(input)

        // Verify the results
    }

    @Test
    void testCloseQuietly6_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        MyClass.closeQuietly(input)

        // Verify the results
    }

    @Test
    void testCloseQuietly6_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        MyClass.closeQuietly(input)

        // Verify the results
    }

    @Test
    void testCloseQuietly7() {
        // Setup
        def selector = Selector.open()

        // Run the test
        MyClass.closeQuietly(selector)

        // Verify the results
    }

    @Test
    void testCloseQuietly8() {
        // Setup
        def serverSocket = new ServerSocket(0, 0, InetAddress.getByName("localhost"))

        // Run the test
        MyClass.closeQuietly(serverSocket)

        // Verify the results
    }

    @Test
    void testCloseQuietly9() {
        // Setup
        def socket = new Socket("host", 80)

        // Run the test
        MyClass.closeQuietly(socket)

        // Verify the results
    }

    @Test
    void testCloseQuietly10() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.closeQuietly(output)

        // Verify the results
    }

    @Test
    void testCloseQuietly10_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        MyClass.closeQuietly(output)

        // Verify the results
    }

    @Test
    void testConsume() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.consume(input)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testConsume_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.consume(input)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testConsume_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.consume(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testContentEquals1() {
        // Setup
        def input1 = new ByteArrayInputStream("content".getBytes())
        def input2 = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEquals1_EmptyInput1() {
        // Setup
        def input1 = new ByteArrayInputStream(new byte[]{})
        def input2 = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEquals1_BrokenInput1() {
        // Setup
        def input1 = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def input2 = new ByteArrayInputStream("content".getBytes())

        // Run the test
        assertThatThrownBy({
            MyClass.contentEquals(input1, input2)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testContentEquals1_EmptyInput2() {
        // Setup
        def input1 = new ByteArrayInputStream("content".getBytes())
        def input2 = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEquals1_BrokenInput2() {
        // Setup
        def input1 = new ByteArrayInputStream("content".getBytes())
        def input2 = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.contentEquals(input1, input2)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testContentEquals2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEquals2_EmptyInput1() {
        // Setup
        def input1 = new StringReader("")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEquals2_BrokenInput1() {
        // Setup
        def input1 = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def input2 = new StringReader("content")

        // Run the test
        assertThatThrownBy({
            MyClass.contentEquals(input1, input2)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testContentEquals2_EmptyInput2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("")

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEquals2_BrokenInput2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.contentEquals(input1, input2)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testContentEqualsIgnoreEOL() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEqualsIgnoreEOL(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEqualsIgnoreEOL_EmptyInput1() {
        // Setup
        def input1 = new StringReader("")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEqualsIgnoreEOL(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEqualsIgnoreEOL_BrokenInput1() {
        // Setup
        def input1 = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def input2 = new StringReader("content")

        // Run the test
        assertThatThrownBy({
            MyClass.contentEqualsIgnoreEOL(input1, input2)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testContentEqualsIgnoreEOL_EmptyInput2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("")

        // Run the test
        def result = MyClass.contentEqualsIgnoreEOL(input1, input2)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testContentEqualsIgnoreEOL_BrokenInput2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.contentEqualsIgnoreEOL(input1, input2)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCopy1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCopy1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy1_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output, 0)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopy2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output, 0)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopy2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy2_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new StringWriter()

        // Run the test
        MyClass.copy(input, output)

        // Verify the results
    }

    @Test
    void testCopy3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new StringWriter()

        // Run the test
        MyClass.copy(input, output)

        // Verify the results
    }

    @Test
    void testCopy3_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy3_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy4() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new StringWriter()

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testCopy4_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new StringWriter()

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testCopy4_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy4_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy5() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new StringWriter()

        // Run the test
        MyClass.copy(input, output, "inputCharsetName")

        // Verify the results
    }

    @Test
    void testCopy5_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new StringWriter()

        // Run the test
        MyClass.copy(input, output, "inputCharsetName")

        // Verify the results
    }

    @Test
    void testCopy5_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, "inputCharsetName")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy5_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, "inputCharsetName")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy6() {
        // Setup
        def input = new StringReader("content")
        def output = new StringBuffer("value")

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopy6_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringBuffer("value")

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopy6_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringBuffer("value")

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy7() {
        // Setup
        def input = new StringReader("content")
        def output = new StringBuffer("value")
        def buffer = CharBuffer.allocate(0)

        // Run the test
        def result = MyClass.copy(input, output, buffer)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopy7_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringBuffer("value")
        def buffer = CharBuffer.allocate(0)

        // Run the test
        def result = MyClass.copy(input, output, buffer)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopy7_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringBuffer("value")
        def buffer = CharBuffer.allocate(0)

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, buffer)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy8() {
        // Setup
        def input = new StringReader("content")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.copy(input, output)

        // Verify the results
    }

    @Test
    void testCopy8_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.copy(input, output)

        // Verify the results
    }

    @Test
    void testCopy8_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy8_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy9() {
        // Setup
        def input = new StringReader("content")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testCopy9_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.copy(input, output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testCopy9_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy9_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy10() {
        // Setup
        def input = new StringReader("content")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.copy(input, output, "outputCharsetName")

        // Verify the results
    }

    @Test
    void testCopy10_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.copy(input, output, "outputCharsetName")

        // Verify the results
    }

    @Test
    void testCopy10_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, "outputCharsetName")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy10_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output, "outputCharsetName")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy11() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCopy11_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCopy11_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy11_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge1_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, "content".getBytes())

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, "content".getBytes())

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, "content".getBytes())
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge2_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, "content".getBytes())
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge3_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge3_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge4() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge4_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge4_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge4_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge5() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge5_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge5_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge5_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge6() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, ['a'] as char[])

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge6_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, ['a'] as char[])

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge6_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, ['a'] as char[])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge6_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, ['a'] as char[])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge7() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge7_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge7_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge7_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge8() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge8_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testCopyLarge8_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new StringWriter()

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopyLarge8_BrokenOutput() {
        // Setup
        def input = new StringReader("content")
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testLength1() {
        assertThat(MyClass.length("content".getBytes())).isEqualTo(0)
    }

    @Test
    void testLength2() {
        assertThat(MyClass.length(['a'] as char[])).isEqualTo(0)
    }

    @Test
    void testLength3() {
        assertThat(MyClass.length("csq")).isEqualTo(0)
    }

    @Test
    void testLength4() {
        assertThat(MyClass.length(["array"] as Object[])).isEqualTo(0)
    }

    @Test
    void testLineIterator1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.lineIterator(input, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testLineIterator1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.lineIterator(input, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testLineIterator1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.lineIterator(input, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testLineIterator2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.lineIterator(input, "UTF-8")

        // Verify the results
    }

    @Test
    void testLineIterator2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.lineIterator(input, "UTF-8")

        // Verify the results
    }

    @Test
    void testLineIterator2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.lineIterator(input, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testLineIterator3() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.lineIterator(reader)

        // Verify the results
    }

    @Test
    void testLineIterator3_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.lineIterator(reader)

        // Verify the results
    }

    @Test
    void testLineIterator3_BrokenReader() {
        // Setup
        def reader = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.lineIterator(reader)

        // Verify the results
    }

    @Test
    void testRead1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.read(input, "content".getBytes())

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.read(input, "content".getBytes())

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.read(input, "content".getBytes())
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testRead2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.read(input, "content".getBytes(), 0, 0)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.read(input, "content".getBytes(), 0, 0)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.read(input, "content".getBytes(), 0, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testRead3() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        def result = MyClass.read(input, buffer)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead3_ThrowsIOException() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        assertThatThrownBy({
            MyClass.read(input, buffer)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testRead4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[])

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[])

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead4_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.read(input, ['a'] as char[])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testRead5() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[], 0, 0)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead5_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[], 0, 0)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testRead5_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.read(input, ['a'] as char[], 0, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadFully1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.readFully(input, "content".getBytes())

        // Verify the results
    }

    @Test
    void testReadFully1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.readFully(input, "content".getBytes())

        // Verify the results
    }

    @Test
    void testReadFully1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, "content".getBytes())
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadFully2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.readFully(input, "content".getBytes(), 0, 0)

        // Verify the results
    }

    @Test
    void testReadFully2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.readFully(input, "content".getBytes(), 0, 0)

        // Verify the results
    }

    @Test
    void testReadFully2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, "content".getBytes(), 0, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadFully3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readFully(input, 0)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testReadFully3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readFully(input, 0)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testReadFully3_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadFully4() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        MyClass.readFully(input, buffer)

        // Verify the results
    }

    @Test
    void testReadFully4_ThrowsIOException() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, buffer)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadFully4_ThrowsEOFException() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, buffer)
        }).isInstanceOf(EOFException.class)
    }

    @Test
    void testReadFully5() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        MyClass.readFully(input, ['a'] as char[])

        // Verify the results
    }

    @Test
    void testReadFully5_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        MyClass.readFully(input, ['a'] as char[])

        // Verify the results
    }

    @Test
    void testReadFully5_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, ['a'] as char[])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadFully6() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        MyClass.readFully(input, ['a'] as char[], 0, 0)

        // Verify the results
    }

    @Test
    void testReadFully6_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        MyClass.readFully(input, ['a'] as char[], 0, 0)

        // Verify the results
    }

    @Test
    void testReadFully6_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readFully(input, ['a'] as char[], 0, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadLines1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readLines(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadLines2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readLines(input, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readLines(input, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readLines(input, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadLines3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readLines(input, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readLines(input, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines3_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readLines(input, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testReadLines4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testReadLines4_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.readLines(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testResourceToByteArray1() {
        assertThat(MyClass.resourceToByteArray("name")).isEqualTo("content".getBytes())
        assertThatThrownBy({
            MyClass.resourceToByteArray("name")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testResourceToByteArray2() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        def result = MyClass.resourceToByteArray("name", classLoader)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testResourceToByteArray2_ThrowsIOException() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        assertThatThrownBy({
            MyClass.resourceToByteArray("name", classLoader)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testResourceToString1() {
        assertThat(MyClass.resourceToString("name", StandardCharsets.UTF_8)).isEqualTo("result")
        assertThatThrownBy({
            MyClass.resourceToString("name", StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testResourceToString2() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        def result = MyClass.resourceToString("name", StandardCharsets.UTF_8, classLoader)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testResourceToString2_ThrowsIOException() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        assertThatThrownBy({
            MyClass.resourceToString("name", StandardCharsets.UTF_8, classLoader)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testResourceToURL1() {
        assertThat(MyClass.resourceToURL("name")).isEqualTo(new URL("https://example.com/"))
        assertThatThrownBy({
            MyClass.resourceToURL("name")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testResourceToURL2() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        def result = MyClass.resourceToURL("name", classLoader)

        // Verify the results
        assertThat(result).isEqualTo(new URL("https://example.com/"))
    }

    @Test
    void testResourceToURL2_ThrowsIOException() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        assertThatThrownBy({
            MyClass.resourceToURL("name", classLoader)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSkip1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testSkip1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testSkip1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.skip(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSkip2() {
        // Setup
        def input = null

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testSkip2_ThrowsIOException() {
        // Setup
        def input = null

        // Run the test
        assertThatThrownBy({
            MyClass.skip(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSkip2_ThrowsIllegalArgumentException() {
        // Setup
        def input = null

        // Run the test
        assertThatThrownBy({
            MyClass.skip(input, 0L)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testSkip3() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testSkip3_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testSkip3_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.skip(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSkipFully1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        MyClass.skipFully(input, 0L)

        // Verify the results
    }

    @Test
    void testSkipFully1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        MyClass.skipFully(input, 0L)

        // Verify the results
    }

    @Test
    void testSkipFully1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.skipFully(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSkipFully2() {
        // Setup
        def input = null

        // Run the test
        MyClass.skipFully(input, 0L)

        // Verify the results
    }

    @Test
    void testSkipFully2_ThrowsIOException() {
        // Setup
        def input = null

        // Run the test
        assertThatThrownBy({
            MyClass.skipFully(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSkipFully2_ThrowsIllegalArgumentException() {
        // Setup
        def input = null

        // Run the test
        assertThatThrownBy({
            MyClass.skipFully(input, 0L)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testSkipFully2_ThrowsEOFException() {
        // Setup
        def input = null

        // Run the test
        assertThatThrownBy({
            MyClass.skipFully(input, 0L)
        }).isInstanceOf(EOFException.class)
    }

    @Test
    void testSkipFully3() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        MyClass.skipFully(input, 0L)

        // Verify the results
    }

    @Test
    void testSkipFully3_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        MyClass.skipFully(input, 0L)

        // Verify the results
    }

    @Test
    void testSkipFully3_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.skipFully(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToBufferedInputStream1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toBufferedInputStream(input)

        // Verify the results
    }

    @Test
    void testToBufferedInputStream1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toBufferedInputStream(input)

        // Verify the results
    }

    @Test
    void testToBufferedInputStream1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toBufferedInputStream(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToBufferedInputStream2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toBufferedInputStream(input, 0)

        // Verify the results
    }

    @Test
    void testToBufferedInputStream2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toBufferedInputStream(input, 0)

        // Verify the results
    }

    @Test
    void testToBufferedInputStream2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toBufferedInputStream(input, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToBufferedReader1() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.toBufferedReader(reader)

        // Verify the results
    }

    @Test
    void testToBufferedReader1_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.toBufferedReader(reader)

        // Verify the results
    }

    @Test
    void testToBufferedReader1_BrokenReader() {
        // Setup
        def reader = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.toBufferedReader(reader)

        // Verify the results
    }

    @Test
    void testToBufferedReader2() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.toBufferedReader(reader, 0)

        // Verify the results
    }

    @Test
    void testToBufferedReader2_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.toBufferedReader(reader, 0)

        // Verify the results
    }

    @Test
    void testToBufferedReader2_BrokenReader() {
        // Setup
        def reader = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        def result = MyClass.toBufferedReader(reader, 0)

        // Verify the results
    }

    @Test
    void testToByteArray1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray1_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toByteArray(input, 0)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toByteArray(input, 0)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray2_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(input, 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toByteArray(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toByteArray(input, 0L)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray3_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(input, 0L)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray4_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray5() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toByteArray(input, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray5_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toByteArray(input, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray5_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(input, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray6() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toByteArray(input, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray6_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toByteArray(input, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray6_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(input, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray7() {
        assertThat(MyClass.toByteArray("input")).isEqualTo("content".getBytes())
        assertThatThrownBy({
            MyClass.toByteArray("input")
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toByteArray("input")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToByteArray8() {
        assertThat(MyClass.toByteArray(new URI("https://example.com/"))).isEqualTo("content".getBytes())
        assertThatThrownBy({
            MyClass.toByteArray(new URI("https://example.com/"))
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toByteArray(new URI("https://example.com/"))
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToByteArray9() {
        assertThat(MyClass.toByteArray(new URL("https://example.com/"))).isEqualTo("content".getBytes())
        assertThatThrownBy({
            MyClass.toByteArray(new URL("https://example.com/"))
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toByteArray(new URL("https://example.com/"))
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToByteArray10() {
        // Setup
        def urlConn = null

        // Run the test
        def result = MyClass.toByteArray(urlConn)

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testToByteArray10_ThrowsIOException() {
        // Setup
        def urlConn = null

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(urlConn)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToByteArray10_ThrowsNullPointerException() {
        // Setup
        def urlConn = null

        // Run the test
        assertThatThrownBy({
            MyClass.toByteArray(urlConn)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToCharArray1() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toCharArray(is)

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray1_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toCharArray(is)

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray1_BrokenIs() {
        // Setup
        def is = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toCharArray(is)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToCharArray2() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toCharArray(is, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray2_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toCharArray(is, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray2_BrokenIs() {
        // Setup
        def is = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toCharArray(is, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToCharArray3() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toCharArray(is, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray3_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toCharArray(is, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray3_BrokenIs() {
        // Setup
        def is = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toCharArray(is, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToCharArray4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toCharArray(input)

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toCharArray(input)

        // Verify the results
        assertThat(result).isEqualTo(['a'] as char[])
    }

    @Test
    void testToCharArray4_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toCharArray(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToInputStream1() {
        // Setup
        // Run the test
        def result = MyClass.toInputStream("input")

        // Verify the results
    }

    @Test
    void testToInputStream2() {
        // Setup
        // Run the test
        def result = MyClass.toInputStream("input", StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testToInputStream3() {
        // Setup
        // Run the test
        def result = MyClass.toInputStream("input", "UTF-8")

        // Verify the results
    }

    @Test
    void testToInputStream3_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.toInputStream("input", "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToInputStream3_ThrowsUnsupportedCharsetException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.toInputStream("input", "UTF-8")
        }).isInstanceOf(UnsupportedCharsetException.class)
    }

    @Test
    void testToInputStream4() {
        // Setup
        // Run the test
        def result = MyClass.toInputStream("input")

        // Verify the results
    }

    @Test
    void testToInputStream5() {
        // Setup
        // Run the test
        def result = MyClass.toInputStream("input", StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testToInputStream6() {
        // Setup
        // Run the test
        def result = MyClass.toInputStream("input", "UTF-8")

        // Verify the results
    }

    @Test
    void testToInputStream6_ThrowsIOException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.toInputStream("input", "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToInputStream6_ThrowsUnsupportedCharsetException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.toInputStream("input", "UTF-8")
        }).isInstanceOf(UnsupportedCharsetException.class)
    }

    @Test
    void testToString1() {
        assertThat(MyClass.toString("content".getBytes())).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString("content".getBytes())
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toString("content".getBytes())
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToString2() {
        assertThat(MyClass.toString("content".getBytes(), "UTF-8")).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString("content".getBytes(), "UTF-8")
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toString("content".getBytes(), "UTF-8")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToString3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString3_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toString(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString4() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toString(input, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString4_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toString(input, StandardCharsets.UTF_8)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString4_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toString(input, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString5() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toString(input, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString5_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toString(input, "UTF-8")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString5_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toString(input, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString6() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString6_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testToString6_BrokenInput() {
        // Setup
        def input = new Reader() {

            private final IOException exception = new IOException("Error")

            @Override
            public int read(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public boolean ready() throws IOException {
                throw exception
            }

            @Override
            public void mark(final int readAheadLimit) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.toString(input)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString7() {
        assertThat(MyClass.toString(new URI("https://example.com/"))).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString(new URI("https://example.com/"))
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString8() {
        assertThat(MyClass.toString(new URI("https://example.com/"), StandardCharsets.UTF_8)).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString(new URI("https://example.com/"), StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString9() {
        assertThat(MyClass.toString(new URI("https://example.com/"), "UTF-8")).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString(new URI("https://example.com/"), "UTF-8")
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toString(new URI("https://example.com/"), "UTF-8")
        }).isInstanceOf(UnsupportedCharsetException.class)
    }

    @Test
    void testToString10() {
        assertThat(MyClass.toString(new URL("https://example.com/"))).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString(new URL("https://example.com/"))
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString11() {
        assertThat(MyClass.toString(new URL("https://example.com/"), StandardCharsets.UTF_8)).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString(new URL("https://example.com/"), StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString12() {
        assertThat(MyClass.toString(new URL("https://example.com/"), "UTF-8")).isEqualTo("result")
        assertThatThrownBy({
            MyClass.toString(new URL("https://example.com/"), "UTF-8")
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.toString(new URL("https://example.com/"), "UTF-8")
        }).isInstanceOf(UnsupportedCharsetException.class)
    }

    @Test
    void testWrite1() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("content".getBytes(), output)

        // Verify the results
    }

    @Test
    void testWrite1_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("content".getBytes(), output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite2() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.write("content".getBytes(), output)

        // Verify the results
    }

    @Test
    void testWrite2_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("content".getBytes(), output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite3() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.write("content".getBytes(), output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testWrite3_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("content".getBytes(), output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite4() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.write("content".getBytes(), output, "UTF-8")

        // Verify the results
    }

    @Test
    void testWrite4_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("content".getBytes(), output, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite5() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write(['a'] as char[], output)

        // Verify the results
    }

    @Test
    void testWrite5_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(['a'] as char[], output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite6() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write(['a'] as char[], output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testWrite6_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(['a'] as char[], output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite7() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write(['a'] as char[], output, "UTF-8")

        // Verify the results
    }

    @Test
    void testWrite7_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(['a'] as char[], output, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite8() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.write(['a'] as char[], output)

        // Verify the results
    }

    @Test
    void testWrite8_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(['a'] as char[], output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite9() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("data", output)

        // Verify the results
    }

    @Test
    void testWrite9_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite10() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("data", output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testWrite10_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite11() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("data", output, "UTF-8")

        // Verify the results
    }

    @Test
    void testWrite11_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite12() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.write("data", output)

        // Verify the results
    }

    @Test
    void testWrite12_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite13() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("data", output)

        // Verify the results
    }

    @Test
    void testWrite13_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite14() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("data", output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testWrite14_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite15() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write("data", output, "UTF-8")

        // Verify the results
    }

    @Test
    void testWrite15_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite16() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.write("data", output)

        // Verify the results
    }

    @Test
    void testWrite16_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write("data", output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite17() {
        // Setup
        def data = new StringBuffer("value")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write(data, output)

        // Verify the results
    }

    @Test
    void testWrite17_BrokenOutput() {
        // Setup
        def data = new StringBuffer("value")
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(data, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite18() {
        // Setup
        def data = new StringBuffer("value")
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.write(data, output, "UTF-8")

        // Verify the results
    }

    @Test
    void testWrite18_BrokenOutput() {
        // Setup
        def data = new StringBuffer("value")
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(data, output, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWrite19() {
        // Setup
        def data = new StringBuffer("value")
        def output = new StringWriter()

        // Run the test
        MyClass.write(data, output)

        // Verify the results
    }

    @Test
    void testWrite19_BrokenOutput() {
        // Setup
        def data = new StringBuffer("value")
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.write(data, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriteChunked1() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.writeChunked("content".getBytes(), output)

        // Verify the results
    }

    @Test
    void testWriteChunked1_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.writeChunked("content".getBytes(), output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriteChunked2() {
        // Setup
        def output = new StringWriter()

        // Run the test
        MyClass.writeChunked(['a'] as char[], output)

        // Verify the results
    }

    @Test
    void testWriteChunked2_BrokenOutput() {
        // Setup
        def output = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.writeChunked(['a'] as char[], output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriteLines1() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.writeLines(["value"], "lineEnding", output)

        // Verify the results
    }

    @Test
    void testWriteLines1_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.writeLines(["value"], "lineEnding", output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriteLines2() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.writeLines(["value"], "lineEnding", output, StandardCharsets.UTF_8)

        // Verify the results
    }

    @Test
    void testWriteLines2_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.writeLines(["value"], "lineEnding", output, StandardCharsets.UTF_8)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriteLines3() {
        // Setup
        def output = new ByteArrayOutputStream()

        // Run the test
        MyClass.writeLines(["value"], "lineEnding", output, "UTF-8")

        // Verify the results
    }

    @Test
    void testWriteLines3_BrokenOutput() {
        // Setup
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.writeLines(["value"], "lineEnding", output, "UTF-8")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriteLines4() {
        // Setup
        def writer = new StringWriter()

        // Run the test
        MyClass.writeLines(["value"], "lineEnding", writer)

        // Verify the results
    }

    @Test
    void testWriteLines4_BrokenWriter() {
        // Setup
        def writer = new Writer() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final char[] cbuf, final int off, final int len) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.writeLines(["value"], "lineEnding", writer)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testWriter() {
        // Setup
        def appendable = new StringBuffer("value")

        // Run the test
        def result = MyClass.writer(appendable)

        // Verify the results
    }

    @Test
    void testWriter_ThrowsNullPointerException() {
        // Setup
        def appendable = new StringBuffer("value")

        // Run the test
        assertThatThrownBy({
            MyClass.writer(appendable)
        }).isInstanceOf(NullPointerException.class)
    }
}
