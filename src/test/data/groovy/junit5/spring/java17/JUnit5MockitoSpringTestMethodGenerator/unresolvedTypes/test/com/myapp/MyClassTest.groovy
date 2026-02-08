package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooBar mockFoobar

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFoobar, [:], [] as OtherFooBar[])
    }

    @Test
    void testAddFoo1() {
        // Setup
        // Run the test
        myClassUnderTest.addFoo(null)

        // Verify the results
    }

    @Test
    void testRetrieveFooBar1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.retrieveFooBar()

        // Verify the results
    }

    @Test
    void testAddSpecialFoo1() {
        // Setup
        def otherFooBarList = []

        // Run the test
        myClassUnderTest.addSpecialFoo(otherFooBarList)

        // Verify the results
    }
}