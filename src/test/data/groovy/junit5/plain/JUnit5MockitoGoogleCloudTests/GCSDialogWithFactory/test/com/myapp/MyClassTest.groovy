package com.myapp

import com.google.cloud.dialogflow.cx.v3.QueryResult
import com.google.cloud.dialogflow.cx.v3.SessionsClient
import com.myapp.gcs.dialog.SessionFactory
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SessionFactory mockSessionFactory

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockSessionFactory)
    }

    @Test
    void testDetectIntent() {
        // Setup
        def expectedResult = ["value": QueryResult.newBuilder().build()]

        // Configure SessionFactory.openSession(...).
        def mockSessionsClient = mock(SessionsClient.class)
        when(mockSessionFactory.openSession()).thenReturn(mockSessionsClient)

        // Run the test
        def result = myClassUnderTest.detectIntent("projectId", "locationId", "agentId", "sessionId", ["value"],
                "languageCode")

        // Verify the results
        assert expectedResult == result
        verify(mockSessionsClient).close()
    }
}
