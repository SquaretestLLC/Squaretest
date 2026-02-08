package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testGetLowercaseColor() {
        assertThat(MyClass.RED.getLowercaseColor()).isEqualTo("result")
        assertThat(MyClass.BLUE.getLowercaseColor()).isEqualTo("result")
        assertThat(MyClass.GREEN.getLowercaseColor()).isEqualTo("result")
        assertThat(MyClass.PURPLE.getLowercaseColor()).isEqualTo("result")
    }

    @Test
    void testGetUppercaseColor() {
        assertThat(MyClass.RED.getUppercaseColor()).isEqualTo("result")
        assertThat(MyClass.BLUE.getUppercaseColor()).isEqualTo("result")
        assertThat(MyClass.GREEN.getUppercaseColor()).isEqualTo("result")
        assertThat(MyClass.PURPLE.getUppercaseColor()).isEqualTo("result")
    }

    @Test
    void testIsGreen() {
        assertThat(MyClass.RED.isGreen()).isFalse()
        assertThat(MyClass.BLUE.isGreen()).isFalse()
        assertThat(MyClass.GREEN.isGreen()).isFalse()
        assertThat(MyClass.PURPLE.isGreen()).isFalse()
    }

    @Test
    void testIsSupported() {
        assertThat(MyClass.isSupported("colorName")).isFalse()
    }

    @Test
    void testToString() {
        assertThat(MyClass.RED.toString()).isEqualTo("result")
        assertThat(MyClass.BLUE.toString()).isEqualTo("result")
        assertThat(MyClass.GREEN.toString()).isEqualTo("result")
        assertThat(MyClass.PURPLE.toString()).isEqualTo("result")
    }

    @Test
    void testName() {
        assertThat(MyClass.RED.name()).isEqualTo("name")
        assertThat(MyClass.BLUE.name()).isEqualTo("name")
        assertThat(MyClass.GREEN.name()).isEqualTo("name")
        assertThat(MyClass.PURPLE.name()).isEqualTo("name")
    }

    @Test
    void testOrdinal() {
        assertThat(MyClass.RED.ordinal()).isEqualTo(0)
        assertThat(MyClass.BLUE.ordinal()).isEqualTo(0)
        assertThat(MyClass.GREEN.ordinal()).isEqualTo(0)
        assertThat(MyClass.PURPLE.ordinal()).isEqualTo(0)
    }

    @Test
    void testEquals() {
        assertThat(MyClass.RED.equals("other")).isFalse()
        assertThat(MyClass.BLUE.equals("other")).isFalse()
        assertThat(MyClass.GREEN.equals("other")).isFalse()
        assertThat(MyClass.PURPLE.equals("other")).isFalse()
    }

    @Test
    void testHashCode() {
        assertThat(MyClass.RED.hashCode()).isEqualTo(0)
        assertThat(MyClass.BLUE.hashCode()).isEqualTo(0)
        assertThat(MyClass.GREEN.hashCode()).isEqualTo(0)
        assertThat(MyClass.PURPLE.hashCode()).isEqualTo(0)
    }

    @Test
    void testCompareTo() {
        assertThat(MyClass.RED.compareTo(MyClass.RED)).isEqualTo(0)
        assertThat(MyClass.BLUE.compareTo(MyClass.RED)).isEqualTo(0)
        assertThat(MyClass.GREEN.compareTo(MyClass.RED)).isEqualTo(0)
        assertThat(MyClass.PURPLE.compareTo(MyClass.RED)).isEqualTo(0)
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThatThrownBy({
            MyClass.RED.compareTo(MyClass.RED)
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            MyClass.BLUE.compareTo(MyClass.RED)
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            MyClass.GREEN.compareTo(MyClass.RED)
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            MyClass.PURPLE.compareTo(MyClass.RED)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThatThrownBy({
            MyClass.RED.compareTo(MyClass.RED)
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            MyClass.BLUE.compareTo(MyClass.RED)
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            MyClass.GREEN.compareTo(MyClass.RED)
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            MyClass.PURPLE.compareTo(MyClass.RED)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testGetDeclaringClass() {
        assertThat(MyClass.RED.getDeclaringClass()).isEqualTo(MyClass.class)
        assertThat(MyClass.BLUE.getDeclaringClass()).isEqualTo(MyClass.class)
        assertThat(MyClass.GREEN.getDeclaringClass()).isEqualTo(MyClass.class)
        assertThat(MyClass.PURPLE.getDeclaringClass()).isEqualTo(MyClass.class)
    }

    @Test
    void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.RED)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(IllegalArgumentException.class)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(NullPointerException.class)
    }
}
