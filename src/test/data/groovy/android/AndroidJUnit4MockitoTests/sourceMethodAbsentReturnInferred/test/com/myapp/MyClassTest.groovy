package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.junit.Assert.assertNull
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MetricsAdapter mockMetricsAdapter
    @Mock
    private FooMapper mockFooMapper

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService, mockMetricsAdapter, mockFooMapper)
    }

    @Test
    void testGetFooByIdOptional() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional1() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional1(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional1_FooServiceGetFooByIdOptional1ReturnsAbsent() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional1(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional1_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional1(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional2() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional2(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional2_FooServiceReturnsAbsent() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional2(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional2_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional2(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional3() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional3(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event")
    }

    @Test
    void testGetFooByIdOptional3_FooServiceReturnsAbsent() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional3(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event")
    }

    @Test
    void testGetFooByIdOptional3_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional3(0L)

        // Verify the results
        assert Optional.empty() == result
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event")
    }

    @Test
    void testGetFooByIdOptional4() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional4(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdOptional4_FooServiceReturnsAbsent() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional4(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdOptional4_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional4(0L)

        // Verify the results
        assert Optional.empty() == result
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdOptional5() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional5(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional5_FooServiceReturnsAbsent() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional5(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional5_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional5(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional6() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional6(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional6_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional6(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional7() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional7(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional7_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Configure FooService.getFooByIdOptionalSame(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional7(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional7_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional7(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional8() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional8(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockFooService).getFooByIdOptionalSame(0L)
    }

    @Test
    void testGetFooByIdOptional8_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional8(0L)

        // Verify the results
        assert Optional.empty() == result
        verify(mockFooService).getFooByIdOptionalSame(0L)
    }

    @Test
    void testGetFooByIdOptional9() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional9(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional9_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional9(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional10() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional10(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional10_FooServiceGetFooByIdNullable2ReturnsNull() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null)
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional10(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdOptional10_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null)
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional10(0L)

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testGetFooByIdOptional11() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional11(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockFooService).getFooByIdNullable2(0L)
    }

    @Test
    void testGetFooByIdOptional11_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional11(0L)

        // Verify the results
        assert Optional.empty() == result
        verify(mockFooService).getFooByIdNullable2(0L)
    }

    @Test
    void testGetFooByIdNullable() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdNullable_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable(0L)

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFooByIdNullable2() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable2(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = IllegalArgumentException.class)
    void testGetFooByIdNullable2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getFooByIdNullable2(0L)
    }

    @Test
    void testGetFooByIdNullable3() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")

        // Configure FooService.getFooByIdOptional(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable3(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Configure FooService.getFooByIdOptionalSame(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable3(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = IllegalArgumentException.class)
    void testGetFooByIdNullable3_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getFooByIdNullable3(0L)
    }

    @Test
    void testGetFooByIdNullable4() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")

        // Configure FooService.getFooByIdOptionalSame(...).
        def fooDataWithEq = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(fooDataWithEq)

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable4(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockFooService).getFooByIdOptional(0L)
    }

    @Test(expected = IllegalArgumentException.class)
    void testGetFooByIdNullable4_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getFooByIdNullable4(0L)
    }

    @Test
    void testGetFooByIdNullable5() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable5(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdNullable5_FooServiceReturnsAbsent() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable5(0L)

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test(expected = NoSuchElementException.class)
    void testGetFooByIdNullable5_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        myClassUnderTest.getFooByIdNullable5(0L)
    }

    @Test
    void testGetFooByIdNullable6() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")

        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable6(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooByIdNullable6_FooServiceReturnsAbsent() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(Optional.empty())
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable6(0L)

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = NoSuchElementException.class)
    void testGetFooByIdNullable6_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        myClassUnderTest.getFooByIdNullable6(0L)
    }
}
