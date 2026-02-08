package com.myapp;

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
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGet() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 expectedResult = new FooData5();
        expectedResult.setFooData5Id("fooData5Id");
        expectedResult.setFooData5Name("fooData5Name");

        // Run the test
        final FooData5 result = myClassUnderTest.get(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddAll1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        final boolean result = myClassUnderTest.addAll(c);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testRemoveAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
    }

    @Test
    void testRemoveIf() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Predicate<? super FooData5> filter = val -> {
            return false;
        };

        // Run the test
        final boolean result = myClassUnderTest.removeIf(filter);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testGetTheValues() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final MyList<FooData5> expectedResult = new MyList<>(Arrays.asList(fooData5));

        // Run the test
        final MyList<FooData5> result = myClassUnderTest.getTheValues();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooDatas1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
    void testGetFooDatas2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData1 fooData1 = new FooData1();
        fooData1.setId("id");
        fooData1.setName("name");
        final SubFoo subFoo = new SubFoo();
        subFoo.setSubFooId("subFooId");
        subFoo.setSubFooName("subFooName");
        fooData1.setSubFoos(Arrays.asList(subFoo));
        final ArrayList<FooData1> fooDatas = new ArrayList<>(Arrays.asList(fooData1));
        final FooData1 fooData11 = new FooData1();
        fooData11.setId("id");
        fooData11.setName("name");
        final SubFoo subFoo1 = new SubFoo();
        subFoo1.setSubFooId("subFooId");
        subFoo1.setSubFooName("subFooName");
        fooData11.setSubFoos(Arrays.asList(subFoo1));
        final List<FooData1> expectedResult = Arrays.asList(fooData11);

        // Run the test
        final List<FooData1> result = myClassUnderTest.getFooDatas(fooDatas);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooFromService() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(new MyList<>(), result);
    }

    @Test
    void testLoadData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testLoadDatas1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testLoadData2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testLoadFooData3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
        BaseClass.sort(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testSort2() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        BaseClass.sort(Arrays.asList("value"), c);

        // Verify the results
    }

    @Test
    void testBinarySearch1() {
        assertEquals(-1, BaseClass.binarySearch(Arrays.asList(), "key"));
    }

    @Test
    void testBinarySearch2() {
        assertEquals(-1, BaseClass.binarySearch(Arrays.asList("value"), "key", Comparator.comparing(Object::toString)));
    }

    @Test
    void testShuffle() {
        // Setup
        // Run the test
        BaseClass.shuffle(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testCopy() {
        // Setup
        // Run the test
        BaseClass.copy(Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testMin() {
        assertNull(BaseClass.min(Arrays.asList("value")));
    }

    @Test
    void testDefaultIfBlank() {
        assertEquals("result", BaseClass.defaultIfBlank("str", "defaultStr"));
    }

    @Test
    void testFirstNonBlank() {
        assertEquals("result", BaseClass.firstNonBlank("values"));
    }

    @Test
    void testGetIfBlank() {
        assertEquals("result", BaseClass.getIfBlank("str", () -> "value"));
    }

    @Test
    void testGetIfEmpty() {
        assertEquals("result", BaseClass.getIfEmpty("str", () -> "value"));
    }

    @Test
    void testJoin() {
        assertEquals("result", BaseClass.join("elements"));
    }

    @Test
    void testTrimToSize() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.trimToSize();

        // Verify the results
    }

    @Test
    void testEnsureCapacity() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.ensureCapacity(0);

        // Verify the results
    }

    @Test
    void testSize() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.size());
    }

    @Test
    void testIsEmpty() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.isEmpty());
    }

    @Test
    void testContains() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.contains("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.contains("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.contains("o"));
    }

    @Test
    void testIndexOf() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.indexOf("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.indexOf("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.indexOf("o"));
    }

    @Test
    void testLastIndexOf() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.lastIndexOf("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.lastIndexOf("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.lastIndexOf("o"));
    }

    @Test
    void testClone() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("result", myClassUnderTest.clone());
    }

    @Test
    void testToArray1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertArrayEquals(new Object[]{"result"}, myClassUnderTest.toArray());
    }

    @Test
    void testToArray2() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertArrayEquals(new String[]{"result"}, myClassUnderTest.toArray(new String[]{"a"}));
        assertArrayEquals(new String[]{}, myClassUnderTest.toArray(new String[]{"a"}));
        assertThrows(ArrayStoreException.class, () -> myClassUnderTest.toArray(new String[]{"a"}));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.toArray(new String[]{"a"}));
    }

    @Test
    void testSet() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        final FooData5 expectedResult = new FooData5();
        expectedResult.setFooData5Id("fooData5Id");
        expectedResult.setFooData5Name("fooData5Name");

        // Run the test
        final FooData5 result = myClassUnderTest.set(0, element);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSet_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.set(0, element));
    }

    @Test
    void testAdd1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        final boolean result = myClassUnderTest.add(e);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testAdd1_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.add(e));
    }

    @Test
    void testAdd1_ThrowsClassCastException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThrows(ClassCastException.class, () -> myClassUnderTest.add(e));
    }

    @Test
    void testAdd1_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.add(e));
    }

    @Test
    void testAdd1_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.add(e));
    }

    @Test
    void testAdd2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(0, element);

        // Verify the results
    }

    @Test
    void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.add(0, element));
    }

    @Test
    void testRemove1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 expectedResult = new FooData5();
        expectedResult.setFooData5Id("fooData5Id");
        expectedResult.setFooData5Name("fooData5Name");

        // Run the test
        final FooData5 result = myClassUnderTest.remove(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testRemove1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.remove(0));
    }

    @Test
    void testEquals() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.equals("o"));
    }

    @Test
    void testHashCode() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.hashCode());
    }

    @Test
    void testRemove2() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.remove("o"));
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.remove("o"));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.remove("o"));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.remove("o"));
    }

    @Test
    void testClear() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.clear());
    }

    @Test
    void testAddAll2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        final boolean result = myClassUnderTest.addAll(0, c);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testAddAll2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.addAll(0, c));
    }

    @Test
    void testAddAll2_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.addAll(0, c));
    }

    @Test
    void testRetainAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.retainAll(Arrays.asList("value")));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.retainAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.retainAll(Arrays.asList("value")));
    }

    @Test
    void testListIterator1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final ListIterator<FooData5> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.listIterator(0));
    }

    @Test
    void testListIterator2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final ListIterator<FooData5> result = myClassUnderTest.listIterator();

        // Verify the results
    }

    @Test
    void testIterator() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Iterator<FooData5> result = myClassUnderTest.iterator();

        // Verify the results
    }

    @Test
    void testSubList() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final List<FooData5> expectedResult = Arrays.asList(fooData5);

        // Run the test
        final List<FooData5> result = myClassUnderTest.subList(0, 0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSubList_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, () -> myClassUnderTest.subList(0, 0));
    }

    @Test
    void testSubList_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.subList(0, 0));
    }

    @Test
    void testForEach() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Consumer<? super FooData5> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);

        // Verify the results
    }

    @Test
    void testForEach_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Consumer<? super FooData5> mockAction = mock(Consumer.class);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.forEach(mockAction));
    }

    @Test
    void testSpliterator() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Spliterator<FooData5> result = myClassUnderTest.spliterator();

        // Verify the results
    }

    @Test
    void testReplaceAll() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);

        // Verify the results
    }

    @Test
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.replaceAll(operator));
    }

    @Test
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.replaceAll(operator));
    }

    @Test
    void testSort3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);

        // Verify the results
    }

    @Test
    void testSort3_ThrowsClassCastException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThrows(ClassCastException.class, () -> myClassUnderTest.sort(c));
    }

    @Test
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThrows(UnsupportedOperationException.class, () -> myClassUnderTest.sort(c));
    }

    @Test
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.sort(c));
    }

    @Test
    void testContainsAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.containsAll(Arrays.asList("value")));
        assertThrows(ClassCastException.class, () -> myClassUnderTest.containsAll(Arrays.asList("value")));
        assertThrows(NullPointerException.class, () -> myClassUnderTest.containsAll(Arrays.asList("value")));
    }

    @Test
    void testToString() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("[]", myClassUnderTest.toString());
    }

    @Test
    void testToArray3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
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
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThrows(ArrayStoreException.class, () -> myClassUnderTest.toArray(generator));
    }

    @Test
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.toArray(generator));
    }

    @Test
    void testStream() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Stream<FooData5> result = myClassUnderTest.stream();

        // Verify the results
    }

    @Test
    void testParallelStream() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Stream<FooData5> result = myClassUnderTest.parallelStream();

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
