package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class MyClassTest {

    @Test
    void testCreateNewConnection1() throws Exception {
        assertNull(MyClass.INSTANCE.createNewConnection());
    }

    @Test
    void testDoSomething1() {
        MyClass.INSTANCE.doSomething();
    }
}