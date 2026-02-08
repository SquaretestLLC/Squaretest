package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    @Test
    void testCreateDefault1() {
        // Run the test
        def result = MyClass.createDefault()
        assert new FooData() == result.getFoo1("id")
        assert new FooData() == result.getDefaultFoo()
        assert "result" == result.getDefaultFooId()
    }
}