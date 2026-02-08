package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetFooData() {
        // Setup
        when(mockFooService.getFooData(0L)).thenReturn(new FooData("name", 0L))

        // Run the test
        def result = myClassUnderTest.getFooData(0L)

        // Verify the results
    }

    @Test(expected = FileNotFoundException.class)
    void testGetFooData_FooServiceThrowsFileNotFoundException() {
        // Setup
        when(mockFooService.getFooData(0L)).thenThrow(FileNotFoundException.class)

        // Run the test
        myClassUnderTest.getFooData(0L)
    }
}
