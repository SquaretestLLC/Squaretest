package com.myapp;

import com.myapp.other.GenericInstanceMethodFoo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private GenericInstanceMethodFoo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testDoLoadWithVal() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithVal("key");

        // Verify the results
        verify(mockFoo).load("key");
    }

    @Test
    public void testDoLoadWithClassAndKey() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithClassAndKey("key");

        // Verify the results
        verify(mockFoo).load(String.class, "key");
    }

    @Test
    public void testDoLoadWithNull() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithNull("key");

        // Verify the results
        verify(mockFoo).load(any(T.class));
    }

    @Test
    public void testDoSaveWithNull() {
        // Setup
        // Run the test
        myClassUnderTest.doSaveWithNull("obj");

        // Verify the results
        verify(mockFoo).save(any(T.class));
    }

    @Test
    public void testDoLoad() {
        // Setup
        // Run the test
        myClassUnderTest.doLoad("key");

        // Verify the results
        verify(mockFoo).load("key");
    }

    @Test
    public void testDoSave() {
        // Setup
        // Run the test
        myClassUnderTest.doSave("obj");

        // Verify the results
        verify(mockFoo).save(any(Object.class));
    }
}
