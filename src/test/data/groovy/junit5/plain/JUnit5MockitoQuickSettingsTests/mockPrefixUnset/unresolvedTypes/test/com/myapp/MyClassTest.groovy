package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooBar foobar

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(foobar, [:], [] as OtherFooBar[])
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
