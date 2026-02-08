package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;

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
