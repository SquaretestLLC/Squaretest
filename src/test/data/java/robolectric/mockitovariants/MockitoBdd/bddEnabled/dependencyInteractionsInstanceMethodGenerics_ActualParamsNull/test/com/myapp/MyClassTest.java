package com.myapp;

import com.myapp.other.GenericInstanceMethodFoo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private GenericInstanceMethodFoo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testDoLoadWithVal() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithVal("key");

        // Verify the results
        then(mockFoo).should().load("key");
    }

    @Test
    public void testDoLoadWithClassAndKey() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithClassAndKey("key");

        // Verify the results
        then(mockFoo).should().load(String.class, "key");
    }

    @Test
    public void testDoLoadWithNull() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithNull("key");

        // Verify the results
        then(mockFoo).should().load(any(T.class));
    }

    @Test
    public void testDoSaveWithNull() {
        // Setup
        // Run the test
        myClassUnderTest.doSaveWithNull("obj");

        // Verify the results
        then(mockFoo).should().save(any(T.class));
    }

    @Test
    public void testDoLoad() {
        // Setup
        // Run the test
        myClassUnderTest.doLoad("key");

        // Verify the results
        then(mockFoo).should().load("key");
    }

    @Test
    public void testDoSave() {
        // Setup
        // Run the test
        myClassUnderTest.doSave("obj");

        // Verify the results
        then(mockFoo).should().save(any(Object.class));
    }
}
