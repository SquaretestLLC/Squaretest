package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

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
}
