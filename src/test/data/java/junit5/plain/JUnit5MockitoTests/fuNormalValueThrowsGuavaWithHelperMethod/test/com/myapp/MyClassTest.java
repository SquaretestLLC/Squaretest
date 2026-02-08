package com.myapp;

import com.google.common.base.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AuthService mockAuthService;
    @Mock
    private UserInfoService mockUserInfoService;
    @Mock
    private FooService mockFooService;
    @Mock
    private OldService mockOldService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAuthService, mockUserInfoService, mockFooService, mockOldService);
    }

    @Test
    void testStoreFoo1_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooDataOptional = Optional.of(new FooData("fooId", "name"));
        when(mockFooService.getFooData1("fooId")).thenReturn(fooDataOptional);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData1("fooId")).thenReturn(Optional.absent());

        // Configure OldService.getFooData1(...).
        final Optional<FooData> fooDataOptional = Optional.of(new FooData("fooId", "name"));
        when(mockOldService.getFooData1("fooId")).thenReturn(fooDataOptional);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_OldServiceReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockFooService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockOldService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo1(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo1_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockOldService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo2_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooDataOptional = Optional.of(new FooData("fooId", "name"));
        when(mockFooService.getFooData1("fooId")).thenReturn(fooDataOptional);

        // Configure OldService.getFooData1(...).
        final Optional<FooData> fooDataOptional1 = Optional.of(new FooData("fooId", "name"));
        when(mockOldService.getFooData1("fooId")).thenReturn(fooDataOptional1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo2("userId", fooData));
    }

    @Test
    void testStoreFoo2_AuthServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Collections.emptyList());
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo2("userId", fooData));
    }

    @Test
    void testStoreFoo2_UserInfoServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo2("userId", fooData));
    }

    @Test
    void testStoreFoo2_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData1("fooId")).thenReturn(Optional.absent());

        // Configure OldService.getFooData1(...).
        final Optional<FooData> fooDataOptional = Optional.of(new FooData("fooId", "name"));
        when(mockOldService.getFooData1("fooId")).thenReturn(fooDataOptional);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo2("userId", fooData));
    }

    @Test
    void testStoreFoo2_OldServiceReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockOldService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo2("userId", fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo2_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockOldService.getFooData1("fooId")).thenReturn(Optional.absent());
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo2("userId", fooData));
    }

    @Test
    void testStoreFoo3_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo3(fooData));
    }

    @Test
    void testStoreFoo3_FooServiceGetFooData2ThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo3(fooData));
    }

    @Test
    void testStoreFoo3_OldServiceThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo3(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo3_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo3(fooData));
    }

    @Test
    void testStoreFoo4_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo4("userId", fooData));
    }

    @Test
    void testStoreFoo4_AuthServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Collections.emptyList());
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo4("userId", fooData));
    }

    @Test
    void testStoreFoo4_UserInfoServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo4("userId", fooData));
    }

    @Test
    void testStoreFoo4_FooServiceGetFooData2ThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo4("userId", fooData));
    }

    @Test
    void testStoreFoo4_OldServiceThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo4("userId", fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo4_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo4("userId", fooData));
    }

    @Test
    void testStoreFoo5_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo5_FooServiceGetFooData2ThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo5_OldServiceThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo5(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo5_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo6_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo6("userId", fooData));
    }

    @Test
    void testStoreFoo6_AuthServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Collections.emptyList());
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo6("userId", fooData));
    }

    @Test
    void testStoreFoo6_UserInfoServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo6("userId", fooData));
    }

    @Test
    void testStoreFoo6_FooServiceGetFooData2ThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo6("userId", fooData));
    }

    @Test
    void testStoreFoo6_OldServiceThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo6("userId", fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo6_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo6("userId", fooData));
    }

    @Test
    void testStoreFoo7_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo7(fooData));
    }

    @Test
    void testStoreFoo7_FooServiceGetFooData2ThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo7(fooData));
    }

    @Test
    void testStoreFoo7_OldServiceThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo7(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo7_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo7(fooData));
    }

    @Test
    void testStoreFoo8_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo8("userId", fooData));
    }

    @Test
    void testStoreFoo8_AuthServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Collections.emptyList());
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo8("userId", fooData));
    }

    @Test
    void testStoreFoo8_UserInfoServiceReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(UserNotAuthorizedException.class, () -> myClassUnderTest.storeFoo8("userId", fooData));
    }

    @Test
    void testStoreFoo8_FooServiceGetFooData2ThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenReturn(new FooData("fooId", "name"));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo8("userId", fooData));
    }

    @Test
    void testStoreFoo8_OldServiceThrowsFooNotFoundException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        final FooData expectedResult = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenReturn(new FooData("fooId", "name"));

        // Run the test
        final FooData result = myClassUnderTest.storeFoo8("userId", fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo8_FooServiceStoreFoo1ThrowsFooStoreException() throws Exception {
        // Setup
        final FooData fooData = new FooData("fooId", "name");
        when(mockAuthService.getAllowedGroupIds1("StoreFoo")).thenReturn(Arrays.asList("value"));
        when(mockUserInfoService.getUsersAuthGroups("userId")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockOldService.getFooData2("fooId")).thenThrow(FooNotFoundException.class);
        when(mockFooService.storeFoo1(new FooData("fooId", "name"))).thenThrow(FooStoreException.class);

        // Run the test
        assertThrows(FooStoreException.class, () -> myClassUnderTest.storeFoo8("userId", fooData));
    }
}
