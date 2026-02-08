package com.myapp

import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.junit.Assert.assertNull
import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
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
