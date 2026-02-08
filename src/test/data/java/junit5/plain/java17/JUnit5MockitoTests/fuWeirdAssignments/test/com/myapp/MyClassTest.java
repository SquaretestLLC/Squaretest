package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private OtherService mockOtherService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockOtherService, mockMetricService);
    }

    @Test
    void testGetBestFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordFooPresent("id");
        verify(mockMetricService).recordFooAbsent("id");
    }

    @Test
    void testGetBestFoo1_FooServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordFooPresent("id");
        verify(mockMetricService).recordFooAbsent("id");
    }

    @Test
    void testGetBestFoo2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo2("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordFooPresent("id");
        verify(mockMetricService).recordFooIdMatch("id");
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
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo3("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordFooPresent("id");
        verify(mockMetricService).recordFooIdMatch("id");
    }

    @Test
    void testGetBestFoo3_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getBestFoo3("id"));
    }

    @Test
    void testGetBestFoo4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo4_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo5() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo5_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo6() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestFoo6_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getBestFoo6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBestOtherData1() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final String result = myClassUnderTest.getBestOtherData1("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData1_OtherServiceGetOtherData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData1("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData1_OtherServiceGetOtherData2ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData1("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData1_OtherServiceGetOtherData3ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData1("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData2() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final String result = myClassUnderTest.getBestOtherData2("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getBestOtherData2("id"));
    }

    @Test
    void testGetBestOtherData2_OtherServiceGetOtherData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData2("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData2_OtherServiceGetOtherData2ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData2("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData2_OtherServiceGetOtherData3ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData2("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData3() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final String result = myClassUnderTest.getBestOtherData3("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData3_OtherServiceGetOtherData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData3("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData3_OtherServiceGetOtherData2ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData3("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData3_OtherServiceGetOtherData3ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData("id", "name", FooType.Type1, "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData3("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData4() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.of("content".getBytes()));
        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final String result = myClassUnderTest.getBestOtherData4("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData4_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getBestOtherData4("id"));
    }

    @Test
    void testGetBestOtherData4_OtherServiceGetOtherData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData1("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData4("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData4_OtherServiceGetOtherData2ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData4("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetBestOtherData4_OtherServiceGetOtherData3ReturnsAbsent() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData3("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getBestOtherData4("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordOtherDataCallComplete("id");
    }

    @Test
    void testGetIdOrName1() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final String result = myClassUnderTest.getIdOrName1("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordFooId("id");
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
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", FooType.Type1, "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final String result = myClassUnderTest.getIdOrName2("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordFooId("id");
    }

    @Test
    void testGetIdOrName2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getIdOrName2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetMultilineConstant() {
        assertEquals("This\nIs\nA\nMultiline\nString!\n", MyClass.getMultilineConstant());
    }
}
