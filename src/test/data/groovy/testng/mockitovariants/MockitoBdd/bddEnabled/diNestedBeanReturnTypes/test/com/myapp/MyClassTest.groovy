package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @Test
    void testCreateNonBeanFoos() {
        // Setup
        // Configure FooCreator.createNonBeanFoos(...).
        def foos = Optional.of([new Foo<>(new FooData("purchaseId", "licenseName", new OtherData("dataName")))])
        given(mockFooCreator.createNonBeanFoos()).willReturn(foos)

        // Run the test
        def result = myClassUnderTest.createNonBeanFoos()

        // Verify the results
    }

    @Test
    void testCreateNonBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createNonBeanFoos()).willReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createNonBeanFoos()

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testCreateNonBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createNonBeanFoos()).willReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createNonBeanFoos()

        // Verify the results
        assert Optional.of([]) == result
    }

    @Test
    void testCreateNonBeanFooWithEquals() {
        // Setup
        def expectedResult = Optional.of(
                [new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))])

        // Configure FooCreator.createNonBeanFooWithEquals(...).
        def fooWithEquals = Optional.of(
                [new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))])
        given(mockFooCreator.createNonBeanFooWithEquals()).willReturn(fooWithEquals)

        // Run the test
        def result = myClassUnderTest.createNonBeanFooWithEquals()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testCreateNonBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createNonBeanFooWithEquals()).willReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createNonBeanFooWithEquals()

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testCreateNonBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createNonBeanFooWithEquals()).willReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createNonBeanFooWithEquals()

        // Verify the results
        assert Optional.of([]) == result
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
        given(mockFooCreator.createBeanFoos()).willReturn(foos)

        // Run the test
        def result = myClassUnderTest.createBeanFoos()

        // Verify the results
    }

    @Test
    void testCreateBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createBeanFoos()).willReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createBeanFoos()

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testCreateBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanFoos()).willReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createBeanFoos()

        // Verify the results
        assert Optional.of([]) == result
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
        given(mockFooCreator.createBeanFooWithEquals()).willReturn(fooWithEquals)

        // Run the test
        def result = myClassUnderTest.createBeanFooWithEquals()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testCreateBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createBeanFooWithEquals()).willReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createBeanFooWithEquals()

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testCreateBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanFooWithEquals()).willReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createBeanFooWithEquals()

        // Verify the results
        assert Optional.of([]) == result
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
        given(mockFooCreator.createBeanFooWithoutEquals()).willReturn(fooWithEquals)

        // Run the test
        def result = myClassUnderTest.createBeanFooWithoutEquals()

        // Verify the results
    }

    @Test
    void testCreateBeanFooWithoutEquals_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createBeanFooWithoutEquals()).willReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.createBeanFooWithoutEquals()

        // Verify the results
        assert Optional.empty() == result
    }

    @Test
    void testCreateBeanFooWithoutEquals_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanFooWithoutEquals()).willReturn(Optional.of([]))

        // Run the test
        def result = myClassUnderTest.createBeanFooWithoutEquals()

        // Verify the results
        assert Optional.of([]) == result
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
        given(mockFooCreator.createBeanInsideNestedLists()).willReturn(lists)

        // Run the test
        def result = myClassUnderTest.createBeanInsideNestedLists()

        // Verify the results
    }

    @Test
    void testCreateBeanInsideNestedLists_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanInsideNestedLists()).willReturn([])

        // Run the test
        def result = myClassUnderTest.createBeanInsideNestedLists()

        // Verify the results
        assert [] == result
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
        assert expectedResult == result
    }

    @Test
    void testCreateBeanWithEqualsInsideNestedLists2() {
        assert [] == myClassUnderTest.createBeanWithEqualsInsideNestedLists2()
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
        assert expectedResult == result
    }

    @Test
    void testGetNestedTypesThatDoNotOverrideEquals() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getNestedTypesThatDoNotOverrideEquals()

        // Verify the results
    }
}
