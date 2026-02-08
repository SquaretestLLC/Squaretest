package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertNull

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection() {
        assertNull(MyClass.INSTANCE.createNewConnection())
    }

    @Test
    void testDoSomething() {
        MyClass.INSTANCE.doSomething()
    }
}
