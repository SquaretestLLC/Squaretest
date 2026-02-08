package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.function.Supplier

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MyList<String> mockValues

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGet() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertThat(myClassUnderTest.get(0)).isEqualTo("result")
        assertThatThrownBy({
            myClassUnderTest.get(0)
        }).isInstanceOf(IndexOutOfBoundsException.class)
    }

    @Test
    void testAddAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertThat(myClassUnderTest.addAll(["value"])).isFalse()
        assertThatThrownBy({
            myClassUnderTest.addAll(["value"])
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testRemoveAll() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
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
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertThat(myClassUnderTest.getTheValues()).isEqualTo(mockValues)
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
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooDatasEmpty() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertThat(myClassUnderTest.getFooDatasEmpty()).isEqualTo(new MyList<>())
    }

    @Test
    void testGetFooDatas2() {
        def myClassUnderTest = new MyClass<>(mockFooService)
        // TODO: Set the following fields: values.
        assertThat(myClassUnderTest.getFooDatas(new ArrayList<>([new FooData1()]))).isEqualTo([])
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(new MyList<>())
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
        assertThat(result).isEqualTo("result")
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
        assertThat(result).isEqualTo(["value"])
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
        assertThat(result).isEqualTo([])
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo(expectedResult)
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
        assertThat(result).isEqualTo([])
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
        assertThat(result).isEqualTo(expectedResult)
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
}
