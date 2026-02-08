package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.lang.constant.ClassDesc

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        assertThat(MyClass.INSTANCE.createNewConnection()).isNull()
    }

    @Test
    void testDoSomething1() {
        MyClass.INSTANCE.doSomething()
    }

    @Test
    void testName() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.name()

        // Verify the results
        assertThat(result).isEqualTo("name")
    }

    @Test
    void testOrdinal() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.ordinal()

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testToString() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.toString()

        // Verify the results
        assertThat(result).isEqualTo("name")
    }

    @Test
    void testEquals() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.equals("other")

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testHashCode() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.hashCode()

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCompareTo() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.compareTo(MyClass.INSTANCE)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        assertThatThrownBy({
            MyClass.INSTANCE.compareTo(MyClass.INSTANCE)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        // Run the test
        assertThatThrownBy({
            MyClass.INSTANCE.compareTo(MyClass.INSTANCE)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testGetDeclaringClass() {
        // Setup
        // Run the test
        def result = MyClass.INSTANCE.getDeclaringClass()

        // Verify the results
        assertThat(result).isEqualTo(MyClass.class)
    }

    @Test
    void testDescribeConstable() {
        // Setup
        def expectedResult = Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name"))

        // Run the test
        def result = MyClass.INSTANCE.describeConstable()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.INSTANCE)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(IllegalArgumentException.class)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(NullPointerException.class)
    }
}