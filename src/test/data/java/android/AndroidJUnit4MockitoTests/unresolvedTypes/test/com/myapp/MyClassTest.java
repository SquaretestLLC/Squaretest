package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooBar mockFoobar;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoobar, new HashMap<>(), new OtherFooBar[]{});
    }

    @Test
    public void testAddFoo() {
        // Setup
        // Run the test
        myClassUnderTest.addFoo(null);

        // Verify the results
    }

    @Test
    public void testRetrieveFooBar() {
        // Setup
        // Run the test
        final DifferentFooBar result = myClassUnderTest.retrieveFooBar();

        // Verify the results
    }

    @Test
    public void testAddSpecialFoo() {
        // Setup
        final List<? extends OtherFooBars> otherFooBarList = Arrays.asList();

        // Run the test
        myClassUnderTest.addSpecialFoo(otherFooBarList);

        // Verify the results
    }
}
