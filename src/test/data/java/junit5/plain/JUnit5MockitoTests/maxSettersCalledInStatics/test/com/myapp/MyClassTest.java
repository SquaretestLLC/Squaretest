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
        expectedResult.setValue18("value18");
        expectedResult.setValue19("value19");
        expectedResult.setValue20("value20");
        expectedResult.setValue21("value21");
        expectedResult.setValue22("value22");
        expectedResult.setValue23("value23");
        expectedResult.setValue24("value24");
        expectedResult.setValue25("value25");
        expectedResult.setValue26("value26");
        expectedResult.setValue27("value27");
        expectedResult.setValue28("value28");
        expectedResult.setValue29("value29");
        expectedResult.setValue30("value30");
        expectedResult.setValue31("value31");
        expectedResult.setValue32("value32");
        expectedResult.setValue33("value33");
        expectedResult.setValue34("value34");
        expectedResult.setValue35("value35");
        expectedResult.setValue36("value36");
        expectedResult.setValue37("value37");
        expectedResult.setValue38("value38");
        expectedResult.setValue39("value39");
        expectedResult.setValue40("value40");
        expectedResult.setValue41("value41");
        expectedResult.setValue42("value42");
        expectedResult.setValue43("value43");
        expectedResult.setValue44("value44");
        expectedResult.setValue45("value45");
        expectedResult.setValue46("value46");
        expectedResult.setValue47("value47");
        expectedResult.setValue48("value48");
        expectedResult.setValue49("value49");
        expectedResult.setValue50("value50");
        expectedResult.setValue51("value51");
        expectedResult.setValue52("value52");
        expectedResult.setValue53("value53");
        expectedResult.setValue54("value54");
        expectedResult.setValue55("value55");
        expectedResult.setValue56("value56");
        expectedResult.setValue57("value57");
        expectedResult.setValue58("value58");
        expectedResult.setValue59("value59");
        expectedResult.setValue60("value60");
        expectedResult.setValue61("value61");
        expectedResult.setValue62("value62");
        expectedResult.setValue63("value63");
        expectedResult.setValue64("value64");
        expectedResult.setValue65("value65");
        expectedResult.setValue66("value66");
        expectedResult.setValue67("value67");

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
        firstInnerBeanData.setValue18("value18");
        firstInnerBeanData.setValue19("value19");
        firstInnerBeanData.setValue20("value20");
        firstInnerBeanData.setValue21("value21");
        firstInnerBeanData.setValue22("value22");
        firstInnerBeanData.setValue23("value23");
        firstInnerBeanData.setValue24("value24");
        firstInnerBeanData.setValue25("value25");
        firstInnerBeanData.setValue26("value26");
        firstInnerBeanData.setValue27("value27");
        firstInnerBeanData.setValue28("value28");
        firstInnerBeanData.setValue29("value29");
        firstInnerBeanData.setValue30("value30");
        firstInnerBeanData.setValue31("value31");
        firstInnerBeanData.setValue32("value32");
        firstInnerBeanData.setValue33("value33");
        firstInnerBeanData.setValue34("value34");
        firstInnerBeanData.setValue35("value35");
        firstInnerBeanData.setValue36("value36");
        firstInnerBeanData.setValue37("value37");
        firstInnerBeanData.setValue38("value38");
        firstInnerBeanData.setValue39("value39");
        firstInnerBeanData.setValue40("value40");
        firstInnerBeanData.setValue41("value41");
        firstInnerBeanData.setValue42("value42");
        firstInnerBeanData.setValue43("value43");
        firstInnerBeanData.setValue44("value44");
        firstInnerBeanData.setValue45("value45");
        firstInnerBeanData.setValue46("value46");
        firstInnerBeanData.setValue47("value47");
        firstInnerBeanData.setValue48("value48");
        firstInnerBeanData.setValue49("value49");
        firstInnerBeanData.setValue50("value50");
        firstInnerBeanData.setValue51("value51");
        firstInnerBeanData.setValue52("value52");
        firstInnerBeanData.setValue53("value53");
        firstInnerBeanData.setValue54("value54");
        firstInnerBeanData.setValue55("value55");
        firstInnerBeanData.setValue56("value56");
        firstInnerBeanData.setValue57("value57");
        firstInnerBeanData.setValue58("value58");
        firstInnerBeanData.setValue59("value59");
        firstInnerBeanData.setValue60("value60");
        firstInnerBeanData.setValue61("value61");
        firstInnerBeanData.setValue62("value62");
        firstInnerBeanData.setValue63("value63");
        firstInnerBeanData.setValue64("value64");
        firstInnerBeanData.setValue65("value65");
        firstInnerBeanData.setValue66("value66");
        fooData.setFirstInnerBeanData(firstInnerBeanData);
        when(mockFooService.getFooData("id")).thenReturn(fooData);

        // Run the test
        final InnerBeanDTO result = myClassUnderTest.getFooData("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
