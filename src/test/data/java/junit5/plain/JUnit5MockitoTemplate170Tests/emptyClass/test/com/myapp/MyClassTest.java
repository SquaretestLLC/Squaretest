package com.myapp;

import org.junit.jupiter.api.BeforeEach;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }
}
