package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.concurrent.CompletableFuture

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.*

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private FooService mockMainFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockMainFooService)
    }

    @Test
    void testStoreFooData1() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.storeFooData1(fooData)

        // Verify the results
        // Confirm FooService.storeFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockMainFooService).storeFoo1(fooData1)
    }

    @Test
    void testStoreFooData1_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFoo1(fooData1)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData1(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData2() {
        // Setup
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooData = [fooData1]

        // Run the test
        myClassUnderTest.storeFooData2(fooData)

        // Verify the results
        // Confirm FooService.storeFoo2(...).
        def fooData3 = new FooData()
        fooData3.setId("id")
        fooData3.setName("name")
        def fooData2 = [fooData3]
        verify(mockMainFooService).storeFoo2(fooData2)
    }

    @Test
    void testStoreFooData2_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooData = [fooData1]

        // Configure FooService.storeFoo2(...).
        def fooData3 = new FooData()
        fooData3.setId("id")
        fooData3.setName("name")
        def fooData2 = [fooData3]
        when(mockMainFooService.storeFoo2(fooData2)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData2(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData3() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.storeFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockMainFooService.storeFoo1(fooData2)).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.storeFooData3(fooData)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testStoreFooData3_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFoo1(fooData1)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData3(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData4() {
        // Setup
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooData = [fooData1]
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        def expectedResult = [fooData2]

        // Configure FooService.storeFoo2(...).
        def fooData4 = new FooData()
        fooData4.setId("id")
        fooData4.setName("name")
        def fooData3 = [fooData4]
        def fooData6 = new FooData()
        fooData6.setId("id")
        fooData6.setName("name")
        def fooData5 = [fooData6]
        when(mockMainFooService.storeFoo2(fooData5)).thenReturn(fooData3)

        // Run the test
        def result = myClassUnderTest.storeFooData4(fooData)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testStoreFooData4_FooServiceReturnsNoItems() {
        // Setup
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooData = [fooData1]

        // Configure FooService.storeFoo2(...).
        def fooData3 = new FooData()
        fooData3.setId("id")
        fooData3.setName("name")
        def fooData2 = [fooData3]
        when(mockMainFooService.storeFoo2(fooData2)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.storeFooData4(fooData)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testStoreFooData4_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooData = [fooData1]

        // Configure FooService.storeFoo2(...).
        def fooData3 = new FooData()
        fooData3.setId("id")
        fooData3.setName("name")
        def fooData2 = [fooData3]
        when(mockMainFooService.storeFoo2(fooData2)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData4(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData5() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.storeFooData5(fooData)

        // Verify the results
        // Confirm FooService.storeFooDataOpt(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockMainFooService).storeFooDataOpt(fooData1)
    }

    @Test
    void testStoreFooData5_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataOpt(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFooDataOpt(fooData1)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData5(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData6() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def expectedResult = Optional.of(fooData1)

        // Configure FooService.storeFooDataOpt(...).
        def fooData3 = new FooData()
        fooData3.setId("id")
        fooData3.setName("name")
        def fooData2 = Optional.of(fooData3)
        def fooData4 = new FooData()
        fooData4.setId("id")
        fooData4.setName("name")
        when(mockMainFooService.storeFooDataOpt(fooData4)).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.storeFooData6(fooData)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testStoreFooData6_FooServiceReturnsAbsent() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataOpt(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFooDataOpt(fooData1)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.storeFooData6(fooData)

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testStoreFooData6_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataOpt(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFooDataOpt(fooData1)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData6(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData7() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.storeFooData7(fooData)

        // Verify the results
        // Confirm FooService.storeFooDataFuture(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockMainFooService).storeFooDataFuture(fooData1)
    }

    @Test
    void testStoreFooData7_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataFuture(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFooDataFuture(fooData1)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData7(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData8() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataFuture(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        def fooDataCompletableFuture = CompletableFuture.completedFuture(fooData1)
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockMainFooService.storeFooDataFuture(fooData2)).thenReturn(fooDataCompletableFuture)

        // Run the test
        def result = myClassUnderTest.storeFooData8(fooData)

        // Verify the results
    }

    @Test
    void testStoreFooData8_FooServiceReturnsFailure() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataFuture(...).
        def fooDataCompletableFuture = new CompletableFuture<>()
        fooDataCompletableFuture.completeExceptionally(new Exception("message"))
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFooDataFuture(fooData1)).thenReturn(fooDataCompletableFuture)

        // Run the test
        def result = myClassUnderTest.storeFooData8(fooData)

        // Verify the results
    }

    @Test
    void testStoreFooData8_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataFuture(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockMainFooService.storeFooDataFuture(fooData1)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData8(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData9() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream("content".getBytes()))
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenReturn(spyInputStream)

        // Run the test
        myClassUnderTest.storeFooData9(fooData)

        // Verify the results
        verify(spyInputStream).close()
    }

    @Test
    void testStoreFooData9_FooServiceReturnsNoContent() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream(new byte[]{}))
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenReturn(spyInputStream)

        // Run the test
        myClassUnderTest.storeFooData9(fooData)

        // Verify the results
        verify(spyInputStream).close()
    }

    @Test
    void testStoreFooData9_FooServiceReturnsBrokenIo() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
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
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenReturn(spyInputStream)

        // Run the test
        myClassUnderTest.storeFooData9(fooData)

        // Verify the results
        verify(spyInputStream).close()
    }

    @Test
    void testStoreFooData9_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData9(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFooData10() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream("content".getBytes()))
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenReturn(spyInputStream)

        // Run the test
        def result = myClassUnderTest.storeFooData10(fooData)

        // Verify the results
        assertThat(result).isEqualTo("result")
        verify(spyInputStream).close()
    }

    @Test
    void testStoreFooData10_FooServiceReturnsNoContent() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
        def spyInputStream = spy(new ByteArrayInputStream(new byte[]{}))
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenReturn(spyInputStream)

        // Run the test
        def result = myClassUnderTest.storeFooData10(fooData)

        // Verify the results
        assertThat(result).isEqualTo("result")
        verify(spyInputStream).close()
    }

    @Test
    void testStoreFooData10_FooServiceReturnsBrokenIo() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
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
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenReturn(spyInputStream)

        // Run the test
        def result = myClassUnderTest.storeFooData10(fooData)

        // Verify the results
        assertThat(result).isEqualTo("result")
        verify(spyInputStream).close()
    }

    @Test
    void testStoreFooData10_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.storeFooDataStream(...).
        def id = new FooData()
        id.setId("id")
        id.setName("name")
        when(mockMainFooService.storeFooDataStream(id)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFooData10(fooData)
        }).isInstanceOf(FooServiceException.class)
    }
}
