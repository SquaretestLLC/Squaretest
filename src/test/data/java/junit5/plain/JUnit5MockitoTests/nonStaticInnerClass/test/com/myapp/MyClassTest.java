package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetSubFoo1() {
        // Setup
        // Configure FooService.subFoo(...).
        final FooService.SubFoo mockSubFoo = mock(FooService.SubFoo.class);
        when(mockFooService.subFoo()).thenReturn(mockSubFoo);

        // Run the test
        final String result = myClassUnderTest.getSubFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomethingWithSubFoo() {
        // Setup
        final FooService.SubFoo mockSubFoo = mock(FooService.SubFoo.class);

        // Run the test
        final String result = myClassUnderTest.doSomethingWithSubFoo(mockSubFoo, "id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetInfo1() {
        // Setup
        final BeanWithSubFoo beanWithSubFoo = new BeanWithSubFoo();
        beanWithSubFoo.setBeanId("beanId");
        beanWithSubFoo.setSubFoo(null);

        // Run the test
        final String result = myClassUnderTest.getInfo1(beanWithSubFoo);

        // Verify the results
        assertEquals("beanId", result);
    }
}
