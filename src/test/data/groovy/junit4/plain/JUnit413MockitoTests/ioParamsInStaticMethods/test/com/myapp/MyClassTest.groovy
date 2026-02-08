package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.channels.Selector
import java.nio.charset.StandardCharsets
import java.nio.charset.UnsupportedCharsetException
import java.util.function.Consumer

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertThrows
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
        assertThrows(IOException.class, {
            MyClass.close(closeable)
        })
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
        assertThrows(IOException.class, {
            MyClass.close(closeables)
        })
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
        assertThrows(IOException.class, {
            MyClass.close(closeable, consumer)
        })
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
        assert 0L == result
    }

    @Test
    void testConsume_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.consume(input)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.consume(input)
        })
    }

    @Test
    void testContentEquals1() {
        // Setup
        def input1 = new ByteArrayInputStream("content".getBytes())
        def input2 = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testContentEquals1_EmptyInput1() {
        // Setup
        def input1 = new ByteArrayInputStream(new byte[]{})
        def input2 = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertFalse(result)
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
        assertThrows(IOException.class, {
            MyClass.contentEquals(input1, input2)
        })
    }

    @Test
    void testContentEquals1_EmptyInput2() {
        // Setup
        def input1 = new ByteArrayInputStream("content".getBytes())
        def input2 = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertFalse(result)
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
        assertThrows(IOException.class, {
            MyClass.contentEquals(input1, input2)
        })
    }

    @Test
    void testContentEquals2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testContentEquals2_EmptyInput1() {
        // Setup
        def input1 = new StringReader("")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertFalse(result)
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
        assertThrows(IOException.class, {
            MyClass.contentEquals(input1, input2)
        })
    }

    @Test
    void testContentEquals2_EmptyInput2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("")

        // Run the test
        def result = MyClass.contentEquals(input1, input2)

        // Verify the results
        assertFalse(result)
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
        assertThrows(IOException.class, {
            MyClass.contentEquals(input1, input2)
        })
    }

    @Test
    void testContentEqualsIgnoreEOL() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEqualsIgnoreEOL(input1, input2)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testContentEqualsIgnoreEOL_EmptyInput1() {
        // Setup
        def input1 = new StringReader("")
        def input2 = new StringReader("content")

        // Run the test
        def result = MyClass.contentEqualsIgnoreEOL(input1, input2)

        // Verify the results
        assertFalse(result)
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
        assertThrows(IOException.class, {
            MyClass.contentEqualsIgnoreEOL(input1, input2)
        })
    }

    @Test
    void testContentEqualsIgnoreEOL_EmptyInput2() {
        // Setup
        def input1 = new StringReader("content")
        def input2 = new StringReader("")

        // Run the test
        def result = MyClass.contentEqualsIgnoreEOL(input1, input2)

        // Verify the results
        assertFalse(result)
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
        assertThrows(IOException.class, {
            MyClass.contentEqualsIgnoreEOL(input1, input2)
        })
    }

    @Test
    void testCopy1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testCopy1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0 == result
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
    }

    @Test
    void testCopy2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output, 0)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopy2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output, 0)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, 0)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, 0)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, "inputCharsetName")
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, "inputCharsetName")
        })
    }

    @Test
    void testCopy6() {
        // Setup
        def input = new StringReader("content")
        def output = new StringBuffer("value")

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopy6_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringBuffer("value")

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assert 0L == result
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
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, buffer)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, "outputCharsetName")
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output, "outputCharsetName")
        })
    }

    @Test
    void testCopy11() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testCopy11_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0 == result
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copy(input, output)
        })
    }

    @Test
    void testCopyLarge1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output)
        })
    }

    @Test
    void testCopyLarge2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, "content".getBytes())

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, "content".getBytes())

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, "content".getBytes())
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, "content".getBytes())
        })
    }

    @Test
    void testCopyLarge3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L)
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L)
        })
    }

    @Test
    void testCopyLarge4() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge4_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L, "content".getBytes())
        })
    }

    @Test
    void testCopyLarge5() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge5_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output)
        })
    }

    @Test
    void testCopyLarge6() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, ['a'] as char[])

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge6_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, ['a'] as char[])

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, ['a'] as char[])
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, ['a'] as char[])
        })
    }

    @Test
    void testCopyLarge7() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge7_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L)
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L)
        })
    }

    @Test
    void testCopyLarge8() {
        // Setup
        def input = new StringReader("content")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])

        // Verify the results
        assert 0L == result
    }

    @Test
    void testCopyLarge8_EmptyInput() {
        // Setup
        def input = new StringReader("")
        def output = new StringWriter()

        // Run the test
        def result = MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])
        })
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
        assertThrows(IOException.class, {
            MyClass.copyLarge(input, output, 0L, 0L, ['a'] as char[])
        })
    }

    @Test
    void testLength1() {
        assert 0 == MyClass.length("content".getBytes())
    }

    @Test
    void testLength2() {
        assert 0 == MyClass.length(['a'] as char[])
    }

    @Test
    void testLength3() {
        assert 0 == MyClass.length("csq")
    }

    @Test
    void testLength4() {
        assert 0 == MyClass.length(["array"] as Object[])
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
        assertThrows(IOException.class, {
            MyClass.lineIterator(input, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.lineIterator(input, "UTF-8")
        })
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
        assert 0 == result
    }

    @Test
    void testRead1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.read(input, "content".getBytes())

        // Verify the results
        assert 0 == result
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
        assertThrows(IOException.class, {
            MyClass.read(input, "content".getBytes())
        })
    }

    @Test
    void testRead2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.read(input, "content".getBytes(), 0, 0)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testRead2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.read(input, "content".getBytes(), 0, 0)

        // Verify the results
        assert 0 == result
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
        assertThrows(IOException.class, {
            MyClass.read(input, "content".getBytes(), 0, 0)
        })
    }

    @Test
    void testRead3() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        def result = MyClass.read(input, buffer)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testRead3_ThrowsIOException() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        assertThrows(IOException.class, {
            MyClass.read(input, buffer)
        })
    }

    @Test
    void testRead4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[])

        // Verify the results
        assert 0 == result
    }

    @Test
    void testRead4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[])

        // Verify the results
        assert 0 == result
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
        assertThrows(IOException.class, {
            MyClass.read(input, ['a'] as char[])
        })
    }

    @Test
    void testRead5() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[], 0, 0)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testRead5_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.read(input, ['a'] as char[], 0, 0)

        // Verify the results
        assert 0 == result
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
        assertThrows(IOException.class, {
            MyClass.read(input, ['a'] as char[], 0, 0)
        })
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
        assertThrows(IOException.class, {
            MyClass.readFully(input, "content".getBytes())
        })
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
        assertThrows(IOException.class, {
            MyClass.readFully(input, "content".getBytes(), 0, 0)
        })
    }

    @Test
    void testReadFully3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readFully(input, 0)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testReadFully3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readFully(input, 0)

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.readFully(input, 0)
        })
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
        assertThrows(IOException.class, {
            MyClass.readFully(input, buffer)
        })
    }

    @Test
    void testReadFully4_ThrowsEOFException() {
        // Setup
        def input = null
        def buffer = ByteBuffer.wrap("content".getBytes())

        // Run the test
        assertThrows(EOFException.class, {
            MyClass.readFully(input, buffer)
        })
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
        assertThrows(IOException.class, {
            MyClass.readFully(input, ['a'] as char[])
        })
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
        assertThrows(IOException.class, {
            MyClass.readFully(input, ['a'] as char[], 0, 0)
        })
    }

    @Test
    void testReadLines1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testReadLines1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assert ["value"] == result
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
        assertThrows(IOException.class, {
            MyClass.readLines(input)
        })
    }

    @Test
    void testReadLines2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readLines(input, StandardCharsets.UTF_8)

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testReadLines2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readLines(input, StandardCharsets.UTF_8)

        // Verify the results
        assert ["value"] == result
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
        assertThrows(IOException.class, {
            MyClass.readLines(input, StandardCharsets.UTF_8)
        })
    }

    @Test
    void testReadLines3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.readLines(input, "UTF-8")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testReadLines3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.readLines(input, "UTF-8")

        // Verify the results
        assert ["value"] == result
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
        assertThrows(IOException.class, {
            MyClass.readLines(input, "UTF-8")
        })
    }

    @Test
    void testReadLines4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testReadLines4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.readLines(input)

        // Verify the results
        assert ["value"] == result
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
        assertThrows(IOException.class, {
            MyClass.readLines(input)
        })
    }

    @Test
    void testResourceToByteArray1() {
        assert "content".getBytes() == MyClass.resourceToByteArray("name")
        assertThrows(IOException.class, {
            MyClass.resourceToByteArray("name")
        })
    }

    @Test
    void testResourceToByteArray2() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        def result = MyClass.resourceToByteArray("name", classLoader)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testResourceToByteArray2_ThrowsIOException() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        assertThrows(IOException.class, {
            MyClass.resourceToByteArray("name", classLoader)
        })
    }

    @Test
    void testResourceToString1() {
        assert "result" == MyClass.resourceToString("name", StandardCharsets.UTF_8)
        assertThrows(IOException.class, {
            MyClass.resourceToString("name", StandardCharsets.UTF_8)
        })
    }

    @Test
    void testResourceToString2() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        def result = MyClass.resourceToString("name", StandardCharsets.UTF_8, classLoader)

        // Verify the results
        assert "result" == result
    }

    @Test
    void testResourceToString2_ThrowsIOException() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        assertThrows(IOException.class, {
            MyClass.resourceToString("name", StandardCharsets.UTF_8, classLoader)
        })
    }

    @Test
    void testResourceToURL1() {
        assert new URL("https://example.com/") == MyClass.resourceToURL("name")
        assertThrows(IOException.class, {
            MyClass.resourceToURL("name")
        })
    }

    @Test
    void testResourceToURL2() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        def result = MyClass.resourceToURL("name", classLoader)

        // Verify the results
        assert new URL("https://example.com/") == result
    }

    @Test
    void testResourceToURL2_ThrowsIOException() {
        // Setup
        def classLoader = ClassLoader.getPlatformClassLoader()

        // Run the test
        assertThrows(IOException.class, {
            MyClass.resourceToURL("name", classLoader)
        })
    }

    @Test
    void testSkip1() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testSkip1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.skip(input, 0L)
        })
    }

    @Test
    void testSkip2() {
        // Setup
        def input = null

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testSkip2_ThrowsIOException() {
        // Setup
        def input = null

        // Run the test
        assertThrows(IOException.class, {
            MyClass.skip(input, 0L)
        })
    }

    @Test
    void testSkip2_ThrowsIllegalArgumentException() {
        // Setup
        def input = null

        // Run the test
        assertThrows(IllegalArgumentException.class, {
            MyClass.skip(input, 0L)
        })
    }

    @Test
    void testSkip3() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assert 0L == result
    }

    @Test
    void testSkip3_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.skip(input, 0L)

        // Verify the results
        assert 0L == result
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
        assertThrows(IOException.class, {
            MyClass.skip(input, 0L)
        })
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
        assertThrows(IOException.class, {
            MyClass.skipFully(input, 0L)
        })
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
        assertThrows(IOException.class, {
            MyClass.skipFully(input, 0L)
        })
    }

    @Test
    void testSkipFully2_ThrowsIllegalArgumentException() {
        // Setup
        def input = null

        // Run the test
        assertThrows(IllegalArgumentException.class, {
            MyClass.skipFully(input, 0L)
        })
    }

    @Test
    void testSkipFully2_ThrowsEOFException() {
        // Setup
        def input = null

        // Run the test
        assertThrows(EOFException.class, {
            MyClass.skipFully(input, 0L)
        })
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
        assertThrows(IOException.class, {
            MyClass.skipFully(input, 0L)
        })
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
        assertThrows(IOException.class, {
            MyClass.toBufferedInputStream(input)
        })
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
        assertThrows(IOException.class, {
            MyClass.toBufferedInputStream(input, 0)
        })
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
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray1_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.toByteArray(input)
        })
    }

    @Test
    void testToByteArray2() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toByteArray(input, 0)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray2_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toByteArray(input, 0)

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.toByteArray(input, 0)
        })
    }

    @Test
    void testToByteArray3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toByteArray(input, 0L)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toByteArray(input, 0L)

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.toByteArray(input, 0L)
        })
    }

    @Test
    void testToByteArray4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toByteArray(input)

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.toByteArray(input)
        })
    }

    @Test
    void testToByteArray5() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toByteArray(input, StandardCharsets.UTF_8)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray5_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toByteArray(input, StandardCharsets.UTF_8)

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.toByteArray(input, StandardCharsets.UTF_8)
        })
    }

    @Test
    void testToByteArray6() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toByteArray(input, "UTF-8")

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray6_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toByteArray(input, "UTF-8")

        // Verify the results
        assert "content".getBytes() == result
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
        assertThrows(IOException.class, {
            MyClass.toByteArray(input, "UTF-8")
        })
    }

    @Test
    void testToByteArray7() {
        assert "content".getBytes() == MyClass.toByteArray("input")
        assertThrows(IOException.class, {
            MyClass.toByteArray("input")
        })
        assertThrows(NullPointerException.class, {
            MyClass.toByteArray("input")
        })
    }

    @Test
    void testToByteArray8() {
        assert "content".getBytes() == MyClass.toByteArray(new URI("https://example.com/"))
        assertThrows(IOException.class, {
            MyClass.toByteArray(new URI("https://example.com/"))
        })
        assertThrows(NullPointerException.class, {
            MyClass.toByteArray(new URI("https://example.com/"))
        })
    }

    @Test
    void testToByteArray9() {
        assert "content".getBytes() == MyClass.toByteArray(new URL("https://example.com/"))
        assertThrows(IOException.class, {
            MyClass.toByteArray(new URL("https://example.com/"))
        })
        assertThrows(NullPointerException.class, {
            MyClass.toByteArray(new URL("https://example.com/"))
        })
    }

    @Test
    void testToByteArray10() {
        // Setup
        def urlConn = null

        // Run the test
        def result = MyClass.toByteArray(urlConn)

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testToByteArray10_ThrowsIOException() {
        // Setup
        def urlConn = null

        // Run the test
        assertThrows(IOException.class, {
            MyClass.toByteArray(urlConn)
        })
    }

    @Test
    void testToByteArray10_ThrowsNullPointerException() {
        // Setup
        def urlConn = null

        // Run the test
        assertThrows(NullPointerException.class, {
            MyClass.toByteArray(urlConn)
        })
    }

    @Test
    void testToCharArray1() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toCharArray(is)

        // Verify the results
        assert ['a'] as char[] == result
    }

    @Test
    void testToCharArray1_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toCharArray(is)

        // Verify the results
        assert ['a'] as char[] == result
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
        assertThrows(IOException.class, {
            MyClass.toCharArray(is)
        })
    }

    @Test
    void testToCharArray2() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toCharArray(is, StandardCharsets.UTF_8)

        // Verify the results
        assert ['a'] as char[] == result
    }

    @Test
    void testToCharArray2_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toCharArray(is, StandardCharsets.UTF_8)

        // Verify the results
        assert ['a'] as char[] == result
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
        assertThrows(IOException.class, {
            MyClass.toCharArray(is, StandardCharsets.UTF_8)
        })
    }

    @Test
    void testToCharArray3() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toCharArray(is, "UTF-8")

        // Verify the results
        assert ['a'] as char[] == result
    }

    @Test
    void testToCharArray3_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toCharArray(is, "UTF-8")

        // Verify the results
        assert ['a'] as char[] == result
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
        assertThrows(IOException.class, {
            MyClass.toCharArray(is, "UTF-8")
        })
    }

    @Test
    void testToCharArray4() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toCharArray(input)

        // Verify the results
        assert ['a'] as char[] == result
    }

    @Test
    void testToCharArray4_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toCharArray(input)

        // Verify the results
        assert ['a'] as char[] == result
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
        assertThrows(IOException.class, {
            MyClass.toCharArray(input)
        })
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
        assertThrows(IOException.class, {
            MyClass.toInputStream("input", "UTF-8")
        })
    }

    @Test
    void testToInputStream3_ThrowsUnsupportedCharsetException() {
        // Setup
        // Run the test
        assertThrows(UnsupportedCharsetException.class, {
            MyClass.toInputStream("input", "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.toInputStream("input", "UTF-8")
        })
    }

    @Test
    void testToInputStream6_ThrowsUnsupportedCharsetException() {
        // Setup
        // Run the test
        assertThrows(UnsupportedCharsetException.class, {
            MyClass.toInputStream("input", "UTF-8")
        })
    }

    @Test
    void testToString1() {
        assert "result" == MyClass.toString("content".getBytes())
        assertThrows(IOException.class, {
            MyClass.toString("content".getBytes())
        })
        assertThrows(NullPointerException.class, {
            MyClass.toString("content".getBytes())
        })
    }

    @Test
    void testToString2() {
        assert "result" == MyClass.toString("content".getBytes(), "UTF-8")
        assertThrows(IOException.class, {
            MyClass.toString("content".getBytes(), "UTF-8")
        })
        assertThrows(NullPointerException.class, {
            MyClass.toString("content".getBytes(), "UTF-8")
        })
    }

    @Test
    void testToString3() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assert "result" == result
    }

    @Test
    void testToString3_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assert "result" == result
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
        assertThrows(IOException.class, {
            MyClass.toString(input)
        })
    }

    @Test
    void testToString4() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toString(input, StandardCharsets.UTF_8)

        // Verify the results
        assert "result" == result
    }

    @Test
    void testToString4_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toString(input, StandardCharsets.UTF_8)

        // Verify the results
        assert "result" == result
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
        assertThrows(IOException.class, {
            MyClass.toString(input, StandardCharsets.UTF_8)
        })
    }

    @Test
    void testToString5() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.toString(input, "UTF-8")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testToString5_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.toString(input, "UTF-8")

        // Verify the results
        assert "result" == result
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
        assertThrows(IOException.class, {
            MyClass.toString(input, "UTF-8")
        })
    }

    @Test
    void testToString6() {
        // Setup
        def input = new StringReader("content")

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assert "result" == result
    }

    @Test
    void testToString6_EmptyInput() {
        // Setup
        def input = new StringReader("")

        // Run the test
        def result = MyClass.toString(input)

        // Verify the results
        assert "result" == result
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
        assertThrows(IOException.class, {
            MyClass.toString(input)
        })
    }

    @Test
    void testToString7() {
        assert "result" == MyClass.toString(new URI("https://example.com/"))
        assertThrows(IOException.class, {
            MyClass.toString(new URI("https://example.com/"))
        })
    }

    @Test
    void testToString8() {
        assert "result" == MyClass.toString(new URI("https://example.com/"), StandardCharsets.UTF_8)
        assertThrows(IOException.class, {
            MyClass.toString(new URI("https://example.com/"), StandardCharsets.UTF_8)
        })
    }

    @Test
    void testToString9() {
        assert "result" == MyClass.toString(new URI("https://example.com/"), "UTF-8")
        assertThrows(IOException.class, {
            MyClass.toString(new URI("https://example.com/"), "UTF-8")
        })
        assertThrows(UnsupportedCharsetException.class, {
            MyClass.toString(new URI("https://example.com/"), "UTF-8")
        })
    }

    @Test
    void testToString10() {
        assert "result" == MyClass.toString(new URL("https://example.com/"))
        assertThrows(IOException.class, {
            MyClass.toString(new URL("https://example.com/"))
        })
    }

    @Test
    void testToString11() {
        assert "result" == MyClass.toString(new URL("https://example.com/"), StandardCharsets.UTF_8)
        assertThrows(IOException.class, {
            MyClass.toString(new URL("https://example.com/"), StandardCharsets.UTF_8)
        })
    }

    @Test
    void testToString12() {
        assert "result" == MyClass.toString(new URL("https://example.com/"), "UTF-8")
        assertThrows(IOException.class, {
            MyClass.toString(new URL("https://example.com/"), "UTF-8")
        })
        assertThrows(UnsupportedCharsetException.class, {
            MyClass.toString(new URL("https://example.com/"), "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.write("content".getBytes(), output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("content".getBytes(), output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("content".getBytes(), output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("content".getBytes(), output, "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.write(['a'] as char[], output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write(['a'] as char[], output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.write(['a'] as char[], output, "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.write(['a'] as char[], output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output, "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output, "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.write("data", output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write(data, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.write(data, output, "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.write(data, output)
        })
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
        assertThrows(IOException.class, {
            MyClass.writeChunked("content".getBytes(), output)
        })
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
        assertThrows(IOException.class, {
            MyClass.writeChunked(['a'] as char[], output)
        })
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
        assertThrows(IOException.class, {
            MyClass.writeLines(["value"], "lineEnding", output)
        })
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
        assertThrows(IOException.class, {
            MyClass.writeLines(["value"], "lineEnding", output, StandardCharsets.UTF_8)
        })
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
        assertThrows(IOException.class, {
            MyClass.writeLines(["value"], "lineEnding", output, "UTF-8")
        })
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
        assertThrows(IOException.class, {
            MyClass.writeLines(["value"], "lineEnding", writer)
        })
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
        assertThrows(NullPointerException.class, {
            MyClass.writer(appendable)
        })
    }
}
