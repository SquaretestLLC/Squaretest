package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService theMockFooService;
    @Mock
    private FooService theMockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(theMockFooService);
        // TODO: Set the following fields: fooService.
    }

    @Test
    void testGetFoo11() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(theMockFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}