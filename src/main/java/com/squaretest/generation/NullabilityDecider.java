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
package com.squaretest.generation;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypes;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;
import com.squaretest.generation.defaulttypes.DefaultDiInfoHolder;
import com.squaretest.generation.defaulttypes.TypeCreatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.squaretest.annotations.AnnotationCreator.getAnnotationName;

public class NullabilityDecider {

    /**
     * Replace link expressions, because they have periods (.) in them. The {@link #ReturnLineFindAnyExpressions} use periods
     * as part of their detection logic.
     */
    private static final Pattern[] ExpressionsToReplaceWithIgnoredValue = new Pattern[]{
            Pattern.compile("\\{@(link|linkplain|inheritDoc).*?\\}", Pattern.DOTALL),
            Pattern.compile("<a\\s.*?<\\/a>", Pattern.DOTALL),
            // Parenthetical "e.g." and "i.e."; Example: "(e.g. the default foo)"
            Pattern.compile("(;[\\s\\*\\/]+|\\.[\\s\\*\\/]+){0,1}\\(([Ii]\\.e\\.|[eE]\\.g\\.).*?\\)", Pattern.DOTALL),
            // Other "e.g." and "i.e." expressions; Example: "; e.g. the default foo)" or "e.g. the default foo".
            Pattern.compile("(;[\\s\\*\\/]+|\\.[\\s\\*\\/]+){0,1}([Ii]\\.e\\.|[eE]\\.g\\.)", Pattern.DOTALL),
            // Terms that start with Null; e.g. NullPointerException, nullValue(), ..etc.
            Pattern.compile("[nN]ull[a-zA-Z0-9]+", Pattern.DOTALL)
    };

    /**
     * Replace expressions like "{@code null}" with "null".
     */
    private static final Pattern[] ExpressionsToReplaceWithNull = new Pattern[]{
            Pattern.compile("\\{\\@code\\s+[nN]ull\\s*\\.*\\s*\\}", Pattern.DOTALL),
    };

