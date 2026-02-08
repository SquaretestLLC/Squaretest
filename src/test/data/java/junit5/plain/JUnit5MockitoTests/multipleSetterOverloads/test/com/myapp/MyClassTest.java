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
        expectedResult.setValue2(Value2Enum.FirstVal);
        expectedResult.setValue3("value3");
        expectedResult.setValue4("value4");
        expectedResult.setValue5("value5");
        expectedResult.setValue6("value6");
        expectedResult.setValue7("value7");
        expectedResult.setValue8("value8");
        expectedResult.setValue9("value9");
        expectedResult.setValue10("value10");
        expectedResult.setValue11("value11");
        expectedResult.setValue12("value12");
        expectedResult.setValue13("value13");
        expectedResult.setValue14("value14");
        expectedResult.setValue15("value15");
        expectedResult.setValue16("value16");
        expectedResult.setValue17("value17");

        // Configure FooService.getFooData(...).
        final FooData fooData = new FooData();
        final InnerBeanData firstInnerBeanData = new InnerBeanData();
        firstInnerBeanData.setId(0L);
        firstInnerBeanData.setCreateDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        firstInnerBeanData.setName("name");
        firstInnerBeanData.setValue1("value1");
        firstInnerBeanData.setValue2("value2");
        firstInnerBeanData.setValue3("value3");
        firstInnerBeanData.setValue4("value4");
        firstInnerBeanData.setValue5("value5");
        firstInnerBeanData.setValue6("value6");
        firstInnerBeanData.setValue7("value7");
        firstInnerBeanData.setValue8("value8");
        firstInnerBeanData.setValue9("value9");
        firstInnerBeanData.setValue10("value10");
        firstInnerBeanData.setValue11("value11");
        firstInnerBeanData.setValue12("value12");
        firstInnerBeanData.setValue13("value13");
        firstInnerBeanData.setValue14("value14");
        firstInnerBeanData.setValue15("value15");
        firstInnerBeanData.setValue16("value16");
        firstInnerBeanData.setValue17("value17");
        fooData.setFirstInnerBeanData(firstInnerBeanData);
        when(mockFooService.getFooData("id")).thenReturn(fooData);

        // Run the test
        final InnerBeanDTO result = myClassUnderTest.getFooData("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
