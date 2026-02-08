package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BarService mockBarService;

    @InjectMocks
    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        final ConfigBean configBean = new ConfigBean();
        configBean.setSpecialPath("specialPath");
        ReflectionTestUtils.setField(myClassUnderTest, "configBean", configBean);
        ReflectionTestUtils.setField(myClassUnderTest, "defaultFooId", "defaultFooId");
        ReflectionTestUtils.setField(myClassUnderTest, "specialValuesMap", new HashMap<>());
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
