package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MyClassTest {

    @Test
    public void testCreateNewConnection() throws Exception {
        assertNull(MyClass.INSTANCE.createNewConnection());
    }

    @Test
    public void testDoSomething() {
        MyClass.INSTANCE.doSomething();
    }
}
