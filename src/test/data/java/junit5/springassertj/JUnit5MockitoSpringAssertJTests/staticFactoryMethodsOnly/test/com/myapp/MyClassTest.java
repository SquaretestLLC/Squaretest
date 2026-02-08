package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testOf() {
        // Run the test
        final MyClass<String, Throwable> result = MyClass.of("value");
        final Function<Throwable, ? extends RuntimeException> exceptionSupplier = val -> {
            return new RuntimeException("message");
        };
        assertThat(result.onError(exceptionSupplier)).isEqualTo("result");
        assertThat(result.get()).isEqualTo("result");
        assertThat(result.getError()).isEqualTo(new Exception("message"));
        assertThat(result.isError()).isFalse();
    }

    @Test
    void testOfError() {
        // Run the test
        final MyClass<String, Throwable> result = MyClass.ofError(new Exception("message"));
        final Function<Throwable, ? extends RuntimeException> exceptionSupplier = val -> {
            return new RuntimeException("message");
        };
        assertThat(result.onError(exceptionSupplier)).isEqualTo("result");
        assertThat(result.get()).isEqualTo("result");
        assertThat(result.getError()).isEqualTo(new Exception("message"));
        assertThat(result.isError()).isFalse();
    }
}
