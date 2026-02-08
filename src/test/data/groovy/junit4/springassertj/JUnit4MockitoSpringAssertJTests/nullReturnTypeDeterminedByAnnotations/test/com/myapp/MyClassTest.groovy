package com.myapp

import com.myapp.nullables.*
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private StandardAnnotationNullFooProvider mockStandardAnnotationNullFooProvider
    @Mock
    private CustomAnnotationNullFooProvider mockCustomAnnotationNullFooProvider
    @Mock
    private MissingAnnotationNullFooProvider mockMissingAnnotationNullFooProvider
    @Mock
    private SubNullFooProvider mockSubNullFooProvider
    @Mock
    private SubJavadocNullFooProvider mockSubJavadocNullFooProvider

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockStandardAnnotationNullFooProvider, mockCustomAnnotationNullFooProvider,
                mockMissingAnnotationNullFooProvider, mockSubNullFooProvider, mockSubJavadocNullFooProvider)
    }

    @Test
    void testSafeGetFoo() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.safeGetFoo()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testSafeGetFoo_StandardAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.safeGetFoo()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.getFoo()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo_StandardAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockStandardAnnotationNullFooProvider.getFoo()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeGetFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.safeGetFoo1()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo1()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testSafeGetFoo1_CustomAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.safeGetFoo1()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo1()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getFoo1()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo1()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getFoo1()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo1()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testGetOtherFoo1() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getOtherFoo1()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getOtherFoo1()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetOtherFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockCustomAnnotationNullFooProvider.getOtherFoo1()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getOtherFoo1()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeGetFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.safeGetFoo2()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo2()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testSafeGetFoo2_MissingAnnotationNullFooProviderReturnsNull() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.safeGetFoo2()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo2()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getFoo2()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getFoo2()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo2()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testGetOtherFoo2() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getOtherFoo2()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getOtherFoo2()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetOtherFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockMissingAnnotationNullFooProvider.getOtherFoo2()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getOtherFoo2()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeGetFoo3() {
        // Setup
        when(mockSubNullFooProvider.safeGetFoo3()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo3()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testSafeGetFoo3_SubNullFooProviderReturnsNull() {
        // Setup
        when(mockSubNullFooProvider.safeGetFoo3()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo3()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo3() {
        // Setup
        when(mockSubNullFooProvider.getFoo3()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo3()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo3_SubNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockSubNullFooProvider.getFoo3()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo3()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeGetFoo4() {
        // Setup
        when(mockSubJavadocNullFooProvider.safeGetFoo4()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo4()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testSafeGetFoo4_SubJavadocNullFooProviderReturnsNull() {
        // Setup
        when(mockSubJavadocNullFooProvider.safeGetFoo4()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo4()

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo4() {
        // Setup
        when(mockSubJavadocNullFooProvider.getFoo4()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo4()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo4_SubJavadocNullFooProviderThrowsRuntimeException() {
        // Setup
        when(mockSubJavadocNullFooProvider.getFoo4()).thenThrow(RuntimeException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFoo4()
        }).isInstanceOf(RuntimeException.class)
    }
}
