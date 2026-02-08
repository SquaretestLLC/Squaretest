package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

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
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testCopy_BrokenInput() {
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
        assertThrows(IOException.class, () -> MyClass.copy(input, output));
    }

    @Test
    void testCopy_BrokenOutput() {
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
        assertThrows(IOException.class, () -> MyClass.copy(input, output));
    }

    @Test
    void testName() {
        assertEquals("name", MyClass.INSTANCE1.name());
        assertEquals("name", MyClass.INSTANCE2.name());
    }

    @Test
    void testOrdinal() {
        assertEquals(0, MyClass.INSTANCE1.ordinal());
        assertEquals(0, MyClass.INSTANCE2.ordinal());
    }

    @Test
    void testToString() {
        assertEquals("name", MyClass.INSTANCE1.toString());
        assertEquals("name", MyClass.INSTANCE2.toString());
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.INSTANCE1.equals("other"));
        assertFalse(MyClass.INSTANCE2.equals("other"));
    }

    @Test
    void testHashCode() {
        assertEquals(0, MyClass.INSTANCE1.hashCode());
        assertEquals(0, MyClass.INSTANCE2.hashCode());
    }

    @Test
    void testCompareTo() {
        assertEquals(0, MyClass.INSTANCE1.compareTo(MyClass.INSTANCE1));
        assertEquals(0, MyClass.INSTANCE2.compareTo(MyClass.INSTANCE1));
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> MyClass.INSTANCE1.compareTo(MyClass.INSTANCE1));
        assertThrows(NullPointerException.class, () -> MyClass.INSTANCE2.compareTo(MyClass.INSTANCE1));
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, () -> MyClass.INSTANCE1.compareTo(MyClass.INSTANCE1));
        assertThrows(ClassCastException.class, () -> MyClass.INSTANCE2.compareTo(MyClass.INSTANCE1));
    }

    @Test
    void testGetDeclaringClass() {
        assertEquals(MyClass.class, MyClass.INSTANCE1.getDeclaringClass());
        assertEquals(MyClass.class, MyClass.INSTANCE2.getDeclaringClass());
    }

    @Test
    void testValueOf() {
        assertEquals(MyClass.INSTANCE1, Enum.valueOf(MyClass.class, "name"));
        assertThrows(IllegalArgumentException.class, () -> Enum.valueOf(MyClass.class, "name"));
        assertThrows(NullPointerException.class, () -> Enum.valueOf(MyClass.class, "name"));
    }
}
