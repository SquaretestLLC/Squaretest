package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyList<String> mockValues;

    private MyClass<String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>(mockFooService);
        // TODO: Set the following fields: values.
    }

    @Test
    void testGet() {
        assertThat(myClassUnderTest.get(0)).isEqualTo("result");
        assertThatThrownBy(() -> myClassUnderTest.get(0)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testAddAll() {
        assertThat(myClassUnderTest.addAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.addAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testRemoveAll() {
        assertThat(myClassUnderTest.removeAll(Arrays.asList("value"))).isFalse();
        assertThatThrownBy(() -> myClassUnderTest.removeAll(Arrays.asList("value")))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.removeAll(Arrays.asList("value")))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testRemoveIf() {
        // Setup
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        final boolean result = myClassUnderTest.removeIf(filter);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testRemoveIf_ThrowsNullPointerException() {
        // Setup
        final Predicate<? super String> filter = val -> {
            return false;
        };

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.removeIf(filter)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testGetTheValues() {
        assertThat(myClassUnderTest.getTheValues()).isEqualTo(mockValues);
    }

    @Test
    void testGetFooDatas1() {
        // Setup
        final FooData1 fooData1 = new FooData1();
        fooData1.setId("id");
        fooData1.setName("name");
        final SubFoo subFoo = new SubFoo();
        subFoo.setSubFooId("subFooId");
        subFoo.setSubFooName("subFooName");
        fooData1.setSubFoos(Arrays.asList(subFoo));
        final MyList<FooData1> expectedResult = new MyList<>(Arrays.asList(fooData1));

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooDatas();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooDatasEmpty() {
        assertThat(myClassUnderTest.getFooDatasEmpty()).isEqualTo(new MyList<>());
    }

    @Test
    void testGetFooDatas2() {
        assertThat(myClassUnderTest.getFooDatas(new ArrayList<>(Arrays.asList(new FooData1()))))
                .isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetFooFromService() {
        // Setup
        final FooData1 fooData1 = new FooData1();
        fooData1.setId("id");
        fooData1.setName("name");
        final SubFoo subFoo = new SubFoo();
        subFoo.setSubFooId("subFooId");
        subFoo.setSubFooName("subFooName");
        fooData1.setSubFoos(Arrays.asList(subFoo));
        final MyList<FooData1> expectedResult = new MyList<>(Arrays.asList(fooData1));

        // Configure FooService.getFooData1(...).
        final FooData1 fooData11 = new FooData1();
        fooData11.setId("id");
        fooData11.setName("name");
        final SubFoo subFoo1 = new SubFoo();
        subFoo1.setSubFooId("subFooId");
        subFoo1.setSubFooName("subFooName");
        fooData11.setSubFoos(Arrays.asList(subFoo1));
        final MyList<FooData1> fooData1s = new MyList<>(Arrays.asList(fooData11));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1s);

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetFooFromService_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new MyList<>());

        // Run the test
        final MyList<FooData1> result = myClassUnderTest.getFooFromService("id");

        // Verify the results
        assertThat(result).isEqualTo(new MyList<>());
    }

    @Test
    void testLoadData1() {
        // Setup
        when(mockFooService.loadData1(String.class, "theId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.loadData1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testLoadDatas1() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testLoadDatas1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(String.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.loadDatas1(String.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testLoadData2() {
        // Setup
        final FooData2 expectedResult = new FooData2();
        expectedResult.setFooData2Id("fooData2Id");
        expectedResult.setFooData2Name("fooData2Name");

        // Configure FooService.loadData1(...).
        final FooData2 fooData2 = new FooData2();
        fooData2.setFooData2Id("fooData2Id");
        fooData2.setFooData2Name("fooData2Name");
        when(mockFooService.loadData1(FooData2.class, "theId")).thenReturn(fooData2);

        // Run the test
        final FooData2 result = myClassUnderTest.loadData2(FooData2.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testLoadDatas2() {
        // Setup
        final FooData2 fooData2 = new FooData2();
        fooData2.setFooData2Id("fooData2Id");
        fooData2.setFooData2Name("fooData2Name");
        final List<FooData2> expectedResult = Arrays.asList(fooData2);

        // Configure FooService.loadDatas1(...).
        final FooData2 fooData21 = new FooData2();
        fooData21.setFooData2Id("fooData2Id");
        fooData21.setFooData2Name("fooData2Name");
        final List<FooData2> fooData2s = Arrays.asList(fooData21);
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(fooData2s);

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testLoadDatas2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.loadDatas1(FooData2.class, "theId")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData2> result = myClassUnderTest.loadDatas2(FooData2.class, "theId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testLoadFooData3() {
        // Setup
        final FooData3 expectedResult = new FooData3();
        expectedResult.setFooData3Id("fooData3Id");
        expectedResult.setFooData3Name("fooData3Name");

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result");

        // Run the test
        final FooData3 result = myClassUnderTest.loadFooData3("loadFooData3Param");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testLoadFooData4() {
        // Setup
        final FooData4 expectedResult = new FooData4();
        expectedResult.setFooData4Id("fooData4Id");
        expectedResult.setFooData4Name("fooData4Name");

        when(mockFooService.loadData2(String.class, "loadFooData3Param")).thenReturn("result");

        // Run the test
        final FooData4 result = myClassUnderTest.loadFooData4("loadFooData3Param");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSort1() {
        // Setup
        // Run the test
        MyClass.sort(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testSort2() {
        // Setup
        final Comparator<? super String> c = Comparator.comparing(Object::toString);

        // Run the test
        MyClass.sort(Arrays.asList("value"), c);

        // Verify the results
    }

    @Test
    void testBinarySearch1() {
        assertThat(MyClass.binarySearch(Arrays.asList(), "key")).isEqualTo(-1);
    }

    @Test
    void testBinarySearch2() {
        assertThat(
                MyClass.binarySearch(Arrays.asList("value"), "key", Comparator.comparing(Object::toString)))
                .isEqualTo(-1);
    }

    @Test
    void testShuffle() {
        // Setup
        // Run the test
        MyClass.shuffle(Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testCopy() {
        // Setup
        // Run the test
        MyClass.copy(Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testMin() {
        assertThat(MyClass.min(Arrays.asList("value"))).isNull();
    }

    @Test
    void testDefaultIfBlank() {
        assertThat(MyClass.defaultIfBlank("str", "defaultStr")).isEqualTo("result");
    }

    @Test
    void testFirstNonBlank() {
        assertThat(MyClass.firstNonBlank("values")).isEqualTo("result");
    }

    @Test
    void testGetIfBlank() {
        assertThat(MyClass.getIfBlank("str", () -> "value")).isEqualTo("result");
    }

    @Test
    void testGetIfEmpty() {
        assertThat(MyClass.getIfEmpty("str", () -> "value")).isEqualTo("result");
    }

    @Test
    void testJoin() {
        assertThat(MyClass.join("elements")).isEqualTo("result");
    }
}
