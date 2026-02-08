package com.myapp

import com.myapp.javadocthrows.FooServiceException
import com.myapp.javadocthrows.OtherException
import com.myapp.javadocthrows.ThrowingFooProvider
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
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
        when(mockFooProvider.getFooFromPlace("key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFooFromPlace("key")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = RuntimeException.class)
    void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        when(mockFooProvider.getFooFromPlace("key")).thenThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFooFromPlace("key")
    }

    @Test
    void testGetBar() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = FooServiceException.class)
    void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getBar("barName")
    }

    @Test
    void testGetBar1() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar1("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = FooServiceException.class)
    void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getBar1("barName")
    }

    @Test
    void testGetBar2() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar2("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.getBar2("barName")
    }

    @Test
    void testGetBar3() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getBar3("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.getBar3("barName")
    }

    @Test(expected = FooServiceException.class)
    void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getBar3("barName")
    }

    @Test
    void testTryGetBarWithDupExceptions() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithDupExceptions("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName")
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithDupExceptions("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions1() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions1("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions2() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions2("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions3() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions3("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions4() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions4("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions5() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions5("barName")
    }

    @Test
    void testTryGetBarWithTwoExceptions6() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = OtherException.class)
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName")
    }

    @Test(expected = FooServiceException.class)
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.tryGetBarWithTwoExceptions6("barName")
    }
}
