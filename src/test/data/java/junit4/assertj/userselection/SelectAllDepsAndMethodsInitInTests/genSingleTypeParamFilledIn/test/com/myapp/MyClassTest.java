package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isFalse();
    }

    @Test
    public void testRemoveAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.removeAll(Arrays.asList("value"))).isFalse();
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
        assertThat(result).isFalse();
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertThat(result).isEqualTo(new MyList<>());
    }

    @Test
    public void testLoadData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testLoadDatas1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    public void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(BaseClass.binarySearch(Arrays.asList(), "key")).isEqualTo(-1);
    }

    @Test
    public void testBinarySearch2() {
        assertThat(BaseClass.binarySearch(Arrays.asList("value"), "key",
                Comparator.comparing(Object::toString))).isEqualTo(-1);
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
        assertThat(BaseClass.min(Arrays.asList("value"))).isNull();
    }

    @Test
    public void testDefaultIfBlank() {
        assertThat(BaseClass.defaultIfBlank("str", "defaultStr")).isEqualTo("result");
    }

    @Test
    public void testFirstNonBlank() {
        assertThat(BaseClass.firstNonBlank("values")).isEqualTo("result");
    }

    @Test
    public void testGetIfBlank() {
        assertThat(BaseClass.getIfBlank("str", () -> "value")).isEqualTo("result");
    }

    @Test
    public void testGetIfEmpty() {
        assertThat(BaseClass.getIfEmpty("str", () -> "value")).isEqualTo("result");
    }

    @Test
    public void testJoin() {
        assertThat(BaseClass.join("elements")).isEqualTo("result");
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
        assertThat(myClassUnderTest.size()).isEqualTo(0);
    }

    @Test
    public void testIsEmpty() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.isEmpty()).isFalse();
    }

    @Test
    public void testContains() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.contains("o")).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.contains("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.contains("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testIndexOf() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.indexOf("o")).isEqualTo(0);
        assertThatThrownBy(() -> myClassUnderTest.indexOf("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.indexOf("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testLastIndexOf() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.lastIndexOf("o")).isEqualTo(0);
        assertThatThrownBy(() -> myClassUnderTest.lastIndexOf("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.lastIndexOf("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testClone() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.clone()).isEqualTo("result");
    }

    @Test
    public void testToArray1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.toArray()).isEqualTo(new Object[]{"result"});
    }

    @Test
    public void testToArray2() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.toArray(new String[]{"a"})).isEqualTo(new String[]{"result"});
        assertThat(myClassUnderTest.toArray(new String[]{"a"})).isEqualTo(new String[]{});
        assertThatThrownBy(() -> myClassUnderTest.toArray(new String[]{"a"})).isInstanceOf(ArrayStoreException.class);
        assertThatThrownBy(() -> myClassUnderTest.toArray(new String[]{"a"})).isInstanceOf(NullPointerException.class);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testSet_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.set(0, element)).isInstanceOf(IndexOutOfBoundsException.class);
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
        assertThat(result).isFalse();
    }

    @Test
    public void testAdd1_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.add(e)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testAdd1_ThrowsClassCastException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.add(e)).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testAdd1_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.add(e)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testAdd1_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 e = new FooData5();
        e.setFooData5Id("fooData5Id");
        e.setFooData5Name("fooData5Name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.add(e)).isInstanceOf(IllegalArgumentException.class);
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

    @Test
    public void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 element = new FooData5();
        element.setFooData5Id("fooData5Id");
        element.setFooData5Name("fooData5Name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.add(0, element)).isInstanceOf(IndexOutOfBoundsException.class);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testRemove1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.remove(0)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testEquals() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.equals("o")).isFalse();
    }

    @Test
    public void testHashCode() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    public void testRemove2() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.remove("o")).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.remove("o")).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> myClassUnderTest.remove("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.remove("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testClear() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        myClassUnderTest.clear();

        // Verify the results
    }

    @Test
    public void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.clear()).isInstanceOf(UnsupportedOperationException.class);
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
        assertThat(result).isFalse();
    }

    @Test
    public void testAddAll2_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.addAll(0, c)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testAddAll2_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final FooData5 fooData5 = new FooData5();
        fooData5.setFooData5Id("fooData5Id");
        fooData5.setFooData5Name("fooData5Name");
        final Collection<? extends FooData5> c = Arrays.asList(fooData5);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.addAll(0, c)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testRetainAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.retainAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.retainAll(Arrays.asList("value")))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.retainAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testListIterator1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        final ListIterator<FooData5> result = myClassUnderTest.listIterator(0);

        // Verify the results
    }

    @Test
    public void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.listIterator(0)).isInstanceOf(IndexOutOfBoundsException.class);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testSubList_ThrowsIndexOutOfBoundsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.subList(0, 0)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testSubList_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.subList(0, 0)).isInstanceOf(IllegalArgumentException.class);
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

    @Test
    public void testForEach_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Consumer<? super FooData5> mockAction = mock(Consumer.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.forEach(mockAction)).isInstanceOf(NullPointerException.class);
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

    @Test
    public void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.replaceAll(operator))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final UnaryOperator<FooData5> operator = val -> val;

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.replaceAll(operator)).isInstanceOf(NullPointerException.class);
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

    @Test
    public void testSort3_ThrowsClassCastException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.sort(c)).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.sort(c)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final Comparator<? super FooData5> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.sort(c)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testContainsAll() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.containsAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.containsAll(Arrays.asList("value")))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.containsAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testToString() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertThat(myClassUnderTest.toString()).isEqualTo("[]");
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
        assertThat(result).isEqualTo(new String[]{"result"});
    }

    @Test
    public void testToArray3_ThrowsArrayStoreException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.toArray(generator)).isInstanceOf(ArrayStoreException.class);
    }

    @Test
    public void testToArray3_ThrowsNullPointerException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.toArray(generator)).isInstanceOf(NullPointerException.class);
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
        assertThat(List.of()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testOf2() {
        assertThat(List.of("e1")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf3() {
        assertThat(List.of("e1", "e2")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf4() {
        assertThat(List.of("e1", "e2", "e3")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf5() {
        assertThat(List.of("e1", "e2", "e3", "e4")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf6() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf7() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf8() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf9() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf10() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf11() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"))
                .isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"))
                .isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testOf12() {
        assertThat(List.of("elements")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("elements")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("elements")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCopyOf() {
        assertThat(List.copyOf(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(List.copyOf(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.copyOf(Arrays.asList("value"))).isInstanceOf(NullPointerException.class);
    }
}
