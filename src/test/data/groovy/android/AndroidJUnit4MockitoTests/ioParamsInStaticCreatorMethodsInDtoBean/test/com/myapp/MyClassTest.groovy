package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testGetId() {
        assert 0L == myClassUnderTest.getId()
    }

    @Test
    void testGetName() {
        assert "name" == myClassUnderTest.getName()
    }

    @Test
    void testFromInputStream() {
        // Setup
        def is = new ByteArrayInputStream("content".getBytes())

        // Run the test
        def result = MyClass.fromInputStream(is)
        assert 0L == result.getId()
        assert "name" == result.getName()
    }

    @Test
    void testFromInputStream_EmptyIs() {
        // Setup
        def is = new ByteArrayInputStream(new byte[]{})

        // Run the test
        def result = MyClass.fromInputStream(is)

        // Verify the results
    }

    @Test
    void testFromInputStream_BrokenIs() {
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
        def result = MyClass.fromInputStream(is)

        // Verify the results
    }

    @Test
    void testFromReader() {
        // Setup
        def reader = new StringReader("content")

        // Run the test
        def result = MyClass.fromReader(reader)
        assert 0L == result.getId()
        assert "name" == result.getName()
    }

    @Test
    void testFromReader_EmptyReader() {
        // Setup
        def reader = new StringReader("")

        // Run the test
        def result = MyClass.fromReader(reader)

        // Verify the results
    }

    @Test
    void testFromReader_BrokenReader() {
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
        def result = MyClass.fromReader(reader)

        // Verify the results
    }
}
