package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    private MetricsAdapter mockMetricsAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockOtherService, mockMetricsAdapter);
    }

    @Test
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData1("id"));
    }

    @Test
    void testGetFooData1_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData1_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData2("id"));
    }

    @Test
    void testGetFooData2_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData2_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData3("id"));
    }

    @Test
    void testGetFooData3_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData3_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData3_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData4_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData4("id"));
    }

    @Test
    void testGetFooData4_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData4_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData4_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData4_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData5() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData5_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData5("id"));
    }

    @Test
    void testGetFooData5_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData5("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData5_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData5("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData5_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData5("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData5_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData6() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData6_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData6("id"));
    }

    @Test
    void testGetFooData6_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData6("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData6_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData6("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData6_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData6("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData6_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData7() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData7("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData7_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData7("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData7_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData7("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData8() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData8("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordFooTypeCallComplete("id");
    }

    @Test
    void testGetFooData8_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData8("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricsAdapter).recordFooTypeCallComplete("id");
    }

    @Test
    void testGetFooData8_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData8("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricsAdapter).recordFooTypeCallComplete("id");
    }

    @Test
    void testGetFooData9() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData9("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordFooTypeCallComplete("id");
    }

    @Test
    void testGetFooData9_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData9("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricsAdapter).recordFooTypeCallComplete("id");
    }

    @Test
    void testGetFooData9_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData9("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricsAdapter).recordFooTypeCallComplete("id");
    }

    @Test
    void testGetFooData10() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData10("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData10_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getFooData10("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData10_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData10("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData11() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.of("content".getBytes()));

        // Run the test
        final FooData result = myClassUnderTest.getFooData11("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData11_FooServiceGetFooType2ReturnsNull() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFooData11("id"));
    }

    @Test
    void testGetFooData11_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData11("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData11_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData11("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData11_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooData11("id"));
    }

    @Test
    void testGetFooData11_OtherServiceReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooType2("id")).thenReturn(FooType.Type1);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        when(mockOtherService.getOtherData2("otherId")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData11("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
