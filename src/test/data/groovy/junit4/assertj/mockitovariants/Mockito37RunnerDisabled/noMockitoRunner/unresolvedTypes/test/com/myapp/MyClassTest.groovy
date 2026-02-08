package com.myapp

import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooBar mockFoobar

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoobar, [:], [] as OtherFooBar[])
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testAddFoo() {
        // Setup
        // Run the test
        myClassUnderTest.addFoo(null)

        // Verify the results
    }

    @Test
    void testRetrieveFooBar() {
        // Setup
        // Run the test
        def result = myClassUnderTest.retrieveFooBar()

        // Verify the results
    }

    @Test
    void testAddSpecialFoo() {
        // Setup
        def otherFooBarList = []

        // Run the test
        myClassUnderTest.addSpecialFoo(otherFooBarList)

        // Verify the results
    }
}
