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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockMainFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMainFooService);
    }

    @Test
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"));
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooData1("id")).isInstanceOf(FooServiceException.class);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooData1("id")).isInstanceOf(FooServiceException.class);
    }

    @Test
    void testGetFooData2() {
        // Setup
        final Optional<FooData> expectedResult = Optional.of(new FooData("id", "name"));
        when(mockMainFooService.getFooDataOpt(FooType.Normal)).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockMainFooService.getFooDataOpt("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final Optional<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooData2_FooServiceGetFooDataOpt1ReturnsAbsent() {
        // Setup
        when(mockMainFooService.getFooDataOpt(FooType.Normal)).thenReturn(Optional.empty());

        // Run the test
        final Optional<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testGetFooData2_FooServiceGetFooDataOpt2ReturnsAbsent() {
        // Setup
        when(mockMainFooService.getFooDataOpt("id")).thenReturn(Optional.empty());

        // Run the test
        final Optional<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testGetFooData3() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockMainFooService.getFooDataList(FooType.Normal)).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockMainFooService.getFooDataList("id")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooData3_FooServiceGetFooDataList1ReturnsNoItems() {
        // Setup
        when(mockMainFooService.getFooDataList(FooType.Normal)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetFooData3_FooServiceGetFooDataList2ReturnsNoItems() {
        // Setup
        when(mockMainFooService.getFooDataList("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetFooData4() {
        // Setup
        // Configure FooService.getFooDataFuture(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(
                new FooData("id", "name"));
        when(mockMainFooService.getFooDataFuture(FooType.Normal)).thenReturn(fooDataCompletableFuture);

        // Configure FooService.getFooDataFuture(...).
        final CompletableFuture<FooData> fooDataCompletableFuture1 = CompletableFuture.completedFuture(
                new FooData("id", "name"));
        when(mockMainFooService.getFooDataFuture("id")).thenReturn(fooDataCompletableFuture1);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooData4("id");

        // Verify the results
    }

    @Test
    void testGetFooData4_FooServiceGetFooDataFuture1ReturnsFailure() {
        // Setup
        // Configure FooService.getFooDataFuture(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockMainFooService.getFooDataFuture(FooType.Normal)).thenReturn(fooDataCompletableFuture);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooData4("id");

        // Verify the results
    }

    @Test
    void testGetFooData4_FooServiceGetFooDataFuture2ReturnsFailure() {
        // Setup
        // Configure FooService.getFooDataFuture(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockMainFooService.getFooDataFuture("id")).thenReturn(fooDataCompletableFuture);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.getFooData4("id");

        // Verify the results
    }

    @Test
    void testGetFooData() throws Exception {
        // Setup
        // Configure FooService.getFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockMainFooService.getFooDataStream(FooType.Normal)).thenReturn(spyInputStream);

        // Configure FooService.getFooDataStream(...).
        final InputStream spyInputStream1 = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockMainFooService.getFooDataStream("id")).thenReturn(spyInputStream1);

        // Run the test
        final String result = myClassUnderTest.getFooData("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(spyInputStream).close();
        verify(spyInputStream1).close();
    }

    @Test
    void testGetFooData_FooServiceGetFooDataStream1ReturnsNoContent() throws Exception {
        // Setup
        // Configure FooService.getFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream(new byte[]{}));
        when(mockMainFooService.getFooDataStream(FooType.Normal)).thenReturn(spyInputStream);

        // Run the test
        final String result = myClassUnderTest.getFooData("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(spyInputStream).close();
    }

    @Test
    void testGetFooData_FooServiceGetFooDataStream1ReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooService.getFooDataStream(...).
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
        when(mockMainFooService.getFooDataStream(FooType.Normal)).thenReturn(spyInputStream);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooData("id")).isInstanceOf(IOException.class);
        verify(spyInputStream).close();
    }

    @Test
    void testGetFooData_FooServiceGetFooDataStream2ReturnsNoContent() throws Exception {
        // Setup
        // Configure FooService.getFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream(new byte[]{}));
        when(mockMainFooService.getFooDataStream("id")).thenReturn(spyInputStream);

        // Run the test
        final String result = myClassUnderTest.getFooData("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(spyInputStream).close();
    }

    @Test
    void testGetFooData_FooServiceGetFooDataStream2ReturnsBrokenIo() throws Exception {
        // Setup
        // Configure FooService.getFooDataStream(...).
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
        when(mockMainFooService.getFooDataStream("id")).thenReturn(spyInputStream);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooData("id")).isInstanceOf(IOException.class);
        verify(spyInputStream).close();
    }
}
