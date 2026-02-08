package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testGetFooData() {
        // Setup
        // Configure Foo.getData(...).
        def fooData = new FooData("purchaseId", "licenseName", new OtherData("dataName"))
        when(mockFoo.getData("name")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData("name")

        // Verify the results
    }
}
