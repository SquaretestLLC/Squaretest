package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assertThat(myClassUnderTest.getKey()).isEqualTo(left)
    }

    @Test
    void testGetValue() {
        def myClassUnderTest = new MyClass<>()
        assertThat(myClassUnderTest.getValue()).isEqualTo("result")
    }

    @Test
    void testSetValue() {
        def myClassUnderTest = new MyClass<>()
        assertThat(myClassUnderTest.setValue("setValueParam")).isEqualTo("result")
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(UnsupportedOperationException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(IllegalArgumentException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(IllegalStateException.class)
    }

    @Test
    void testComparingByKey1() {
        // Setup
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByKey()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testComparingByValue1() {
        // Setup
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByValue()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testComparingByKey2() {
        // Setup
        def cmp = Comparator.comparing(Object.&toString)
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByKey(cmp)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testComparingByValue2() {
        // Setup
        def cmp = Comparator.comparing(Object.&toString)
        def expectedResult = Comparator.comparing(Object.&toString)

        // Run the test
        def result = Map.Entry.comparingByValue(cmp)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }
}
