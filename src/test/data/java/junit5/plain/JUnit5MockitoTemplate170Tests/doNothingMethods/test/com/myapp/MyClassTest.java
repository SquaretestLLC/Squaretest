package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testStore1() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.store1(fooData);

        // Verify the results
    }

    @Test
    void testStore2() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.store2("fooData");

        // Verify the results
    }

    @Test
    void testStore2_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.store2("fooData"));
    }

    @Test
    void testStore3() {
        // Setup
        final SearchParams searchParams = new SearchParams();
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.store3(searchParams, fooData);

        // Verify the results
    }

    @Test
    void testStore4() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.store4(null, fooData);

        // Verify the results
    }

    @Test
    void testStore5() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        MyClass.store5(fooData);

        // Verify the results
    }

    @Test
    void testStore6() {
        // Setup
        // Run the test
        MyClass.store6("fooData");

        // Verify the results
    }

    @Test
    void testStore7() {
        // Setup
        final SearchParams searchParams = new SearchParams();
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        MyClass.store7(searchParams, fooData);

        // Verify the results
    }

    @Test
    void testStore8() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        MyClass.store8(null, fooData);

        // Verify the results
    }
}
