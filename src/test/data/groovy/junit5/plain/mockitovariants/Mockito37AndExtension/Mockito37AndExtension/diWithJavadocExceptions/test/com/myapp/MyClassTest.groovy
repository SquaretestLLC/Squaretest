package com.myapp

import com.myapp.javadocthrows.FooServiceException
import com.myapp.javadocthrows.OtherException
import com.myapp.javadocthrows.ThrowingFooProvider
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.when

@CompileStatic
@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private ThrowingFooProvider mockFooProvider

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
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

    @Test
    void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        when(mockFooProvider.getFooFromPlace("key")).thenThrow(RuntimeException.class)

        // Run the test
        assertThrows(RuntimeException.class, {
            myClassUnderTest.getFooFromPlace("key")
        })
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

    @Test
    void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getBar("barName")
        })
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

    @Test
    void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getBar1("barName")
        })
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

    @Test
    void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.getBar2("barName")
        })
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

    @Test
    void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.getBar3("barName")
        })
    }

    @Test
    void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getBar3("barName")
        })
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

    @Test
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithDupExceptions("barName")
        })
    }

    @Test
    void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithDupExceptions("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions1("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions1("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions2("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions2("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions3("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions3("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions4("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions4("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions5("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions5("barName")
        })
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

    @Test
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class)

        // Run the test
        assertThrows(OtherException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions6("barName")
        })
    }

    @Test
    void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.tryGetBarWithTwoExceptions6("barName")
        })
    }
}
