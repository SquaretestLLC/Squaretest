package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;
    @Mock
    private AlphaService mockAlphaService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockBarService, mockAlphaService, mockMetricService);
    }

    @Test
    void testValidateFooData1() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        // Configure AlphaService.getAlphaData1(...).
        final List<AlphaData> alphaData = Arrays.asList(new AlphaData("id", "name"));
        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(alphaData);

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordTuple("id", "id", "id");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFooData1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("fooId")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFooData1_BarServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        when(mockBarService.getBars1("barId")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFooData1_AlphaServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.validateFooData1("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testGetFooData1() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        // Configure AlphaService.getAlphaData1(...).
        final List<AlphaData> alphaData = Arrays.asList(new AlphaData("id", "name"));
        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(alphaData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooData1_BarServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        when(mockBarService.getBars1("barId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_AlphaServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        // Configure AlphaService.getAlphaData1(...).
        final List<AlphaData> alphaData = Arrays.asList(new AlphaData("id", "name"));
        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(alphaData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooData2_BarServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        when(mockBarService.getBars1("barId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_AlphaServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        // Configure AlphaService.getAlphaData1(...).
        final List<AlphaData> alphaData = Arrays.asList(new AlphaData("id", "name"));
        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(alphaData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooData3_BarServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        when(mockBarService.getBars1("barId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_AlphaServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "barId"));

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testValidateFoo2() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        // Configure AlphaService.getAlphaData1(...).
        final List<AlphaData> alphaData = Arrays.asList(new AlphaData("id", "name"));
        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(alphaData);

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData1 = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos2("fooId")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.validateFoo2("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordTuple("id", "id", "id");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFoo2_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("fooId")).thenReturn(Collections.emptyList());

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos2("fooId")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFoo2("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFoo2_BarServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        when(mockBarService.getBars1("barId")).thenReturn(Collections.emptyList());

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData1 = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos2("fooId")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.validateFoo2("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFoo2_AlphaServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(Collections.emptyList());

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData1 = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos2("fooId")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.validateFoo2("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }

    @Test
    void testValidateFoo2_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "barId"));
        when(mockFooService.getFoos1("fooId")).thenReturn(fooData);

        // Configure BarService.getBars1(...).
        final List<BarData> barData = Arrays.asList(new BarData("id", "alphaId"));
        when(mockBarService.getBars1("barId")).thenReturn(barData);

        // Configure AlphaService.getAlphaData1(...).
        final List<AlphaData> alphaData = Arrays.asList(new AlphaData("id", "name"));
        when(mockAlphaService.getAlphaData1("alphaId")).thenReturn(alphaData);

        when(mockFooService.getFoos2("fooId")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.validateFoo2("fooId");

        // Verify the results
        verify(mockMetricService).recordStartOfValidateMethod("fooId");
        verify(mockMetricService).recordTuple("id", "id", "id");
        verify(mockMetricService).recordEndOfValidateMethod("fooId");
    }
}
