package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private BeanFixer mockBeanConverter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockBeanConverter);
    }

    @Test
    void testConvertBean() {
        // Setup
        final Bean theBean = new Bean();
        theBean.setId(0);
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        theBean.setFooData(fooData);

        // Configure BeanFixer.convertBean(...).
        final OtherBean otherBean = new OtherBean();
        final FooData data = new FooData();
        data.setPurchaseId("purchaseId");
        data.setNameOnTheLicense("nameOnTheLicense");
        data.setOtherData(new OtherData("dataName"));
        otherBean.setOurData(data);
        otherBean.setOurStr("ourStr");
        when(mockBeanConverter.convertBean(any(Bean.class))).thenReturn(otherBean);

        // Run the test
        final OtherBean result = myClassUnderTest.convertBean(theBean);

        // Verify the results
    }

    @Test
    void testConvertDefaultBeanAnonInnerClass() {
        // Setup
        // Configure BeanFixer.convertBean(...).
        final OtherBean otherBean = new OtherBean();
        final FooData data = new FooData();
        data.setPurchaseId("purchaseId");
        data.setNameOnTheLicense("nameOnTheLicense");
        data.setOtherData(new OtherData("dataName"));
        otherBean.setOurData(data);
        otherBean.setOurStr("ourStr");
        when(mockBeanConverter.convertBean(any(Bean.class))).thenReturn(otherBean);

        // Run the test
        final OtherBean result = myClassUnderTest.convertDefaultBeanAnonInnerClass();

        // Verify the results
    }

    @Test
    void testConvertDefaultObjectAnonInnerClass() {
        // Setup
        // Configure BeanFixer.convertObject(...).
        final OtherBean otherBean = new OtherBean();
        final FooData data = new FooData();
        data.setPurchaseId("purchaseId");
        data.setNameOnTheLicense("nameOnTheLicense");
        data.setOtherData(new OtherData("dataName"));
        otherBean.setOurData(data);
        otherBean.setOurStr("ourStr");
        when(mockBeanConverter.convertObject(any(Object.class))).thenReturn(otherBean);

        // Run the test
        final OtherBean result = myClassUnderTest.convertDefaultObjectAnonInnerClass();

        // Verify the results
    }

    @Test
    void testCreateGenericBean() {
        // Setup
        // Configure BeanFixer.createGenericBean(...).
        final Bean bean = new Bean();
        bean.setId(0);
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        when(mockBeanConverter.createGenericBean(any(Bean.class))).thenReturn(bean);

        // Run the test
        final Bean result = myClassUnderTest.createGenericBean();

        // Verify the results
    }

    @Test
    void testCreateGenericBeanWithAnonInnerClass() {
        // Setup
        when(mockBeanConverter.createGenericBean(any(Bean.class))).thenReturn(null);

        // Run the test
        final Bean result = myClassUnderTest.createGenericBeanWithAnonInnerClass();

        // Verify the results
    }

    @Test
    void testCreateGenericBeanWithLocalClass() {
        // Setup
        // Run the test
        myClassUnderTest.createGenericBeanWithLocalClass();

        // Verify the results
        verify(mockBeanConverter).createGenericBean(any(PhoneNumber.class));
    }

    @Test
    void testCreateGenericBeanWithLocalClass1() {
        // Setup
        when(mockBeanConverter.createGenericBean(any(PhoneNumber.class))).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.createGenericBeanWithLocalClass1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateGenericBeanWithAnonLocalClass() {
        // Setup
        // Run the test
        myClassUnderTest.createGenericBeanWithAnonLocalClass();

        // Verify the results
        verify(mockBeanConverter).createGenericBean(any(PhoneNumber.class));
    }

    @Test
    void testCreateGenericBeanWithAnonLocalClass1() {
        // Setup
        when(mockBeanConverter.createGenericBean(any(PhoneNumber.class))).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.createGenericBeanWithAnonLocalClass1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testMakeBeanWithClass_LocalClass() {
        // Setup
        when(mockBeanConverter.createGenericBeanWithClass(PhoneNumber.class)).thenReturn(null);

        // Run the test
        myClassUnderTest.makeBeanWithClass_LocalClass("beanId");

        // Verify the results
    }

    @Test
    void testMakeBeanWithClass_InnerInstanceClass() {
        // Setup
        // Configure BeanFixer.createGenericBeanWithClass(...).
        final MyClass.PhoneNumberInstance mockPhoneNumberInstance = mock(MyClass.PhoneNumberInstance.class);
        when(mockBeanConverter.createGenericBeanWithClass(MyClass.PhoneNumberInstance.class))
                .thenReturn(mockPhoneNumberInstance);

        // Run the test
        myClassUnderTest.makeBeanWithClass_InnerInstanceClass(null);

        // Verify the results
    }

    @Test
    void testMakeBeanWithClass_InnerStaticClass() {
        // Setup
        final MyClass.PhoneNumberStatic phoneNumberStatic = new MyClass.PhoneNumberStatic();
        phoneNumberStatic.setNum("num");

        // Configure BeanFixer.createGenericBeanWithClass(...).
        final MyClass.PhoneNumberStatic phoneNumberStatic1 = new MyClass.PhoneNumberStatic();
        phoneNumberStatic1.setNum("num");
        when(mockBeanConverter.createGenericBeanWithClass(MyClass.PhoneNumberStatic.class))
                .thenReturn(phoneNumberStatic1);

        // Run the test
        myClassUnderTest.makeBeanWithClass_InnerStaticClass(phoneNumberStatic);

        // Verify the results
    }

    @Test
    void testMakeBeanWithClass_AnonClass() {
        // Setup
        // Run the test
        myClassUnderTest.makeBeanWithClass_AnonClass("beanId");

        // Verify the results
        verify(mockBeanConverter).createGenericBeanWithClass(Bean.class);
    }

    @Test
    void testMakeBeanWithClass_AnonClass1() {
        // Setup
        when(mockBeanConverter.createGenericBeanWithClass(Bean.class)).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.makeBeanWithClass_AnonClass1("beanId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testMakeBeanWithClass_LambdaClass() {
        // Setup
        // Run the test
        myClassUnderTest.makeBeanWithClass_LambdaClass("beanId");

        // Verify the results
        verify(mockBeanConverter).createGenericBeanWithClass(Supplier.class);
    }

    @Test
    void testMakeBeanWithClass_LambdaClass1() {
        // Setup
        when(mockBeanConverter.createGenericBeanWithClass(Supplier.class)).thenReturn(() -> null);

        // Run the test
        final String result = myClassUnderTest.makeBeanWithClass_LambdaClass1("beanId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testStoreHungarianNotationBean() {
        // Setup
        final HungarianBean hungarianBean = new HungarianBean();
        hungarianBean.setWords("words");
        hungarianBean.setId(0);

        // Run the test
        myClassUnderTest.storeHungarianNotationBean(hungarianBean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithSameFieldName() {
        // Setup
        final BeanWithSameMethodFieldNames beanWithSameMethodFieldNames = new BeanWithSameMethodFieldNames();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        beanWithSameMethodFieldNames.fooData(fooData);
        beanWithSameMethodFieldNames.theString("theString");

        // Run the test
        myClassUnderTest.storeBeanWithSameFieldName(beanWithSameMethodFieldNames);

        // Verify the results
    }

    @Test
    void testStoreBeanWithObjectMethods() {
        // Setup
        final BeanWithObjectMethods beanWithObjectMethods = new BeanWithObjectMethods();
        final FooData data = new FooData();
        data.setPurchaseId("purchaseId");
        data.setNameOnTheLicense("nameOnTheLicense");
        data.setOtherData(new OtherData("dataName"));
        beanWithObjectMethods.setOurData(data);
        beanWithObjectMethods.setOurStr("ourStr");

        // Run the test
        myClassUnderTest.storeBeanWithObjectMethods(beanWithObjectMethods);

        // Verify the results
    }

    @Test
    void testStoreBeanWithAltBooleans() {
        // Setup
        final BeanWithAltBooleans beanWithAltBooleans = new BeanWithAltBooleans();
        beanWithAltBooleans.setAlpha(false);
        beanWithAltBooleans.setBravo(false);
        beanWithAltBooleans.charlie(false);
        beanWithAltBooleans.delta(false);
        beanWithAltBooleans.setEcho(false);

        // Run the test
        myClassUnderTest.storeBeanWithAltBooleans(beanWithAltBooleans);

        // Verify the results
    }

    @Test
    void testStoreBeanWithExtraMethodThatEndsInDto() {
        // Setup
        final BeanWithExtraMethodButEndsInDto bean = new BeanWithExtraMethodButEndsInDto();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");

        // Run the test
        myClassUnderTest.storeBeanWithExtraMethodThatEndsInDto(bean);

        // Verify the results
    }

    @Test
    void testStoreBean3() {
        // Setup
        final BeanWithDefaultConstructorInCode bean = new BeanWithDefaultConstructorInCode();
        bean.setTheStr("theStr");

        // Run the test
        myClassUnderTest.storeBean3(bean);

        // Verify the results
    }

    @Test
    void testStoreBean4() {
        // Setup
        final BeanWithOneField bean = new BeanWithOneField();
        bean.setTheStr("theStr");

        // Run the test
        myClassUnderTest.storeBean4(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanJAXBBeanWithAcronym() {
        // Setup
        final GetAllCountriesMapRequest bean = new GetAllCountriesMapRequest();
        bean.setName("name");
        bean.setSAMIdentifier("samIdentifier");

        // Run the test
        myClassUnderTest.storeBeanJAXBBeanWithAcronym(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithFieldsWithSameLowercaseNames() {
        // Setup
        final BeanWithFieldsWithSameLowercaseNames bean = new BeanWithFieldsWithSameLowercaseNames();
        bean.setWebOne("webOne");
        bean.setWeBone("weBone");
        bean.setWhoRepresents("whoRepresents");
        bean.setWhorePresents("whorePresents");

        // Run the test
        myClassUnderTest.storeBeanWithFieldsWithSameLowercaseNames(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithIdsWithPrefixes1() {
        // Setup
        final BeanWithIdsWithPrefixes1 bean = new BeanWithIdsWithPrefixes1();
        bean.setId(0L);
        bean.setmId(0L);

        // Run the test
        myClassUnderTest.storeBeanWithIdsWithPrefixes1(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithIdsWithPrefixes2() {
        // Setup
        final BeanWithIdsWithPrefixes2 bean = new BeanWithIdsWithPrefixes2();
        bean.setId(0L);
        bean.setMId(0L);

        // Run the test
        myClassUnderTest.storeBeanWithIdsWithPrefixes2(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithFieldPrefixInFieldAndGetter() {
        // Setup
        final BeanWithFieldPrefixInFieldAndGetter bean = new BeanWithFieldPrefixInFieldAndGetter();
        bean.setmId(0L);
        bean.setmCreationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setmName("mName");
        bean.setmAddress("mAddress");

        // Run the test
        myClassUnderTest.storeBeanWithFieldPrefixInFieldAndGetter(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithFieldPrefixInFieldAndGetter1() {
        // Setup
        final BeanWithFieldPrefixInFieldAndGetter1 bean = new BeanWithFieldPrefixInFieldAndGetter1();
        bean.setMId(0L);
        bean.setMCreationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setMName("mName");
        bean.setMAddress("mAddress");

        // Run the test
        myClassUnderTest.storeBeanWithFieldPrefixInFieldAndGetter1(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithFieldPrefixInFieldAndGetter2() {
        // Setup
        final BeanWithFieldPrefixInFieldAndGetter2 bean = new BeanWithFieldPrefixInFieldAndGetter2();
        bean.set_Id(0L);
        bean.set_CreationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.set_Name("_Name");
        bean.set_Address("_Address");

        // Run the test
        myClassUnderTest.storeBeanWithFieldPrefixInFieldAndGetter2(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithFieldPrefixInFieldButNotGetter() {
        // Setup
        final BeanWithFieldPrefixInFieldButNotGetter bean = new BeanWithFieldPrefixInFieldButNotGetter();
        bean.setId(0L);
        bean.setCreationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.setName("mName");
        bean.setAddress("mAddress");

        // Run the test
        myClassUnderTest.storeBeanWithFieldPrefixInFieldButNotGetter(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithPrefixAndFieldNameSetters() {
        // Setup
        final BeanWithPrefixAndFieldNameSetters bean = new BeanWithPrefixAndFieldNameSetters();
        bean.mId(0L);
        bean.mCreationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.mName("mName");
        bean.mAddress("mAddress");

        // Run the test
        myClassUnderTest.storeBeanWithPrefixAndFieldNameSetters(bean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithPrefixAndFieldNameSetters1() {
        // Setup
        final BeanWithPrefixAndFieldNameSetters1 bean = new BeanWithPrefixAndFieldNameSetters1();
        bean.id(0L);
        bean.creationDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        bean.name("mName");
        bean.address("mAddress");

        // Run the test
        myClassUnderTest.storeBeanWithPrefixAndFieldNameSetters1(bean);

        // Verify the results
    }

    @Test
    void testStoreBean5() {
        // Setup
        final OuterBean.InnerBean innerBean = new OuterBean.InnerBean();
        innerBean.setTheString("theString");
        final OuterBean.OtherInnerBean bean = new OuterBean.OtherInnerBean();
        bean.setName("name");
        innerBean.setBean(bean);

        // Run the test
        myClassUnderTest.storeBean5(innerBean);

        // Verify the results
    }

    @Test
    void testStoreNotBean1() {
        // Setup
        final NotBeanExtraConstructor bean = new NotBeanExtraConstructor("theStr");

        // Run the test
        myClassUnderTest.storeNotBean1(bean);

        // Verify the results
    }

    @Test
    void testStoreNotBean2() {
        // Setup
        final NotBeanGetterOnly bean = new NotBeanGetterOnly();

        // Run the test
        myClassUnderTest.storeNotBean2(bean);

        // Verify the results
    }

    @Test
    void testStoreNotBean3() {
        // Setup
        final NotBeanWithExtraMethod bean = new NotBeanWithExtraMethod();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");

        // Run the test
        myClassUnderTest.storeNotBean3(bean);

        // Verify the results
    }

    @Test
    void testStoreNotBean4() {
        // Setup
        final NotBeanWithNoFields bean = new NotBeanWithNoFields();

        // Run the test
        myClassUnderTest.storeNotBean4(bean);

        // Verify the results
    }
}
