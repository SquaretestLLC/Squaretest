package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.javadocthrows.FooServiceException;
import com.myapp.javadocthrows.OtherException;
import com.myapp.javadocthrows.ThrowingFooProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private ThrowingFooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @Test
    public void testGetFooFromPlace() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooFromPlace("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFooFromPlace("key");
    }

    @Test
    public void testGetBar() {
        // Setup
        given(mockFooProvider.getBar("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getBar("barName");
    }

    @Test
    public void testGetBar1() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar1("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getBar1("barName");
    }

    @Test
    public void testGetBar2() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar2("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.getBar2("barName");
    }

    @Test
    public void testGetBar3() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar3("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.getBar3("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getBar3("barName");
    }

    @Test
    public void testTryGetBarWithDupExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithDupExceptions("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName");
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions1() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions2() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions3() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions4() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions5() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions6() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName");
    }
}
