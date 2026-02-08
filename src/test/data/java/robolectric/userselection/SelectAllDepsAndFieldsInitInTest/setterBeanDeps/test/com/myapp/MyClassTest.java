package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean;
    @Mock
    private FooService mockFooService;
    @Mock
    private OtherBean mockOtherBean;

    @Before
    public void setUp() {
        initMocks(this);
    }

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
