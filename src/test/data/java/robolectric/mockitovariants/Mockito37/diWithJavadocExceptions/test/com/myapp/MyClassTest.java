package com.myapp;

import com.myapp.javadocthrows.FooServiceException;
import com.myapp.javadocthrows.OtherException;
import com.myapp.javadocthrows.ThrowingFooProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private ThrowingFooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testGetFooFromPlace() {
        // Setup
        when(mockFooProvider.getFooFromPlace("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooFromPlace("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        when(mockFooProvider.getFooFromPlace("key")).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFooFromPlace("key");
    }

    @Test
    public void testGetBar() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getBar("barName");
    }

    @Test
    public void testGetBar1() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar1("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = FooServiceException.class)
    public void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getBar1("barName");
    }

    @Test
    public void testGetBar2() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar2("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.getBar2("barName");
    }

    @Test
    public void testGetBar3() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar3("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.getBar3("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.getBar3("barName");
    }

    @Test
    public void testTryGetBarWithDupExceptions() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithDupExceptions("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName");
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions1() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions2() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions3() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions4() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions5() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName");
    }

    @Test
    public void testTryGetBarWithTwoExceptions6() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = OtherException.class)
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName");
    }

    @Test(expected = FooServiceException.class)
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName");
    }
}
