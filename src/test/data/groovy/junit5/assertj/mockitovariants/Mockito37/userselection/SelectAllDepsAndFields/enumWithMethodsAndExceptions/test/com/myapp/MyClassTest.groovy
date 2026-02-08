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
    void testSomethingThatThrows() {
        assertThatThrownBy({
            MyClass.RED.somethingThatThrows("arg")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testIsSupported() {
        assertThat(MyClass.isSupported("colorName")).isFalse()
    }

    @Test
    void testConvertTo() {
        assertThatThrownBy({
            MyClass.convertTo("name")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testToString() {
        assertThat(MyClass.RED.toString()).isEqualTo("result")
        assertThat(MyClass.BLUE.toString()).isEqualTo("result")
        assertThat(MyClass.GREEN.toString()).isEqualTo("result")
        assertThat(MyClass.PURPLE.toString()).isEqualTo("result")
    }
}