    /**
     * Search the Javadoc text to see if any part matches one of the following expressions.
     */
    private static final Pattern[] ReturnLineFindAnyExpressions = new Pattern[]{
            // @return null
            // @return Null.
            // @return <code>null.</code>
            // @return <code> null</code>.
            Pattern.compile("(This[^.]*return[s]?|This[^.]*get[s]?|This[^.]*obtain[s]?|This[^.]*retrieve[s]?|This[^.]*download[s]?|@return|Return[s]?|Get[s]?|Obtain[s]?|Retrieve[s]?|Download[s]?)[\\s\\*\\/]*(<code>)?[\\s\\*\\/]*[nN]ull[\\s\\*\\/]*(<\\/code>)?", Pattern.DOTALL),

            // @return the foo device or null if there isn't one.
            // @return the <code>foo</code> device or <code>null</code> if there isn't one.
            // @return the foo device or <code>null</code>.
            // @return some very long explanation that requires multiple lines to describe
            //      * or <code> null </code>.
            // @return some very long explanation that requires multiple lines to describe
            //      * or <code> null </code> if there isn't one.
            // @return some very long explanation that requires multiple lines to describe
            //      * and <code> null </code> if there isn't one.
            // @return some very long explanation that requires multiple lines to describe
            //      * or <code>null</code> if this is not the case.
            // @return some very long explanation that requires multiple lines to describe
            //      * or null.
            Pattern.compile("(This[^.]*return[s]?|This[^.]*get[s]?|This[^.]*obtain[s]?|This[^.]*retrieve[s]?|This[^.]*download[s]?|@return|Return[s]?|Get[s]?|Obtain[s]?|Retrieve[s]?|Download[s]?)[^.]*(or|and)[\\s\\*\\/]*(<code>)?[\\s\\*\\/]*[nN]ull[\\s\\*\\/]*(<\\/code>)?[\\s\\*\\/]*[^.]*", Pattern.DOTALL),

            // @return   the foo device if available; Otherwise, this returns null.
            // @return   the foo device if available;
            //      *    Otherwise, this returns null.
            // @return the foo device if one is available. Otherwise, this returns null.
            // @return the foo device if one is available, otherwise, this returns null.
            // @return the foo device if one is available; otherwise this returns null.
            // @return the foo device if one is available; else, this returns null.
            // @return the foo device if one is available. If not, this returns null.
            // @return the foo device if one is available. If not this returns null.
            // @return the foo device if one is available. If not this returns <code>null</code>.
            // @return the foo device if one is available. If not this returns <code>null.</code>
            // @return the foo device if one is available. If not this returns <code>Null.</code>
            // @return the foo device if one is available.
            //      *     If not this returns Null.
            // @return the foo device if one is available; otherwise, return null.
            // @return the foo device if one is available; else, return null.
            // @return the foo device if one is available; otherwise return null.
            // @return the foo device if one is available; else return null.
            Pattern.compile("(This[^.]*return[s]?|This[^.]*get[s]?|This[^.]*obtain[s]?|This[^.]*retrieve[s]?|This[^.]*download[s]?|@return|Return[s]?|Get[s]?|Obtain[s]?|Retrieve[s]?|Download[s]?)[^.]*[\\.]?[\\s\\*\\/]*([oO]therwise|[eE]lse|[iI]f not|[iI]f that is not the case|[,;.])[^.]*([Tt]his returns|return[s]?)[\\s\\*\\/]*(<code>)?[nN]ull[\\s\\*\\/]*(<\\/code>)?[\\s\\*\\/]*[^.]*", Pattern.DOTALL),

            // @return the foo device if one is available; otherwise null.
            Pattern.compile("(This[^.]*return[s]?|This[^.]*get[s]?|This[^.]*obtain[s]?|This[^.]*retrieve[s]?|This[^.]*download[s]?|@return|Return[s]?|Get[s]?|Obtain[s]?|Retrieve[s]?|Download[s]?)[^.]*[\\.]?[\\s\\*\\/]*([oO]therwise|[eE]lse|[iI]f not|[iI]f that is not the case)[\\s\\*\\/]*(<code>)?[nN]ull[\\s\\*\\/]*(<\\/code>)?[\\s\\*\\/]*[^.]*", Pattern.DOTALL),

            // @return the foo device if one is available and null otherwise.
            // @return the foo device if one is available and null if not.
            // @return the foo device if one is available or null otherwise.
            // @return the foo device if one is available or null if not.
            // @return an object with the given hash key, or null if no such object exists.
            // @return returns an object with the given hash key, or null if no such object exists.
            // Returns the foo device if one is available and null otherwise.
            // This method returns the foo device if one is available and null if not.
            // This function returns the foo device if one is available and null if not.
            // This method returns the foo device if one is available and null if it is not.
            // This thing returns the foo device if one is available and null if that is not the case.
            // This returns the foo device if one is available or null otherwise.
            // Return the foo device if one is available or null if not.
            // @return returns an object with the given hash key, or null if no such object exists.
            Pattern.compile("(This[^.]*return[s]?|This[^.]*get[s]?|This[^.]*obtain[s]?|This[^.]*retrieve[s]?|This[^.]*download[s]?|@return|Return[s]?|Get[s]?|Obtain[s]?|Retrieve[s]?|Download[s]?)[^.]*[\\.]?[\\s\\*\\/]*(and|or)[^.]*(<code>)?[nN]ull[\\s\\*\\/]*(<\\/code>)?[\\s\\*\\/]*([Oo]therwise|[Ii]f)[^.]*", Pattern.DOTALL)
    };

