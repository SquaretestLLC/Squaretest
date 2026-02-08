package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @Test
    public void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final Foo1 foo1 = new Foo1();
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        simpleBean1.setMyOtherId(0L);
        simpleBean1.setMyLastName("myLastName");
        foo1.setSimpleBean1(simpleBean1);
        given(mockFooMaker.makeFoo1()).willReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
