package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testCreateDefault1() {
        // Run the test
        final MyClass result = MyClass.createDefault();
        assertThat(result.getFoo1("id")).isEqualTo(new FooData());
        assertThat(result.getDefaultFoo()).isEqualTo(new FooData());
        assertThat(result.getDefaultFooId()).isEqualTo("result");
    }
}