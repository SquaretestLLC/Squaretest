package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testMakeBean1() {
        // Setup
        // Configure Foo.makeBean1(...).
        final Bean1 bean1 = new Bean1();
        bean1.setName("name");
        final Bean2 bean2 = new Bean2();
        bean2.setName("name");
        bean1.setBean2(bean2);
        when(mockFoo.makeBean1()).thenReturn(bean1);

        // Run the test
        final Bean1 result = myClassUnderTest.makeBean1();

        // Verify the results
    }
}
