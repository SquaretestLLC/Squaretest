package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    public void testCreateNonBeanFoos() {
        // Setup
        // Configure FooCreator.createNonBeanFoos(...).
        final Optional<List<Foo<FooData>>> foos = Optional.of(
                Arrays.asList(new Foo<>(new FooData("purchaseId", "licenseName", new OtherData("dataName")))));
        given(mockFooCreator.createNonBeanFoos()).willReturn(foos);

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
    }

    @Test
    public void testCreateNonBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createNonBeanFoos()).willReturn(Optional.empty());

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCreateNonBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createNonBeanFoos()).willReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    public void testCreateNonBeanFooWithEquals() {
        // Setup
        final Optional<List<FooWithEquals<FooDataWithEquals>>> expectedResult = Optional.of(Arrays.asList(
                new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))));

        // Configure FooCreator.createNonBeanFooWithEquals(...).
        final Optional<List<FooWithEquals<FooDataWithEquals>>> fooWithEquals = Optional.of(Arrays.asList(
                new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))));
        given(mockFooCreator.createNonBeanFooWithEquals()).willReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateNonBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createNonBeanFooWithEquals()).willReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCreateNonBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createNonBeanFooWithEquals()).willReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    public void testCreateBeanFoos() {
        // Setup
        // Configure FooCreator.createBeanFoos(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final Optional<List<Foo<Bean>>> foos = Optional.of(Arrays.asList(new Foo<>(bean)));
        given(mockFooCreator.createBeanFoos()).willReturn(foos);

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
    }

    @Test
    public void testCreateBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createBeanFoos()).willReturn(Optional.empty());

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCreateBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanFoos()).willReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    public void testCreateBeanFooWithEquals() {
        // Setup
        final BeanWithEquals beanWithEquals = new BeanWithEquals();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        beanWithEquals.setFooData(fooData);
        beanWithEquals.setTheString("theString");
        final Optional<List<FooWithEquals<BeanWithEquals>>> expectedResult = Optional.of(
                Arrays.asList(new FooWithEquals<>(beanWithEquals)));

        // Configure FooCreator.createBeanFooWithEquals(...).
        final BeanWithEquals beanWithEquals1 = new BeanWithEquals();
        final FooData fooData1 = new FooData();
        fooData1.setPurchaseId("purchaseId");
        fooData1.setNameOnTheLicense("nameOnTheLicense");
        fooData1.setOtherData(new OtherData("dataName"));
        beanWithEquals1.setFooData(fooData1);
        beanWithEquals1.setTheString("theString");
        final Optional<List<FooWithEquals<BeanWithEquals>>> fooWithEquals = Optional.of(
                Arrays.asList(new FooWithEquals<>(beanWithEquals1)));
        given(mockFooCreator.createBeanFooWithEquals()).willReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createBeanFooWithEquals()).willReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCreateBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanFooWithEquals()).willReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    public void testCreateBeanFooWithoutEquals() {
        // Setup
        // Configure FooCreator.createBeanFooWithoutEquals(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final Optional<List<FooWithEquals<Bean>>> fooWithEquals = Optional.of(Arrays.asList(new FooWithEquals<>(bean)));
        given(mockFooCreator.createBeanFooWithoutEquals()).willReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
    }

    @Test
    public void testCreateBeanFooWithoutEquals_FooCreatorReturnsAbsent() {
        // Setup
        given(mockFooCreator.createBeanFooWithoutEquals()).willReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testCreateBeanFooWithoutEquals_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanFooWithoutEquals()).willReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    public void testCreateBeanInsideNestedLists() {
        // Setup
        // Configure FooCreator.createBeanInsideNestedLists(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final List<List<Bean>> lists = Arrays.asList(Arrays.asList(bean));
        given(mockFooCreator.createBeanInsideNestedLists()).willReturn(lists);

        // Run the test
        final List<List<Bean>> result = myClassUnderTest.createBeanInsideNestedLists();

        // Verify the results
    }

    @Test
    public void testCreateBeanInsideNestedLists_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.createBeanInsideNestedLists()).willReturn(Collections.emptyList());

        // Run the test
        final List<List<Bean>> result = myClassUnderTest.createBeanInsideNestedLists();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testCreateBeanWithEqualsInsideNestedLists1() {
        // Setup
        final BeanWithEquals beanWithEquals = new BeanWithEquals();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        beanWithEquals.setFooData(fooData);
        beanWithEquals.setTheString("theString");
        final List<List<BeanWithEquals>> expectedResult = Arrays.asList(Arrays.asList(beanWithEquals));

        // Run the test
        final List<List<BeanWithEquals>> result = myClassUnderTest.createBeanWithEqualsInsideNestedLists1();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateBeanWithEqualsInsideNestedLists2() {
        assertEquals(Collections.emptyList(), myClassUnderTest.createBeanWithEqualsInsideNestedLists2());
    }

    @Test
    public void testGetNestedTypesThatOverrideEquals() {
        // Setup
        final Map<String, Map<String, FooWithEquals<BeanWithEquals>>> expectedResult = new HashMap<>();

        // Run the test
        final Map<String, Map<String, FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.getNestedTypesThatOverrideEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetNestedTypesThatDoNotOverrideEquals() {
        // Setup
        // Run the test
        final Map<String, Map<String, FooWithEquals<Bean>>> result = myClassUnderTest.getNestedTypesThatDoNotOverrideEquals();

        // Verify the results
    }
}
