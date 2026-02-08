package com.myapp;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testIsEmpty1() {
        assertThat(MyClass.isEmpty("cs")).isFalse();
    }

    @Test
    public void testTrim1() {
        assertThat(MyClass.trim("str")).isEqualTo("result");
    }

    @Test
    public void testTrimToNull1() {
        assertThat(MyClass.trimToNull("str")).isEqualTo("result");
        assertThat(MyClass.trimToNull("str")).isNull();
    }

    @Test
    public void testTrimToOptional() {
        assertThat(MyClass.trimToOptional("str")).isEqualTo(Optional.of("value"));
        assertThat(MyClass.trimToOptional("str")).isEmpty();
    }
}