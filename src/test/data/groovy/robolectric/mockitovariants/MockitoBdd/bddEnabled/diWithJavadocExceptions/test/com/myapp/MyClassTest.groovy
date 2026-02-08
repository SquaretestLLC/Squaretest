package com.myapp

import com.myapp.javadocthrows.FooServiceException
import com.myapp.javadocthrows.OtherException
import com.myapp.javadocthrows.ThrowingFooProvider
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private ThrowingFooProvider mockFooProvider

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooProvider)
    }

    @Test
    void testGetFooFromPlace() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getFooFromPlace("key")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFooFromPlace("key")
    }

    @Test
    void testGetBar() {
        // Setup
        given(mockFooProvider.getBar("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = FooServiceException.class)
    void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getBar("barName")
    }

    @Test
    void testGetBar1() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar1("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = FooServiceException.class)
    void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getBar1("barName")
    }

    @Test
    void testGetBar2() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar2("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.getBar2("barName")
    }

    @Test
    void testGetBar3() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar3("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.getBar3("barName")
    }

    @Test(expected = FooServiceException.class)
    void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getBar3("barName")
    }

    @Test
    void testTryGetBarWithDupExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithDupExceptions("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName")
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions1() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions2() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions3() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions4() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions5() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions6() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName")
    }
}
