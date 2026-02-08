package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricsAdapter mockMetricsAdapter;
    @Mock
    private FooMapper mockFooMapper;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockMetricsAdapter, mockFooMapper);
    }

    @Test
    void testGetFooByIdList() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList1() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList1(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList1_FooServiceGetFooByIdList1ReturnsNoItems() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList1(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList1_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList1(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList2() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList2(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList2(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList3() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event");
    }

    @Test
    void testGetFooByIdList3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList3(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event");
    }

    @Test
    void testGetFooByIdList4() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList4(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdList4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList4(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdList5() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList5(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList5(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList6() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList6(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList6(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList7() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs1 = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs1);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList7(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList7_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList7(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList7_FooServiceGetFooByIdListSameReturnsNoItems() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        when(mockFooService.getFooByIdListSame(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList7(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList8() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs1 = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs1);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList8(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockFooService).getFooByIdListSame(0L);
    }

    @Test
    void testGetFooByIdList8_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());

        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList8(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockFooService).getFooByIdListSame(0L);
    }

    @Test
    void testGetFooByIdList9() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdNullable(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList9(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList9_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList9(0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooByIdList9_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList9(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList10() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList10(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList10_FooServiceGetFooByIdNullable2ReturnsNull() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null);

        // Configure FooService.getFooByIdNullable(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList10(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdList10_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null);
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList10(0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooByIdList10_FooServiceGetFooByIdNullableReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null);
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList10(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooByIdList11() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs);

        // Configure FooService.getFooByIdNullable(...).
        final List<FooDataWithEq> fooDataWithEqs1 = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(fooDataWithEqs1);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList11(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockFooService).getFooByIdListSame(0L);
    }

    @Test
    void testGetFooByIdList11_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs);

        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList11(0L);

        // Verify the results
        assertNull(result);
        verify(mockFooService).getFooByIdListSame(0L);
    }

    @Test
    void testGetFooByIdList11_FooServiceGetFooByIdNullableReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs);

        when(mockFooService.getFooByIdNullable(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdList11(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockFooService).getFooByIdListSame(0L);
    }

    @Test
    void testGetFooByIdNullable() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdNullable(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable(0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooByIdNullable_FooServiceReturnsNoItems() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable2() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable2(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFooByIdNullable2(0L));
    }

    @Test
    void testGetFooByIdNullable3() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());

        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdListSameReturnsNoItems() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdList(0L)).thenReturn(Collections.emptyList());
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable4() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        // Configure FooService.getFooByIdListSame(...).
        final List<FooDataWithEq> fooDataWithEqs1 = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdListSame(0L)).thenReturn(fooDataWithEqs1);

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdNullable4(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockFooService).getFooByIdList(0L);
    }

    @Test
    void testGetFooByIdNullable4_FooServiceGetFooByIdListSameReturnsNoItems() {
        // Setup
        // Configure FooService.getFooByIdList(...).
        final List<FooDataWithEq> fooDataWithEqs = Arrays.asList(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdList(0L)).thenReturn(fooDataWithEqs);

        when(mockFooService.getFooByIdListSame(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdNullable4(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockFooService).getFooByIdList(0L);
    }

    @Test
    void testGetFooByIdNullable5() {
        // Setup
        final List<FooDataWithEq> expectedResult = Arrays.asList(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdNullable5(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdNullable5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final List<FooDataWithEq> result = myClassUnderTest.getFooByIdNullable5(0L);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdNullable6() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdList1(...).
        final List<OtherFooData> otherFooData = Arrays.asList(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdList1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable6(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable6_FooServiceReturnsNoItems() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdList1(0L)).thenReturn(Collections.emptyList());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable6(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
