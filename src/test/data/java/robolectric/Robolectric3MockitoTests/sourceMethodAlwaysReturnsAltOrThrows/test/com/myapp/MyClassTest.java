package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private MetricsAdapter mockMetricsAdapter;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMetricsAdapter, new UnmockableMetrics());
    }

    @Test(expected = NotImplementedUncheckedException.class)
    public void testDoSomething1_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething1();
    }

    @Test(expected = NotImplementedUncheckedException.class)
    public void testDoSomething2_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething2();
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething3_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething3();
    }

    @Test(expected = NotImplementedUncheckedException.class)
    public void testDoSomething4_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething4();
    }

    @Test(expected = NotImplementedUncheckedException.class)
    public void testDoSomething5_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething5();
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething6_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething6();
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething7_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething7("orderId");
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething8_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething8("orderId");
    }

    @Test
    public void testDoSomething9() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething9("orderId");

        // Verify the results
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
    }

    @Test
    public void testDoSomething10() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething10("orderId");

        // Verify the results
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
    }

    @Test
    public void testDoSomething11() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething11("orderId");

        // Verify the results
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething11_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething11("orderId");
    }

    @Test
    public void testDoSomething12() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething12("orderId");

        // Verify the results
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething12_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething12("orderId");
    }

    @Test
    public void testDoSomething13() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething13("orderId");

        // Verify the results
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId");
    }

    @Test
    public void testDoSomething14() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething14("orderId");

        // Verify the results
    }

    @Test(expected = NotImplementedCheckedException.class)
    public void testDoSomething14_ThrowsNotImplementedCheckedException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething14("orderId");
    }

    @Test
    public void testGetSomething1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething1();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testGetSomething2() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething2();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testGetSomething3() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething3();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetSomething4() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething4();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetSomething5() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getSomething5());
    }

    @Test
    public void testGetSomething6() {
        assertEquals(Collections.emptyList(), myClassUnderTest.getSomething6());
    }

    @Test
    public void testGetSomething7() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething7("orderId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId");
    }

    @Test
    public void testGetSomething8() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething8("orderId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetSomething9() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("orderId", "name"));

        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething9("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
    }

    @Test
    public void testGetSomething10() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("orderId", "name"));

        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething10("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSomething11() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething11();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricsAdapter).recordMethodCall();
        verify(mockMetricsAdapter).recordInsideCallableCall();
    }

    @Test
    public void testGetSomething12() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething12();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
