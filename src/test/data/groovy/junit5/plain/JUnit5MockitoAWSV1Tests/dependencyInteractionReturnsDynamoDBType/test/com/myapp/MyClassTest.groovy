package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testGetFooData() {
        // Setup
        // Configure Foo.getData(...).
        def fooData = new FooData("purchaseId", "licenseName", new OtherData("dataName"))
        when(mockFoo.getData("name")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData("name")

        // Verify the results
    }
}
