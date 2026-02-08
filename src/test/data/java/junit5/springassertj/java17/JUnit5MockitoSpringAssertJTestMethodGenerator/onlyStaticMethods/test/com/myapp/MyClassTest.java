package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testIsEmpty1() {
        assertThat(MyClass.isEmpty("cs")).isFalse();
    }

    @Test
    void testTrim1() {
        assertThat(MyClass.trim("str")).isEqualTo("result");
    }

    @Test
    void testTrimToNull1() {
        assertThat(MyClass.trimToNull("str")).isEqualTo("result");
        assertThat(MyClass.trimToNull("str")).isNull();
    }

    @Test
    void testTrimToOptional() {
        assertThat(MyClass.trimToOptional("str")).isEqualTo(Optional.of("value"));
        assertThat(MyClass.trimToOptional("str")).isEmpty();
    }
}