    /**
     * Similar to {@link #ReturnLineFindAnyExpressions} but the expressions start with lowercase characters. In this case, the whole javadoc must match the expression.
     * This covers cases where the explanation is simple but not capitalized correctly; e.g. "return the foo device or null if there isn't one".
     */
    private static final Pattern[] ReturnCommentFullMatchExpressions = new Pattern[]{
            // return null
            // returns Null.
            // return <code>null.</code>
            // return <code> null</code>.
            Pattern.compile("[\\s\\*\\/]*(this[^.]*return[s]?|this[^.]*get[s]?|this[^.]*obtain[s]?|this[^.]*retrieve[s]?|this[^.]*download[s]?|return[s]?|get[s]?|obtain[s]?|retrieve[s]?|download[s]?)[\\s\\*\\/]*(<code>)?[\\s\\*\\/]*[nN]ull[\\s\\*\\/]*(<\\/code>)?.*", Pattern.DOTALL),

            // return the foo device or null if there isn't one.
            // return the <code>foo</code> device or <code>null</code> if there isn't one.
            // return the foo device or <code>null</code>.
            // return some very long explanation that requires multiple lines to describe
            //      * or <code> null </code>.
            // return some very long explanation that requires multiple lines to describe
            //      * or <code> null </code> if there isn't one.
            // return some very long explanation that requires multiple lines to describe
            //      * and <code> null </code> if there isn't one.
            // return some very long explanation that requires multiple lines to describe
            //      * or <code>null</code> if this is not the case.
            // return some very long explanation that requires multiple lines to describe
            //      * or null.
            Pattern.compile("[\\s\\*\\/]*(this[^.]*return[s]?|this[^.]*get[s]?|this[^.]*obtain[s]?|this[^.]*retrieve[s]?|this[^.]*download[s]?|return[s]?|get[s]?|obtain[s]?|retrieve[s]?|download[s]?)[^.]*(or|and)[\\s\\*\\/]*(<code>)?[\\s\\*\\/]*[nN]ull[\\s\\*\\/]*(<\\/code>)?.*", Pattern.DOTALL),

            // return   the foo device if available; Otherwise, this returns null.
            // return   the foo device if available;
            //      *    Otherwise, this returns null.
            // return the foo device if one is available. Otherwise, this returns null.
            // return the foo device if one is available, otherwise, this returns null.
            // return the foo device if one is available; otherwise this returns null.
            // return the foo device if one is available; else, this returns null.
            // return the foo device if one is available. If not, this returns null.
            // return the foo device if one is available. If not this returns null.
            // return the foo device if one is available. If not this returns <code>null</code>.
            // return the foo device if one is available. If not this returns <code>null.</code>
            // return the foo device if one is available. If not this returns <code>Null.</code>
            // return the foo device if one is available.
            //      *     If not this returns Null.
            // return the foo device if one is available; otherwise, return null.
            // return the foo device if one is available; else, return null.
            // return the foo device if one is available; otherwise return null.
            // return the foo device if one is available; else return null.
            Pattern.compile("[\\s\\*\\/]*(this[^.]*return[s]?|this[^.]*get[s]?|this[^.]*obtain[s]?|this[^.]*retrieve[s]?|this[^.]*download[s]?|return[s]?|get[s]?|obtain[s]?|retrieve[s]?|download[s]?)[^.]*[\\.]?[\\s\\*\\/]*([oO]therwise|[eE]lse|[iI]f not|[iI]f that is not the case|[,;.])[^.]*([Tt]his returns|return[s]?)[\\s\\*\\/]*(<code>)?[nN]ull[\\s\\*\\/]*(<\\/code>)?.*", Pattern.DOTALL),

            // return the foo device if one is available; otherwise null.
            Pattern.compile("[\\s\\*\\/]*(this[^.]*return[s]?|this[^.]*get[s]?|this[^.]*obtain[s]?|this[^.]*retrieve[s]?|this[^.]*download[s]?|return[s]?|get[s]?|obtain[s]?|retrieve[s]?|download[s]?)[^.]*[\\.]?[\\s\\*\\/]*([oO]therwise|[eE]lse|[iI]f not|[iI]f that is not the case)[\\s\\*\\/]*(<code>)?[nN]ull[\\s\\*\\/]*(<\\/code>)?.*", Pattern.DOTALL),

            // return the foo device if one is available and null otherwise.
            // return the foo device if one is available and null if not.
            // return the foo device if one is available or null otherwise.
            // return the foo device if one is available or null if not.
            // return an object with the given hash key, or null if no such object exists.
            // return returns an object with the given hash key, or null if no such object exists.
            // returns the foo device if one is available and null otherwise.
            // this method returns the foo device if one is available and null if not.
            // this function returns the foo device if one is available and null if not.
            // this method returns the foo device if one is available and null if it is not.
            // this thing returns the foo device if one is available and null if that is not the case.
            // this returns the foo device if one is available or null otherwise.
            // return the foo device if one is available or null if not.
            // return returns an object with the given hash key, or null if no such object exists.
            Pattern.compile("[\\s\\*\\/]*(this[^.]*return[s]?|this[^.]*get[s]?|this[^.]*obtain[s]?|this[^.]*retrieve[s]?|this[^.]*download[s]?|return[s]?|get[s]?|obtain[s]?|retrieve[s]?|download[s]?)[^.]*[\\.]?[\\s\\*\\/]*(and|or)[^.]*(<code>)?[nN]ull[\\s\\*\\/]*(<\\/code>)?[\\s\\*\\/]*([Oo]therwise|[Ii]f).*", Pattern.DOTALL)
    };

    private final DefaultDiInfoHolder defaultDiInfoHolder;
    private final DataflowNullabilityDecider dataflowNullabilityDecider;

    private final Map<PsiMethod, NullabilityStatus> cachedNullabilityStatusMap;

    public NullabilityDecider(
            final DefaultDiInfoHolder defaultDiInfoHolder,
            final DataflowNullabilityDecider dataflowNullabilityDecider) {
        this.defaultDiInfoHolder = defaultDiInfoHolder;
        this.dataflowNullabilityDecider = dataflowNullabilityDecider;
        this.cachedNullabilityStatusMap = new IdentityHashMap<>();
    }

