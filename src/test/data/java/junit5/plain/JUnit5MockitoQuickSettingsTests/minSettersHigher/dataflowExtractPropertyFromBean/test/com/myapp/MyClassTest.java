package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetFoo() {
        // Setup
        // Configure FooService.getFoo(...).
        final FooDTO fooDTO = new FooDTO();
        fooDTO.setFooDTOName("fooDTOName");
        final SubFoo1 subFoo1 = new SubFoo1();
        subFoo1.setSubFoo1Name("subFoo1Name");
        final SubFoo2 subFoo2 = new SubFoo2();
        subFoo2.setSubFoo2Name("subFoo2Name");
        subFoo2.setSubFoo2ValueWeCareAbout("subFoo2ValueWeCareAbout");
        subFoo1.setSubFoo2(subFoo2);
        fooDTO.setSubFoo1(subFoo1);
        when(mockFooService.getFoo("fooId")).thenReturn(fooDTO);

        // Run the test
        final String result = myClassUnderTest.getFoo("fooId");

        // Verify the results
        assertEquals("subFoo2ValueWeCareAbout", result);
    }

    @Test
    void testGetValue1() {
        // Setup
        when(mockFooService.getValue1(MyClass.ConstantValue)).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getValue1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetValue2() {
        // Setup
        when(mockFooService.getValue2("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getValue2();

        // Verify the results
        assertEquals("result", result);
    }
}
