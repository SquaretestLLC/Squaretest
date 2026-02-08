package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testCreateNewConnection() {
        assertNull(MyClass.INSTANCE.createNewConnection());
    }

    @Test
    public void testDoSomething() {
        MyClass.INSTANCE.doSomething();
    }
}
