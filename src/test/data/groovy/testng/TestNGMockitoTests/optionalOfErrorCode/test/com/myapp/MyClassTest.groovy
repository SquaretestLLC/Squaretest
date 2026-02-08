package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testPutFoo1() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo1(fooData)

        // Verify the results
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo1_FooServiceReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId))

        // Run the test
        myClassUnderTest.putFoo1(fooData)
    }

    @Test
    void testPutFoo2() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty())

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo2(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo2_FooServicePutFoo1ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId))

        // Run the test
        myClassUnderTest.putFoo2(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo2_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty())

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo2(fooData)
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo3_ThrowsFooStoreException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo3(fooData)
    }

    @Test
    void testPutFoo3_FooServicePutFoo1ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId))

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo3(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo3_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId))

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo3(fooData)
    }

    @Test
    void testPutFoo4() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.putFoo4(fooData)

        // Verify the results
        // Confirm FooService.putFoo1(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockFooService).putFoo1(fooData1)
    }

    @Test
    void testPutFoo5() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.putFoo5(fooData)

        // Verify the results
        assert expectedResult == result

        // Confirm FooService.putFoo1(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        verify(mockFooService).putFoo1(fooData2)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo5_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo5(fooData)
    }

    @Test
    void testPutFoo6() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo2(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo6(fooData)

        // Verify the results
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo6_FooServiceReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo2(...).
        def errorInfo1 = new ErrorInfo()
        errorInfo1.setCode("code")
        errorInfo1.setDescription("description")
        def errorInfo = Optional.of(errorInfo1)
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo)

        // Run the test
        myClassUnderTest.putFoo6(fooData)
    }

    @Test
    void testPutFoo7() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo2(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty())

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo7(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo7_FooServicePutFoo2ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo2(...).
        def errorInfo1 = new ErrorInfo()
        errorInfo1.setCode("code")
        errorInfo1.setDescription("description")
        def errorInfo = Optional.of(errorInfo1)
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo)

        // Run the test
        myClassUnderTest.putFoo7(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo7_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo2(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty())

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo7(fooData)
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo8_ThrowsFooStoreException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo2(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo8(fooData)
    }

    @Test
    void testPutFoo8_FooServicePutFoo2ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo2(...).
        def errorInfo1 = new ErrorInfo()
        errorInfo1.setCode("code")
        errorInfo1.setDescription("description")
        def errorInfo = Optional.of(errorInfo1)
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo)

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo8(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo8_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo2(...).
        def errorInfo1 = new ErrorInfo()
        errorInfo1.setCode("code")
        errorInfo1.setDescription("description")
        def errorInfo = Optional.of(errorInfo1)
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo)

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo8(fooData)
    }

    @Test
    void testPutFoo9() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.putFoo9(fooData)

        // Verify the results
        // Confirm FooService.putFoo2(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockFooService).putFoo2(fooData1)
    }

    @Test
    void testPutFoo10() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.putFoo10(fooData)

        // Verify the results
        assert expectedResult == result

        // Confirm FooService.putFoo2(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        verify(mockFooService).putFoo2(fooData2)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo10_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo10(fooData)
    }

    @Test
    void testPutFoo11() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo11(fooData)

        // Verify the results
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo11_FooServiceReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound))

        // Run the test
        myClassUnderTest.putFoo11(fooData)
    }

    @Test
    void testPutFoo12() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty())

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo12(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo12_FooServicePutFoo3ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound))

        // Run the test
        myClassUnderTest.putFoo12(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo12_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty())

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo12(fooData)
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo13_ThrowsFooStoreException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo13(fooData)
    }

    @Test
    void testPutFoo13_FooServicePutFoo3ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound))

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo13(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo13_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound))

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo13(fooData)
    }

    @Test
    void testPutFoo14() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.putFoo14(fooData)

        // Verify the results
        // Confirm FooService.putFoo3(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockFooService).putFoo3(fooData1)
    }

    @Test
    void testPutFoo15() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.putFoo15(fooData)

        // Verify the results
        assert expectedResult == result

        // Confirm FooService.putFoo3(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        verify(mockFooService).putFoo3(fooData2)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo15_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo15(fooData)
    }

    @Test
    void testPutFoo16() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo4(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo16(fooData)

        // Verify the results
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo16_FooServiceReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo4(...).
        def errorInfo = new ErrorInfo()
        errorInfo.setCode("code")
        errorInfo.setDescription("description")
        def failureInfo = Optional.of(new FailureInfo([errorInfo]))
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo)

        // Run the test
        myClassUnderTest.putFoo16(fooData)
    }

    @Test
    void testPutFoo17() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo4(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty())

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo17(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo17_FooServicePutFoo4ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo4(...).
        def errorInfo = new ErrorInfo()
        errorInfo.setCode("code")
        errorInfo.setDescription("description")
        def failureInfo = Optional.of(new FailureInfo([errorInfo]))
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo)

        // Run the test
        myClassUnderTest.putFoo17(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo17_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo4(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty())

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo17(fooData)
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo18_ThrowsFooStoreException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo4(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo18(fooData)
    }

    @Test
    void testPutFoo18_FooServicePutFoo4ReturnsError() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo4(...).
        def errorInfo = new ErrorInfo()
        errorInfo.setCode("code")
        errorInfo.setDescription("description")
        def failureInfo = Optional.of(new FailureInfo([errorInfo]))
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo)

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo18(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo18_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo4(...).
        def errorInfo = new ErrorInfo()
        errorInfo.setCode("code")
        errorInfo.setDescription("description")
        def failureInfo = Optional.of(new FailureInfo([errorInfo]))
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo)

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo18(fooData)
    }

    @Test
    void testPutFoo19() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.putFoo19(fooData)

        // Verify the results
        // Confirm FooService.putFoo4(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockFooService).putFoo4(fooData1)
    }

    @Test
    void testPutFoo20() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.putFoo20(fooData)

        // Verify the results
        assert expectedResult == result

        // Confirm FooService.putFoo4(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        verify(mockFooService).putFoo4(fooData2)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo20_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo20(fooData)
    }

    @Test
    void testPutFoo21() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo21(fooData)

        // Verify the results
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo21_FooServiceReturnsFailure() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")))

        // Run the test
        myClassUnderTest.putFoo21(fooData)
    }

    @Test
    void testPutFoo22() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty())

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo22(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo22_FooServicePutFoo5ReturnsFailure() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")))

        // Run the test
        myClassUnderTest.putFoo22(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo22_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty())

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo22(fooData)
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo23_ThrowsFooStoreException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo23(fooData)
    }

    @Test
    void testPutFoo23_FooServicePutFoo5ReturnsFailure() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")))

        // Configure FooService.getFoo(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.putFoo23(fooData)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo23_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")))

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo23(fooData)
    }

    @Test
    void testPutFoo24() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Run the test
        myClassUnderTest.putFoo24(fooData)

        // Verify the results
        // Confirm FooService.putFoo5(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        verify(mockFooService).putFoo5(fooData1)
    }

    @Test
    void testPutFoo25() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        def expectedResult = new FooData()
        expectedResult.setId("id")
        expectedResult.setName("name")

        // Configure FooService.getFoo(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.getFoo("id")).thenReturn(fooData1)

        // Run the test
        def result = myClassUnderTest.putFoo25(fooData)

        // Verify the results
        assert expectedResult == result

        // Confirm FooService.putFoo5(...).
        def fooData2 = new FooData()
        fooData2.setId("id")
        fooData2.setName("name")
        verify(mockFooService).putFoo5(fooData2)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testPutFoo25_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.putFoo25(fooData)
    }

    @Test
    void testPutFoo26() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo6(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo6(fooData1)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.putFoo26(fooData)

        // Verify the results
    }

    @Test(expectedExceptions = [FooStoreException.class])
    void testPutFoo26_FooServiceReturnsFailure() {
        // Setup
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")

        // Configure FooService.putFoo6(...).
        def fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(mockFooService.putFoo6(fooData1)).thenReturn(Optional.of(new Exception("message")))

        // Run the test
        myClassUnderTest.putFoo26(fooData)
    }
}
