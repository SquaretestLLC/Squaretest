package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass<String> myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass<>(null);
    }

    @Test
    public void testGet() {
        assertEquals("result", myClassUnderTest.get(0));
    }

    @Test
    public void testAddAll1() {
        assertFalse(myClassUnderTest.addAll(Arrays.asList("value")));
    }

    @Test
    public void testRemoveAll() {
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
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
    public void testGetTheValues() {
        assertEquals(new MyList<>(Arrays.asList("value")), myClassUnderTest.getTheValues());
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

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoadData1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testLoadDatas1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testLoadData2() {
        // Setup
        final FooData2 expectedResult = new FooData2();
        expectedResult.setFooData2Id("fooData2Id");
        expectedResult.setFooData2Name("fooData2Name");

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

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoadFooData3() {
        // Setup
        final FooData3 expectedResult = new FooData3();
        expectedResult.setFooData3Id("fooData3Id");
        expectedResult.setFooData3Name("fooData3Name");

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
        // Run the test
        myClassUnderTest.trimToSize();

        // Verify the results
    }

    @Test
    public void testEnsureCapacity() {
        // Setup
        // Run the test
        myClassUnderTest.ensureCapacity(0);

        // Verify the results
    }

    @Test
    public void testSize() {
        assertEquals(0, myClassUnderTest.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(myClassUnderTest.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(myClassUnderTest.contains("o"));
    }

    @Test
    public void testIndexOf() {
        assertEquals(0, myClassUnderTest.indexOf("o"));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(0, myClassUnderTest.lastIndexOf("o"));
    }

    @Test
    public void testClone() {
        assertEquals("result", myClassUnderTest.clone());
    }

    @Test
    public void testToArray1() {
        assertArrayEquals(new Object[]{"result"}, myClassUnderTest.toArray());
    }

    @Test
    public void testToArray2() {
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.toArray(new String[]{"a"}));
        assertArrayEquals(new String[]{}, myClassUnderTest.toArray(new String[]{"a"}));
    }

    @Test
    public void testSet() {
        assertEquals("result", myClassUnderTest.set(0, "element"));
    }

    @Test
    public void testAdd1() {
        assertFalse(myClassUnderTest.add("e"));
    }

    @Test
    public void testAdd2() {
        // Setup
        // Run the test
        myClassUnderTest.add(0, "element");

        // Verify the results
    }

    @Test
    public void testRemove1() {
        assertEquals("result", myClassUnderTest.remove(0));
    }

    @Test
    public void testRemove2() {
        assertFalse(myClassUnderTest.remove("o"));
    }

    @Test
    public void testClear() {
        // Setup
        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test
    public void testAddAll2() {
        assertFalse(myClassUnderTest.addAll(0, Arrays.asList("value")));
    }

    @Test
    public void testRetainAll() {
        assertFalse(myClassUnderTest.retainAll(Arrays.asList("value")));
    }

    @Test
    public void testListIterator1() {
        // Setup
        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test
    public void testListIterator2() {
        // Setup
        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator();

        // Verify the results
    }

    @Test
    public void testIterator() {
        assertEquals(Arrays.asList("value").iterator(), myClassUnderTest.iterator());
    }

    @Test
    public void testSubList() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.subList(0, 0));
        assertEquals(Collections.emptyList(), myClassUnderTest.subList(0, 0));
    }

    @Test
    public void testForEach() {
        // Setup
        final Consumer<? super String> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);

        // Verify the results
    }

    @Test
    public void testSpliterator() {
        // Setup
        // Run the test
        final Spliterator<String> result = myClassUnderTest.spliterator();

        // Verify the results
    }

    @Test
    public void testReplaceAll() {
        // Setup
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);

        // Verify the results
    }

    @Test
    public void testSort3() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);

        // Verify the results
    }

    @Test
    public void testEquals() {
        assertFalse(myClassUnderTest.equals("o"));
    }

    @Test
    public void testHashCode() {
        assertEquals(0, myClassUnderTest.hashCode());
    }

    @Test
    public void testContainsAll() {
        assertFalse(myClassUnderTest.containsAll(Arrays.asList("value")));
    }

    @Test
    public void testToString() {
        assertEquals("result", myClassUnderTest.toString());
    }

    @Test
    public void testStream() {
        // Setup
        // Run the test
        final Stream<String> result = myClassUnderTest.stream();

        // Verify the results
    }

    @Test
    public void testParallelStream() {
        // Setup
        // Run the test
        final Stream<String> result = myClassUnderTest.parallelStream();

        // Verify the results
    }
}
