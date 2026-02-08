package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testFrom() {
        // Setup
        def myData = new MyData("name", 0L, "path")

        // Run the test
        def result = MyClass.from(myData)
        assertThat(result.getName()).isEqualTo("name")
        assertThat(result.getId()).isEqualTo(0L)
        assertThat(result.getPath()).isEqualTo("path")
    }
}
