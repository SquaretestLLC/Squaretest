package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.MyMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final List<Foo1> foo1s = Arrays.asList(new Foo1(new MyMap<>(new HashMap<>())));
        when(mockFooMaker.makeFoo1()).thenReturn(foo1s);

        // Run the test
        final List<Foo1> result = myClassUnderTest.makeFoo1();

        // Verify the results
    }

    @Test
    void testMakeFoo1_FooMakerReturnsNoItems() {
        // Setup
        when(mockFooMaker.makeFoo1()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Foo1> result = myClassUnderTest.makeFoo1();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
