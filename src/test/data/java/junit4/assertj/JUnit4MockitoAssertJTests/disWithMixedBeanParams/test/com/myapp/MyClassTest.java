package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockMainFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockMainFooService);
    }

    @Test
    public void testStoreFooData1() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.storeFooData1(fooData);

        // Verify the results
        // Confirm FooService.storeFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockMainFooService).storeFoo1(any(SearchParams.class), eq(fooData1));
    }

    @Test
    public void testStoreFooData1_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFoo1(any(SearchParams.class), eq(fooData1))).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData1(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData2() {
        // Setup
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final List<FooData> fooData = Arrays.asList(fooData1);

        // Run the test
        myClassUnderTest.storeFooData2(fooData);

        // Verify the results
        // Confirm FooService.storeFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        final List<FooData> fooData2 = Arrays.asList(fooData3);
        verify(mockMainFooService).storeFoo2(any(SearchParams.class), eq(fooData2));
    }

    @Test
    public void testStoreFooData2_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final List<FooData> fooData = Arrays.asList(fooData1);

        // Configure FooService.storeFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        final List<FooData> fooData2 = Arrays.asList(fooData3);
        when(mockMainFooService.storeFoo2(any(SearchParams.class), eq(fooData2))).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData2(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData3() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.storeFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockMainFooService.storeFoo1(any(SearchParams.class), eq(fooData2))).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.storeFooData3(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testStoreFooData3_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFoo1(any(SearchParams.class), eq(fooData1))).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData3(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData4() {
        // Setup
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final List<FooData> fooData = Arrays.asList(fooData1);
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final List<FooData> expectedResult = Arrays.asList(fooData2);

        // Configure FooService.storeFoo2(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        fooData4.setName("name");
        final List<FooData> fooData3 = Arrays.asList(fooData4);
        final FooData fooData6 = new FooData();
        fooData6.setId("id");
        fooData6.setName("name");
        final List<FooData> fooData5 = Arrays.asList(fooData6);
        when(mockMainFooService.storeFoo2(any(SearchParams.class), eq(fooData5))).thenReturn(fooData3);

        // Run the test
        final List<FooData> result = myClassUnderTest.storeFooData4(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testStoreFooData4_FooServiceReturnsNoItems() {
        // Setup
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final List<FooData> fooData = Arrays.asList(fooData1);

        // Configure FooService.storeFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        final List<FooData> fooData2 = Arrays.asList(fooData3);
        when(mockMainFooService.storeFoo2(any(SearchParams.class), eq(fooData2))).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.storeFooData4(fooData);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testStoreFooData4_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final List<FooData> fooData = Arrays.asList(fooData1);

        // Configure FooService.storeFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        final List<FooData> fooData2 = Arrays.asList(fooData3);
        when(mockMainFooService.storeFoo2(any(SearchParams.class), eq(fooData2))).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData4(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData5() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.storeFooData5(fooData);

        // Verify the results
        // Confirm FooService.storeFooDataOpt(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockMainFooService).storeFooDataOpt(any(SearchParams.class), eq(fooData1));
    }

    @Test
    public void testStoreFooData5_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataOpt(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFooDataOpt(any(SearchParams.class), eq(fooData1)))
                .thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData5(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData6() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final Optional<FooData> expectedResult = Optional.of(fooData1);

        // Configure FooService.storeFooDataOpt(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        final Optional<FooData> fooData2 = Optional.of(fooData3);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        fooData4.setName("name");
        when(mockMainFooService.storeFooDataOpt(any(SearchParams.class), eq(fooData4))).thenReturn(fooData2);

        // Run the test
        final Optional<FooData> result = myClassUnderTest.storeFooData6(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testStoreFooData6_FooServiceReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataOpt(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFooDataOpt(any(SearchParams.class), eq(fooData1))).thenReturn(Optional.empty());

        // Run the test
        final Optional<FooData> result = myClassUnderTest.storeFooData6(fooData);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testStoreFooData6_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataOpt(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFooDataOpt(any(SearchParams.class), eq(fooData1)))
                .thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData6(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData7() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.storeFooData7(fooData);

        // Verify the results
        // Confirm FooService.storeFooDataFuture(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockMainFooService).storeFooDataFuture(any(SearchParams.class), eq(fooData1));
    }

    @Test
    public void testStoreFooData7_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataFuture(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFooDataFuture(any(SearchParams.class), eq(fooData1)))
                .thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData7(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData8() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataFuture(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final CompletableFuture<FooData> fooDataCompletableFuture = CompletableFuture.completedFuture(fooData1);
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockMainFooService.storeFooDataFuture(any(SearchParams.class), eq(fooData2)))
                .thenReturn(fooDataCompletableFuture);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.storeFooData8(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFooData8_FooServiceReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataFuture(...).
        final CompletableFuture<FooData> fooDataCompletableFuture = new CompletableFuture<>();
        fooDataCompletableFuture.completeExceptionally(new Exception("message"));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFooDataFuture(any(SearchParams.class), eq(fooData1)))
                .thenReturn(fooDataCompletableFuture);

        // Run the test
        final CompletableFuture<FooData> result = myClassUnderTest.storeFooData8(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFooData8_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataFuture(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockMainFooService.storeFooDataFuture(any(SearchParams.class), eq(fooData1)))
                .thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData8(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData9() throws Exception {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id))).thenReturn(spyInputStream);

        // Run the test
        myClassUnderTest.storeFooData9(fooData);

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    public void testStoreFooData9_FooServiceReturnsNoContent() throws Exception {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream(new byte[]{}));
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id))).thenReturn(spyInputStream);

        // Run the test
        myClassUnderTest.storeFooData9(fooData);

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    public void testStoreFooData9_FooServiceReturnsBrokenIo() throws Exception {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
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
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id))).thenReturn(spyInputStream);

        // Run the test
        myClassUnderTest.storeFooData9(fooData);

        // Verify the results
        verify(spyInputStream).close();
    }

    @Test
    public void testStoreFooData9_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id)))
                .thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData9(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFooData10() throws Exception {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id))).thenReturn(spyInputStream);

        // Run the test
        final String result = myClassUnderTest.storeFooData10(fooData);

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(spyInputStream).close();
    }

    @Test
    public void testStoreFooData10_FooServiceReturnsNoContent() throws Exception {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream(new byte[]{}));
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id))).thenReturn(spyInputStream);

        // Run the test
        final String result = myClassUnderTest.storeFooData10(fooData);

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(spyInputStream).close();
    }

    @Test
    public void testStoreFooData10_FooServiceReturnsBrokenIo() throws Exception {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
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
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id))).thenReturn(spyInputStream);

        // Run the test
        final String result = myClassUnderTest.storeFooData10(fooData);

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(spyInputStream).close();
    }

    @Test
    public void testStoreFooData10_FooServiceThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.storeFooDataStream(...).
        final FooData id = new FooData();
        id.setId("id");
        id.setName("name");
        when(mockMainFooService.storeFooDataStream(any(SearchParams.class), eq(id)))
                .thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFooData10(fooData)).isInstanceOf(FooServiceException.class);
    }
}
