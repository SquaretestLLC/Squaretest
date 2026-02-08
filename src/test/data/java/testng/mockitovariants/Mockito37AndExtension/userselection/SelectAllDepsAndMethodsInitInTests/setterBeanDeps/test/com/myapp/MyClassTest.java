package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean;
    @Mock
    private FooService mockFooService;
    @Mock
    private OtherBean mockOtherBean;

    @Test
    public void testGetFooData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.setConfigBean(mockConfigBean);
        myClassUnderTest.setFooService(mockFooService);
        myClassUnderTest.setOtherBean(mockOtherBean);
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
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.setConfigBean(mockConfigBean);
        myClassUnderTest.setFooService(mockFooService);
        myClassUnderTest.setOtherBean(mockOtherBean);
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
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.setConfigBean(mockConfigBean);
        myClassUnderTest.setFooService(mockFooService);
        myClassUnderTest.setOtherBean(mockOtherBean);
        assertEquals(mockOtherBean, myClassUnderTest.getOtherBean());
    }
}
