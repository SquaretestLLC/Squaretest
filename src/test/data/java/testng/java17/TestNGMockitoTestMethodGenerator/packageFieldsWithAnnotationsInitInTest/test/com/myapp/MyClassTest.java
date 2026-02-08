package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetFooAndBar11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.fooService = mockFooService;
        myClassUnderTest.barService = mockBarService;
        myClassUnderTest.defaultBarId = "defaultBarId";

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData();
        barData.setId("id");
        barData.setName("name");
        when(mockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData);

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}