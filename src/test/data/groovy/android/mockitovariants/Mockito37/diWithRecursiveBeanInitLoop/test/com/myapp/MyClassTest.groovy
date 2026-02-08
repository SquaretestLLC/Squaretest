package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testMakeBean1() {
        // Setup
        // Configure Foo.makeBean1(...).
        def bean1 = new Bean1()
        bean1.setName("name")
        def bean2 = new Bean2()
        bean2.setName("name")
        bean1.setBean2(bean2)
        when(mockFoo.makeBean1()).thenReturn(bean1)

        // Run the test
        def result = myClassUnderTest.makeBean1()

        // Verify the results
    }
}
