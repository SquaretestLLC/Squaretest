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

import com.squaretest.generation.InitStrategy;
import com.squaretest.template.AccessModifier;
import com.squaretest.template.api.Api;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClassMemberImplTest {

    @Test
    public void testIsAnnotatedWithAny_SearchForEmptyList() {
        // Setup
        final List<String> annotationCanonicalNames = List.of();
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsAnnotatedWithAny_SearchForNullList() {
        // Setup
        final List<String> annotationCanonicalNames = List.of();
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = null;

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsAnnotatedWithAny_NoAnnotationsOnMember() {
        // Setup
        final List<String> annotationCanonicalNames = List.of();
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final boolean expectedResult = false;
        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{"Inject", "javax.validation.constraints.NotEmpty.List"};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsAnnotatedWithAny_SearchForSimpleName_HasAnnotation() {
        // Setup
        final List<String> annotationCanonicalNames = List.of("javax.inject.Inject");
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{"Autowired", "javax.validation.constraints.NotEmpty.List", "Inject"};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsAnnotatedWithAny_SearchForCanonicalName_HasAnnotation() {
        // Setup
        final List<String> annotationCanonicalNames = List.of("javax.inject.Inject");
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{"javax.inject.Inject"};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsAnnotatedWithAny_SearchForCanonicalName_HasAnnotationWithCanonicalNameWithoutDot() {
        // Setup
        final List<String> annotationCanonicalNames = List.of("Inject");
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{"Inject", "Autowired"};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsAnnotatedWithAny_SearchForCanonicalName_DoesNotHaveAnnotation() {
        // Setup
        final List<String> annotationCanonicalNames = List.of("com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable");
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{"javax.inject.Inject"};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsAnnotatedWithAny_SearchForSimpleName_DoesNotHaveAnnotation() {
        // Setup
        final List<String> annotationCanonicalNames = List.of("com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable");
        final ClassMemberImpl classMemberImplUnderTest = new ClassMemberImpl(createFakeVar(annotationCanonicalNames), AccessModifier.Public, false, false, false);

        final String[] simpleOrCannonicalNamesToSearchFor = new String[]{"Inject"};

        // Run the test
        final boolean result = classMemberImplUnderTest.hasAnnotation(simpleOrCannonicalNamesToSearchFor);

        // Verify the results
        assertFalse(result);
    }

    private VariableImpl createFakeVar(final List<String> annotationCanonicalNames) {
        final TypeImpl type = new TypeImpl("name", "canonicalName", "canonicalText", "new Object()", null, false, "null", null, null, null, null, null, new FluentListImpl<>(), new FluentListImpl<>(), false,
                false, AccessModifier.Public, true, true, false, false, true, false, false, false, false, false, false,
                false, false, false, false, false,
                false, false, InitStrategy.Default);
        final Api.FluentList<Api.Annotation> annotations = new FluentListImpl<>();
        for(final String canonicalName : annotationCanonicalNames) {
            annotations.add(new AnnotationImpl(NameUtils.computeSimpleName(canonicalName), canonicalName, Api.FluentListFactory.emptyList()));
        }
        return new VariableImpl(type, "declaredName", "declaredNameWithoutPrefix",
                true, annotations, "testClassMemberName", "testClassLocalName", false, false, false);
    }

}
