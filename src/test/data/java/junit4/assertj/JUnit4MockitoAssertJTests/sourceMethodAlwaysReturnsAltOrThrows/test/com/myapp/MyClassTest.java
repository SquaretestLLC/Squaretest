package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private MetricsAdapter mockMetricsAdapter;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockMetricsAdapter, new UnmockableMetrics());
    }

    @Test
    public void testDoSomething1_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething1()).isInstanceOf(NotImplementedUncheckedException.class);
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testDoSomething2_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething2()).isInstanceOf(NotImplementedUncheckedException.class);
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testDoSomething3_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething3()).isInstanceOf(NotImplementedCheckedException.class);
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testDoSomething4_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething4()).isInstanceOf(NotImplementedUncheckedException.class);
    }

    @Test
    public void testDoSomething5_ThrowsNotImplementedUncheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething5()).isInstanceOf(NotImplementedUncheckedException.class);
    }

    @Test
    public void testDoSomething6_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething6()).isInstanceOf(NotImplementedCheckedException.class);
    }

    @Test
    public void testDoSomething7_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething7("orderId"))
                .isInstanceOf(NotImplementedCheckedException.class);
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId");
    }

    @Test
    public void testDoSomething8_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething8("orderId"))
                .isInstanceOf(NotImplementedCheckedException.class);
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

    @Test
    public void testDoSomething11_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething11("orderId"))
                .isInstanceOf(NotImplementedCheckedException.class);
    }

    @Test
    public void testDoSomething12() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomething12("orderId");

        // Verify the results
    }

    @Test
    public void testDoSomething12_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething12("orderId"))
                .isInstanceOf(NotImplementedCheckedException.class);
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

    @Test
    public void testDoSomething14_ThrowsNotImplementedCheckedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomething14("orderId"))
                .isInstanceOf(NotImplementedCheckedException.class);
    }

    @Test
    public void testGetSomething1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething1();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testGetSomething2() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething2();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsAdapter).recordMethodCall();
    }

    @Test
    public void testGetSomething3() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething3();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetSomething4() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething4();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetSomething5() {
        assertThat(myClassUnderTest.getSomething5()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetSomething6() {
        assertThat(myClassUnderTest.getSomething6()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetSomething7() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething7("orderId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
        verify(mockMetricsAdapter).recordNewOrderMethodCall("orderId");
    }

    @Test
    public void testGetSomething8() {
        // Setup
        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething8("orderId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetSomething9() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("orderId", "name"));

        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething9("orderId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMetricsAdapter).recordOldOrderMethodCall("orderId");
    }

    @Test
    public void testGetSomething10() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("orderId", "name"));

        // Run the test
        final List<FooData> result = myClassUnderTest.getSomething10("orderId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetSomething11() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething11();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockMetricsAdapter).recordMethodCall();
        verify(mockMetricsAdapter).recordInsideCallableCall();
    }

    @Test
    public void testGetSomething12() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getSomething12();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
