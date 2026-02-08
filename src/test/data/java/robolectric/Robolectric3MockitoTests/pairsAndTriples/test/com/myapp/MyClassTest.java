package com.myapp;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.net.Socket;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testMakePair() {
        // Setup
        final Pair<String, Integer> expectedResult = Pair.of("left", 0);

        // Run the test
        final Pair<String, Integer> result = myClassUnderTest.makePair();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeImmutablePair() {
        // Setup
        final ImmutablePair<String, Integer> expectedResult = new ImmutablePair<>("test", 0);

        // Run the test
        final ImmutablePair<String, Integer> result = myClassUnderTest.makeImmutablePair();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeMutablePair() {
        // Setup
        final MutablePair<String, Integer> expectedResult = new MutablePair<>("test", 0);

        // Run the test
        final MutablePair<String, Integer> result = myClassUnderTest.makeMutablePair();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMakeTripleWithoutOverridesEquals() {
        // Setup
        // Run the test
        final Triple<String, Integer, Socket> result = myClassUnderTest.makeTripleWithoutOverridesEquals();

        // Verify the results
    }

    @Test
    public void testMakeTripleWithOverridesEquals() {
        // Setup
        final Triple<String, Integer, Double> expectedResult = Triple.of("left", 0, 0.0);

        // Run the test
        final Triple<String, Integer, Double> result = myClassUnderTest.makeTripleWithOverridesEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
