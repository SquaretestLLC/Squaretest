package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    void testGetMainFooData() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("MyId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getMainFooData();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetMainFooData() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("param")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.setMainFooData("param");

        // Verify the results
    }

    @Test
    void testSetOtherFooData() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("param")).thenReturn(fooData);

        // Run the test
        final MyClass result = myClassUnderTest.setOtherFooData("param");

        // Verify the results
    }

    @Test
    void testThirdFooDataGetterAndSetter() {
        final FooData thirdFooData = new FooData();
        myClassUnderTest.setThirdFooData(thirdFooData);
        assertEquals(thirdFooData, myClassUnderTest.getThirdFooData());
    }

    @Test
    void testFourthDataGetterAndSetter() {
        final String fourthData = "fourthData";
        myClassUnderTest.setFourthData(fourthData);
        assertEquals(fourthData, myClassUnderTest.getFourthData());
    }

    @Test
    void testFifthDataGetterAndSetter() {
        final Object fifthData = "fifthData";
        myClassUnderTest.setFifthData(fifthData);
        assertEquals(fifthData, myClassUnderTest.getFifthData());
    }

    @Test
    void testSixthDataGetterAndSetter() {
        final String sixthData = "sixthData";
        myClassUnderTest.setSixthData(sixthData);
        assertEquals(sixthData, myClassUnderTest.getSixthData());
    }

    @Test
    void testSeventhDataGetterAndSetter() {
        final String seventhData = "seventhData";
        myClassUnderTest.setSeventhData(seventhData);
        assertEquals(seventhData, myClassUnderTest.getSeventhData());
    }

    @Test
    void testEighthDataGetterAndSetter() {
        final Object eighthData = "eighthDataParam";
        myClassUnderTest.setEighthData(eighthData);
        assertEquals(eighthData, myClassUnderTest.getEighthData());
    }

    @Test
    void testNinthDataGetterAndSetter() {
        final String ninthData = "ninthDataParam";
        myClassUnderTest.setNinthData(ninthData);
        assertEquals(ninthData, myClassUnderTest.getNinthData());
    }
}
