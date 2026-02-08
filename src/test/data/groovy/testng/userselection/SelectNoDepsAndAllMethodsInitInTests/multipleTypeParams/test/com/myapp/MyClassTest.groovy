package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assert left == myClassUnderTest.getKey()
    }

    @Test
    void testGetValue() {
        def myClassUnderTest = new MyClass<>()
        assert "result" == myClassUnderTest.getValue()
    }

    @Test
    void testSetValue() {
        def myClassUnderTest = new MyClass<>()
        assert "result" == myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testSetValue_ThrowsUnsupportedOperationException() {
        def myClassUnderTest = new MyClass<>()
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testSetValue_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>()
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testSetValue_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>()
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [IllegalArgumentException.class])
    void testSetValue_ThrowsIllegalArgumentException() {
        def myClassUnderTest = new MyClass<>()
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [IllegalStateException.class])
    void testSetValue_ThrowsIllegalStateException() {
        def myClassUnderTest = new MyClass<>()
        myClassUnderTest.setValue("setValueParam")
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
