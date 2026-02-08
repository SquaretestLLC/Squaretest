package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection() {
        assertThat(MyClass.INSTANCE.createNewConnection()).isNull()
    }

    @Test
    void testDoSomething() {
        MyClass.INSTANCE.doSomething()
    }
}
