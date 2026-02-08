package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testCreateDefault1() {
        // Run the test
        def result = MyClass.createDefault()
        assertThat(result.getFoo1("id")).isEqualTo(new FooData())
        assertThat(result.getDefaultFoo()).isEqualTo(new FooData())
        assertThat(result.getDefaultFooId()).isEqualTo("result")
    }
}