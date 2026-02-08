package com.myapp


import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private FooService theMockFooService
    @Mock
    private MyClass theMockOldDataStore

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(theMockFooService, theMockOldDataStore)
    }

    @Test
    void testGetFooById1() {
        // Setup
        when(theMockFooService.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }

    @Test
    void testGetFooById_FooServiceReturnsNull1() {
        // Setup
        when(theMockFooService.getFooById("id")).thenReturn(null)
        when(theMockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}