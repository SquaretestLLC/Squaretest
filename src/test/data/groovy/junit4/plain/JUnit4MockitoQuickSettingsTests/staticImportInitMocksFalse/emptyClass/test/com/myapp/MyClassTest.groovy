package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }
}
