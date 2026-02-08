package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AuthService mockAuthService;
    @Mock
    private UserService mockUserService;
    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAuthService, mockUserService, mockFooService);
    }

    @Test
    void testGetFoos1() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos1("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos1_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos1("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos1_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos1("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos1("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos2() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos2("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos2_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos2("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos2_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos2("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos2("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos3() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroup("userId")).thenReturn("result");
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos3("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos3_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroup("userId")).thenReturn("result");
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos3("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroup("userId")).thenReturn("result");
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos3("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos4() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroup("userId")).thenReturn("result");
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos4("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos4_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroup("userId")).thenReturn("result");
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos4("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroup("userId")).thenReturn("result");
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos4("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos5() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos5("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos5_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos5("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos5_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos5("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos5_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos5("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos6() throws Exception {
        // Setup
        final Set<FooData> expectedResult = Set.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final Set<FooData> result = myClassUnderTest.getFoos6("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos6_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos6("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos6_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos6("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos6_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final Set<FooData> result = myClassUnderTest.getFoos6("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testGetFoos7() throws Exception {
        // Setup
        final Set<FooData> expectedResult = Set.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups2("userId")).thenReturn(new String[]{"result"});
        when(mockAuthService.getAllowedAuthGroupIds2()).thenReturn(new String[]{"result"});
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(new FooData[]{new FooData("id", "name")});

        // Run the test
        final Set<FooData> result = myClassUnderTest.getFoos7("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos7_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups2("userId")).thenReturn(new String[]{});
        when(mockAuthService.getAllowedAuthGroupIds2()).thenReturn(new String[]{"result"});

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos7("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos7_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups2("userId")).thenReturn(new String[]{"result"});
        when(mockAuthService.getAllowedAuthGroupIds2()).thenReturn(new String[]{});

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos7("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos7_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups2("userId")).thenReturn(new String[]{"result"});
        when(mockAuthService.getAllowedAuthGroupIds2()).thenReturn(new String[]{"result"});
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(new FooData[]{});

        // Run the test
        final Set<FooData> result = myClassUnderTest.getFoos7("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testGetFoos8() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos3("searchCriteria")).thenReturn(new FooData("id", "name"));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos8("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos8_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos8("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos8_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos8("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos9() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos3("searchCriteria")).thenReturn(new FooData("id", "name"));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos9("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos9_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos9("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos9_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos9("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos10() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(new FooData[]{new FooData("id", "name")});

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos10("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos10_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos10("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos10_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos10("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos10_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos2("searchCriteria")).thenReturn(new FooData[]{});

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos10("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos11() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(List.of(new OtherData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos11("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos11_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos11("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos11_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos11("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos11_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos11("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos12() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(List.of(new OtherData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos12("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos12_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos12("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos12_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos12("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos12_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos12("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos13() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(List.of(new OtherData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos13("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos13_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos13("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos13_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos13("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos13_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos13("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos14() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(List.of(new OtherData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos14("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos14_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos14("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos14_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos14("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos14_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos4("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos14("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos15() throws Exception {
        // Setup
        final List<FooData> expectedResult = List.of(new FooData("id", "name"));
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(List.of(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos15("userId", "searchCriteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos15_UserServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(Collections.emptyList());
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos15("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos15_AuthServiceReturnsNoItems() {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.getFoos15("userId", "searchCriteria"));
    }

    @Test
    void testGetFoos15_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAuthGroups1("userId")).thenReturn(List.of("value"));
        when(mockAuthService.getAllowedAuthGroupIds1()).thenReturn(List.of("value"));
        when(mockFooService.getFoos1("searchCriteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos15("userId", "searchCriteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
