package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testCreateDefault1() {
        // Run the test
        final MyClass result = MyClass.createDefault();
        assertThat(result.getFoo1("id")).isEqualTo(new FooData());
        assertThat(result.getDefaultFoo()).isEqualTo(new FooData());
        assertThat(result.getDefaultFooId()).isEqualTo("result");
    }
}