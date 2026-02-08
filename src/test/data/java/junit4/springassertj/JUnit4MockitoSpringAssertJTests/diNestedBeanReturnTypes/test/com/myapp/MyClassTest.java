package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    public void testCreateNonBeanFoos() {
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
    public void testCreateNonBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createNonBeanFoos()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testCreateNonBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createNonBeanFoos()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<Foo<FooData>>> result = myClassUnderTest.createNonBeanFoos();

        // Verify the results
        assertThat(result).isEqualTo(Optional.of(Collections.emptyList()));
    }

    @Test
    public void testCreateNonBeanFooWithEquals() {
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateNonBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testCreateNonBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createNonBeanFooWithEquals()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<FooDataWithEquals>>> result = myClassUnderTest.createNonBeanFooWithEquals();

        // Verify the results
        assertThat(result).isEqualTo(Optional.of(Collections.emptyList()));
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
        when(mockFooCreator.createBeanFoos()).thenReturn(foos);

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
    }

    @Test
    public void testCreateBeanFoos_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFoos()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testCreateBeanFoos_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFoos()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<Foo<Bean>>> result = myClassUnderTest.createBeanFoos();

        // Verify the results
        assertThat(result).isEqualTo(Optional.of(Collections.emptyList()));
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
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateBeanFooWithEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testCreateBeanFooWithEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFooWithEquals()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.createBeanFooWithEquals();

        // Verify the results
        assertThat(result).isEqualTo(Optional.of(Collections.emptyList()));
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
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(fooWithEquals);

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
    }

    @Test
    public void testCreateBeanFooWithoutEquals_FooCreatorReturnsAbsent() {
        // Setup
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(Optional.empty());

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testCreateBeanFooWithoutEquals_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanFooWithoutEquals()).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final Optional<List<FooWithEquals<Bean>>> result = myClassUnderTest.createBeanFooWithoutEquals();

        // Verify the results
        assertThat(result).isEqualTo(Optional.of(Collections.emptyList()));
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
        when(mockFooCreator.createBeanInsideNestedLists()).thenReturn(lists);

        // Run the test
        final List<List<Bean>> result = myClassUnderTest.createBeanInsideNestedLists();

        // Verify the results
    }

    @Test
    public void testCreateBeanInsideNestedLists_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.createBeanInsideNestedLists()).thenReturn(Collections.emptyList());

        // Run the test
        final List<List<Bean>> result = myClassUnderTest.createBeanInsideNestedLists();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateBeanWithEqualsInsideNestedLists2() {
        assertThat(myClassUnderTest.createBeanWithEqualsInsideNestedLists2()).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetNestedTypesThatOverrideEquals() {
        // Setup
        final Map<String, Map<String, FooWithEquals<BeanWithEquals>>> expectedResult = new HashMap<>();

        // Run the test
        final Map<String, Map<String, FooWithEquals<BeanWithEquals>>> result = myClassUnderTest.getNestedTypesThatOverrideEquals();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetNestedTypesThatDoNotOverrideEquals() {
        // Setup
        // Run the test
        final Map<String, Map<String, FooWithEquals<Bean>>> result = myClassUnderTest.getNestedTypesThatDoNotOverrideEquals();

        // Verify the results
    }
}
