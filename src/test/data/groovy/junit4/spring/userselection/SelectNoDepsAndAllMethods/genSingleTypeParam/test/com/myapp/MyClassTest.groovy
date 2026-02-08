package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.util.function.Consumer
import java.util.function.Supplier

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNull
import static org.mockito.Mockito.mock

@CompileStatic
class MyClassTest {

    private MyClass<String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>(null)
    }

    @Test
    void testGet() {
        assert "result" == myClassUnderTest.get(0)
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testGet_ThrowsIndexOutOfBoundsException() {
        myClassUnderTest.get(0)
    }

    @Test
    void testAddAll1() {
        assertFalse(myClassUnderTest.addAll(["value"]))
    }

    @Test(expected = NullPointerException.class)
    void testAddAll1_ThrowsNullPointerException() {
        myClassUnderTest.addAll(["value"])
    }

    @Test
    void testRemoveAll() {
        assertFalse(myClassUnderTest.removeAll(["value"]))
    }

    @Test(expected = ClassCastException.class)
    void testRemoveAll_ThrowsClassCastException() {
        myClassUnderTest.removeAll(["value"])
    }

    @Test(expected = NullPointerException.class)
    void testRemoveAll_ThrowsNullPointerException() {
        myClassUnderTest.removeAll(["value"])
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
        assertFalse(result)
    }

    @Test(expected = NullPointerException.class)
    void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        def filter = { val ->
            return false
        }

        // Run the test
        myClassUnderTest.removeIf(filter)
    }

    @Test
    void testGetTheValues() {
        assert new MyList<>(["value"]) == myClassUnderTest.getTheValues()
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
        assert expectedResult == result
    }

    @Test
    void testGetFooDatasEmpty() {
        assert new MyList<>() == myClassUnderTest.getFooDatasEmpty()
    }

    @Test
    void testGetFooDatas2() {
        assert [] == myClassUnderTest.getFooDatas(new ArrayList<>([new FooData1()]))
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

        // Run the test
        def result = myClassUnderTest.getFooFromService("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testLoadData1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.loadData1(String.class, "theId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testLoadDatas1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testLoadData2() {
        // Setup
        def expectedResult = new FooData2()
        expectedResult.setFooData2Id("fooData2Id")
        expectedResult.setFooData2Name("fooData2Name")

        // Run the test
        def result = myClassUnderTest.loadData2(FooData2.class, "theId")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testLoadDatas2() {
        // Setup
        def fooData2 = new FooData2()
        fooData2.setFooData2Id("fooData2Id")
        fooData2.setFooData2Name("fooData2Name")
        def expectedResult = [fooData2]

        // Run the test
        def result = myClassUnderTest.loadDatas2(FooData2.class, "theId")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testLoadFooData3() {
        // Setup
        def expectedResult = new FooData3()
        expectedResult.setFooData3Id("fooData3Id")
        expectedResult.setFooData3Name("fooData3Name")

        // Run the test
        def result = myClassUnderTest.loadFooData3("loadFooData3Param")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testLoadFooData4() {
        // Setup
        def expectedResult = new FooData4()
        expectedResult.setFooData4Id("fooData4Id")
        expectedResult.setFooData4Name("fooData4Name")

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
        assert 0 == myClassUnderTest.size()
    }

    @Test
    void testIsEmpty() {
        assertFalse(myClassUnderTest.isEmpty())
    }

    @Test
    void testContains() {
        assertFalse(myClassUnderTest.contains("o"))
    }

    @Test(expected = ClassCastException.class)
    void testContains_ThrowsClassCastException() {
        myClassUnderTest.contains("o")
    }

    @Test(expected = NullPointerException.class)
    void testContains_ThrowsNullPointerException() {
        myClassUnderTest.contains("o")
    }

    @Test
    void testIndexOf() {
        assert 0 == myClassUnderTest.indexOf("o")
    }

    @Test(expected = ClassCastException.class)
    void testIndexOf_ThrowsClassCastException() {
        myClassUnderTest.indexOf("o")
    }

    @Test(expected = NullPointerException.class)
    void testIndexOf_ThrowsNullPointerException() {
        myClassUnderTest.indexOf("o")
    }

    @Test
    void testLastIndexOf() {
        assert 0 == myClassUnderTest.lastIndexOf("o")
    }

    @Test(expected = ClassCastException.class)
    void testLastIndexOf_ThrowsClassCastException() {
        myClassUnderTest.lastIndexOf("o")
    }

    @Test(expected = NullPointerException.class)
    void testLastIndexOf_ThrowsNullPointerException() {
        myClassUnderTest.lastIndexOf("o")
    }

    @Test
    void testClone() {
        assert "result" == myClassUnderTest.clone()
    }

    @Test(expected = CloneNotSupportedException.class)
    void testClone_ThrowsCloneNotSupportedException() {
        myClassUnderTest.clone()
    }

    @Test
    void testToArray1() {
        assert ["result"] as Object[] == myClassUnderTest.toArray()
    }

    @Test
    void testToArray2() {
        assert ["result"] as String[] == myClassUnderTest.toArray(["a"] as String[])
        assert [] as String[] == myClassUnderTest.toArray(["a"] as String[])
    }

    @Test(expected = ArrayStoreException.class)
    void testToArray2_ThrowsArrayStoreException() {
        myClassUnderTest.toArray(["a"] as String[])
    }

    @Test(expected = NullPointerException.class)
    void testToArray2_ThrowsNullPointerException() {
        myClassUnderTest.toArray(["a"] as String[])
    }

    @Test
    void testSet() {
        assert "result" == myClassUnderTest.set(0, "element")
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testSet_ThrowsIndexOutOfBoundsException() {
        myClassUnderTest.set(0, "element")
    }

    @Test
    void testAdd1() {
        assertFalse(myClassUnderTest.add("e"))
    }

    @Test(expected = UnsupportedOperationException.class)
    void testAdd1_ThrowsUnsupportedOperationException() {
        myClassUnderTest.add("e")
    }

    @Test(expected = ClassCastException.class)
    void testAdd1_ThrowsClassCastException() {
        myClassUnderTest.add("e")
    }

    @Test(expected = NullPointerException.class)
    void testAdd1_ThrowsNullPointerException() {
        myClassUnderTest.add("e")
    }

    @Test(expected = IllegalArgumentException.class)
    void testAdd1_ThrowsIllegalArgumentException() {
        myClassUnderTest.add("e")
    }

    @Test
    void testAdd2() {
        // Setup
        // Run the test
        myClassUnderTest.add(0, "element")

        // Verify the results
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testAdd2_ThrowsIndexOutOfBoundsException() {
        // Setup
        // Run the test
        myClassUnderTest.add(0, "element")
    }

    @Test
    void testRemove1() {
        assert "result" == myClassUnderTest.remove(0)
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testRemove1_ThrowsIndexOutOfBoundsException() {
        myClassUnderTest.remove(0)
    }

    @Test
    void testEquals() {
        assertFalse(myClassUnderTest.equals("o"))
    }

    @Test
    void testHashCode() {
        assert 0 == myClassUnderTest.hashCode()
    }

    @Test
    void testRemove2() {
        assertFalse(myClassUnderTest.remove("o"))
    }

    @Test(expected = UnsupportedOperationException.class)
    void testRemove2_ThrowsUnsupportedOperationException() {
        myClassUnderTest.remove("o")
    }

    @Test(expected = ClassCastException.class)
    void testRemove2_ThrowsClassCastException() {
        myClassUnderTest.remove("o")
    }

    @Test(expected = NullPointerException.class)
    void testRemove2_ThrowsNullPointerException() {
        myClassUnderTest.remove("o")
    }

    @Test
    void testClear() {
        // Setup
        // Run the test
        myClassUnderTest.clear()

        // Verify the results
    }

    @Test(expected = UnsupportedOperationException.class)
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        // Run the test
        myClassUnderTest.clear()
    }

    @Test
    void testAddAll2() {
        assertFalse(myClassUnderTest.addAll(0, ["value"]))
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testAddAll2_ThrowsIndexOutOfBoundsException() {
        myClassUnderTest.addAll(0, ["value"])
    }

    @Test(expected = NullPointerException.class)
    void testAddAll2_ThrowsNullPointerException() {
        myClassUnderTest.addAll(0, ["value"])
    }

    @Test
    void testRetainAll() {
        assertFalse(myClassUnderTest.retainAll(["value"]))
    }

    @Test(expected = ClassCastException.class)
    void testRetainAll_ThrowsClassCastException() {
        myClassUnderTest.retainAll(["value"])
    }

    @Test(expected = NullPointerException.class)
    void testRetainAll_ThrowsNullPointerException() {
        myClassUnderTest.retainAll(["value"])
    }

    @Test
    void testListIterator1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.listIterator(0)

        // Verify the results
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        // Run the test
        myClassUnderTest.listIterator(0)
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
        assert ["value"].iterator() == myClassUnderTest.iterator()
    }

    @Test
    void testSubList() {
        assert ["value"] == myClassUnderTest.subList(0, 0)
        assert [] == myClassUnderTest.subList(0, 0)
    }

    @Test(expected = IndexOutOfBoundsException.class)
    void testSubList_ThrowsIndexOutOfBoundsException() {
        myClassUnderTest.subList(0, 0)
    }

    @Test(expected = IllegalArgumentException.class)
    void testSubList_ThrowsIllegalArgumentException() {
        myClassUnderTest.subList(0, 0)
    }

    @Test
    void testForEach() {
        // Setup
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)

        // Verify the results
    }

    @Test(expected = NullPointerException.class)
    void testForEach_ThrowsNullPointerException() {
        // Setup
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)
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

    @Test(expected = UnsupportedOperationException.class)
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)
    }

    @Test(expected = NullPointerException.class)
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)
    }

    @Test
    void testSort3() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)

        // Verify the results
    }

    @Test(expected = ClassCastException.class)
    void testSort3_ThrowsClassCastException() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)
    }

    @Test(expected = UnsupportedOperationException.class)
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)
    }

    @Test(expected = IllegalArgumentException.class)
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)
    }

    @Test
    void testContainsAll() {
        assertFalse(myClassUnderTest.containsAll(["value"]))
    }

    @Test(expected = ClassCastException.class)
    void testContainsAll_ThrowsClassCastException() {
        myClassUnderTest.containsAll(["value"])
    }

    @Test(expected = NullPointerException.class)
    void testContainsAll_ThrowsNullPointerException() {
        myClassUnderTest.containsAll(["value"])
    }

    @Test
    void testToString() {
        assert "[]" == myClassUnderTest.toString()
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
        assert ["result"] as String[] == result
    }

    @Test(expected = ArrayStoreException.class)
    void testToArray3_ThrowsArrayStoreException() {
        // Setup
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        myClassUnderTest.toArray(generator)
    }

    @Test(expected = NullPointerException.class)
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        myClassUnderTest.toArray(generator)
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
        assert [] == List.of()
    }

    @Test
    void testOf2() {
        assert ["value"] == List.of("e1")
        assert [] == List.of("e1")
    }

    @Test(expected = NullPointerException.class)
    void testOf2_ThrowsNullPointerException() {
        List.of("e1")
    }

    @Test
    void testOf3() {
        assert ["value"] == List.of("e1", "e2")
        assert [] == List.of("e1", "e2")
    }

    @Test(expected = NullPointerException.class)
    void testOf3_ThrowsNullPointerException() {
        List.of("e1", "e2")
    }

    @Test
    void testOf4() {
        assert ["value"] == List.of("e1", "e2", "e3")
        assert [] == List.of("e1", "e2", "e3")
    }

    @Test(expected = NullPointerException.class)
    void testOf4_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3")
    }

    @Test
    void testOf5() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4")
        assert [] == List.of("e1", "e2", "e3", "e4")
    }

    @Test(expected = NullPointerException.class)
    void testOf5_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4")
    }

    @Test
    void testOf6() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5")
    }

    @Test(expected = NullPointerException.class)
    void testOf6_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5")
    }

    @Test
    void testOf7() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6")
    }

    @Test(expected = NullPointerException.class)
    void testOf7_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6")
    }

    @Test
    void testOf8() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
    }

    @Test(expected = NullPointerException.class)
    void testOf8_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
    }

    @Test
    void testOf9() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
    }

    @Test(expected = NullPointerException.class)
    void testOf9_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
    }

    @Test
    void testOf10() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
    }

    @Test(expected = NullPointerException.class)
    void testOf10_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
    }

    @Test
    void testOf11() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
    }

    @Test(expected = NullPointerException.class)
    void testOf11_ThrowsNullPointerException() {
        List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
    }

    @Test
    void testOf12() {
        assert ["value"] == List.of("elements")
        assert [] == List.of("elements")
    }

    @Test(expected = NullPointerException.class)
    void testOf12_ThrowsNullPointerException() {
        List.of("elements")
    }

    @Test
    void testCopyOf() {
        assert ["value"] == List.copyOf(["value"])
        assert [] == List.copyOf(["value"])
    }

    @Test(expected = NullPointerException.class)
    void testCopyOf_ThrowsNullPointerException() {
        List.copyOf(["value"])
    }
}
