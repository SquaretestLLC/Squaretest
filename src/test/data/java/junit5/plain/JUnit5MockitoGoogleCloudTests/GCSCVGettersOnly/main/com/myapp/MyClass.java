/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;

public class MyClass {

    public AnnotateImageRequest createRequest() {
        // Test only calling getter methods on the builder.
        final AnnotateImageRequest.Builder builder = AnnotateImageRequest.newBuilder();
        for (Feature feature : builder.getFeaturesList()) {
            System.out.println(feature.getMaxResults());
        }
        builder.getFeatures(0);
        builder.getFeaturesBuilder(0);
        final Image image = builder.getImage();
        System.out.println(image.getContent());
        return builder.build();
    }
}