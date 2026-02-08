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
package com.squaretest.generation.defaulttypes;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.defaulttypes.JavaSourcesGenerator.*;
import static com.squaretest.generation.defaulttypes.TypeCreatorUtil.determineNumberOfPlaceholdersInInitExpression;

class GroovySourcesGenerator {

    private static final String GeneratedClassName = "DefaultTypes";
    private final Map<String, DefaultTypeInfo> defaultTypesMap;
    private final String groovyInitExpressionsDir;

    GroovySourcesGenerator(
            final Map<String, DefaultTypeInfo> defaultTypesMap, final String groovyInitExpressionsDir) {
        this.defaultTypesMap = defaultTypesMap;
        this.groovyInitExpressionsDir = groovyInitExpressionsDir;
    }

    void generateAll() {
        generateGroovySources();
    }

    void generateGroovySources() {
        final Map<String, Integer> classNamesToUsageCount = new HashMap<>();

        // Collect field declarations.
        final List<String> fieldDeclarationLines = new ArrayList<>(defaultTypesMap.size());
        for(final Map.Entry<String, DefaultTypeInfo> entry : defaultTypesMap.entrySet()) {
            final DefaultTypeInfo defaultTypeInfo = entry.getValue();
            if(StringUtils.equalsAny(defaultTypeInfo.getCanonicalName(), "java.util.concurrent.TimeUnit", "com.fasterxml.jackson.core.type.TypeReference", "org.springframework.core.ParameterizedTypeReference")) {
                continue;
            }
            if(defaultTypeInfo.getGroovyInitExpression() != null) {
                final String fieldDeclarationLine = String.format("private %s %s = %s", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        defaultTypeInfo.getGroovyInitExpression());
                fieldDeclarationLines.add(fieldDeclarationLine);
            } else {
                final String fieldDeclarationLine = String.format("private %s %s = %s", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        defaultTypeInfo.getInitExpression());
                fieldDeclarationLines.add(fieldDeclarationLine);
            }

            final int numberOfTypes = determineNumberOfPlaceholdersInInitExpression(defaultTypeInfo.getGroovyInitExpressionWithTypePlaceholder());
            if(numberOfTypes != 0) {
                final String typeParamsStr = buildTypeParamsStr(numberOfTypes);
                final String newInitExpression =
                        fillInPlaceholders(defaultTypeInfo.getGroovyInitExpressionWithTypePlaceholder(), numberOfTypes);
                String fieldDeclarationLineWithTypes = String.format("private %s" + typeParamsStr + " %s = %s", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        newInitExpression);
                fieldDeclarationLines.add(fieldDeclarationLineWithTypes);
                // Construct failure init expression.
                String failureInitExpressionWithTypePlaceholder = defaultTypeInfo.getGroovyFailureInitExpressionWithTypePlaceholder();
                if(failureInitExpressionWithTypePlaceholder == null) {
                    failureInitExpressionWithTypePlaceholder = defaultTypeInfo.getFailureInitExpressionWithTypePlaceholder();
                }
                if(failureInitExpressionWithTypePlaceholder != null) {
                    final String newFailureExpressionWithTypePlaceholder =
                            fillInPlaceholders(failureInitExpressionWithTypePlaceholder, numberOfTypes);
                    final String failureFieldDeclarationLineWithTypes = String.format("private %s" + typeParamsStr + " %s = %s", entry.getKey(),
                            getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                            newFailureExpressionWithTypePlaceholder);
                    fieldDeclarationLines.add(failureFieldDeclarationLineWithTypes);
                }
            }
            final String absentInitExpression = defaultTypeInfo.getAbsentInitExpression();
            if(absentInitExpression != null) {
                final String absentDeclarationLine = String.format("private %s %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount), absentInitExpression);
                fieldDeclarationLines.add(absentDeclarationLine);
            }
            String emptyInitExpression = defaultTypeInfo.getGroovyEmptyInitExpression();
            if(emptyInitExpression == null) {
                emptyInitExpression = defaultTypeInfo.getEmptyInitExpression();
            }
            if(emptyInitExpression != null) {
                final String emptyDeclarationLine = String.format("private %s %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount), emptyInitExpression);
                fieldDeclarationLines.add(emptyDeclarationLine);
            }
            String failureInitExpression = defaultTypeInfo.getGroovyFailureInitExpression();
            if(failureInitExpression == null) {
                failureInitExpression = defaultTypeInfo.getFailureInitExpression();
            }
            if(failureInitExpression != null) {
                final String failureInitFieldLine = String.format("private %s %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        failureInitExpression);
                fieldDeclarationLines.add(failureInitFieldLine);
            }
        }
        // Collect imports.
        final Set<String> importLines = collectImportLines(defaultTypesMap.values());
        String classFileText = generateClass(importLines, fieldDeclarationLines);
        writeFileToDestination(groovyInitExpressionsDir, GeneratedClassName, classFileText);
    }

    private static String generateClass(final Set<String> importLines, final List<String> fieldDeclarationLines) {
        final String packageLine = "package generated;";

        // Store start and end class declaration pieces.
        final String classHeader = "class " + GeneratedClassName + " {";
        final String defaultConstructor = GeneratedClassName + "() throws Exception {}";
        final String classEnd = "}";

        // Create the class source-code.
        final StringBuilder classBuilder = new StringBuilder();
        classBuilder.append(packageLine);
        classBuilder.append("\n");
        for(final String importLine : importLines) {
            classBuilder.append(importLine);
            classBuilder.append("\n");
        }

        classBuilder.append("\n");
        classBuilder.append(classHeader);
        classBuilder.append("\n");
        classBuilder.append(defaultConstructor);
        classBuilder.append("\n");
        for(final String fieldDeclaration : fieldDeclarationLines) {
            classBuilder.append(fieldDeclaration);
            classBuilder.append("\n");
        }

        classBuilder.append(classEnd);
        return classBuilder.toString();
    }

    void writeFileToDestination(final String dirName, final String classFileName, final String classFileText) {
        final File file = new File(dirName + File.separator + classFileName + ".groovy");
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            // false to overwrite.
            fileWriter.write(classFileText);
            fileWriter.close();
        } catch(final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
