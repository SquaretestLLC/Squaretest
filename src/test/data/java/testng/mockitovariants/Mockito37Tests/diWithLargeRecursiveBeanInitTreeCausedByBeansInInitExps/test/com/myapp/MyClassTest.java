package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeMethod
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final Foo1 foo1 = new Foo1();
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        simpleBean.setMyOtherId(0L);
        simpleBean.setMyLastName("myLastName");
        foo1.setSimpleBean1(Arrays.asList(simpleBean));
        when(mockFooMaker.makeFoo1()).thenReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
