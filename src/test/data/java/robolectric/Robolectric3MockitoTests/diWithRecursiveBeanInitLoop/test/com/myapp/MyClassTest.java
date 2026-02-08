package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
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
