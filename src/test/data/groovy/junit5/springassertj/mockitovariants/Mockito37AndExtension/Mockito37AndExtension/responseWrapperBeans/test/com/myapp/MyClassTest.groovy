package com.myapp

import com.myapp.otherbeans.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import java.time.LocalDateTime

import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator1 mockFooCreator

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Configure FooCreator1.getFoo1(...).
        def simpleBean = new SimpleBean()
        simpleBean.setId(0L)
        simpleBean.setName("name")
        simpleBean.setDescription("description")
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def simpleBeanResponseWrapper = ResponseWrapper.success(simpleBean)
        when(mockFooCreator.getFoo1()).thenReturn(simpleBeanResponseWrapper)

        // Run the test
        def result = myClassUnderTest.getFoo1()

        // Verify the results
    }

    @Test
    void testGetFoo1_FooCreator1ReturnsNoItem() {
        // Setup
        when(mockFooCreator.getFoo1()).thenReturn(ResponseWrapper.success())

        // Run the test
        def result = myClassUnderTest.getFoo1()

        // Verify the results
    }

    @Test
    void testGetFoo1_FooCreator1ReturnsFailure() {
        // Setup
        // Configure FooCreator1.getFoo1(...).
        def simpleBeanResponseWrapper = ResponseWrapper.failure()
        when(mockFooCreator.getFoo1()).thenReturn(simpleBeanResponseWrapper)

        // Run the test
        def result = myClassUnderTest.getFoo1()

        // Verify the results
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Configure FooCreator1.getFoo2(...).
        def simpleBean = new SimpleBean()
        simpleBean.setId(0L)
        simpleBean.setName("name")
        simpleBean.setDescription("description")
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def simpleBeanResponseWrapperWithBeanAnnotations = ResponseWrapperWithBeanAnnotations.success(simpleBean)
        when(mockFooCreator.getFoo2()).thenReturn(simpleBeanResponseWrapperWithBeanAnnotations)

        // Run the test
        def result = myClassUnderTest.getFoo2()

        // Verify the results
    }

    @Test
    void testGetFoo2_FooCreator1ReturnsNoItem() {
        // Setup
        when(mockFooCreator.getFoo2()).thenReturn(ResponseWrapperWithBeanAnnotations.success())

        // Run the test
        def result = myClassUnderTest.getFoo2()

        // Verify the results
    }

    @Test
    void testGetFoo2_FooCreator1ReturnsFailure() {
        // Setup
        // Configure FooCreator1.getFoo2(...).
        def simpleBeanResponseWrapperWithBeanAnnotations = ResponseWrapperWithBeanAnnotations.failure()
        when(mockFooCreator.getFoo2()).thenReturn(simpleBeanResponseWrapperWithBeanAnnotations)

        // Run the test
        def result = myClassUnderTest.getFoo2()

        // Verify the results
    }

    @Test
    void testGetFoo3() {
        // Setup
        // Configure FooCreator1.getFoo3(...).
        def simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator = new ResponseWrapperBeanAnnotationsNoStaticCreator<>()
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setSuccess(false)
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setCode("code")
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setErrorMessage("errorMessage")
        def simpleBean = new SimpleBean()
        simpleBean.setId(0L)
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setPayload(simpleBean)
        when(mockFooCreator.getFoo3()).thenReturn(simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator)

        // Run the test
        def result = myClassUnderTest.getFoo3()

        // Verify the results
    }

    @Test
    void testGetFoo4() {
        // Setup
        // Configure FooCreator1.getFoo4(...).
        def beanWithBeanAnnotationName = new BeanWithBeanAnnotationName()
        beanWithBeanAnnotationName.setId(0L)
        beanWithBeanAnnotationName.setName("name")
        beanWithBeanAnnotationName.setDescription("description")
        beanWithBeanAnnotationName.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooCreator.getFoo4()).thenReturn(beanWithBeanAnnotationName)

        // Run the test
        def result = myClassUnderTest.getFoo4()

        // Verify the results
    }

    @Test
    void testGetFoo5() {
        // Setup
        // Configure FooCreator1.getFoo5(...).
        def beanWithBeanAnnotationNameOnField = new BeanWithBeanAnnotationNameOnField()
        beanWithBeanAnnotationNameOnField.setId(0L)
        beanWithBeanAnnotationNameOnField.setName("name")
        beanWithBeanAnnotationNameOnField.setDescription("description")
        beanWithBeanAnnotationNameOnField.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooCreator.getFoo5()).thenReturn(beanWithBeanAnnotationNameOnField)

        // Run the test
        def result = myClassUnderTest.getFoo5()

        // Verify the results
    }

    @Test
    void testGetFoo6() {
        // Setup
        // Configure FooCreator1.getFoo6(...).
        def beanWithMissingBeanAnnotation = new BeanWithMissingBeanAnnotation()
        beanWithMissingBeanAnnotation.setId(0L)
        beanWithMissingBeanAnnotation.setName("name")
        beanWithMissingBeanAnnotation.setDescription("description")
        beanWithMissingBeanAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooCreator.getFoo6()).thenReturn(beanWithMissingBeanAnnotation)

        // Run the test
        def result = myClassUnderTest.getFoo6()

        // Verify the results
    }

    @Test
    void testGetFoo7() {
        // Setup
        // Configure FooCreator1.getFoo7(...).
        def notBean = new NotBean()
        notBean.setId(0L)
        notBean.setName("name")
        notBean.setDescription("description")
        notBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooCreator.getFoo7()).thenReturn(notBean)

        // Run the test
        def result = myClassUnderTest.getFoo7()

        // Verify the results
    }

    @Test
    void testGetFoo8() {
        // Setup
        // Configure FooCreator1.getFoo8(...).
        def beanWithFieldNameBeanPrefixAnnotation = new BeanWithFieldNameBeanPrefixAnnotation()
        beanWithFieldNameBeanPrefixAnnotation.setId(0L)
        beanWithFieldNameBeanPrefixAnnotation.setName("name")
        beanWithFieldNameBeanPrefixAnnotation.setDescription("description")
        beanWithFieldNameBeanPrefixAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooCreator.getFoo8()).thenReturn(beanWithFieldNameBeanPrefixAnnotation)

        // Run the test
        def result = myClassUnderTest.getFoo8()

        // Verify the results
    }

    @Test
    void testGetFoo9() {
        // Setup
        // Configure FooCreator1.getFoo9(...).
        def beanWithFieldNameBeanAnnotation = new BeanWithFieldNameBeanAnnotation()
        beanWithFieldNameBeanAnnotation.setId(0L)
        beanWithFieldNameBeanAnnotation.setName("name")
        beanWithFieldNameBeanAnnotation.setDescription("description")
        beanWithFieldNameBeanAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        when(mockFooCreator.getFoo9()).thenReturn(beanWithFieldNameBeanAnnotation)

        // Run the test
        def result = myClassUnderTest.getFoo9()

        // Verify the results
    }
}
