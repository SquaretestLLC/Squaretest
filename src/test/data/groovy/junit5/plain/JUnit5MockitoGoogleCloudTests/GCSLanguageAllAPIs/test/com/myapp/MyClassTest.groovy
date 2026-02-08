package com.myapp

import com.google.api.core.ApiFuture
import com.google.api.core.SettableApiFuture
import com.google.api.gax.rpc.ApiCallContext
import com.google.api.gax.rpc.ApiException
import com.google.api.gax.rpc.UnaryCallable
import com.google.cloud.language.v1.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.concurrent.TimeUnit

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private LanguageServiceClient mockLanguageServiceClient

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockLanguageServiceClient)
    }

    @Test
    void testTryAnalyzeSentiment1() {
        // Setup
        // Configure LanguageServiceClient.analyzeSentiment(...).
        def analyzeSentimentResponse = AnalyzeSentimentResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeSentiment(Document.newBuilder().build(), EncodingType.NONE))
                .thenReturn(analyzeSentimentResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeSentiment1()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSentiment1_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeSentiment(Document.newBuilder().build(), EncodingType.NONE))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeSentiment1()
        })
    }

    @Test
    void testTryAnalyzeSentiment2() {
        // Setup
        // Configure LanguageServiceClient.analyzeSentiment(...).
        def analyzeSentimentResponse = AnalyzeSentimentResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeSentiment(Document.newBuilder().build()))
                .thenReturn(analyzeSentimentResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeSentiment2()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSentiment2_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeSentiment(Document.newBuilder().build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeSentiment2()
        })
    }

    @Test
    void testTryAnalyzeSentiment3() {
        // Setup
        // Configure LanguageServiceClient.analyzeSentiment(...).
        def analyzeSentimentResponse = AnalyzeSentimentResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeSentiment(AnalyzeSentimentRequest.newBuilder().build()))
                .thenReturn(analyzeSentimentResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeSentiment3()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSentiment3_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeSentiment(AnalyzeSentimentRequest.newBuilder().build()))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeSentiment3()
        })
    }

    @Test
    void testTryAnalyzeSentimentCallable() {
        // Setup
        // Configure LanguageServiceClient.analyzeSentimentCallable(...).
        def analyzeSentimentRequestAnalyzeSentimentResponseUnaryCallable = new UnaryCallable<AnalyzeSentimentRequest, AnalyzeSentimentResponse>() {
            @Override
            ApiFuture<AnalyzeSentimentResponse> futureCall(final AnalyzeSentimentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(AnalyzeSentimentResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeSentimentCallable())
                .thenReturn(analyzeSentimentRequestAnalyzeSentimentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeSentimentCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSentimentCallable_LanguageServiceClientReturnsFailure() {
        // Setup
        // Configure LanguageServiceClient.analyzeSentimentCallable(...).
        def analyzeSentimentRequestAnalyzeSentimentResponseUnaryCallable = new UnaryCallable<AnalyzeSentimentRequest, AnalyzeSentimentResponse>() {
            @Override
            ApiFuture<AnalyzeSentimentResponse> futureCall(final AnalyzeSentimentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeSentimentCallable())
                .thenReturn(analyzeSentimentRequestAnalyzeSentimentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeSentimentCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntities1() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntities(...).
        def analyzeEntitiesResponse = AnalyzeEntitiesResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeEntities(Document.newBuilder().build(), EncodingType.NONE))
                .thenReturn(analyzeEntitiesResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeEntities1()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntities1_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeEntities(Document.newBuilder().build(), EncodingType.NONE))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeEntities1()
        })
    }

    @Test
    void testTryAnalyzeEntities2() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntities(...).
        def analyzeEntitiesResponse = AnalyzeEntitiesResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeEntities(Document.newBuilder().build()))
                .thenReturn(analyzeEntitiesResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeEntities2()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntities2_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeEntities(Document.newBuilder().build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeEntities2()
        })
    }

    @Test
    void testTryAnalyzeEntities3() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntities(...).
        def analyzeEntitiesResponse = AnalyzeEntitiesResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeEntities(AnalyzeEntitiesRequest.newBuilder().build()))
                .thenReturn(analyzeEntitiesResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeEntities3()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntities3_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeEntities(AnalyzeEntitiesRequest.newBuilder().build()))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeEntities3()
        })
    }

    @Test
    void testTryAnalyzeEntitiesCallable() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitiesCallable(...).
        def analyzeEntitiesRequestAnalyzeEntitiesResponseUnaryCallable = new UnaryCallable<AnalyzeEntitiesRequest, AnalyzeEntitiesResponse>() {
            @Override
            ApiFuture<AnalyzeEntitiesResponse> futureCall(final AnalyzeEntitiesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(AnalyzeEntitiesResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeEntitiesCallable())
                .thenReturn(analyzeEntitiesRequestAnalyzeEntitiesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitiesCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntitiesCallable_LanguageServiceClientReturnsFailure() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitiesCallable(...).
        def analyzeEntitiesRequestAnalyzeEntitiesResponseUnaryCallable = new UnaryCallable<AnalyzeEntitiesRequest, AnalyzeEntitiesResponse>() {
            @Override
            ApiFuture<AnalyzeEntitiesResponse> futureCall(final AnalyzeEntitiesRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeEntitiesCallable())
                .thenReturn(analyzeEntitiesRequestAnalyzeEntitiesResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitiesCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntitySentiment1() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitySentiment(...).
        def analyzeEntitySentimentResponse = AnalyzeEntitySentimentResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeEntitySentiment(Document.newBuilder().build(),
                EncodingType.NONE)).thenReturn(analyzeEntitySentimentResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitySentiment1()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntitySentiment1_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeEntitySentiment(Document.newBuilder().build(),
                EncodingType.NONE)).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeEntitySentiment1()
        })
    }

    @Test
    void testTryAnalyzeEntitySentiment2() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitySentiment(...).
        def analyzeEntitySentimentResponse = AnalyzeEntitySentimentResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeEntitySentiment(Document.newBuilder().build()))
                .thenReturn(analyzeEntitySentimentResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitySentiment2()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntitySentiment2_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeEntitySentiment(Document.newBuilder().build()))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeEntitySentiment2()
        })
    }

    @Test
    void testTryAnalyzeEntitySentiment3() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitySentiment(...).
        def analyzeEntitySentimentResponse = AnalyzeEntitySentimentResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeEntitySentiment(
                AnalyzeEntitySentimentRequest.newBuilder().build())).thenReturn(analyzeEntitySentimentResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitySentiment3()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntitySentiment3_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeEntitySentiment(
                AnalyzeEntitySentimentRequest.newBuilder().build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeEntitySentiment3()
        })
    }

    @Test
    void testTryAnalyzeEntitySentimentCallable() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitySentimentCallable(...).
        def analyzeEntitySentimentRequestAnalyzeEntitySentimentResponseUnaryCallable = new UnaryCallable<AnalyzeEntitySentimentRequest, AnalyzeEntitySentimentResponse>() {
            @Override
            ApiFuture<AnalyzeEntitySentimentResponse> futureCall(final AnalyzeEntitySentimentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(AnalyzeEntitySentimentResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeEntitySentimentCallable())
                .thenReturn(analyzeEntitySentimentRequestAnalyzeEntitySentimentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitySentimentCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeEntitySentimentCallable_LanguageServiceClientReturnsFailure() {
        // Setup
        // Configure LanguageServiceClient.analyzeEntitySentimentCallable(...).
        def analyzeEntitySentimentRequestAnalyzeEntitySentimentResponseUnaryCallable = new UnaryCallable<AnalyzeEntitySentimentRequest, AnalyzeEntitySentimentResponse>() {
            @Override
            ApiFuture<AnalyzeEntitySentimentResponse> futureCall(final AnalyzeEntitySentimentRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeEntitySentimentCallable())
                .thenReturn(analyzeEntitySentimentRequestAnalyzeEntitySentimentResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeEntitySentimentCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSyntax1() {
        // Setup
        // Configure LanguageServiceClient.analyzeSyntax(...).
        def analyzeSyntaxResponse = AnalyzeSyntaxResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeSyntax(Document.newBuilder().build(), EncodingType.NONE))
                .thenReturn(analyzeSyntaxResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeSyntax1()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSyntax1_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeSyntax(Document.newBuilder().build(), EncodingType.NONE))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeSyntax1()
        })
    }

    @Test
    void testTryAnalyzeSyntax2() {
        // Setup
        // Configure LanguageServiceClient.analyzeSyntax(...).
        def analyzeSyntaxResponse = AnalyzeSyntaxResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeSyntax(Document.newBuilder().build())).thenReturn(analyzeSyntaxResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeSyntax2()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSyntax2_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeSyntax(Document.newBuilder().build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeSyntax2()
        })
    }

    @Test
    void testTryAnalyzeSyntax3() {
        // Setup
        // Configure LanguageServiceClient.analyzeSyntax(...).
        def analyzeSyntaxResponse = AnalyzeSyntaxResponse.newBuilder().build()
        when(mockLanguageServiceClient.analyzeSyntax(AnalyzeSyntaxRequest.newBuilder().build()))
                .thenReturn(analyzeSyntaxResponse)

        // Run the test
        myClassUnderTest.tryAnalyzeSyntax3()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSyntax3_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.analyzeSyntax(AnalyzeSyntaxRequest.newBuilder().build()))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnalyzeSyntax3()
        })
    }

    @Test
    void testTryAnalyzeSyntaxCallable() {
        // Setup
        // Configure LanguageServiceClient.analyzeSyntaxCallable(...).
        def analyzeSyntaxRequestAnalyzeSyntaxResponseUnaryCallable = new UnaryCallable<AnalyzeSyntaxRequest, AnalyzeSyntaxResponse>() {
            @Override
            ApiFuture<AnalyzeSyntaxResponse> futureCall(final AnalyzeSyntaxRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(AnalyzeSyntaxResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeSyntaxCallable())
                .thenReturn(analyzeSyntaxRequestAnalyzeSyntaxResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeSyntaxCallable()

        // Verify the results
    }

    @Test
    void testTryAnalyzeSyntaxCallable_LanguageServiceClientReturnsFailure() {
        // Setup
        // Configure LanguageServiceClient.analyzeSyntaxCallable(...).
        def analyzeSyntaxRequestAnalyzeSyntaxResponseUnaryCallable = new UnaryCallable<AnalyzeSyntaxRequest, AnalyzeSyntaxResponse>() {
            @Override
            ApiFuture<AnalyzeSyntaxResponse> futureCall(final AnalyzeSyntaxRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.analyzeSyntaxCallable())
                .thenReturn(analyzeSyntaxRequestAnalyzeSyntaxResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnalyzeSyntaxCallable()

        // Verify the results
    }

    @Test
    void testTryClassifyText1() {
        // Setup
        // Configure LanguageServiceClient.classifyText(...).
        def classifyTextResponse = ClassifyTextResponse.newBuilder().build()
        when(mockLanguageServiceClient.classifyText(Document.newBuilder().build())).thenReturn(classifyTextResponse)

        // Run the test
        myClassUnderTest.tryClassifyText1()

        // Verify the results
    }

    @Test
    void testTryClassifyText1_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.classifyText(Document.newBuilder().build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryClassifyText1()
        })
    }

    @Test
    void testTryClassifyText2() {
        // Setup
        // Configure LanguageServiceClient.classifyText(...).
        def classifyTextResponse = ClassifyTextResponse.newBuilder().build()
        when(mockLanguageServiceClient.classifyText(ClassifyTextRequest.newBuilder().build()))
                .thenReturn(classifyTextResponse)

        // Run the test
        myClassUnderTest.tryClassifyText2()

        // Verify the results
    }

    @Test
    void testTryClassifyText2_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.classifyText(ClassifyTextRequest.newBuilder().build()))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryClassifyText2()
        })
    }

    @Test
    void testTryClassifyTextCallable() {
        // Setup
        // Configure LanguageServiceClient.classifyTextCallable(...).
        def classifyTextRequestClassifyTextResponseUnaryCallable = new UnaryCallable<ClassifyTextRequest, ClassifyTextResponse>() {
            @Override
            ApiFuture<ClassifyTextResponse> futureCall(final ClassifyTextRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(ClassifyTextResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.classifyTextCallable())
                .thenReturn(classifyTextRequestClassifyTextResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryClassifyTextCallable()

        // Verify the results
    }

    @Test
    void testTryClassifyTextCallable_LanguageServiceClientReturnsFailure() {
        // Setup
        // Configure LanguageServiceClient.classifyTextCallable(...).
        def classifyTextRequestClassifyTextResponseUnaryCallable = new UnaryCallable<ClassifyTextRequest, ClassifyTextResponse>() {
            @Override
            ApiFuture<ClassifyTextResponse> futureCall(final ClassifyTextRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.classifyTextCallable())
                .thenReturn(classifyTextRequestClassifyTextResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryClassifyTextCallable()

        // Verify the results
    }

    @Test
    void testTryAnnotateText1() {
        // Setup
        // Configure LanguageServiceClient.annotateText(...).
        def annotateTextResponse = AnnotateTextResponse.newBuilder().build()
        when(mockLanguageServiceClient.annotateText(Document.newBuilder().build(),
                AnnotateTextRequest.Features.newBuilder().build(), EncodingType.NONE)).thenReturn(annotateTextResponse)

        // Run the test
        myClassUnderTest.tryAnnotateText1()

        // Verify the results
    }

    @Test
    void testTryAnnotateText1_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.annotateText(Document.newBuilder().build(),
                AnnotateTextRequest.Features.newBuilder().build(), EncodingType.NONE)).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnnotateText1()
        })
    }

    @Test
    void testTryAnnotateText2() {
        // Setup
        // Configure LanguageServiceClient.annotateText(...).
        def annotateTextResponse = AnnotateTextResponse.newBuilder().build()
        when(mockLanguageServiceClient.annotateText(Document.newBuilder().build(),
                AnnotateTextRequest.Features.newBuilder().build())).thenReturn(annotateTextResponse)

        // Run the test
        myClassUnderTest.tryAnnotateText2()

        // Verify the results
    }

    @Test
    void testTryAnnotateText2_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.annotateText(Document.newBuilder().build(),
                AnnotateTextRequest.Features.newBuilder().build())).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnnotateText2()
        })
    }

    @Test
    void testTryAnnotateText3() {
        // Setup
        // Configure LanguageServiceClient.annotateText(...).
        def annotateTextResponse = AnnotateTextResponse.newBuilder().build()
        when(mockLanguageServiceClient.annotateText(AnnotateTextRequest.newBuilder().build()))
                .thenReturn(annotateTextResponse)

        // Run the test
        myClassUnderTest.tryAnnotateText3()

        // Verify the results
    }

    @Test
    void testTryAnnotateText3_LanguageServiceClientThrowsApiException() {
        // Setup
        when(mockLanguageServiceClient.annotateText(AnnotateTextRequest.newBuilder().build()))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryAnnotateText3()
        })
    }

    @Test
    void testTryAnnotateTextCallable() {
        // Setup
        // Configure LanguageServiceClient.annotateTextCallable(...).
        def annotateTextRequestAnnotateTextResponseUnaryCallable = new UnaryCallable<AnnotateTextRequest, AnnotateTextResponse>() {
            @Override
            ApiFuture<AnnotateTextResponse> futureCall(final AnnotateTextRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(AnnotateTextResponse.newBuilder().build())
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.annotateTextCallable())
                .thenReturn(annotateTextRequestAnnotateTextResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnnotateTextCallable()

        // Verify the results
    }

    @Test
    void testTryAnnotateTextCallable_LanguageServiceClientReturnsFailure() {
        // Setup
        // Configure LanguageServiceClient.annotateTextCallable(...).
        def annotateTextRequestAnnotateTextResponseUnaryCallable = new UnaryCallable<AnnotateTextRequest, AnnotateTextResponse>() {
            @Override
            ApiFuture<AnnotateTextResponse> futureCall(final AnnotateTextRequest request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockLanguageServiceClient.annotateTextCallable())
                .thenReturn(annotateTextRequestAnnotateTextResponseUnaryCallable)

        // Run the test
        myClassUnderTest.tryAnnotateTextCallable()

        // Verify the results
    }

    @Test
    void testTryClose() {
        // Setup
        // Run the test
        myClassUnderTest.tryClose()

        // Verify the results
        verify(mockLanguageServiceClient).close()
    }

    @Test
    void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown()

        // Verify the results
        verify(mockLanguageServiceClient).shutdown()
    }

    @Test
    void testTryIsShutdown() {
        // Setup
        when(mockLanguageServiceClient.isShutdown()).thenReturn(false)

        // Run the test
        myClassUnderTest.tryIsShutdown()

        // Verify the results
    }

    @Test
    void testTryIsTerminated() {
        // Setup
        when(mockLanguageServiceClient.isTerminated()).thenReturn(false)

        // Run the test
        myClassUnderTest.tryIsTerminated()

        // Verify the results
    }

    @Test
    void testTryShutdownNow() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdownNow()

        // Verify the results
        verify(mockLanguageServiceClient).shutdownNow()
    }

    @Test
    void testTryAwaitTermination() {
        // Setup
        when(mockLanguageServiceClient.awaitTermination(0L, TimeUnit.MILLISECONDS)).thenReturn(false)

        // Run the test
        myClassUnderTest.tryAwaitTermination()

        // Verify the results
    }

    @Test
    void testTryAwaitTermination_LanguageServiceClientThrowsInterruptedException() {
        // Setup
        when(mockLanguageServiceClient.awaitTermination(0L, TimeUnit.MILLISECONDS))
                .thenThrow(InterruptedException.class)

        // Run the test
        assertThrows(InterruptedException.class, {
            myClassUnderTest.tryAwaitTermination()
        })
    }

    @Test
    void testTryCreate1() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate1()

        // Verify the results
    }

    @Test
    void testTryCreate1_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate1()
        })
    }

    @Test
    void testTryCreate2() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate2()

        // Verify the results
    }

    @Test
    void testTryCreate2_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate2()
        })
    }

    @Test
    void testTryCreate3() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate3()

        // Verify the results
    }
}
