package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.verify
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private MetricsAdapter mockMetricsAdapter

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockMetricsAdapter, new UnmockableMetrics())
    }

    @Test(expected = NotImplementedUncheckedException.class)
    void testDoSomething1_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething1()
    }

    @Test(expected = NotImplementedUncheckedException.class)
    void testDoSomething2_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething2()
    }

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething3_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething3()
    }

    @Test(expected = NotImplementedUncheckedException.class)
    void testDoSomething4_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething4()
    }

    @Test(expected = NotImplementedUncheckedException.class)
    void testDoSomething5_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething5()
    }

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething6_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething6()
    }

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething7_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething7("orderId")
    }

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething8_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething8("orderId")
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

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething11_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething11("orderId")
    }

    @Test
    void testDoSomething12() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething12("orderId")

        // Verify the results
    }

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething12_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething12("orderId")
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

    @Test(expected = NotImplementedCheckedException.class)
    void testDoSomething14_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething14("orderId")
    }

    @Test
    void testGetSomething1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething1()

        // Verify the results
        assert [] == result
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testGetSomething2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething2()

        // Verify the results
        assert [] == result
        verify(mockMetricsAdapter).recordMethodCall()
    }

    @Test
    void testGetSomething3() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething3()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetSomething4() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething4()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetSomething5() {
        assert [] == myClassUnderTest.getSomething5()
    }

    @Test
    void testGetSomething6() {
        assert [] == myClassUnderTest.getSomething6()
    }

    @Test
    void testGetSomething7() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething7("orderId")

        // Verify the results
        assert [] == result
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId")
    }

    @Test
    void testGetSomething8() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething8("orderId")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetSomething9() {
        // Setup
        def expectedResult = [new FooData("orderId", "name")]

        // Run the test
        def result = myClassUnderTest.getSomething9("orderId")

        // Verify the results
        assert expectedResult == result
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId")
    }

    @Test
    void testGetSomething10() {
        // Setup
        def expectedResult = [new FooData("orderId", "name")]

        // Run the test
        def result = myClassUnderTest.getSomething10("orderId")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetSomething11() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething11()

        // Verify the results
        assert [] == result
        verify(mockMetricsAdapter).recordMethodCall()
        verify(mockMetricsAdapter).recordInsideCallableCall()
    }

    @Test
    void testGetSomething12() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getSomething12()

        // Verify the results
        assert [] == result
    }
}
