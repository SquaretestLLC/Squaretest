package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import java.util.function.Consumer
import java.util.function.Supplier

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNull
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MyList<String> mockValues

    @Before
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGet() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.get(0)
    }

    @Test
    void testAddAll1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.addAll(["value"]))
    }

    @Test
    void testRemoveAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.removeAll(["value"]))
    }

    @Test
    void testRemoveIf() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def filter = { val ->
            return false
        }

        // Run the test
        def result = myClassUnderTest.removeIf(filter)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testGetTheValues() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert mockValues == myClassUnderTest.getTheValues()
    }

    @Test
    void testGetFooDatas1() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        assert expectedResult == result
    }

    @Test
    void testGetFooDatasEmpty() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert new MyList<>() == myClassUnderTest.getFooDatasEmpty()
    }

    @Test
    void testGetFooDatas2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert [] == myClassUnderTest.getFooDatas(new ArrayList<>([new FooData1()]))
    }

    @Test
    void testGetFooFromService() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        assert expectedResult == result
    }

    @Test
    void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>())

        // Run the test
        def result = myClassUnderTest.getFooFromService("id")

        // Verify the results
        assert new MyList<>() == result
    }

    @Test
    void testLoadData1() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadData1(String.class, "theId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testLoadDatas1() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assert [] == result
    }

    @Test
    void testLoadData2() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        assert expectedResult == result
    }

    @Test
    void testLoadDatas2() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        assert expectedResult == result
    }

    @Test
    void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas2(FooData2.class, "theId")

        // Verify the results
        assert [] == result
    }

    @Test
    void testLoadFooData3() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def expectedResult = new FooData3()
        expectedResult.setFooData3Id("fooData3Id")
        expectedResult.setFooData3Name("fooData3Name")

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadFooData3("loadFooData3Param")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testLoadFooData4() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def expectedResult = new FooData4()
        expectedResult.setFooData4Id("fooData4Id")
        expectedResult.setFooData4Name("fooData4Name")

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadFooData4("loadFooData3Param")

        // Verify the results
        assert expectedResult == result
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
        assert -1 == MyClass.binarySearch([], "key")
    }

    @Test
    void testBinarySearch2() {
        assert -1 == MyClass.binarySearch(["value"], "key", Comparator.comparing(Object.&toString))
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
        assertNull(MyClass.min(["value"]))
    }

    @Test
    void testDefaultIfBlank() {
        assert "result" == MyClass.defaultIfBlank("str", "defaultStr")
    }

    @Test
    void testFirstNonBlank() {
        assert "result" == MyClass.firstNonBlank("values")
    }

    @Test
    void testGetIfBlank() {
        assert "result" == MyClass.getIfBlank("str", { "value" } as Supplier)
    }

    @Test
    void testGetIfEmpty() {
        assert "result" == MyClass.getIfEmpty("str", { "value" } as Supplier)
    }

    @Test
    void testJoin() {
        assert "result" == MyClass.join("elements")
    }

    @Test
    void testTrimToSize() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.trimToSize()

        // Verify the results
    }

    @Test
    void testEnsureCapacity() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.ensureCapacity(0)

        // Verify the results
    }

    @Test
    void testSize() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert 0 == myClassUnderTest.size()
    }

    @Test
    void testIsEmpty() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.isEmpty())
    }

    @Test
    void testContains() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.contains("o"))
    }

    @Test
    void testIndexOf() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert 0 == myClassUnderTest.indexOf("o")
    }

    @Test
    void testLastIndexOf() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert 0 == myClassUnderTest.lastIndexOf("o")
    }

    @Test
    void testClone() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.clone()
    }

    @Test
    void testToArray1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert ["result"] as Object[] == myClassUnderTest.toArray()
    }

    @Test
    void testToArray2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert ["result"] as String[] == myClassUnderTest.toArray(["a"] as String[])
        assert [] as String[] == myClassUnderTest.toArray(["a"] as String[])
    }

    @Test
    void testSet() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.set(0, "element")
    }

    @Test
    void testAdd1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.add("e"))
    }

    @Test
    void testAdd2() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.add(0, "element")

        // Verify the results
    }

    @Test
    void testRemove1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.remove(0)
    }

    @Test
    void testRemove2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.remove("o"))
    }

    @Test
    void testClear() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.clear()

        // Verify the results
    }

    @Test
    void testAddAll2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.addAll(0, ["value"]))
    }

    @Test
    void testRetainAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.retainAll(["value"]))
    }

    @Test
    void testListIterator1() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        def result = myClassUnderTest.listIterator(0)

        // Verify the results
    }

    @Test
    void testListIterator2() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        def result = myClassUnderTest.listIterator()

        // Verify the results
    }

    @Test
    void testIterator() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert ["value"].iterator() == myClassUnderTest.iterator()
    }

    @Test
    void testSubList() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert ["value"] == myClassUnderTest.subList(0, 0)
        assert [] == myClassUnderTest.subList(0, 0)
    }

    @Test
    void testForEach() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)

        // Verify the results
    }

    @Test
    void testSpliterator() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        def result = myClassUnderTest.spliterator()

        // Verify the results
    }

    @Test
    void testReplaceAll() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)

        // Verify the results
    }

    @Test
    void testSort3() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)

        // Verify the results
    }

    @Test
    void testEquals() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.equals("o"))
    }

    @Test
    void testHashCode() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert 0 == myClassUnderTest.hashCode()
    }

    @Test
    void testContainsAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.containsAll(["value"]))
    }

    @Test
    void testToString() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.toString()
    }

    @Test
    void testStream() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        def result = myClassUnderTest.stream()

        // Verify the results
    }

    @Test
    void testParallelStream() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        def result = myClassUnderTest.parallelStream()

        // Verify the results
    }
}
