package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
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
        bean.setTheInt(0)
        bean.setOtherBeanMatrix([] as OtherSubBean[][])
        def otherSubBean = new OtherSubBean()
        otherSubBean.setOtherBeanID("otherBeanID")
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherBeanArray([otherSubBean] as OtherSubBean[])
        def otherSubBean1 = new OtherSubBean()
        otherSubBean1.setOtherBeanID("otherBeanID")
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherSubBeans([otherSubBean1])
        bean.setValue(false)
        bean.setOtherValue(false)
        def subBean = new SubBean()
        subBean.setId(0L)
        subBean.setName("name")
        bean.setSubBean(subBean)
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
        bean.setTheInt(0)
        bean.setOtherBeanMatrix([] as OtherSubBean[][])
        def otherSubBean = new OtherSubBean()
        otherSubBean.setOtherBeanID("otherBeanID")
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherBeanArray([otherSubBean] as OtherSubBean[])
        def otherSubBean1 = new OtherSubBean()
        otherSubBean1.setOtherBeanID("otherBeanID")
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        bean.setOtherSubBeans([otherSubBean1])
        bean.setValue(false)
        bean.setOtherValue(false)
        def subBean = new SubBean()
        subBean.setId(0L)
        subBean.setName("name")
        bean.setSubBean(subBean)
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
