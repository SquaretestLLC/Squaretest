package com.myapp

import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testGetTheInts() {
        // Setup
        // Configure FooCreator.getTheInts(...).
        def ints = [0] as int[]
        when(mockFooCreator.getTheInts()).thenReturn(ints)

        // Run the test
        def result = myClassUnderTest.getTheInts()

        // Verify the results
        assert [0] as int[] == result
    }

    @Test
    void testGetTheInts_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheInts()).thenReturn([] as int[])

        // Run the test
        def result = myClassUnderTest.getTheInts()

        // Verify the results
        assert [] as int[] == result
    }

    @Test
    void testGetTheIntCube() {
        // Setup
        def expectedResult = [] as int[][][]

        // Configure FooCreator.getTheIntCube(...).
        def ints = [] as int[][][]
        when(mockFooCreator.getTheIntCube()).thenReturn(ints)

        // Run the test
        def result = myClassUnderTest.getTheIntCube()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetTheIntCube_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheIntCube()).thenReturn([] as int[][][])

        // Run the test
        def result = myClassUnderTest.getTheIntCube()

        // Verify the results
        assert [] as int[][][] == result
    }

    @Test
    void testGetTheIntArrays() {
        // Setup
        // Configure FooCreator.getTheIntArrays(...).
        def ints = [[0] as int[]]
        when(mockFooCreator.getTheIntArrays()).thenReturn(ints)

        // Run the test
        def result = myClassUnderTest.getTheIntArrays()

        // Verify the results
    }

    @Test
    void testGetTheIntArrays_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheIntArrays()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getTheIntArrays()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetTheTables() {
        // Setup
        // Configure FooCreator.getTheTables(...).
        def strings = [[] as String[][]]
        when(mockFooCreator.getTheTables()).thenReturn(strings)

        // Run the test
        def result = myClassUnderTest.getTheTables()

        // Verify the results
    }

    @Test
    void testGetTheTables_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheTables()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getTheTables()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetTheBeans() {
        // Setup
        // Configure FooCreator.getTheBeans(...).
        def bean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")
        def beans = [bean] as Bean[]
        when(mockFooCreator.getTheBeans()).thenReturn(beans)

        // Run the test
        def result = myClassUnderTest.getTheBeans()

        // Verify the results
    }

    @Test
    void testGetTheBeans_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheBeans()).thenReturn([] as Bean[])

        // Run the test
        def result = myClassUnderTest.getTheBeans()

        // Verify the results
        assert [] as Bean[] == result
    }

    @Test
    void testGetTheBeanMatrix() {
        // Setup
        // Configure FooCreator.getTheBeanMatrix(...).
        def beans = [] as Bean[][]
        when(mockFooCreator.getTheBeanMatrix()).thenReturn(beans)

        // Run the test
        def result = myClassUnderTest.getTheBeanMatrix()

        // Verify the results
    }

    @Test
    void testGetTheBeanMatrix_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheBeanMatrix()).thenReturn([] as Bean[][])

        // Run the test
        def result = myClassUnderTest.getTheBeanMatrix()

        // Verify the results
        assert [] as Bean[][] == result
    }

    @Test
    void testGetTheBeanArrays() {
        // Setup
        // Configure FooCreator.getTheBeanArrays(...).
        def bean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")
        def beans = [[bean] as Bean[]]
        when(mockFooCreator.getTheBeanArrays()).thenReturn(beans)

        // Run the test
        def result = myClassUnderTest.getTheBeanArrays()

        // Verify the results
    }

    @Test
    void testGetTheBeanArrays_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheBeanArrays()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getTheBeanArrays()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetTheByteArray() {
        // Setup
        when(mockFooCreator.getTheByteArray()).thenReturn("content".getBytes())

        // Run the test
        def result = myClassUnderTest.getTheByteArray()

        // Verify the results
        assert "content".getBytes() == result
    }

    @Test
    void testGetTheByteMatrix() {
        // Setup
        def expectedResult = [] as byte[][]

        // Configure FooCreator.getTheByteMatrix(...).
        def bytes = [] as byte[][]
        when(mockFooCreator.getTheByteMatrix()).thenReturn(bytes)

        // Run the test
        def result = myClassUnderTest.getTheByteMatrix()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetTheByteMatrix_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheByteMatrix()).thenReturn([] as byte[][])

        // Run the test
        def result = myClassUnderTest.getTheByteMatrix()

        // Verify the results
        assert [] as byte[][] == result
    }

    @Test
    void testGetTyeByteArrays() {
        // Setup
        when(mockFooCreator.getTheByteArrays()).thenReturn(["content".getBytes()])

        // Run the test
        def result = myClassUnderTest.getTyeByteArrays()

        // Verify the results
    }

    @Test
    void testGetTyeByteArrays_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheByteArrays()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getTyeByteArrays()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetTyeByteMatricies() {
        // Setup
        // Configure FooCreator.getTheByteMatricies(...).
        def bytes = [[] as byte[][]]
        when(mockFooCreator.getTheByteMatricies()).thenReturn(bytes)

        // Run the test
        def result = myClassUnderTest.getTyeByteMatricies()

        // Verify the results
    }

    @Test
    void testGetTyeByteMatricies_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheByteMatricies()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getTyeByteMatricies()

        // Verify the results
        assert [] == result
    }
}
