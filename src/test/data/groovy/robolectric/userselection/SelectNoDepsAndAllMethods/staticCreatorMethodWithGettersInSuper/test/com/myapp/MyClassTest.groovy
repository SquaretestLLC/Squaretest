package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
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
