package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }
}
