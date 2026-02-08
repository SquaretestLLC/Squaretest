package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFoo1() {
        // Setup
        final BeanWithAllPackageLocalMethods expectedResult = new BeanWithAllPackageLocalMethods();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo1(...).
        final BeanWithAllPackageLocalMethods beanWithAllPackageLocalMethods = new BeanWithAllPackageLocalMethods();
        beanWithAllPackageLocalMethods.setId("id");
        beanWithAllPackageLocalMethods.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(beanWithAllPackageLocalMethods);

        // Run the test
        final BeanWithAllPackageLocalMethods result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testPutFoo1() {
        // Setup
        // Configure FooService.putFoo1(...).
        final BeanWithAllPackageLocalMethodsAndWithMethods bean = new BeanWithAllPackageLocalMethodsAndWithMethods();
        bean.setId("id");
        bean.setName("name");
        bean.setProp1("prop1");
        bean.setProp2("prop2");
        bean.setProp3("prop3");
        bean.setProp4("prop4");
        bean.setProp5("prop5");
        when(mockFooService.putFoo1(bean)).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.putFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Configure FooService.getFoo2(...).
        final DataWithPackageLocalFactoryMethod dataWithPackageLocalFactoryMethod = DataWithPackageLocalFactoryMethod.parse(
                "dataStr");
        when(mockFooService.getFoo2("id")).thenReturn(dataWithPackageLocalFactoryMethod);

        // Run the test
        final DataWithPackageLocalFactoryMethod result = myClassUnderTest.getFoo2("id");

        // Verify the results
    }

    @Test
    void testGetFoo3() {
        // Setup
        // Configure FooService.getFoo3(...).
        final DataWithPackageLocalGenericFactoryMethod<String> stringDataWithPackageLocalGenericFactoryMethod = DataWithPackageLocalGenericFactoryMethod.create(
                "value");
        when(mockFooService.getFoo3("id")).thenReturn(stringDataWithPackageLocalGenericFactoryMethod);

        // Run the test
        final DataWithPackageLocalGenericFactoryMethod<String> result = myClassUnderTest.getFoo3("id");

        // Verify the results
    }

    @Test
    void testGetFoo4() {
        // Setup
        // Configure FooService.getFoo4(...).
        final DataWithPackageLocalConstructor dataWithPackageLocalConstructor = new DataWithPackageLocalConstructor(
                "id", "name");
        when(mockFooService.getFoo4("id")).thenReturn(dataWithPackageLocalConstructor);

        // Run the test
        final DataWithPackageLocalConstructor result = myClassUnderTest.getFoo4("id");

        // Verify the results
    }

    @Test
    void testGetFoo5() {
        // Setup
        // Configure FooService.getFoo5(...).
        final DataWithPackageLocalGenericConstructor<String> stringDataWithPackageLocalGenericConstructor = new DataWithPackageLocalGenericConstructor<>(
                "payload");
        when(mockFooService.getFoo5("id")).thenReturn(stringDataWithPackageLocalGenericConstructor);

        // Run the test
        final DataWithPackageLocalGenericConstructor<String> result = myClassUnderTest.getFoo5("id");

        // Verify the results
    }

    @Test
    void testGetFoo6() {
        // Setup
        // Configure FooService.getFoo6(...).
        final DataWithGenericAndBothConstructors<String> stringDataWithGenericAndBothConstructors = new DataWithGenericAndBothConstructors<>(
                "payload");
        when(mockFooService.getFoo6("id")).thenReturn(stringDataWithGenericAndBothConstructors);

        // Run the test
        final DataWithGenericAndBothConstructors<String> result = myClassUnderTest.getFoo6("id");

        // Verify the results
    }
}
