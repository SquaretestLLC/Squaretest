package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertFalse

@CompileStatic
class MyClassTest {

    @Test
    void testAtLeast() {
        assertFalse(MyClass.JAVA_0_9.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_1.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_2.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_3.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_4.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_5.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_6.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_7.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_8.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_9.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_9.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_10.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_11.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_12.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_13.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_14.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_15.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_16.atLeast(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_RECENT.atLeast(MyClass.JAVA_0_9))
    }

    @Test
    void testAtMost() {
        assertFalse(MyClass.JAVA_0_9.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_1.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_2.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_3.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_4.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_5.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_6.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_7.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_8.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_1_9.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_9.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_10.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_11.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_12.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_13.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_14.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_15.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_16.atMost(MyClass.JAVA_0_9))
        assertFalse(MyClass.JAVA_RECENT.atMost(MyClass.JAVA_0_9))
    }

    @Test
    void testGetJavaVersion() {
        assert MyClass.JAVA_0_9 == MyClass.getJavaVersion("nom")
    }

    @Test
    void testGet() {
        assert MyClass.JAVA_0_9 == MyClass.get("nom")
    }

    @Test
    void testToString() {
        assert "name" == MyClass.JAVA_0_9.toString()
        assert "name" == MyClass.JAVA_1_1.toString()
        assert "name" == MyClass.JAVA_1_2.toString()
        assert "name" == MyClass.JAVA_1_3.toString()
        assert "name" == MyClass.JAVA_1_4.toString()
        assert "name" == MyClass.JAVA_1_5.toString()
        assert "name" == MyClass.JAVA_1_6.toString()
        assert "name" == MyClass.JAVA_1_7.toString()
        assert "name" == MyClass.JAVA_1_8.toString()
        assert "name" == MyClass.JAVA_1_9.toString()
        assert "name" == MyClass.JAVA_9.toString()
        assert "name" == MyClass.JAVA_10.toString()
        assert "name" == MyClass.JAVA_11.toString()
        assert "name" == MyClass.JAVA_12.toString()
        assert "name" == MyClass.JAVA_13.toString()
        assert "name" == MyClass.JAVA_14.toString()
        assert "name" == MyClass.JAVA_15.toString()
        assert "name" == MyClass.JAVA_16.toString()
        assert "name" == MyClass.JAVA_RECENT.toString()
    }
}
