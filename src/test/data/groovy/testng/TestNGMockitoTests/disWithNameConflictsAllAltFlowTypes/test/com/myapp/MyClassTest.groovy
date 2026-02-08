package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.concurrent.CompletableFuture

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockMainFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockMainFooService)
    }

    @Test
    void testGetFooData1() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData1_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData1("id")
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData1_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData1("id")
    }

    @Test
    void testGetFooData2() {
        // Setup
        def expectedResult = Optional.of(new FooData("id", "name"))
        when(mockMainFooService.getFooDataOpt(FooType.Normal)).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockMainFooService.getFooDataOpt("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooData2_FooServiceGetFooDataOpt1ReturnsAbsent() {
        // Setup
        when(mockMainFooService.getFooDataOpt(FooType.Normal)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooData2_FooServiceGetFooDataOpt2ReturnsAbsent() {
        // Setup
        when(mockMainFooService.getFooDataOpt("id")).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooData3() {
        // Setup
        def expectedResult = [new FooData("id", "name")]
        when(mockMainFooService.getFooDataList(FooType.Normal)).thenReturn([new FooData("id", "name")])
        when(mockMainFooService.getFooDataList("id")).thenReturn([new FooData("id", "name")])

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooData3_FooServiceGetFooDataList1ReturnsNoItems() {
        // Setup
        when(mockMainFooService.getFooDataList(FooType.Normal)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFooData3_FooServiceGetFooDataList2ReturnsNoItems() {
        // Setup
        when(mockMainFooService.getFooDataList("id")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFooData4() {
        // Setup
        // Configure FooService.getFooDataFuture(...).
        def fooDataCompletableFuture = CompletableFuture.completedFuture(new FooData("id", "name"))
        when(mockMainFooService.getFooDataFuture(FooType.Normal)).thenReturn(fooDataCompletableFuture)

        // Configure FooService.getFooDataFuture(...).
        def fooDataCompletableFuture1 = CompletableFuture.completedFuture(new FooData("id", "name"))
        when(mockMainFooService.getFooDataFuture("id")).thenReturn(fooDataCompletableFuture1)

        // Run the test
        def result = myClassUnderTest.getFooData4("id")

        // Verify the results
    }

    @Test
    void testGetFooData4_FooServiceGetFooDataFuture1ReturnsFailure() {
        // Setup
        // Configure FooService.getFooDataFuture(...).
        def fooDataCompletableFuture = new CompletableFuture<>()
        fooDataCompletableFuture.completeExceptionally(new Exception("message"))
        when(mockMainFooService.getFooDataFuture(FooType.Normal)).thenReturn(fooDataCompletableFuture)

        // Run the test
        def result = myClassUnderTest.getFooData4("id")

        // Verify the results
    }

    @Test
    void testGetFooData4_FooServiceGetFooDataFuture2ReturnsFailure() {
        // Setup
        // Configure FooService.getFooDataFuture(...).
        def fooDataCompletableFuture = new CompletableFuture<>()
        fooDataCompletableFuture.completeExceptionally(new Exception("message"))
        when(mockMainFooService.getFooDataFuture("id")).thenReturn(fooDataCompletableFuture)

        // Run the test
        def result = myClassUnderTest.getFooData4("id")

        // Verify the results
    }

    @Test
    void testGetFooData() {
        // Setup
        // Configure FooService.getFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream("content".getBytes()))
        when(mockMainFooService.getFooDataStream(FooType.Normal)).thenReturn(spyInputStream)

        // Configure FooService.getFooDataStream(...).
        def spyInputStream1 = spy(new ByteArrayInputStream("content".getBytes()))
        when(mockMainFooService.getFooDataStream("id")).thenReturn(spyInputStream1)

        // Run the test
        def result = myClassUnderTest.getFooData("id")

        // Verify the results
        assert "result" == result
        verify(spyInputStream).close()
        verify(spyInputStream1).close()
    }

    @Test
    void testGetFooData_FooServiceGetFooDataStream1ReturnsNoContent() {
        // Setup
        // Configure FooService.getFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream(new byte[]{}))
        when(mockMainFooService.getFooDataStream(FooType.Normal)).thenReturn(spyInputStream)

        // Run the test
        def result = myClassUnderTest.getFooData("id")

        // Verify the results
        assert "result" == result
        verify(spyInputStream).close()
    }

    @Test(expectedExceptions = [IOException.class])
    void testGetFooData_FooServiceGetFooDataStream1ReturnsBrokenIo() {
        // Setup
        // Configure FooService.getFooDataStream(...).
        def spyInputStream = spy(new InputStream() {
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
        })
        when(mockMainFooService.getFooDataStream(FooType.Normal)).thenReturn(spyInputStream)

        // Run the test
        myClassUnderTest.getFooData("id")
    }

    @Test
    void testGetFooData_FooServiceGetFooDataStream2ReturnsNoContent() {
        // Setup
        // Configure FooService.getFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream(new byte[]{}))
        when(mockMainFooService.getFooDataStream("id")).thenReturn(spyInputStream)

        // Run the test
        def result = myClassUnderTest.getFooData("id")

        // Verify the results
        assert "result" == result
        verify(spyInputStream).close()
    }

    @Test(expectedExceptions = [IOException.class])
    void testGetFooData_FooServiceGetFooDataStream2ReturnsBrokenIo() {
        // Setup
        // Configure FooService.getFooDataStream(...).
        def spyInputStream = spy(new InputStream() {
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
        })
        when(mockMainFooService.getFooDataStream("id")).thenReturn(spyInputStream)

        // Run the test
        myClassUnderTest.getFooData("id")
    }
}
