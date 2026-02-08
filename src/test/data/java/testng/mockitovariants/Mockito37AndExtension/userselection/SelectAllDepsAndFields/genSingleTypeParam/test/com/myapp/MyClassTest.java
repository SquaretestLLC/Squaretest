package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyList<String> mockValues;

    private MyClass<String> myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
    }

    @Test
    public void testGet() {
        assertEquals("result", myClassUnderTest.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.get(0));
    }

    @Test
    public void testAddAll() {
        assertFalse(myClassUnderTest.addAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.addAll(Arrays.asList("value")));
    }

    @Test
    public void testRemoveAll() {
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.removeAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.removeAll(Arrays.asList("value")));
    }

    @Test
    public void testRemoveIf() {
        // Setup
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        final boolean result = myClassUnderTest.removeIf(filter);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.removeIf(filter));
    }

    @Test
    public void testGetTheValues() {
        assertEquals(mockValues, myClassUnderTest.getTheValues());
    }

    @Test
    public void testGetFooDatas1() {
        // Setup
        final FooData1 fooData1 = new FooData1();
        fooData1.setId("id");
        fooData1.setName("name");
        final SubFoo subFoo = new SubFoo();
        subFoo.setSubFooId("subFooId");
        subFoo.setSubFooName("subFooName");
        fooData1.setSubFoos(Arrays.asList(subFoo));
        final MyList<FooData1> expectedResult = new MyList<>(Arrays.asList(fooData1));

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooDatas();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetFooDatasEmpty() {
        assertEquals(new MyList<>(), myClassUnderTest.getFooDatasEmpty());
    }

    @Test
    public void testGetFooDatas2() {
        assertEquals(Collections.emptyList(),
                myClassUnderTest.getFooDatas(new ArrayList<>(Arrays.asList(new FooData1()))));
    }

    @Test
    public void testGetFooFromService() {
        // Setup
        final FooData1 fooData1 = new FooData1();
        fooData1.setId("id");
        fooData1.setName("name");
        final SubFoo subFoo = new SubFoo();
        subFoo.setSubFooId("subFooId");
        subFoo.setSubFooName("subFooName");
        fooData1.setSubFoos(Arrays.asList(subFoo));
        final MyList<FooData1> expectedResult = new MyList<>(Arrays.asList(fooData1));

        // Configure FooService.getFooData1(...).
        final FooData1 fooData11 = new FooData1();
        fooData11.setId("id");
        fooData11.setName("name");
        final SubFoo subFoo1 = new SubFoo();
        subFoo1.setSubFooId("subFooId");
        subFoo1.setSubFooName("subFooName");
        fooData11.setSubFoos(Arrays.asList(subFoo1));
        final MyList<FooData1> fooData1s = new MyList<>(Arrays.asList(fooData11));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1s);

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(new MyList<>(), result);
    }

    @Test
    public void testLoadData1() {
        // Setup
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testLoadDatas1() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLoadData2() {
        // Setup
        final FooData2 expectedResult = new FooData2();
        expectedResult.setFooData2Id("fooData2Id");
        expectedResult.setFooData2Name("fooData2Name");

        // Configure FooService.loadData1(...).
        final FooData2 fooData2 = new FooData2();
        fooData2.setFooData2Id("fooData2Id");
        fooData2.setFooData2Name("fooData2Name");
        when(mockFooService.loadData1(FooData2.class, "theId")).thenReturn(fooData2);

        // Run the test
        final FooData2 result = myClassUnderTest.loadData2(FooData2.class, "theId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoadDatas2() {
        // Setup
        final FooData2 fooData2 = new FooData2();
        fooData2.setFooData2Id("fooData2Id");
        fooData2.setFooData2Name("fooData2Name");
        final List<FooData2> expectedResult = Arrays.asList(fooData2);

        // Configure FooService.loadDatas1(...).
        final FooData2 fooData21 = new FooData2();
        fooData21.setFooData2Id("fooData2Id");
        fooData21.setFooData2Name("fooData2Name");
        final List<FooData2> fooData2s = Arrays.asList(fooData21);
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(fooData2s);

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLoadFooData3() {
        // Setup
        final FooData3 expectedResult = new FooData3();
        expectedResult.setFooData3Id("fooData3Id");
        expectedResult.setFooData3Name("fooData3Name");

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result");

        // Run the test
        final FooData3 result = myClassUnderTest.loadFooData3("loadFooData3Param");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoadFooData4() {
        // Setup
        final FooData4 expectedResult = new FooData4();
        expectedResult.setFooData4Id("fooData4Id");
        expectedResult.setFooData4Name("fooData4Name");

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result");

        // Run the test
        final FooData4 result = myClassUnderTest.loadFooData4("loadFooData3Param");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSort1() {
        // Setup
        // Run the test
        MyClass.sort(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testSort2() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        MyClass.sort(Arrays.asList("value"), c);

        // Verify the results
    }

    @Test
    public void testBinarySearch1() {
        assertEquals(-1, MyClass.binarySearch(Arrays.asList(), "key"));
    }

    @Test
    public void testBinarySearch2() {
        assertEquals(-1, MyClass.binarySearch(Arrays.asList("value"), "key", Comparator.comparing(Object::toString)));
    }

    @Test
    public void testShuffle() {
        // Setup
        // Run the test
        MyClass.shuffle(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testCopy() {
        // Setup
        // Run the test
        MyClass.copy(Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testMin() {
        assertNull(MyClass.min(Arrays.asList("value")));
    }

    @Test
    public void testDefaultIfBlank() {
        assertEquals("result", MyClass.defaultIfBlank("str", "defaultStr"));
    }

    @Test
    public void testFirstNonBlank() {
        assertEquals("result", MyClass.firstNonBlank("values"));
    }

    @Test
    public void testGetIfBlank() {
        assertEquals("result", MyClass.getIfBlank("str", () -> "value"));
    }

    @Test
    public void testGetIfEmpty() {
        assertEquals("result", MyClass.getIfEmpty("str", () -> "value"));
    }

    @Test
    public void testJoin() {
        assertEquals("result", MyClass.join("elements"));
    }
}
