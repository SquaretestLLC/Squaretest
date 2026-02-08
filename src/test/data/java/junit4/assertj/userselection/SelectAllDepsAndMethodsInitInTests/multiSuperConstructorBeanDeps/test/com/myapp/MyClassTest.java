package com.myapp;

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

    @Test
    public void testGetFooData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);

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
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
        assertThat(myClassUnderTest.getOtherData()).isEqualTo("otherData");
    }

    @Test
    public void testSetOtherData() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);

        // Run the test
        myClassUnderTest.setOtherData("otherData");

        // Verify the results
    }

    @Test
    public void testGetFooData2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(mockOtherBean);
    }

    @Test
    public void testSomeDataGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertThat(myClassUnderTest.getSomeData()).isEqualTo(someData);
    }

    @Test
    public void testGetBaseId() {
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService);
        assertThat(myClassUnderTest.getBaseId()).isEqualTo("BaseId");
    }
}
