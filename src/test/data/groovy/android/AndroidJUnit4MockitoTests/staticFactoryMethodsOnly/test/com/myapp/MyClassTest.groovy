package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
