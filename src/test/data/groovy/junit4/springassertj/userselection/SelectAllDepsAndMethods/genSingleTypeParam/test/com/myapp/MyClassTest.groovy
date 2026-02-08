package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.function.Consumer
import java.util.function.Supplier

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MyList<String> mockValues

    private MyClass<String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
    }

    @Test
    void testGet() {
        assertThat(myClassUnderTest.get(0)).isEqualTo("result")
        assertThatThrownBy({
            myClassUnderTest.get(0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testAddAll1() {
        assertThat(myClassUnderTest.addAll(["value"])).isFalse()
        assertThatThrownBy({
            myClassUnderTest.addAll(["value"])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testRemoveAll() {
        assertThat(myClassUnderTest.removeAll(["value"])).isFalse()
        assertThatThrownBy({
            myClassUnderTest.removeAll(["value"])
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.removeAll(["value"])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testRemoveIf() {
        // Setup
        def filter = { val ->
            return false
        }

        // Run the test
        def result = myClassUnderTest.removeIf(filter)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        def filter = { val ->
            return false
        }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.removeIf(filter)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testGetTheValues() {
        assertThat(myClassUnderTest.getTheValues()).isEqualTo(mockValues)
    }

    @Test
    void testGetFooDatas1() {
        // Setup
        def fooData1 = new FooData1()
        fooData1.setId("id")
        fooData1.setName("name")
        def subFoo = new SubFoo()
        subFoo.setSubFooId("subFooId")
        subFoo.setSubFooName("subFooName")
        fooData1.setSubFoos([subFoo])
        def expectedResult = new MyList<>([fooData1])

        // Run the test
        def result = myClassUnderTest.getFooDatas()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooDatasEmpty() {
        assertThat(myClassUnderTest.getFooDatasEmpty()).isEqualTo(new MyList<>())
    }

    @Test
    void testGetFooDatas2() {
        assertThat(myClassUnderTest.getFooDatas(new ArrayList<>([new FooData1()]))).isEqualTo([])
    }

    @Test
    void testGetFooFromService() {
        // Setup
        def fooData1 = new FooData1()
        fooData1.setId("id")
        fooData1.setName("name")
        def subFoo = new SubFoo()
        subFoo.setSubFooId("subFooId")
        subFoo.setSubFooName("subFooName")
        fooData1.setSubFoos([subFoo])
        def expectedResult = new MyList<>([fooData1])

        // Configure FooService.getFooData1(...).
        def fooData11 = new FooData1()
        fooData11.setId("id")
        fooData11.setName("name")
        def subFoo1 = new SubFoo()
        subFoo1.setSubFooId("subFooId")
        subFoo1.setSubFooName("subFooName")
        fooData11.setSubFoos([subFoo1])
        def fooData1s = new MyList<>([fooData11])
        when(mockFooService.getFooData1("id")).thenReturn(fooData1s)

        // Run the test
        def result = myClassUnderTest.getFooFromService("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>())

        // Run the test
        def result = myClassUnderTest.getFooFromService("id")

        // Verify the results
        assertThat(result).isEqualTo(new MyList<>())
    }

    @Test
    void testLoadData1() {
        // Setup
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadData1(String.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testLoadDatas1() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testLoadData2() {
        // Setup
        def expectedResult = new FooData2()
        expectedResult.setFooData2Id("fooData2Id")
        expectedResult.setFooData2Name("fooData2Name")

        // Configure FooService.loadData1(...).
        def fooData2 = new FooData2()
        fooData2.setFooData2Id("fooData2Id")
        fooData2.setFooData2Name("fooData2Name")
        when(mockFooService.loadData1(FooData2.class, "theId")).thenReturn(fooData2)

        // Run the test
        def result = myClassUnderTest.loadData2(FooData2.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testLoadDatas2() {
        // Setup
        def fooData2 = new FooData2()
        fooData2.setFooData2Id("fooData2Id")
        fooData2.setFooData2Name("fooData2Name")
        def expectedResult = [fooData2]

        // Configure FooService.loadDatas1(...).
        def fooData21 = new FooData2()
        fooData21.setFooData2Id("fooData2Id")
        fooData21.setFooData2Name("fooData2Name")
        def fooData2s = [fooData21]
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(fooData2s)

        // Run the test
        def result = myClassUnderTest.loadDatas2(FooData2.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas2(FooData2.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testLoadFooData3() {
        // Setup
        def expectedResult = new FooData3()
        expectedResult.setFooData3Id("fooData3Id")
        expectedResult.setFooData3Name("fooData3Name")

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadFooData3("loadFooData3Param")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testLoadFooData4() {
        // Setup
        def expectedResult = new FooData4()
        expectedResult.setFooData4Id("fooData4Id")
        expectedResult.setFooData4Name("fooData4Name")

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadFooData4("loadFooData3Param")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testSort1() {
        // Setup
        // Run the test
        MyClass.sort(["value"])

        // Verify the results
    }

    @Test
    void testSort2() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        MyClass.sort(["value"], c)

        // Verify the results
    }

    @Test
    void testBinarySearch1() {
        assertThat(MyClass.binarySearch([], "key")).isEqualTo(-1)
    }

    @Test
    void testBinarySearch2() {
        assertThat(MyClass.binarySearch(["value"], "key", Comparator.comparing(Object.&toString))).isEqualTo(-1)
    }

    @Test
    void testShuffle() {
        // Setup
        // Run the test
        MyClass.shuffle(["value"])

        // Verify the results
    }

    @Test
    void testCopy() {
        // Setup
        // Run the test
        MyClass.copy(["value"], ["value"])

        // Verify the results
    }

    @Test
    void testMin() {
        assertThat(MyClass.min(["value"])).isNull()
    }

    @Test
    void testDefaultIfBlank() {
        assertThat(MyClass.defaultIfBlank("str", "defaultStr")).isEqualTo("result")
    }

    @Test
    void testFirstNonBlank() {
        assertThat(MyClass.firstNonBlank("values")).isEqualTo("result")
    }

    @Test
    void testGetIfBlank() {
        assertThat(MyClass.getIfBlank("str", { "value" } as Supplier)).isEqualTo("result")
    }

    @Test
    void testGetIfEmpty() {
        assertThat(MyClass.getIfEmpty("str", { "value" } as Supplier)).isEqualTo("result")
    }

    @Test
    void testJoin() {
        assertThat(MyClass.join("elements")).isEqualTo("result")
    }

    @Test
    void testTrimToSize() {
        // Setup
        // Run the test
        myClassUnderTest.trimToSize()

        // Verify the results
    }

    @Test
    void testEnsureCapacity() {
        // Setup
        // Run the test
        myClassUnderTest.ensureCapacity(0)

        // Verify the results
    }

    @Test
    void testSize() {
        assertThat(myClassUnderTest.size()).isEqualTo(0)
    }

    @Test
    void testIsEmpty() {
        assertThat(myClassUnderTest.isEmpty()).isFalse()
    }

    @Test
    void testContains() {
        assertThat(myClassUnderTest.contains("o")).isFalse()
        assertThatThrownBy({
            myClassUnderTest.contains("o")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.contains("o")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testIndexOf() {
        assertThat(myClassUnderTest.indexOf("o")).isEqualTo(0)
        assertThatThrownBy({
            myClassUnderTest.indexOf("o")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.indexOf("o")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testLastIndexOf() {
        assertThat(myClassUnderTest.lastIndexOf("o")).isEqualTo(0)
        assertThatThrownBy({
            myClassUnderTest.lastIndexOf("o")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.lastIndexOf("o")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testClone() {
        assertThat(myClassUnderTest.clone()).isEqualTo("result")
    }

    @Test
    void testToArray1() {
        assertThat(myClassUnderTest.toArray()).isEqualTo(["result"] as Object[])
    }

    @Test
    void testToArray2() {
        assertThat(myClassUnderTest.toArray(["a"] as String[])).isEqualTo(["result"] as String[])
        assertThat(myClassUnderTest.toArray(["a"] as String[])).isEqualTo([] as String[])
        assertThatThrownBy({
            myClassUnderTest.toArray(["a"] as String[])
        }).isInstanceOf(ArrayStoreException.class)
        assertThatThrownBy({
            myClassUnderTest.toArray(["a"] as String[])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testSet() {
        assertThat(myClassUnderTest.set(0, "element")).isEqualTo("result")
        assertThatThrownBy({
            myClassUnderTest.set(0, "element")
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testAdd1() {
        assertThat(myClassUnderTest.add("e")).isFalse()
        assertThatThrownBy({
            myClassUnderTest.add("e")
        }).isInstanceOf(UnsupportedOperationException.class)
        assertThatThrownBy({
            myClassUnderTest.add("e")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.add("e")
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            myClassUnderTest.add("e")
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testAdd2() {
        // Setup
        // Run the test
        myClassUnderTest.add(0, "element")

        // Verify the results
    }

    @Test
    void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.add(0, "element")
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testRemove1() {
        assertThat(myClassUnderTest.remove(0)).isEqualTo("result")
        assertThatThrownBy({
            myClassUnderTest.remove(0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testEquals() {
        assertThat(myClassUnderTest.equals("o")).isFalse()
    }

    @Test
    void testHashCode() {
        assertThat(myClassUnderTest.hashCode()).isEqualTo(0)
    }

    @Test
    void testRemove2() {
        assertThat(myClassUnderTest.remove("o")).isFalse()
        assertThatThrownBy({
            myClassUnderTest.remove("o")
        }).isInstanceOf(UnsupportedOperationException.class)
        assertThatThrownBy({
            myClassUnderTest.remove("o")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.remove("o")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testClear() {
        // Setup
        // Run the test
        myClassUnderTest.clear()

        // Verify the results
    }

    @Test
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.clear()
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testAddAll2() {
        assertThat(myClassUnderTest.addAll(0, ["value"])).isFalse()
        assertThatThrownBy({
            myClassUnderTest.addAll(0, ["value"])
        }).isInstanceOf(IndexOutOfBoundsException.class)
        assertThatThrownBy({
            myClassUnderTest.addAll(0, ["value"])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testRetainAll() {
        assertThat(myClassUnderTest.retainAll(["value"])).isFalse()
        assertThatThrownBy({
            myClassUnderTest.retainAll(["value"])
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.retainAll(["value"])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testListIterator1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.listIterator(0)

        // Verify the results
    }

    @Test
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.listIterator(0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testListIterator2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.listIterator()

        // Verify the results
    }

    @Test
    void testIterator() {
        assertThat(myClassUnderTest.iterator()).isEqualTo(["value"].iterator())
    }

    @Test
    void testSubList() {
        assertThat(myClassUnderTest.subList(0, 0)).isEqualTo(["value"])
        assertThat(myClassUnderTest.subList(0, 0)).isEqualTo([])
        assertThatThrownBy({
            myClassUnderTest.subList(0, 0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
        assertThatThrownBy({
            myClassUnderTest.subList(0, 0)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testForEach() {
        // Setup
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)

        // Verify the results
    }

    @Test
    void testForEach_ThrowsNullPointerException() {
        // Setup
        def mockAction = mock(Consumer.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.forEach(mockAction)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testSpliterator() {
        // Setup
        // Run the test
        def result = myClassUnderTest.spliterator()

        // Verify the results
    }

    @Test
    void testReplaceAll() {
        // Setup
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)

        // Verify the results
    }

    @Test
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        def operator = { val -> val }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.replaceAll(operator)
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        def operator = { val -> val }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.replaceAll(operator)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testSort3() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)

        // Verify the results
    }

    @Test
    void testSort3_ThrowsClassCastException() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.sort(c)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.sort(c)
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.sort(c)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testContainsAll() {
        assertThat(myClassUnderTest.containsAll(["value"])).isFalse()
        assertThatThrownBy({
            myClassUnderTest.containsAll(["value"])
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.containsAll(["value"])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testToString() {
        assertThat(myClassUnderTest.toString()).isEqualTo("[]")
    }

    @Test
    void testToArray3() {
        // Setup
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        def result = myClassUnderTest.toArray(generator)

        // Verify the results
        assertThat(result).isEqualTo(["result"] as String[])
    }

    @Test
    void testToArray3_ThrowsArrayStoreException() {
        // Setup
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.toArray(generator)
        }).isInstanceOf(ArrayStoreException.class)
    }

    @Test
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.toArray(generator)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testStream() {
        // Setup
        // Run the test
        def result = myClassUnderTest.stream()

        // Verify the results
    }

    @Test
    void testParallelStream() {
        // Setup
        // Run the test
        def result = myClassUnderTest.parallelStream()

        // Verify the results
    }

    @Test
    void testOf1() {
        assertThat(List.of()).isEqualTo([])
    }

    @Test
    void testOf2() {
        assertThat(List.of("e1")).isEqualTo(["value"])
        assertThat(List.of("e1")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf3() {
        assertThat(List.of("e1", "e2")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf4() {
        assertThat(List.of("e1", "e2", "e3")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf5() {
        assertThat(List.of("e1", "e2", "e3", "e4")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf6() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4", "e5")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4", "e5")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf7() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4", "e5", "e6")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf8() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf9() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf10() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf11() {
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")).isEqualTo(["value"])
        assertThat(List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")).isEqualTo([])
        assertThatThrownBy({
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testOf12() {
        assertThat(List.of("elements")).isEqualTo(["value"])
        assertThat(List.of("elements")).isEqualTo([])
        assertThatThrownBy({
            List.of("elements")
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testCopyOf() {
        assertThat(List.copyOf(["value"])).isEqualTo(["value"])
        assertThat(List.copyOf(["value"])).isEqualTo([])
        assertThatThrownBy({
            List.copyOf(["value"])
        }).isInstanceOf(NullPointerException.class)
    }
}
