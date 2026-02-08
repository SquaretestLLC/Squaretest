package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.function.Consumer
import java.util.function.Supplier

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGet() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.get(0)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testAddAll1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        def result = myClassUnderTest.addAll(c)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testRemoveAll() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.removeAll(["value"])).isFalse()
    }

    @Test
    void testRemoveIf() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def filter = { val ->
            return false
        }

        // Run the test
        def result = myClassUnderTest.removeIf(filter)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testGetTheValues() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def expectedResult = new MyList<>([fooData5])

        // Run the test
        def result = myClassUnderTest.getTheValues()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooDatas1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
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
    void testGetFooDatas2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData1 = new FooData1()
        fooData1.setId("id")
        fooData1.setName("name")
        def subFoo = new SubFoo()
        subFoo.setSubFooId("subFooId")
        subFoo.setSubFooName("subFooName")
        fooData1.setSubFoos([subFoo])
        def fooDatas = new ArrayList<>([fooData1])
        def fooData11 = new FooData1()
        fooData11.setId("id")
        fooData11.setName("name")
        def subFoo1 = new SubFoo()
        subFoo1.setSubFooId("subFooId")
        subFoo1.setSubFooName("subFooName")
        fooData11.setSubFoos([subFoo1])
        def expectedResult = [fooData11]

        // Run the test
        def result = myClassUnderTest.getFooDatas(fooDatas)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooFromService() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>())

        // Run the test
        def result = myClassUnderTest.getFooFromService("id")

        // Verify the results
        assertThat(result).isEqualTo(new MyList<>())
    }

    @Test
    void testLoadData1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadData1(String.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testLoadDatas1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testLoadData2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas2(FooData2.class, "theId")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testLoadFooData3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
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
        BaseClass.sort(["value"])

        // Verify the results
    }

    @Test
    void testSort2() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        BaseClass.sort(["value"], c)

        // Verify the results
    }

    @Test
    void testBinarySearch1() {
        assertThat(BaseClass.binarySearch([], "key")).isEqualTo(-1)
    }

    @Test
    void testBinarySearch2() {
        assertThat(BaseClass.binarySearch(["value"], "key", Comparator.comparing(Object.&toString))).isEqualTo(-1)
    }

    @Test
    void testShuffle() {
        // Setup
        // Run the test
        BaseClass.shuffle(["value"])

        // Verify the results
    }

    @Test
    void testCopy() {
        // Setup
        // Run the test
        BaseClass.copy(["value"], ["value"])

        // Verify the results
    }

    @Test
    void testMin() {
        assertThat(BaseClass.min(["value"])).isNull()
    }

    @Test
    void testDefaultIfBlank() {
        assertThat(BaseClass.defaultIfBlank("str", "defaultStr")).isEqualTo("result")
    }

    @Test
    void testFirstNonBlank() {
        assertThat(BaseClass.firstNonBlank("values")).isEqualTo("result")
    }

    @Test
    void testGetIfBlank() {
        assertThat(BaseClass.getIfBlank("str", { "value" } as Supplier)).isEqualTo("result")
    }

    @Test
    void testGetIfEmpty() {
        assertThat(BaseClass.getIfEmpty("str", { "value" } as Supplier)).isEqualTo("result")
    }

    @Test
    void testJoin() {
        assertThat(BaseClass.join("elements")).isEqualTo("result")
    }

    @Test
    void testTrimToSize() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        myClassUnderTest.trimToSize()

        // Verify the results
    }

    @Test
    void testEnsureCapacity() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        myClassUnderTest.ensureCapacity(0)

        // Verify the results
    }

    @Test
    void testSize() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.size()).isEqualTo(0)
    }

    @Test
    void testIsEmpty() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.isEmpty()).isFalse()
    }

    @Test
    void testContains() {
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.clone()).isEqualTo("result")
    }

    @Test
    void testToArray1() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.toArray()).isEqualTo(["result"] as Object[])
    }

    @Test
    void testToArray2() {
        def myClassUnderTest = new MyClass(mockFooService)
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
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.set(0, element)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testSet_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.set(0, element)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testAdd1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.add(e)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testAdd1_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.add(e)
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testAdd1_ThrowsClassCastException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.add(e)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testAdd1_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.add(e)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testAdd1_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.add(e)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testAdd2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        // Run the test
        myClassUnderTest.add(0, element)

        // Verify the results
    }

    @Test
    void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.add(0, element)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testRemove1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.remove(0)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testRemove1_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.remove(0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testEquals() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.equals("o")).isFalse()
    }

    @Test
    void testHashCode() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.hashCode()).isEqualTo(0)
    }

    @Test
    void testRemove2() {
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        myClassUnderTest.clear()

        // Verify the results
    }

    @Test
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.clear()
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testAddAll2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        def result = myClassUnderTest.addAll(0, c)

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testAddAll2_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.addAll(0, c)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testAddAll2_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.addAll(0, c)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testRetainAll() {
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        def result = myClassUnderTest.listIterator(0)

        // Verify the results
    }

    @Test
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.listIterator(0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testListIterator2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        def result = myClassUnderTest.listIterator()

        // Verify the results
    }

    @Test
    void testIterator() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        def result = myClassUnderTest.iterator()

        // Verify the results
    }

    @Test
    void testSubList() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def expectedResult = [fooData5]

        // Run the test
        def result = myClassUnderTest.subList(0, 0)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testSubList_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.subList(0, 0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testSubList_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.subList(0, 0)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testForEach() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)

        // Verify the results
    }

    @Test
    void testForEach_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def mockAction = mock(Consumer.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.forEach(mockAction)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testSpliterator() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        def result = myClassUnderTest.spliterator()

        // Verify the results
    }

    @Test
    void testReplaceAll() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)

        // Verify the results
    }

    @Test
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def operator = { val -> val }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.replaceAll(operator)
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def operator = { val -> val }

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.replaceAll(operator)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testSort3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)

        // Verify the results
    }

    @Test
    void testSort3_ThrowsClassCastException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.sort(c)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.sort(c)
        }).isInstanceOf(UnsupportedOperationException.class)
    }

    @Test
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.sort(c)
        }).isInstanceOf(IllegalArgumentException.class)
    }

    @Test
    void testContainsAll() {
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.toString()).isEqualTo("[]")
    }

    @Test
    void testToArray3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)

        // Run the test
        def result = myClassUnderTest.stream()

        // Verify the results
    }

    @Test
    void testParallelStream() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)

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
