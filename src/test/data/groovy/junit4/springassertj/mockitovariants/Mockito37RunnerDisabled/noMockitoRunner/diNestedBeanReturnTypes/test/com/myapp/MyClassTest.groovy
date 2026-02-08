package com.myapp

import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
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
    void testCreateNonBeanFoos() {
        // Setup
        // Configure FooCreator.createNonBeanFoos(...).
        def foos = Optional.of([new Foo<>(new FooData("purchaseId", "licenseName", new OtherData("dataName")))])
        when(mockFooCreator.createNonBeanFoos()).thenReturn(foos)

        // Run the test
        def result = myClassUnderTest.createNonBeanFoos()

        // Verify the results
    }

    @Test
    void testCreateNonBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createNonBeanFoos()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createNonBeanFoos()

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testCreateNonBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createNonBeanFoos()).thenReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createNonBeanFoos()

        // Verify the results
        assertThat(result).isEqualTo(Optional.of([]))
    }

    @Test
    void testCreateNonBeanFooWithEquals() {
        // Setup
        def expectedResult = Optional.of(
                [new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))])

        // Configure FooCreator.createNonBeanFooWithEquals(...).
        def fooWithEquals = Optional.of(
                [new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))])
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(fooWithEquals)

        // Run the test
        def result = myClassUnderTest.createNonBeanFooWithEquals()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testCreateNonBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createNonBeanFooWithEquals()

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testCreateNonBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createNonBeanFooWithEquals()

        // Verify the results
        assertThat(result).isEqualTo(Optional.of([]))
    }

    @Test
    void testCreateBeanFoos() {
        // Setup
        // Configure FooCreator.createBeanFoos(...).
        def bean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")
        def foos = Optional.of([new Foo<>(bean)])
        when(mockFooCreator.createBeanFoos()).thenReturn(foos)

        // Run the test
        def result = myClassUnderTest.createBeanFoos()

        // Verify the results
    }

    @Test
    void testCreateBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFoos()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createBeanFoos()

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testCreateBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFoos()).thenReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createBeanFoos()

        // Verify the results
        assertThat(result).isEqualTo(Optional.of([]))
    }

    @Test
    void testCreateBeanFooWithEquals() {
        // Setup
        def beanWithEquals = new BeanWithEquals()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        beanWithEquals.setFooData(fooData)
        beanWithEquals.setTheString("theString")
        def expectedResult = Optional.of([new FooWithEquals<>(beanWithEquals)])

        // Configure FooCreator.createBeanFooWithEquals(...).
        def beanWithEquals1 = new BeanWithEquals()
        def fooData1 = new FooData()
        fooData1.setPurchaseId("purchaseId")
        fooData1.setNameOnTheLicense("nameOnTheLicense")
        fooData1.setOtherData(new OtherData("dataName"))
        beanWithEquals1.setFooData(fooData1)
        beanWithEquals1.setTheString("theString")
        def fooWithEquals = Optional.of([new FooWithEquals<>(beanWithEquals1)])
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(fooWithEquals)

        // Run the test
        def result = myClassUnderTest.createBeanFooWithEquals()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testCreateBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createBeanFooWithEquals()

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testCreateBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createBeanFooWithEquals()

        // Verify the results
        assertThat(result).isEqualTo(Optional.of([]))
    }

    @Test
    void testCreateBeanFooWithoutEquals() {
        // Setup
        // Configure FooCreator.createBeanFooWithoutEquals(...).
        def bean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")
        def fooWithEquals = Optional.of([new FooWithEquals<>(bean)])
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(fooWithEquals)

        // Run the test
        def result = myClassUnderTest.createBeanFooWithoutEquals()

        // Verify the results
    }

    @Test
    void testCreateBeanFooWithoutEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createBeanFooWithoutEquals()

        // Verify the results
        assertThat(result).isEmpty()
    }

    @Test
    void testCreateBeanFooWithoutEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createBeanFooWithoutEquals()

        // Verify the results
        assertThat(result).isEqualTo(Optional.of([]))
    }

    @Test
    void testCreateBeanInsideNestedLists() {
        // Setup
        // Configure FooCreator.createBeanInsideNestedLists(...).
        def bean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")
        def lists = [[bean]]
        when(mockFooCreator.createBeanInsideNestedLists()).thenReturn(lists)

        // Run the test
        def result = myClassUnderTest.createBeanInsideNestedLists()

        // Verify the results
    }

    @Test
    void testCreateBeanInsideNestedLists_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanInsideNestedLists()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.createBeanInsideNestedLists()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testCreateBeanWithEqualsInsideNestedLists1() {
        // Setup
        def beanWithEquals = new BeanWithEquals()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        beanWithEquals.setFooData(fooData)
        beanWithEquals.setTheString("theString")
        def expectedResult = [[beanWithEquals]]

        // Run the test
        def result = myClassUnderTest.createBeanWithEqualsInsideNestedLists1()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testCreateBeanWithEqualsInsideNestedLists2() {
        assertThat(myClassUnderTest.createBeanWithEqualsInsideNestedLists2()).isEqualTo([])
    }

    @Test
    void testGetNestedTypesThatOverrideEquals() {
        // Setup
        def beanWithEquals = new BeanWithEquals()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        beanWithEquals.setFooData(fooData)
        beanWithEquals.setTheString("theString")
        def expectedResult = ["value": ["value": new FooWithEquals<>(beanWithEquals)]]

        // Run the test
        def result = myClassUnderTest.getNestedTypesThatOverrideEquals()

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetNestedTypesThatDoNotOverrideEquals() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getNestedTypesThatDoNotOverrideEquals()

        // Verify the results
    }
}
