package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.tuple.ImmutablePair
import org.apache.commons.lang3.tuple.MutablePair
import org.apache.commons.lang3.tuple.Pair
import org.apache.commons.lang3.tuple.Triple
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testMakePair() {
        // Setup
        def expectedResult = Pair.of("left", 0)

        // Run the test
        def result = myClassUnderTest.makePair()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeImmutablePair() {
        // Setup
        def expectedResult = new ImmutablePair<>("test", 0)

        // Run the test
        def result = myClassUnderTest.makeImmutablePair()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeMutablePair() {
        // Setup
        def expectedResult = new MutablePair<>("test", 0)

        // Run the test
        def result = myClassUnderTest.makeMutablePair()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testMakeTripleWithoutOverridesEquals() {
        // Setup
        // Run the test
        def result = myClassUnderTest.makeTripleWithoutOverridesEquals()

        // Verify the results
    }

    @Test
    void testMakeTripleWithOverridesEquals() {
        // Setup
        def expectedResult = Triple.of("left", 0, 0.0d)

        // Run the test
        def result = myClassUnderTest.makeTripleWithOverridesEquals()

        // Verify the results
        assert expectedResult == result
    }
}
