package com.myapp;

import com.myapp.nullables.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

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

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockStandardAnnotationNullFooProvider, mockCustomAnnotationNullFooProvider,
                mockMissingAnnotationNullFooProvider, mockSubNullFooProvider, mockSubJavadocNullFooProvider);
    }

    @Test
    void testSafeGetFoo() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.safeGetFoo()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo_StandardAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.safeGetFoo()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo();

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.getFoo()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo_StandardAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.getFoo()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo());
    }

    @Test
    void testSafeGetFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.safeGetFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo1_CustomAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.safeGetFoo1()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1();

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getFoo1()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo1());
    }

    @Test
    void testGetOtherFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getOtherFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOtherFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getOtherFoo1()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOtherFoo1());
    }

    @Test
    void testSafeGetFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.safeGetFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo2_MissingAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.safeGetFoo2()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo2();

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getFoo2()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo2());
    }

    @Test
    void testGetOtherFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getOtherFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOtherFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getOtherFoo2()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getOtherFoo2());
    }

    @Test
    void testSafeGetFoo3() {
        // Setup
        when(mockSubNullFooProvider.safeGetFoo3()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo3();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo3_SubNullFooProviderReturnsNull() {
        // Setup
        when(mockSubNullFooProvider.safeGetFoo3()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo3();

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo3() {
        // Setup
        when(mockSubNullFooProvider.getFoo3()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_SubNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockSubNullFooProvider.getFoo3()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo3());
    }

    @Test
    void testSafeGetFoo4() {
        // Setup
        when(mockSubJavadocNullFooProvider.safeGetFoo4()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo4();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo4_SubJavadocNullFooProviderReturnsNull() {
        // Setup
        when(mockSubJavadocNullFooProvider.safeGetFoo4()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo4();

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo4() {
        // Setup
        when(mockSubJavadocNullFooProvider.getFoo4()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4_SubJavadocNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockSubJavadocNullFooProvider.getFoo4()).thenThrow(RuntimeException.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo4());
    }
}
