package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.verify
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private MetricsAdapter mockMetricsAdapter

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockMetricsAdapter, new UnmockableMetrics())
    }

    @Test
    void testDoSomething1_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething1()
        }).isInstanceOf(NotImplementedUncheckedException.class)
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testDoSomething2_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething2()
        }).isInstanceOf(NotImplementedUncheckedException.class)
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testDoSomething3_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething3()
        }).isInstanceOf(NotImplementedCheckedException.class)
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testDoSomething4_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething4()
        }).isInstanceOf(NotImplementedUncheckedException.class)
    }

    @Test
    void testDoSomething5_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething5()
        }).isInstanceOf(NotImplementedUncheckedException.class)
    }

    @Test
    void testDoSomething6_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething6()
        }).isInstanceOf(NotImplementedCheckedException.class)
    }

    @Test
    void testDoSomething7_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething7("orderId")
        }).isInstanceOf(NotImplementedCheckedException.class)
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId")
    }

    @Test
    void testDoSomething8_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething8("orderId")
        }).isInstanceOf(NotImplementedCheckedException.class)
    }

    @Test
    void testDoSomething9() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething9("orderId")

        // Verify the results
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
    }

    @Test
    void testDoSomething10() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething10("orderId")

        // Verify the results
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
    }

    @Test
    void testDoSomething11() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething11("orderId")

        // Verify the results
    }

    @Test
    void testDoSomething11_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething11("orderId")
        }).isInstanceOf(NotImplementedCheckedException.class)
    }

    @Test
    void testDoSomething12() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething12("orderId")

        // Verify the results
    }

    @Test
    void testDoSomething12_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething12("orderId")
        }).isInstanceOf(NotImplementedCheckedException.class)
    }

    @Test
    void testDoSomething13() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething13("orderId")

        // Verify the results
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId")
    }

    @Test
    void testDoSomething14() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething14("orderId")

        // Verify the results
    }

    @Test
    void testDoSomething14_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomething14("orderId")
        }).isInstanceOf(NotImplementedCheckedException.class)
    }

    @Test
    void testGetSomething1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething1()

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testGetSomething2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething2()

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testGetSomething3() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething3()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetSomething4() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething4()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetSomething5() {
        assertThat(myClassUnderTest.getSomething5()).isEqualTo([])
    }

    @Test
    void testGetSomething6() {
        assertThat(myClassUnderTest.getSomething6()).isEqualTo([])
    }

    @Test
    void testGetSomething7() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething7("orderId")

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId")
    }

    @Test
    void testGetSomething8() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething8("orderId")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetSomething9() {
        // Setup
        def expectedResult = [new FooData("orderId", "name")]

        // Run the test
        def result = myClassUnderTest.getSomething9("orderId")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
    }

    @Test
    void testGetSomething10() {
        // Setup
        def expectedResult = [new FooData("orderId", "name")]

        // Run the test
        def result = myClassUnderTest.getSomething10("orderId")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetSomething11() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething11()

        // Verify the results
        assertThat(result).isEqualTo([])
        verify(mockMetricsAdapter).recordMethodCall()
        verify(mockMetricsAdapter).recordInsideCallableCall()
    }

    @Test
    void testGetSomething12() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething12()

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}
