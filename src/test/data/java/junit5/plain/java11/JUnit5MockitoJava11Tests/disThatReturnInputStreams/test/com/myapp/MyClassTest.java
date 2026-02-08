package com.myapp;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.myapp.closeables.CloseableFoo;
import com.myapp.closeables.FooProvider;
import com.myapp.closeables.FooWithIs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @Test
    void testMakeInputStreamFuture() {
        // Setup
        // Configure FooProvider.makeInputStreamFuture(...).
        final Future<InputStream> inputStreamFuture = CompletableFuture.completedFuture(
                new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeInputStreamFuture()).thenReturn(inputStreamFuture);

        // Run the test
        final Future<InputStream> result = myClassUnderTest.makeInputStreamFuture();

        // Verify the results
    }

    @Test
    void testMakeInputStreamFuture_FooProviderReturnsFailure() {
        // Setup
        // Configure FooProvider.makeInputStreamFuture(...).
        final Future<InputStream> inputStreamFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(mockFooProvider.makeInputStreamFuture()).thenReturn(inputStreamFuture);

        // Run the test
        final Future<InputStream> result = myClassUnderTest.makeInputStreamFuture();

        // Verify the results
    }

    @Test
    void testMakeCloseableFooFuture() {
        // Setup
        // Configure FooProvider.makeCloseableFooFuture(...).
        final Future<CloseableFoo> closeableFooFuture = CompletableFuture.completedFuture(
                new CloseableFoo(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeCloseableFooFuture()).thenReturn(closeableFooFuture);

        // Run the test
        final Future<CloseableFoo> result = myClassUnderTest.makeCloseableFooFuture();

        // Verify the results
    }

    @Test
    void testMakeCloseableFooFuture_FooProviderReturnsFailure() {
        // Setup
        // Configure FooProvider.makeCloseableFooFuture(...).
        final Future<CloseableFoo> closeableFooFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(mockFooProvider.makeCloseableFooFuture()).thenReturn(closeableFooFuture);

        // Run the test
        final Future<CloseableFoo> result = myClassUnderTest.makeCloseableFooFuture();

        // Verify the results
    }

    @Test
    void testMakeFooWithIsFuture() {
        // Setup
        // Configure FooProvider.makeFooWithIsFuture(...).
        final Future<FooWithIs> fooWithIsFuture = CompletableFuture.completedFuture(
                new FooWithIs(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeFooWithIsFuture()).thenReturn(fooWithIsFuture);

        // Run the test
        final Future<FooWithIs> result = myClassUnderTest.makeFooWithIsFuture();

        // Verify the results
    }

    @Test
    void testMakeFooWithIsFuture_FooProviderReturnsFailure() {
        // Setup
        // Configure FooProvider.makeFooWithIsFuture(...).
        final Future<FooWithIs> fooWithIsFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(mockFooProvider.makeFooWithIsFuture()).thenReturn(fooWithIsFuture);

        // Run the test
        final Future<FooWithIs> result = myClassUnderTest.makeFooWithIsFuture();

        // Verify the results
    }

    @Test
    void testMakeInputStreamListenableFuture() {
        // Setup
        // Configure FooProvider.makeInputStreamListenableFuture(...).
        final ListenableFuture<InputStream> inputStreamListenableFuture = Futures.immediateFuture(
                new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeInputStreamListenableFuture()).thenReturn(inputStreamListenableFuture);

        // Run the test
        final ListenableFuture<InputStream> result = myClassUnderTest.makeInputStreamListenableFuture();

        // Verify the results
    }

    @Test
    void testMakeInputStreamListenableFuture_FooProviderReturnsFailure() {
        // Setup
        // Configure FooProvider.makeInputStreamListenableFuture(...).
        final ListenableFuture<InputStream> inputStreamListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        when(mockFooProvider.makeInputStreamListenableFuture()).thenReturn(inputStreamListenableFuture);

        // Run the test
        final ListenableFuture<InputStream> result = myClassUnderTest.makeInputStreamListenableFuture();

        // Verify the results
    }

    @Test
    void testMakeCloseableFooListenableFuture() {
        // Setup
        // Configure FooProvider.makeCloseableFooListenableFuture(...).
        final ListenableFuture<CloseableFoo> closeableFooListenableFuture = Futures.immediateFuture(
                new CloseableFoo(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeCloseableFooListenableFuture()).thenReturn(closeableFooListenableFuture);

        // Run the test
        final ListenableFuture<CloseableFoo> result = myClassUnderTest.makeCloseableFooListenableFuture();

        // Verify the results
    }

    @Test
    void testMakeCloseableFooListenableFuture_FooProviderReturnsFailure() {
        // Setup
        // Configure FooProvider.makeCloseableFooListenableFuture(...).
        final ListenableFuture<CloseableFoo> closeableFooListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        when(mockFooProvider.makeCloseableFooListenableFuture()).thenReturn(closeableFooListenableFuture);

        // Run the test
        final ListenableFuture<CloseableFoo> result = myClassUnderTest.makeCloseableFooListenableFuture();

        // Verify the results
    }

    @Test
    void testMakeFooWithIsListenableFuture() {
        // Setup
        // Configure FooProvider.makeFooWithIsListenableFuture(...).
        final ListenableFuture<FooWithIs> fooWithIsListenableFuture = Futures.immediateFuture(
                new FooWithIs(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeFooWithIsListenableFuture()).thenReturn(fooWithIsListenableFuture);

        // Run the test
        final ListenableFuture<FooWithIs> result = myClassUnderTest.makeFooWithIsListenableFuture();

        // Verify the results
    }

    @Test
    void testMakeFooWithIsListenableFuture_FooProviderReturnsFailure() {
        // Setup
        // Configure FooProvider.makeFooWithIsListenableFuture(...).
        final ListenableFuture<FooWithIs> fooWithIsListenableFuture = Futures.immediateFailedFuture(
                new Exception("message"));
        when(mockFooProvider.makeFooWithIsListenableFuture()).thenReturn(fooWithIsListenableFuture);

        // Run the test
        final ListenableFuture<FooWithIs> result = myClassUnderTest.makeFooWithIsListenableFuture();

        // Verify the results
    }

    @Test
    void testMakeInputStream() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStream();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStream_FooProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(InputStream.nullInputStream());
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStream();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStream_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new InputStream() {
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
        });
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStream();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeCloseableFoo() throws Exception {
        // Setup
        // Configure FooProvider.makeCloseableFoo(...).
        final CloseableFoo spyCloseableFoo = spy(new CloseableFoo(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeCloseableFoo()).thenReturn(spyCloseableFoo);

        // Run the test
        final CloseableFoo result = myClassUnderTest.makeCloseableFoo();

        // Verify the results
        verify(spyCloseableFoo).close();
    }

    @Test
    void testMakeFooWithIs() {
        // Setup
        // Configure FooProvider.makeFooWithIs(...).
        final FooWithIs fooWithIs = new FooWithIs(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeFooWithIs()).thenReturn(fooWithIs);

        // Run the test
        final FooWithIs result = myClassUnderTest.makeFooWithIs();

        // Verify the results
    }

    @Test
    void testMakeReader() throws Exception {
        // Setup
        // Configure FooProvider.makeReader(...).
        final Reader spyReader = spy(new StringReader("content"));
        when(mockFooProvider.makeReader()).thenReturn(spyReader);

        // Run the test
        final Reader result = myClassUnderTest.makeReader();

        // Verify the results
        verify(spyReader).close();
    }

    @Test
    void testMakeReader_FooProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure FooProvider.makeReader(...).
        final Reader spyReader = spy(Reader.nullReader());
        when(mockFooProvider.makeReader()).thenReturn(spyReader);

        // Run the test
        final Reader result = myClassUnderTest.makeReader();

        // Verify the results
        verify(spyReader).close();
    }

    @Test
    void testMakeReader_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeReader(...).
        final Reader spyReader = spy(new Reader() {

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
        });
        when(mockFooProvider.makeReader()).thenReturn(spyReader);

        // Run the test
        final Reader result = myClassUnderTest.makeReader();

        // Verify the results
        verify(spyReader).close();
    }

    @Test
    void testMakeOutputStream() throws Exception {
        // Setup
        // Configure FooProvider.makeOutputStream(...).
        final OutputStream spyOutputStream = spy(new ByteArrayOutputStream());
        when(mockFooProvider.makeOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        final OutputStream result = myClassUnderTest.makeOutputStream();

        // Verify the results
        verify(spyOutputStream).close();
    }

    @Test
    void testMakeOutputStream_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeOutputStream(...).
        final OutputStream spyOutputStream = spy(new OutputStream() {

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
        });
        when(mockFooProvider.makeOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        final OutputStream result = myClassUnderTest.makeOutputStream();

        // Verify the results
        verify(spyOutputStream).close();
    }

    @Test
    void testMakeWriter() throws Exception {
        // Setup
        // Configure FooProvider.makeWriter(...).
        final Writer spyWriter = spy(new StringWriter());
        when(mockFooProvider.makeWriter()).thenReturn(spyWriter);

        // Run the test
        final Writer result = myClassUnderTest.makeWriter();

        // Verify the results
        verify(spyWriter).close();
    }

    @Test
    void testMakeWriter_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeWriter(...).
        final Writer spyWriter = spy(new Writer() {

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
        });
        when(mockFooProvider.makeWriter()).thenReturn(spyWriter);

        // Run the test
        final Writer result = myClassUnderTest.makeWriter();

        // Verify the results
        verify(spyWriter).close();
    }

    @Test
    void testMakeInputStreamThatThrows() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrows();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrows_FooProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(InputStream.nullInputStream());
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrows();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrows_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new InputStream() {
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
        });
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.makeInputStreamThatThrows());
        verify(spyInputStream).close();
    }

    @Test
    void testMakeCloseableFooThatThrows() throws Exception {
        // Setup
        // Configure FooProvider.makeCloseableFoo(...).
        final CloseableFoo spyCloseableFoo = spy(new CloseableFoo(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeCloseableFoo()).thenReturn(spyCloseableFoo);

        // Run the test
        final CloseableFoo result = myClassUnderTest.makeCloseableFooThatThrows();

        // Verify the results
        verify(spyCloseableFoo).close();
    }

    @Test
    void testMakeFooWithIsThatThrows() throws Exception {
        // Setup
        // Configure FooProvider.makeFooWithIs(...).
        final FooWithIs fooWithIs = new FooWithIs(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeFooWithIs()).thenReturn(fooWithIs);

        // Run the test
        final FooWithIs result = myClassUnderTest.makeFooWithIsThatThrows();

        // Verify the results
    }

    @Test
    void testMakeReaderThatThrows() throws Exception {
        // Setup
        // Configure FooProvider.makeReader(...).
        final Reader spyReader = spy(new StringReader("content"));
        when(mockFooProvider.makeReader()).thenReturn(spyReader);

        // Run the test
        final Reader result = myClassUnderTest.makeReaderThatThrows();

        // Verify the results
        verify(spyReader).close();
    }

    @Test
    void testMakeReaderThatThrows_FooProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure FooProvider.makeReader(...).
        final Reader spyReader = spy(Reader.nullReader());
        when(mockFooProvider.makeReader()).thenReturn(spyReader);

        // Run the test
        final Reader result = myClassUnderTest.makeReaderThatThrows();

        // Verify the results
        verify(spyReader).close();
    }

    @Test
    void testMakeReaderThatThrows_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeReader(...).
        final Reader spyReader = spy(new Reader() {

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
        });
        when(mockFooProvider.makeReader()).thenReturn(spyReader);

        // Run the test
        final Reader result = myClassUnderTest.makeReaderThatThrows();

        // Verify the results
        verify(spyReader).close();
    }

    @Test
    void testMakeOutputStreamThatThrows() throws Exception {
        // Setup
        // Configure FooProvider.makeOutputStream(...).
        final OutputStream spyOutputStream = spy(new ByteArrayOutputStream());
        when(mockFooProvider.makeOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        final OutputStream result = myClassUnderTest.makeOutputStreamThatThrows();

        // Verify the results
        verify(spyOutputStream).close();
    }

    @Test
    void testMakeOutputStreamThatThrows_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeOutputStream(...).
        final OutputStream spyOutputStream = spy(new OutputStream() {

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
        });
        when(mockFooProvider.makeOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.makeOutputStreamThatThrows());
        verify(spyOutputStream).close();
    }

    @Test
    void testMakeWriterThatThrows() throws Exception {
        // Setup
        // Configure FooProvider.makeWriter(...).
        final Writer spyWriter = spy(new StringWriter());
        when(mockFooProvider.makeWriter()).thenReturn(spyWriter);

        // Run the test
        final Writer result = myClassUnderTest.makeWriterThatThrows();

        // Verify the results
        verify(spyWriter).close();
    }

    @Test
    void testMakeWriterThatThrows_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeWriter(...).
        final Writer spyWriter = spy(new Writer() {

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
        });
        when(mockFooProvider.makeWriter()).thenReturn(spyWriter);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.makeWriterThatThrows());
        verify(spyWriter).close();
    }

    @Test
    void testMakeInputStreamThatThrowsMultiple() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrowsMultiple();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrowsMultiple_FooProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(InputStream.nullInputStream());
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrowsMultiple();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrowsMultiple_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new InputStream() {
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
        });
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.makeInputStreamThatThrowsMultiple());
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrowsMultipleNonIo() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrowsMultipleNonIo();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrowsMultipleNonIo_FooProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(InputStream.nullInputStream());
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrowsMultipleNonIo();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeInputStreamThatThrowsMultipleNonIo_FooProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooProvider.makeInputStream(...).
        final InputStream spyInputStream = spy(new InputStream() {
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
        });
        when(mockFooProvider.makeInputStream()).thenReturn(spyInputStream);

        // Run the test
        final InputStream result = myClassUnderTest.makeInputStreamThatThrowsMultipleNonIo();

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    void testMakeByteArrayInputStream() throws Exception {
        // Setup
        // Configure FooProvider.makeByteArrayInputStream(...).
        final ByteArrayInputStream spyByteArrayInputStream = spy(new ByteArrayInputStream("arrayContent".getBytes()));
        when(mockFooProvider.makeByteArrayInputStream()).thenReturn(spyByteArrayInputStream);

        // Run the test
        final ByteArrayInputStream result = myClassUnderTest.makeByteArrayInputStream();

        // Verify the results
        verify(spyByteArrayInputStream).close();
    }

    @Test
    void testMakeByteArrayOutputStream() throws Exception {
        // Setup
        // Configure FooProvider.makeByteArrayOutputStream(...).
        final ByteArrayOutputStream spyByteArrayOutputStream = spy(new ByteArrayOutputStream(16));
        when(mockFooProvider.makeByteArrayOutputStream()).thenReturn(spyByteArrayOutputStream);

        // Run the test
        final ByteArrayOutputStream result = myClassUnderTest.makeByteArrayOutputStream();

        // Verify the results
        verify(spyByteArrayOutputStream).close();
    }
}
