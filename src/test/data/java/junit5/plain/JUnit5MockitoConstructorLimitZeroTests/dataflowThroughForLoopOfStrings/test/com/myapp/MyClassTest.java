package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

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
    void testGetSubFooFullDataForFooId() {
        // Setup
        final List<SubFooFullData> expectedResult = Arrays.asList(
                new SubFooFullData("subFooFullDataId", "subFooFullDataValue", "subFooFullDataOtherValue",
                        "subFooFullDataDifferentValue"));

        // Configure FooService.getFooById(...).
        final Foo foo = new Foo("id", "name", Arrays.asList("value"));
        when(mockFooService.getFooById("fooId")).thenReturn(foo);

        // Configure FooService.getSubFooById(...).
        final SubFooFullData subFooFullData = new SubFooFullData("subFooFullDataId", "subFooFullDataValue",
                "subFooFullDataOtherValue", "subFooFullDataDifferentValue");
        when(mockFooService.getSubFooById("getSubFooByIdParam")).thenReturn(subFooFullData);

        // Run the test
        final List<SubFooFullData> result = myClassUnderTest.getSubFooFullDataForFooId("fooId");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
