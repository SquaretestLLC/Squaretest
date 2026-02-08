package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdOptional_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional(0L)

        // Verify the results
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdOptional6_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional6(0L)

        // Verify the results
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
        verify(mockFooService).getFooByIdOptionalSame(0L)
    }

    @Test
    void testGetFooByIdOptional8_FooServiceGetFooByIdOptionalReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional8(0L)

        // Verify the results
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdOptional9_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional9(0L)

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testGetFooByIdOptional10() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional10(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdOptional10_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null)
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional10(0L)

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testGetFooByIdOptional11() {
        // Setup
        def expectedResult = Optional.of(new FooDataWithEq(0L, "name"))
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional11(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockFooService).getFooByIdNullable2(0L)
    }

    @Test
    void testGetFooByIdOptional11_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdOptional11(0L)

        // Verify the results
        assertThat(result).isEmpty()
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable(0L)

        // Verify the results
        assertThat(result).isNull()
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooByIdNullable2(0L)
        }).isInstanceOf(IllegalArgumentException.class)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptional(0L)).thenReturn(Optional.empty())
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooByIdNullable3(0L)
        }).isInstanceOf(IllegalArgumentException.class)
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
        assertThat(result).isEqualTo(expectedResult)
        verify(mockFooService).getFooByIdOptional(0L)
    }

    @Test
    void testGetFooByIdNullable4_FooServiceGetFooByIdOptionalSameReturnsAbsent() {
        // Setup
        when(mockFooService.getFooByIdOptionalSame(0L)).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooByIdNullable4(0L)
        }).isInstanceOf(IllegalArgumentException.class)
        verify(mockFooService).getFooByIdOptional(0L)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdNullable5_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooByIdNullable5(0L)
        }).isInstanceOf(NoSuchElementException.class)
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable6_FooMapperReturnsNull() {
        // Setup
        // Configure FooService.getFooByIdOptional1(...).
        def otherFooData = Optional.of(new OtherFooData(0L, "name"))
        when(mockFooService.getFooByIdOptional1(0L)).thenReturn(otherFooData)

        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(null)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooByIdNullable6(0L)
        }).isInstanceOf(NoSuchElementException.class)
    }
}
