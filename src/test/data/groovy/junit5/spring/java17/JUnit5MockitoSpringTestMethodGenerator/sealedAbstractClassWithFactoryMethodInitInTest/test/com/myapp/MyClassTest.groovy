package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

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