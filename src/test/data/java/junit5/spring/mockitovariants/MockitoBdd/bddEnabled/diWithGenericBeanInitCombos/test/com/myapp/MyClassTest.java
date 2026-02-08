package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testCreateGenericFooWithNonBeanSubtype() {
        // Setup
        // Configure Foo.createGenericFooWithNonBeanSubtype(...).
        final GenericFooWithConstructor<Foo.Bar> barGenericFooWithConstructor = new GenericFooWithConstructor<>(
                new Foo.Bar());
        given(mockFoo.createGenericFooWithNonBeanSubtype()).willReturn(barGenericFooWithConstructor);

        // Run the test
        final GenericFooWithConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithNonBeanSubtype();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithBeanSubtype() {
        // Setup
        // Configure Foo.createGenericFooWithBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithConstructor<SimpleBean> simpleBeanGenericFooWithConstructor = new GenericFooWithConstructor<>(
                simpleBean);
        given(mockFoo.createGenericFooWithBeanSubtype()).willReturn(simpleBeanGenericFooWithConstructor);

        // Run the test
        final GenericFooWithConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithBeanSubtype();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithVargsConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructor(...).
        final GenericFooWithVargsConstructor<Foo.Bar> barGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(
                new Foo.Bar());
        given(mockFoo.createGenericFooWithVargsConstructor()).willReturn(barGenericFooWithVargsConstructor);

        // Run the test
        final GenericFooWithVargsConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithVargsConstructor();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithVargsConstructorAndBean() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructorAndBean(...).
        final SimpleBean simpleBeans = new SimpleBean();
        simpleBeans.setMyId(0L);
        simpleBeans.setMyName("myName");
        final GenericFooWithVargsConstructor<SimpleBean> simpleBeanGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(
                simpleBeans);
        given(mockFoo.createGenericFooWithVargsConstructorAndBean())
                .willReturn(simpleBeanGenericFooWithVargsConstructor);

        // Run the test
        final GenericFooWithVargsConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithVargsConstructorAndBean();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithMultiArgConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithMultiArgConstructor(...).
        final GenericFooWithMultiArgConstructor<Foo.Bar> barGenericFooWithMultiArgConstructor = new GenericFooWithMultiArgConstructor<>(
                new Foo.Bar(), new Foo.Bar());
        given(mockFoo.createGenericFooWithMultiArgConstructor()).willReturn(barGenericFooWithMultiArgConstructor);

        // Run the test
        final GenericFooWithMultiArgConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithMultiArgConstructor();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithMultiArgConstructorWithBean() {
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
        given(mockFoo.createGenericFooWithMultiArgConstructorWithBean())
                .willReturn(simpleBeanGenericFooWithMultiArgConstructor);

        // Run the test
        final GenericFooWithMultiArgConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithMultiArgConstructorWithBean();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructor() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructor()).willReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithIntConstructor();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorWithBean() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructorWithBean()).willReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithIntConstructorWithBean();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorAndListOfType() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructorAndListOfType())
                .willReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<List<Foo.Bar>> result = myClassUnderTest.createGenericFooWithIntConstructorAndListOfType();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorWithListOfBean() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructorWithListOfBean())
                .willReturn(new GenericFooWithIntConstructor<>(0));

        // Run the test
        final GenericFooWithIntConstructor<List<SimpleBean>> result = myClassUnderTest.createGenericFooWithIntConstructorWithListOfBean();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructor() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructor()).willReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithDefaultConstructor();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorWithBean() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructorWithBean())
                .willReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBean();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorAndListOfType() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructorAndListOfType())
                .willReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<List<Foo.Bar>> result = myClassUnderTest.createGenericFooWithDefaultConstructorAndListOfType();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorWithBeanWithListOfBean() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructorWithBeanWithListOfBean())
                .willReturn(new GenericFooWithDefaultConstructor<>());

        // Run the test
        final GenericFooWithDefaultConstructor<List<SimpleBean>> result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBeanWithListOfBean();

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndNonBeanSubtype() {
        // Setup
        given(mockFoo.createFooWithStaticInitAndNonBeanSubtype())
                .willReturn(GenericFooWithStaticInit.of(new Foo.Bar()));

        // Run the test
        final GenericFooWithStaticInit<Foo.Bar> result = myClassUnderTest.createFooWithStaticInitAndNonBeanSubtype();

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndBeanSubtype() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithStaticInit<SimpleBean> simpleBeanGenericFooWithStaticInit = GenericFooWithStaticInit.of(
                simpleBean);
        given(mockFoo.createFooWithStaticInitAndBeanSubtype()).willReturn(simpleBeanGenericFooWithStaticInit);

        // Run the test
        final GenericFooWithStaticInit<SimpleBean> result = myClassUnderTest.createFooWithStaticInitAndBeanSubtype();

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndListOfBeanSubtype() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndListOfBeanSubtype(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithStaticInit<List<SimpleBean>> listGenericFooWithStaticInit = GenericFooWithStaticInit.of(
                Arrays.asList(simpleBean));
        given(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).willReturn(listGenericFooWithStaticInit);

        // Run the test
        final GenericFooWithStaticInit<List<SimpleBean>> result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype();

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndListOfBeanSubtype_FooReturnsNoItems() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndListOfBeanSubtype(...).
        final GenericFooWithStaticInit<List<SimpleBean>> listGenericFooWithStaticInit = GenericFooWithStaticInit.of(
                Collections.emptyList());
        given(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).willReturn(listGenericFooWithStaticInit);

        // Run the test
        final GenericFooWithStaticInit<List<SimpleBean>> result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype();

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndVargs() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargs(...).
        final GenericFooWithStaticInitVargs<Foo.Bar> barGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(
                new Foo.Bar());
        given(mockFoo.createFooWithStaticInitAndVargs()).willReturn(barGenericFooWithStaticInitVargs);

        // Run the test
        final GenericFooWithStaticInitVargs<Foo.Bar> result = myClassUnderTest.createFooWithStaticInitAndVargs();

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndVargsAndBean() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargsAndBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithStaticInitVargs<SimpleBean> simpleBeanGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(
                simpleBean);
        given(mockFoo.createFooWithStaticInitAndVargsAndBean()).willReturn(simpleBeanGenericFooWithStaticInitVargs);

        // Run the test
        final GenericFooWithStaticInitVargs<SimpleBean> result = myClassUnderTest.createFooWithStaticInitAndVargsAndBean();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithListConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructor(...).
        final GenericFooWithListConstructor<Foo.Bar> barGenericFooWithListConstructor = new GenericFooWithListConstructor<>(
                Arrays.asList(new Foo.Bar()));
        given(mockFoo.createGenericFooWithListConstructor()).willReturn(barGenericFooWithListConstructor);

        // Run the test
        final GenericFooWithListConstructor<Foo.Bar> result = myClassUnderTest.createGenericFooWithListConstructor();

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithListConstructorAndBean() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructorAndBean(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        final GenericFooWithListConstructor<SimpleBean> simpleBeanGenericFooWithListConstructor = new GenericFooWithListConstructor<>(
                Arrays.asList(simpleBean));
        given(mockFoo.createGenericFooWithListConstructorAndBean()).willReturn(simpleBeanGenericFooWithListConstructor);

        // Run the test
        final GenericFooWithListConstructor<SimpleBean> result = myClassUnderTest.createGenericFooWithListConstructorAndBean();

        // Verify the results
    }
}
