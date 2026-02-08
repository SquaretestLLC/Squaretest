package com.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateGenericFooWithNonBeanSubtype() {
        // Setup
        // Configure Foo.createGenericFooWithNonBeanSubtype(...).
        final GenericFooWithConstructor<Foo.Bar> barGenericFooWithConstructor = new GenericFooWithConstructor<>(
                new Foo.Bar());
        when(mockFoo.createGenericFooWithNonBeanSubtype()).thenReturn(barGenericFooWithConstructor);

        // Run the test
        final GenericFooWithConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithNonBeanSubtype();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithBeanSubtype() {
        // Setup
        // Configure Foo.createGenericFooWithBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithConstructor<SimpleBean> simpleBeanGenericFooWithConstructor = new GenericFooWithConstructor<>(
                simpleBean);
        when(mockFoo.createGenericFooWithBeanSubtype()).thenReturn(simpleBeanGenericFooWithConstructor);

        // Run the test
        final GenericFooWithConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithBeanSubtype();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithVargsConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructor(...).
        final GenericFooWithVargsConstructor<Foo.Bar> barGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(
                new Foo.Bar());
        when(mockFoo.createGenericFooWithVargsConstructor()).thenReturn(barGenericFooWithVargsConstructor);

        // Run the test
        final GenericFooWithVargsConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithVargsConstructor();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithVargsConstructorAndBean() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructorAndBean(...).
        final SimpleBean simpleBeans = new SimpleBean();
        simpleBeans.setMyId(0L);
        simpleBeans.setMyName("myName");
        final GenericFooWithVargsConstructor<SimpleBean> simpleBeanGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(
                simpleBeans);
        when(mockFoo.createGenericFooWithVargsConstructorAndBean())
                .thenReturn(simpleBeanGenericFooWithVargsConstructor);

        // Run the test
        final GenericFooWithVargsConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithVargsConstructorAndBean();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithMultiArgConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithMultiArgConstructor(...).
        final GenericFooWithMultiArgConstructor<Foo.Bar> barGenericFooWithMultiArgConstructor = new GenericFooWithMultiArgConstructor<>(
                new Foo.Bar(), new Foo.Bar());
        when(mockFoo.createGenericFooWithMultiArgConstructor()).thenReturn(barGenericFooWithMultiArgConstructor);

        // Run the test
        final GenericFooWithMultiArgConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithMultiArgConstructor();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithMultiArgConstructorWithBean() {
        // Setup
        // Configure Foo.createGenericFooWithMultiArgConstructorWithBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        final GenericFooWithMultiArgConstructor<SimpleBean> simpleBeanGenericFooWithMultiArgConstructor = new GenericFooWithMultiArgConstructor<>(
                simpleBean, simpleBean1);
        when(mockFoo.createGenericFooWithMultiArgConstructorWithBean())
                .thenReturn(simpleBeanGenericFooWithMultiArgConstructor);

        // Run the test
        final GenericFooWithMultiArgConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithMultiArgConstructorWithBean();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithIntConstructor() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructor()).thenReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithIntConstructor();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithIntConstructorWithBean() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructorWithBean()).thenReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithIntConstructorWithBean();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithIntConstructorAndListOfType() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructorAndListOfType())
                .thenReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<List<Foo.Bar>> result = myClassUnderTest.createGenericFooWithIntConstructorAndListOfType();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithIntConstructorWithListOfBean() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructorWithListOfBean())
                .thenReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<List<SimpleBean>> result = myClassUnderTest.createGenericFooWithIntConstructorWithListOfBean();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithDefaultConstructor() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructor()).thenReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithDefaultConstructor();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithDefaultConstructorWithBean() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructorWithBean())
                .thenReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBean();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithDefaultConstructorAndListOfType() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructorAndListOfType())
                .thenReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<List<Foo.Bar>> result = myClassUnderTest.createGenericFooWithDefaultConstructorAndListOfType();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithDefaultConstructorWithBeanWithListOfBean() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructorWithBeanWithListOfBean())
                .thenReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<List<SimpleBean>> result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBeanWithListOfBean();

        // Verify the results
    }

    @Test
    public void testCreateFooWithStaticInitAndNonBeanSubtype() {
        // Setup
        when(mockFoo.createFooWithStaticInitAndNonBeanSubtype()).thenReturn(GenericFooWithStaticInit.of(new Foo.Bar()));

        // Run the test
        final GenericFooWithStaticInit<Foo.Bar> result = myClassUnderTest.createFooWithStaticInitAndNonBeanSubtype();

        // Verify the results
    }

    @Test
    public void testCreateFooWithStaticInitAndBeanSubtype() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithStaticInit<SimpleBean> simpleBeanGenericFooWithStaticInit = GenericFooWithStaticInit.of(
                simpleBean);
        when(mockFoo.createFooWithStaticInitAndBeanSubtype()).thenReturn(simpleBeanGenericFooWithStaticInit);

        // Run the test
        final GenericFooWithStaticInit<SimpleBean> result = myClassUnderTest.createFooWithStaticInitAndBeanSubtype();

        // Verify the results
    }

    @Test
    public void testCreateFooWithStaticInitAndListOfBeanSubtype() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndListOfBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithStaticInit<List<SimpleBean>> listGenericFooWithStaticInit = GenericFooWithStaticInit.of(
                Arrays.asList(simpleBean));
        when(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).thenReturn(listGenericFooWithStaticInit);

        // Run the test
        final GenericFooWithStaticInit<List<SimpleBean>> result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype();

        // Verify the results
    }

    @Test
    public void testCreateFooWithStaticInitAndListOfBeanSubtype_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndListOfBeanSubtype(...).
        final GenericFooWithStaticInit<List<SimpleBean>> listGenericFooWithStaticInit = GenericFooWithStaticInit.of(
                Collections.emptyList());
        when(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).thenReturn(listGenericFooWithStaticInit);

        // Run the test
        final GenericFooWithStaticInit<List<SimpleBean>> result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype();

        // Verify the results
    }

    @Test
    public void testCreateFooWithStaticInitAndVargs() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargs(...).
        final GenericFooWithStaticInitVargs<Foo.Bar> barGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(
                new Foo.Bar());
        when(mockFoo.createFooWithStaticInitAndVargs()).thenReturn(barGenericFooWithStaticInitVargs);

        // Run the test
        final GenericFooWithStaticInitVargs<Foo.Bar> result = myClassUnderTest.createFooWithStaticInitAndVargs();

        // Verify the results
    }

    @Test
    public void testCreateFooWithStaticInitAndVargsAndBean() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargsAndBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithStaticInitVargs<SimpleBean> simpleBeanGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(
                simpleBean);
        when(mockFoo.createFooWithStaticInitAndVargsAndBean()).thenReturn(simpleBeanGenericFooWithStaticInitVargs);

        // Run the test
        final GenericFooWithStaticInitVargs<SimpleBean> result = myClassUnderTest.createFooWithStaticInitAndVargsAndBean();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithListConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructor(...).
        final GenericFooWithListConstructor<Foo.Bar> barGenericFooWithListConstructor = new GenericFooWithListConstructor<>(
                Arrays.asList(new Foo.Bar()));
        when(mockFoo.createGenericFooWithListConstructor()).thenReturn(barGenericFooWithListConstructor);

        // Run the test
        final GenericFooWithListConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithListConstructor();

        // Verify the results
    }

    @Test
    public void testCreateGenericFooWithListConstructorAndBean() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructorAndBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithListConstructor<SimpleBean> simpleBeanGenericFooWithListConstructor = new GenericFooWithListConstructor<>(
                Arrays.asList(simpleBean));
        when(mockFoo.createGenericFooWithListConstructorAndBean()).thenReturn(simpleBeanGenericFooWithListConstructor);

        // Run the test
        final GenericFooWithListConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithListConstructorAndBean();

        // Verify the results
    }
}
