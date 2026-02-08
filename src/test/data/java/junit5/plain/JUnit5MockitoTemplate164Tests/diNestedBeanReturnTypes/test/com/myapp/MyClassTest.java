package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
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
        bean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean));
        bean.setValue(false);
        bean.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        bean.setSubBean(subBean);
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
        beanWithEquals.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        beanWithEquals.setTheString("theString");
        beanWithEquals.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        beanWithEquals.setOtherSubBeans(Arrays.asList(otherSubBean));
        beanWithEquals.setValue(false);
        beanWithEquals.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        beanWithEquals.setSubBean(subBean);
        final Optional<List<FooWithEquals<BeanWithEquals>>> expectedResult = Optional.of(
                Arrays.asList(new FooWithEquals<>(beanWithEquals)));

        // Configure FooCreator.createBeanFooWithEquals(...).
        final BeanWithEquals beanWithEquals1 = new BeanWithEquals();
        beanWithEquals1.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        beanWithEquals1.setTheString("theString");
        beanWithEquals1.setTheInt(0);
        final OtherSubBean otherSubBean1 = new OtherSubBean();
        otherSubBean1.setOtherBeanID("otherBeanID");
        otherSubBean1.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        beanWithEquals1.setOtherSubBeans(Arrays.asList(otherSubBean1));
        beanWithEquals1.setValue(false);
        beanWithEquals1.setOtherValue(false);
        final SubBean subBean1 = new SubBean();
        subBean1.setId(0L);
        subBean1.setName("name");
        beanWithEquals1.setSubBean(subBean1);
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
        bean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean));
        bean.setValue(false);
        bean.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        bean.setSubBean(subBean);
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
        bean.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        bean.setTheString("theString");
        bean.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setOtherSubBeans(Arrays.asList(otherSubBean));
        bean.setValue(false);
        bean.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        bean.setSubBean(subBean);
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
        beanWithEquals.setFooData(new FooData("purchaseId", "licenseName", new OtherData("dataName")));
        beanWithEquals.setTheString("theString");
        beanWithEquals.setTheInt(0);
        final OtherSubBean otherSubBean = new OtherSubBean();
        otherSubBean.setOtherBeanID("otherBeanID");
        otherSubBean.setExpirationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        beanWithEquals.setOtherSubBeans(Arrays.asList(otherSubBean));
        beanWithEquals.setValue(false);
        beanWithEquals.setOtherValue(false);
        final SubBean subBean = new SubBean();
        subBean.setId(0L);
        subBean.setName("name");
        beanWithEquals.setSubBean(subBean);
        final List<List<BeanWithEquals>> expectedResult = Arrays.asList(Arrays.asList(beanWithEquals));

        // Run the test
        final List<List<BeanWithEquals>> result = myClassUnderTest.createBeanWithEqualsInsideNestedLists1();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCreateBeanWithEqualsInsideNestedLists2() {
        assertEquals(Arrays.asList(Arrays.asList(new BeanWithEquals())),
                myClassUnderTest.createBeanWithEqualsInsideNestedLists2());
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
