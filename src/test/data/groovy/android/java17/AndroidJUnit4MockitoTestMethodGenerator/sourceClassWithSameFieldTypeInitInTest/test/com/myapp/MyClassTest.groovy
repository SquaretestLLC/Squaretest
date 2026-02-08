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
    @Mock
    private MyClass mockOldDataStore

    @Before
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFooById1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockOldDataStore)
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }

    @Test
    void testGetFooById_FooServiceReturnsNull1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockOldDataStore)
        when(mockFooService.getFooById("id")).thenReturn(null)
        when(mockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}