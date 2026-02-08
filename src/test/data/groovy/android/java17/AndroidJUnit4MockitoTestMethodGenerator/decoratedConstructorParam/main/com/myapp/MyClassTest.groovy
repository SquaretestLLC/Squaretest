package com.myapp

import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {
    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
        // TODO: Set the following fields: fooService.
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}
