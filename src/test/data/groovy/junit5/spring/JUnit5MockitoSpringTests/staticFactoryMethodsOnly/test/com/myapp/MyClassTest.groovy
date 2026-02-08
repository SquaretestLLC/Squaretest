package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse

@CompileStatic
class MyClassTest {

    @Test
    void testOf() {
        // Run the test
        def result = MyClass.of("value")
        def exceptionSupplier = { val ->
            return new RuntimeException("message")
        }
        assert "result" == result.onError(exceptionSupplier)
        assert "result" == result.get()
        assert new Exception("message") == result.getError()
        assertFalse(result.isError())
    }

    @Test
    void testOfError() {
        // Run the test
        def result = MyClass.ofError(new Exception("message"))
        def exceptionSupplier = { val ->
            return new RuntimeException("message")
        }
        assert "result" == result.onError(exceptionSupplier)
        assert "result" == result.get()
        assert new Exception("message") == result.getError()
        assertFalse(result.isError())
    }
}