    public NullabilityStatus determineIfNullable(
            final PsiMethod method, final PsiMethod[] superPsiMethods,
            @Nullable final PsiMethodCallExpression psiMethodCallExpression) {
        // Check the config to see if Squaretest recognizes this type.
        final String canonicalKey = TypeCreatorUtil.getCanonicalKey(method);
        NullabilityStatus status = defaultDiInfoHolder.getNullabilityStatus(canonicalKey);
        if(status != null) {
            return status;
        }

        final NullabilityStatus methodStatus = cachedNullabilityStatusMap.computeIfAbsent(
                method, x -> computeNullabilityStatusForMethod(x, superPsiMethods));
        if(methodStatus != NullabilityStatus.UNKNOWN) {
            return methodStatus;
        }

        // Try to get the nullabiltiy status based on the call expression.
        if(psiMethodCallExpression == null) {
            return NullabilityStatus.UNKNOWN;
        }
        return dataflowNullabilityDecider.computeNullabilityStatus(psiMethodCallExpression);
    }

    private NullabilityStatus computeNullabilityStatusForMethod(final PsiMethod method, final PsiMethod[] superPsiMethods) {
        final PsiType returnType = method.getReturnType();
        if(returnType == null || PsiTypes.voidType().equals(returnType) || returnType instanceof PsiPrimitiveType) {
            return NullabilityStatus.NOT_NULL;
        }

        // Determine nullability from annotations.
        NullabilityStatus status = determineIfNullableFromAnnotation(method);
        if(status != NullabilityStatus.UNKNOWN) {
            return status;
        }
        for(final PsiMethod currentMethod : superPsiMethods) {
            status = determineIfNullableFromAnnotation(currentMethod);
            if(status != NullabilityStatus.UNKNOWN) {
                return status;
            }
        }

        // Determine nullability from javadocs.
        status = determineIfNullFromJavadoc(method);
        if(status != NullabilityStatus.UNKNOWN) {
            return status;
        }
        for(final PsiMethod superMethod : superPsiMethods) {
            status = determineIfNullFromJavadoc(superMethod);
            if(status != NullabilityStatus.UNKNOWN) {
                return status;
            }
        }

        return status;
    }

    private static NullabilityStatus determineIfNullFromJavadoc(PsiMethod method) {

        // Get the method sources if it is compiled.
        method = CompiledUtils.getMethodWithSourceCode(method);
        final PsiDocComment javaDoc = method.getDocComment();
        if(javaDoc == null) {
            return NullabilityStatus.UNKNOWN;
        }
        NullabilityStatus status = NullabilityStatus.UNKNOWN;
        final Optional<PsiDocTag> returnTag = Arrays.stream(javaDoc.getTags()).filter(x -> x.getName().equals("return")).findFirst();
        if(returnTag.isPresent()) {
            status = determineStatusFromReturnTag(returnTag.get().getText());
        }

        if(status != NullabilityStatus.UNKNOWN) {
            return status;
        }

        return determineNullabilityFromJavaDocText(javaDoc.getText());
    }

    static NullabilityStatus determineStatusFromReturnTag(final String returnTagText) {
        return determineIfReturnStatementPatternExists(returnTagText);
    }

    static NullabilityStatus determineNullabilityFromJavaDocText(final String text) {
        return determineIfReturnStatementPatternExists(text);
    }

    @NotNull
    private static NullabilityStatus determineIfReturnStatementPatternExists(final String text) {
        if(StringUtils.isEmpty(text)) {
            return NullabilityStatus.UNKNOWN;
        }

        String textWithoutLinks = text;
        for(final Pattern pattern : ExpressionsToReplaceWithIgnoredValue) {
            textWithoutLinks = pattern.matcher(textWithoutLinks).replaceAll("Type");
        }
        for(final Pattern pattern : ExpressionsToReplaceWithNull) {
            textWithoutLinks = pattern.matcher(textWithoutLinks).replaceAll("null");
        }

        // Check the find/search matcher expressions.
        for(final Pattern pattern : ReturnLineFindAnyExpressions) {
            if(pattern.matcher(textWithoutLinks).find()) {
                return NullabilityStatus.NULLABLE;
            }
        }

        // Check the full expression matchers.
        for(final Pattern pattern : ReturnCommentFullMatchExpressions) {
            if(pattern.matcher(textWithoutLinks).matches()) {
                return NullabilityStatus.NULLABLE;
            }
        }

        return NullabilityStatus.UNKNOWN;
    }

    private static NullabilityStatus determineIfNullableFromAnnotation(final PsiMethod method) {
        final PsiAnnotation[] annotations = method.getAnnotations();
        for(final PsiAnnotation annotation : annotations) {
            // Determine the name.
            final String name = getAnnotationName(annotation);
            if(name == null) {
                continue;
            }
            if(StringUtils.equalsAnyIgnoreCase(name, "Nullable", "CheckForNull")) {
                return NullabilityStatus.NULLABLE;
            }
            if(StringUtils.equalsAnyIgnoreCase(name, "Nonnull", "NotNull")) {
                return NullabilityStatus.NOT_NULL;
            }
        }
        return NullabilityStatus.UNKNOWN;
    }
}
