/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.squaretest.template.impl;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.squaretest.generation.PsiToTemplateDataConverter;
import com.squaretest.generation.defaulttypes.DefaultTypesHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClassUtilsImplTest {

    @Mock
    private PsiElement mockTestSourcesRoot;
    @Mock
    private JavaPsiFacade mockPsiFacade;
    @Mock
    private PsiToTemplateDataConverter mockPsiToTemplateDataConverter;

    private ClassUtilsImpl classUtilsImplUnderTest;

    @Before
    public void setUp() {
        classUtilsImplUnderTest = new ClassUtilsImpl(mockTestSourcesRoot, mockPsiFacade, mockPsiToTemplateDataConverter, DefaultTypesHolder.getInstance());
    }

    @Test
    public void testRemovePackageQualifiers() {
        // Setup
        final String canonicalText = "new com.myapp.Foo(\"aString\", 0, 0.0d, new java.io.File(\"path.name\"), new com.something.Bar(new com.something.OtherFoo(\"asdf\"), null), MyEnum.First)\n";
        final String expectedResult = "new Foo(\"aString\", 0, 0.0d, new File(\"path.name\"), new Bar(new OtherFoo(\"asdf\"), null), First)\n";

        // Run the test
        final String result = classUtilsImplUnderTest.removePackageQualifiers(canonicalText);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testRemovePackageQualifiersBuilder() {
        // Setup
        final String canonicalText = "software.amazon.awssdk.services.s3.model.AbortMultipartUploadResponse.builder().build()";
        final String expectedResult = "AbortMultipartUploadResponse.builder().build()";

        // Run the test
        final String result = classUtilsImplUnderTest.removePackageQualifiers(canonicalText);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
