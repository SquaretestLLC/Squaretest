package com.myapp;

import com.myapp.nullables.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

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
        initMocks(this);
        myClassUnderTest = new MyClass(mockStandardAnnotationNullFooProvider, mockCustomAnnotationNullFooProvider,
                mockMissingAnnotationNullFooProvider, mockSubNullFooProvider, mockSubJavadocNullFooProvider);
    }

    @Test
    void testSafeGetFoo() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.safeGetFoo()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testSafeGetFoo_StandardAnnotationNullFooProviderReturnsNull() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.safeGetFoo()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.getFoo()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo_StandardAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.getFoo()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeGetFoo1() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.safeGetFoo1()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testSafeGetFoo1_CustomAnnotationNullFooProviderReturnsNull() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.safeGetFoo1()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo1() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getFoo1()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getFoo1()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo1()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testGetOtherFoo1() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getOtherFoo1()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherFoo1();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetOtherFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getOtherFoo1()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherFoo1()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeGetFoo2() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.safeGetFoo2()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo2();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testSafeGetFoo2_MissingAnnotationNullFooProviderReturnsNull() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.safeGetFoo2()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo2();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo2() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getFoo2()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getFoo2()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo2()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testGetOtherFoo2() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getOtherFoo2()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherFoo2();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetOtherFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getOtherFoo2()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getOtherFoo2()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeGetFoo3() {
        // Setup
        given(mockSubNullFooProvider.safeGetFoo3()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo3();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testSafeGetFoo3_SubNullFooProviderReturnsNull() {
        // Setup
        given(mockSubNullFooProvider.safeGetFoo3()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo3();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo3() {
        // Setup
        given(mockSubNullFooProvider.getFoo3()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo3_SubNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockSubNullFooProvider.getFoo3()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo3()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeGetFoo4() {
        // Setup
        given(mockSubJavadocNullFooProvider.safeGetFoo4()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo4();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testSafeGetFoo4_SubJavadocNullFooProviderReturnsNull() {
        // Setup
        given(mockSubJavadocNullFooProvider.safeGetFoo4()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.safeGetFoo4();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo4() {
        // Setup
        given(mockSubJavadocNullFooProvider.getFoo4()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo4_SubJavadocNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockSubJavadocNullFooProvider.getFoo4()).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo4()).isInstanceOf(RuntimeException.class);
    }
}
