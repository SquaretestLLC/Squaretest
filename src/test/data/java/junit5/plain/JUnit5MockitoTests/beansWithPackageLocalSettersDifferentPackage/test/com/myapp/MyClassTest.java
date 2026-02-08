package com.myapp;

import com.myapp.other.*;
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
    void testGetFoo1() {
        // Setup
        final BeanWithAllPackageLocalMethods expectedResult = null;
        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        final BeanWithAllPackageLocalMethods result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo1() {
        // Setup
        when(mockFooService.putFoo1(
                BeanWithAllPackageLocalMethodsAndWithMethods.makeBeanWithAllPackageLocalMethodsAndWithMethods(
                        "serialized"))).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.putFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockFooService.getFoo2("id")).thenReturn(null);

        // Run the test
        final DataWithPackageLocalFactoryMethod result = myClassUnderTest.getFoo2("id");

        // Verify the results
    }

    @Test
    void testGetFoo3() {
        // Setup
        when(mockFooService.getFoo3("id")).thenReturn(null);

        // Run the test
        final DataWithPackageLocalGenericFactoryMethod<String> result = myClassUnderTest.getFoo3("id");

        // Verify the results
    }

    @Test
    void testGetFoo4() {
        // Setup
        when(mockFooService.getFoo4("id")).thenReturn(null);

        // Run the test
        final DataWithPackageLocalConstructor result = myClassUnderTest.getFoo4("id");

        // Verify the results
    }

    @Test
    void testGetFoo5() {
        // Setup
        when(mockFooService.getFoo5("id")).thenReturn(null);

        // Run the test
        final DataWithPackageLocalGenericConstructor<String> result = myClassUnderTest.getFoo5("id");

        // Verify the results
    }

    @Test
    void testGetFoo6() {
        // Setup
        // Configure FooService.getFoo6(...).
        final DataWithGenericAndBothConstructors<String> stringDataWithGenericAndBothConstructors = new DataWithGenericAndBothConstructors<>();
        stringDataWithGenericAndBothConstructors.setPayload("value");
        when(mockFooService.getFoo6("id")).thenReturn(stringDataWithGenericAndBothConstructors);

        // Run the test
        final DataWithGenericAndBothConstructors<String> result = myClassUnderTest.getFoo6("id");

        // Verify the results
    }
}
