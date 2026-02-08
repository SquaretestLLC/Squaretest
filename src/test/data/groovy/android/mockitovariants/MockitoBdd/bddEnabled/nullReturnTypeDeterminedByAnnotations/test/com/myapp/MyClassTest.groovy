package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.nullables.*
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.junit.Assert.assertNull
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
        initMocks(this)
        myClassUnderTest = new MyClass(mockStandardAnnotationNullFooProvider, mockCustomAnnotationNullFooProvider,
                mockMissingAnnotationNullFooProvider, mockSubNullFooProvider, mockSubJavadocNullFooProvider)
    }

    @Test
    void testSafeGetFoo() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.safeGetFoo()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testSafeGetFoo_StandardAnnotationNullFooProviderReturnsNull() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.safeGetFoo()).willReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.getFoo()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo_StandardAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockStandardAnnotationNullFooProvider.getFoo()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo()
    }

    @Test
    void testSafeGetFoo1() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.safeGetFoo1()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo1()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testSafeGetFoo1_CustomAnnotationNullFooProviderReturnsNull() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.safeGetFoo1()).willReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo1()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo1() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getFoo1()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo1()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getFoo1()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo1()
    }

    @Test
    void testGetOtherFoo1() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getOtherFoo1()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getOtherFoo1()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetOtherFoo1_CustomAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockCustomAnnotationNullFooProvider.getOtherFoo1()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getOtherFoo1()
    }

    @Test
    void testSafeGetFoo2() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.safeGetFoo2()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo2()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testSafeGetFoo2_MissingAnnotationNullFooProviderReturnsNull() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.safeGetFoo2()).willReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo2()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo2() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getFoo2()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getFoo2()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo2()
    }

    @Test
    void testGetOtherFoo2() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getOtherFoo2()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getOtherFoo2()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetOtherFoo2_MissingAnnotationNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockMissingAnnotationNullFooProvider.getOtherFoo2()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getOtherFoo2()
    }

    @Test
    void testSafeGetFoo3() {
        // Setup
        given(mockSubNullFooProvider.safeGetFoo3()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo3()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testSafeGetFoo3_SubNullFooProviderReturnsNull() {
        // Setup
        given(mockSubNullFooProvider.safeGetFoo3()).willReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo3()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo3() {
        // Setup
        given(mockSubNullFooProvider.getFoo3()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo3()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo3_SubNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockSubNullFooProvider.getFoo3()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo3()
    }

    @Test
    void testSafeGetFoo4() {
        // Setup
        given(mockSubJavadocNullFooProvider.safeGetFoo4()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.safeGetFoo4()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testSafeGetFoo4_SubJavadocNullFooProviderReturnsNull() {
        // Setup
        given(mockSubJavadocNullFooProvider.safeGetFoo4()).willReturn(null)

        // Run the test
        def result = myClassUnderTest.safeGetFoo4()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo4() {
        // Setup
        given(mockSubJavadocNullFooProvider.getFoo4()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo4()

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo4_SubJavadocNullFooProviderThrowsRuntimeException() {
        // Setup
        given(mockSubJavadocNullFooProvider.getFoo4()).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo4()
    }
}
