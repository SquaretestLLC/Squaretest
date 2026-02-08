package com.myapp;

import com.google.cloud.dialogflow.cx.v3.QueryResult;
import com.google.cloud.dialogflow.cx.v3.SessionsClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SessionFactory mockSessionFactory;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockSessionFactory);
    }

    @Test
    void testDetectIntent() throws Exception {
        // Setup
        final Map<String, QueryResult> expectedResult = new HashMap<>();

        // Configure SessionFactory.openSession(...).
        final SessionsClient mockSessionsClient = mock(SessionsClient.class);
        when(mockSessionFactory.openSession()).thenReturn(mockSessionsClient);

        // Run the test
        final Map<String, QueryResult> result = myClassUnderTest.detectIntent("projectId", "locationId", "agentId",
                "sessionId", Arrays.asList("value"), "languageCode");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockSessionsClient).close();
    }
}
