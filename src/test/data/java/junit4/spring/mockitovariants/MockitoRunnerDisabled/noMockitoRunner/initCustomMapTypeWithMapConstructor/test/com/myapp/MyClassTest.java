package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.MyMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @Test
    public void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final List<Foo1> foo1s = Arrays.asList(new Foo1(new MyMap<>(new HashMap<>())));
        when(mockFooMaker.makeFoo1()).thenReturn(foo1s);

        // Run the test
        final List<Foo1> result = myClassUnderTest.makeFoo1();

        // Verify the results
    }

    @Test
    public void testMakeFoo1_FooMakerReturnsNoItems() {
        // Setup
        when(mockFooMaker.makeFoo1()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Foo1> result = myClassUnderTest.makeFoo1();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
