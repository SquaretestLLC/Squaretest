package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;

    @InjectMocks
    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        // TODO: Set the following fields: configBean, defaultFooId, specialValuesMap.
    }

    @Test
    public void testGetFooAndBar1() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData();
        barData.setId("id");
        barData.setName("name");
        when(mockBarService.getDefaultBar1("defaultFooId")).thenReturn(barData);

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }

    @Test
    public void testGetDefaultFoo1() {
        // Setup
        // Configure FooService.getDefaultFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getDefaultFoo1("defaultFooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getDefaultFoo1();

        // Verify the results
    }

    @Test
    public void testGetSpecialFoo1() {
        // Setup
        // Configure FooService.getSpecialFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getSpecialFoo1("defaultFooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getSpecialFoo1();

        // Verify the results
    }

    @Test
    public void testGetSpecialFoo2() {
        // Setup
        // Configure FooService.getSpecialFoo2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getSpecialFoo2("specialPath")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getSpecialFoo2();

        // Verify the results
    }
}
