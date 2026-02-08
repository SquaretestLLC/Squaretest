package com.myapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyList<String> mockValues;

    private MyClass<String> myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testGet() {
        assertEquals("result", myClassUnderTest.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.get(0));
    }

    @Test
    void testAddAll1() {
        assertFalse(myClassUnderTest.addAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.addAll(Arrays.asList("value")));
    }

    @Test
    void testRemoveAll() {
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.removeAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.removeAll(Arrays.asList("value")));
    }

    @Test
    void testRemoveIf() {
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
    void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.removeIf(filter));
    }

    @Test
    void testGetTheValues() {
        assertEquals(mockValues, myClassUnderTest.getTheValues());
    }

    @Test
    void testGetFooDatas1() {
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
    void testGetFooDatasEmpty() {
        assertEquals(new MyList<>(), myClassUnderTest.getFooDatasEmpty());
    }

    @Test
    void testGetFooDatas2() {
        assertEquals(Collections.emptyList(),
                myClassUnderTest.getFooDatas(new ArrayList<>(Arrays.asList(new FooData1()))));
    }

    @Test
    void testGetFooFromService() {
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
    void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(new MyList<>(), result);
    }

    @Test
    void testLoadData1() {
        // Setup
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testLoadDatas1() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testLoadData2() {
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
    void testLoadDatas2() {
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
    void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testLoadFooData3() {
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
    void testLoadFooData4() {
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
    void testSort1() {
        // Setup
        // Run the test
        MyClass.sort(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testSort2() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        MyClass.sort(Arrays.asList("value"), c);

        // Verify the results
    }

    @Test
    void testBinarySearch1() {
        assertEquals(-1, MyClass.binarySearch(Arrays.asList(), "key"));
    }

    @Test
    void testBinarySearch2() {
        assertEquals(-1, MyClass.binarySearch(Arrays.asList("value"), "key", Comparator.comparing(Object::toString)));
    }

    @Test
    void testShuffle() {
        // Setup
        // Run the test
        MyClass.shuffle(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testCopy() {
        // Setup
        // Run the test
        MyClass.copy(Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testMin() {
        assertNull(MyClass.min(Arrays.asList("value")));
    }

    @Test
    void testDefaultIfBlank() {
        assertEquals("result", MyClass.defaultIfBlank("str", "defaultStr"));
    }

    @Test
    void testFirstNonBlank() {
        assertEquals("result", MyClass.firstNonBlank("values"));
    }

    @Test
    void testGetIfBlank() {
        assertEquals("result", MyClass.getIfBlank("str", () -> "value"));
    }

    @Test
    void testGetIfEmpty() {
        assertEquals("result", MyClass.getIfEmpty("str", () -> "value"));
    }

    @Test
    void testJoin() {
        assertEquals("result", MyClass.join("elements"));
    }

    @Test
    void testTrimToSize() {
        // Setup
        // Run the test
        myClassUnderTest.trimToSize();

        // Verify the results
    }

    @Test
    void testEnsureCapacity() {
        // Setup
        // Run the test
        myClassUnderTest.ensureCapacity(0);

        // Verify the results
    }

    @Test
    void testSize() {
        assertEquals(0, myClassUnderTest.size());
    }

    @Test
    void testIsEmpty() {
        assertFalse(myClassUnderTest.isEmpty());
    }

    @Test
    void testContains() {
        assertFalse(myClassUnderTest.contains("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.contains("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.contains("o"));
    }

    @Test
    void testIndexOf() {
        assertEquals(0, myClassUnderTest.indexOf("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.indexOf("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.indexOf("o"));
    }

    @Test
    void testLastIndexOf() {
        assertEquals(0, myClassUnderTest.lastIndexOf("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.lastIndexOf("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.lastIndexOf("o"));
    }

    @Test
    void testClone() {
        assertEquals("result", myClassUnderTest.clone());
    }

    @Test
    void testToArray1() {
        assertArrayEquals(new Object[]{"result"}, myClassUnderTest.toArray());
    }

    @Test
    void testToArray2() {
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.toArray(new String[]{"a"}));
        assertArrayEquals(new String[]{}, myClassUnderTest.toArray(new String[]{"a"}));
        assertThrows(ArrayStoreException.class, () -> myClassUnderTest.toArray(new String[]{"a"}));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.toArray(new String[]{"a"}));
    }

    @Test
    void testSet() {
        assertEquals("result", myClassUnderTest.set(0, "element"));
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.set(0, "element"));
    }

    @Test
    void testAdd1() {
        assertFalse(myClassUnderTest.add("e"));
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.add("e"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.add("e"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.add("e"));
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.add("e"));
    }

    @Test
    void testAdd2() {
        // Setup
        // Run the test
        myClassUnderTest.add(0, "element");

        // Verify the results
    }

    @Test
    void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.add(0, "element"));
    }

    @Test
    void testRemove1() {
        assertEquals("result", myClassUnderTest.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.remove(0));
    }

    @Test
    void testEquals() {
        assertFalse(myClassUnderTest.equals("o"));
    }

    @Test
    void testHashCode() {
        assertEquals(0, myClassUnderTest.hashCode());
    }

    @Test
    void testRemove2() {
        assertFalse(myClassUnderTest.remove("o"));
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.remove("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.remove("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.remove("o"));
    }

    @Test
    void testClear() {
        // Setup
        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.clear());
    }

    @Test
    void testAddAll2() {
        assertFalse(myClassUnderTest.addAll(0, Arrays.asList("value")));
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.addAll(0, Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.addAll(0, Arrays.asList("value")));
    }

    @Test
    void testRetainAll() {
        assertFalse(myClassUnderTest.retainAll(Arrays.asList("value")));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.retainAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.retainAll(Arrays.asList("value")));
    }

    @Test
    void testListIterator1() {
        // Setup
        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.listIterator(0));
    }

    @Test
    void testListIterator2() {
        // Setup
        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator();

        // Verify the results
    }

    @Test
    void testIterator() {
        assertEquals(Arrays.asList("value").iterator(), myClassUnderTest.iterator());
    }

    @Test
    void testSubList() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.subList(0, 0));
        assertEquals(Collections.emptyList(), myClassUnderTest.subList(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.subList(0, 0));
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.subList(0, 0));
    }

    @Test
    void testForEach() {
        // Setup
        final Consumer<? super String> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);

        // Verify the results
    }

    @Test
    void testForEach_ThrowsNullPointerException() {
        // Setup
        final Consumer<? super String> mockAction = mock(Consumer.class);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.forEach(mockAction));
    }

    @Test
    void testSpliterator() {
        // Setup
        // Run the test
        final Spliterator<String> result = myClassUnderTest.spliterator();

        // Verify the results
    }

    @Test
    void testReplaceAll() {
        // Setup
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);

        // Verify the results
    }

    @Test
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.replaceAll(operator));
    }

    @Test
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.replaceAll(operator));
    }

    @Test
    void testSort3() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);

        // Verify the results
    }

    @Test
    void testSort3_ThrowsClassCastException() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThrows(ClassCastException.class, () -> myClassUnderTest.sort(c));
    }

    @Test
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.sort(c));
    }

    @Test
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.sort(c));
    }

    @Test
    void testContainsAll() {
        assertFalse(myClassUnderTest.containsAll(Arrays.asList("value")));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.containsAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.containsAll(Arrays.asList("value")));
    }

    @Test
    void testToString() {
        assertEquals("[]", myClassUnderTest.toString());
    }

    @Test
    void testToArray3() {
        // Setup
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        final String[] result = myClassUnderTest.toArray(generator);

        // Verify the results
        assertArrayEquals(new String[]{"result"}, result);
    }

    @Test
    void testToArray3_ThrowsArrayStoreException() {
        // Setup
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThrows(ArrayStoreException.class, () -> myClassUnderTest.toArray(generator));
    }

    @Test
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.toArray(generator));
    }

    @Test
    void testStream() {
        // Setup
        // Run the test
        final Stream<String> result = myClassUnderTest.stream();

        // Verify the results
    }

    @Test
    void testParallelStream() {
        // Setup
        // Run the test
        final Stream<String> result = myClassUnderTest.parallelStream();

        // Verify the results
    }

    @Test
    void testOf1() {
        assertEquals(Collections.emptyList(), List.of());
    }

    @Test
    void testOf2() {
        assertEquals(Arrays.asList("value"), List.of("e1"));
        assertEquals(Collections.emptyList(), List.of("e1"));
        assertThrows(NullPointerException.class, () -> List.of("e1"));
    }

    @Test
    void testOf3() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2"));
    }

    @Test
    void testOf4() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3"));
    }

    @Test
    void testOf5() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3", "e4"));
    }

    @Test
    void testOf6() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3", "e4", "e5"));
    }

    @Test
    void testOf7() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3", "e4", "e5", "e6"));
    }

    @Test
    void testOf8() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
    }

    @Test
    void testOf9() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
    }

    @Test
    void testOf10() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
        assertThrows(NullPointerException.class, () -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
    }

    @Test
    void testOf11() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
        assertThrows(NullPointerException.class,
                () -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
    }

    @Test
    void testOf12() {
        assertEquals(Arrays.asList("value"), List.of("elements"));
        assertEquals(Collections.emptyList(), List.of("elements"));
        assertThrows(NullPointerException.class, () -> List.of("elements"));
    }

    @Test
    void testCopyOf() {
        assertEquals(Arrays.asList("value"), List.copyOf(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), List.copyOf(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> List.copyOf(Arrays.asList("value")));
    }
}
