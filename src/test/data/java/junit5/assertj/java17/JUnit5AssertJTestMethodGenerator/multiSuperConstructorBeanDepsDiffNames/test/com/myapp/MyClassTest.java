package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
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

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(theMockConfigBean, theMockOtherBean, theMockFooService);
    }

    @Test
    void testGetFooData11() {
        // Setup
        // Configure FooService.getFooData1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(theMockFooService.getFooData1("newPath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
    }

    @Test
    void testGetFooData31() {
        // Setup
        when(theMockConfigBean.getThirdPath()).thenReturn("thirdPath");

        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(theMockFooService.getFooData3("thirdPath", "id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id");

        // Verify the results
    }

    @Test
    void testGetOtherData1() {
        assertThat(myClassUnderTest.getOtherData()).isEqualTo("otherData");
    }

    @Test
    void testSetOtherData1() {
        // Setup
        // Run the test
        myClassUnderTest.setOtherData("otherData");

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

    @Test
    void testGetOtherBean() {
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(theMockOtherBean);
    }

    @Test
    void testSomeDataGetterAndSetter() {
        final String someData = "someData";
        myClassUnderTest.setSomeData(someData);
        assertThat(myClassUnderTest.getSomeData()).isEqualTo(someData);
    }

    @Test
    void testGetBaseId() {
        assertThat(myClassUnderTest.getBaseId()).isEqualTo("BaseId");
    }
}