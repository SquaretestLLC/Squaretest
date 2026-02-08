package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertFalse
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Test
    void testIsEmpty1() {
        assertFalse(MyClass.isEmpty("cs"))
    }

    @Test
    void testTrim1() {
        assert "result" == MyClass.trim("str")
    }

    @Test
    void testTrimToNull1() {
        assert "result" == MyClass.trimToNull("str")
        assertNull(MyClass.trimToNull("str"))
    }

    @Test
    void testTrimToOptional() {
        assert Optional.of("value") == MyClass.trimToOptional("str")
        assert Optional.empty() == MyClass.trimToOptional("str")
    }
}