package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }
}
