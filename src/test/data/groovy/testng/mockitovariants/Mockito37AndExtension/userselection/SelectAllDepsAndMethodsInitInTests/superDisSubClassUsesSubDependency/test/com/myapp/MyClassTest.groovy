package com.myapp

import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.testng.Assert.assertNull

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private SubFooService mockSubFooService

    @Test
    void testGetFoo() {
        // Setup
        def myClassUnderTest = new MyClass(mockSubFooService)
        when(mockSubFooService.getData("key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo_SubFooServiceThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockSubFooService)
        when(mockSubFooService.getData("key")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(mockSubFooService)
        when(mockSubFooService.getOtherData("key")).thenReturn("result")
        when(mockSubFooService.doSomething("key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("key")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo2_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockSubFooService)
        when(mockSubFooService.getOtherData("key")).thenThrow(IOException.class)
        when(mockSubFooService.doSomething("key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("key")

        // Verify the results
        assert "result" == result
    }
}
