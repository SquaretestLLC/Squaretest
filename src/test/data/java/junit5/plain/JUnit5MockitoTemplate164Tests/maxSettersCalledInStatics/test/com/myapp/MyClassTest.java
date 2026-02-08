package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFooData() {
        // Setup
        final InnerBeanDTO expectedResult = new InnerBeanDTO();
        expectedResult.setId(0L);
        expectedResult.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setName("name");
        expectedResult.setValue1("value1");
        expectedResult.setValue2("value2");
        expectedResult.setValue3("value3");
        expectedResult.setValue4("value4");
        expectedResult.setValue5("value5");
        expectedResult.setValue6("value6");
        expectedResult.setValue7("value7");

        // Configure FooService.getFooData(...).
        final FooData fooData = new FooData();
        fooData.setParam1("param1");
        fooData.setParam2("param2");
        fooData.setParam3("param3");
        fooData.setParam4("param4");
        fooData.setParam5("param5");
        fooData.setParam6("param6");
        fooData.setParam7("param7");
        fooData.setParam8("param8");
        fooData.setParam9("param9");
        fooData.setParam10("param10");
        when(mockFooService.getFooData("id")).thenReturn(fooData);

        // Run the test
        final InnerBeanDTO result = myClassUnderTest.getFooData("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
