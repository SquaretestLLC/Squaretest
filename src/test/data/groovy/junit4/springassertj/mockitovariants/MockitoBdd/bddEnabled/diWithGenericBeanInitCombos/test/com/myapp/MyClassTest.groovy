package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testCreateGenericFooWithNonBeanSubtype() {
        // Setup
        // Configure Foo.createGenericFooWithNonBeanSubtype(...).
        def barGenericFooWithConstructor = new GenericFooWithConstructor<>(new Foo.Bar())
        given(mockFoo.createGenericFooWithNonBeanSubtype()).willReturn(barGenericFooWithConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithNonBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithBeanSubtype() {
        // Setup
        // Configure Foo.createGenericFooWithBeanSubtype(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanGenericFooWithConstructor = new GenericFooWithConstructor<>(simpleBean)
        given(mockFoo.createGenericFooWithBeanSubtype()).willReturn(simpleBeanGenericFooWithConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithVargsConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructor(...).
        def barGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(new Foo.Bar())
        given(mockFoo.createGenericFooWithVargsConstructor()).willReturn(barGenericFooWithVargsConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithVargsConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithVargsConstructorAndBean() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructorAndBean(...).
        def simpleBeans = new SimpleBean()
        simpleBeans.setMyId(0L)
        simpleBeans.setMyName("myName")
        def simpleBeanGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(simpleBeans)
        given(mockFoo.createGenericFooWithVargsConstructorAndBean())
                .willReturn(simpleBeanGenericFooWithVargsConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithVargsConstructorAndBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithMultiArgConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithMultiArgConstructor(...).
        def barGenericFooWithMultiArgConstructor = new GenericFooWithMultiArgConstructor<>(new Foo.Bar(),
                new Foo.Bar())
        given(mockFoo.createGenericFooWithMultiArgConstructor()).willReturn(barGenericFooWithMultiArgConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithMultiArgConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithMultiArgConstructorWithBean() {
        // Setup
        // Configure Foo.createGenericFooWithMultiArgConstructorWithBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBean1 = new SimpleBean()
        simpleBean1.setMyId(0L)
        simpleBean1.setMyName("myName")
        def simpleBeanGenericFooWithMultiArgConstructor = new GenericFooWithMultiArgConstructor<>(simpleBean,
                simpleBean1)
        given(mockFoo.createGenericFooWithMultiArgConstructorWithBean())
                .willReturn(simpleBeanGenericFooWithMultiArgConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithMultiArgConstructorWithBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructor() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructor()).willReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorWithBean() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructorWithBean()).willReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructorWithBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorAndListOfType() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructorAndListOfType())
                .willReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructorAndListOfType()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorWithListOfBean() {
        // Setup
        given(mockFoo.createGenericFooWithIntConstructorWithListOfBean())
                .willReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructorWithListOfBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructor() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructor()).willReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorWithBean() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructorWithBean())
                .willReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorAndListOfType() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructorAndListOfType())
                .willReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructorAndListOfType()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorWithBeanWithListOfBean() {
        // Setup
        given(mockFoo.createGenericFooWithDefaultConstructorWithBeanWithListOfBean())
                .willReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBeanWithListOfBean()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndNonBeanSubtype() {
        // Setup
        given(mockFoo.createFooWithStaticInitAndNonBeanSubtype())
                .willReturn(GenericFooWithStaticInit.of(new Foo.Bar()))

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndNonBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndBeanSubtype() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndBeanSubtype(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanGenericFooWithStaticInit = GenericFooWithStaticInit.of(simpleBean)
        given(mockFoo.createFooWithStaticInitAndBeanSubtype()).willReturn(simpleBeanGenericFooWithStaticInit)

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndListOfBeanSubtype() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndListOfBeanSubtype(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def listGenericFooWithStaticInit = GenericFooWithStaticInit.of([simpleBean])
        given(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).willReturn(listGenericFooWithStaticInit)

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndListOfBeanSubtype_FooReturnsNoItems() {
        // Setup
        given(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).willReturn(GenericFooWithStaticInit.of([]))

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndVargs() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargs(...).
        def barGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(new Foo.Bar())
        given(mockFoo.createFooWithStaticInitAndVargs()).willReturn(barGenericFooWithStaticInitVargs)

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndVargs()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndVargsAndBean() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargsAndBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(simpleBean)
        given(mockFoo.createFooWithStaticInitAndVargsAndBean()).willReturn(simpleBeanGenericFooWithStaticInitVargs)

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndVargsAndBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithListConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructor(...).
        def barGenericFooWithListConstructor = new GenericFooWithListConstructor<>([new Foo.Bar()])
        given(mockFoo.createGenericFooWithListConstructor()).willReturn(barGenericFooWithListConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithListConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithListConstructorAndBean() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructorAndBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanGenericFooWithListConstructor = new GenericFooWithListConstructor<>([simpleBean])
        given(mockFoo.createGenericFooWithListConstructorAndBean()).willReturn(simpleBeanGenericFooWithListConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithListConstructorAndBean()

        // Verify the results
    }
}
