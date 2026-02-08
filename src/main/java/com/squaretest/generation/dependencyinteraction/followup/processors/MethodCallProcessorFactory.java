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
package com.squaretest.generation.dependencyinteraction.followup.processors;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.squaretest.generation.dependencyinteraction.followup.processors.awsv1.dynamodb.DynamoDbClientResultProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.awsv1.dynamodb.DynamoDbMapperProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.awsv1.s3.S3ClientResultProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.awsv2.dynamodb.DynamoDBEnhancedProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.awsv2.dynamodb.DynamoDbClientProcessor;
import com.squaretest.generation.dependencyinteraction.followup.processors.awsv2.s3.S3ClientResponseProcessor;
import com.squaretest.generation.dependencyinteraction.outcomes.MethodPathsInfoProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MethodCallProcessorFactory {
    public static List<MethodCallProcessor> createProcessors(
            @NotNull final MethodPathsInfoProvider methodPathsInfoProvider,
            @NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass sourceClass) {
        return List.of(
                new JavaOptionalProcessor(methodPathsInfoProvider, javaPsiFacade, sourceClass),
                new JavaCompletableFutureProcessor(javaPsiFacade, sourceClass),
                new GuavaOptionalProcessor(javaPsiFacade, sourceClass),
                new JavaCollectionProcessor(javaPsiFacade, sourceClass),
                new JavaCollectionsProcessor(),
                new JavaMapProcessor(),
                new DynamoDbClientResultProcessor(),
                new DynamoDbMapperProcessor(),
                new DynamoDbClientProcessor(),
                new DynamoDBEnhancedProcessor(),
                new S3ClientResultProcessor(),
                new S3ClientResponseProcessor(),
                new JavaArraysProcessor(),
                new JavaStreamSupportProcessor(),
                new JavaStreamProcessor(),
                new SpringStreamableProcessor(),
                new ApacheCollectionUtilsProcessor(),
                new GenericCollectionUtilsProcessor(),
                new JavaIterableProcessor(),
                new JavaIteratorProcessor(),
                new JavaSpliteratorProcessor(),
                new ApacheCommonsLangValidateProcessor(javaPsiFacade, sourceClass),
                new ApacheCommonsLang3ValidateProcessor(javaPsiFacade, sourceClass),
                new AWSUtilsValidateProcessor(javaPsiFacade, sourceClass),
                new GuavaVerifyProcessor(javaPsiFacade, sourceClass),
                new SpringAssertProcessor(javaPsiFacade, sourceClass),
                new GuavaPreconditionsProcessor(javaPsiFacade, sourceClass),
                new IterableFactoryMethodProcessor(),
                new ObjectProcessor(),
                new ApacheHttpAssertsProcessor(javaPsiFacade, sourceClass),
                new ObjectsProcessor(javaPsiFacade, sourceClass)
        );
    }
}
