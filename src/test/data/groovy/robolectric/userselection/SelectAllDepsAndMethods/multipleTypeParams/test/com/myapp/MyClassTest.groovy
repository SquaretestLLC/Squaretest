package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass<String, String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testLeftGetterAndSetter() {
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assert left == myClassUnderTest.getKey()
    }

    @Test
    void testGetValue() {
        assert "result" == myClassUnderTest.getValue()
    }

    @Test
    void testSetValue() {
        assert "result" == myClassUnderTest.setValue("setValueParam")
    }

    @Test
    void testComparingByKey1() {
        // Setup
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByKey()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testComparingByValue1() {
        // Setup
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByValue()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testComparingByKey2() {
        // Setup
        def cmp = Comparator.comparing(Object.&toString)
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByKey(cmp)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testComparingByValue2() {
        // Setup
        def cmp = Comparator.comparing(Object.&toString)
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByValue(cmp)

        // Verify the results
        assert expectedResult == result
    }
}
