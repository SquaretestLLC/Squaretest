package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import java.util.function.Consumer
import java.util.function.Supplier

import static org.mockito.Mockito.mock
import static org.testng.Assert.*

@CompileStatic
class MyClassTest {

    @Test
    void testGet() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.get(0)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testAddAll1() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        def result = myClassUnderTest.addAll(c)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testRemoveAll() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.removeAll(["value"]))
    }

    @Test
    void testRemoveIf() {
        // Setup
        def myClassUnderTest = new MyClass(null)
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
        // Setup
        def myClassUnderTest = new MyClass(null)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def expectedResult = new MyList<>([fooData5])

        // Run the test
        def result = myClassUnderTest.getTheValues()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFooDatas1() {
        // Setup
        def myClassUnderTest = new MyClass(null)
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
    void testGetFooDatas2() {
        // Setup
        def myClassUnderTest = new MyClass(null)
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
        assert expectedResult == result
    }

    @Test
    void testGetFooFromService() {
        // Setup
        def myClassUnderTest = new MyClass(null)
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
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.loadData1(String.class, "theId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testLoadDatas1() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testLoadData2() {
        // Setup
        def myClassUnderTest = new MyClass(null)
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
        def myClassUnderTest = new MyClass(null)
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
        def myClassUnderTest = new MyClass(null)
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
        def myClassUnderTest = new MyClass(null)
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
        assert -1 == BaseClass.binarySearch([], "key")
    }

    @Test
    void testBinarySearch2() {
        assert -1 == BaseClass.binarySearch(["value"], "key", Comparator.comparing(Object.&toString))
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
        assertNull(BaseClass.min(["value"]))
    }

    @Test
    void testDefaultIfBlank() {
        assert "result" == BaseClass.defaultIfBlank("str", "defaultStr")
    }

    @Test
    void testFirstNonBlank() {
        assert "result" == BaseClass.firstNonBlank("values")
    }

    @Test
    void testGetIfBlank() {
        assert "result" == BaseClass.getIfBlank("str", { "value" } as Supplier)
    }

    @Test
    void testGetIfEmpty() {
        assert "result" == BaseClass.getIfEmpty("str", { "value" } as Supplier)
    }

    @Test
    void testJoin() {
        assert "result" == BaseClass.join("elements")
    }

    @Test
    void testTrimToSize() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        myClassUnderTest.trimToSize()

        // Verify the results
    }

    @Test
    void testEnsureCapacity() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        myClassUnderTest.ensureCapacity(0)

        // Verify the results
    }

    @Test
    void testSize() {
        def myClassUnderTest = new MyClass(null)
        assert 0 == myClassUnderTest.size()
    }

    @Test
    void testIsEmpty() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.isEmpty())
    }

    @Test
    void testContains() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.contains("o"))
        assertThrows(ClassCastException.class, {
            myClassUnderTest.contains("o")
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.contains("o")
        })
    }

    @Test
    void testIndexOf() {
        def myClassUnderTest = new MyClass(null)
        assert 0 == myClassUnderTest.indexOf("o")
        assertThrows(ClassCastException.class, {
            myClassUnderTest.indexOf("o")
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.indexOf("o")
        })
    }

    @Test
    void testLastIndexOf() {
        def myClassUnderTest = new MyClass(null)
        assert 0 == myClassUnderTest.lastIndexOf("o")
        assertThrows(ClassCastException.class, {
            myClassUnderTest.lastIndexOf("o")
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.lastIndexOf("o")
        })
    }

    @Test
    void testClone() {
        def myClassUnderTest = new MyClass(null)
        assert "result" == myClassUnderTest.clone()
    }

    @Test
    void testToArray1() {
        def myClassUnderTest = new MyClass(null)
        assert ["result"] as Object[] == myClassUnderTest.toArray()
    }

    @Test
    void testToArray2() {
        def myClassUnderTest = new MyClass(null)
        assert ["result"] as String[] == myClassUnderTest.toArray(["a"] as String[])
        assert [] as String[] == myClassUnderTest.toArray(["a"] as String[])
        assertThrows(ArrayStoreException.class, {
            myClassUnderTest.toArray(["a"] as String[])
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.toArray(["a"] as String[])
        })
    }

    @Test
    void testSet() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.set(0, element)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testSet_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, {
            myClassUnderTest.set(0, element)
        })
    }

    @Test
    void testAdd1() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.add(e)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testAdd1_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.add(e)
        })
    }

    @Test
    void testAdd1_ThrowsClassCastException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThrows(ClassCastException.class, {
            myClassUnderTest.add(e)
        })
    }

    @Test
    void testAdd1_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThrows(NullPointerException.class, {
            myClassUnderTest.add(e)
        })
    }

    @Test
    void testAdd1_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def e = new FooData5()
        e.setFooData5Id("fooData5Id")
        e.setFooData5Name("fooData5Name")

        // Run the test
        assertThrows(IllegalArgumentException.class, {
            myClassUnderTest.add(e)
        })
    }

    @Test
    void testAdd2() {
        // Setup
        def myClassUnderTest = new MyClass(null)
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
        def myClassUnderTest = new MyClass(null)
        def element = new FooData5()
        element.setFooData5Id("fooData5Id")
        element.setFooData5Name("fooData5Name")

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, {
            myClassUnderTest.add(0, element)
        })
    }

    @Test
    void testRemove1() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.remove(0)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testRemove1_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, {
            myClassUnderTest.remove(0)
        })
    }

    @Test
    void testEquals() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.equals("o"))
    }

    @Test
    void testHashCode() {
        def myClassUnderTest = new MyClass(null)
        assert 0 == myClassUnderTest.hashCode()
    }

    @Test
    void testRemove2() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.remove("o"))
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.remove("o")
        })
        assertThrows(ClassCastException.class, {
            myClassUnderTest.remove("o")
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.remove("o")
        })
    }

    @Test
    void testClear() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        myClassUnderTest.clear()

        // Verify the results
    }

    @Test
    void testClear_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.clear()
        })
    }

    @Test
    void testAddAll2() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        def result = myClassUnderTest.addAll(0, c)

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testAddAll2_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, {
            myClassUnderTest.addAll(0, c)
        })
    }

    @Test
    void testAddAll2_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def c = [fooData5]

        // Run the test
        assertThrows(NullPointerException.class, {
            myClassUnderTest.addAll(0, c)
        })
    }

    @Test
    void testRetainAll() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.retainAll(["value"]))
        assertThrows(ClassCastException.class, {
            myClassUnderTest.retainAll(["value"])
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.retainAll(["value"])
        })
    }

    @Test
    void testListIterator1() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.listIterator(0)

        // Verify the results
    }

    @Test
    void testListIterator1_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, {
            myClassUnderTest.listIterator(0)
        })
    }

    @Test
    void testListIterator2() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.listIterator()

        // Verify the results
    }

    @Test
    void testIterator() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.iterator()

        // Verify the results
    }

    @Test
    void testSubList() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def fooData5 = new FooData5()
        fooData5.setFooData5Id("fooData5Id")
        fooData5.setFooData5Name("fooData5Name")
        def expectedResult = [fooData5]

        // Run the test
        def result = myClassUnderTest.subList(0, 0)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testSubList_ThrowsIndexOutOfBoundsException() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        assertThrows(IndexOutOfBoundsException.class, {
            myClassUnderTest.subList(0, 0)
        })
    }

    @Test
    void testSubList_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        assertThrows(IllegalArgumentException.class, {
            myClassUnderTest.subList(0, 0)
        })
    }

    @Test
    void testForEach() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def mockAction = mock(Consumer.class)

        // Run the test
        myClassUnderTest.forEach(mockAction)

        // Verify the results
    }

    @Test
    void testForEach_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def mockAction = mock(Consumer.class)

        // Run the test
        assertThrows(NullPointerException.class, {
            myClassUnderTest.forEach(mockAction)
        })
    }

    @Test
    void testSpliterator() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.spliterator()

        // Verify the results
    }

    @Test
    void testReplaceAll() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def operator = { val -> val }

        // Run the test
        myClassUnderTest.replaceAll(operator)

        // Verify the results
    }

    @Test
    void testReplaceAll_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def operator = { val -> val }

        // Run the test
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.replaceAll(operator)
        })
    }

    @Test
    void testReplaceAll_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def operator = { val -> val }

        // Run the test
        assertThrows(NullPointerException.class, {
            myClassUnderTest.replaceAll(operator)
        })
    }

    @Test
    void testSort3() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        myClassUnderTest.sort(c)

        // Verify the results
    }

    @Test
    void testSort3_ThrowsClassCastException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThrows(ClassCastException.class, {
            myClassUnderTest.sort(c)
        })
    }

    @Test
    void testSort3_ThrowsUnsupportedOperationException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.sort(c)
        })
    }

    @Test
    void testSort3_ThrowsIllegalArgumentException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def c = Comparator.comparing(Object.&toString)

        // Run the test
        assertThrows(IllegalArgumentException.class, {
            myClassUnderTest.sort(c)
        })
    }

    @Test
    void testContainsAll() {
        def myClassUnderTest = new MyClass(null)
        assertFalse(myClassUnderTest.containsAll(["value"]))
        assertThrows(ClassCastException.class, {
            myClassUnderTest.containsAll(["value"])
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.containsAll(["value"])
        })
    }

    @Test
    void testToString() {
        def myClassUnderTest = new MyClass(null)
        assert "[]" == myClassUnderTest.toString()
    }

    @Test
    void testToArray3() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        def result = myClassUnderTest.toArray(generator)

        // Verify the results
        assert ["result"] as String[] == result
    }

    @Test
    void testToArray3_ThrowsArrayStoreException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        assertThrows(ArrayStoreException.class, {
            myClassUnderTest.toArray(generator)
        })
    }

    @Test
    void testToArray3_ThrowsNullPointerException() {
        // Setup
        def myClassUnderTest = new MyClass(null)
        def generator = { val ->
            return ["value"] as String[]
        }

        // Run the test
        assertThrows(NullPointerException.class, {
            myClassUnderTest.toArray(generator)
        })
    }

    @Test
    void testStream() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.stream()

        // Verify the results
    }

    @Test
    void testParallelStream() {
        // Setup
        def myClassUnderTest = new MyClass(null)

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
        assertThrows(NullPointerException.class, {
            List.of("e1")
        })
    }

    @Test
    void testOf3() {
        assert ["value"] == List.of("e1", "e2")
        assert [] == List.of("e1", "e2")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2")
        })
    }

    @Test
    void testOf4() {
        assert ["value"] == List.of("e1", "e2", "e3")
        assert [] == List.of("e1", "e2", "e3")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3")
        })
    }

    @Test
    void testOf5() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4")
        assert [] == List.of("e1", "e2", "e3", "e4")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4")
        })
    }

    @Test
    void testOf6() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4", "e5")
        })
    }

    @Test
    void testOf7() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4", "e5", "e6")
        })
    }

    @Test
    void testOf8() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7")
        })
    }

    @Test
    void testOf9() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8")
        })
    }

    @Test
    void testOf10() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9")
        })
    }

    @Test
    void testOf11() {
        assert ["value"] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
        assert [] == List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
        assertThrows(NullPointerException.class, {
            List.of("e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10")
        })
    }

    @Test
    void testOf12() {
        assert ["value"] == List.of("elements")
        assert [] == List.of("elements")
        assertThrows(NullPointerException.class, {
            List.of("elements")
        })
    }

    @Test
    void testCopyOf() {
        assert ["value"] == List.copyOf(["value"])
        assert [] == List.copyOf(["value"])
        assertThrows(NullPointerException.class, {
            List.copyOf(["value"])
        })
    }
}
