package com.myapp;

import com.myapp.javadocthrows.FooServiceException;
import com.myapp.javadocthrows.OtherException;
import com.myapp.javadocthrows.ThrowingFooProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ThrowingFooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @Test
    void testGetFooFromPlace() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooFromPlace("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooFromPlace("key"));
    }

    @Test
    void testGetBar() {
        // Setup
        given(mockFooProvider.getBar("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getBar("barName"));
    }

    @Test
    void testGetBar1() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar1("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getBar1("barName"));
    }

    @Test
    void testGetBar2() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar2("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.getBar2("barName"));
    }

    @Test
    void testGetBar3() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar3("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.getBar3("barName"));
    }

    @Test
    void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getBar3("barName"));
    }

    @Test
    void testTryGetBarWithDupExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithDupExceptions("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithDupExceptions("barName"));
    }

    @Test
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithDupExceptions("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions1() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions1("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions1("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions2() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions2("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions2("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions3() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions3("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions3("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions4() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions4("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions4("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions5() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions5("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions5("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions6() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThrows(OtherException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions6("barName"));
    }

    @Test
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.tryGetBarWithTwoExceptions6("barName"));
    }
}
