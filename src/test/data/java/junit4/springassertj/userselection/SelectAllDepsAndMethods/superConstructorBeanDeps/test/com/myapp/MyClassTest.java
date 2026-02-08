package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean;
    @Mock
    private OtherBean mockOtherBean;
    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
    }

    @Test
    public void testGetFooData1() {
        // Setup
        // Configure FooService.getFooData1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFooData1("newPath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    public void testGetFooData3() {
        // Setup
        when(mockConfigBean.getThirdPath()).thenReturn("thirdPath");

        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFooData3("thirdPath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
    }

    @Test
    public void testGetOtherData() {
        assertThat(myClassUnderTest.getOtherData()).isEqualTo("otherData");
    }

    @Test
    public void testSetOtherData() {
        // Setup
        // Run the test
        myClassUnderTest.setOtherData("otherData");

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
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(mockOtherBean);
    }

    @Test
    public void testSomeDataGetterAndSetter() {
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertThat(myClassUnderTest.getSomeData()).isEqualTo(someData);
    }
}
