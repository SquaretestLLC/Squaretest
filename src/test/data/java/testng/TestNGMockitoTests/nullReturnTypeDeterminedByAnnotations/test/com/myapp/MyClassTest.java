package com.myapp;

import com.myapp.nullables.*;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MyClassTest {

    @Mock
    private StandardAnnotationNullFooProvider mockStandardAnnotationNullFooProvider;
    @Mock
    private CustomAnnotationNullFooProvider mockCustomAnnotationNullFooProvider;
    @Mock
    private MissingAnnotationNullFooProvider mockMissingAnnotationNullFooProvider;
    @Mock
    private SubNullFooProvider mockSubNullFooProvider;
    @Mock
    private SubJavadocNullFooProvider mockSubJavadocNullFooProvider;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockStandardAnnotationNullFooProvider, mockCustomAnnotationNullFooProvider,
                mockMissingAnnotationNullFooProvider, mockSubNullFooProvider, mockSubJavadocNullFooProvider);
    }

    @Test
    public void testSafeGetFoo() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.safeGetFoo()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testSafeGetFoo_StandardAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.safeGetFoo()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.getFoo()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo_StandardAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.getFoo()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo();
    }

    @Test
    public void testSafeGetFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.safeGetFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testSafeGetFoo1_CustomAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.safeGetFoo1()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getFoo1()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo1();
    }

    @Test
    public void testGetOtherFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getOtherFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetOtherFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getOtherFoo1()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getOtherFoo1();
    }

    @Test
    public void testSafeGetFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.safeGetFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testSafeGetFoo2_MissingAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.safeGetFoo2()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo2();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getFoo2()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo2();
    }

    @Test
    public void testGetOtherFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getOtherFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetOtherFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getOtherFoo2()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getOtherFoo2();
    }

    @Test
    public void testSafeGetFoo3() {
        // Setup
        when(mockSubNullFooProvider.safeGetFoo3()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo3();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testSafeGetFoo3_SubNullFooProviderReturnsNull() {
        // Setup
        when(mockSubNullFooProvider.safeGetFoo3()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo3();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo3() {
        // Setup
        when(mockSubNullFooProvider.getFoo3()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo3_SubNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockSubNullFooProvider.getFoo3()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo3();
    }

    @Test
    public void testSafeGetFoo4() {
        // Setup
        when(mockSubJavadocNullFooProvider.safeGetFoo4()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo4();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testSafeGetFoo4_SubJavadocNullFooProviderReturnsNull() {
        // Setup
        when(mockSubJavadocNullFooProvider.safeGetFoo4()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo4();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo4() {
        // Setup
        when(mockSubJavadocNullFooProvider.getFoo4()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetFoo4_SubJavadocNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockSubJavadocNullFooProvider.getFoo4()).thenThrow(RuntimeException.class);

        // Run the test
        myClassUnderTest.getFoo4();
    }
}
