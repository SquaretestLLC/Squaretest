package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ConfigBean theMockConfigBean;
    @Mock
    private OtherBean theMockOtherBean;
    @Mock
    private FooService theMockFooService;

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(theMockConfigBean, theMockOtherBean, theMockFooService);
    }

    @Test
    void testGetFooData1() {
        // Setup
        when(theMockConfigBean.getBasePath()).thenReturn("basePath");

        // Configure FooService.getFooData1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(theMockFooService.getFooData1("basePath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        when(theMockConfigBean.getSpecialPath()).thenReturn("specialPath");

        // Configure FooService.getFooData2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(theMockFooService.getFooData2("specialPath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
    }
}
