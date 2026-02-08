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
    void testCreateDefault1() {
        // Run the test
        def result = MyClass.createDefault()
        assert new FooData() == result.getFoo1("id")
        assert new FooData() == result.getDefaultFoo()
        assert "result" == result.getDefaultFooId()
    }
}