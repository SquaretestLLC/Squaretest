package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertNull

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        assertNull(MyClass.INSTANCE.createNewConnection())
    }

    @Test
    void testDoSomething1() {
        MyClass.INSTANCE.doSomething()
    }
}