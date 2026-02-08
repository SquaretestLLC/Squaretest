package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @Test
    void testGetTheInts() {
        // Setup
        // Configure FooCreator.getTheInts(...).
        def ints = [0] as int[]
        given(mockFooCreator.getTheInts()).willReturn(ints)

        // Run the test
        def result = myClassUnderTest.getTheInts()

        // Verify the results
        assertThat(result).isEqualTo([0] as int[])
    }

    @Test
    void testGetTheInts_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheInts()).willReturn([] as int[])

        // Run the test
        def result = myClassUnderTest.getTheInts()

        // Verify the results
        assertThat(result).isEqualTo([] as int[])
    }

    @Test
    void testGetTheIntCube() {
        // Setup
        def expectedResult = [] as int[][][]

        // Configure FooCreator.getTheIntCube(...).
        def ints = [] as int[][][]
        given(mockFooCreator.getTheIntCube()).willReturn(ints)

        // Run the test
        def result = myClassUnderTest.getTheIntCube()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetTheIntCube_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheIntCube()).willReturn([] as int[][][])

        // Run the test
        def result = myClassUnderTest.getTheIntCube()

        // Verify the results
        assertThat(result).isEqualTo([] as int[][][])
    }

    @Test
    void testGetTheIntArrays() {
        // Setup
        // Configure FooCreator.getTheIntArrays(...).
        def ints = [[0] as int[]]
        given(mockFooCreator.getTheIntArrays()).willReturn(ints)

        // Run the test
        def result = myClassUnderTest.getTheIntArrays()

        // Verify the results
    }

    @Test
    void testGetTheIntArrays_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheIntArrays()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getTheIntArrays()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetTheTables() {
        // Setup
        // Configure FooCreator.getTheTables(...).
        def strings = [[] as String[][]]
        given(mockFooCreator.getTheTables()).willReturn(strings)

        // Run the test
        def result = myClassUnderTest.getTheTables()

        // Verify the results
    }

    @Test
    void testGetTheTables_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheTables()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getTheTables()

        // Verify the results
        assertThat(result).isEqualTo([])
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
        given(mockFooCreator.getTheBeans()).willReturn(beans)

        // Run the test
        def result = myClassUnderTest.getTheBeans()

        // Verify the results
    }

    @Test
    void testGetTheBeans_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheBeans()).willReturn([] as Bean[])

        // Run the test
        def result = myClassUnderTest.getTheBeans()

        // Verify the results
        assertThat(result).isEqualTo([] as Bean[])
    }

    @Test
    void testGetTheBeanMatrix() {
        // Setup
        // Configure FooCreator.getTheBeanMatrix(...).
        def beans = [] as Bean[][]
        given(mockFooCreator.getTheBeanMatrix()).willReturn(beans)

        // Run the test
        def result = myClassUnderTest.getTheBeanMatrix()

        // Verify the results
    }

    @Test
    void testGetTheBeanMatrix_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheBeanMatrix()).willReturn([] as Bean[][])

        // Run the test
        def result = myClassUnderTest.getTheBeanMatrix()

        // Verify the results
        assertThat(result).isEqualTo([] as Bean[][])
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
        given(mockFooCreator.getTheBeanArrays()).willReturn(beans)

        // Run the test
        def result = myClassUnderTest.getTheBeanArrays()

        // Verify the results
    }

    @Test
    void testGetTheBeanArrays_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheBeanArrays()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getTheBeanArrays()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetTheByteArray() {
        // Setup
        given(mockFooCreator.getTheByteArray()).willReturn("content".getBytes())

        // Run the test
        def result = myClassUnderTest.getTheByteArray()

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes())
    }

    @Test
    void testGetTheByteMatrix() {
        // Setup
        def expectedResult = [] as byte[][]

        // Configure FooCreator.getTheByteMatrix(...).
        def bytes = [] as byte[][]
        given(mockFooCreator.getTheByteMatrix()).willReturn(bytes)

        // Run the test
        def result = myClassUnderTest.getTheByteMatrix()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetTheByteMatrix_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheByteMatrix()).willReturn([] as byte[][])

        // Run the test
        def result = myClassUnderTest.getTheByteMatrix()

        // Verify the results
        assertThat(result).isEqualTo([] as byte[][])
    }

    @Test
    void testGetTyeByteArrays() {
        // Setup
        given(mockFooCreator.getTheByteArrays()).willReturn(["content".getBytes()])

        // Run the test
        def result = myClassUnderTest.getTyeByteArrays()

        // Verify the results
    }

    @Test
    void testGetTyeByteArrays_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheByteArrays()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getTyeByteArrays()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetTyeByteMatricies() {
        // Setup
        // Configure FooCreator.getTheByteMatricies(...).
        def bytes = [[] as byte[][]]
        given(mockFooCreator.getTheByteMatricies()).willReturn(bytes)

        // Run the test
        def result = myClassUnderTest.getTyeByteMatricies()

        // Verify the results
    }

    @Test
    void testGetTyeByteMatricies_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheByteMatricies()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getTyeByteMatricies()

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}
