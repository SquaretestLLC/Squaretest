package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testParseInputStream() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.INSTANCE.parseInputStream(is)

        // Verify the results
    }

    @Test
    void testParseInputStream_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.INSTANCE.parseInputStream(is)

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testParseInputStream_BrokenIs() {
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
        MyClass.INSTANCE.parseInputStream(is)
    }

    @Test
    void testParseReader() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.INSTANCE.parseReader(reader)

        // Verify the results
    }

    @Test
    void testParseReader_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.INSTANCE.parseReader(reader)

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testParseReader_BrokenReader() {
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
        MyClass.INSTANCE.parseReader(reader)
    }

    @Test
    void testSafeParseInputStream1() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.INSTANCE.safeParseInputStream(is)

        // Verify the results
    }

    @Test
    void testSafeParseInputStream1_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.INSTANCE.safeParseInputStream(is)

        // Verify the results
    }

    @Test
    void testSafeParseInputStream1_BrokenIs() {
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
        def result = MyClass.INSTANCE.safeParseInputStream(is)

        // Verify the results
    }

    @Test
    void testSafeParseReader1() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.INSTANCE.safeParseReader(reader)

        // Verify the results
    }

    @Test
    void testSafeParseReader1_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.INSTANCE.safeParseReader(reader)

        // Verify the results
    }

    @Test
    void testSafeParseReader1_BrokenReader() {
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
        def result = MyClass.INSTANCE.safeParseReader(reader)

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.INSTANCE.safeParseInputStream(is, 0L)

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.INSTANCE.safeParseInputStream(is, 0L)

        // Verify the results
    }

    @Test
    void testSafeParseInputStream2_BrokenIs() {
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
        def result = MyClass.INSTANCE.safeParseInputStream(is, 0L)

        // Verify the results
    }

    @Test
    void testSafeParseReader2() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.INSTANCE.safeParseReader(reader, 0L)

        // Verify the results
    }

    @Test
    void testSafeParseReader2_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.INSTANCE.safeParseReader(reader, 0L)

        // Verify the results
    }

    @Test
    void testSafeParseReader2_BrokenReader() {
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
        def result = MyClass.INSTANCE.safeParseReader(reader, 0L)

        // Verify the results
    }

    @Test
    void testWriteToOs() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

        def outputStream = new ByteArrayOutputStream()

        // Run the test
        MyClass.INSTANCE.writeToOs(jsonDto, outputStream)

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testWriteToOs_BrokenOutputStream() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

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
        MyClass.INSTANCE.writeToOs(jsonDto, outputStream)
    }

    @Test
    void testWriteToWriter() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

        def writer = new StringWriter()

        // Run the test
        MyClass.INSTANCE.writeToWriter(jsonDto, writer)

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testWriteToWriter_BrokenWriter() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

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
        MyClass.INSTANCE.writeToWriter(jsonDto, writer)
    }

    @Test
    void testSafeWriteToOs() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

        def outputStream = new ByteArrayOutputStream()

        // Run the test
        MyClass.INSTANCE.safeWriteToOs(jsonDto, outputStream)

        // Verify the results
    }

    @Test
    void testSafeWriteToOs_BrokenOutputStream() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

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
        MyClass.INSTANCE.safeWriteToOs(jsonDto, outputStream)

        // Verify the results
    }

    @Test
    void testSafeWriteToWriter() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

        def writer = new StringWriter()

        // Run the test
        MyClass.INSTANCE.safeWriteToWriter(jsonDto, writer)

        // Verify the results
    }

    @Test
    void testSafeWriteToWriter_BrokenWriter() {
        // Setup
        def jsonDto = new JsonDto()
        jsonDto.setName("name")
        jsonDto.setDisplayName("displayName")
        jsonDto.setId(0L)

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
        MyClass.INSTANCE.safeWriteToWriter(jsonDto, writer)

        // Verify the results
    }

    @Test
    void testCopy() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testCopy_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assert 0 == result
    }

    @Test(expected = IOException.class)
    void testCopy_BrokenInput() {
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
        MyClass.copy(input, output)
    }

    @Test(expected = IOException.class)
    void testCopy_BrokenOutput() {
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
        MyClass.copy(input, output)
    }
}
