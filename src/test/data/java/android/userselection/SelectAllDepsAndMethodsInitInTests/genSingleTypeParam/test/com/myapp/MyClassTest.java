package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyList<String> mockValues;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGet() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals("result", myClassUnderTest.get(0));
    }

    @Test
    public void testAddAll1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.addAll(Arrays.asList("value")));
    }

    @Test
    public void testRemoveAll() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
    }

    @Test
    public void testRemoveIf() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        final boolean result = myClassUnderTest.removeIf(filter);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testGetTheValues() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(mockValues, myClassUnderTest.getTheValues());
    }

    @Test
    public void testGetFooDatas1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
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
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(new MyList<>(), myClassUnderTest.getFooDatasEmpty());
    }

    @Test
    public void testGetFooDatas2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(Collections.emptyList(),
                myClassUnderTest.getFooDatas(new ArrayList<>(Arrays.asList(new FooData1()))));
    }

    @Test
    public void testGetFooFromService() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
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
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(new MyList<>(), result);
    }

    @Test
    public void testLoadData1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testLoadDatas1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLoadData2() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
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
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
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
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLoadFooData3() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
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
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
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

    @Test
    public void testTrimToSize() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.trimToSize();

        // Verify the results
    }

    @Test
    public void testEnsureCapacity() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.ensureCapacity(0);

        // Verify the results
    }

    @Test
    public void testSize() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(0, myClassUnderTest.size());
    }

    @Test
    public void testIsEmpty() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.isEmpty());
    }

    @Test
    public void testContains() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.contains("o"));
    }

    @Test
    public void testIndexOf() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(0, myClassUnderTest.indexOf("o"));
    }

    @Test
    public void testLastIndexOf() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(0, myClassUnderTest.lastIndexOf("o"));
    }

    @Test
    public void testClone() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals("result", myClassUnderTest.clone());
    }

    @Test
    public void testToArray1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertArrayEquals(new Object[]{"result"}, myClassUnderTest.toArray());
    }

    @Test
    public void testToArray2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.toArray(new String[]{"a"}));
        assertArrayEquals(new String[]{}, myClassUnderTest.toArray(new String[]{"a"}));
    }

    @Test
    public void testSet() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals("result", myClassUnderTest.set(0, "element"));
    }

    @Test
    public void testAdd1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.add("e"));
    }

    @Test
    public void testAdd2() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.add(0, "element");

        // Verify the results
    }

    @Test
    public void testRemove1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals("result", myClassUnderTest.remove(0));
    }

    @Test
    public void testRemove2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.remove("o"));
    }

    @Test
    public void testClear() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test
    public void testAddAll2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.addAll(0, Arrays.asList("value")));
    }

    @Test
    public void testRetainAll() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.retainAll(Arrays.asList("value")));
    }

    @Test
    public void testListIterator1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test
    public void testListIterator2() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator();

        // Verify the results
    }

    @Test
    public void testIterator() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(Arrays.asList("value").iterator(), myClassUnderTest.iterator());
    }

    @Test
    public void testSubList() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(Arrays.asList("value"), myClassUnderTest.subList(0, 0));
        assertEquals(Collections.emptyList(), myClassUnderTest.subList(0, 0));
    }

    @Test
    public void testForEach() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        final Consumer<? super String> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);

        // Verify the results
    }

    @Test
    public void testSpliterator() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        final Spliterator<String> result = myClassUnderTest.spliterator();

        // Verify the results
    }

    @Test
    public void testReplaceAll() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);

        // Verify the results
    }

    @Test
    public void testSort3() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);

        // Verify the results
    }

    @Test
    public void testEquals() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.equals("o"));
    }

    @Test
    public void testHashCode() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals(0, myClassUnderTest.hashCode());
    }

    @Test
    public void testContainsAll() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.containsAll(Arrays.asList("value")));
    }

    @Test
    public void testToString() {
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        assertEquals("result", myClassUnderTest.toString());
    }

    @Test
    public void testStream() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        final Stream<String> result = myClassUnderTest.stream();

        // Verify the results
    }

    @Test
    public void testParallelStream() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
        // Run the test
        final Stream<String> result = myClassUnderTest.parallelStream();

        // Verify the results
    }
}
