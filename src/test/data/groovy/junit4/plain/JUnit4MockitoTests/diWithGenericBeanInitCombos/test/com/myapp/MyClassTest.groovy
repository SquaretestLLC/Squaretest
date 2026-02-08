package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
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
        when(mockFoo.createGenericFooWithNonBeanSubtype()).thenReturn(barGenericFooWithConstructor)

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
        when(mockFoo.createGenericFooWithBeanSubtype()).thenReturn(simpleBeanGenericFooWithConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithVargsConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithVargsConstructor(...).
        def barGenericFooWithVargsConstructor = new GenericFooWithVargsConstructor<>(new Foo.Bar())
        when(mockFoo.createGenericFooWithVargsConstructor()).thenReturn(barGenericFooWithVargsConstructor)

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
        when(mockFoo.createGenericFooWithVargsConstructorAndBean())
                .thenReturn(simpleBeanGenericFooWithVargsConstructor)

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
        when(mockFoo.createGenericFooWithMultiArgConstructor()).thenReturn(barGenericFooWithMultiArgConstructor)

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
        when(mockFoo.createGenericFooWithMultiArgConstructorWithBean())
                .thenReturn(simpleBeanGenericFooWithMultiArgConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithMultiArgConstructorWithBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructor() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructor()).thenReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorWithBean() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructorWithBean()).thenReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructorWithBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorAndListOfType() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructorAndListOfType())
                .thenReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructorAndListOfType()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithIntConstructorWithListOfBean() {
        // Setup
        when(mockFoo.createGenericFooWithIntConstructorWithListOfBean())
                .thenReturn(new GenericFooWithIntConstructor<>(0))

        // Run the test
        def result = myClassUnderTest.createGenericFooWithIntConstructorWithListOfBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructor() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructor()).thenReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructor()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorWithBean() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructorWithBean())
                .thenReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorAndListOfType() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructorAndListOfType())
                .thenReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructorAndListOfType()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithDefaultConstructorWithBeanWithListOfBean() {
        // Setup
        when(mockFoo.createGenericFooWithDefaultConstructorWithBeanWithListOfBean())
                .thenReturn(new GenericFooWithDefaultConstructor<>())

        // Run the test
        def result = myClassUnderTest.createGenericFooWithDefaultConstructorWithBeanWithListOfBean()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndNonBeanSubtype() {
        // Setup
        when(mockFoo.createFooWithStaticInitAndNonBeanSubtype()).thenReturn(GenericFooWithStaticInit.of(new Foo.Bar()))

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
        when(mockFoo.createFooWithStaticInitAndBeanSubtype()).thenReturn(simpleBeanGenericFooWithStaticInit)

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
        when(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).thenReturn(listGenericFooWithStaticInit)

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndListOfBeanSubtype_FooReturnsNoItems() {
        // Setup
        when(mockFoo.createFooWithStaticInitAndListOfBeanSubtype()).thenReturn(GenericFooWithStaticInit.of([]))

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndListOfBeanSubtype()

        // Verify the results
    }

    @Test
    void testCreateFooWithStaticInitAndVargs() {
        // Setup
        // Configure Foo.createFooWithStaticInitAndVargs(...).
        def barGenericFooWithStaticInitVargs = GenericFooWithStaticInitVargs.of(new Foo.Bar())
        when(mockFoo.createFooWithStaticInitAndVargs()).thenReturn(barGenericFooWithStaticInitVargs)

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
        when(mockFoo.createFooWithStaticInitAndVargsAndBean()).thenReturn(simpleBeanGenericFooWithStaticInitVargs)

        // Run the test
        def result = myClassUnderTest.createFooWithStaticInitAndVargsAndBean()

        // Verify the results
    }

    @Test
    void testCreateGenericFooWithListConstructor() {
        // Setup
        // Configure Foo.createGenericFooWithListConstructor(...).
        def barGenericFooWithListConstructor = new GenericFooWithListConstructor<>([new Foo.Bar()])
        when(mockFoo.createGenericFooWithListConstructor()).thenReturn(barGenericFooWithListConstructor)

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
        when(mockFoo.createGenericFooWithListConstructorAndBean()).thenReturn(simpleBeanGenericFooWithListConstructor)

        // Run the test
        def result = myClassUnderTest.createGenericFooWithListConstructorAndBean()

        // Verify the results
    }
}
