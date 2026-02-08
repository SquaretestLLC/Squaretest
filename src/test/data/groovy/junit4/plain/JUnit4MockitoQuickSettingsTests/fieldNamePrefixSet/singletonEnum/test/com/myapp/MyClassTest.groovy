package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.junit.Assert.assertNull

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
