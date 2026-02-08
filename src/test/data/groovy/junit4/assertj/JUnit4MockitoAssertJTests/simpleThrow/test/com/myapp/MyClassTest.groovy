package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testGetTheStringg1() {
        assertThatThrownBy({
            myClassUnderTest.getTheStringg1("id")
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testGetTheString2() {
        assertThatThrownBy({
            myClassUnderTest.getTheString2("id")
        }).isInstanceOf(IllegalStateException.class)
    }

    @Test
    void testGetTheString3() {
        assertThat(myClassUnderTest.getTheString3("id")).isEqualTo("id")
    }

    @Test
    void testGetTheString4() {
        assertThatThrownBy({
            myClassUnderTest.getTheString4(new FooData())
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testGetTheString5() {
        assertThatThrownBy({
            myClassUnderTest.getTheString5(new FooData())
        }).isInstanceOf(RuntimeException.class)
    }
}
