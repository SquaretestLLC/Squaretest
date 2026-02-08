package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.function.Consumer
import java.util.function.Supplier

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertFalse
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MyList<String> mockValues

    @BeforeMethod
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGet() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.get(0)
    }

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testGet_ThrowsIndexOutOfBoundsException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.get(0)
    }

    @Test
    void testAddAll1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.addAll(["value"]))
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testAddAll1_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.addAll(["value"])
    }

    @Test
    void testRemoveAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.removeAll(["value"]))
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testRemoveAll_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.removeAll(["value"])
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testRemoveAll_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.removeAll(["value"])
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

    @Test(expectedExceptions = [NullPointerException.class])
    void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def filter = { val ->
            return false
        }

        // Run the test
        myClassUnderTest.removeIf(filter)
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

    @Test(expectedExceptions = [ClassCastException.class])
    void testContains_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.contains("o")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testContains_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.contains("o")
    }

    @Test
    void testIndexOf() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert 0 == myClassUnderTest.indexOf("o")
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testIndexOf_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.indexOf("o")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testIndexOf_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.indexOf("o")
    }

    @Test
    void testLastIndexOf() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert 0 == myClassUnderTest.lastIndexOf("o")
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testLastIndexOf_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.lastIndexOf("o")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testLastIndexOf_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.lastIndexOf("o")
    }

    @Test
    void testClone() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.clone()
    }

    @Test(expectedExceptions = [CloneNotSupportedException.class])
    void testClone_ThrowsCloneNotSupportedException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.clone()
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

    @Test(expectedExceptions = [ArrayStoreException.class])
    void testToArray2_ThrowsArrayStoreException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.toArray(["a"] as String[])
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testToArray2_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.toArray(["a"] as String[])
    }

    @Test
    void testSet() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.set(0, "element")
    }

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testSet_ThrowsIndexOutOfBoundsException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.set(0, "element")
    }

    @Test
    void testAdd1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.add("e"))
    }

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testAdd1_ThrowsUnsupportedOperationException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.add("e")
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testAdd1_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.add("e")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testAdd1_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.add("e")
    }

    @Test(expectedExceptions = [IllegalArgumentException.class])
    void testAdd1_ThrowsIllegalArgumentException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.add("e")
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

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.add(0, "element")
    }

    @Test
    void testRemove1() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "result" == myClassUnderTest.remove(0)
    }

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testRemove1_ThrowsIndexOutOfBoundsException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.remove(0)
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
    void testRemove2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.remove("o"))
    }

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testRemove2_ThrowsUnsupportedOperationException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.remove("o")
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testRemove2_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.remove("o")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testRemove2_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.remove("o")
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

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.clear()
    }

    @Test
    void testAddAll2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.addAll(0, ["value"]))
    }

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testAddAll2_ThrowsIndexOutOfBoundsException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.addAll(0, ["value"])
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testAddAll2_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.addAll(0, ["value"])
    }

    @Test
    void testRetainAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.retainAll(["value"]))
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testRetainAll_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.retainAll(["value"])
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testRetainAll_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.retainAll(["value"])
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

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        // Run the test
        myClassUnderTest.listIterator(0)
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

    @Test(expectedExceptions = [IndexOutOfBoundsException.class])
    void testSubList_ThrowsIndexOutOfBoundsException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.subList(0, 0)
    }

    @Test(expectedExceptions = [IllegalArgumentException.class])
    void testSubList_ThrowsIllegalArgumentException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.subList(0, 0)
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

    @Test(expectedExceptions = [NullPointerException.class])
    void testForEach_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)
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

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)
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

    @Test(expectedExceptions = [ClassCastException.class])
    void testSort3_ThrowsClassCastException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)
    }

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)
    }

    @Test(expectedExceptions = [IllegalArgumentException.class])
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)
    }

    @Test
    void testContainsAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertFalse(myClassUnderTest.containsAll(["value"]))
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testContainsAll_ThrowsClassCastException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.containsAll(["value"])
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testContainsAll_ThrowsNullPointerException() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        myClassUnderTest.containsAll(["value"])
    }

    @Test
    void testToString() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assert "[]" == myClassUnderTest.toString()
    }

    @Test
    void testToArray3() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        def result = myClassUnderTest.toArray(generator)

        // Verify the results
        assert ["result"] as String[] == result
    }

    @Test(expectedExceptions = [ArrayStoreException.class])
    void testToArray3_ThrowsArrayStoreException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        myClassUnderTest.toArray(generator)
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        myClassUnderTest.toArray(generator)
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

    @Test
    void testOf1() {
        assert [] == List.of()
    }

    @Test
    void testOf2() {
        assert ["value"] == List.of("e1")
        assert [] == List.of("e1")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf2_ThrowsNullPointerException() {
        List.of("e1")
    }

    @Test
    void testOf3() {
        assert ["value"] == List.of("e1", "e2")
        assert [] == List.of("e1", "e2")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf3_ThrowsNullPointerException() {
        List.of("e1", "e2")
    }

    @Test
    void testOf4() {
        assert ["value"] == List.of("e1", "e2", "e3")
        assert [] == List.of("e1", "e2", "e3")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf4_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3")
    }

    @Test
    void testOf5() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4")
        assert [] == List.of("e1", "e2", "e3", "e4")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf5_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4")
    }

    @Test
    void testOf6() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf6_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5")
    }

    @Test
    void testOf7() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf7_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6")
    }

    @Test
    void testOf8() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf8_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
    }

    @Test
    void testOf9() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf9_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
    }

    @Test
    void testOf10() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf10_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
    }

    @Test
    void testOf11() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf11_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
    }

    @Test
    void testOf12() {
        assert ["value"] == List.of("elements")
        assert [] == List.of("elements")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testOf12_ThrowsNullPointerException() {
        List.of("elements")
    }

    @Test
    void testCopyOf() {
        assert ["value"] == List.copyOf(["value"])
        assert [] == List.copyOf(["value"])
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testCopyOf_ThrowsNullPointerException() {
        List.copyOf(["value"])
    }
}
