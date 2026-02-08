package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testGetLowercaseColor() {
        assert "result" == MyClass.RED.getLowercaseColor()
        assert "result" == MyClass.BLUE.getLowercaseColor()
        assert "result" == MyClass.GREEN.getLowercaseColor()
        assert "result" == MyClass.PURPLE.getLowercaseColor()
    }

    @Test
    void testGetUppercaseColor() {
        assert "result" == MyClass.RED.getUppercaseColor()
        assert "result" == MyClass.BLUE.getUppercaseColor()
        assert "result" == MyClass.GREEN.getUppercaseColor()
        assert "result" == MyClass.PURPLE.getUppercaseColor()
    }

    @Test
    void testToString() {
        assert "result" == MyClass.RED.toString()
        assert "result" == MyClass.BLUE.toString()
        assert "result" == MyClass.GREEN.toString()
        assert "result" == MyClass.PURPLE.toString()
    }
}
