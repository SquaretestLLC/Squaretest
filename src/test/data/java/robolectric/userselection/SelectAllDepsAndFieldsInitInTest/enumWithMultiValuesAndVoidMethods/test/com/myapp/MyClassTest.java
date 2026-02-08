package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testNormalize() {
        // Setup
        final MutableInt theIntToNormalize = new MutableInt(0);

        // Run the test
        MyClass.FirstSpace.normalize(theIntToNormalize);
        MyClass.SecondSpace.normalize(theIntToNormalize);
        MyClass.ThirdSpace.normalize(theIntToNormalize);
        MyClass.FourthSpace.normalize(theIntToNormalize);

        // Verify the results
    }

    @Test
    public void testNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.normalizeNoArg();
        MyClass.SecondSpace.normalizeNoArg();
        MyClass.ThirdSpace.normalizeNoArg();
        MyClass.FourthSpace.normalizeNoArg();

        // Verify the results
    }

    @Test
    public void testTryNormalize() {
        // Setup
        final MutableInt someInt = new MutableInt(0);

        // Run the test
        MyClass.FirstSpace.tryNormalize(someInt);
        MyClass.SecondSpace.tryNormalize(someInt);
        MyClass.ThirdSpace.tryNormalize(someInt);
        MyClass.FourthSpace.tryNormalize(someInt);

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryNormalize_ThrowsRuntimeException() {
        MyClass.FirstSpace.tryNormalize(new MutableInt(0));
    }

    @Test
    public void testTryNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.tryNormalizeNoArg();
        MyClass.SecondSpace.tryNormalizeNoArg();
        MyClass.ThirdSpace.tryNormalizeNoArg();
        MyClass.FourthSpace.tryNormalizeNoArg();

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryNormalizeNoArg_ThrowsRuntimeException() {
        MyClass.FirstSpace.tryNormalizeNoArg();
    }
}
