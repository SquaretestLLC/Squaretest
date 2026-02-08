package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testNormalize() {
        // Setup
        def theIntToNormalize = new MutableInt(0)

        // Run the test
        MyClass.FirstSpace.normalize(theIntToNormalize)
        MyClass.SecondSpace.normalize(theIntToNormalize)
        MyClass.ThirdSpace.normalize(theIntToNormalize)
        MyClass.FourthSpace.normalize(theIntToNormalize)

        // Verify the results
    }

    @Test
    void testNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.normalizeNoArg()
        MyClass.SecondSpace.normalizeNoArg()
        MyClass.ThirdSpace.normalizeNoArg()
        MyClass.FourthSpace.normalizeNoArg()

        // Verify the results
    }

    @Test
    void testTryNormalize() {
        // Setup
        def someInt = new MutableInt(0)

        // Run the test
        MyClass.FirstSpace.tryNormalize(someInt)
        MyClass.SecondSpace.tryNormalize(someInt)
        MyClass.ThirdSpace.tryNormalize(someInt)
        MyClass.FourthSpace.tryNormalize(someInt)

        // Verify the results
    }

    @Test
    void testTryNormalize_ThrowsRuntimeException() {
        assertThatThrownBy({
            MyClass.FirstSpace.tryNormalize(new MutableInt(0))
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.SecondSpace.tryNormalize(new MutableInt(0))
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.ThirdSpace.tryNormalize(new MutableInt(0))
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.FourthSpace.tryNormalize(new MutableInt(0))
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testTryNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.tryNormalizeNoArg()
        MyClass.SecondSpace.tryNormalizeNoArg()
        MyClass.ThirdSpace.tryNormalizeNoArg()
        MyClass.FourthSpace.tryNormalizeNoArg()

        // Verify the results
    }

    @Test
    void testTryNormalizeNoArg_ThrowsRuntimeException() {
        assertThatThrownBy({
            MyClass.FirstSpace.tryNormalizeNoArg()
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.SecondSpace.tryNormalizeNoArg()
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.ThirdSpace.tryNormalizeNoArg()
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.FourthSpace.tryNormalizeNoArg()
        }).isInstanceOf(RuntimeException.class)
    }
}
