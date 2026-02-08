package com.myapp

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private FooService mockMainFooService
    @Mock
    private FooService mockAltFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockMainFooService, mockAltFooService)
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockMainFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }

    @Test
    void testGetFoo1_MainFooServiceReturnsNull() {
        // Setup
        when(mockMainFooService.getFoo1("id")).thenReturn(null)

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockAltFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}
