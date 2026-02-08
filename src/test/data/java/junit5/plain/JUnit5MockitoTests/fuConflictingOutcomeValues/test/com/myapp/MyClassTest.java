package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private FooSerializer mockFooSerializer;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockFooSerializer);
    }

    @Test
    void testExists() throws Exception {
        // Setup
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final Optional<Boolean> result = myClassUnderTest.exists("searchCriteria");

        // Verify the results
        assertEquals(Optional.of(false), result);
    }

    @Test
    void testExists_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final Optional<Boolean> result = myClassUnderTest.exists("searchCriteria");

        // Verify the results
        assertEquals(Optional.of(false), result);
    }

    @Test
    void testExists_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFoos1("searchCriteria")).thenThrow(FooServiceException.class);

        // Run the test
        final Optional<Boolean> result = myClassUnderTest.exists("searchCriteria");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFoos1() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos1("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos1("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos1_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;
        when(mockFooService.getFoos1("searchCriteria")).thenThrow(FooServiceException.class);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos1("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos2() {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos2("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos3() {
        // Setup
        final MyList<FooData> expectedResult = null;
        when(mockFooService.getFoo1("fooId")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos3("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos3_FooServiceReturnsAbsent() {
        // Setup
        final MyList<FooData> expectedResult = null;
        when(mockFooService.getFoo1("fooId")).thenReturn(Optional.empty());

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos3("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos4() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        when(mockFooSerializer.readFoos(any(InputStream.class))).thenReturn(null);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos4("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoContent() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream(new byte[]{}));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        when(mockFooSerializer.readFoos(any(InputStream.class))).thenReturn(null);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos4("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos4_FooServiceReturnsBrokenIo() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
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
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos4("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos4_FooSerializerThrowsIOException() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        when(mockFooSerializer.readFoos(any(InputStream.class))).thenThrow(IOException.class);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos4("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos5() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos5("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos5_FooServiceReturnsNoContent() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream(new byte[]{}));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos5("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos5_FooServiceReturnsBrokenIo() throws Exception {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoos2(...).
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
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(spyInputStream);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos5("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFoos6() {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoosAsync1(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Arrays.asList(new FooData("id", "name")));
        when(mockFooService.getFoosAsync1("searchCriteria")).thenReturn(listCompletableFuture);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos6("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos6_FooServiceReturnsNoItems() {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoosAsync1(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = CompletableFuture.completedFuture(
                Collections.emptyList());
        when(mockFooService.getFoosAsync1("searchCriteria")).thenReturn(listCompletableFuture);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos6("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos6_FooServiceReturnsFailure() {
        // Setup
        final MyList<FooData> expectedResult = null;

        // Configure FooService.getFoosAsync1(...).
        final CompletableFuture<List<FooData>> listCompletableFuture = new CompletableFuture<>();
        listCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFooService.getFoosAsync1("searchCriteria")).thenReturn(listCompletableFuture);

        // Run the test
        final MyList<FooData> result = myClassUnderTest.getFoos6("searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
