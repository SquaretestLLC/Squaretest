package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testGetId() {
        assertEquals(0L, myClassUnderTest.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("name", myClassUnderTest.getName());
    }

    @Test
    public void testFromInputStream() {
        // Setup
        final InputStream is = new ByteArrayInputStream("content".getBytes());

        // Run the test
        final MyClass result = MyClass.fromInputStream(is);
        assertEquals(0L, result.getId());
        assertEquals("name", result.getName());
    }

    @Test
    public void testFromInputStream_EmptyIs() {
        // Setup
        final InputStream is = new ByteArrayInputStream(new byte[]{});

        // Run the test
        final MyClass result = MyClass.fromInputStream(is);

        // Verify the results
    }

    @Test
    public void testFromInputStream_BrokenIs() {
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
        final MyClass result = MyClass.fromInputStream(is);

        // Verify the results
    }

    @Test
    public void testFromReader() {
        // Setup
        final Reader reader = new StringReader("content");

        // Run the test
        final MyClass result = MyClass.fromReader(reader);
        assertEquals(0L, result.getId());
        assertEquals("name", result.getName());
    }

    @Test
    public void testFromReader_EmptyReader() {
        // Setup
        final Reader reader = new StringReader("");

        // Run the test
        final MyClass result = MyClass.fromReader(reader);

        // Verify the results
    }

    @Test
    public void testFromReader_BrokenReader() {
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
        final MyClass result = MyClass.fromReader(reader);

        // Verify the results
    }
}
