package com.myapp;

import com.myapp.other.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

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
    void testGetFooWithUUID1() {
        // Setup
        when(mockFooService.getFooById1("be57af0f-a1e6-4f4f-9cb2-d4e85e976ed1")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID1(UUID.fromString("be57af0f-a1e6-4f4f-9cb2-d4e85e976ed1"));

        // Verify the results
    }

    @Test
    void testGetFooWithUUID2() {
        // Setup
        when(mockFooService.getFooByUUID2(UUID.fromString("7367f191-0872-4573-9337-a4213da77595")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID2(UUID.fromString("7367f191-0872-4573-9337-a4213da77595"));

        // Verify the results
    }

    @Test
    void testGetFooWithUUID3() {
        // Setup
        when(mockFooService.getFooByUUID3(UUID.fromString("d701f475-d033-45e8-a946-431d2f763efd")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID3("d701f475-d033-45e8-a946-431d2f763efd");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID4() {
        // Setup
        when(mockFooService.getFooByUUID4(UUID.fromString("387c2cac-df36-41a6-9591-a46ea4a4df22")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID4();

        // Verify the results
    }

    @Test
    void testGetFooWithUUID5() {
        // Setup
        when(mockFooService.getFooByUUID5("387c2cac-df36-41a6-9591-a46ea4a4df22")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID5();

        // Verify the results
    }

    @Test
    void testGetFooWithUUID6() {
        // Setup
        when(mockFooService.getFooByUUID6(UUID.fromString("5489e069-0820-41fb-bcea-b5a9644f3f66")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID6("5489e069-0820-41fb-bcea-b5a9644f3f66");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID7() {
        // Setup
        when(mockFooService.getFooByUUID7(UUID.fromString("eeddc68a-df5a-4cb0-a996-d15571e20d10")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID7("eeddc68a-df5a-4cb0-a996-d15571e20d10");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID8() {
        // Setup
        when(mockFooService.getFooByUUID8(UUID.fromString("c3f25b05-92c6-422f-95e7-4aaaeed26e29")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID8("c3f25b05-92c6-422f-95e7-4aaaeed26e29");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID9() {
        // Setup
        final Foo foo = new Foo("id", "name");
        when(mockFooService.getFooByUUID9(UUID.fromString("1602ac18-538d-4b6d-8494-07c4f06bf766")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID9(foo);

        // Verify the results
    }

    @Test
    void testGetFooWithUUID10() {
        // Setup
        final Foo foo = new Foo("id", "name");
        when(mockFooService.getFooByUUID10("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID10(foo);

        // Verify the results
    }

    @Test
    void testGetFooWithUUID11() {
        // Setup
        final Foo foo = new Foo("id", "name");
        when(mockFooService.getFooByUUID11("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID11(foo);

        // Verify the results
    }

    @Test
    void testGetFooWithUUID12() {
        // Setup
        when(mockFooService.getFooByUUID12(UUID.fromString("2a9f6789-20f8-479c-bdb2-db82845c5a57"),
                UUID.fromString("2a9f6789-20f8-479c-bdb2-db82845c5a57"))).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID12(UUID.fromString("2a9f6789-20f8-479c-bdb2-db82845c5a57"));

        // Verify the results
    }

    @Test
    void testGetFooWithUUID13() {
        // Setup
        when(mockFooService.getFooByUUID13(UUID.fromString("c87fb300-6fbf-4d76-a7af-12ac0384a40b")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID13("c87fb300-6fbf-4d76-a7af-12ac0384a40b");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID14() {
        // Setup
        when(mockFooService.getFooByUUID14("fooId")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID14("fooId");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID15() {
        // Setup
        when(mockFooService.getFooByUUID15(UUID.fromString("af510f6d-2345-49a4-9dd1-f598f10e3ddd")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID15("af510f6d-2345-49a4-9dd1-f598f10e3ddd");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID16() {
        // Setup
        when(mockFooService.getFooByUUID16(UUID.fromString("387c2cac-df36-41a6-9591-a46ea4a4df22")))
                .thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID16();

        // Verify the results
    }

    @Test
    void testGetFooWithUUID17() {
        // Setup
        when(mockFooService.getFooByUUID17("387c2cac-df36-41a6-9591-a46ea4a4df22")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID17();

        // Verify the results
    }

    @Test
    void testGetFooWithUUID18() {
        // Setup
        when(mockFooService.getFooByUUID18("f53e5b76-f5ea-4bdd-911a-169e5146c193")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID18("f53e5b76-f5ea-4bdd-911a-169e5146c193");

        // Verify the results
    }

    @Test
    void testGetFooWithUUID19() {
        // Setup
        when(mockFooService.getFooByUUID19(Constants.ConstantFooId2)).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID19();

        // Verify the results
    }

    @Test
    void testGetFooWithUUID20() {
        // Setup
        when(mockFooService.getFooByUUID20(Constants.ConstantCircular2)).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID20();

        // Verify the results
    }

    @Test
    void testGetFooWithUUID21() {
        // Setup
        when(mockFooService.getFooByUUID21(Constants.UUIDThatUsesStringCircular2)).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooWithUUID21();

        // Verify the results
    }
}
