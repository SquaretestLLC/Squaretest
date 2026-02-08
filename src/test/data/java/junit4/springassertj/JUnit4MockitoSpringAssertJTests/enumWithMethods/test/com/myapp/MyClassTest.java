package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testGetLowercaseColor() {
        assertThat(MyClass.RED.getLowercaseColor()).isEqualTo("result");
        assertThat(MyClass.BLUE.getLowercaseColor()).isEqualTo("result");
        assertThat(MyClass.GREEN.getLowercaseColor()).isEqualTo("result");
        assertThat(MyClass.PURPLE.getLowercaseColor()).isEqualTo("result");
    }

    @Test
    public void testGetUppercaseColor() {
        assertThat(MyClass.RED.getUppercaseColor()).isEqualTo("result");
        assertThat(MyClass.BLUE.getUppercaseColor()).isEqualTo("result");
        assertThat(MyClass.GREEN.getUppercaseColor()).isEqualTo("result");
        assertThat(MyClass.PURPLE.getUppercaseColor()).isEqualTo("result");
    }

    @Test
    public void testIsGreen() {
        assertThat(MyClass.RED.isGreen()).isFalse();
        assertThat(MyClass.BLUE.isGreen()).isFalse();
        assertThat(MyClass.GREEN.isGreen()).isFalse();
        assertThat(MyClass.PURPLE.isGreen()).isFalse();
    }

    @Test
    public void testIsSupported() {
        assertThat(MyClass.isSupported("colorName")).isFalse();
    }

    @Test
    public void testToString() {
        assertThat(MyClass.RED.toString()).isEqualTo("result");
        assertThat(MyClass.BLUE.toString()).isEqualTo("result");
        assertThat(MyClass.GREEN.toString()).isEqualTo("result");
        assertThat(MyClass.PURPLE.toString()).isEqualTo("result");
    }
}
