package com.myapp;

import com.myapp.otherbeans.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooCreator1 mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Configure FooCreator1.getFoo1(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBean.setName("name");
        simpleBean.setDescription("description");
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final ResponseWrapper<SimpleBean> simpleBeanResponseWrapper = ResponseWrapper.success(simpleBean);
        given(mockFooCreator.getFoo1()).willReturn(simpleBeanResponseWrapper);

        // Run the test
        final ResponseWrapper<SimpleBean> result = myClassUnderTest.getFoo1();

        // Verify the results
    }

    @Test
    void testGetFoo1_FooCreator1ReturnsNoItem() {
        // Setup
        given(mockFooCreator.getFoo1()).willReturn(ResponseWrapper.success());

        // Run the test
        final ResponseWrapper<SimpleBean> result = myClassUnderTest.getFoo1();

        // Verify the results
    }

    @Test
    void testGetFoo1_FooCreator1ReturnsFailure() {
        // Setup
        // Configure FooCreator1.getFoo1(...).
        final ResponseWrapper<SimpleBean> simpleBeanResponseWrapper = ResponseWrapper.failure();
        given(mockFooCreator.getFoo1()).willReturn(simpleBeanResponseWrapper);

        // Run the test
        final ResponseWrapper<SimpleBean> result = myClassUnderTest.getFoo1();

        // Verify the results
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Configure FooCreator1.getFoo2(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBean.setName("name");
        simpleBean.setDescription("description");
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final ResponseWrapperWithBeanAnnotations<SimpleBean> simpleBeanResponseWrapperWithBeanAnnotations = ResponseWrapperWithBeanAnnotations.success(
                simpleBean);
        given(mockFooCreator.getFoo2()).willReturn(simpleBeanResponseWrapperWithBeanAnnotations);

        // Run the test
        final ResponseWrapperWithBeanAnnotations<SimpleBean> result = myClassUnderTest.getFoo2();

        // Verify the results
    }

    @Test
    void testGetFoo2_FooCreator1ReturnsNoItem() {
        // Setup
        given(mockFooCreator.getFoo2()).willReturn(ResponseWrapperWithBeanAnnotations.success());

        // Run the test
        final ResponseWrapperWithBeanAnnotations<SimpleBean> result = myClassUnderTest.getFoo2();

        // Verify the results
    }

    @Test
    void testGetFoo2_FooCreator1ReturnsFailure() {
        // Setup
        // Configure FooCreator1.getFoo2(...).
        final ResponseWrapperWithBeanAnnotations<SimpleBean> simpleBeanResponseWrapperWithBeanAnnotations = ResponseWrapperWithBeanAnnotations.failure();
        given(mockFooCreator.getFoo2()).willReturn(simpleBeanResponseWrapperWithBeanAnnotations);

        // Run the test
        final ResponseWrapperWithBeanAnnotations<SimpleBean> result = myClassUnderTest.getFoo2();

        // Verify the results
    }

    @Test
    void testGetFoo3() {
        // Setup
        // Configure FooCreator1.getFoo3(...).
        final ResponseWrapperBeanAnnotationsNoStaticCreator<SimpleBean> simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator = new ResponseWrapperBeanAnnotationsNoStaticCreator<>();
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setSuccess(false);
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setCode("code");
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setErrorMessage("errorMessage");
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setPayload(simpleBean);
        given(mockFooCreator.getFoo3()).willReturn(simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator);

        // Run the test
        final ResponseWrapperBeanAnnotationsNoStaticCreator<SimpleBean> result = myClassUnderTest.getFoo3();

        // Verify the results
    }

    @Test
    void testGetFoo4() {
        // Setup
        // Configure FooCreator1.getFoo4(...).
        final BeanWithBeanAnnotationName beanWithBeanAnnotationName = new BeanWithBeanAnnotationName();
        beanWithBeanAnnotationName.setId(0L);
        beanWithBeanAnnotationName.setName("name");
        beanWithBeanAnnotationName.setDescription("description");
        beanWithBeanAnnotationName.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooCreator.getFoo4()).willReturn(beanWithBeanAnnotationName);

        // Run the test
        final BeanWithBeanAnnotationName result = myClassUnderTest.getFoo4();

        // Verify the results
    }

    @Test
    void testGetFoo5() {
        // Setup
        // Configure FooCreator1.getFoo5(...).
        final BeanWithBeanAnnotationNameOnField beanWithBeanAnnotationNameOnField = new BeanWithBeanAnnotationNameOnField();
        beanWithBeanAnnotationNameOnField.setId(0L);
        beanWithBeanAnnotationNameOnField.setName("name");
        beanWithBeanAnnotationNameOnField.setDescription("description");
        beanWithBeanAnnotationNameOnField.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooCreator.getFoo5()).willReturn(beanWithBeanAnnotationNameOnField);

        // Run the test
        final BeanWithBeanAnnotationNameOnField result = myClassUnderTest.getFoo5();

        // Verify the results
    }

    @Test
    void testGetFoo6() {
        // Setup
        // Configure FooCreator1.getFoo6(...).
        final BeanWithMissingBeanAnnotation beanWithMissingBeanAnnotation = new BeanWithMissingBeanAnnotation();
        beanWithMissingBeanAnnotation.setId(0L);
        beanWithMissingBeanAnnotation.setName("name");
        beanWithMissingBeanAnnotation.setDescription("description");
        beanWithMissingBeanAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooCreator.getFoo6()).willReturn(beanWithMissingBeanAnnotation);

        // Run the test
        final BeanWithMissingBeanAnnotation result = myClassUnderTest.getFoo6();

        // Verify the results
    }

    @Test
    void testGetFoo7() {
        // Setup
        // Configure FooCreator1.getFoo7(...).
        final NotBean notBean = new NotBean();
        notBean.setId(0L);
        notBean.setName("name");
        notBean.setDescription("description");
        notBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooCreator.getFoo7()).willReturn(notBean);

        // Run the test
        final NotBean result = myClassUnderTest.getFoo7();

        // Verify the results
    }

    @Test
    void testGetFoo8() {
        // Setup
        // Configure FooCreator1.getFoo8(...).
        final BeanWithFieldNameBeanPrefixAnnotation beanWithFieldNameBeanPrefixAnnotation = new BeanWithFieldNameBeanPrefixAnnotation();
        beanWithFieldNameBeanPrefixAnnotation.setId(0L);
        beanWithFieldNameBeanPrefixAnnotation.setName("name");
        beanWithFieldNameBeanPrefixAnnotation.setDescription("description");
        beanWithFieldNameBeanPrefixAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooCreator.getFoo8()).willReturn(beanWithFieldNameBeanPrefixAnnotation);

        // Run the test
        final BeanWithFieldNameBeanPrefixAnnotation result = myClassUnderTest.getFoo8();

        // Verify the results
    }

    @Test
    void testGetFoo9() {
        // Setup
        // Configure FooCreator1.getFoo9(...).
        final BeanWithFieldNameBeanAnnotation beanWithFieldNameBeanAnnotation = new BeanWithFieldNameBeanAnnotation();
        beanWithFieldNameBeanAnnotation.setId(0L);
        beanWithFieldNameBeanAnnotation.setName("name");
        beanWithFieldNameBeanAnnotation.setDescription("description");
        beanWithFieldNameBeanAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        given(mockFooCreator.getFoo9()).willReturn(beanWithFieldNameBeanAnnotation);

        // Run the test
        final BeanWithFieldNameBeanAnnotation result = myClassUnderTest.getFoo9();

        // Verify the results
    }
}
