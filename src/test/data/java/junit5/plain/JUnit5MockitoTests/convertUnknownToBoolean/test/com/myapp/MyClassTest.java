package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.putFoo1(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        verify(mockFooService).putFoo1(fooData2);
    }

    @Test
    void testPutFoo1_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData2);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo1(fooData));
    }

    @Test
    void testPutFoo2() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.putFoo2(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        verify(mockFooService).putFoo1(fooData2);
    }

    @Test
    void testPutFoo2_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData1);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo2(fooData));
    }

    @Test
    void testPutFoo3() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.putFoo3(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo3_FooServiceGetFoo1ReturnsNull() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        myClassUnderTest.putFoo3(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        verify(mockFooService).putFoo1(fooData1);
    }

    @Test
    void testPutFoo3_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData1);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo3(fooData));
    }

    @Test
    void testPutFoo4() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.putFoo4(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        verify(mockFooService).putFoo1(fooData2);
    }

    @Test
    void testPutFoo4_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData2);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo4(fooData));
    }

    @Test
    void testPutFoo5() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.putFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        when(mockFooService.putFoo2(fooData3)).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo5(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo5_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.putFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.putFoo2(fooData2)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo5(fooData));
    }

    @Test
    void testPutFoo6() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.putFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        when(mockFooService.putFoo2(fooData3)).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo6(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo6_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.putFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.putFoo2(fooData2)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo6(fooData));
    }

    @Test
    void testPutFoo7() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData2.setSubData(subData1);
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData3.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.putFoo7(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo7_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getCachedData1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getCachedData1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData2);

        // Run the test
        myClassUnderTest.putFoo7(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        verify(mockFooService).putFoo1(fooData3);
    }

    @Test
    void testPutFoo7_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getCachedData1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getCachedData1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData2);

        // Configure FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData3);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo7(fooData));
    }

    @Test
    void testPutFoo8() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo8(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo8_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        when(mockFooService.getFoo1("id")).thenReturn(fooData2);

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData5 = new SubData();
        subData5.setId("id");
        final OtherData otherData5 = new OtherData();
        otherData5.setId("id");
        subData5.setOtherData(otherData5);
        fooData4.setSubData(subData5);
        when(mockFooService.putFoo2(fooData4)).thenReturn(fooData3);

        // Run the test
        final FooData result = myClassUnderTest.putFoo8(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo8_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getCachedData1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getCachedData1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData2);

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.putFoo2(fooData3)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo8(fooData));
    }

    @Test
    void testPutFoo9() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo9(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo9_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        when(mockFooService.getFoo1("id")).thenReturn(fooData2);

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData5 = new SubData();
        subData5.setId("id");
        final OtherData otherData5 = new OtherData();
        otherData5.setId("id");
        subData5.setOtherData(otherData5);
        fooData4.setSubData(subData5);
        when(mockFooService.putFoo2(fooData4)).thenReturn(fooData3);

        // Run the test
        final FooData result = myClassUnderTest.putFoo9(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo9_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getCachedData1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getCachedData1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData2);

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.putFoo2(fooData3)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo9(fooData));
    }

    @Test
    void testPutFoo10() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData3.setSubData(subData2);
        final Optional<FooData> fooData2 = Optional.of(fooData3);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData4)).thenReturn(fooData2);

        // Run the test
        myClassUnderTest.putFoo10(fooData);

        // Verify the results
    }

    @Test
    void testPutFoo10_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo10(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        verify(mockFooService).putFoo1(fooData3);
    }

    @Test
    void testPutFoo10_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData3);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo10(fooData));
    }

    @Test
    void testPutFoo11_ThrowsCachedFooAlreadyExists() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData3.setSubData(subData2);
        final Optional<FooData> fooData2 = Optional.of(fooData3);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData4)).thenReturn(fooData2);

        // Run the test
        assertThrows(CachedFooAlreadyExists.class, () -> myClassUnderTest.putFoo11(fooData));
    }

    @Test
    void testPutFoo11_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo11(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        verify(mockFooService).putFoo1(fooData3);
    }

    @Test
    void testPutFoo11_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData3);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo11(fooData));
    }

    @Test
    void testPutFoo12_ThrowsCachedFooAlreadyExists() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo2("id")).thenReturn(fooData2);

        // Configure FooService.getCachedData1(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        final Optional<FooData> fooData3 = Optional.of(fooData4);
        final FooData fooData5 = new FooData();
        fooData5.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData5.setSubData(subData4);
        when(mockFooService.getCachedData1(fooData5)).thenReturn(fooData3);

        // Run the test
        assertThrows(CachedFooAlreadyExists.class, () -> myClassUnderTest.putFoo12(fooData));
    }

    @Test
    void testPutFoo12_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo2("id")).thenReturn(fooData2);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo12(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData4.setSubData(subData4);
        verify(mockFooService).putFoo1(fooData4);
    }

    @Test
    void testPutFoo12_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo2("id")).thenReturn(fooData2);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(Optional.empty());

        // Configure FooService.putFoo1(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData4.setSubData(subData4);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData4);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo12(fooData));
    }

    @Test
    void testPutFoo13_ThrowsCachedFooAlreadyExists() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData3.setSubData(subData2);
        final Optional<FooData> fooData2 = Optional.of(fooData3);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData4)).thenReturn(fooData2);

        // Run the test
        assertThrows(CachedFooAlreadyExists.class, () -> myClassUnderTest.putFoo13(fooData));
    }

    @Test
    void testPutFoo13_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo13(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        verify(mockFooService).putFoo1(fooData3);
    }

    @Test
    void testPutFoo13_FooServicePutFoo1ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        doThrow(FooServiceException.class).when(mockFooService).putFoo1(fooData3);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo13(fooData));
    }

    @Test
    void testPutFoo14() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData3.setSubData(subData2);
        final Optional<FooData> fooData2 = Optional.of(fooData3);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData4)).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo14(fooData);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testPutFoo14_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData5 = new SubData();
        subData5.setId("id");
        final OtherData otherData5 = new OtherData();
        otherData5.setId("id");
        subData5.setOtherData(otherData5);
        fooData4.setSubData(subData5);
        when(mockFooService.putFoo2(fooData4)).thenReturn(fooData3);

        // Run the test
        final FooData result = myClassUnderTest.putFoo14(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo14_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.putFoo2(fooData3)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo14(fooData));
    }

    @Test
    void testPutFoo15() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo2("id")).thenReturn(fooData2);

        // Configure FooService.getCachedData1(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        final Optional<FooData> fooData3 = Optional.of(fooData4);
        final FooData fooData5 = new FooData();
        fooData5.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData5.setSubData(subData4);
        when(mockFooService.getCachedData1(fooData5)).thenReturn(fooData3);

        // Run the test
        final FooData result = myClassUnderTest.putFoo15(fooData);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testPutFoo15_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        when(mockFooService.getFoo2("id")).thenReturn(fooData2);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(Optional.empty());

        // Configure FooService.putFoo2(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData5 = new SubData();
        subData5.setId("id");
        final OtherData otherData5 = new OtherData();
        otherData5.setId("id");
        subData5.setOtherData(otherData5);
        fooData4.setSubData(subData5);
        final FooData fooData5 = new FooData();
        fooData5.setId("id");
        final SubData subData6 = new SubData();
        subData6.setId("id");
        final OtherData otherData6 = new OtherData();
        otherData6.setId("id");
        subData6.setOtherData(otherData6);
        fooData5.setSubData(subData6);
        when(mockFooService.putFoo2(fooData5)).thenReturn(fooData4);

        // Run the test
        final FooData result = myClassUnderTest.putFoo15(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo15_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getFoo2("id")).thenReturn(fooData2);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData3)).thenReturn(Optional.empty());

        // Configure FooService.putFoo2(...).
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData4.setSubData(subData4);
        when(mockFooService.putFoo2(fooData4)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo15(fooData));
    }

    @Test
    void testPutFoo16() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData3.setSubData(subData2);
        final Optional<FooData> fooData2 = Optional.of(fooData3);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData4.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData4)).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo16(fooData);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testPutFoo16_FooServiceGetCachedData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        expectedResult.setSubData(subData1);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData1.setSubData(subData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData2.setSubData(subData3);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData4 = new SubData();
        subData4.setId("id");
        final OtherData otherData4 = new OtherData();
        otherData4.setId("id");
        subData4.setOtherData(otherData4);
        fooData3.setSubData(subData4);
        final FooData fooData4 = new FooData();
        fooData4.setId("id");
        final SubData subData5 = new SubData();
        subData5.setId("id");
        final OtherData otherData5 = new OtherData();
        otherData5.setId("id");
        subData5.setOtherData(otherData5);
        fooData4.setSubData(subData5);
        when(mockFooService.putFoo2(fooData4)).thenReturn(fooData3);

        // Run the test
        final FooData result = myClassUnderTest.putFoo16(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo16_FooServicePutFoo2ThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        final SubData subData = new SubData();
        subData.setId("id");
        final OtherData otherData = new OtherData();
        otherData.setId("id");
        subData.setOtherData(otherData);
        fooData.setSubData(subData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        final SubData subData1 = new SubData();
        subData1.setId("id");
        final OtherData otherData1 = new OtherData();
        otherData1.setId("id");
        subData1.setOtherData(otherData1);
        fooData1.setSubData(subData1);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Configure FooService.getCachedData1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        final SubData subData2 = new SubData();
        subData2.setId("id");
        final OtherData otherData2 = new OtherData();
        otherData2.setId("id");
        subData2.setOtherData(otherData2);
        fooData2.setSubData(subData2);
        when(mockFooService.getCachedData1(fooData2)).thenReturn(Optional.empty());

        // Configure FooService.putFoo2(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        final SubData subData3 = new SubData();
        subData3.setId("id");
        final OtherData otherData3 = new OtherData();
        otherData3.setId("id");
        subData3.setOtherData(otherData3);
        fooData3.setSubData(subData3);
        when(mockFooService.putFoo2(fooData3)).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.putFoo16(fooData));
    }
}
