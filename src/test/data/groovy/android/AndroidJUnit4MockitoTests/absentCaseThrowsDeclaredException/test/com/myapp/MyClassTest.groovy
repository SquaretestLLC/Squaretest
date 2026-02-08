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
    void testGetData() {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.of(new FooData(0L, "name")))

        // Run the test
        def result = myClassUnderTest.getData(0L)

        // Verify the results
    }

    @Test(expected = FooException.class)
    void testGetData_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getData(0L)
    }
}
