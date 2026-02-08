package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class MyClassTest {

    @Mock
    private FooService mockMainFooService;
    @Mock
    private FooService mockAltFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMainFooService, mockAltFooService);
    }

    @Test
    public void testGetFoo11() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockMainFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }

    @Test
    public void testGetFoo1_MainFooServiceReturnsNull1() {
        // Setup
        when(mockMainFooService.getFoo1("id")).thenReturn(null);

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockAltFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}