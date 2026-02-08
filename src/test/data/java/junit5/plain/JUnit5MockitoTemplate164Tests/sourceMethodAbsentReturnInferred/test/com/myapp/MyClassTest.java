package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void testGetFooByIdOptional() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional1() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional1(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional1_FooServiceGetFooByIdOptional1ReturnsAbsent() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional1(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional1_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional1(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional2() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional2(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional2(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional2_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional2(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional3() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event");
    }

    @Test
    void testGetFooByIdOptional3_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional3(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event");
    }

    @Test
    void testGetFooByIdOptional3_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional3(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event");
    }

    @Test
    void testGetFooByIdOptional4() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional4(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdOptional4_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional4(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdOptional4_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional4(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdOptional5() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional5(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional5_FooServiceReturnsAbsent() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional5(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional5_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional5(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional6() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional6(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional6_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional6(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional7() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq1 = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq1);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional7(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional7_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional7(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional7_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional7(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional8() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq1 = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq1);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional8(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockFooService).getFooByIdOptionalSame(0L);
    }

    @Test
    void testGetFooByIdOptional8_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional8(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockFooService).getFooByIdOptionalSame(0L);
    }

    @Test
    void testGetFooByIdOptional9() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional9(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional9_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional9(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooByIdOptional10() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional10(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional10_FooServiceGetFooByIdNullable2ReturnsNull() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null);
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional10(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional10_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional10(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdOptional11() {
        // Setup
        final Optional<FooDataWithEq> expectedResult = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional11(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockFooService).getFooByIdNullable2(0L);
    }

    @Test
    void testGetFooByIdOptional11_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null);

        // Run the test
        final Optional<FooDataWithEq> result = myClassUnderTest.getFooByIdOptional11(0L);

        // Verify the results
        assertEquals(Optional.empty(), result);
        verify(mockFooService).getFooByIdNullable2(0L);
    }

    @Test
    void testGetFooByIdNullable() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"));

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
    void testGetFooByIdNullable2() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable2(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable2(0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooByIdNullable3() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq1 = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq1);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty());

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty());

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable3(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable4() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        // Configure FooService.getFooByIdOptionalSame(...).
        final Optional<FooDataWithEq> fooDataWithEq1 = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq1);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable4(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockFooService).getFooByIdOptional(0L);
    }

    @Test
    void testGetFooByIdNullable4_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        // Configure FooService.getFooByIdOptional(...).
        final Optional<FooDataWithEq> fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"));
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq);

        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty());

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable4(0L);

        // Verify the results
        assertNull(result);
        verify(mockFooService).getFooByIdOptional(0L);
    }

    @Test
    void testGetFooByIdNullable5() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable5(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdNullable5_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable5(0L);

        // Verify the results
        assertNull(result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdNullable5_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable5(0L);

        // Verify the results
        assertNull(result);
        verify(mockMetricsAdapter).recordMetricWithReturn("event");
    }

    @Test
    void testGetFooByIdNullable6() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");

        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable6(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable6_FooServiceReturnsAbsent() {
        // Setup
        final FooDataWithEq expectedResult = new FooDataWithEq(0L, "name");
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty());
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"));

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable6(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooByIdNullable6_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        final Optional<OtherFooData> otherFooData = Optional.of(new OtherFooData(0L, "name"));
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData);

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent());
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null);

        // Run the test
        final FooDataWithEq result = myClassUnderTest.getFooByIdNullable6(0L);

        // Verify the results
        assertNull(result);
    }
}
