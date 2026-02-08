package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean;
    @Mock
    private FooService mockFooService;
    @Mock
    private OtherBean mockOtherBean;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass();
        myClassUnderTest.setConfigBean(mockConfigBean);
        myClassUnderTest.setFooService(mockFooService);
        myClassUnderTest.setOtherBean(mockOtherBean);
    }

    @Test
    public void testGetFooData1() {
        // Setup
        when(mockConfigBean.getBasePath()).thenReturn("basePath");

        // Configure FooService.getFooData1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFooData1("basePath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData2() {
        // Setup
        when(mockConfigBean.getSpecialPath()).thenReturn("specialPath");

        // Configure FooService.getFooData2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFooData2("specialPath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }

    @Test
    public void testGetOtherBean() {
        assertEquals(mockOtherBean, myClassUnderTest.getOtherBean());
    }
}
