package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertThrows

@CompileStatic
class MyClassTest {

    @Test
    void testGetLowercaseColor1() {
        assert "result" == MyClass.RED.getLowercaseColor()
        assert "result" == MyClass.BLUE.getLowercaseColor()
        assert "result" == MyClass.GREEN.getLowercaseColor()
        assert "result" == MyClass.PURPLE.getLowercaseColor()
    }

    @Test
    void testGetUppercaseColor1() {
        assert "result" == MyClass.RED.getUppercaseColor()
        assert "result" == MyClass.BLUE.getUppercaseColor()
        assert "result" == MyClass.GREEN.getUppercaseColor()
        assert "result" == MyClass.PURPLE.getUppercaseColor()
    }

    @Test
    void testIsGreen1() {
        assertFalse(MyClass.RED.isGreen())
        assertFalse(MyClass.BLUE.isGreen())
        assertFalse(MyClass.GREEN.isGreen())
        assertFalse(MyClass.PURPLE.isGreen())
    }

    @Test
    void testSomethingThatThrows1() {
        assertThrows(IOException.class, {
            MyClass.RED.somethingThatThrows("arg")
        })
    }

    @Test
    void testIsSupported1() {
        assertFalse(MyClass.isSupported("colorName"))
    }

    @Test
    void testConvertTo1() {
        assertThrows(IOException.class, {
            MyClass.convertTo("name")
        })
    }

    @Test
    void testConvertToSafe1() {
        assert "" == MyClass.convertToSafe("name")
    }

    @Test
    void testToString1() {
        assert "result" == MyClass.RED.toString()
        assert "result" == MyClass.BLUE.toString()
        assert "result" == MyClass.GREEN.toString()
        assert "result" == MyClass.PURPLE.toString()
    }
}