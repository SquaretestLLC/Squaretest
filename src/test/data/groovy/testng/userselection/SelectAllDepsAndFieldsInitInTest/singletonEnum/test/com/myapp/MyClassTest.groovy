package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertNull

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
