package com.myapp

import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private SubFooService mockSubFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockSubFooService)
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockSubFooService.getData("key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo_SubFooServiceThrowsIOException() {
        // Setup
        when(mockSubFooService.getData("key")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo2() {
        // Setup
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
        when(mockSubFooService.getOtherData("key")).thenThrow(IOException.class)
        when(mockSubFooService.doSomething("key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("key")

        // Verify the results
        assert "result" == result
    }
}
