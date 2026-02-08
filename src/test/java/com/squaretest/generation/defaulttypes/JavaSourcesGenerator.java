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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.squaretest.generation.defaulttypes.TypeCreatorUtil.determineNumberOfPlaceholdersInInitExpression;

class JavaSourcesGenerator {

    static final String GeneratedClassName = "DefaultTypes";
    // Skip ParameterizedTypeReference, because
    // the default init expression uses <>(){} for the default init expression, and inferred type params in anon inner
    // classes are not supported before Java 9. We still want to use this in prod, because the user will likely want to
    // fill in the type params in cases where we can't infer them. Generating the current default type gets them closer
    // to achieving that goal.
    static final String[] Java9Types = new String[]{"org.springframework.core.ParameterizedTypeReference", "com.fasterxml.jackson.core.type.TypeReference"};
    // Types only available in Java21+. The class java.net.http.HttpResponse is available earlier, but it's easier to
    // test it in the Java21+ tests.
    static final String[] Java21Types = new String[]{"java.net.http.HttpResponse", "java.util.SequencedCollection", "java.util.SequencedMap", "java.util.SequencedSet"};
    private final Map<String, DefaultTypeInfo> defaultTypesMap;
    private final String java8InitExpressionsDir;
    private final String java9InitExpressionsDir;
    private final String java21InitExpressionsDir;

    JavaSourcesGenerator(
            final Map<String, DefaultTypeInfo> defaultTypesMap,
            final String java8InitExpressionsDir,
            final String java9InitExpressionsDir,
            final String java21InitExpressionsDir) {
        this.defaultTypesMap = defaultTypesMap;
        this.java8InitExpressionsDir = java8InitExpressionsDir;
        this.java9InitExpressionsDir = java9InitExpressionsDir;
        this.java21InitExpressionsDir = java21InitExpressionsDir;
    }

    void generateAll() {
        generateJava8Sources();
        generateJava9Sources();
        generateJava21Sources();
    }

