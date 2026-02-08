package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNull

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testIsEmpty1() {
        assertFalse(MyClass.isEmpty("cs"))
    }

    @Test
    void testTrim1() {
        assert "result" == MyClass.trim("str")
    }

    @Test
    void testTrimToNull1() {
        assert "result" == MyClass.trimToNull("str")
        assertNull(MyClass.trimToNull("str"))
    }

    @Test
    void testTrimToOptional() {
        assert Optional.of("value") == MyClass.trimToOptional("str")
        assert Optional.empty() == MyClass.trimToOptional("str")
    }
}