package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertNull

@CompileStatic
class MyClassTest {

    @Test
    void testIsEmpty() {
        assertFalse(MyClass.isEmpty("cs"))
    }

    @Test
    void testTrim() {
        assert "result" == MyClass.trim("str")
    }

    @Test
    void testTrimToNull() {
        assert "result" == MyClass.trimToNull("str")
        assertNull(MyClass.trimToNull("str"))
    }

    @Test
    void testTrimToOptional() {
        assert Optional.of("value") == MyClass.trimToOptional("str")
        assert Optional.empty() == MyClass.trimToOptional("str")
    }
}