    void generateJava8Sources() {
        final Map<String, Integer> classNamesToUsageCount = new HashMap<>();

        // Collect field declarations.
        ArrayList<String> fieldDeclarationLines = new ArrayList<>(defaultTypesMap.size());
        for(final Map.Entry<String, DefaultTypeInfo> entry : defaultTypesMap.entrySet()) {
            final DefaultTypeInfo defaultTypeInfo = entry.getValue();
            if(StringUtils.equalsAny(defaultTypeInfo.getCanonicalName(), Java9Types) || StringUtils.equalsAny(defaultTypeInfo.getCanonicalName(), Java21Types)) {
                continue;
            }
            final String fieldDeclarationLine = String.format("private %s %s = %s;", entry.getKey(),
                    getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount), defaultTypeInfo.getInitExpression());
            fieldDeclarationLines.add(fieldDeclarationLine);
            final int numberOfTypes = determineNumberOfPlaceholdersInInitExpression(defaultTypeInfo.getInitExpressionWithTypePlaceholder());
            if(numberOfTypes != 0) {
                final String typeParamsStr = buildTypeParamsStr(numberOfTypes);
                final String newInitExpression =
                        fillInPlaceholders(defaultTypeInfo.getInitExpressionWithTypePlaceholder(), numberOfTypes);
                String fieldDeclarationLineWithTypes = String.format("private %s" + typeParamsStr + " %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        newInitExpression);
                fieldDeclarationLines.add(fieldDeclarationLineWithTypes);
                // Construct failure init expression.
                String failureInitExpressionWithTypePlaceholder = defaultTypeInfo.getFailureInitExpressionWithTypePlaceholder();
                if(failureInitExpressionWithTypePlaceholder != null) {
                    final String newFailureExpressionWithTypePlaceholder =
                            fillInPlaceholders(failureInitExpressionWithTypePlaceholder, numberOfTypes);
                    final String failureFieldDeclarationLineWithTypes = String.format("private %s" + typeParamsStr + " %s = %s;", entry.getKey(),
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

            final String emptyInitExpression = defaultTypeInfo.getEmptyInitExpression();
            if(emptyInitExpression != null) {
                final String emptyDeclarationLine = String.format("private %s %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount), emptyInitExpression);
                fieldDeclarationLines.add(emptyDeclarationLine);
            }

            final String failureInitExpression = defaultTypeInfo.getFailureInitExpression();
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
        writeFileToDestination(java8InitExpressionsDir, GeneratedClassName, classFileText);
    }

    void generateJava9Sources() {
        final Map<String, Integer> classNamesToUsageCount = new HashMap<>();

        // Collect field declarations.
        final List<String> fieldDeclarationLines = new ArrayList<>(defaultTypesMap.size());
        for(final Map.Entry<String, DefaultTypeInfo> entry : defaultTypesMap.entrySet()) {
            final DefaultTypeInfo defaultTypeInfo = entry.getValue();
            if(StringUtils.equalsAny(defaultTypeInfo.getCanonicalName(), Java21Types)) {
                continue;
            }
            if(defaultTypeInfo.getJava9InitExpression() != null) {
                final String fieldDeclarationLine = String.format("private %s %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        defaultTypeInfo.getJava9InitExpression());
                fieldDeclarationLines.add(fieldDeclarationLine);
            }
            final int numberOfTypes = determineNumberOfPlaceholdersInInitExpression(defaultTypeInfo.getJava9InitExpressionWithTypePlaceholder());
            if(numberOfTypes != 0) {
                final String typeParamsStr = buildTypeParamsStr(numberOfTypes);
                final String newInitExpression =
                        fillInPlaceholders(defaultTypeInfo.getJava9InitExpressionWithTypePlaceholder(), numberOfTypes);
                String fieldDeclarationLineWithTypes = String.format("private %s" + typeParamsStr + " %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        newInitExpression);
                fieldDeclarationLines.add(fieldDeclarationLineWithTypes);
            }
        }
        // Collect imports.
        final Set<String> importLines = collectImportLines(defaultTypesMap.values());
        String classFileText = generateClass(importLines, fieldDeclarationLines);
        writeFileToDestination(java9InitExpressionsDir, GeneratedClassName, classFileText);
    }

    public void generateJava21Sources() {
        final Map<String, Integer> classNamesToUsageCount = new HashMap<>();
        // Collect field declarations.
        final List<String> fieldDeclarationLines = new ArrayList<>(defaultTypesMap.size());
        for(final Map.Entry<String, DefaultTypeInfo> entry : defaultTypesMap.entrySet()) {
            final DefaultTypeInfo defaultTypeInfo = entry.getValue();
            if(!StringUtils.equalsAny(defaultTypeInfo.getCanonicalName(), Java21Types)) {
                continue;
            }
            if(defaultTypeInfo.getJava9InitExpression() != null) {
                final String fieldDeclarationLine = String.format("private %s %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        defaultTypeInfo.getJava9InitExpression());
                fieldDeclarationLines.add(fieldDeclarationLine);
            }
            final int numberOfTypes = determineNumberOfPlaceholdersInInitExpression(defaultTypeInfo.getJava9InitExpressionWithTypePlaceholder());
            if(numberOfTypes != 0) {
                final String typeParamsStr = buildTypeParamsStr(numberOfTypes);
                final String newInitExpression =
                        fillInPlaceholders(defaultTypeInfo.getJava9InitExpressionWithTypePlaceholder(), numberOfTypes);
                String fieldDeclarationLineWithTypes = String.format("private %s" + typeParamsStr + " %s = %s;", entry.getKey(),
                        getMemberNameFromCanonicalName(entry.getValue().getCanonicalName(), classNamesToUsageCount),
                        newInitExpression);
                fieldDeclarationLines.add(fieldDeclarationLineWithTypes);
            }
        }
        // Collect imports.
        final Set<String> importLines = collectImportLines(defaultTypesMap.values());
        String classFileText = generateClass(importLines, fieldDeclarationLines);
        writeFileToDestination(java21InitExpressionsDir, GeneratedClassName, classFileText);
    }

    private static String generateClass(final Set<String> importLines, final List<String> fieldDeclarationLines) {
        final String packageLine = "package generated;";

        // Store start and end class declaration pieces.
        final String classHeader = "public class " + GeneratedClassName + " {";
        final String defaultConstructor = "public " + GeneratedClassName + "() throws Exception {}";
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

    private void writeFileToDestination(final String dirName, final String classFileName, final String classFileText) {
        final File file = new File(dirName + File.separator + classFileName + ".java");
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            // false to overwrite.
            fileWriter.write(classFileText);
            fileWriter.close();
        } catch(final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    static Set<String> collectImportLines(final Collection<DefaultTypeInfo> defaultTypeInfos) {
        final Set<String> imports = new HashSet<>();
        for(final DefaultTypeInfo defaultTypeInfo : defaultTypeInfos) {
            final List<String> importsRequiredForType = defaultTypeInfo.getImportsRequired();
            for(final String importForType : importsRequiredForType) {
                final String importLine = String.format("import %s;", importForType);
                imports.add(importLine);
            }

        }

        return imports;
    }

    static String getMemberNameFromCanonicalName(
            final String canonicalName, final Map<String, Integer> classNamesToUsageCount) {
        String className;
        if(canonicalName.contains(".")) {
            className = canonicalName.substring(canonicalName.lastIndexOf(".") + 1);
        } else {
            className = canonicalName;
        }

        String usageSuffix;
        final Integer usageForName = classNamesToUsageCount.get(className);
        if(usageForName == null) {
            usageSuffix = "0";
            classNamesToUsageCount.put(className, 1);
        } else {
            usageSuffix = Integer.toString(usageForName);
            classNamesToUsageCount.put(className, usageForName + 1);
        }

        // Add a usageSuffix to avoid having the member name be a keyword and also handle cases where the class-name
        // is the same, but the canonical name is different; e.g. classes in commons-lang2 that are also in
        // commons-lang3.
        className = className + usageSuffix;
        return className;
    }

    static String fillInPlaceholders(String initExp, int numberOfTypes) {
        if(initExp == null) {
            return null;
        }
        for(int i = 0; i < numberOfTypes; i++) {
            final String typePlaceholder = "{{TYPE" + (i + 1) + "}}";
            final String typeTextPlaceholder = "{{TYPETEXT" + (i + 1) + "}}";
            final String valuePlaceholder = "{{VALUE" + (i + 1) + "}}";
            final String typeReplacement = isEven(i) ? "java.lang.String" : "java.lang.Integer";
            final String valueReplacement = isEven(i) ? "\"value\"" : "0";
            initExp = initExp.replace(typePlaceholder, typeReplacement).replace(typeTextPlaceholder, typeReplacement).replace(valuePlaceholder, valueReplacement);
        }
        return initExp;
    }

    static String buildTypeParamsStr(final int numberOfTypeParams) {
        if(numberOfTypeParams <= 0) {
            return "";
        }
        final StringBuilder builder = new StringBuilder("<");
        for(int i = 0; i < numberOfTypeParams; i++) {
            if(isEven(i)) {
                builder.append("String");
            } else {
                builder.append("Integer");
            }
            if(i < numberOfTypeParams - 1) {
                builder.append(", ");
            }
        }
        builder.append(">");
        return builder.toString();
    }

    private static boolean isEven(final int i) {
        return (i & 1) == 0;
    }
}
