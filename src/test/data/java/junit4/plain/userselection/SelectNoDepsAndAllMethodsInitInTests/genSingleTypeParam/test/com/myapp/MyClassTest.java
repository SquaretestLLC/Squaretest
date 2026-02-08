package com.myapp;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MyClassTest {

    @Test
    public void testGet() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals("result", myClassUnderTest.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_ThrowsIndexOutOfBoundsException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.get(0);
    }

    @Test
    public void testAddAll1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.addAll(Arrays.asList("value")));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAll1_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.addAll(Arrays.asList("value"));
    }

    @Test
    public void testRemoveAll() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveAll_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.removeAll(Arrays.asList("value"));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAll_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.removeAll(Arrays.asList("value"));
    }

    @Test
    public void testRemoveIf() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        final boolean result = myClassUnderTest.removeIf(filter);

        // Verify the results
        assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        myClassUnderTest.removeIf(filter);
    }

    @Test
    public void testGetTheValues() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(new MyList<>(Arrays.asList("value")), myClassUnderTest.getTheValues());
    }

    @Test
    public void testGetFooDatas1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
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
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(new MyList<>(), myClassUnderTest.getFooDatasEmpty());
    }

    @Test
    public void testGetFooDatas2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(Collections.emptyList(),
                myClassUnderTest.getFooDatas(new ArrayList<>(Arrays.asList(new FooData1()))));
    }

    @Test
    public void testGetFooFromService() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
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
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testLoadDatas1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testLoadData2() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
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
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
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
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
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
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
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
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.trimToSize();

        // Verify the results
    }

    @Test
    public void testEnsureCapacity() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.ensureCapacity(0);

        // Verify the results
    }

    @Test
    public void testSize() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(0, myClassUnderTest.size());
    }

    @Test
    public void testIsEmpty() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.isEmpty());
    }

    @Test
    public void testContains() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.contains("o"));
    }

    @Test(expected = ClassCastException.class)
    public void testContains_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.contains("o");
    }

    @Test(expected = NullPointerException.class)
    public void testContains_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.contains("o");
    }

    @Test
    public void testIndexOf() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(0, myClassUnderTest.indexOf("o"));
    }

    @Test(expected = ClassCastException.class)
    public void testIndexOf_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.indexOf("o");
    }

    @Test(expected = NullPointerException.class)
    public void testIndexOf_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.indexOf("o");
    }

    @Test
    public void testLastIndexOf() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(0, myClassUnderTest.lastIndexOf("o"));
    }

    @Test(expected = ClassCastException.class)
    public void testLastIndexOf_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.lastIndexOf("o");
    }

    @Test(expected = NullPointerException.class)
    public void testLastIndexOf_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.lastIndexOf("o");
    }

    @Test
    public void testClone() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals("result", myClassUnderTest.clone());
    }

    @Test(expected = CloneNotSupportedException.class)
    public void testClone_ThrowsCloneNotSupportedException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.clone();
    }

    @Test
    public void testToArray1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertArrayEquals(new Object[]{"result"}, myClassUnderTest.toArray());
    }

    @Test
    public void testToArray2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.toArray(new String[]{"a"}));
        assertArrayEquals(new String[]{}, myClassUnderTest.toArray(new String[]{"a"}));
    }

    @Test(expected = ArrayStoreException.class)
    public void testToArray2_ThrowsArrayStoreException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.toArray(new String[]{"a"});
    }

    @Test(expected = NullPointerException.class)
    public void testToArray2_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.toArray(new String[]{"a"});
    }

    @Test
    public void testSet() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals("result", myClassUnderTest.set(0, "element"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet_ThrowsIndexOutOfBoundsException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.set(0, "element");
    }

    @Test
    public void testAdd1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.add("e"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd1_ThrowsUnsupportedOperationException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.add("e");
    }

    @Test(expected = ClassCastException.class)
    public void testAdd1_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.add("e");
    }

    @Test(expected = NullPointerException.class)
    public void testAdd1_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.add("e");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd1_ThrowsIllegalArgumentException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.add("e");
    }

    @Test
    public void testAdd2() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.add(0, "element");

        // Verify the results
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.add(0, "element");
    }

    @Test
    public void testRemove1() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals("result", myClassUnderTest.remove(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemove1_ThrowsIndexOutOfBoundsException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.remove(0);
    }

    @Test
    public void testEquals() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.equals("o"));
    }

    @Test
    public void testHashCode() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(0, myClassUnderTest.hashCode());
    }

    @Test
    public void testRemove2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.remove("o"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove2_ThrowsUnsupportedOperationException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.remove("o");
    }

    @Test(expected = ClassCastException.class)
    public void testRemove2_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.remove("o");
    }

    @Test(expected = NullPointerException.class)
    public void testRemove2_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.remove("o");
    }

    @Test
    public void testClear() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.clear();
    }

    @Test
    public void testAddAll2() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.addAll(0, Arrays.asList("value")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAll2_ThrowsIndexOutOfBoundsException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.addAll(0, Arrays.asList("value"));
    }

    @Test(expected = NullPointerException.class)
    public void testAddAll2_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.addAll(0, Arrays.asList("value"));
    }

    @Test
    public void testRetainAll() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.retainAll(Arrays.asList("value")));
    }

    @Test(expected = ClassCastException.class)
    public void testRetainAll_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.retainAll(Arrays.asList("value"));
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAll_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.retainAll(Arrays.asList("value"));
    }

    @Test
    public void testListIterator1() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        myClassUnderTest.listIterator(0);
    }

    @Test
    public void testListIterator2() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final ListIterator<String> result = myClassUnderTest.listIterator();

        // Verify the results
    }

    @Test
    public void testIterator() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(Arrays.asList("value").iterator(), myClassUnderTest.iterator());
    }

    @Test
    public void testSubList() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals(Arrays.asList("value"), myClassUnderTest.subList(0, 0));
        assertEquals(Collections.emptyList(), myClassUnderTest.subList(0, 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubList_ThrowsIndexOutOfBoundsException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.subList(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubList_ThrowsIllegalArgumentException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.subList(0, 0);
    }

    @Test
    public void testForEach() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Consumer<? super String> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);

        // Verify the results
    }

    @Test(expected = NullPointerException.class)
    public void testForEach_ThrowsNullPointerException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Consumer<? super String> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);
    }

    @Test
    public void testSpliterator() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final Spliterator<String> result = myClassUnderTest.spliterator();

        // Verify the results
    }

    @Test
    public void testReplaceAll() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);

        // Verify the results
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);
    }

    @Test(expected = NullPointerException.class)
    public void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);
    }

    @Test
    public void testSort3() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);

        // Verify the results
    }

    @Test(expected = ClassCastException.class)
    public void testSort3_ThrowsClassCastException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);
    }

    @Test
    public void testContainsAll() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertFalse(myClassUnderTest.containsAll(Arrays.asList("value")));
    }

    @Test(expected = ClassCastException.class)
    public void testContainsAll_ThrowsClassCastException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.containsAll(Arrays.asList("value"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAll_ThrowsNullPointerException() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        myClassUnderTest.containsAll(Arrays.asList("value"));
    }

    @Test
    public void testToString() {
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        assertEquals("[]", myClassUnderTest.toString());
    }

    @Test
    public void testToArray3() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        final String[] result = myClassUnderTest.toArray(generator);

        // Verify the results
        assertArrayEquals(new String[]{"result"}, result);
    }

    @Test(expected = ArrayStoreException.class)
    public void testToArray3_ThrowsArrayStoreException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        myClassUnderTest.toArray(generator);
    }

    @Test(expected = NullPointerException.class)
    public void testToArray3_ThrowsNullPointerException() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        myClassUnderTest.toArray(generator);
    }

    @Test
    public void testStream() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final Stream<String> result = myClassUnderTest.stream();

        // Verify the results
    }

    @Test
    public void testParallelStream() {
        // Setup
        final MyClass<String> myClassUnderTest = new MyClass<>(null);

        // Run the test
        final Stream<String> result = myClassUnderTest.parallelStream();

        // Verify the results
    }

    @Test
    public void testOf1() {
        assertEquals(Collections.emptyList(), List.of());
    }

    @Test
    public void testOf2() {
        assertEquals(Arrays.asList("value"), List.of("e1"));
        assertEquals(Collections.emptyList(), List.of("e1"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf2_ThrowsNullPointerException() {
        List.of("e1");
    }

    @Test
    public void testOf3() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf3_ThrowsNullPointerException() {
        List.of("e1", "e2");
    }

    @Test
    public void testOf4() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf4_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3");
    }

    @Test
    public void testOf5() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf5_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4");
    }

    @Test
    public void testOf6() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf6_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5");
    }

    @Test
    public void testOf7() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf7_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6");
    }

    @Test
    public void testOf8() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf8_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7");
    }

    @Test
    public void testOf9() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf9_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8");
    }

    @Test
    public void testOf10() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf10_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9");
    }

    @Test
    public void testOf11() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf11_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10");
    }

    @Test
    public void testOf12() {
        assertEquals(Arrays.asList("value"), List.of("elements"));
        assertEquals(Collections.emptyList(), List.of("elements"));
    }

    @Test(expected = NullPointerException.class)
    public void testOf12_ThrowsNullPointerException() {
        List.of("elements");
    }

    @Test
    public void testCopyOf() {
        assertEquals(Arrays.asList("value"), List.copyOf(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), List.copyOf(Arrays.asList("value")));
    }

    @Test(expected = NullPointerException.class)
    public void testCopyOf_ThrowsNullPointerException() {
        List.copyOf(Arrays.asList("value"));
    }
}
