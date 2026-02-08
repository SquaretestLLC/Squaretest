package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testOf() {
        // Run the test
        def result = MyClass.of("value")
        def exceptionSupplier = { val ->
            return new RuntimeException("message")
        }
        assertThat(result.onError(exceptionSupplier)).isEqualTo("result")
        assertThat(result.get()).isEqualTo("result")
        assertThat(result.getError()).isEqualTo(new Exception("message"))
        assertThat(result.isError()).isFalse()
    }

    @Test
    void testOfError() {
        // Run the test
        def result = MyClass.ofError(new Exception("message"))
        def exceptionSupplier = { val ->
            return new RuntimeException("message")
        }
        assertThat(result.onError(exceptionSupplier)).isEqualTo("result")
        assertThat(result.get()).isEqualTo("result")
        assertThat(result.getError()).isEqualTo(new Exception("message"))
        assertThat(result.isError()).isFalse()
    }
}
