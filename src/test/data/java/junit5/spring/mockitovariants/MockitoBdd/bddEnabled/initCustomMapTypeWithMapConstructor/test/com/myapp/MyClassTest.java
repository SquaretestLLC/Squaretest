package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.MyMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final List<Foo1> foo1s = Arrays.asList(new Foo1(new MyMap<>(new HashMap<>())));
        given(mockFooMaker.makeFoo1()).willReturn(foo1s);

        // Run the test
        final List<Foo1> result = myClassUnderTest.makeFoo1();

        // Verify the results
    }

    @Test
    void testMakeFoo1_FooMakerReturnsNoItems() {
        // Setup
        given(mockFooMaker.makeFoo1()).willReturn(Collections.emptyList());

        // Run the test
        final List<Foo1> result = myClassUnderTest.makeFoo1();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
