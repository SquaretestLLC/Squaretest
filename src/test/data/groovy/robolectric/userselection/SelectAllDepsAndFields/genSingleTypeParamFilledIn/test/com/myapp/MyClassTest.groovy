package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertFalse
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGet() {
        // Setup
        def expectedResult = new FooData5()
        expectedResult.setFooData5Id("fooData5Id")
        expectedResult.setFooData5Name("fooData5Name")

        // Run the test
        def result = myClassUnderTest.get(0)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testAddAll() {
        // Setup
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
        assertFalse(myClassUnderTest.removeAll(["value"]))
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

    @Test
    void testGetTheValues() {
        // Setup
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
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>())

        // Run the test
        def result = myClassUnderTest.getFooFromService("id")

        // Verify the results
        assert new MyList<>() == result
    }

    @Test
    void testLoadData1() {
        // Setup
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.loadData1(String.class, "theId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testLoadDatas1() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas1(String.class, "theId")

        // Verify the results
        assert [] == result
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
        assert expectedResult == result
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
        assert expectedResult == result
    }

    @Test
    void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.loadDatas2(FooData2.class, "theId")

        // Verify the results
        assert [] == result
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
        assert expectedResult == result
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
        assert expectedResult == result
    }
}
