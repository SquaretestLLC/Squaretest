package com.myapp


import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
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