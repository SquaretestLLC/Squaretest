package com.myapp;

import com.myapp.otherbeans.*;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private FooCreator1 mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    public void testGetFoo1() {
        // Setup
        // Configure FooCreator1.getFoo1(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBean.setName("name");
        simpleBean.setDescription("description");
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final ResponseWrapper<SimpleBean> simpleBeanResponseWrapper = ResponseWrapper.success(simpleBean);
        when(mockFooCreator.getFoo1()).thenReturn(simpleBeanResponseWrapper);

        // Run the test
        final ResponseWrapper<SimpleBean> result = myClassUnderTest.getFoo1();

        // Verify the results
    }

    @Test
    public void testGetFoo1_FooCreator1ReturnsNoItem() {
        // Setup
        when(mockFooCreator.getFoo1()).thenReturn(ResponseWrapper.success());

        // Run the test
        final ResponseWrapper<SimpleBean> result = myClassUnderTest.getFoo1();

        // Verify the results
    }

    @Test
    public void testGetFoo1_FooCreator1ReturnsFailure() {
        // Setup
        // Configure FooCreator1.getFoo1(...).
        final ResponseWrapper<SimpleBean> simpleBeanResponseWrapper = ResponseWrapper.failure();
        when(mockFooCreator.getFoo1()).thenReturn(simpleBeanResponseWrapper);

        // Run the test
        final ResponseWrapper<SimpleBean> result = myClassUnderTest.getFoo1();

        // Verify the results
    }

    @Test
    public void testGetFoo2() {
        // Setup
        // Configure FooCreator1.getFoo2(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBean.setName("name");
        simpleBean.setDescription("description");
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final ResponseWrapperWithBeanAnnotations<SimpleBean> simpleBeanResponseWrapperWithBeanAnnotations = ResponseWrapperWithBeanAnnotations.success(
                simpleBean);
        when(mockFooCreator.getFoo2()).thenReturn(simpleBeanResponseWrapperWithBeanAnnotations);

        // Run the test
        final ResponseWrapperWithBeanAnnotations<SimpleBean> result = myClassUnderTest.getFoo2();

        // Verify the results
    }

    @Test
    public void testGetFoo2_FooCreator1ReturnsNoItem() {
        // Setup
        when(mockFooCreator.getFoo2()).thenReturn(ResponseWrapperWithBeanAnnotations.success());

        // Run the test
        final ResponseWrapperWithBeanAnnotations<SimpleBean> result = myClassUnderTest.getFoo2();

        // Verify the results
    }

    @Test
    public void testGetFoo2_FooCreator1ReturnsFailure() {
        // Setup
        // Configure FooCreator1.getFoo2(...).
        final ResponseWrapperWithBeanAnnotations<SimpleBean> simpleBeanResponseWrapperWithBeanAnnotations = ResponseWrapperWithBeanAnnotations.failure();
        when(mockFooCreator.getFoo2()).thenReturn(simpleBeanResponseWrapperWithBeanAnnotations);

        // Run the test
        final ResponseWrapperWithBeanAnnotations<SimpleBean> result = myClassUnderTest.getFoo2();

        // Verify the results
    }

    @Test
    public void testGetFoo3() {
        // Setup
        // Configure FooCreator1.getFoo3(...).
        final ResponseWrapperBeanAnnotationsNoStaticCreator<SimpleBean> simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator = new ResponseWrapperBeanAnnotationsNoStaticCreator<>();
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setSuccess(false);
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setCode("code");
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setErrorMessage("errorMessage");
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(0L);
        simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator.setPayload(simpleBean);
        when(mockFooCreator.getFoo3()).thenReturn(simpleBeanResponseWrapperBeanAnnotationsNoStaticCreator);

        // Run the test
        final ResponseWrapperBeanAnnotationsNoStaticCreator<SimpleBean> result = myClassUnderTest.getFoo3();

        // Verify the results
    }

    @Test
    public void testGetFoo4() {
        // Setup
        // Configure FooCreator1.getFoo4(...).
        final BeanWithBeanAnnotationName beanWithBeanAnnotationName = new BeanWithBeanAnnotationName();
        beanWithBeanAnnotationName.setId(0L);
        beanWithBeanAnnotationName.setName("name");
        beanWithBeanAnnotationName.setDescription("description");
        beanWithBeanAnnotationName.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooCreator.getFoo4()).thenReturn(beanWithBeanAnnotationName);

        // Run the test
        final BeanWithBeanAnnotationName result = myClassUnderTest.getFoo4();

        // Verify the results
    }

    @Test
    public void testGetFoo5() {
        // Setup
        // Configure FooCreator1.getFoo5(...).
        final BeanWithBeanAnnotationNameOnField beanWithBeanAnnotationNameOnField = new BeanWithBeanAnnotationNameOnField();
        beanWithBeanAnnotationNameOnField.setId(0L);
        beanWithBeanAnnotationNameOnField.setName("name");
        beanWithBeanAnnotationNameOnField.setDescription("description");
        beanWithBeanAnnotationNameOnField.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooCreator.getFoo5()).thenReturn(beanWithBeanAnnotationNameOnField);

        // Run the test
        final BeanWithBeanAnnotationNameOnField result = myClassUnderTest.getFoo5();

        // Verify the results
    }

    @Test
    public void testGetFoo6() {
        // Setup
        // Configure FooCreator1.getFoo6(...).
        final BeanWithMissingBeanAnnotation beanWithMissingBeanAnnotation = new BeanWithMissingBeanAnnotation();
        beanWithMissingBeanAnnotation.setId(0L);
        beanWithMissingBeanAnnotation.setName("name");
        beanWithMissingBeanAnnotation.setDescription("description");
        beanWithMissingBeanAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooCreator.getFoo6()).thenReturn(beanWithMissingBeanAnnotation);

        // Run the test
        final BeanWithMissingBeanAnnotation result = myClassUnderTest.getFoo6();

        // Verify the results
    }

    @Test
    public void testGetFoo7() {
        // Setup
        // Configure FooCreator1.getFoo7(...).
        final NotBean notBean = new NotBean();
        notBean.setId(0L);
        notBean.setName("name");
        notBean.setDescription("description");
        notBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooCreator.getFoo7()).thenReturn(notBean);

        // Run the test
        final NotBean result = myClassUnderTest.getFoo7();

        // Verify the results
    }

    @Test
    public void testGetFoo8() {
        // Setup
        // Configure FooCreator1.getFoo8(...).
        final BeanWithFieldNameBeanPrefixAnnotation beanWithFieldNameBeanPrefixAnnotation = new BeanWithFieldNameBeanPrefixAnnotation();
        beanWithFieldNameBeanPrefixAnnotation.setId(0L);
        beanWithFieldNameBeanPrefixAnnotation.setName("name");
        beanWithFieldNameBeanPrefixAnnotation.setDescription("description");
        beanWithFieldNameBeanPrefixAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooCreator.getFoo8()).thenReturn(beanWithFieldNameBeanPrefixAnnotation);

        // Run the test
        final BeanWithFieldNameBeanPrefixAnnotation result = myClassUnderTest.getFoo8();

        // Verify the results
    }

    @Test
    public void testGetFoo9() {
        // Setup
        // Configure FooCreator1.getFoo9(...).
        final BeanWithFieldNameBeanAnnotation beanWithFieldNameBeanAnnotation = new BeanWithFieldNameBeanAnnotation();
        beanWithFieldNameBeanAnnotation.setId(0L);
        beanWithFieldNameBeanAnnotation.setName("name");
        beanWithFieldNameBeanAnnotation.setDescription("description");
        beanWithFieldNameBeanAnnotation.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockFooCreator.getFoo9()).thenReturn(beanWithFieldNameBeanAnnotation);

        // Run the test
        final BeanWithFieldNameBeanAnnotation result = myClassUnderTest.getFoo9();

        // Verify the results
    }
}
