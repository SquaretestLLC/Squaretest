package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockMetricService);
    }

    @Test
    void testGetBestFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo1_FooServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getBestFoo2("id"));
    }

    @Test
    void testGetBestFoo3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo3_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo3_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo4_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getBestFoo4("id"));
    }

    @Test
    void testGetBestFoo4_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getBestFoo4("id"));
    }

    @Test
    void testGetIdOrName1() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final String result = myClassUnderTest.getIdOrName1("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordFooId("id");
        verify(mockMetricService).recordFooName("name");
    }

    @Test
    void testGetIdOrName1_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getIdOrName1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetIdOrName2() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final String result = myClassUnderTest.getIdOrName2("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordFooId("id");
        verify(mockMetricService).recordFooName("name");
    }

    @Test
    void testGetIdOrName2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getIdOrName2("id"));
    }

    @Test
    void testGetIdOrName3() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockFooService.getFooData3("id")).thenReturn(new FooData("id", "name", "otherId"));

        // Run the test
        final String result = myClassUnderTest.getIdOrName3("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordFooId("id");
    }

    @Test
    void testGetIdOrName3_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getIdOrName3("id"));
    }

    @Test
    void testGetIdOrName4() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockFooService.getFooData4("fooId")).thenReturn(new FooData("id", "name", "otherId"));

        // Run the test
        final String result = myClassUnderTest.getIdOrName4("id");

        // Verify the results
        assertEquals("name", result);
        verify(mockMetricService).recordFooId("id");
    }

    @Test
    void testGetIdOrName4_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getIdOrName4("id"));
    }
}
