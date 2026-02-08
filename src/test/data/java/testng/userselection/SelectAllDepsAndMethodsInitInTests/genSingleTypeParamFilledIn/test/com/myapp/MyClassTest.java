package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;

public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGet() {
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
    public void testAddAll1() {
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
    public void testRemoveAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.removeAll(Arrays.asList("value")));
    }

    @Test
    public void testRemoveIf() {
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
    public void testGetTheValues() {
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
    public void testGetFooDatas1() {
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
    public void testGetFooDatas2() {
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
    public void testGetFooFromService() {
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
    public void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertEquals(new MyList<>(), result);
    }

    @Test
    public void testLoadData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testLoadDatas1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLoadData2() {
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
    public void testLoadDatas2() {
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
    public void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testLoadFooData3() {
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
    public void testLoadFooData4() {
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
    public void testSort1() {
        // Setup
        // Run the test
        BaseClass.sort(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testSort2() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        BaseClass.sort(Arrays.asList("value"), c);

        // Verify the results
    }

    @Test
    public void testBinarySearch1() {
        assertEquals(-1, BaseClass.binarySearch(Arrays.asList(), "key"));
    }

    @Test
    public void testBinarySearch2() {
        assertEquals(-1, BaseClass.binarySearch(Arrays.asList("value"), "key", Comparator.comparing(Object::toString)));
    }

    @Test
    public void testShuffle() {
        // Setup
        // Run the test
        BaseClass.shuffle(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testCopy() {
        // Setup
        // Run the test
        BaseClass.copy(Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    public void testMin() {
        assertNull(BaseClass.min(Arrays.asList("value")));
    }

    @Test
    public void testDefaultIfBlank() {
        assertEquals("result", BaseClass.defaultIfBlank("str", "defaultStr"));
    }

    @Test
    public void testFirstNonBlank() {
        assertEquals("result", BaseClass.firstNonBlank("values"));
    }

    @Test
    public void testGetIfBlank() {
        assertEquals("result", BaseClass.getIfBlank("str", () -> "value"));
    }

    @Test
    public void testGetIfEmpty() {
        assertEquals("result", BaseClass.getIfEmpty("str", () -> "value"));
    }

    @Test
    public void testJoin() {
        assertEquals("result", BaseClass.join("elements"));
    }

    @Test
    public void testTrimToSize() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.trimToSize();

        // Verify the results
    }

    @Test
    public void testEnsureCapacity() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.ensureCapacity(0);

        // Verify the results
    }

    @Test
    public void testSize() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.size());
    }

    @Test
    public void testIsEmpty() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.isEmpty());
    }

    @Test
    public void testContains() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.contains("o"));
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testContains_ThrowsClassCastException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.contains("o");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testContains_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.contains("o");
    }

    @Test
    public void testIndexOf() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.indexOf("o"));
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testIndexOf_ThrowsClassCastException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.indexOf("o");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testIndexOf_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.indexOf("o");
    }

    @Test
    public void testLastIndexOf() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.lastIndexOf("o"));
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testLastIndexOf_ThrowsClassCastException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.lastIndexOf("o");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testLastIndexOf_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.lastIndexOf("o");
    }

    @Test
    public void testClone() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("result", myClassUnderTest.clone());
    }

    @Test(expectedExceptions = {CloneNotSupportedException.class})
    public void testClone_ThrowsCloneNotSupportedException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.clone();
    }

    @Test
    public void testToArray1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(new Object[]{"result"}, myClassUnderTest.toArray());
    }

    @Test
    public void testToArray2() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(new String[]{"result"}, myClassUnderTest.toArray(new String[]{"a"}));
        assertEquals(new String[]{}, myClassUnderTest.toArray(new String[]{"a"}));
    }

    @Test(expectedExceptions = {ArrayStoreException.class})
    public void testToArray2_ThrowsArrayStoreException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.toArray(new String[]{"a"});
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testToArray2_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.toArray(new String[]{"a"});
    }

    @Test
    public void testSet() {
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

    @Test(expectedExceptions = {IndexOutOfBoundsException.class})
    public void testSet_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.set(0, element);
    }

    @Test
    public void testAdd1() {
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

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testAdd1_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(e);
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testAdd1_ThrowsClassCastException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(e);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testAdd1_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(e);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAdd1_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(e);
    }

    @Test
    public void testAdd2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(0, element);

        // Verify the results
    }

    @Test(expectedExceptions = {IndexOutOfBoundsException.class})
    public void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        myClassUnderTest.add(0, element);
    }

    @Test
    public void testRemove1() {
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

    @Test(expectedExceptions = {IndexOutOfBoundsException.class})
    public void testRemove1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.remove(0);
    }

    @Test
    public void testEquals() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.equals("o"));
    }

    @Test
    public void testHashCode() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals(0, myClassUnderTest.hashCode());
    }

    @Test
    public void testRemove2() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.remove("o"));
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testRemove2_ThrowsUnsupportedOperationException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.remove("o");
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testRemove2_ThrowsClassCastException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.remove("o");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testRemove2_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.remove("o");
    }

    @Test
    public void testClear() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.clear();
    }

    @Test
    public void testAddAll2() {
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

    @Test(expectedExceptions = {IndexOutOfBoundsException.class})
    public void testAddAll2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        myClassUnderTest.addAll(0, c);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testAddAll2_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        myClassUnderTest.addAll(0, c);
    }

    @Test
    public void testRetainAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.retainAll(Arrays.asList("value")));
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testRetainAll_ThrowsClassCastException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.retainAll(Arrays.asList("value"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testRetainAll_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.retainAll(Arrays.asList("value"));
    }

    @Test
    public void testListIterator1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final ListIterator<FooData5> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test(expectedExceptions = {IndexOutOfBoundsException.class})
    public void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.listIterator(0);
    }

    @Test
    public void testListIterator2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final ListIterator<FooData5> result = myClassUnderTest.listIterator();

        // Verify the results
    }

    @Test
    public void testIterator() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Iterator<FooData5> result = myClassUnderTest.iterator();

        // Verify the results
    }

    @Test
    public void testSubList() {
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

    @Test(expectedExceptions = {IndexOutOfBoundsException.class})
    public void testSubList_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.subList(0, 0);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testSubList_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.subList(0, 0);
    }

    @Test
    public void testForEach() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Consumer<? super FooData5> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);

        // Verify the results
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testForEach_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Consumer<? super FooData5> mockAction = mock(Consumer.class);

        // Run the test
        myClassUnderTest.forEach(mockAction);
    }

    @Test
    public void testSpliterator() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Spliterator<FooData5> result = myClassUnderTest.spliterator();

        // Verify the results
    }

    @Test
    public void testReplaceAll() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);

        // Verify the results
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        myClassUnderTest.replaceAll(operator);
    }

    @Test
    public void testSort3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);

        // Verify the results
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testSort3_ThrowsClassCastException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);
    }

    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        myClassUnderTest.sort(c);
    }

    @Test
    public void testContainsAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertFalse(myClassUnderTest.containsAll(Arrays.asList("value")));
    }

    @Test(expectedExceptions = {ClassCastException.class})
    public void testContainsAll_ThrowsClassCastException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.containsAll(Arrays.asList("value"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testContainsAll_ThrowsNullPointerException() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        myClassUnderTest.containsAll(Arrays.asList("value"));
    }

    @Test
    public void testToString() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("[]", myClassUnderTest.toString());
    }

    @Test
    public void testToArray3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        final String[] result = myClassUnderTest.toArray(generator);

        // Verify the results
        assertEquals(new String[]{"result"}, result);
    }

    @Test(expectedExceptions = {ArrayStoreException.class})
    public void testToArray3_ThrowsArrayStoreException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        myClassUnderTest.toArray(generator);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testToArray3_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        myClassUnderTest.toArray(generator);
    }

    @Test
    public void testStream() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Stream<FooData5> result = myClassUnderTest.stream();

        // Verify the results
    }

    @Test
    public void testParallelStream() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final Stream<FooData5> result = myClassUnderTest.parallelStream();

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

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf2_ThrowsNullPointerException() {
        List.of("e1");
    }

    @Test
    public void testOf3() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf3_ThrowsNullPointerException() {
        List.of("e1", "e2");
    }

    @Test
    public void testOf4() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf4_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3");
    }

    @Test
    public void testOf5() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf5_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4");
    }

    @Test
    public void testOf6() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf6_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5");
    }

    @Test
    public void testOf7() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf7_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6");
    }

    @Test
    public void testOf8() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf8_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7");
    }

    @Test
    public void testOf9() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf9_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8");
    }

    @Test
    public void testOf10() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf10_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9");
    }

    @Test
    public void testOf11() {
        assertEquals(Arrays.asList("value"), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
        assertEquals(Collections.emptyList(), List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf11_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10");
    }

    @Test
    public void testOf12() {
        assertEquals(Arrays.asList("value"), List.of("elements"));
        assertEquals(Collections.emptyList(), List.of("elements"));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testOf12_ThrowsNullPointerException() {
        List.of("elements");
    }

    @Test
    public void testCopyOf() {
        assertEquals(Arrays.asList("value"), List.copyOf(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), List.copyOf(Arrays.asList("value")));
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testCopyOf_ThrowsNullPointerException() {
        List.copyOf(Arrays.asList("value"));
    }
}
