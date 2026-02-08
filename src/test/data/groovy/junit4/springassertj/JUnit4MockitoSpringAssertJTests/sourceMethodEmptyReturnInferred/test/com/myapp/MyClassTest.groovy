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

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
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
    void testGetFooByIdList() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList1() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList1(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList1_FooServiceGetFooByIdList1ReturnsNoItems() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList1(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList1_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockFooService.getFooByIdList(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList1(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList2() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList2(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList2(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList3() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList3(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event")
    }

    @Test
    void testGetFooByIdList3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList3(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordMetricEventWithoutReturn("event")
    }

    @Test
    void testGetFooByIdList4() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList4(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdList4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList4(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdList5() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList5(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList5(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList6() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())

        // Run the test
        def result = myClassUnderTest.getFooByIdList6(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn([])
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())

        // Run the test
        def result = myClassUnderTest.getFooByIdList6(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList7() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])
        when(mockFooService.getFooByIdListSame(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList7(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList7_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList7(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList7_FooServiceGetFooByIdListSameReturnsNoItems() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])
        when(mockFooService.getFooByIdListSame(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList7(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList8() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList8(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockFooService).getFooByIdListSame(0L)
    }

    @Test
    void testGetFooByIdList8_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList8(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockFooService).getFooByIdListSame(0L)
    }

    @Test
    void testGetFooByIdList9() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList9(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList9_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdList9(0L)

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFooByIdList9_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList9(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList10() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdList10(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList10_FooServiceGetFooByIdNullable2ReturnsNull() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null)
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList10(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdList10_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null)
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdList10(0L)

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFooByIdList10_FooServiceGetFooByIdNullableReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdNullable2(0L)).thenReturn(null)
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList10(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetFooByIdList11() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdList11(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockFooService).getFooByIdListSame(0L)
    }

    @Test
    void testGetFooByIdList11_FooServiceGetFooByIdNullableReturnsNull() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.getFooByIdList11(0L)

        // Verify the results
        assertThat(result).isNull()
        verify(mockFooService).getFooByIdListSame(0L)
    }

    @Test
    void testGetFooByIdList11_FooServiceGetFooByIdNullableReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdList11(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockFooService).getFooByIdListSame(0L)
    }

    @Test
    void testGetFooByIdNullable() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([new FooDataWithEq(0L, "name")])

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
    void testGetFooByIdNullable_FooServiceReturnsNoItems() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdNullable(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable2() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable2(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList(0L)).thenReturn([])

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooByIdNullable2(0L)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testGetFooByIdNullable3() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdList(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable3(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdListReturnsNoItems() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdList(0L)).thenReturn([])
        when(mockFooService.getFooByIdListSame(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable3(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable3_FooServiceGetFooByIdListSameReturnsNoItems() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdList(0L)).thenReturn([])
        when(mockFooService.getFooByIdListSame(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable3(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable4() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdListSame(0L)).thenReturn([new FooDataWithEq(0L, "name")])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable4(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockFooService).getFooByIdList(0L)
    }

    @Test
    void testGetFooByIdNullable4_FooServiceGetFooByIdListSameReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdListSame(0L)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable4(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockFooService).getFooByIdList(0L)
    }

    @Test
    void testGetFooByIdNullable5() {
        // Setup
        def expectedResult = [new FooDataWithEq(0L, "name")]
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable5(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdNullable5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable5(0L)

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordMetricWithReturn("event")
    }

    @Test
    void testGetFooByIdNullable6() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdList1(0L)).thenReturn([new OtherFooData(0L, "name")])
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable6(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooByIdNullable6_FooServiceReturnsNoItems() {
        // Setup
        def expectedResult = new FooDataWithEq(0L, "name")
        when(mockFooService.getFooByIdList1(0L)).thenReturn([])
        when(mockMetricsAdapter.recordMetricWithReturn("event")).thenReturn(new MetricEvent())
        when(mockFooMapper.convert(new OtherFooData(0L, "name"))).thenReturn(new FooDataWithEq(0L, "name"))

        // Run the test
        def result = myClassUnderTest.getFooByIdNullable6(0L)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }
}
