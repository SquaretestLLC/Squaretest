/*
 * Copyright (c) 2022 Squaretest LLC. All Rights Reserved.
 * Use is subject to license terms.
 * This code is based on an example program created by Google Inc.
 * The code was modified to test Squaretest functionality.
 * The original copyright notice and license are included below.
 */
/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyClass {

    private final ImageAnnotatorClient client;

    public MyClass(final ImageAnnotatorClient client) {
        this.client = client;
    }

    /**
     * Performs document text detection on a local image file.
     *
     * @param filePath The path to the local file to detect document text on.
     * @throws IOException on Input/Output errors.
     */
    public void detectDocumentText(String filePath, final AnalyzeSentimentResponse analyzeSentimentResponse) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        // Read one property from AnalyzeSentimentResponse and set it on Feature.
        Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).setModel(analyzeSentimentResponse.getLanguage()).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();

        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                System.out.format("Error: %s%n", res.getError().getMessage());
                return;
            }

            // For full list of available annotations, see http://g.co/cloud/vision/docs
            TextAnnotation annotation = res.getFullTextAnnotation();
            for (Page page : annotation.getPagesList()) {
                String pageText = "";
                for (Block block : page.getBlocksList()) {
                    String blockText = "";
                    for (Paragraph para : block.getParagraphsList()) {
                        String paraText = "";
                        for (Word word : para.getWordsList()) {
                            String wordText = "";
                            for (Symbol symbol : word.getSymbolsList()) {
                                wordText = wordText + symbol.getText();
                                System.out.format(
                                        "Symbol text: %s (confidence: %f)%n",
                                        symbol.getText(), symbol.getConfidence());
                            }
                            System.out.format(
                                    "Word text: %s (confidence: %f)%n%n", wordText, word.getConfidence());
                            paraText = String.format("%s %s", paraText, wordText);
                        }
                        // Output Example using Paragraph:
                        System.out.println("%nParagraph: %n" + paraText);
                        System.out.format("Paragraph Confidence: %f%n", para.getConfidence());
                        blockText = blockText + paraText;
                    }
                    pageText = pageText + blockText;
                }
            }
            System.out.println("%nComplete annotation:");
            System.out.println(annotation.getText());
        }
    }
}