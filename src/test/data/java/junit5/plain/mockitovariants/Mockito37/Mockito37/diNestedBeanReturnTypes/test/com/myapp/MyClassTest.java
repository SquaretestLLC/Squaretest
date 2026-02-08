package com.myapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testCreateNonBeanFoos() {
        // Setup
        // Configure FooCreator.createNonBeanFoos(...).
        final Optional<List<Foo<FooData>>> foos = Optional.of(
                Arrays.asList(new Foo<>(new FooData("purchaseId", "licenseName", new OtherData("dataName")))));
        when(mockFooCreator.createNonBeanFoos()).thenReturn(foos);

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
    }

    @Test
    void testCreateNonBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createNonBeanFoos()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testCreateNonBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createNonBeanFoos()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testCreateNonBeanFooWithEquals() {
        // Setup
        final Optional<List<FooWithEquals<FooDataWithEquals>>> expectedResult = Optional.of(Arrays.asList(
                new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))));

        // Configure FooCreator.createNonBeanFooWithEquals(...).
        final Optional<List<FooWithEquals<FooDataWithEquals>>> fooWithEquals = Optional.of(Arrays.asList(
                new FooWithEquals<>(new FooDataWithEquals("purchaseId", "licenseName", new OtherData("dataName")))));
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCreateNonBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testCreateNonBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testCreateBeanFoos() {
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
        when(mockFooCreator.createBeanFoos()).thenReturn(foos);

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
    }

    @Test
    void testCreateBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFoos()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testCreateBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFoos()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testCreateBeanFooWithEquals() {
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
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCreateBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testCreateBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testCreateBeanFooWithoutEquals() {
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
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
    }

    @Test
    void testCreateBeanFooWithoutEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testCreateBeanFooWithoutEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }

    @Test
    void testCreateBeanInsideNestedLists() {
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
        when(mockFooCreator.createBeanInsideNestedLists()).thenReturn(lists);

        // Run the test
        final List<List<Bean>> result = myClassUnderTest.createBeanInsideNestedLists();

        // Verify the results
    }

    @Test
    void testCreateBeanInsideNestedLists_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanInsideNestedLists()).thenReturn(Collections.emptyList());

        // Run the test
        final List<List<Bean>> result = myClassUnderTest.createBeanInsideNestedLists();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testCreateBeanWithEqualsInsideNestedLists1() {
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
    void testCreateBeanWithEqualsInsideNestedLists2() {
        assertEquals(Collections.emptyList(), myClassUnderTest.createBeanWithEqualsInsideNestedLists2());
    }

    @Test
    void testGetNestedTypesThatOverrideEquals() {
        // Setup
        final Map<String, Map<String, FooWithEquals<BeanWithEquals>>> expectedResult = new HashMap<>();

        // Run the test
        final Map<String, Map<String, FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.getNestedTypesThatOverrideEquals();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNestedTypesThatDoNotOverrideEquals() {
        // Setup
        // Run the test
        final Map<String, Map<String, FooWithEquals<Bean>>> result = myClassUnderTest.getNestedTypesThatDoNotOverrideEquals();

        // Verify the results
    }
}
