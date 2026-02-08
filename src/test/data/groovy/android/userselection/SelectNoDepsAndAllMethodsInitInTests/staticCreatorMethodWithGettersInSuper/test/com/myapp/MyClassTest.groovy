package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
