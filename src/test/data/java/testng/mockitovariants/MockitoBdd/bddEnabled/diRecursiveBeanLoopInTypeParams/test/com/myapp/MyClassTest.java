package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    public void testLoadBean1() {
        // Setup
        // Configure FooCreator.loadBean1(...).
        final Bean1 bean1 = new Bean1();
        bean1.setName("name");
        final Bean2 bean2 = new Bean2();
        bean2.setBean2Name("bean2Name");
        bean2.setBean1List(Arrays.asList(new Bean1()));
        bean1.setBean2List(Arrays.asList(bean2));
        given(mockFooCreator.loadBean1()).willReturn(bean1);

        // Run the test
        final Bean1 result = myClassUnderTest.loadBean1();

        // Verify the results
    }

    @Test
    public void testLoadAllBean1s() {
        // Setup
        // Configure FooCreator.loadAllBean1s(...).
        final Bean1 bean1 = new Bean1();
        bean1.setName("name");
        final Bean2 bean2 = new Bean2();
        bean2.setBean2Name("bean2Name");
        bean2.setBean1List(Arrays.asList(new Bean1()));
        bean1.setBean2List(Arrays.asList(bean2));
        final List<Bean1> bean1s = Arrays.asList(bean1);
        given(mockFooCreator.loadAllBean1s()).willReturn(bean1s);

        // Run the test
        final List<Bean1> result = myClassUnderTest.loadAllBean1s();

        // Verify the results
    }

    @Test
    public void testLoadAllBean1s_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.loadAllBean1s()).willReturn(Collections.emptyList());

        // Run the test
        final List<Bean1> result = myClassUnderTest.loadAllBean1s();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
