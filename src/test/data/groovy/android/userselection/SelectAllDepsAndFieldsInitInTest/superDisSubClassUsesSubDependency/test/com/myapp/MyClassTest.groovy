package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.junit.Assert.assertNull
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private SubFooService mockSubFooService

    @Before
    void setUp() {
        initMocks(this)
    }

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
