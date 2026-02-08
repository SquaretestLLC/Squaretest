package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testParseInputStream() {
        assert new JsonDto() == MyClass.INSTANCE1.parseInputStream(new ByteArrayInputStream("content".getBytes()))
        assert new JsonDto() == MyClass.INSTANCE2.parseInputStream(new ByteArrayInputStream("content".getBytes()))
    }

    @Test
    void testParseReader() {
        assert new JsonDto() == MyClass.INSTANCE1.parseReader(new StringReader("content"))
        assert new JsonDto() == MyClass.INSTANCE2.parseReader(new StringReader("content"))
    }

    @Test
    void testSafeParseInputStream1() {
        assert new JsonDto() == MyClass.INSTANCE1.safeParseInputStream(new ByteArrayInputStream("content".getBytes()))
        assert new JsonDto() == MyClass.INSTANCE2.safeParseInputStream(new ByteArrayInputStream("content".getBytes()))
    }

    @Test
    void testSafeParseReader1() {
        assert new JsonDto() == MyClass.INSTANCE1.safeParseReader(new StringReader("content"))
        assert new JsonDto() == MyClass.INSTANCE2.safeParseReader(new StringReader("content"))
    }

    @Test
    void testSafeParseInputStream2() {
        assert new JsonDto() == MyClass.INSTANCE1.safeParseInputStream(new ByteArrayInputStream("content".getBytes()),
                0L)
        assert new JsonDto() == MyClass.INSTANCE2.safeParseInputStream(new ByteArrayInputStream("content".getBytes()),
                0L)
    }

    @Test
    void testSafeParseReader2() {
        assert new JsonDto() == MyClass.INSTANCE1.safeParseReader(new StringReader("content"), 0L)
        assert new JsonDto() == MyClass.INSTANCE2.safeParseReader(new StringReader("content"), 0L)
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
        MyClass.INSTANCE1.writeToOs(jsonDto, outputStream)
        MyClass.INSTANCE2.writeToOs(jsonDto, outputStream)

        // Verify the results
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
        MyClass.INSTANCE1.writeToWriter(jsonDto, writer)
        MyClass.INSTANCE2.writeToWriter(jsonDto, writer)

        // Verify the results
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
        MyClass.INSTANCE1.safeWriteToOs(jsonDto, outputStream)
        MyClass.INSTANCE2.safeWriteToOs(jsonDto, outputStream)

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
        MyClass.INSTANCE1.safeWriteToWriter(jsonDto, writer)
        MyClass.INSTANCE2.safeWriteToWriter(jsonDto, writer)

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

    @Test
    void testName() {
        assert "result" == MyClass.INSTANCE1.name()
        assert "result" == MyClass.INSTANCE2.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.INSTANCE1.ordinal()
        assert 0 == MyClass.INSTANCE2.ordinal()
    }

    @Test
    void testToString() {
        assert "result" == MyClass.INSTANCE1.toString()
        assert "result" == MyClass.INSTANCE2.toString()
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.INSTANCE1.equals("other"))
        assertFalse(MyClass.INSTANCE2.equals("other"))
    }

    @Test
    void testHashCode() {
        assert 0 == MyClass.INSTANCE1.hashCode()
        assert 0 == MyClass.INSTANCE2.hashCode()
    }

    @Test
    void testCompareTo() {
        assert 0 == MyClass.INSTANCE1.compareTo(MyClass.INSTANCE1)
        assert 0 == MyClass.INSTANCE2.compareTo(MyClass.INSTANCE1)
    }

    @Test
    void testGetDeclaringClass() {
        assert MyClass.class == MyClass.INSTANCE1.getDeclaringClass()
        assert MyClass.class == MyClass.INSTANCE2.getDeclaringClass()
    }

    @Test
    void testValueOf() {
        assert MyClass.INSTANCE1 == Enum.valueOf(MyClass.class, "name")
    }
}
