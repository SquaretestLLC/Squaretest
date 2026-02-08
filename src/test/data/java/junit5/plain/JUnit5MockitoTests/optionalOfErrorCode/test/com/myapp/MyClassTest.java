package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testPutFoo1() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo1(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo1_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo1(fooData));
    }

    @Test
    void testPutFoo2() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo2(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo2_FooServicePutFoo1ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo2(fooData));
    }

    @Test
    void testPutFoo2_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo2(fooData));
    }

    @Test
    void testPutFoo3_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo3(fooData));
    }

    @Test
    void testPutFoo3_FooServicePutFoo1ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo3(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo3_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo3(fooData));
    }

    @Test
    void testPutFoo4() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo4(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo1(fooData1);
    }

    @Test
    void testPutFoo5() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo5(fooData);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo1(fooData2);
    }

    @Test
    void testPutFoo5_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo5(fooData));

        // Confirm FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo1(fooData1);
    }

    @Test
    void testPutFoo6() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo6(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo6_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo6(fooData));
    }

    @Test
    void testPutFoo7() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo7(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo7_FooServicePutFoo2ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo7(fooData));
    }

    @Test
    void testPutFoo7_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo7(fooData));
    }

    @Test
    void testPutFoo8_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo8(fooData));
    }

    @Test
    void testPutFoo8_FooServicePutFoo2ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo8(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo8_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo8(fooData));
    }

    @Test
    void testPutFoo9() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo9(fooData);

        // Verify the results
        // Confirm FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo2(fooData1);
    }

    @Test
    void testPutFoo10() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo10(fooData);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm FooService.putFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo2(fooData2);
    }

    @Test
    void testPutFoo10_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo10(fooData));

        // Confirm FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo2(fooData1);
    }

    @Test
    void testPutFoo11() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo11(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo11_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo11(fooData));
    }

    @Test
    void testPutFoo12() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo12(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo12_FooServicePutFoo3ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo12(fooData));
    }

    @Test
    void testPutFoo12_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo12(fooData));
    }

    @Test
    void testPutFoo13_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo13(fooData));
    }

    @Test
    void testPutFoo13_FooServicePutFoo3ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo13(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo13_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo13(fooData));
    }

    @Test
    void testPutFoo14() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo14(fooData);

        // Verify the results
        // Confirm FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo3(fooData1);
    }

    @Test
    void testPutFoo15() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo15(fooData);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm FooService.putFoo3(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo3(fooData2);
    }

    @Test
    void testPutFoo15_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo15(fooData));

        // Confirm FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo3(fooData1);
    }

    @Test
    void testPutFoo16() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo16(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo16_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo16(fooData));
    }

    @Test
    void testPutFoo17() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo17(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo17_FooServicePutFoo4ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo17(fooData));
    }

    @Test
    void testPutFoo17_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo17(fooData));
    }

    @Test
    void testPutFoo18_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo18(fooData));
    }

    @Test
    void testPutFoo18_FooServicePutFoo4ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo18(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo18_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo18(fooData));
    }

    @Test
    void testPutFoo19() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo19(fooData);

        // Verify the results
        // Confirm FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo4(fooData1);
    }

    @Test
    void testPutFoo20() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo20(fooData);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm FooService.putFoo4(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo4(fooData2);
    }

    @Test
    void testPutFoo20_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo20(fooData));

        // Confirm FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo4(fooData1);
    }

    @Test
    void testPutFoo21() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo21(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo21_FooServiceReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo21(fooData));
    }

    @Test
    void testPutFoo22() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo22(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo22_FooServicePutFoo5ReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo22(fooData));
    }

    @Test
    void testPutFoo22_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo22(fooData));
    }

    @Test
    void testPutFoo23_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo23(fooData));
    }

    @Test
    void testPutFoo23_FooServicePutFoo5ReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo23(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo23_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo23(fooData));
    }

    @Test
    void testPutFoo24() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo24(fooData);

        // Verify the results
        // Confirm FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo5(fooData1);
    }

    @Test
    void testPutFoo25() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo25(fooData);

        // Verify the results
        assertEquals(expectedResult, result);

        // Confirm FooService.putFoo5(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo5(fooData2);
    }

    @Test
    void testPutFoo25_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo25(fooData));

        // Confirm FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo5(fooData1);
    }

    @Test
    void testPutFoo26() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo6(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo6(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo26(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo26_FooServiceReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo6(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo6(fooData1)).thenReturn(Optional.of(new Exception("message")));

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.putFoo26(fooData));
    }
}
