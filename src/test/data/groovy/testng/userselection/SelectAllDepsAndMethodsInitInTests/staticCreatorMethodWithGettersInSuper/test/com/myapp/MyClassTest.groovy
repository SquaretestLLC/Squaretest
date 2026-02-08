package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    @Test
    void testFrom() {
        // Setup
        def myData = new MyData("name", 0L, "path")

        // Run the test
        def result = MyClass.from(myData)
        assert "name" == result.getName()
        assert 0L == result.getId()
        assert "path" == result.getPath()
    }

    @Test
    void testGetConstantVal() {
        assert "ignored" == MyClassBase.getConstantVal()
    }
}
