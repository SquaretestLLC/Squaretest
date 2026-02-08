package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyList<String> mockValues;

    private MyClass<String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
    }

    @Test
    void testGet() {
        assertThat(myClassUnderTest.get(0)).isEqualTo("result");
        assertThatThrownBy(() -> myClassUnderTest.get(0)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testAddAll1() {
        assertThat(myClassUnderTest.addAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.addAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testRemoveAll() {
        assertThat(myClassUnderTest.removeAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.removeAll(Arrays.asList("value")))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.removeAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
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
        assertThat(result).isFalse();
    }

    @Test
    void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.removeIf(filter)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testGetTheValues() {
        assertThat(myClassUnderTest.getTheValues()).isEqualTo(mockValues);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooDatasEmpty() {
        assertThat(myClassUnderTest.getFooDatasEmpty()).isEqualTo(new MyList<>());
    }

    @Test
    void testGetFooDatas2() {
        assertThat(myClassUnderTest.getFooDatas(new ArrayList<>(Arrays.asList(new FooData1()))))
                .isEqualTo(Collections.emptyList());
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertThat(result).isEqualTo(new MyList<>());
    }

    @Test
    void testLoadData1() {
        // Setup
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testLoadDatas1() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(result).isEqualTo(expectedResult);
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
        assertThat(MyClass.binarySearch(Arrays.asList(), "key")).isEqualTo(-1);
    }

    @Test
    void testBinarySearch2() {
        assertThat(
                MyClass.binarySearch(Arrays.asList("value"), "key", Comparator.comparing(Object::toString)))
                .isEqualTo(-1);
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
        assertThat(MyClass.min(Arrays.asList("value"))).isNull();
    }

    @Test
    void testDefaultIfBlank() {
        assertThat(MyClass.defaultIfBlank("str", "defaultStr")).isEqualTo("result");
    }

    @Test
    void testFirstNonBlank() {
        assertThat(MyClass.firstNonBlank("values")).isEqualTo("result");
    }

    @Test
    void testGetIfBlank() {
        assertThat(MyClass.getIfBlank("str", () -> "value")).isEqualTo("result");
    }

    @Test
    void testGetIfEmpty() {
        assertThat(MyClass.getIfEmpty("str", () -> "value")).isEqualTo("result");
    }

    @Test
    void testJoin() {
        assertThat(MyClass.join("elements")).isEqualTo("result");
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
        assertThat(myClassUnderTest.size()).isEqualTo(0);
    }

    @Test
    void testIsEmpty() {
        assertThat(myClassUnderTest.isEmpty()).isFalse();
    }

    @Test
    void testContains() {
        assertThat(myClassUnderTest.contains("o")).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.contains("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.contains("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testIndexOf() {
        assertThat(myClassUnderTest.indexOf("o")).isEqualTo(0);
        assertThatThrownBy(() -> myClassUnderTest.indexOf("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.indexOf("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testLastIndexOf() {
        assertThat(myClassUnderTest.lastIndexOf("o")).isEqualTo(0);
        assertThatThrownBy(() -> myClassUnderTest.lastIndexOf("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.lastIndexOf("o")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testClone() {
        assertThat(myClassUnderTest.clone()).isEqualTo("result");
    }

    @Test
    void testToArray1() {
        assertThat(myClassUnderTest.toArray()).isEqualTo(new Object[]{"result"});
    }

    @Test
    void testToArray2() {
        assertThat(myClassUnderTest.toArray(new String[]{"a"})).isEqualTo(new String[]{"result"});
        assertThat(myClassUnderTest.toArray(new String[]{"a"})).isEqualTo(new String[]{});
        assertThatThrownBy(() -> myClassUnderTest.toArray(new String[]{"a"})).isInstanceOf(ArrayStoreException.class);
        assertThatThrownBy(() -> myClassUnderTest.toArray(new String[]{"a"})).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testSet() {
        assertThat(myClassUnderTest.set(0, "element")).isEqualTo("result");
        assertThatThrownBy(() -> myClassUnderTest.set(0, "element")).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testAdd1() {
        assertThat(myClassUnderTest.add("e")).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.add("e")).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> myClassUnderTest.add("e")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.add("e")).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> myClassUnderTest.add("e")).isInstanceOf(IllegalArgumentException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.add(0, "element")).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testRemove1() {
        assertThat(myClassUnderTest.remove(0)).isEqualTo("result");
        assertThatThrownBy(() -> myClassUnderTest.remove(0)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testEquals() {
        assertThat(myClassUnderTest.equals("o")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(myClassUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testRemove2() {
        assertThat(myClassUnderTest.remove("o")).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.remove("o")).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> myClassUnderTest.remove("o")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.remove("o")).isInstanceOf(NullPointerException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.clear()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testAddAll2() {
        assertThat(myClassUnderTest.addAll(0, Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.addAll(0, Arrays.asList("value")))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> myClassUnderTest.addAll(0, Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testRetainAll() {
        assertThat(myClassUnderTest.retainAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.retainAll(Arrays.asList("value")))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.retainAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.listIterator(0)).isInstanceOf(IndexOutOfBoundsException.class);
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
        assertThat(myClassUnderTest.iterator()).isEqualTo(Arrays.asList("value").iterator());
    }

    @Test
    void testSubList() {
        assertThat(myClassUnderTest.subList(0, 0)).isEqualTo(Arrays.asList("value"));
        assertThat(myClassUnderTest.subList(0, 0)).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> myClassUnderTest.subList(0, 0)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> myClassUnderTest.subList(0, 0)).isInstanceOf(IllegalArgumentException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.forEach(mockAction)).isInstanceOf(NullPointerException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.replaceAll(operator))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        final UnaryOperator<String> operator = val -> val;

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.replaceAll(operator)).isInstanceOf(NullPointerException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.sort(c)).isInstanceOf(ClassCastException.class);
    }

    @Test
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.sort(c)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.sort(c)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testContainsAll() {
        assertThat(myClassUnderTest.containsAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.containsAll(Arrays.asList("value")))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.containsAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testToString() {
        assertThat(myClassUnderTest.toString()).isEqualTo("[]");
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
        assertThat(result).isEqualTo(new String[]{"result"});
    }

    @Test
    void testToArray3_ThrowsArrayStoreException() {
        // Setup
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.toArray(generator)).isInstanceOf(ArrayStoreException.class);
    }

    @Test
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        final IntFunction<String[]> generator = val -> {
            return new String[]{"value"};
        };

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.toArray(generator)).isInstanceOf(NullPointerException.class);
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
        assertThat(List.of()).isEqualTo(Collections.emptyList());
    }

    @Test
    void testOf2() {
        assertThat(List.of("e1")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf3() {
        assertThat(List.of("e1", "e2")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf4() {
        assertThat(List.of("e1", "e2", "e3")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf5() {
        assertThat(List.of("e1", "e2", "e3", "e4")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf6() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf7() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf8() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf9() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf10() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf11() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"))
                .isEqualTo(Arrays.asList("value"));
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"))
                .isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testOf12() {
        assertThat(List.of("elements")).isEqualTo(Arrays.asList("value"));
        assertThat(List.of("elements")).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.of("elements")).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCopyOf() {
        assertThat(List.copyOf(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(List.copyOf(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
        assertThatThrownBy(() -> List.copyOf(Arrays.asList("value"))).isInstanceOf(NullPointerException.class);
    }
}
