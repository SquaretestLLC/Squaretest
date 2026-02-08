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
package com.squaretest.template.api;

import com.squaretest.template.impl.AltInfoImpl;
import com.squaretest.template.impl.FluentListImpl;
import com.squaretest.template.impl.NameUtils;
import com.squaretest.template.impl.TestInfoImpl;
import org.apache.commons.lang3.CharSequenceUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * All public-facing interfaces need to be in one file.
 */
@SuppressWarnings("unused")
public interface Api {

    interface Annotation extends PropsHolder {
        @NotNull
        String getName();

        String getCanonicalName();

        /**
         * @return the canonical name of the annotation if it is non-null. Otherwise, this returns the name of the annotation.
         */
        @NotNull
        default String getCanonicalNameOrName() {
            final String canonicalName = getCanonicalName();
            if(canonicalName != null) {
                return canonicalName;
            }
            return getName();
        }

        Api.FluentList<AnnotationParameter> getParameters();
    }

    interface AnnotationParameter extends PropsHolder {
        String getKey();
        AnnotationValue getValue();
    }

    interface AnnotationValue extends PropsHolder {
        @NotNull
        String getText();

        @NotNull
        String getFirstStringValue();
    }

    interface AnnotationHolder {

        FluentList<Annotation> getAnnotations();

        /**
         * Returns whether or not this object is annotated with one of the provided annotations names.
         * @param annotations the names of the annotations. Callers may provide either the simple names or the canonical names.
         * @return whether or not this object is annotated with at least one of the provided annotations names.
         */
        default boolean hasAnnotation(String... annotations) {
            final List<String> canonicalNames = getAnnotations().stream().map(Annotation::getCanonicalNameOrName).collect(Collectors.toList());
            return NameUtils.containsAnnotation(canonicalNames, annotations);
        }

        /**
         * Returns whether or not this object is annotated with one of the provided annotations names.
         * @param annotations the names of the annotations. Callers may provide either the simple names or the canonical names.
         * @return whether or not this object is annotated with at least one of the provided annotations names.
         */
        default boolean hasAnnotation(List<String> annotations) {
            return hasAnnotation(annotations.toArray(new String[]{}));
        }

        /**
         * Returns whether or not this object has an annotation whose name starts with one of the provided prefixes.
         * @param prefixes the prefixes to search for. Callers may provide prefixes for simple names or canonical names.
         * @return whether or not this object is annotated with at least one of the provided annotations names.
         */
        default boolean hasAnnotationWithPrefix(String... prefixes) {
            final List<String> canonicalNames = getAnnotations().stream().map(Annotation::getCanonicalNameOrName).collect(Collectors.toList());
            return NameUtils.containsAnnotationWithPrefix(canonicalNames, prefixes);
        }

        /**
         * Returns whether or not this object has an annotation whose name starts with one of the provided prefixes.
         * @param prefixes the prefixes to search for. Callers may provide prefixes for simple names or canonical names.
         * @return whether or not this object is annotated with at least one of the provided annotations names.
         */
        default boolean hasAnnotationWithPrefix(List<String> prefixes) {
            return hasAnnotationWithPrefix(prefixes.toArray(new String[]{}));
        }

        /**
         * @deprecated Please use {@link #hasAnnotation(String...)}
         * Returns whether or not this object is annotated with one of the provided annotations names.
         * @param simpleOrCannonicalNamesToSearchFor the names of the annotations. Callers may provide either the simple names or the canonical names.
         * @return whether or not this object is annotated with at least one of the provided annotations names.
         */
        @Deprecated
        default boolean isAnnotatedWith(final String... simpleOrCannonicalNamesToSearchFor) {
            return hasAnnotation(simpleOrCannonicalNamesToSearchFor);
        }

        /**
         * @deprecated Please use {@link #hasAnnotation(List)}
         * Returns whether or not this object is annotated with one of the provided annotations names.
         * @param simpleOrCannonicalNamesToSearchFor the names of the annotations. Callers may provide either the simple names or the canonical names.
         * @return whether or not this object is annotated with at least one of the provided annotations names.
         */
        @Deprecated
        default boolean isAnnotatedWith(List<String> simpleOrCannonicalNamesToSearchFor) {
            return hasAnnotation(simpleOrCannonicalNamesToSearchFor.toArray(new String[]{}));
        }

    }

    interface PropsHolder {
        Map<Object, Object> getProps();
    }

    interface SuperTypesHolder {

        String getCanonicalName();

        /**
         * @return a list containing the canonical names of all super types of this object.
         */
        Api.FluentList<String> getAllSuperTypeNames();

        /**
         * @return a list containing this object's canonical name as well as the canonical names of all super types of this object.
         */
        default FluentList<String> getAllNames() {
            String canonicalName = getCanonicalName();
            if(canonicalName == null) {
                return getAllSuperTypeNames();
            } else {
                final FluentList<String> ret = new FluentListImpl<>();
                ret.add(canonicalName);
                ret.addAll(getAllSuperTypeNames());
                return ret;
            }
        }

        /**
         * Returns whether or not this object is an instance of the provided canonicalName.
         * This method checks this class's canonicalName as well as this class's super types' canonical names.
         * @param canonicalName the name to check
         * @return boolean.
         */
        default boolean is(final String canonicalName) {
            if(canonicalName == null) {
                return false;
            }
            return Objects.equals(getCanonicalName(), canonicalName) || this.getAllSuperTypeNames().contains(canonicalName);
        }

        /**
         * Returns whether or not this object is an instance of all of the provided canonicalName.
         * This method checks this class's canonicalName as well as this class's super types' canonical names.
         * @param canonicalNames the names to check
         * @return boolean.
         */
        default boolean isAll(final String... canonicalNames) {
            return isAll(Arrays.asList(canonicalNames));
        }

        /**
         * Returns whether or not this object is an instance of all of the provided canonicalName.
         * This method checks this class's canonicalName as well as this class's super types' canonical names.
         * @param canonicalNames the names to check
         * @return boolean.
         */
        default boolean isAll(final List<String> canonicalNames) {
            if(canonicalNames == null) {
                return true;
            }
            return getAllSuperTypeNames().concat(Collections.singletonList(getCanonicalName())).containsAll(canonicalNames);
        }

        /**
         * Returns whether or not this object is an instance of any of the provided canonicalName.
         * This method checks this class's canonicalName as well as this class's super types' canonical names.
         * @param canonicalNames the names to check
         * @return boolean.
         */
        default boolean isAny(final String... canonicalNames) {
            return isAny(Arrays.asList(canonicalNames));
        }

        /**
         * Returns whether or not this object is an instance of any of the provided canonicalName.
         * This method checks this class's canonicalName as well as this class's super types' canonical names.
         * @param canonicalNames the names to check
         * @return boolean.
         */
        default boolean isAny(final List<String> canonicalNames) {
            if(canonicalNames == null) {
                return false;
            }
            final FluentList<String> allSuperTypeNames = this.getAllSuperTypeNames();
            for(final String name : canonicalNames) {
                if(allSuperTypeNames.contains(name) || Objects.equals(getCanonicalName(), name)) {
                    return true;
                }
            }
            return false;
        }
    }

    interface BeanContextFactory {
        static BeanContext create(final Api.Type topLevelType, final int minSettersToCall, final int maxSettersToCall) {
            if(topLevelType == null || topLevelType.getCanonicalName() == null) {
                return null;
            }
            return new BeanContext(topLevelType, minSettersToCall, maxSettersToCall);
        }

        static BeanContext create(final Api.Variable topLevelVariable, final int minSettersToCall, final int maxSettersToCall) {
            if(topLevelVariable == null || topLevelVariable.getType().getCanonicalName() == null) {
                return null;
            }
            return new BeanContext(topLevelVariable.getType(), minSettersToCall, maxSettersToCall);
        }
    }

    /**
     * Describes the class for which the test is being generated (the source class).
     */
    interface SourceClass extends AnnotationHolder, SuperTypesHolder, PropsHolder {

        /**
         * @return the name of the source class.
         */
        @NotNull
        String getName();

        /**
         * @return the canonical name if it is non-null. Otherwise, this returns the name.
         */
        @NotNull
        default String getCanonicalNameOrName() {
            final String canonicalName = getCanonicalName();
            if(canonicalName != null) {
                return canonicalName;
            }
            return getName();
        }

        /**
         * @deprecated use {@link #getAllSuperTypeNames()}
         * @return all supertypes of the source class.
         */
        @NotNull
        @Deprecated
        FluentList<String> getAllSuperTypes();

        /**
         * @return whether or not the class is sealed.
         */
        boolean isSealed();

        /**
         * @return whether or not the class is sealed and abstract.
         */
        boolean isSealedAbstract();

        boolean isDtoBean();

        boolean isDtoBeanWithInputIoProperty();

        /**
         * @return the package name from the package declaration in the file containing the source class.
         */
        @NotNull
        String getPackageName();

        /**
         * Contains the longest constructor with at least package-local access.
         */
        @Nullable
        Constructor getPreferredConstructor();

        /**
         * Sets the preferredConstructor to the given Constructor.
         * @param preferredConstructor the new preferredConstructor.
         */
        void setPreferredConstructor(@Nullable Constructor preferredConstructor);

        /**
         * Returns all constructors, including the default constructor provided by the Java language spec if no other
         * constructors are present.
         * @return all constructors.
         */
        @NotNull
        FluentList<Constructor> getConstructors();

        /**
         * Returns all public constructors, including the default constructor provided by the Java language spec if
         * no other constructors are present.
         * @return all public constructors
         */
        @NotNull
        FluentList<Constructor> getPublicConstructors();

        /**
         * @return all protected constructors.
         */
        @NotNull
        FluentList<Constructor> getProtectedConstructors();

        /**
         * @return all constructors with package-local access.
         */
        @NotNull
        FluentList<Constructor> getPackageLocalConstructors();

        /**
         * @return all private constructors.
         */
        @NotNull
        FluentList<Constructor> getPrivateConstructors();

        // Methods.

        /**
         * Returns all public instance methods declared in the class. This does not include constructors.
         * @return public instance methods declared in the class.
         */
        @NotNull
        FluentList<Method> getPublicInstanceMethods();

        /**
         * Returns all protected instance methods declared in the class. This does not include constructors.
         * @return protected instance methods declared in the class.
         */
        @NotNull
        FluentList<Method> getProtectedInstanceMethods();

        /**
         * Returns all package-local instance methods declared in the class. This does not include constructors.
         * @return package-local instance methods declared in the class.
         */
        @NotNull
        FluentList<Method> getPackageLocalInstanceMethods();

        /**
         * Returns all private instance methods declared in the class. This does not include constructors.
         * @return private instance methods declared in the class.
         */
        @NotNull
        FluentList<Method> getPrivateInstanceMethods();

        @NotNull
        FluentList<Method> getSimpleSetters();

        @NotNull
        FluentList<Method> getSimpleGetters();

        @NotNull
        FluentList<Method> getPreferredInitSetters();

        @NotNull
        FluentList<Method> getPreferredInitMethods();

        /**
         * Returns all instance methods declared in the class. This does not include constructors.
         * @return instance methods declared in the class.
         */
        @NotNull
        FluentList<Method> getInstanceMethods();

        /**
         * @return all public static methods declared in the class.
         */
        @NotNull
        FluentList<Method> getPublicStaticMethods();

        /**
         * @return all protected static methods declared in the class}.
         */
        @NotNull
        FluentList<Method> getProtectedStaticMethods();

        /**
         * @return all package-local static methods declared in the class.
         */
        @NotNull
        FluentList<Method> getPackageLocalStaticMethods();

        /**
         * @return all private static methods declared in the class.
         */
        @NotNull
        FluentList<Method> getPrivateStaticMethods();

        /**
         * @return all static methods declared in the class.
         */
        @NotNull
        FluentList<Method> getStaticMethods();

        /**
         * Returns all package-visible (package-local or protected) static creator methods. A static creator method
         * is a static method that returns an instance of the source class.
         * @return the static creator methods.
         */
        @NotNull
        FluentList<Method> getPackageVisibleStaticCreatorMethods();

        /**
         * Returns all methods declared in the class. This does not include constructors.
         * @return instance methods declared in the class.
         */
        @NotNull
        FluentList<Method> getMethods();

        /**
         * @return the public instance fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getPublicInstanceFields();

        /**
         * @return the protected instance fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getProtectedInstanceFields();

        /**
         * @return the package-local instance fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getPackageLocalInstanceFields();

        /**
         * @return the private instance fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getPrivateInstanceFields();

        FluentList<Method> getAllMethods();

        FluentList<Method> getAllNonObjectMethods();

        FluentList<Method> getAllLowestOverrideMethods();

        FluentList<Method> getAllNonObjectLowestOverrideMethods();

        /**
         * @return the instance fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getInstanceFields();

        /**
         * @return the public static fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getPublicStaticFields();

        /**
         * @return the protected static fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getProtectedStaticFields();

        /**
         * @return the package-local static fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getPackageLocalStaticFields();

        /**
         * @return the private static fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getPrivateStaticFields();

        /**
         * @return the static fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getStaticFields();

        /**
         * @return the public static fields declared in the class.
         */
        @NotNull
        FluentList<ClassMember> getFields();

        @NotNull
        FluentList<ClassMember> getAllFields();

        /**
         * Returns the fields annotated with dependency-annotations; i.e. @Inject or @Autowired.
         * @deprecated Please call {@link #fieldsAnnotatedWith(String...)} with the list of annotation names instead.
         * @return the fields annotated with dependency-annotations; i.e. @Inject or @Autowired.
         */
        @NotNull
        @Deprecated
        FluentList<ClassMember> getDependencyAnnotatedFields();

        /**
         * Returns all {@link ClassMember ClassMembers} annotated with at least one of the provided annotations.
         * @param annotations the simple names or canonical names of the annotations to search for.
         *                    Any value containing a dot (.) will be treated as a canonical name when searching for
         *                    {@link ClassMember ClassMembers} with matching annotations. Any value without a
         *                    dot (.) will be treated as a simple name.
         * @return the fields annotated with at least one of the provided annotations.
         */
        @NotNull
        FluentList<ClassMember> fieldsAnnotatedWith(final String... annotations);

        /**
         * Returns all {@link Method Methods} annotated with at least one of the provided annotations.
         * @param annotations the simple names or canonical names of the annotations to search for.
         *                    Any value containing a dot (.) will be treated as a canonical name when searching for
         *                    {@link Method Methods} with matching annotations. Any value without a
         *                    dot (.) will be treated as a simple name.
         * @return the fields annotated with at least one of the provided annotations.
         */
        @NotNull
        FluentList<Api.Method> methodsAnnotatedWith(final String... annotations);

        /**
         * Returns all {@link Constructor Constructors} annotated with at least one of the provided annotations.
         * @param annotations the simple names or canonical names of the annotations to search for.
         *                    Any value containing a dot (.) will be treated as a canonical name when searching for
         *                    {@link Constructor Constructors} with matching annotations. Any value without a
         *                    dot (.) will be treated as a simple name.
         * @return the fields annotated with at least one of the provided annotations.
         */
        @NotNull
        FluentList<Api.Method> constructorsAnnotatedWith(final String... annotations);

        /**
         * Returns the name that should be used to store an instance of class in a member field in the
         * test class. The default value is determined by the class name and the user's code style settings.
         * @return the test class member field name.
         */
        String getTestClassMemberName();

        /**
         * Sets the testClassMemberName property to the given value.
         * @param testClassMemberName the new testClassMemberName.
         */
        void setTestClassMemberName(String testClassMemberName);

        // Template class field naming info.

        /**
         * Returns the name that should be used to store an instance of class in a local field in the
         * test class. The default value is determined by the class name and the IDE code style settings.
         * @return the test class local field name.
         */
        String getTestClassLocalFieldName();

        /**
         * Sets the testClassLocalFieldName property to the given value.
         * @param testClassLocalFieldName the new testClassLocalFieldName.
         */
        void setTestClassLocalFieldName(String testClassLocalFieldName);

        /**
         * @return true if the class is an enum.
         */
        boolean isEnum();

        /**
         * @return the first enum value if this class is an enum and has at least one value.
         */
        @Nullable
        String getEnumFirstValue();

        /**
         * @return the values in the enum if the class is an enum, or {@link java.util.Collections#emptyList()} if not.
         */
        @NotNull
        FluentList<String> getEnumValues();

        /**
         * Returns whether or not this class is a singleton. The class is considered to be a singleton if the one of
         * the following are true.
         * <ol>
         *     <li>
         *         The class has a package-visible static method called instance() or getInstance(), ignoring case
         *         that returns an instance of the class.
         *     </li>
         *     <li>
         *         The class is an enum with a single value named INSTANCE, ignoring case.
         *     </li>
         * </ol>
         *
         * @return whether or not the class is a singleton.
         */
        boolean isSingleton();

        /**
         * The expression needed to access the instance of the singleton if SourceClass is a singleton;
         * e.g. getInstance(); this returns null if class is not a singleton.
         * @return the singleton access expression.
         */
        String getSingletonAccessExpression();

        /**
         * @return whether or not the class is abstract.
         */
        boolean isAbstract();

        /**
         * @return all super classes and interfaces of this class.
         */
        FluentList<SourceClass> getAllSuperClasses();

        /**
         * Returns the body of an anonymous instance of the abstract class that contains stubs
         * for all of the abstract methods in the class.
         * @return the abstract class body.
         */
        @Nullable
        String getAbstractClassBody();

        /**
         * @return The {@link Type} of the class.
         */
        @NotNull
        Type getType();

        /**
         * @return whether or not the class has generic type parameters.
         */
        boolean getHasGenerics();
    }


    /**
     * Inherits from {@link Method}.
     * Constructor implements {@link Comparable} and are ordered by number of parameters.
     */
    interface Constructor extends Method, Comparable<Constructor> {
    }

    /**
     * Describes a method in the class.
     */
    interface Method extends AnnotationHolder, PropsHolder {
        /**
         * @return the method's parameters.
         */
        @NotNull
        FluentList<Variable> getParameters();

        /**
         * Returns all super method parameters at the specified index. For example, calling getSuperMethodParametersAtIndex(0)
         * will return a list containing the first parameter of each super method.
         * @param index the index of the parameter to return.
         * @return the super method parameters at the specified index.
         */
        FluentList<Variable> getSuperMethodParametersAtIndex(int index);

        /**
         * Returns the target field this method is a getter or setter for, or null if this is not a getter or setter
         * or if the target field could not be determined.
         * @return the target field of this getter or setter method or null.
         */
        @Nullable
        Api.ClassMember getTargetField();

        @NotNull
        Api.SimpleExitInfo getSimpleExitInfo();

        @NotNull
        String getMethodKey();

        /**
         * @return all super methods this method overrides.
         */
        FluentList<Method> getSuperMethods();

        /**
         * @return the name of the method.
         */
        @NotNull
        String getName();

        /**
         * Returns true if Squaretest has inferred this method can return null.
         * @return boolean
         */
        boolean isInferredNullable();

        /**
         * Returns true if Squaretest has inferred this method can return an "absent" value; e.g. {@link Optional#empty() Optional.empty()}.
         * @return boolean
         */
        boolean getReturnTypeCanBeAbsent();

        /**
         * @return whether or not the method is public.
         */
        boolean isPublic();

        /**
         * @return whether or not the method is protected.
         */
        boolean isProtected();

        /**
         * @return whether or not the method is package-local.
         */
        boolean isPackageLocal();

        /**
         * @return whether or not the method is private.
         */
        boolean isPrivate();

        /**
         * Returns a String representing the access level for this method.
         * Possible values are private, protected, packagePrivate, public.
         * @return the access level for this method.
         */
        String getAccessLevel();

        /**
         * @return whether or not the method is static.
         */
        boolean isStatic();

        boolean isDeprecated();

        /**
         * Returns the class that contains this method. Note that for Api.DependencyInteraction methods, this returns
         * null.
         * @return the class that contains this method or null.
         */
        @Nullable
        Api.SourceClass getContainingClass();

        /**
         * @return whether or not the method is abstract.
         */
        boolean isAbstract();

        boolean isWritable();

        /**
         * @return whether or not the method is native.
         */
        boolean isNative();

        /**
         * @deprecated use {@link #isGetterOrSetter()}.
         * @return whether this is a getter or setter method.
         */
        @Deprecated
        boolean isSimpleGetterOrSetter();

        /**
         * @return whether this is a getter or setter method.
         */
        boolean isGetterOrSetter();

        /**
         * @return whether this is a getter method.
         */
        boolean isGetter();

        /**
         * @deprecated use {@link #isGetter()}.
         * @return whether this is a getter method.
         */
        @Deprecated
        boolean isSimpleGetter();

        /**
         * @deprecated use {@link #isSetter()}.
         * @return whether this is a setter method.
         */
        @Deprecated
        boolean isSimpleSetter();

        /**
         * @return whether this is a setter method.
         */
        boolean isSetter();

        boolean isJaxbListGetter();

        /**
         * @return whether or not this method is a Constructor.
         */
        boolean isConstructor();

        /**
         * @return whether this method is in the main SourceClass (the class the test is being created for).
         */
        boolean isInMainSourceClass();

        /**
         * @return The return {@link Type Type} of the method or null if there isn't one.
         */
        @Nullable
        Type getReturnType();

        boolean isVisibleToTestClass();

        /**
         * @return the list of exceptions this method declares to be thrown.
         */
        FluentList<Exception> getDeclaredExceptions();

        @NotNull
        FluentList<Exception> getJavadocExceptions();

        @NotNull
        FluentList<Exception> getUndeclaredExceptions();

        boolean getShouldUseSimpleTest();

        boolean getAlwaysThrows();

        boolean getShouldUseLastParam();

        void setShouldUseLastParam(boolean shouldUseLastParam);

        void setShouldUseSimpleTest(boolean shouldUseSimpleTest);

        boolean getDoesNothing();

        boolean isLombokSuperBuilderConstructor();

        /**
         * @return whether or not this method declares at least one exception to be thrown.
         */
        boolean getThrowsException();

        /**
         * @return whether or not this method declares at least one checked exception to be thrown.
         */
        boolean getThrowsCheckedException();

        /**
         * A number is appended to overloaded methods to avoid method name conflicts when generating test methods for
         * each overload. The overloadSuffix will contain "" for non-overloaded methods and the first overloaded method
         * found in the class. Subsequent overloads of the same method will have overloadSuffixes: "1", "2", etc.
         * @return the overload suffix.
         */
        @NotNull
        String getOverloadSuffix();

        /**
         * @return whether the method should be tested.
         */
        boolean getShouldTest();

        /**
         * Sets whether the method should be tested.
         * @param shouldTest whether the method should be tested.
         */
        void setShouldTest(boolean shouldTest);

        /**
         * Returns the set of {@link Api.DependencyInteraction DependencyInteractions} in the current class that are reachable from this method.
         * Note: this will not find dependency interactions where the method invoked on the dependency is invoked via reflection.
         * @return the set of {@link Api.DependencyInteraction DependencyInteractions} reachable from this method in the order in which they are called.
         */
        FluentList<DependencyInteraction> getDependencyInteractions();
    }

    /**
     * Describes a declared exception thrown by a method.
     */
    interface Exception extends PropsHolder {
        /**
         * @return the type of the exception.
         */
        Type getType();

        /**
         * Returns the canonical name of the exception type or null if the canonical name could not be determined.
         * This is an alias for {@link #getType() type.canonicalName}.
         * @return the canonical name of the exception thrown or null if it could not be resolved.
         */
        String getCanonicalName();

        /**
         * @return whether or not the exception is checked (must be declared to be thrown).
         */
        boolean isChecked();
    }

    interface SimpleExitInfo extends PropsHolder {
        @Nullable
        String getReturnExpression();
        @Nullable
        Api.Exception getThrownException();
    }

    /**
     * Describes an interaction (method call) with a dependency ({@link ClassMember}).
     * The {@link #getField() field}  is the {@link ClassMember} on which {@link Method method} is invoked.
     */
    interface DependencyInteraction extends PropsHolder {

        /**
         * @return The dependency ({@link ClassMember ClassMember}) on which {@link #getMethod() method} is called.
         */
        ClassMember getField();

        /**
         * @return the {@link Method} that is invoked on the dependency ({@link ClassMember ClassMember}).
         */
        Method getMethod();

        /**
         * Returns a {@link MethodCallExpression} containing information about how the method in this {@link DependencyInteraction}
         * was invoked. The {@link MethodCallExpression} contains information about the actual types used in the method call;
         * e.g. if the method's formal parameter was T or Class&lt;T&gt;, the {@link MethodCallExpression} allows you to get the
         * actual type used in the method call.
         * @return the  {@link MethodCallExpression}.
         */
        @NotNull
        MethodCallExpression getMethodCallExpression();

        /**
         * Returns the canonical key for this dependency interaction.
         *
         * The canonical key is a String that uniquely identifies the (class, method) pair in this dependency interaction.
         * The canonical key is of the form: ClassCanonicalName_MethodName_ArgumentTypeName1_..._ArgumentTypeNameN.
         *
         * For example: the dependency key for {@link java.util.concurrent.ExecutorService#submit(Runnable)} would be:
         * "java.util.concurrent.ExecutorService_submit_Runnable".
         *
         * This is useful for detecting calls to specific APIs that you want to apply special test generation logic to.
         *
         * @return the canonical key.
         */
        String getCanonicalKey();

        boolean isReturnValueIgnored();

        String getOverloadSuffix();
    }

    /**
     * Describes how a method was actually called in the {@link SourceClass} code.
     */
    interface MethodCallExpression extends PropsHolder {
        /**
         * @return arguments provided in the method call expression.
         */
        @NotNull
        FluentList<ArgumentExpression> getArguments();

        /**
         * @return the type this method call expression evaluates to.
         */
        @Nullable
        Type getType();

    }

    /**
     * Provides information about the argument used in a method call.
     */
    interface ArgumentExpression extends PropsHolder {
        /**
         * @return The declared type of the argument (type declared in the method's formal parameter).
         */
        @NotNull
        Type getDeclaredType();

        /**
         * Returns the actual type of the argument used in the method call expression in the {@link SourceClass}.
         *
         * For methods with a declared type of T or Class&lt;T&gt;, Squaretest will attempt to determine the actual type
         * of the argument passed to the method in the source class's code.
         *
         * @return the actual type used in the method call expression.
         */
        @NotNull
        Type getActualType();
    }

    /**
     * Describes a variable -- either a {@link ClassMember} or a method parameter.
     */
    interface Variable extends AnnotationHolder, PropsHolder {

        /**
         * The declared name for the variable; For ClassMembers this property contains the name of the member;
         * for method parameters, this contains the name of the parameter.
         * @return the declared name of the variable
         */
        @NotNull
        String getDeclaredName();

        /**
         * Removes any recognized prefix from the {@link #getDeclaredName()}. Recognized prefixes are:
         * m, _, my, __, our.
         * @return the declared name with any recognized prefix removed.
         */
        @NotNull
        String getDeclaredNameWithoutPrefix();

        /**
         * @return whether or not this variable is declared as final.
         */
        boolean isFinal();

        boolean isUsed();

        /**
         * @return the variable's type.
         */
        @NotNull
        Type getType();

        /**
         * This is an alias for {@link Type#getDefaultInitExpression() Type.defaultInitExpression}.
         * @return {@link Type#getDefaultInitExpression() type.defaultInitExpression}
         */
        @NotNull
        String getDefaultInitExpression();

        void setDefaultInitExpression(String initExpression);

        /**
         * This is an alias for {@link Type#getShouldBeMocked() Type.shouldBeMocked}.
         * @return {@link Type#getShouldBeMocked() type.shouldBeMocked}
         */
        boolean getShouldBeMocked();

        /**
         * This is an alias for {@link Type#getShouldBeMocked() Type.shouldBeMocked}.
         * @param shouldBeMocked The new {@link Type#getShouldBeMocked() type.shouldBeMocked} property.
         */
        void setShouldBeMocked(boolean shouldBeMocked);

        boolean getShouldBeSpied();

        void setShouldBeSpied(boolean shouldBeSpied);

        /**
         * This is an alias for {@link Type#getInitExpression() Type.initExpression}.
         * @return {@link Type#getInitExpression() type.initExpression}
         */
        @NotNull
        String getInitExpression();

        /**
         * This is an alias for {@link Type#setInitExpression(String) Type.initExpression}.
         * @param initExpression The new {@link Type#getInitExpression() Type.initExpression} to use.
         */
        void setInitExpression(String initExpression);

        /**
         * @return whether this Variable is a {@link Api.ClassMember ClassMember}.
         */
        boolean isMember();

        /**
         * Returns whether or not this variable's value should be stored in a reference; e.g. in a test class member
         * or a local variable in one of the test class's methods. This is set to true by default, but may be changed
         * by the velocity code. Setting this to false indicates the template code should use the variable's
         * {@link #getInitExpression() initExpression} inline when it is required.
         *
         * @return whether or not this variable's value should be stored in a reference.
         */
        boolean getShouldStoreInReference();

        /**
         * Sets the {@link #getShouldStoreInReference() shouldStoreInReference} property.
         * @param shouldStoreInReference the new value for {@link #getShouldStoreInReference() shouldStoreInReference}.
         */
        void setShouldStoreInReference(boolean shouldStoreInReference);

        /**
         * Returns the name that should be used to store an instance of this {@link Variable} in a member field in the
         * test class. The default value is determined by the type name and the user's code style settings.
         * @return the test class member field name.
         */
        @NotNull
        String getTestClassMemberName();

        /**
         * Sets the testClassMemberName property to the given value.
         * @param testClassMemberName the new testClassMemberName.
         */
        void setTestClassMemberName(String testClassMemberName);

        /**
         * Returns the name that should be used to store an instance this {@link Variable} in a local field in the
         * test class. The default value is determined by the type name and the user's code style settings.
         * @return the test class local field name.
         */
        @NotNull
        String getTestClassLocalFieldName();

        /**
         * Sets the testClassLocalFieldName property to the given value.
         * @param testClassLocalFieldName the new testClassLocalFieldName.
         */
        void setTestClassLocalFieldName(String testClassLocalFieldName);

        /**
         * @return the method that contains this parameter or null if this is a ClassMember.
         */
        @Nullable Api.Method getContainingMethod();
    }

    /**
     * Describes a member field in the class.
     */
    interface ClassMember extends Variable {

        /**
         * @return whether or not the member is public.
         */
        boolean isPublic();

        /**
         * @return whether or not the member is protected.
         */
        boolean isProtected();

        /**
         * @return whether or not the member is package-local.
         */
        boolean isPackageLocal();

        /**
         * @return whether or not the member is private.
         */
        boolean isPrivate();

        /**
         * Returns a String representing the access level for this member.
         * Possible values are private, protected, packagePrivate, public.
         * @return the access level for this member.
         */
        String getAccessLevel();

        /**
         * @return whether or not the member is static.
         */
        boolean isStatic();

        /**
         * @return whether or not the member is transient.
         */
        boolean isTransient();

        /**
         * Returns true if this member has a dependency-annotation. A dependency-annotation is an annotation named:
         * &#64;Inject or &#64;Autowired.
         * @return whether or not the member has a dependency-annotation.
         */
        boolean isDependencyAnnotated();

        /**
         * @return whether or not the member is final.
         */
        boolean isFinal();

        /**
         * Returns the list of {@link Api.Variable Variables} whose value is assigned to this {@link ClassMember classMember}.
         * Example: If a constructor in class Foo takes in an argument called bar and has the statement: this.bar = bar,
         * Foo.bar.sourceVariables will be: [a pointer to Foo.bar (itself), a pointer to the Variable in constructor[0].parameters.get(0)].
         * @return the list of {@link Api.Variable Variables} whose value is assigned to this {@link ClassMember classMember} at some point.
         */
        FluentList<Variable> getPossibleSourceVariables();

        /**
         * @return getter methods for this ClassMember.
         */
        FluentList<Method> getGetters();

        /**
         * @return setter methods for this ClassMember.
         */
        FluentList<Method> getSetters();

        /**
         * @return whether this class is visible to the test class.
         */
        boolean isVisibleToTestClass();
    }

    /**
     * Describes a Java type.
     */
    interface Type extends SuperTypesHolder, PropsHolder {

        /**
         * This is an alias for this type; i.e. getType() returns this.
         * This is a convenience property that allows templates to create macros that take in either types or variables as
         * parameters.
         * @return This type.
         */
        Type getType();

        /**
         * <p>
         * Returns the name of the type that does not depend on import statements or other context.
         * This includes the canonical names of the type and any type parameters; e.g. {@link java.util.Map Map&lt;String, Date&gt;}
         * would have canonical text:
         * <code>
         *     java.util.Map&lt;java.lang.String, java.util.Date&gt;
         * </code>
         * </p>
         * <p>
         * The template code should use the canonical text when declare local fields and member fields in the test class.
         * This prevents the developer from having to add code to import the required types for each Variable used.
         * Squaretest automatically replaces the canonical names with import statements before saving the generated file;
         * so the fully qualified names should rarely appear in the generated test class.
         * </p>
         * <p>
         * Note: if the canonical text of a type or type parameter within the type cannot be determined, the canonical
         * text will contain the name as it appears in the type declaration; this can happen if the build path is not
         * configured correctly and IntelliJ cannot resolve references to the type.
         * </p>
         * @return the canonical text.
         */
        @NotNull
        String getCanonicalText();

        /**
         * Returns the canonical name of the class.
         * @see <a href="https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.7"> https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html#jls-6.7 </a>
         * This returns null if the canonical name could not be determined.
         * @return the canonical name or null if it could not be determined.
         */
        @Nullable
        String getCanonicalName();

        /**
         * @return the canonical name if it is non-null. Otherwise, this returns the name.
         */
        @NotNull
        String getCanonicalNameOrName();

        /**
         * Returns this type's type parameters; e.g. if this type is List&lt;Foo&gt;, parameters.first will be the {@link Type}:
         * Foo.
         * @return the list of type parameters.
         */
        Api.FluentList<Type> getParameters();

        boolean isWildcard();

        /**
         * Returns true if all of the following are true:
         * <ul>
         *     <li>
         *         All {@link #getParameters() parameters} override {@link Object#equals(Object)} or are
         *          {@link java.lang.Class Class&lt;T&gt;}.
         *     </li>
         *     <li>
         *         All {@link #getParameters() parameters} have allNestedTypeParamsOverrideEquals set to true.
         *     </li>
         * </ul>
         * @return true if this type's parameters (recursively) override {@link Object#equals(Object)}.
         */
        boolean getAllNestedTypeParamsOverrideEquals();

        boolean isOrHasAnyNestedTypeParamWith(final List<String> canonicalNames);

        boolean isOrHasAnyNestedTypeParamWith(final String... canonicalNames);

        /**
         * Returns the innermost component type for the array, if this type is an array.
         * Otherwise, this returns null.
         * @return the inner most component type of the array.
         */
        @Nullable
        Type getDeepArrayComponentType();

        @NotNull
        String getName();

        FluentList<Type> getFailureInitExpressionBeans();

        boolean getShouldStoreInReference();

        void setShouldStoreInReference(boolean shouldStoreInReference);

        /**
         * Creates and returns an initExpression that is the result of replacing the DTO Beans in the default initExpression
         * with the testClassLocalFieldName properties of the provided localFieldBeans.
         * @param localFieldBeans the bean types containing the {@link #getTestClassLocalFieldName() testClassLocalFieldName}
         *                        properties to use in the initExpression.
         * @return the updated initExpression.
         */
        @NotNull
        String createInitExpressionWithLocalFieldBeans(List<Type> localFieldBeans);

        String createFailureInitExpressionWithLocalFieldBeans(List<Type> localFieldBeans);

        /**
         * Returns true if the type is mockable. A type is mockable if it can be mocked using the
         * org.mockito.Mockito.mock method.
         *
         * @return whether or not the type is mockable.
         */
        boolean isMockable();

        /**
         * <p>
         * This is the default initialization expression for the type. This is a Java expression that returns
         * an instance of the type.
         * </p>
         * <p>
         * For certain types in the JDK, this will contain an expression that the user can easily modify to supply
         * a value for the dependency or method parameter; e.g. a Type with
         * {@link Object#getClass()#getCanonicalName() canonicalName} {@link java.util.List java.util.List} will have
         * a defaultInitExpression of {@link java.util.Arrays#asList(Object[]) java.util.Arrays.asList()}.
         * </p>
         * <p>
         * For other types this will contain a String equal to: "null" without quotes.
         * Note that the defaultInitExpression does not and should not end in a semicolon.
         * </p>
         * @return the variable's default init expression.
         */
        @NotNull
        String getDefaultInitExpression();

        /**
         * <p>
         * This is the initialization expression for the type. This is a Java expression that returns
         * an instance of the type.
         * </p>
         * <p>
         * For certain types in the JDK, this will contain an expression that the user can easily modify to supply
         * a value for the dependency or method parameter; e.g. a Type with
         * {@link Object#getClass()#getCanonicalName() canonicalName} {@link java.util.List java.util.List} will have
         * a defaultInitExpression of {@link java.util.Arrays#asList(Object[]) java.util.Arrays.asList()}.
         * </p>
         * <p>
         * For all other types this will contain a String equal to: "null" without quotes.
         * Note that the initExpression does not and should not end in a semicolon.
         * </p>
         * <p>
         * This may be modified by the template code; e.g. by setting $initExpressionOverrides in the
         * quick settings.
         * </p>
         * @return the initialization expression to use for this type.
         */
        @NotNull
        String getInitExpression();

        boolean isDtoBeanWithInputIoProperty();

        boolean isOptional();

        void setOptional(boolean optional);

        boolean isRecognized();

        /**
         * <p>
         * Sets the initialization expression for the type.
         * </p>
         * <p>
         * For certain types in the JDK, the initializationExpression will contain an expression that the user can easily modify to supply
         * a value for the dependency or method parameter; e.g. a Type with
         * {@link Object#getClass()#getCanonicalName() canonicalName} {@link java.util.List java.util.List} will have
         * a defaultInitExpression of {@link java.util.Arrays#asList(Object[]) java.util.Arrays.asList()}.
         * </p>
         * <p>
         * For all other types this will contain a String equal to: "null" without quotes.
         * Note that the initExpression does not and should not end in a semicolon.
         * </p>
         * <p>
         * This may be modified by the template code; e.g. by setting $initExpressionOverrides in the
         * quick settings.
         * </p>
         * @param initExpression the new initialization expression to use for this type.
         */
        void setInitExpression(String initExpression);

        void setDefaultInitExpression(@NotNull String defaultInitExpression);

        String getTestClassMemberName();

        void setTestClassMemberName(final String testClassMemberName);

        String getTestClassLocalFieldName();

        void setTestClassLocalFieldName(final String testClassLocalFieldName);

        String getAbsentInitExpression();

        void setAbsentInitExpression(String absentInitExpression);

        @Nullable
        String getEmptyInitExpression();

        void setEmptyInitExpression(@Nullable String emptyInitExpression);

        String getBrokenIoInitExpression();

        void setBrokenIoInitExpression(String brokenIoInitExpression);

        String getEmptyIoInitExpression();

        void setEmptyIoInitExpression(String emptyIoInitExpression);

        @Nullable
        String getFailureInitExpression();

        void setFailureInitExpression(@Nullable String failureInitExpression);

        String getDefaultFailureInitExpression();

        void setDefaultFailureInitExpression(@Nullable String defaultFailureInitExpression);

        /**
         * Returns whether or not the type should be mocked in the test class. For types with
         * {@link #getDefaultInitExpression() defaultInitExpression} != "null", this will be set to false.
         * For all other types, this will be set to {@link #isMockable() isMockable()}. This property may be set in
         * the template code.
         * @return whether or not the variable should be mocked in the test class.
         */
        boolean getShouldBeMocked();

        /**
         * Sets {@link #getShouldBeMocked() shouldBeMocked}.
         * @param shouldBeMocked the new value for {@link #getShouldBeMocked() shouldBeMocked}.
         */
        void setShouldBeMocked(boolean shouldBeMocked);

        boolean getShouldBeSpied();

        void setShouldBeSpied(boolean shouldBeSpied);

        /**
         * @return whether or not this type is an array.
         */
        boolean isArray();

        /**
         * @return whether or not the type is a Java primitive; e.g. int, char, short, etc.
         */
        boolean isPrimitive();

        /**
         * @return whether this type or one of its super types overrides {@link Object#equals(Object)}.
         */
        boolean getOverridesEquals();

        /**
         * @return whether this type is a {@link Class Class&lt;T&gt;}.
         */
        boolean isClassT();

        /**
         * @return whether this type is a generic type; e.g. T.
         */
        boolean isGeneric();

        /**
         * @return whether or not the type is an enum.
         */
        boolean isEnum();

        /**
         * Returns whether this class is a DTO and a Bean. A class is a DTOBean if the following are true:
         * <ul>
         *     <li>The class only has a no-args constructor.</li>
         *     <li>Every instance method in the class is a getter or setter.</li>
         *     <li>Every field in the class has both a getter and a setter.</li>
         * </ul>
         * This allows templates to determine if type is likely auto-generated from a schema file (xsd, wsdl, etc).
         * @return whether this class is a DTO and a Bean.
         */
        boolean isDtoBean();

        /**
         * @return true if the type implements {@link AutoCloseable}.
         */
        boolean isCloseable();

        /**
         * Returns whether or not Spring components consider this type to be a simple value type.
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/BeanUtils.html#isSimpleValueType-java.lang.Class-">BeanUtils.isSimpleValueType(Class&lt;?&gt;)</a>
         * @return boolean
         */
        boolean isSpringSimple();

        boolean isInterface();

        boolean isAbstract();

        boolean isSimple();

        /**
         * @return the list of DTOBeans ({@link Type Types}) used in the default initExpression.
         */
        Api.FluentList<Api.Type> getInitExpressionBeans();

        boolean isNested();

        boolean isStatic();

        boolean isPublic();

        boolean isProtected();

        boolean isPackageLocal();

        boolean isPrivate();

        boolean getShouldOnlySetUsedProperties();

        void setShouldOnlySetUsedProperties(boolean shouldOnlySetUsedProperties);
    }

    interface BooleanUtils {
        boolean valueOf(final String theString);
    }

    /**
     * Provides methods for obtaining items from lists.
     * Performing certain list operations like filtering and obtaining the min/max values can be difficult
     * to implement in velocity. ListUtils provides utility methods to solve this.
     */
    interface ListUtils {

        /**
         * This returns theList if it is non-null; otherwise, it returns {@link Collections#emptyList()}.
         * @param theList the list to return.
         * @return theList or {@link FluentListFactory#emptyList()}.
         */
        FluentList<?> nullToEmpty(List<?> theList);

        /**
         * Returns a list containing items before the provided item in the provided list.
         * If the provided item is not present in the list, this returns {@link FluentListFactory#emptyList()}.
         * @param theList the list to use.
         * @param obj the item.
         * @param <T> the type of the list.
         * @return a list containing items before the provided item in the provided list.
         */
        <T> FluentList<T> itemsBefore(List<T> theList, final Object obj);

        /**
         * Returns a list containing items after the provided item in the provided list.
         * If the provided item is not present in the list, this returns {@link FluentListFactory#emptyList()}.
         * @param theList the list to use.
         * @param obj the item.
         * @param <T> the type of the list.
         * @return a list containing items after the provided item in the provided list.
         */
        <T> FluentList<T> itemsAfter(List<T> theList, final Object obj);

        /**
         * Returns whether or not the list contains an attribute with the name: attributeNameExpression and a value equal
         * to one of the searchValues.
         * Example: <code>$ListUtils.containsAnyWith($method.parameters, 'type.canonicalName', {@link Runnable java.lang.Runnable}), {@link java.util.concurrent.Callable java.util.concurrent.Callable}</code>
         * will return true if at least one parameter in $method.parameters is a Runnable or Callable.
         *
         * @param theList of values to search
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param searchValues the values to search for.
         * @param <T> the type of the items in the list.
         * @return true if a value is found in the list.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> boolean containsAnyWith(
                List<T> theList, String attributeNameExpression,
                Object... searchValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * @deprecated Please use {@link #containsAnyWith(List, String, Object...)}
         * Returns whether or not the list contains an attribute with the name: attributeNameExpression and a value equal
         * to one of the searchValues.
         * Example: <code>$ListUtils.containsAny($method.parameters, 'type.canonicalName', {@link Runnable java.lang.Runnable}), {@link java.util.concurrent.Callable java.util.concurrent.Callable}</code>
         * will return true if at least one parameter in $method.parameters is a Runnable or Callable.
         *
         * @param theList of values to search
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param searchValues the values to search for.
         * @param <T> the type of the items in the list.
         * @return true if a value is found in the list.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        @Deprecated
        <T> boolean containsAny(
                List<T> theList, String attributeNameExpression,
                Object... searchValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns whether or not theList contains a value with a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @param <T> the type of the list.
         * @return whether or not theList contains a value with a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> boolean containsAnyWithPrefix(List<T> theList, String attributeNameExpression, String... prefixes) throws InvocationTargetException, IllegalAccessException;

        <T> boolean containsAnyWithPrefix(List<T> theList, String attributeNameExpression, List<String> prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns whether or not theList contains a value with a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the provided regular expression.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param regex the regex to use.
         * @param <T> the type of the list.
         * @return whether or not theList contains a value with a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the provided regular expression.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> boolean containsAnyWithRegex(List<T> theList, String attributeNameExpression, String regex) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns whether or not theList contains an attribute with the name: attributeNameExpression and a value equal
         * to searchValue.
         * Example: <code>$ListUtils.contains($method.declaredExceptions, 'type.canonicalName', 'java.io.IOException')</code>
         * will return true if $method.declaredExceptions contains a method that throws an {@link java.io.IOException}.
         *
         * @param theList of values to search
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param searchValue the value to search for.
         * @param <T> the type of the items in the list.
         * @return true if a value is found in the list.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> boolean contains(
                List<T> theList, String attributeNameExpression,
                Object searchValue) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns the index of the first value in the list with an attribute named: attributeNameExpression and a value equal
         * to one of the searchValues. This returns -1 if no such item is found in the list.
         *
         * @param theList of values to search
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param searchValues the values to search for.
         * @param <T> the type of the items in the list.
         * @return the index of the first matching item in the list or -1.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> int indexOfAny(
                List<T> theList, String attributeNameExpression,
                Object... searchValues) throws InvocationTargetException, IllegalAccessException;

        FluentList<?> map(List<?> theList, String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        FluentList<?> flatMap(List<?> theList, String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * This returns a new list containing items in the given list that have a property with name:
         * attributeNameExpression and a value in the attributeValues list.
         *
         * The property on each object in the list is with name: searching for a method called:
         * "get" + capitalize(attributeName) or "is" + capitalize(attributeName) and calling it. If an item in the
         * list does not have such a method, it is not included in the returned list.
         *
         * The example below sets the variable $mockMemberFields to the list of
         * variables in $memberFields that should be mocked.
         * {@code
         *  #set($mockMemberFields = ${ListUtils.filter($memberFields, "shouldBeMocked", true)})
         * }
         *
         * @param theList the list to filter
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValues the attribute value
         * @return the new list containing only items from the original list that match the above criteria.
         * @throws InvocationTargetException thrown if invoking the getter on an object in the list to retrieve the given attribute throws an exception.
         * @throws IllegalAccessException thrown if the property is inaccessible
         */
        <T> FluentList<T> filter(List<T> theList, String attributeNameExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return a list with items whose values contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> FluentList<T> filterItemsWithPrefix(
                List<T> theList, String attributeNameExpression,
                String... prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return a list with items whose values contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> FluentList<T> filterItemsWithPrefix(
                List<T> theList, String attributeNameExpression,
                List<String> prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param regex the regular expression to use.
         * @param <T> the type of the list.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> FluentList<T> filterItemsWithRegex(List<T> theList, String attributeNameExpression, String regex) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a copy of the provided list without items whose values contain a property with name: attributeNameExpression
         * is equal to the provided attributeValue.
         * @param theList the list to use
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValue the value to search for (exclude).
         * @param <T> the type of the list.
         * @return a copy of the provided list without items whose values contain a property with name: attributeNameExpression
         *         is equal to the provided attributeValue.
         */
        <T> FluentList<T> filterOut(List<T> theList, String attributeNameExpression, Object... attributeValue) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a new list whose values do not contain a property with name: attributeNameExpression
         * and a value equal to true.
         * @param theList the list to use
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param <T> the type of the list.
         * @return a copy of the provided list without items whose values do not contain a property with name: attributeNameExpression
         *         is equal to the provided attributeValue.
         */
        <T> FluentList<T> filterOut(List<T> theList, String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a copy of the provided list with the first occurrence of the specified element removed.
         * @param theList the list to use
         * @param valueToRemove the value to remove from theList
         * @param <T> the type of the list.
         * @return a copy of the provided list with the first occurrence of the specified element removed.
         */
        <T> FluentList<T> filterOutItem(List<T> theList, Object valueToRemove);

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @param <T> the type of the list.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> FluentList<T> filterOutItemsWithPrefix(List<T> theList, String attributeNameExpression, List<String> prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @param <T> the type of the list.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> FluentList<T> filterOutItemsWithPrefix(List<T> theList, String attributeNameExpression, String... prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @param theList the list to use.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param regex the regular expression to use.
         * @param <T> the type of the list.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        <T> FluentList<T> filterOutItemsWithRegex(List<T> theList, String attributeNameExpression, String regex) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns the max item in the list as determined by the items' implementations of {@link Comparable#compareTo(Object) compareTo(T)}.
         * Returns {@link java.util.Collections#emptyList() Collections.emptyList()} if the list is empty.
         * @param theList the list of {@link Comparable Comparable} items.
         * @param <T> You don't need to supply this in the velocity code, even if the editor shows a warning;
         *            just ensure the list contains comparable objects; e.g. {@link SourceClass#getPublicConstructors() SubjectClass.publicConstructors}.
         * @return the max item in the list as determined by {@link Comparable#compareTo(Object) compareTo(T)}.
         */
        <T extends Object & Comparable<? super T>> T max(List<? extends T> theList);

        /**
         * Returns the min item in the list as determined by the items' {@link Comparable#compareTo(Object)} implementations.
         * Returns {@link java.util.Collections#emptyList() Collections.emptyList()} if the list is empty.
         * @param theList the list of {@link Comparable Comparable} items.
         * @param <T> You don't need to supply this in the velocity code, even if it shows a warning;
         *            just ensure the list contains comparable objects.
         * @return the min item in the list as determined by {@link Comparable#compareTo(Object)}.
         */
        <T extends Object & Comparable<? super T>> T min(List<? extends T> theList);

        /**
         * Computes the intersection of the two {@link Iterable Iterables}.
         * @param list1 the first list
         * @param list2 the second list
         * @param <T> The type of the lists.
         * @return a {@link FluentList} containing the intersection of list1 and list2.
         */
        <T> FluentList<T> intersection(Iterable<? extends T> list1, Iterable<? extends T> list2);

        /**
         * Returns a new list containing the union of the two lists.
         * @param list1 the first list
         * @param list2 the second list
         * @param <T> The type of the lists.
         * @return a {@link FluentList} containing the union of the two lists.
         */
        <T> FluentList<? extends T> union(Iterable<? extends T> list1, Iterable<? extends T> list2);

        /**
         * Returns a new list containing the union of the two lists using the attributeNameExpression to
         * obtain the value to use for the equality checks.
         * @param list1 the first list
         * @param list2 the second list
         * @param attributeNameExpression the attribute name or expression used to determine the attribute; e.g. "type.canonicalName".
         * @param <T> The type of the lists.
         * @return a {@link FluentList} containing the union of the two lists.
         */
        <T> FluentList<? extends T> union(Iterable<? extends T> list1, Iterable<? extends T> list2, final String attributeNameExpression);

        <T> FluentList<T> filterItemsWithAnnotation(List<T> theList, String... annotations);

        <T> FluentList<T> filterItemsWithAnnotation(List<T> theList, List<String> annotations);

        <T> FluentList<T> filterOutItemsWithAnnotation(List<T> theList, String... annotations);

        <T> FluentList<T> filterOutItemsWithAnnotation(List<T> theList, List<String> annotations);

        <T> FluentList<T> filterItemsWithAnnotationPrefix(List<T> theList, String... prefixes);

        <T> FluentList<T> filterItemsWithAnnotationPrefix(List<T> theList, List<String> prefixes);

        <T> FluentList<T> filterOutItemsWithAnnotationPrefix(List<T> theList, String... prefixes);

        <T> FluentList<T> filterOutItemsWithAnnotationPrefix(List<T> theList, List<String> prefixes);

        <T> boolean containsAnyWithAnnotation(List<T> theList, String... annotations);

        <T> boolean containsAnyWithAnnotation(List<T> theList, List<String> annotations);

        <T> boolean containsAnyWithAnnotationPrefix(List<T> theList, String... prefixes);

        <T> boolean containsAnyWithAnnotationPrefix(List<T> theList, List<String> prefixes);
    }

    /**
     * Provides methods for obtaining recommended names for fields based on the developer's code style settings.
     */
    interface CodeStyleUtils {

        /**
         * Informs {@link CodeStyleUtils} this is the start of a Java or Groovy method. Invoking this method allows
         * {@link CodeStyleUtils} to suggest local variable names that do not conflict others declared in the same method.
         */
        void beginMethodScope();

        /**
         * Informs {@link CodeStyleUtils} this is the end of a Java or Groovy method. Invoking this method allows
         * {@link CodeStyleUtils} to suggest local variable names that do not conflict others declared in the same method.
         */
        void endMethodScope();

        /**
         * Returns the name to use for a member field of type: className.
         * @param className the type to use to determine the name; this is just the name of the type, not the canonical name.
         * @return the suggested member field name.
         */
        @NotNull
        String suggestMemberName(final String className);

        /**
         * Returns the name to use for a local field of type: className.
         * @param className the type to use to determine the name; this is just the name of the type, not the canonical name.
         * @return the suggested local field name.
         */
        @NotNull
        String suggestLocalFieldName(final String className);

        /**
         * Updates the {@link Variable#getTestClassLocalFieldName() testClassLocalFieldName} property of the provided variable
         * or type in a way that avoids name collisions with other variables or types that were updated after
         * {@link CodeStyleUtils#beginMethodScope()} was invoked.
         * @param variable the variable or type to update.
         */
        void updateLocalFieldNameWithMethodScope(Variable variable);

        /**
         * Updates the {@link Variable#getTestClassMemberName() testClassMemberName} property of the provided variable
         * or type in a way that avoids name collisions with other variables or types invoked during test generation.
         * @param variable the variable or type to update.
         */
        void updateMemberFieldNameWithClassScope(Variable variable);

        /**
         * Updates the {@link Variable#getTestClassLocalFieldName() testClassLocalFieldName} property of the provided variable
         * or type in a way that avoids name collisions with other variables or types that were updated after
         * {@link CodeStyleUtils#beginMethodScope()} was invoked.
         * @param type the variable or type to update.
         */
        void updateLocalFieldNameWithMethodScope(Type type);
    }

    /**
     * Contains APIs to show UI components to ask the user to confirm certain settings.
     */
    interface UiUtils {
        /**
         * Shows a dialog asking the user to confirm the provided settings.
         * @param settingsToConfirm a {@link Map Map&lt;String,Object&gt;} containing SettingsName -> Value.
         * @return a {@link Map Map&lt;String,Object&gt;} reflecting the user's decisions.
         * @throws UserCancelledGenerationException if the user closes the dialog asking him/her to confirm the settings.
         */
        Map<String, Object> askUserToConfirmSettings(final Map<String, Object> settingsToConfirm) throws UserCancelledGenerationException;

        /**
         * Shows a dialog asking the user to confirm the provided settings.
         * @param settingsToConfirm a {@link Map Map&lt;String,Object&gt;} containing SettingsName -> Value.
         * @return a {@link Map Map&lt;String,Object&gt;} reflecting the user's decisions.
         * @throws UserCancelledGenerationException if the user closes the dialog asking him/her to confirm the settings.
         */
        Map<String, Object> askUserToConfirmSettingsV2(
                Map<String, Object> settingsToConfirm) throws UserCancelledGenerationException;
    }

    class UserCancelledGenerationException extends java.lang.Exception {
    }

    /**
     * Provides methods for obtaining information about the classes present on the test classpath.
     */
    interface ClassUtils {
        /**
         * Returns true if a class with the provided canonical name is present on the test classpath.
         * @param classCanonicalName the canonical name of the class to search for on the test classpath
         * @return whether or not a class with the given canonical name is on the classpath.
         */
        boolean isInTestClasspath(final String classCanonicalName);

        Api.FluentList<Method> updateOverloadSuffixes(Api.FluentList<Method> methods);

        String removePackageQualifiers(final String canonicalText);

        /**
         * Resolves the class with the provided {@link Type} and returns the {@link SourceClass}.
         * Note: the methods in the returned {@link SourceClass} will not have their {@link Method#getDependencyInteractions() dependencyInteractions}
         * property populated.
         * @param type the type to resolve.
         * @return the {@link SourceClass} corresponding to the type or null if the type could not be resolved.
         */
        @Nullable
        SourceClass resolveClass(@Nullable final Type type);

        @Nullable
        SourceClass resolveClass(@Nullable final Api.Variable variable);

        @Nullable
        SourceClass resolveClass(@Nullable final String canonicalName);

        /**
         * Returns whether this type has a bean with at least one used property in its Type.initExpressionBeans list.
         * @param type the type to check.
         * @return whether this type has a bean with at least one used property in its Type.initExpressionBeans list.
         */
        boolean hasBeanWithUsedProperty(Type type);

        @Nullable
        SourceClass resolveBean(
                @Nullable BeanContext beanContext, @Nullable Variable variable);

        @Nullable
        SourceClass resolveBean(
                @Nullable BeanContext beanContext, @Nullable Type type);
    }

    interface FluentListFactory {

        // This class is needed, because we want the templates to use: $FluentList.emptyList() to create new, empty
        // FluentList objects. However, we can't have the SquaretestVelocityVariableProvider mapping:
        // $FluentList -> Api.FluentList, because when the user types "$FluentList." IDEA will show suggestions for
        // filter(), filterOut(), ...etc. These methods can't be called on static types.

        /**
         * Returns an empty {@link FluentList}.
         * @param <T> unused.
         * @return an empty {@link FluentList}.
         */
        static <T> FluentList<T> emptyList() {
            return new FluentListImpl<>(0);
        }

        static <T> FluentList<T> of(T... items) {
            final FluentList<T> ret = new FluentListImpl<>();
            ret.addAll(Arrays.asList(items));
            return ret;
        }
    }

    interface TestInfo extends PropsHolder {

        Method getMethod();

        TestInfo withMethod(Method sourceMethod);

        boolean getExpectedValueAbsent();

        TestInfo withExpectedValueAbsent(boolean expectedValueAbsent);

        boolean getExpectedValueNull();

        TestInfo withExpectedValueNull(boolean expectedValueNull);

        boolean getExpectedValueEmpty();

        TestInfo withExpectedValueEmpty(boolean expectedValueEmpty);

        boolean getExpectedValueTrue();

        TestInfoImpl withExpectedValueTrue(boolean expectedValueTrue);

        FluentList<DependencyInteraction> getMockedDIs();

        TestInfo withMockedDIs(FluentList<DependencyInteraction> mockedDIs);

        TestInfo addMockedDi(DependencyInteraction mockedDI);

        Exception getExpectedException();

        TestInfo withExpectedException(Exception expectedException);

        Variable getParamWithEmptyIo();

        TestInfo withParamWithEmptyIo(Variable paramWithEmptyIo);

        Variable getParamWithBrokenIo();

        TestInfo withParamWithBrokenIo(Variable paramWithBrokenIo);

        DependencyInteraction getSubjectDi();

        TestInfo withSubjectDi(DependencyInteraction subjectDi);

        TestInfo withDiToReturnAbsent(DependencyInteraction diToReturnAbsent);

        boolean getSubjectDiReturnsEmpty();

        boolean getSubjectDiReturnsAbsent();

        boolean getSubjectDiReturnsEmptyIo();

        boolean getSubjectDiReturnsBrokenIo();

        boolean getSubjectDiReturnsFailure();

        Exception getSubjectDiExceptionToThrow();

        TestInfo withDiToReturnEmpty(DependencyInteraction diToReturnEmpty);

        TestInfo withDiToReturnEmptyIo(DependencyInteraction diToReturnEmptyIo);

        TestInfo withDiToReturnBrokenIo(DependencyInteraction diToReturnBrokenIo);

        TestInfo withDiToReturnFailure(DependencyInteraction diToReturnFailure);

        TestInfo withDiThatThrows(DependencyInteraction diThatThrows, Exception diExceptionToThrow);

        boolean shouldReturnAbsent(DependencyInteraction di);

        boolean shouldReturnEmpty(DependencyInteraction di);

        boolean shouldReturnEmptyIo(DependencyInteraction di);

        boolean shouldReturnBrokenIo(DependencyInteraction di);

        boolean shouldThrowException(DependencyInteraction di);

        Exception getExceptionToThrow(DependencyInteraction di);

        boolean shouldReturnFailure(DependencyInteraction di);

        TestInfo withKeyValuePair(Object key, Object value);

        Object get(Object key);

        Object put(Object key, Object value);
    }

    interface AltInfo extends PropsHolder {

        Exception getExpectedException();

        AltInfo withExpectedException(Exception expectedException);

        void setExpectedException(Exception expectedException);

        Variable getParamWithEmptyIo();

        AltInfo withParamWithEmptyIo(Variable paramWithEmptyIo);

        void setParamWithEmptyIo(Variable paramWithEmptyIo);

        Variable getParamWithBrokenIo();

        AltInfo withParamWithBrokenIo(Variable paramWithBrokenIo);

        DependencyInteraction getSubjectDi();

        void setParamWithBrokenIo(Variable paramWithBrokenIo);

        DependencyInteraction getDiToReturnAbsent();

        AltInfo withDiToReturnAbsent(DependencyInteraction diToReturnAbsent);

        void setDiToReturnAbsent(DependencyInteraction diToReturnAbsent);

        DependencyInteraction getDiToReturnEmpty();

        AltInfo withDiToReturnEmpty(DependencyInteraction diToReturnEmpty);

        void setDiToReturnEmpty(DependencyInteraction diToReturnEmpty);

        DependencyInteraction getDiToReturnEmptyIo();

        AltInfo withDiToReturnEmptyIo(DependencyInteraction diToReturnEmptyIo);

        void setDiToReturnEmptyIo(DependencyInteraction diToReturnEmptyIo);

        DependencyInteraction getDiToReturnBrokenIo();

        AltInfo withDiToReturnBrokenIo(DependencyInteraction diToReturnBrokenIo);

        void setDiToReturnBrokenIo(DependencyInteraction diToReturnBrokenIo);

        DependencyInteraction getDiToReturnFailure();

        AltInfo withDiToReturnFailure(DependencyInteraction diToReturnFailure);

        void setDiToReturnFailure(DependencyInteraction diToReturnFailure);

        DependencyInteraction getDiThatThrows();

        Exception getDiExceptionToThrow();

        AltInfo withDiThatThrows(DependencyInteraction diThatThrows);

        AltInfo withDiThatThrows(DependencyInteraction diThatThrows, Exception diExceptionToThrow);

        AltInfo withDiThatThrows(DependencyInteraction diThatThrows, Exception diExceptionToThrow, Exception expectedException);

        void setDiThatThrows(DependencyInteraction diThatThrows);

        AltInfo withDiExceptionToThrow(Exception diExceptionToThrow);

        void setDiExceptionToThrow(Exception diExceptionToThrow);

        AltInfo withKeyValuePair(Object key, Object value);

        Object get(Object key);

        Object put(Object key, Object value);
    }

    interface AltInfoFactory {
        static AltInfo newValue() {
            return new AltInfoImpl();
        }

        static AltInfo fromExpectedException(final Api.Exception expectedException) {
            return new AltInfoImpl().withExpectedException(expectedException);
        }

        static AltInfo fromParamWithEmptyIo(final Api.Variable paramWithEmptyIo) {
            return new AltInfoImpl().withParamWithEmptyIo(paramWithEmptyIo);
        }

        static AltInfo fromParamWithBrokenIo(final Api.Variable paramWithBrokenIo) {
            return fromParamWithBrokenIo(paramWithBrokenIo, null);
        }

        static AltInfo fromParamWithBrokenIo(final Api.Variable paramWithBrokenIo, final Api.Exception expectedException) {
            return new AltInfoImpl().withParamWithBrokenIo(paramWithBrokenIo).withExpectedException(expectedException);
        }
        static AltInfo fromDiToReturnAbsent(final Api.DependencyInteraction diToReturnAbsent) {
            return new AltInfoImpl().withDiToReturnAbsent(diToReturnAbsent);
        }
        static AltInfo fromDiToReturnEmpty(final Api.DependencyInteraction diToReturnEmpty) {
            return new AltInfoImpl().withDiToReturnEmpty(diToReturnEmpty);
        }
        static AltInfo fromDiToReturnEmptyIo(final Api.DependencyInteraction diToReturnEmptyIo) {
            return new AltInfoImpl().withDiToReturnEmptyIo(diToReturnEmptyIo);
        }
        static AltInfo fromDiToReturnBrokenIo(final Api.DependencyInteraction diToReturnBrokenIo) {
            return fromDiToReturnBrokenIo(diToReturnBrokenIo, null);
        }
        static AltInfo fromDiToReturnBrokenIo(final Api.DependencyInteraction diToReturnBrokenIo, final Api.Exception expectedException) {
            return new AltInfoImpl().withDiToReturnBrokenIo(diToReturnBrokenIo).withExpectedException(expectedException);
        }
        static AltInfo fromDiToReturnFailure(final Api.DependencyInteraction diToReturnFailure) {
            return fromDiToReturnFailure(diToReturnFailure, null);
        }
        static AltInfo fromDiToReturnFailure(final Api.DependencyInteraction diToReturnFailure, final Api.Exception expectedException) {
            return new AltInfoImpl().withDiToReturnFailure(diToReturnFailure).withExpectedException(expectedException);
        }
        static AltInfo fromDiThatThrows(final Api.DependencyInteraction diThatThrows) {
            return fromDiThatThrows(diThatThrows, null, null);
        }
        static AltInfo fromDiThatThrows(final Api.DependencyInteraction diThatThrows, final Api.Exception diExceptionToThrow) {
            return fromDiThatThrows(diThatThrows, diExceptionToThrow, null);
        }
        static AltInfo fromDiThatThrows(final Api.DependencyInteraction diThatThrows, final Api.Exception diExceptionToThrow, final Api.Exception expectedException) {
            return new AltInfoImpl().withDiThatThrows(diThatThrows).withDiExceptionToThrow(diExceptionToThrow).withExpectedException(expectedException);
        }
        static AltInfo fromKeyValuePair(final Object key, final Object value) {
            return new AltInfoImpl().withKeyValuePair(key, value);
        }
    }

    interface TestInfoFactory {
        TestInfo newValue();

        TestInfo primaryFlow(Method sourceMethod, FluentList<DependencyInteraction> mockedDIs);

        TestInfo fromExpectedException(
                Method sourceMethod, Exception expectedException);

        TestInfo fromExpectedException(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                Exception expectedException);

        TestInfo fromParamWithEmptyIo(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                Variable paramWithEmptyIo);

        TestInfo fromParamWithBrokenIo(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                Variable paramWithBrokenIo) throws InvocationTargetException, IllegalAccessException;

        TestInfo fromDiToReturnAbsent(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                DependencyInteraction diToReturnAbsent) throws InvocationTargetException, IllegalAccessException;

        TestInfo fromDiToReturnEmpty(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                DependencyInteraction diToReturnEmpty) throws InvocationTargetException, IllegalAccessException;

        TestInfo fromDiToReturnEmptyIo(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                DependencyInteraction diToReturnEmptyIo);

        TestInfo fromDiToReturnBrokenIo(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                DependencyInteraction diToReturnBrokenIo) throws InvocationTargetException, IllegalAccessException;

        TestInfo fromDiToReturnFailure(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                DependencyInteraction diToReturnFailure);

        TestInfo fromDiThatThrows(
                Method sourceMethod, FluentList<DependencyInteraction> mockedDIs,
                DependencyInteraction diThatThrows, Exception diExceptionToThrow) throws InvocationTargetException, IllegalAccessException;

        /**
         * Updates the dependencies used to create test cases.
         * @param dependencies the dependencies the test class is using.
         */
        void updateDependencies(List<? extends Variable> dependencies);
    }
    
    interface MutableIntFactory {
        static MutableInt create(final int value) {
            return new MutableInt(value);
        }
    }

    interface MutableBooleanFactory {
        static MutableBoolean create(final boolean value) {
            return new SQMutableBoolean(value);
        }
    }

    interface MutableStringFactory {
        static MutableString create() {
            return new MutableString();
        }
        static MutableString create(final String value) {
            return new MutableString(value);
        }
    }

    interface MutableObjectFactory {
        static MutableObject<Object> create() {
            return new MutableObject<>();
        }
        static MutableObject<Object> create(final Object value) {
            return new MutableObject<>(value);
        }
    }

    /**
     * FluentList is a {@link List} with additional methods that make it easier to manipulate from an Apache Velocity
     * Template.
     * @param <T> inherited from {@link List}.
     */
    interface FluentList<T> extends List<T>, PropsHolder {

        FluentList<T> newList();

        @Nullable
        T getFirst();

        @Nullable
        T getSecond();

        @Nullable
        T getThird();

        @Nullable
        T getFourth();

        @Nullable
        T getFifth();

        @Nullable
        abstract T first();

        @Nullable
        T second();

        @Nullable
        T third();

        @Nullable
        T fourth();

        @Nullable
        T fifth();

        @Nullable
        T getLast();

        @Nullable
        T getOrNull(final int index);

        /**
         * Returns a list containing items before the provided item in the list.
         * If the provided item is not present in the list, this returns {@link FluentListFactory#emptyList()}.
         * @param obj the item.
         * @return a list containing items before the provided item in the list.
         */
        FluentList<T> itemsBefore(Object obj);

        /**
         * Returns a list containing items after the provided item in the list.
         * If the provided item is not present in the list, this returns {@link FluentListFactory#emptyList()}.
         * @param obj the item.
         * @return a list containing items after the provided item in the list.
         */
        FluentList<T> itemsAfter(Object obj);

        /**
         * Returns a new FluentList with the first limit elements of this list.
         * @param limit the maximum number of elements to include in the returned list.
         * @return a list containing the first limit elements of this list.
         */
        FluentList<T> limit(int limit);

        /**
         * Returns a String containing each element of this list joined with the provided delimiter.
         * @param delimiter the delimiter to use
         * @return a String containing each element of this list joined with the provided delimiter.
         */
        String join(String delimiter);

        /**
         * Returns whether this list contains any of the provided searchItems.
         * @param searchItems the items to search for.
         * @return whether this list contains any of the provided searchItems.
         */
        boolean containsAny(List<?> searchItems);

        /**
         * Returns whether or not the list contains an item with attribute name: attributeNameExpression and a value equal
         * to one of the searchValues.
         * Example: <code>$method.parameters.containsAny('type.canonicalName', 'java.lang.Runnable', 'java.util.concurrent.Callable')</code>
         * will return true if at least one parameter in $method.parameters is a Runnable or Callable.
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param searchValues the values to search for.
         * @return true if a value is found in the list.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        boolean containsAnyWith(
                String attributeNameExpression, Object... searchValues) throws InvocationTargetException, IllegalAccessException;

        boolean containsAnyWithItem(String attributeNameExpression, List<?> searchValues) throws InvocationTargetException, IllegalAccessException;

        boolean containsAnyWithNonNull(String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns whether or not the list contains a value with a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return whether or not the list contains a value with a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        boolean containsAnyWithPrefix(
                String attributeNameExpression, String... prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns whether or not the list contains a value with a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return whether or not the list contains a value with a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        boolean containsAnyWithPrefix(
                String attributeNameExpression, List<String> prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns whether or not the list contains a value with a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the provided regular expression.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param regex the regex to use.
         * @return whether or not the list contains a value with a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the provided regular expression.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        boolean containsAnyWithRegex(String attributeNameExpression, String regex) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns the index of the first value in the list with an attribute named: attributeNameExpression and a value equal
         * to one of the searchValues. This returns -1 if no such item is found in the list.
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param searchValues the values to search for.
         * @return the index of the first matching item in the list or -1.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        int indexOfAny(
                String attributeNameExpression, Object... searchValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a new {@link FluentList FluentList&lt;T&gt;} with the contents of this list and those of listToAppend appended
         * to it. This does not modify the original list.
         * @param listToAppend the list to append.
         * @return a new list
         */
        FluentList<T> concat(final Collection<T> listToAppend);

        /**
         * Returns a new {@link FluentList FluentList&lt;T&gt;} with the contents of this list and the provided item appended
         * to it. This does not modify the original list.
         * @param itemToAppend the list to append.
         * @return a new list
         */
        FluentList<T> concat(final T itemToAppend);

        /**
         * This returns a new list containing items in the list that have a property with name: attributeNameExpression
         * and a value in attributeValues.
         *
         * The property on each object in the list is with name: searching for a method called:
         * "get" + capitalize(attributeName) or "is" + capitalize(attributeName) and calling it. If an item in the
         * list does not have such a method, it is not included in the returned list.
         *
         * The example below sets the variable $mockMemberFields to the list of
         * variables in $memberFields that should be mocked.
         * {@code
         *  #set($mockMemberFields = $memberFields.filter('shouldBeMocked', true)})
         * }
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValues the attribute value
         * @return the new list containing only items from the original list that match the above criteria.
         * @throws InvocationTargetException thrown if invoking the getter on an object in the list to retrieve the given attribute throws an exception.
         * @throws IllegalAccessException thrown if the property is inaccessible
         */
        FluentList<T> filter(
                String attributeNameExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a {@link FluentList FluentList&lt;?&gt;} containing the results of invoking the given attributeNameExpression
         * on each element of this list.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @return the new list
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<?> map(String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * Invokes the given attributeNameExpression on each item in the list and returns a new list containing all values returned from the invocations.
         * If an invocation returns a List, all items in the list are added to the return list; i.e. the lists are flattened.
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @return the new list
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<?> flatMap(String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a {@link FluentList} containing {@link DependencyInteraction DependencyInteractions}
         * whose dependencies were satisfied by at least one of the provided dependencies.
         *
         * A {@link DependencyInteraction} is satisfied by a dependency if {@link DependencyInteraction#getField() DependencyInteraction.field.possibleSourceVariables}
         * contains at least one item in the provided dependencies list.
         *
         * Note that dependency interactions with the same (possibleSourceVariable, method) combination are removed.
         *
         * @param dependencies the dependencies provided to the instance of the {@link SourceClass}.
         *
         * @return a {@link FluentList}&lt;{@link DependencyInteraction}&gt; containing {@link DependencyInteraction DependencyInteractions} whose dependencies were satisfied by at least one of the provided dependencies.
         */
        FluentList<DependencyInteraction> satisfiedBy(
                List<? extends Variable> dependencies);

        /**
         * Returns a {@link FluentList} containing {@link DependencyInteraction DependencyInteractions}
         * whose field.possibleSourceVariables contain no elements in common with the provided dependencyInteraction.field.possibleSourceVariables.
         *
         * @param dependencyInteraction the interaction containing the field.possibleSourceVariables to use to filter out dependency interactions.
         *
         * @return a {@link FluentList}&lt;{@link DependencyInteraction}&gt;.
         */
        FluentList<DependencyInteraction> filterItemsWithSameSourceVar(DependencyInteraction dependencyInteraction);

        /**
         * Returns a {@link FluentList} containing {@link DependencyInteraction DependencyInteractions}
         * whose field.possibleSourceVariables contain at least one element in common with the provided dependencyInteraction.field.possibleSourceVariables.
         *
         * @param dependencyInteraction the interaction containing the field.possibleSourceVariables to use to filter out dependency interactions.
         *
         * @return a {@link FluentList}&lt;{@link DependencyInteraction}&gt;.
         */
        FluentList<DependencyInteraction> filterOutItemsWithSameSourceVar(DependencyInteraction dependencyInteraction);

        /**
         * This returns a new list containing items in the list that have a property with attributeNameExpression
         * and a value of: true.
         *
         * The property on each object in the list is with name: searching for a method called:
         * "get" + capitalize(attributeName) or "is" + capitalize(attributeName) and calling it. If an item in the
         * list does not have such a method, it is not included in the returned list.
         *
         * The example below sets the variable $mockMemberFields to the list of
         * variables in $memberFields that should be mocked.
         * {@code
         *  #set($mockMemberFields = $memberFields.filter('shouldBeMocked'})
         * }
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @return the new list containing only items from the original list that match the above criteria.
         * @throws InvocationTargetException thrown if invoking the getter on an object in the list to retrieve the given attribute throws an exception.
         * @throws IllegalAccessException thrown if the property is inaccessible
         */
        FluentList<T> filter(
                String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * This returns a new list containing items in the list that have a property with name: attributeNameExpression
         * and a value in attributeValues.
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValues the attribute value
         * @return the new list containing only items from the original list that match the above criteria.
         * @throws InvocationTargetException thrown if invoking the getter on an object in the list to retrieve the given attribute throws an exception.
         * @throws IllegalAccessException thrown if the property is inaccessible
         */
        FluentList<T> filter2(String attributeNameExpression, List<?> attributeValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return a list with items whose values contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<T> filterItemsWithPrefix(
                String attributeNameExpression, List<String> prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * This returns a new list containing items in the list that have a property with name: attributeNameExpression
         * and a value in attributeValues.
         *
         * The property on each object in the list is with name: searching for a method called:
         * "get" + capitalize(attributeName) or "is" + capitalize(attributeName) and calling it. If an item in the
         * list does not have such a method, it is not included in the returned list.
         *
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValues the attribute value
         * @return the new list containing only items from the original list that match the above criteria.
         * @throws InvocationTargetException thrown if invoking the getter on an object in the list to retrieve the given attribute throws an exception.
         * @throws IllegalAccessException thrown if the property is inaccessible
         */
        FluentList<T> filterItemsWithAny(String attributeNameExpression, List<Object> attributeValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return a list with items whose values contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<T> filterItemsWithPrefix(
                String attributeNameExpression, String... prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param regex the regular expression to use.
         * @return a list with items whose values contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<T> filterItemsWithRegex(
                String attributeNameExpression, String regex) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a new list containing items in the list that do not have a property with name: attributeNameExpression
         * and a value in attributeValues.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValues the value to search for (exclude).
         * @return a list without items whose values contain a property with name: attributeNameExpression
         *         is equal to the provided attributeValue.
         */
        FluentList<T> filterOut(
                String attributeNameExpression, Object... attributeValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a new list whose values do not contain a property with name: attributeNameExpression
         * and a value equal to true.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @return a copy of the list without items whose values contain a property with name: attributeNameExpression
         *         is equal to the provided attributeValue.
         */
        FluentList<T> filterOut(String attributeNameExpression) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a new list containing items in the list that do not have a property with name: attributeNameExpression
         * and a value in attributeValues.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param attributeValues the value to search for (exclude).
         * @return a list without items whose values contain a property with name: attributeNameExpression
         *         is equal to the provided attributeValue.
         */
        FluentList<T> filterOut2(String attributeNameExpression, List<?> attributeValues) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<T> filterOutItemsWithPrefix(
                String attributeNameExpression, List<String> prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * starts with at least one of the provided prefixes.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param prefixes the prefixes to search for.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         *  starts with at least one of the provided prefixes.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<T> filterOutItemsWithPrefix(
                String attributeNameExpression, String... prefixes) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a list with items whose values do not contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @param attributeNameExpression the attribute name or an expression used to determine the attribute name; e.g. "type.canonicalName".
         * @param regex the regular expression to use.
         * @return a list with items whose values do not contain a property with name: attributeNameExpression that
         * 1. is a String and 2. matches the given regular expression.
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        FluentList<T> filterOutItemsWithRegex(
                String attributeNameExpression, String regex) throws InvocationTargetException, IllegalAccessException;

        /**
         * Returns a copy of the list with all occurrences of the specified element removed.
         * @param valueToRemove the value to remove from theList
         * @return a copy of the list with the first occurrence of the specified element removed.
         */
        FluentList<T> filterOutItem(T valueToRemove);

        FluentList<T> filterItemsWithAnnotation(String... annotations);

        FluentList<T> filterItemsWithAnnotation(List<String> annotations);

        FluentList<T> filterOutItemsWithAnnotation(String... annotations);

        FluentList<T> filterOutItemsWithAnnotation(List<String> annotations);

        FluentList<T> filterItemsWithAnnotationPrefix(String... annotations);

        FluentList<T> filterItemsWithAnnotationPrefix(List<String> annotations);

        FluentList<T> filterOutItemsWithAnnotationPrefix(String... annotations);

        FluentList<T> filterOutItemsWithAnnotationPrefix(List<String> annotations);

        boolean containsAnyWithAnnotation(String... annotations);

        boolean containsAnyWithAnnotation(List<String> annotations);

        boolean containsAnyWithAnnotationPrefix(String... prefixes);

        boolean containsAnyWithAnnotationPrefix(List<String> prefixes);

        /**
         * Computes the intersection of this and the provided {@link Iterable}.
         * @param iterable the iterable
         * @return a {@link FluentList} containing the intersection of this and the provided iterable.
         */
        FluentList<? extends T> intersection(Iterable<? extends T> iterable);

        FluentList<? extends T> removeDuplicates();

        FluentList<? extends T> removeDuplicates(String attributeNameExpression);

        /**
         * Returns a new list containing the union of this list and the provided list.
         * @param otherList the other list
         * @return a new list containing the union of this and the provided other list.
         */
        FluentList<? extends T> union(Iterable<? extends T> otherList);

        /**
         * Returns a new list containing union of this list and the provided list using the attributeNameExpression to
         * obtain the value to use for the equality checks.
         * @param otherList the other list
         * @param attributeNameExpression the attribute name or expression used to determine the attribute; e.g. "type.canonicalName".
         * @return a new list containing the union of this and the provided other list.
         */
        FluentList<? extends T> union(Iterable<? extends T> otherList, String attributeNameExpression);

    }

    // The StringUtils interface was automatically generated from org.apache.commons.lang3.StringUtils in
    // CommonsLang-3.5.

    /**
     * <p>Operations on {@link java.lang.String} that are
     * {@code null} safe.</p>
     *
     * <ul>
     *  <li><b>IsEmpty/IsBlank</b>
     *      - checks if a String contains text</li>
     *  <li><b>Trim/Strip</b>
     *      - removes leading and trailing whitespace</li>
     *  <li><b>Equals/Compare</b>
     *      - compares two strings in a null-safe manner</li>
     *  <li><b>startsWith</b>
     *      - check if a String starts with a prefix in a null-safe manner</li>
     *  <li><b>endsWith</b>
     *      - check if a String ends with a suffix in a null-safe manner</li>
     *  <li><b>IndexOf/LastIndexOf/Contains</b>
     *      - null-safe index-of checks
     *  <li><b>IndexOfAny/LastIndexOfAny/IndexOfAnyBut/LastIndexOfAnyBut</b>
     *      - index-of any of a set of Strings</li>
     *  <li><b>ContainsOnly/ContainsNone/ContainsAny</b>
     *      - checks if String contains only/none/any of these characters</li>
     *  <li><b>Substring/Left/Right/Mid</b>
     *      - null-safe substring extractions</li>
     *  <li><b>SubstringBefore/SubstringAfter/SubstringBetween</b>
     *      - substring extraction relative to other strings</li>
     *  <li><b>Split/Join</b>
     *      - splits a String into an array of substrings and vice versa</li>
     *  <li><b>Remove/Delete</b>
     *      - removes part of a String</li>
     *  <li><b>Replace/Overlay</b>
     *      - Searches a String and replaces one String with another</li>
     *  <li><b>Chomp/Chop</b>
     *      - removes the last part of a String</li>
     *  <li><b>AppendIfMissing</b>
     *      - appends a suffix to the end of the String if not present</li>
     *  <li><b>PrependIfMissing</b>
     *      - prepends a prefix to the start of the String if not present</li>
     *  <li><b>LeftPad/RightPad/Center/Repeat</b>
     *      - pads a String</li>
     *  <li><b>UpperCase/LowerCase/SwapCase/Capitalize/Uncapitalize</b>
     *      - changes the case of a String</li>
     *  <li><b>CountMatches</b>
     *      - counts the number of occurrences of one String in another</li>
     *  <li><b>IsAlpha/IsNumeric/IsWhitespace/IsAsciiPrintable</b>
     *      - checks the characters in a String</li>
     *  <li><b>DefaultString</b>
     *      - protects against a null input String</li>
     *  <li><b>Rotate</b>
     *      - rotate (circular shift) a String</li>
     *  <li><b>Reverse/ReverseDelimited</b>
     *      - reverses a String</li>
     *  <li><b>Abbreviate</b>
     *      - abbreviates a string using ellipses or another given String</li>
     *  <li><b>Difference</b>
     *      - compares Strings and reports on their differences</li>
     *  <li><b>LevenshteinDistance</b>
     *      - the number of changes needed to change one String into another</li>
     * </ul>
     *
     * <p>The {@code StringUtils} class defines certain words related to
     * String handling.</p>
     *
     * <ul>
     *  <li>null - {@code null}</li>
     *  <li>empty - a zero-length string ({@code ""})</li>
     *  <li>space - the space character ({@code ' '}, char 32)</li>
     *  <li>whitespace - the characters defined by {@link Character#isWhitespace(char)}</li>
     *  <li>trim - the characters &lt;= 32 as in {@link String#trim()}</li>
     * </ul>
     *
     * <p>{@code StringUtils} handles {@code null} input Strings quietly.
     * That is to say that a {@code null} input will return {@code null}.
     * Where a {@code boolean} or {@code int} is being returned
     * details vary by method.</p>
     *
     * <p>A side effect of the {@code null} handling is that a
     * {@code NullPointerException} should be considered a bug in
     * {@code StringUtils}.</p>
     *
     * <p>Methods in this class include sample code in their Javadoc comments to explain their operation.
     * The symbol {@code *} is used to indicate any input including {@code null}.</p>
     *
     * <p>#ThreadSafe#</p>
     * @see java.lang.String
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    interface StringUtils {

        // Methods added by Squaretest (part of SQStringUtils, but not Apache Commons Lang 3 StringUtils).

        /**
         * Returns the longest of the provided Strings or null if all of the strings are null or this is called with no arguments.
         * @param strings the Strings
         * @return the longest String
         */
        String longest(final String... strings);

        /**
         * Returns the longest of the provided Strings or null if all of the strings are null or this is called with an empty list.
         * @param strings the Strings
         * @return the longest String
         */
        String longest(final List<String> strings);

        /**
         * Returns the shortest of the provided Strings or null if all of the strings are null or this is called with no arguments.
         * @param strings the Strings
         * @return the shortest String
         */
        String shortest(final String... strings);

        /**
         * Returns the shortest of the provided Strings or null if all of the strings are null or this is called with an empty list.
         * @param strings the Strings
         * @return the shortest String
         */
        String shortest(final List<String> strings);

        /**
         * <p>Searches a String for substrings delimited by the provided tag,
         * returning all matching substrings in an array.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} tag returns {@code null} (no match).
         * An empty ("") tag returns {@code null} (no match).</p>
         *
         * <pre>
         * StringUtils.substringsBetween("$a$...$b$", "$") = ["a","b"]
         * StringUtils.substringsBetween(null, *)          = null
         * StringUtils.substringsBetween(*, null)          = null
         * StringUtils.substringsBetween("", "$")          = null
         * </pre>
         *
         * @param str  the String containing the substrings, null returns null, empty returns empty
         * @param tag  the String identifying the start and end of the substring, empty returns null
         * @return a String Array of substrings, or {@code null} if no match
         * @since 2.3
         */
        String[] substringsBetween(final String str, final String tag);

        // Methods from Apache Commons Lang 3 StringUtils.

        /**
         * <p>Abbreviates a String using ellipses. This will turn
         * "Now is the time for all good men" into "Now is the time for..."</p>
         *
         * <p>Specifically:</p>
         * <ul>
         *   <li>If the number of characters in {@code str} is less than or equal to
         *       {@code maxWidth}, return {@code str}.</li>
         *   <li>Else abbreviate it to {@code (substring(str, 0, max-3) + "...")}.</li>
         *   <li>If {@code maxWidth} is less than {@code 4}, throw an
         *       {@code IllegalArgumentException}.</li>
         *   <li>In no case will it return a String of length greater than
         *       {@code maxWidth}.</li>
         * </ul>
         *
         * <pre>
         * StringUtils.abbreviate(null, *)      = null
         * StringUtils.abbreviate("", 4)        = ""
         * StringUtils.abbreviate("abcdefg", 6) = "abc..."
         * StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
         * StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
         * StringUtils.abbreviate("abcdefg", 4) = "a..."
         * StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
         * </pre>
         *
         * @param str  the String to check, may be null
         * @param maxWidth  maximum length of result String, must be at least 4
         * @return abbreviated String, {@code null} if null String input
         * @throws IllegalArgumentException if the width is too small
         * @since 2.0
         */
        String abbreviate(String str, int maxWidth);

        /**
         * <p>Abbreviates a String using ellipses. This will turn
         * "Now is the time for all good men" into "...is the time for..."</p>
         *
         * <p>Works like {@code abbreviate(String, int)}, but allows you to specify
         * a "left edge" offset.  Note that this left edge is not necessarily going to
         * be the leftmost character in the result, or the first character following the
         * ellipses, but it will appear somewhere in the result.
         *
         * <p>In no case will it return a String of length greater than
         * {@code maxWidth}.</p>
         *
         * <pre>
         * StringUtils.abbreviate(null, *, *)                = null
         * StringUtils.abbreviate("", 0, 4)                  = ""
         * StringUtils.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
         * StringUtils.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
         * StringUtils.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
         * StringUtils.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
         * StringUtils.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
         * StringUtils.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
         * StringUtils.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
         * StringUtils.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
         * StringUtils.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
         * StringUtils.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
         * StringUtils.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
         * </pre>
         *
         * @param str  the String to check, may be null
         * @param offset  left edge of source String
         * @param maxWidth  maximum length of result String, must be at least 4
         * @return abbreviated String, {@code null} if null String input
         * @throws IllegalArgumentException if the width is too small
         * @since 2.0
         */
        String abbreviate(String str, int offset, int maxWidth);

        /**
         * <p>Abbreviates a String using another given String as replacement marker. This will turn
         * "Now is the time for all good men" into "Now is the time for..." if "..." was defined
         * as the replacement marker.</p>
         *
         * <p>Specifically:</p>
         * <ul>
         *   <li>If the number of characters in {@code str} is less than or equal to
         *       {@code maxWidth}, return {@code str}.</li>
         *   <li>Else abbreviate it to {@code (substring(str, 0, max-abbrevMarker.length) + abbrevMarker)}.</li>
         *   <li>If {@code maxWidth} is less than {@code abbrevMarker.length + 1}, throw an
         *       {@code IllegalArgumentException}.</li>
         *   <li>In no case will it return a String of length greater than
         *       {@code maxWidth}.</li>
         * </ul>
         *
         * <pre>
         * StringUtils.abbreviate(null, "...", *)      = null
         * StringUtils.abbreviate("abcdefg", null, *)  = "abcdefg"
         * StringUtils.abbreviate("", "...", 4)        = ""
         * StringUtils.abbreviate("abcdefg", ".", 5)   = "abcd."
         * StringUtils.abbreviate("abcdefg", ".", 7)   = "abcdefg"
         * StringUtils.abbreviate("abcdefg", ".", 8)   = "abcdefg"
         * StringUtils.abbreviate("abcdefg", "..", 4)  = "ab.."
         * StringUtils.abbreviate("abcdefg", "..", 3)  = "a.."
         * StringUtils.abbreviate("abcdefg", "..", 2)  = IllegalArgumentException
         * StringUtils.abbreviate("abcdefg", "...", 3) = IllegalArgumentException
         * </pre>
         *
         * @param str  the String to check, may be null
         * @param abbrevMarker  the String used as replacement marker
         * @param maxWidth  maximum length of result String, must be at least {@code abbrevMarker.length + 1}
         * @return abbreviated String, {@code null} if null String input
         * @throws IllegalArgumentException if the width is too small
         * @since 3.6
         */
        String abbreviate(String str, String abbrevMarker, int maxWidth);

        /**
         * <p>Abbreviates a String using a given replacement marker. This will turn
         * "Now is the time for all good men" into "...is the time for..." if "..." was defined
         * as the replacement marker.</p>
         *
         * <p>Works like {@code abbreviate(String, String, int)}, but allows you to specify
         * a "left edge" offset.  Note that this left edge is not necessarily going to
         * be the leftmost character in the result, or the first character following the
         * replacement marker, but it will appear somewhere in the result.
         *
         * <p>In no case will it return a String of length greater than {@code maxWidth}.</p>
         *
         * <pre>
         * StringUtils.abbreviate(null, null, *, *)                 = null
         * StringUtils.abbreviate("abcdefghijklmno", null, *, *)    = "abcdefghijklmno"
         * StringUtils.abbreviate("", "...", 0, 4)                  = ""
         * StringUtils.abbreviate("abcdefghijklmno", "---", -1, 10) = "abcdefg---"
         * StringUtils.abbreviate("abcdefghijklmno", ",", 0, 10)    = "abcdefghi,"
         * StringUtils.abbreviate("abcdefghijklmno", ",", 1, 10)    = "abcdefghi,"
         * StringUtils.abbreviate("abcdefghijklmno", ",", 2, 10)    = "abcdefghi,"
         * StringUtils.abbreviate("abcdefghijklmno", "::", 4, 10)   = "::efghij::"
         * StringUtils.abbreviate("abcdefghijklmno", "...", 6, 10)  = "...ghij..."
         * StringUtils.abbreviate("abcdefghijklmno", "*", 9, 10)    = "*ghijklmno"
         * StringUtils.abbreviate("abcdefghijklmno", "'", 10, 10)   = "'ghijklmno"
         * StringUtils.abbreviate("abcdefghijklmno", "!", 12, 10)   = "!ghijklmno"
         * StringUtils.abbreviate("abcdefghij", "abra", 0, 4)       = IllegalArgumentException
         * StringUtils.abbreviate("abcdefghij", "...", 5, 6)        = IllegalArgumentException
         * </pre>
         *
         * @param str  the String to check, may be null
         * @param abbrevMarker  the String used as replacement marker
         * @param offset  left edge of source String
         * @param maxWidth  maximum length of result String, must be at least 4
         * @return abbreviated String, {@code null} if null String input
         * @throws IllegalArgumentException if the width is too small
         * @since 3.6
         */
        String abbreviate(String str, String abbrevMarker, int offset, int maxWidth);

        /**
         * <p>Abbreviates a String to the length passed, replacing the middle characters with the supplied
         * replacement String.</p>
         *
         * <p>This abbreviation only occurs if the following criteria is met:</p>
         * <ul>
         * <li>Neither the String for abbreviation nor the replacement String are null or empty </li>
         * <li>The length to truncate to is less than the length of the supplied String</li>
         * <li>The length to truncate to is greater than 0</li>
         * <li>The abbreviated String will have enough room for the length supplied replacement String
         * and the first and last characters of the supplied String for abbreviation</li>
         * </ul>
         * <p>Otherwise, the returned String will be the same as the supplied String for abbreviation.
         * </p>
         *
         * <pre>
         * StringUtils.abbreviateMiddle(null, null, 0)      = null
         * StringUtils.abbreviateMiddle("abc", null, 0)      = "abc"
         * StringUtils.abbreviateMiddle("abc", ".", 0)      = "abc"
         * StringUtils.abbreviateMiddle("abc", ".", 3)      = "abc"
         * StringUtils.abbreviateMiddle("abcdef", ".", 4)     = "ab.f"
         * </pre>
         *
         * @param str  the String to abbreviate, may be null
         * @param middle the String to replace the middle characters with, may be null
         * @param length the length to abbreviate {@code str} to.
         * @return the abbreviated String if the above criteria is met, or the original String supplied for abbreviation.
         * @since 2.5
         */
        String abbreviateMiddle(String str, String middle, int length);

        /**
         * Appends the suffix to the end of the string if the string does not
         * already end with any of the suffixes.
         *
         * <pre>
         * StringUtils.appendIfMissing(null, null) = null
         * StringUtils.appendIfMissing("abc", null) = "abc"
         * StringUtils.appendIfMissing("", "xyz") = "xyz"
         * StringUtils.appendIfMissing("abc", "xyz") = "abcxyz"
         * StringUtils.appendIfMissing("abcxyz", "xyz") = "abcxyz"
         * StringUtils.appendIfMissing("abcXYZ", "xyz") = "abcXYZxyz"
         * </pre>
         * <p>With additional suffixes,</p>
         * <pre>
         * StringUtils.appendIfMissing(null, null, null) = null
         * StringUtils.appendIfMissing("abc", null, null) = "abc"
         * StringUtils.appendIfMissing("", "xyz", null) = "xyz"
         * StringUtils.appendIfMissing("abc", "xyz", new CharSequence[]{null}) = "abcxyz"
         * StringUtils.appendIfMissing("abc", "xyz", "") = "abc"
         * StringUtils.appendIfMissing("abc", "xyz", "mno") = "abcxyz"
         * StringUtils.appendIfMissing("abcxyz", "xyz", "mno") = "abcxyz"
         * StringUtils.appendIfMissing("abcmno", "xyz", "mno") = "abcmno"
         * StringUtils.appendIfMissing("abcXYZ", "xyz", "mno") = "abcXYZxyz"
         * StringUtils.appendIfMissing("abcMNO", "xyz", "mno") = "abcMNOxyz"
         * </pre>
         *
         * @param str The string.
         * @param suffix The suffix to append to the end of the string.
         * @param suffixes Additional suffixes that are valid terminators.
         *
         * @return A new String if suffix was appended, the same string otherwise.
         *
         * @since 3.2
         */
        String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes);

        /**
         * Appends the suffix to the end of the string if the string does not
         * already end, case insensitive, with any of the suffixes.
         *
         * <pre>
         * StringUtils.appendIfMissingIgnoreCase(null, null) = null
         * StringUtils.appendIfMissingIgnoreCase("abc", null) = "abc"
         * StringUtils.appendIfMissingIgnoreCase("", "xyz") = "xyz"
         * StringUtils.appendIfMissingIgnoreCase("abc", "xyz") = "abcxyz"
         * StringUtils.appendIfMissingIgnoreCase("abcxyz", "xyz") = "abcxyz"
         * StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz") = "abcXYZ"
         * </pre>
         * <p>With additional suffixes,</p>
         * <pre>
         * StringUtils.appendIfMissingIgnoreCase(null, null, null) = null
         * StringUtils.appendIfMissingIgnoreCase("abc", null, null) = "abc"
         * StringUtils.appendIfMissingIgnoreCase("", "xyz", null) = "xyz"
         * StringUtils.appendIfMissingIgnoreCase("abc", "xyz", new CharSequence[]{null}) = "abcxyz"
         * StringUtils.appendIfMissingIgnoreCase("abc", "xyz", "") = "abc"
         * StringUtils.appendIfMissingIgnoreCase("abc", "xyz", "mno") = "abcxyz"
         * StringUtils.appendIfMissingIgnoreCase("abcxyz", "xyz", "mno") = "abcxyz"
         * StringUtils.appendIfMissingIgnoreCase("abcmno", "xyz", "mno") = "abcmno"
         * StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz", "mno") = "abcXYZ"
         * StringUtils.appendIfMissingIgnoreCase("abcMNO", "xyz", "mno") = "abcMNO"
         * </pre>
         *
         * @param str The string.
         * @param suffix The suffix to append to the end of the string.
         * @param suffixes Additional suffixes that are valid terminators.
         *
         * @return A new String if suffix was appended, the same string otherwise.
         *
         * @since 3.2
         */
        String appendIfMissingIgnoreCase(String str, CharSequence suffix, CharSequence... suffixes);

        /**
         * <p>Capitalizes a String changing the first character to title case as
         * per {@link Character#toTitleCase(int)}. No other characters are changed.</p>
         *
         * <p>For a word based algorithm, see {@link org.apache.commons.lang3.text.WordUtils#capitalize(String)}.
         * A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.capitalize(null)  = null
         * StringUtils.capitalize("")    = ""
         * StringUtils.capitalize("cat") = "Cat"
         * StringUtils.capitalize("cAt") = "CAt"
         * StringUtils.capitalize("'cat'") = "'cat'"
         * </pre>
         *
         * @param str the String to capitalize, may be null
         * @return the capitalized String, {@code null} if null String input
         * @see org.apache.commons.lang3.text.WordUtils#capitalize(String)
         * @see #uncapitalize(String)
         * @since 2.0
         */
        String capitalize(String str);

        /**
         * <p>Centers a String in a larger String of size {@code size}
         * using the space character (' ').</p>
         *
         * <p>If the size is less than the String length, the original String is returned.
         * A {@code null} String returns {@code null}.
         * A negative size is treated as zero.</p>
         *
         * <p>Equivalent to {@code center(str, size, " ")}.</p>
         *
         * <pre>
         * StringUtils.center(null, *)   = null
         * StringUtils.center("", 4)     = "    "
         * StringUtils.center("ab", -1)  = "ab"
         * StringUtils.center("ab", 4)   = " ab "
         * StringUtils.center("abcd", 2) = "abcd"
         * StringUtils.center("a", 4)    = " a  "
         * </pre>
         *
         * @param str  the String to center, may be null
         * @param size  the int size of new String, negative treated as zero
         * @return centered String, {@code null} if null String input
         */
        String center(String str, int size);

        /**
         * <p>Centers a String in a larger String of size {@code size}.
         * Uses a supplied character as the value to pad the String with.</p>
         *
         * <p>If the size is less than the String length, the String is returned.
         * A {@code null} String returns {@code null}.
         * A negative size is treated as zero.</p>
         *
         * <pre>
         * StringUtils.center(null, *, *)     = null
         * StringUtils.center("", 4, ' ')     = "    "
         * StringUtils.center("ab", -1, ' ')  = "ab"
         * StringUtils.center("ab", 4, ' ')   = " ab "
         * StringUtils.center("abcd", 2, ' ') = "abcd"
         * StringUtils.center("a", 4, ' ')    = " a  "
         * StringUtils.center("a", 4, 'y')    = "yayy"
         * </pre>
         *
         * @param str  the String to center, may be null
         * @param size  the int size of new String, negative treated as zero
         * @param padChar  the character to pad the new String with
         * @return centered String, {@code null} if null String input
         * @since 2.0
         */
        String center(String str, int size, char padChar);

        /**
         * <p>Centers a String in a larger String of size {@code size}.
         * Uses a supplied String as the value to pad the String with.</p>
         *
         * <p>If the size is less than the String length, the String is returned.
         * A {@code null} String returns {@code null}.
         * A negative size is treated as zero.</p>
         *
         * <pre>
         * StringUtils.center(null, *, *)     = null
         * StringUtils.center("", 4, " ")     = "    "
         * StringUtils.center("ab", -1, " ")  = "ab"
         * StringUtils.center("ab", 4, " ")   = " ab "
         * StringUtils.center("abcd", 2, " ") = "abcd"
         * StringUtils.center("a", 4, " ")    = " a  "
         * StringUtils.center("a", 4, "yz")   = "yayz"
         * StringUtils.center("abc", 7, null) = "  abc  "
         * StringUtils.center("abc", 7, "")   = "  abc  "
         * </pre>
         *
         * @param str  the String to center, may be null
         * @param size  the int size of new String, negative treated as zero
         * @param padStr  the String to pad the new String with, must not be null or empty
         * @return centered String, {@code null} if null String input
         * @throws IllegalArgumentException if padStr is {@code null} or empty
         */
        String center(String str, int size, String padStr);

        /**
         * <p>Removes one newline from end of a String if it's there,
         * otherwise leave it alone.  A newline is &quot;{@code \n}&quot;,
         * &quot;{@code \r}&quot;, or &quot;{@code \r\n}&quot;.</p>
         *
         * <p>NOTE: This method changed in 2.0.
         * It now more closely matches Perl chomp.</p>
         *
         * <pre>
         * StringUtils.chomp(null)          = null
         * StringUtils.chomp("")            = ""
         * StringUtils.chomp("abc \r")      = "abc "
         * StringUtils.chomp("abc\n")       = "abc"
         * StringUtils.chomp("abc\r\n")     = "abc"
         * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n"
         * StringUtils.chomp("abc\n\r")     = "abc\n"
         * StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc"
         * StringUtils.chomp("\r")          = ""
         * StringUtils.chomp("\n")          = ""
         * StringUtils.chomp("\r\n")        = ""
         * </pre>
         *
         * @param str  the String to chomp a newline from, may be null
         * @return String without newline, {@code null} if null String input
         */
        String chomp(String str);

        /**
         * <p>Removes {@code separator} from the end of
         * {@code str} if it's there, otherwise leave it alone.</p>
         *
         * <p>NOTE: This method changed in version 2.0.
         * It now more closely matches Perl chomp.
         * For the previous behavior, use {@link #substringBeforeLast(String, String)}.
         * This method uses {@link String#endsWith(String)}.</p>
         *
         * <pre>
         * StringUtils.chomp(null, *)         = null
         * StringUtils.chomp("", *)           = ""
         * StringUtils.chomp("foobar", "bar") = "foo"
         * StringUtils.chomp("foobar", "baz") = "foobar"
         * StringUtils.chomp("foo", "foo")    = ""
         * StringUtils.chomp("foo ", "foo")   = "foo "
         * StringUtils.chomp(" foo", "foo")   = " "
         * StringUtils.chomp("foo", "foooo")  = "foo"
         * StringUtils.chomp("foo", "")       = "foo"
         * StringUtils.chomp("foo", null)     = "foo"
         * </pre>
         *
         * @param str  the String to chomp from, may be null
         * @param separator  separator String, may be null
         * @return String without trailing separator, {@code null} if null String input
         * @deprecated This feature will be removed in Lang 4.0, use {@link org.apache.commons.lang3.StringUtils#removeEnd(String, String)} instead
         */
        @Deprecated
        String chomp(String str, String separator);

        /**
         * <p>Remove the last character from a String.</p>
         *
         * <p>If the String ends in {@code \r\n}, then remove both
         * of them.</p>
         *
         * <pre>
         * StringUtils.chop(null)          = null
         * StringUtils.chop("")            = ""
         * StringUtils.chop("abc \r")      = "abc "
         * StringUtils.chop("abc\n")       = "abc"
         * StringUtils.chop("abc\r\n")     = "abc"
         * StringUtils.chop("abc")         = "ab"
         * StringUtils.chop("abc\nabc")    = "abc\nab"
         * StringUtils.chop("a")           = ""
         * StringUtils.chop("\r")          = ""
         * StringUtils.chop("\n")          = ""
         * StringUtils.chop("\r\n")        = ""
         * </pre>
         *
         * @param str  the String to chop last character from, may be null
         * @return String without last character, {@code null} if null String input
         */
        String chop(String str);

        /**
         * <p>Compare two Strings lexicographically, as per {@link String#compareTo(String)}, returning :</p>
         * <ul>
         *  <li>{@code int = 0}, if {@code str1} is equal to {@code str2} (or both {@code null})</li>
         *  <li>{@code int < 0}, if {@code str1} is less than {@code str2}</li>
         *  <li>{@code int > 0}, if {@code str1} is greater than {@code str2}</li>
         * </ul>
         *
         * <p>This is a {@code null} safe version of :</p>
         * <blockquote><pre>str1.compareTo(str2)</pre></blockquote>
         *
         * <p>{@code null} value is considered less than non-{@code null} value.
         * Two {@code null} references are considered equal.</p>
         *
         * <pre>
         * StringUtils.compare(null, null)   = 0
         * StringUtils.compare(null , "a")   &lt; 0
         * StringUtils.compare("a", null)    &gt; 0
         * StringUtils.compare("abc", "abc") = 0
         * StringUtils.compare("a", "b")     &lt; 0
         * StringUtils.compare("b", "a")     &gt; 0
         * StringUtils.compare("a", "B")     &gt; 0
         * StringUtils.compare("ab", "abc")  &lt; 0
         * </pre>
         *
         * @see #compare(String, String, boolean)
         * @see String#compareTo(String)
         * @param str1  the String to compare from
         * @param str2  the String to compare to
         * @return &lt; 0, 0, &gt; 0, if {@code str1} is respectively less, equal or greater than {@code str2}
         * @since 3.5
         */
        int compare(String str1, String str2);

        /**
         * <p>Compare two Strings lexicographically, as per {@link String#compareTo(String)}, returning :</p>
         * <ul>
         *  <li>{@code int = 0}, if {@code str1} is equal to {@code str2} (or both {@code null})</li>
         *  <li>{@code int < 0}, if {@code str1} is less than {@code str2}</li>
         *  <li>{@code int > 0}, if {@code str1} is greater than {@code str2}</li>
         * </ul>
         *
         * <p>This is a {@code null} safe version of :</p>
         * <blockquote><pre>str1.compareTo(str2)</pre></blockquote>
         *
         * <p>{@code null} inputs are handled according to the {@code nullIsLess} parameter.
         * Two {@code null} references are considered equal.</p>
         *
         * <pre>
         * StringUtils.compare(null, null, *)     = 0
         * StringUtils.compare(null , "a", true)  &lt; 0
         * StringUtils.compare(null , "a", false) &gt; 0
         * StringUtils.compare("a", null, true)   &gt; 0
         * StringUtils.compare("a", null, false)  &lt; 0
         * StringUtils.compare("abc", "abc", *)   = 0
         * StringUtils.compare("a", "b", *)       &lt; 0
         * StringUtils.compare("b", "a", *)       &gt; 0
         * StringUtils.compare("a", "B", *)       &gt; 0
         * StringUtils.compare("ab", "abc", *)    &lt; 0
         * </pre>
         *
         * @see String#compareTo(String)
         * @param str1  the String to compare from
         * @param str2  the String to compare to
         * @param nullIsLess  whether consider {@code null} value less than non-{@code null} value
         * @return &lt; 0, 0, &gt; 0, if {@code str1} is respectively less, equal ou greater than {@code str2}
         * @since 3.5
         */
        int compare(String str1, String str2, boolean nullIsLess);

        /**
         * <p>Compare two Strings lexicographically, ignoring case differences,
         * as per {@link String#compareToIgnoreCase(String)}, returning :</p>
         * <ul>
         *  <li>{@code int = 0}, if {@code str1} is equal to {@code str2} (or both {@code null})</li>
         *  <li>{@code int < 0}, if {@code str1} is less than {@code str2}</li>
         *  <li>{@code int > 0}, if {@code str1} is greater than {@code str2}</li>
         * </ul>
         *
         * <p>This is a {@code null} safe version of :</p>
         * <blockquote><pre>str1.compareToIgnoreCase(str2)</pre></blockquote>
         *
         * <p>{@code null} value is considered less than non-{@code null} value.
         * Two {@code null} references are considered equal.
         * Comparison is case insensitive.</p>
         *
         * <pre>
         * StringUtils.compareIgnoreCase(null, null)   = 0
         * StringUtils.compareIgnoreCase(null , "a")   &lt; 0
         * StringUtils.compareIgnoreCase("a", null)    &gt; 0
         * StringUtils.compareIgnoreCase("abc", "abc") = 0
         * StringUtils.compareIgnoreCase("abc", "ABC") = 0
         * StringUtils.compareIgnoreCase("a", "b")     &lt; 0
         * StringUtils.compareIgnoreCase("b", "a")     &gt; 0
         * StringUtils.compareIgnoreCase("a", "B")     &lt; 0
         * StringUtils.compareIgnoreCase("A", "b")     &lt; 0
         * StringUtils.compareIgnoreCase("ab", "ABC")  &lt; 0
         * </pre>
         *
         * @see #compareIgnoreCase(String, String, boolean)
         * @see String#compareToIgnoreCase(String)
         * @param str1  the String to compare from
         * @param str2  the String to compare to
         * @return &lt; 0, 0, &gt; 0, if {@code str1} is respectively less, equal ou greater than {@code str2},
         *          ignoring case differences.
         * @since 3.5
         */
        int compareIgnoreCase(String str1, String str2);

        /**
         * <p>Compare two Strings lexicographically, ignoring case differences,
         * as per {@link String#compareToIgnoreCase(String)}, returning :</p>
         * <ul>
         *  <li>{@code int = 0}, if {@code str1} is equal to {@code str2} (or both {@code null})</li>
         *  <li>{@code int < 0}, if {@code str1} is less than {@code str2}</li>
         *  <li>{@code int > 0}, if {@code str1} is greater than {@code str2}</li>
         * </ul>
         *
         * <p>This is a {@code null} safe version of :</p>
         * <blockquote><pre>str1.compareToIgnoreCase(str2)</pre></blockquote>
         *
         * <p>{@code null} inputs are handled according to the {@code nullIsLess} parameter.
         * Two {@code null} references are considered equal.
         * Comparison is case insensitive.</p>
         *
         * <pre>
         * StringUtils.compareIgnoreCase(null, null, *)     = 0
         * StringUtils.compareIgnoreCase(null , "a", true)  &lt; 0
         * StringUtils.compareIgnoreCase(null , "a", false) &gt; 0
         * StringUtils.compareIgnoreCase("a", null, true)   &gt; 0
         * StringUtils.compareIgnoreCase("a", null, false)  &lt; 0
         * StringUtils.compareIgnoreCase("abc", "abc", *)   = 0
         * StringUtils.compareIgnoreCase("abc", "ABC", *)   = 0
         * StringUtils.compareIgnoreCase("a", "b", *)       &lt; 0
         * StringUtils.compareIgnoreCase("b", "a", *)       &gt; 0
         * StringUtils.compareIgnoreCase("a", "B", *)       &lt; 0
         * StringUtils.compareIgnoreCase("A", "b", *)       &lt; 0
         * StringUtils.compareIgnoreCase("ab", "abc", *)    &lt; 0
         * </pre>
         *
         * @see String#compareToIgnoreCase(String)
         * @param str1  the String to compare from
         * @param str2  the String to compare to
         * @param nullIsLess  whether consider {@code null} value less than non-{@code null} value
         * @return &lt; 0, 0, &gt; 0, if {@code str1} is respectively less, equal ou greater than {@code str2},
         *          ignoring case differences.
         * @since 3.5
         */
        int compareIgnoreCase(String str1, String str2, boolean nullIsLess);

        /**
         * <p>Checks if CharSequence contains a search CharSequence, handling {@code null}.
         * This method uses {@link String#indexOf(String)} if possible.</p>
         *
         * <p>A {@code null} CharSequence will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.contains(null, *)     = false
         * StringUtils.contains(*, null)     = false
         * StringUtils.contains("", "")      = true
         * StringUtils.contains("abc", "")   = true
         * StringUtils.contains("abc", "a")  = true
         * StringUtils.contains("abc", "z")  = false
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchSeq  the CharSequence to find, may be null
         * @return true if the CharSequence contains the search CharSequence,
         *  false if not or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from contains(String, String) to contains(CharSequence, CharSequence)
         */
        boolean contains(CharSequence seq, CharSequence searchSeq);

        /**
         * <p>Checks if CharSequence contains a search character, handling {@code null}.
         * This method uses {@link String#indexOf(int)} if possible.</p>
         *
         * <p>A {@code null} or empty ("") CharSequence will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.contains(null, *)    = false
         * StringUtils.contains("", *)      = false
         * StringUtils.contains("abc", 'a') = true
         * StringUtils.contains("abc", 'z') = false
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchChar  the character to find
         * @return true if the CharSequence contains the search character,
         *  false if not or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from contains(String, int) to contains(CharSequence, int)
         */
        boolean contains(CharSequence seq, int searchChar);

        /**
         * <p>Checks if the CharSequence contains any character in the given
         * set of characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code false}.
         * A {@code null} or zero length search array will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.containsAny(null, *)                  = false
         * StringUtils.containsAny("", *)                    = false
         * StringUtils.containsAny(*, null)                  = false
         * StringUtils.containsAny(*, [])                    = false
         * StringUtils.containsAny("zzabyycdxx", ['z', 'a']) = true
         * StringUtils.containsAny("zzabyycdxx", ['b', 'y']) = true
         * StringUtils.containsAny("zzabyycdxx", ['z', 'y']) = true
         * StringUtils.containsAny("aba", ['z'])             = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param searchChars  the chars to search for, may be null
         * @return the {@code true} if any of the chars are found,
         * {@code false} if no match or null input
         * @since 2.4
         * @since 3.0 Changed signature from containsAny(String, char[]) to containsAny(CharSequence, char...)
         */
        boolean containsAny(CharSequence cs, char... searchChars);

        /**
         * <p>
         * Checks if the CharSequence contains any character in the given set of characters.
         * </p>
         *
         * <p>
         * A {@code null} CharSequence will return {@code false}. A {@code null} search CharSequence will return
         * {@code false}.
         * </p>
         *
         * <pre>
         * StringUtils.containsAny(null, *)               = false
         * StringUtils.containsAny("", *)                 = false
         * StringUtils.containsAny(*, null)               = false
         * StringUtils.containsAny(*, "")                 = false
         * StringUtils.containsAny("zzabyycdxx", "za")    = true
         * StringUtils.containsAny("zzabyycdxx", "by")    = true
         * StringUtils.containsAny("zzabyycdxx", "zy")    = true
         * StringUtils.containsAny("zzabyycdxx", "\tx")   = true
         * StringUtils.containsAny("zzabyycdxx", "$.#yF") = true
         * StringUtils.containsAny("aba", "z")            = false
         * </pre>
         *
         * @param cs
         *            the CharSequence to check, may be null
         * @param searchChars
         *            the chars to search for, may be null
         * @return the {@code true} if any of the chars are found, {@code false} if no match or null input
         * @since 2.4
         * @since 3.0 Changed signature from containsAny(String, String) to containsAny(CharSequence, CharSequence)
         */
        boolean containsAny(CharSequence cs, CharSequence searchChars);

        /**
         * <p>Checks if the CharSequence contains any of the CharSequences in the given array.</p>
         *
         * <p>
         * A {@code null} {@code cs} CharSequence will return {@code false}. A {@code null} or zero
         * length search array will return {@code false}.
         * </p>
         *
         * <pre>
         * StringUtils.containsAny(null, *)            = false
         * StringUtils.containsAny("", *)              = false
         * StringUtils.containsAny(*, null)            = false
         * StringUtils.containsAny(*, [])              = false
         * StringUtils.containsAny("abcd", "ab", null) = true
         * StringUtils.containsAny("abcd", "ab", "cd") = true
         * StringUtils.containsAny("abc", "d", "abc")  = true
         * </pre>
         *
         *
         * @param cs The CharSequence to check, may be null
         * @param searchCharSequences The array of CharSequences to search for, may be null.
         * Individual CharSequences may be null as well.
         * @return {@code true} if any of the search CharSequences are found, {@code false} otherwise
         * @since 3.4
         */
        boolean containsAny(CharSequence cs, CharSequence... searchCharSequences);

        /**
         * <p>Checks if CharSequence contains a search CharSequence irrespective of case,
         * handling {@code null}. Case-insensitivity is defined as by
         * {@link String#equalsIgnoreCase(String)}.
         *
         * <p>A {@code null} CharSequence will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.containsIgnoreCase(null, *) = false
         * StringUtils.containsIgnoreCase(*, null) = false
         * StringUtils.containsIgnoreCase("", "") = true
         * StringUtils.containsIgnoreCase("abc", "") = true
         * StringUtils.containsIgnoreCase("abc", "a") = true
         * StringUtils.containsIgnoreCase("abc", "z") = false
         * StringUtils.containsIgnoreCase("abc", "A") = true
         * StringUtils.containsIgnoreCase("abc", "Z") = false
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @return true if the CharSequence contains the search CharSequence irrespective of
         * case or false if not or {@code null} string input
         * @since 3.0 Changed signature from containsIgnoreCase(String, String) to containsIgnoreCase(CharSequence, CharSequence)
         */
        boolean containsIgnoreCase(CharSequence str, CharSequence searchStr);

        /**
         * <p>Checks that the CharSequence does not contain certain characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code true}.
         * A {@code null} invalid character array will return {@code true}.
         * An empty CharSequence (length()=0) always returns true.</p>
         *
         * <pre>
         * StringUtils.containsNone(null, *)       = true
         * StringUtils.containsNone(*, null)       = true
         * StringUtils.containsNone("", *)         = true
         * StringUtils.containsNone("ab", '')      = true
         * StringUtils.containsNone("abab", 'xyz') = true
         * StringUtils.containsNone("ab1", 'xyz')  = true
         * StringUtils.containsNone("abz", 'xyz')  = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param searchChars  an array of invalid chars, may be null
         * @return true if it contains none of the invalid chars, or is null
         * @since 2.0
         * @since 3.0 Changed signature from containsNone(String, char[]) to containsNone(CharSequence, char...)
         */
        boolean containsNone(CharSequence cs, char... searchChars);

        /**
         * <p>Checks that the CharSequence does not contain certain characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code true}.
         * A {@code null} invalid character array will return {@code true}.
         * An empty String ("") always returns true.</p>
         *
         * <pre>
         * StringUtils.containsNone(null, *)       = true
         * StringUtils.containsNone(*, null)       = true
         * StringUtils.containsNone("", *)         = true
         * StringUtils.containsNone("ab", "")      = true
         * StringUtils.containsNone("abab", "xyz") = true
         * StringUtils.containsNone("ab1", "xyz")  = true
         * StringUtils.containsNone("abz", "xyz")  = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param invalidChars  a String of invalid chars, may be null
         * @return true if it contains none of the invalid chars, or is null
         * @since 2.0
         * @since 3.0 Changed signature from containsNone(String, String) to containsNone(CharSequence, String)
         */
        boolean containsNone(CharSequence cs, String invalidChars);

        /**
         * <p>Checks if the CharSequence contains only certain characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code false}.
         * A {@code null} valid character array will return {@code false}.
         * An empty CharSequence (length()=0) always returns {@code true}.</p>
         *
         * <pre>
         * StringUtils.containsOnly(null, *)       = false
         * StringUtils.containsOnly(*, null)       = false
         * StringUtils.containsOnly("", *)         = true
         * StringUtils.containsOnly("ab", '')      = false
         * StringUtils.containsOnly("abab", 'abc') = true
         * StringUtils.containsOnly("ab1", 'abc')  = false
         * StringUtils.containsOnly("abz", 'abc')  = false
         * </pre>
         *
         * @param cs  the String to check, may be null
         * @param valid  an array of valid chars, may be null
         * @return true if it only contains valid chars and is non-null
         * @since 3.0 Changed signature from containsOnly(String, char[]) to containsOnly(CharSequence, char...)
         */
        boolean containsOnly(CharSequence cs, char... valid);

        /**
         * <p>Checks if the CharSequence contains only certain characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code false}.
         * A {@code null} valid character String will return {@code false}.
         * An empty String (length()=0) always returns {@code true}.</p>
         *
         * <pre>
         * StringUtils.containsOnly(null, *)       = false
         * StringUtils.containsOnly(*, null)       = false
         * StringUtils.containsOnly("", *)         = true
         * StringUtils.containsOnly("ab", "")      = false
         * StringUtils.containsOnly("abab", "abc") = true
         * StringUtils.containsOnly("ab1", "abc")  = false
         * StringUtils.containsOnly("abz", "abc")  = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param validChars  a String of valid chars, may be null
         * @return true if it only contains valid chars and is non-null
         * @since 2.0
         * @since 3.0 Changed signature from containsOnly(String, String) to containsOnly(CharSequence, String)
         */
        boolean containsOnly(CharSequence cs, String validChars);

        /**
         * <p>Check whether the given CharSequence contains any whitespace characters.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * @param seq the CharSequence to check (may be {@code null})
         * @return {@code true} if the CharSequence is not empty and
         * contains at least 1 (breaking) whitespace character
         * @since 3.0
         */
        // From org.springframework.util.StringUtils, under Apache License 2.0
        boolean containsWhitespace(CharSequence seq);

        /**
         * <p>Counts how many times the char appears in the given string.</p>
         *
         * <p>A {@code null} or empty ("") String input returns {@code 0}.</p>
         *
         * <pre>
         * StringUtils.countMatches(null, *)       = 0
         * StringUtils.countMatches("", *)         = 0
         * StringUtils.countMatches("abba", 0)  = 0
         * StringUtils.countMatches("abba", 'a')   = 2
         * StringUtils.countMatches("abba", 'b')  = 2
         * StringUtils.countMatches("abba", 'x') = 0
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param ch  the char to count
         * @return the number of occurrences, 0 if the CharSequence is {@code null}
         * @since 3.4
         */
        int countMatches(CharSequence str, char ch);

        /**
         * <p>Counts how many times the substring appears in the larger string.</p>
         *
         * <p>A {@code null} or empty ("") String input returns {@code 0}.</p>
         *
         * <pre>
         * StringUtils.countMatches(null, *)       = 0
         * StringUtils.countMatches("", *)         = 0
         * StringUtils.countMatches("abba", null)  = 0
         * StringUtils.countMatches("abba", "")    = 0
         * StringUtils.countMatches("abba", "a")   = 2
         * StringUtils.countMatches("abba", "ab")  = 1
         * StringUtils.countMatches("abba", "xxx") = 0
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param sub  the substring to count, may be null
         * @return the number of occurrences, 0 if either CharSequence is {@code null}
         * @since 3.0 Changed signature from countMatches(String, String) to countMatches(CharSequence, CharSequence)
         */
        int countMatches(CharSequence str, CharSequence sub);

        /**
         * <p>Returns either the passed in CharSequence, or if the CharSequence is
         * whitespace, empty ("") or {@code null}, the value of {@code defaultStr}.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.defaultIfBlank(null, "NULL")  = "NULL"
         * StringUtils.defaultIfBlank("", "NULL")    = "NULL"
         * StringUtils.defaultIfBlank(" ", "NULL")   = "NULL"
         * StringUtils.defaultIfBlank("bat", "NULL") = "bat"
         * StringUtils.defaultIfBlank("", null)      = null
         * </pre>
         * @param <T> the specific kind of CharSequence
         * @param str the CharSequence to check, may be null
         * @param defaultStr  the default CharSequence to return
         *  if the input is whitespace, empty ("") or {@code null}, may be null
         * @return the passed in CharSequence, or the default
         * @see org.apache.commons.lang3.StringUtils#defaultString(String, String)
         */
        <T extends CharSequence> T defaultIfBlank(T str, T defaultStr);

        /**
         * <p>Returns either the passed in CharSequence, or if the CharSequence is
         * empty or {@code null}, the value of {@code defaultStr}.</p>
         *
         * <pre>
         * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
         * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
         * StringUtils.defaultIfEmpty(" ", "NULL")   = " "
         * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
         * StringUtils.defaultIfEmpty("", null)      = null
         * </pre>
         * @param <T> the specific kind of CharSequence
         * @param str  the CharSequence to check, may be null
         * @param defaultStr  the default CharSequence to return
         *  if the input is empty ("") or {@code null}, may be null
         * @return the passed in CharSequence, or the default
         * @see org.apache.commons.lang3.StringUtils#defaultString(String, String)
         */
        <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr);

        /**
         * <p>Returns either the passed in String,
         * or if the String is {@code null}, an empty String ("").</p>
         *
         * <pre>
         * StringUtils.defaultString(null)  = ""
         * StringUtils.defaultString("")    = ""
         * StringUtils.defaultString("bat") = "bat"
         * </pre>
         *
         * @see ObjectUtils#toString(Object)
         * @see String#valueOf(Object)
         * @param str  the String to check, may be null
         * @return the passed in String, or the empty String if it
         *  was {@code null}
         */
        String defaultString(String str);

        /**
         * <p>Returns either the passed in String, or if the String is
         * {@code null}, the value of {@code defaultStr}.</p>
         *
         * <pre>
         * StringUtils.defaultString(null, "NULL")  = "NULL"
         * StringUtils.defaultString("", "NULL")    = ""
         * StringUtils.defaultString("bat", "NULL") = "bat"
         * </pre>
         *
         * @see ObjectUtils#toString(Object,String)
         * @see String#valueOf(Object)
         * @param str  the String to check, may be null
         * @param defaultStr  the default String to return
         *  if the input is {@code null}, may be null
         * @return the passed in String, or the default if it was {@code null}
         */
        String defaultString(String str, String defaultStr);

        /**
         * <p>Deletes all whitespaces from a String as defined by
         * {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.deleteWhitespace(null)         = null
         * StringUtils.deleteWhitespace("")           = ""
         * StringUtils.deleteWhitespace("abc")        = "abc"
         * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
         * </pre>
         *
         * @param str  the String to delete whitespace from, may be null
         * @return the String without whitespaces, {@code null} if null String input
         */
        String deleteWhitespace(String str);

        /**
         * <p>Compares two Strings, and returns the portion where they differ.
         * More precisely, return the remainder of the second String,
         * starting from where it's different from the first. This means that
         * the difference between "abc" and "ab" is the empty String and not "c". </p>
         *
         * <p>For example,
         * {@code difference("i am a machine", "i am a robot") -> "robot"}.</p>
         *
         * <pre>
         * StringUtils.difference(null, null) = null
         * StringUtils.difference("", "") = ""
         * StringUtils.difference("", "abc") = "abc"
         * StringUtils.difference("abc", "") = ""
         * StringUtils.difference("abc", "abc") = ""
         * StringUtils.difference("abc", "ab") = ""
         * StringUtils.difference("ab", "abxyz") = "xyz"
         * StringUtils.difference("abcde", "abxyz") = "xyz"
         * StringUtils.difference("abcde", "xyz") = "xyz"
         * </pre>
         *
         * @param str1  the first String, may be null
         * @param str2  the second String, may be null
         * @return the portion of str2 where it differs from str1; returns the
         * empty String if they are equal
         * @see #indexOfDifference(CharSequence,CharSequence)
         * @since 2.0
         */
        String difference(String str1, String str2);

        /**
         * <p>Check if a CharSequence ends with a specified suffix.</p>
         *
         * <p>{@code null}s are handled without exceptions. Two {@code null}
         * references are considered to be equal. The comparison is case sensitive.</p>
         *
         * <pre>
         * StringUtils.endsWith(null, null)      = true
         * StringUtils.endsWith(null, "def")     = false
         * StringUtils.endsWith("abcdef", null)  = false
         * StringUtils.endsWith("abcdef", "def") = true
         * StringUtils.endsWith("ABCDEF", "def") = false
         * StringUtils.endsWith("ABCDEF", "cde") = false
         * StringUtils.endsWith("ABCDEF", "")    = true
         * </pre>
         *
         * @see String#endsWith(String)
         * @param str  the CharSequence to check, may be null
         * @param suffix the suffix to find, may be null
         * @return {@code true} if the CharSequence ends with the suffix, case sensitive, or
         *  both {@code null}
         * @since 2.4
         * @since 3.0 Changed signature from endsWith(String, String) to endsWith(CharSequence, CharSequence)
         */
        boolean endsWith(CharSequence str, CharSequence suffix);

        /**
         * <p>Check if a CharSequence ends with any of the provided case-sensitive suffixes.</p>
         *
         * <pre>
         * StringUtils.endsWithAny(null, null)      = false
         * StringUtils.endsWithAny(null, new String[] {"abc"})  = false
         * StringUtils.endsWithAny("abcxyz", null)     = false
         * StringUtils.endsWithAny("abcxyz", new String[] {""}) = true
         * StringUtils.endsWithAny("abcxyz", new String[] {"xyz"}) = true
         * StringUtils.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
         * StringUtils.endsWithAny("abcXYZ", "def", "XYZ") = true
         * StringUtils.endsWithAny("abcXYZ", "def", "xyz") = false
         * </pre>
         *
         * @param sequence  the CharSequence to check, may be null
         * @param searchStrings the case-sensitive CharSequences to find, may be empty or contain {@code null}
         * @see org.apache.commons.lang3.StringUtils#endsWith(CharSequence, CharSequence)
         * @return {@code true} if the input {@code sequence} is {@code null} AND no {@code searchStrings} are provided, or
         *   the input {@code sequence} ends in any of the provided case-sensitive {@code searchStrings}.
         * @since 3.0
         */
        boolean endsWithAny(CharSequence sequence, CharSequence... searchStrings);

        /**
         * <p>Case insensitive check if a CharSequence ends with a specified suffix.</p>
         *
         * <p>{@code null}s are handled without exceptions. Two {@code null}
         * references are considered to be equal. The comparison is case insensitive.</p>
         *
         * <pre>
         * StringUtils.endsWithIgnoreCase(null, null)      = true
         * StringUtils.endsWithIgnoreCase(null, "def")     = false
         * StringUtils.endsWithIgnoreCase("abcdef", null)  = false
         * StringUtils.endsWithIgnoreCase("abcdef", "def") = true
         * StringUtils.endsWithIgnoreCase("ABCDEF", "def") = true
         * StringUtils.endsWithIgnoreCase("ABCDEF", "cde") = false
         * </pre>
         *
         * @see String#endsWith(String)
         * @param str  the CharSequence to check, may be null
         * @param suffix the suffix to find, may be null
         * @return {@code true} if the CharSequence ends with the suffix, case insensitive, or
         *  both {@code null}
         * @since 2.4
         * @since 3.0 Changed signature from endsWithIgnoreCase(String, String) to endsWithIgnoreCase(CharSequence, CharSequence)
         */
        boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix);

        /**
         * <p>Compares two CharSequences, returning {@code true} if they represent
         * equal sequences of characters.</p>
         *
         * <p>{@code null}s are handled without exceptions. Two {@code null}
         * references are considered to be equal. The comparison is <strong>case sensitive</strong>.</p>
         *
         * <pre>
         * StringUtils.equals(null, null)   = true
         * StringUtils.equals(null, "abc")  = false
         * StringUtils.equals("abc", null)  = false
         * StringUtils.equals("abc", "abc") = true
         * StringUtils.equals("abc", "ABC") = false
         * </pre>
         *
         * @param cs1  the first CharSequence, may be {@code null}
         * @param cs2  the second CharSequence, may be {@code null}
         * @return {@code true} if the CharSequences are equal (case-sensitive), or both {@code null}
         * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
         * @see Object#equals(Object)
         * @see #equalsIgnoreCase(CharSequence, CharSequence)
         */
        boolean equals(CharSequence cs1, CharSequence cs2);

        /**
         * <p>Compares given {@code string} to a CharSequences vararg of {@code searchStrings},
         * returning {@code true} if the {@code string} is equal to any of the {@code searchStrings}.</p>
         *
         * <pre>
         * StringUtils.equalsAny(null, (CharSequence[]) null) = false
         * StringUtils.equalsAny(null, null, null)    = true
         * StringUtils.equalsAny(null, "abc", "def")  = false
         * StringUtils.equalsAny("abc", null, "def")  = false
         * StringUtils.equalsAny("abc", "abc", "def") = true
         * StringUtils.equalsAny("abc", "ABC", "DEF") = false
         * </pre>
         *
         * @param string to compare, may be {@code null}.
         * @param searchStrings a vararg of strings, may be {@code null}.
         * @return {@code true} if the string is equal (case-sensitive) to any other element of {@code searchStrings};
         * {@code false} if {@code searchStrings} is null or contains no matches.
         * @since 3.5
         */
        boolean equalsAny(CharSequence string, CharSequence... searchStrings);

        /**
         * <p>Compares given {@code string} to a CharSequences vararg of {@code searchStrings},
         * returning {@code true} if the {@code string} is equal to any of the {@code searchStrings}, ignoring case.</p>
         *
         * <pre>
         * StringUtils.equalsAnyIgnoreCase(null, (CharSequence[]) null) = false
         * StringUtils.equalsAnyIgnoreCase(null, null, null)    = true
         * StringUtils.equalsAnyIgnoreCase(null, "abc", "def")  = false
         * StringUtils.equalsAnyIgnoreCase("abc", null, "def")  = false
         * StringUtils.equalsAnyIgnoreCase("abc", "abc", "def") = true
         * StringUtils.equalsAnyIgnoreCase("abc", "ABC", "DEF") = true
         * </pre>
         *
         * @param string to compare, may be {@code null}.
         * @param searchStrings a vararg of strings, may be {@code null}.
         * @return {@code true} if the string is equal (case-insensitive) to any other element of {@code searchStrings};
         * {@code false} if {@code searchStrings} is null or contains no matches.
         * @since 3.5
         */
        boolean equalsAnyIgnoreCase(CharSequence string, CharSequence... searchStrings);

        /**
         * <p>Compares two CharSequences, returning {@code true} if they represent
         * equal sequences of characters, ignoring case.</p>
         *
         * <p>{@code null}s are handled without exceptions. Two {@code null}
         * references are considered equal. The comparison is <strong>case insensitive</strong>.</p>
         *
         * <pre>
         * StringUtils.equalsIgnoreCase(null, null)   = true
         * StringUtils.equalsIgnoreCase(null, "abc")  = false
         * StringUtils.equalsIgnoreCase("abc", null)  = false
         * StringUtils.equalsIgnoreCase("abc", "abc") = true
         * StringUtils.equalsIgnoreCase("abc", "ABC") = true
         * </pre>
         *
         * @param cs1  the first CharSequence, may be {@code null}
         * @param cs2  the second CharSequence, may be {@code null}
         * @return {@code true} if the CharSequences are equal (case-insensitive), or both {@code null}
         * @since 3.0 Changed signature from equalsIgnoreCase(String, String) to equalsIgnoreCase(CharSequence, CharSequence)
         * @see #equals(CharSequence, CharSequence)
         */
        boolean equalsIgnoreCase(CharSequence cs1, CharSequence cs2);

        /**
         * <p>Returns the first value in the array which is not empty (""),
         * {@code null} or whitespace only.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>If all values are blank or the array is {@code null}
         * or empty then {@code null} is returned.</p>
         *
         * <pre>
         * StringUtils.firstNonBlank(null, null, null)     = null
         * StringUtils.firstNonBlank(null, "", " ")        = null
         * StringUtils.firstNonBlank("abc")                = "abc"
         * StringUtils.firstNonBlank(null, "xyz")          = "xyz"
         * StringUtils.firstNonBlank(null, "", " ", "xyz") = "xyz"
         * StringUtils.firstNonBlank(null, "xyz", "abc")   = "xyz"
         * StringUtils.firstNonBlank()                     = null
         * </pre>
         *
         * @param <T> the specific kind of CharSequence
         * @param values  the values to test, may be {@code null} or empty
         * @return the first value from {@code values} which is not blank,
         *  or {@code null} if there are no non-blank values
         * @since 3.8
         */
        <T extends CharSequence> T firstNonBlank(T... values);

        /**
         * <p>Returns the first value in the array which is not empty.</p>
         *
         * <p>If all values are empty or the array is {@code null}
         * or empty then {@code null} is returned.</p>
         *
         * <pre>
         * StringUtils.firstNonEmpty(null, null, null)   = null
         * StringUtils.firstNonEmpty(null, null, "")     = null
         * StringUtils.firstNonEmpty(null, "", " ")      = " "
         * StringUtils.firstNonEmpty("abc")              = "abc"
         * StringUtils.firstNonEmpty(null, "xyz")        = "xyz"
         * StringUtils.firstNonEmpty("", "xyz")          = "xyz"
         * StringUtils.firstNonEmpty(null, "xyz", "abc") = "xyz"
         * StringUtils.firstNonEmpty()                   = null
         * </pre>
         *
         * @param <T> the specific kind of CharSequence
         * @param values  the values to test, may be {@code null} or empty
         * @return the first value from {@code values} which is not empty,
         *  or {@code null} if there are no non-empty values
         * @since 3.8
         */
        <T extends CharSequence> T firstNonEmpty(T... values);

        /**
         * Calls {@link String#getBytes(Charset)} in a null-safe manner.
         *
         * @param string input string
         * @param charset The {@link Charset} to encode the {@code String}. If null, then use the default Charset.
         * @return The empty byte[] if {@code string} is null, the result of {@link String#getBytes(Charset)} otherwise.
         * @see String#getBytes(Charset)
         * @since 3.10
         */
        byte[] getBytes(String string, Charset charset);

        /**
         * Calls {@link String#getBytes(String)} in a null-safe manner.
         *
         * @param string input string
         * @param charset The {@link Charset} name to encode the {@code String}. If null, then use the default Charset.
         * @return The empty byte[] if {@code string} is null, the result of {@link String#getBytes(String)} otherwise.
         * @throws UnsupportedEncodingException Thrown when the named charset is not supported.
         * @see String#getBytes(String)
         * @since 3.10
         */
        byte[] getBytes(String string, String charset) throws UnsupportedEncodingException;

        /**
         * <p>Compares all Strings in an array and returns the initial sequence of
         * characters that is common to all of them.</p>
         *
         * <p>For example,
         * {@code getCommonPrefix(new String[] {"i am a machine", "i am a robot"}) -&gt; "i am a "}</p>
         *
         * <pre>
         * StringUtils.getCommonPrefix(null) = ""
         * StringUtils.getCommonPrefix(new String[] {}) = ""
         * StringUtils.getCommonPrefix(new String[] {"abc"}) = "abc"
         * StringUtils.getCommonPrefix(new String[] {null, null}) = ""
         * StringUtils.getCommonPrefix(new String[] {"", ""}) = ""
         * StringUtils.getCommonPrefix(new String[] {"", null}) = ""
         * StringUtils.getCommonPrefix(new String[] {"abc", null, null}) = ""
         * StringUtils.getCommonPrefix(new String[] {null, null, "abc"}) = ""
         * StringUtils.getCommonPrefix(new String[] {"", "abc"}) = ""
         * StringUtils.getCommonPrefix(new String[] {"abc", ""}) = ""
         * StringUtils.getCommonPrefix(new String[] {"abc", "abc"}) = "abc"
         * StringUtils.getCommonPrefix(new String[] {"abc", "a"}) = "a"
         * StringUtils.getCommonPrefix(new String[] {"ab", "abxyz"}) = "ab"
         * StringUtils.getCommonPrefix(new String[] {"abcde", "abxyz"}) = "ab"
         * StringUtils.getCommonPrefix(new String[] {"abcde", "xyz"}) = ""
         * StringUtils.getCommonPrefix(new String[] {"xyz", "abcde"}) = ""
         * StringUtils.getCommonPrefix(new String[] {"i am a machine", "i am a robot"}) = "i am a "
         * </pre>
         *
         * @param strs  array of String objects, entries may be null
         * @return the initial sequence of characters that are common to all Strings
         * in the array; empty String if the array is null, the elements are all null
         * or if there is no common prefix.
         * @since 2.4
         */
        String getCommonPrefix(String... strs);

        /**
         * <p>Checks if a String {@code str} contains Unicode digits,
         * if yes then concatenate all the digits in {@code str} and return it as a String.</p>
         *
         * <p>An empty ("") String will be returned if no digits found in {@code str}.</p>
         *
         * <pre>
         * StringUtils.getDigits(null)  = null
         * StringUtils.getDigits("")    = ""
         * StringUtils.getDigits("abc") = ""
         * StringUtils.getDigits("1000$") = "1000"
         * StringUtils.getDigits("1123~45") = "112345"
         * StringUtils.getDigits("(541) 754-3010") = "5417543010"
         * StringUtils.getDigits("\u0967\u0968\u0969") = "\u0967\u0968\u0969"
         * </pre>
         *
         * @param str the String to extract digits from, may be null
         * @return String with only digits,
         *           or an empty ("") String if no digits found,
         *           or {@code null} String if {@code str} is null
         * @since 3.6
         */
        String getDigits(String str);

        /**
         * <p>Find the Fuzzy Distance which indicates the similarity score between two Strings.</p>
         *
         * <p>This string matching algorithm is similar to the algorithms of editors such as Sublime Text,
         * TextMate, Atom and others. One point is given for every matched character. Subsequent
         * matches yield two bonus points. A higher score indicates a higher similarity.</p>
         *
         * <pre>
         * StringUtils.getFuzzyDistance(null, null, null)                                    = IllegalArgumentException
         * StringUtils.getFuzzyDistance("", "", Locale.ENGLISH)                              = 0
         * StringUtils.getFuzzyDistance("Workshop", "b", Locale.ENGLISH)                     = 0
         * StringUtils.getFuzzyDistance("Room", "o", Locale.ENGLISH)                         = 1
         * StringUtils.getFuzzyDistance("Workshop", "w", Locale.ENGLISH)                     = 1
         * StringUtils.getFuzzyDistance("Workshop", "ws", Locale.ENGLISH)                    = 2
         * StringUtils.getFuzzyDistance("Workshop", "wo", Locale.ENGLISH)                    = 4
         * StringUtils.getFuzzyDistance("Apache Software Foundation", "asf", Locale.ENGLISH) = 3
         * </pre>
         *
         * @param term a full term that should be matched against, must not be null
         * @param query the query that will be matched against a term, must not be null
         * @param locale This string matching logic is case insensitive. A locale is necessary to normalize
         *  both Strings to lower case.
         * @return result score
         * @throws IllegalArgumentException if either String input {@code null} or Locale input {@code null}
         * @since 3.4
         * @deprecated as of 3.6, use commons-text
         * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/similarity/FuzzyScore.html">
         * FuzzyScore</a> instead
         */
        @Deprecated
        int getFuzzyDistance(CharSequence term, CharSequence query, Locale locale);

        /**
         * <p>Returns either the passed in CharSequence, or if the CharSequence is
         * whitespace, empty ("") or {@code null}, the value supplied by {@code defaultStrSupplier}.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>Caller responsible for thread-safety and exception handling of default value supplier</p>
         *
         * <pre>
         * {@code
         * StringUtils.getIfBlank(null, () -> "NULL")   = "NULL"
         * StringUtils.getIfBlank("", () -> "NULL")     = "NULL"
         * StringUtils.getIfBlank(" ", () -> "NULL")    = "NULL"
         * StringUtils.getIfBlank("bat", () -> "NULL")  = "bat"
         * StringUtils.getIfBlank("", () -> null)       = null
         * StringUtils.getIfBlank("", null)             = null
         * }</pre>
         * @param <T> the specific kind of CharSequence
         * @param str the CharSequence to check, may be null
         * @param defaultSupplier the supplier of default CharSequence to return
         *  if the input is whitespace, empty ("") or {@code null}, may be null
         * @return the passed in CharSequence, or the default
         * @see org.apache.commons.lang3.StringUtils#defaultString(String, String)
         * @since 3.10
         */
        <T extends CharSequence> T getIfBlank(T str, Supplier<T> defaultSupplier);

        /**
         * <p>Returns either the passed in CharSequence, or if the CharSequence is
         * empty or {@code null}, the value supplied by {@code defaultStrSupplier}.</p>
         *
         * <p>Caller responsible for thread-safety and exception handling of default value supplier</p>
         *
         * <pre>
         * {@code
         * StringUtils.getIfEmpty(null, () -> "NULL")    = "NULL"
         * StringUtils.getIfEmpty("", () -> "NULL")      = "NULL"
         * StringUtils.getIfEmpty(" ", () -> "NULL")     = " "
         * StringUtils.getIfEmpty("bat", () -> "NULL")   = "bat"
         * StringUtils.getIfEmpty("", () -> null)        = null
         * StringUtils.getIfEmpty("", null)              = null
         * }
         * </pre>
         * @param <T> the specific kind of CharSequence
         * @param str  the CharSequence to check, may be null
         * @param defaultSupplier  the supplier of default CharSequence to return
         *  if the input is empty ("") or {@code null}, may be null
         * @return the passed in CharSequence, or the default
         * @see org.apache.commons.lang3.StringUtils#defaultString(String, String)
         * @since 3.10
         */
        <T extends CharSequence> T getIfEmpty(T str, Supplier<T> defaultSupplier);

        /**
         * <p>Find the Jaro Winkler Distance which indicates the similarity score between two Strings.</p>
         *
         * <p>The Jaro measure is the weighted sum of percentage of matched characters from each file and transposed characters.
         * Winkler increased this measure for matching initial characters.</p>
         *
         * <p>This implementation is based on the Jaro Winkler similarity algorithm
         * from <a href="http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance">http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance</a>.</p>
         *
         * <pre>
         * StringUtils.getJaroWinklerDistance(null, null)          = IllegalArgumentException
         * StringUtils.getJaroWinklerDistance("", "")              = 0.0
         * StringUtils.getJaroWinklerDistance("", "a")             = 0.0
         * StringUtils.getJaroWinklerDistance("aaapppp", "")       = 0.0
         * StringUtils.getJaroWinklerDistance("frog", "fog")       = 0.93
         * StringUtils.getJaroWinklerDistance("fly", "ant")        = 0.0
         * StringUtils.getJaroWinklerDistance("elephant", "hippo") = 0.44
         * StringUtils.getJaroWinklerDistance("hippo", "elephant") = 0.44
         * StringUtils.getJaroWinklerDistance("hippo", "zzzzzzzz") = 0.0
         * StringUtils.getJaroWinklerDistance("hello", "hallo")    = 0.88
         * StringUtils.getJaroWinklerDistance("ABC Corporation", "ABC Corp") = 0.93
         * StringUtils.getJaroWinklerDistance("D N H Enterprises Inc", "D &amp; H Enterprises, Inc.") = 0.95
         * StringUtils.getJaroWinklerDistance("My Gym Children's Fitness Center", "My Gym. Childrens Fitness") = 0.92
         * StringUtils.getJaroWinklerDistance("PENNSYLVANIA", "PENNCISYLVNIA") = 0.88
         * </pre>
         *
         * @param first the first String, must not be null
         * @param second the second String, must not be null
         * @return result distance
         * @throws IllegalArgumentException if either String input {@code null}
         * @since 3.3
         * @deprecated as of 3.6, use commons-text
         * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/similarity/JaroWinklerDistance.html">
         * JaroWinklerDistance</a> instead
         */
        @Deprecated
        double getJaroWinklerDistance(CharSequence first, CharSequence second);

        /**
         * <p>Find the Levenshtein distance between two Strings.</p>
         *
         * <p>This is the number of changes needed to change one String into
         * another, where each change is a single character modification (deletion,
         * insertion or substitution).</p>
         *
         * <p>The implementation uses a single-dimensional array of length s.length() + 1. See
         * <a href="http://blog.softwx.net/2014/12/optimizing-levenshtein-algorithm-in-c.html">
         * http://blog.softwx.net/2014/12/optimizing-levenshtein-algorithm-in-c.html</a> for details.</p>
         *
         * <pre>
         * StringUtils.getLevenshteinDistance(null, *)             = IllegalArgumentException
         * StringUtils.getLevenshteinDistance(*, null)             = IllegalArgumentException
         * StringUtils.getLevenshteinDistance("", "")              = 0
         * StringUtils.getLevenshteinDistance("", "a")             = 1
         * StringUtils.getLevenshteinDistance("aaapppp", "")       = 7
         * StringUtils.getLevenshteinDistance("frog", "fog")       = 1
         * StringUtils.getLevenshteinDistance("fly", "ant")        = 3
         * StringUtils.getLevenshteinDistance("elephant", "hippo") = 7
         * StringUtils.getLevenshteinDistance("hippo", "elephant") = 7
         * StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz") = 8
         * StringUtils.getLevenshteinDistance("hello", "hallo")    = 1
         * </pre>
         *
         * @param s  the first String, must not be null
         * @param t  the second String, must not be null
         * @return result distance
         * @throws IllegalArgumentException if either String input {@code null}
         * @since 3.0 Changed signature from getLevenshteinDistance(String, String) to
         * getLevenshteinDistance(CharSequence, CharSequence)
         * @deprecated as of 3.6, use commons-text
         * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/similarity/LevenshteinDistance.html">
         * LevenshteinDistance</a> instead
         */
        @Deprecated
        int getLevenshteinDistance(CharSequence s, CharSequence t);

        /**
         * <p>Find the Levenshtein distance between two Strings if it's less than or equal to a given
         * threshold.</p>
         *
         * <p>This is the number of changes needed to change one String into
         * another, where each change is a single character modification (deletion,
         * insertion or substitution).</p>
         *
         * <p>This implementation follows from Algorithms on Strings, Trees and Sequences by Dan Gusfield
         * and Chas Emerick's implementation of the Levenshtein distance algorithm from
         * <a href="http://www.merriampark.com/ld.htm">http://www.merriampark.com/ld.htm</a></p>
         *
         * <pre>
         * StringUtils.getLevenshteinDistance(null, *, *)             = IllegalArgumentException
         * StringUtils.getLevenshteinDistance(*, null, *)             = IllegalArgumentException
         * StringUtils.getLevenshteinDistance(*, *, -1)               = IllegalArgumentException
         * StringUtils.getLevenshteinDistance("", "", 0)              = 0
         * StringUtils.getLevenshteinDistance("aaapppp", "", 8)       = 7
         * StringUtils.getLevenshteinDistance("aaapppp", "", 7)       = 7
         * StringUtils.getLevenshteinDistance("aaapppp", "", 6))      = -1
         * StringUtils.getLevenshteinDistance("elephant", "hippo", 7) = 7
         * StringUtils.getLevenshteinDistance("elephant", "hippo", 6) = -1
         * StringUtils.getLevenshteinDistance("hippo", "elephant", 7) = 7
         * StringUtils.getLevenshteinDistance("hippo", "elephant", 6) = -1
         * </pre>
         *
         * @param s  the first String, must not be null
         * @param t  the second String, must not be null
         * @param threshold the target threshold, must not be negative
         * @return result distance, or {@code -1} if the distance would be greater than the threshold
         * @throws IllegalArgumentException if either String input {@code null} or negative threshold
         * @deprecated as of 3.6, use commons-text
         * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/similarity/LevenshteinDistance.html">
         * LevenshteinDistance</a> instead
         */
        @Deprecated
        int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold);

        /**
         * <p>Finds the first index within a CharSequence, handling {@code null}.
         * This method uses {@link String#indexOf(String, int)} if possible.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.indexOf(null, *)          = -1
         * StringUtils.indexOf(*, null)          = -1
         * StringUtils.indexOf("", "")           = 0
         * StringUtils.indexOf("", *)            = -1 (except when * = "")
         * StringUtils.indexOf("aabaabaa", "a")  = 0
         * StringUtils.indexOf("aabaabaa", "b")  = 2
         * StringUtils.indexOf("aabaabaa", "ab") = 1
         * StringUtils.indexOf("aabaabaa", "")   = 0
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchSeq  the CharSequence to find, may be null
         * @return the first index of the search CharSequence,
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from indexOf(String, String) to indexOf(CharSequence, CharSequence)
         */
        int indexOf(CharSequence seq, CharSequence searchSeq);

        /**
         * <p>Finds the first index within a CharSequence, handling {@code null}.
         * This method uses {@link String#indexOf(String, int)} if possible.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A negative start position is treated as zero.
         * An empty ("") search CharSequence always matches.
         * A start position greater than the string length only matches
         * an empty search CharSequence.</p>
         *
         * <pre>
         * StringUtils.indexOf(null, *, *)          = -1
         * StringUtils.indexOf(*, null, *)          = -1
         * StringUtils.indexOf("", "", 0)           = 0
         * StringUtils.indexOf("", *, 0)            = -1 (except when * = "")
         * StringUtils.indexOf("aabaabaa", "a", 0)  = 0
         * StringUtils.indexOf("aabaabaa", "b", 0)  = 2
         * StringUtils.indexOf("aabaabaa", "ab", 0) = 1
         * StringUtils.indexOf("aabaabaa", "b", 3)  = 5
         * StringUtils.indexOf("aabaabaa", "b", 9)  = -1
         * StringUtils.indexOf("aabaabaa", "b", -1) = 2
         * StringUtils.indexOf("aabaabaa", "", 2)   = 2
         * StringUtils.indexOf("abc", "", 9)        = 3
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchSeq  the CharSequence to find, may be null
         * @param startPos  the start position, negative treated as zero
         * @return the first index of the search CharSequence (always &ge; startPos),
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from indexOf(String, String, int) to indexOf(CharSequence, CharSequence, int)
         */
        int indexOf(CharSequence seq, CharSequence searchSeq, int startPos);

        /**
         * Returns the index within {@code seq} of the first occurrence of
         * the specified character. If a character with value
         * {@code searchChar} occurs in the character sequence represented by
         * {@code seq} {@code CharSequence} object, then the index (in Unicode
         * code units) of the first such occurrence is returned. For
         * values of {@code searchChar} in the range from 0 to 0xFFFF
         * (inclusive), this is the smallest value <i>k</i> such that:
         * <blockquote><pre>
         * this.charAt(<i>k</i>) == searchChar
         * </pre></blockquote>
         * is true. For other values of {@code searchChar}, it is the
         * smallest value <i>k</i> such that:
         * <blockquote><pre>
         * this.codePointAt(<i>k</i>) == searchChar
         * </pre></blockquote>
         * is true. In either case, if no such character occurs in {@code seq},
         * then {@code INDEX_NOT_FOUND (-1)} is returned.
         *
         * <p>Furthermore, a {@code null} or empty ("") CharSequence will
         * return {@code INDEX_NOT_FOUND (-1)}.</p>
         *
         * <pre>
         * StringUtils.indexOf(null, *)         = -1
         * StringUtils.indexOf("", *)           = -1
         * StringUtils.indexOf("aabaabaa", 'a') = 0
         * StringUtils.indexOf("aabaabaa", 'b') = 2
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchChar  the character to find
         * @return the first index of the search character,
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from indexOf(String, int) to indexOf(CharSequence, int)
         * @since 3.6 Updated {@link CharSequenceUtils} call to behave more like {@code String}
         */
        int indexOf(CharSequence seq, int searchChar);

        /**
         *
         * Returns the index within {@code seq} of the first occurrence of the
         * specified character, starting the search at the specified index.
         * <p>
         * If a character with value {@code searchChar} occurs in the
         * character sequence represented by the {@code seq} {@code CharSequence}
         * object at an index no smaller than {@code startPos}, then
         * the index of the first such occurrence is returned. For values
         * of {@code searchChar} in the range from 0 to 0xFFFF (inclusive),
         * this is the smallest value <i>k</i> such that:
         * <blockquote><pre>
         * (this.charAt(<i>k</i>) == searchChar) &amp;&amp; (<i>k</i> &gt;= startPos)
         * </pre></blockquote>
         * is true. For other values of {@code searchChar}, it is the
         * smallest value <i>k</i> such that:
         * <blockquote><pre>
         * (this.codePointAt(<i>k</i>) == searchChar) &amp;&amp; (<i>k</i> &gt;= startPos)
         * </pre></blockquote>
         * is true. In either case, if no such character occurs in {@code seq}
         * at or after position {@code startPos}, then
         * {@code -1} is returned.
         *
         * <p>
         * There is no restriction on the value of {@code startPos}. If it
         * is negative, it has the same effect as if it were zero: this entire
         * string may be searched. If it is greater than the length of this
         * string, it has the same effect as if it were equal to the length of
         * this string: {@code (INDEX_NOT_FOUND) -1} is returned. Furthermore, a
         * {@code null} or empty ("") CharSequence will
         * return {@code (INDEX_NOT_FOUND) -1}.
         *
         * <p>All indices are specified in {@code char} values
         * (Unicode code units).
         *
         * <pre>
         * StringUtils.indexOf(null, *, *)          = -1
         * StringUtils.indexOf("", *, *)            = -1
         * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
         * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
         * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
         * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchChar  the character to find
         * @param startPos  the start position, negative treated as zero
         * @return the first index of the search character (always &ge; startPos),
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from indexOf(String, int, int) to indexOf(CharSequence, int, int)
         * @since 3.6 Updated {@link CharSequenceUtils} call to behave more like {@code String}
         */
        int indexOf(CharSequence seq, int searchChar, int startPos);

        /**
         * <p>Search a CharSequence to find the first index of any
         * character in the given set of characters.</p>
         *
         * <p>A {@code null} String will return {@code -1}.
         * A {@code null} or zero length search array will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.indexOfAny(null, *)                  = -1
         * StringUtils.indexOfAny("", *)                    = -1
         * StringUtils.indexOfAny(*, null)                  = -1
         * StringUtils.indexOfAny(*, [])                    = -1
         * StringUtils.indexOfAny("zzabyycdxx", ['z', 'a']) = 0
         * StringUtils.indexOfAny("zzabyycdxx", ['b', 'y']) = 3
         * StringUtils.indexOfAny("aba", ['z'])             = -1
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param searchChars  the chars to search for, may be null
         * @return the index of any of the chars, -1 if no match or null input
         * @since 2.0
         * @since 3.0 Changed signature from indexOfAny(String, char[]) to indexOfAny(CharSequence, char...)
         */
        int indexOfAny(CharSequence cs, char... searchChars);

        /**
         * <p>Find the first index of any of a set of potential substrings.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A {@code null} or zero length search array will return {@code -1}.
         * A {@code null} search array entry will be ignored, but a search
         * array containing "" will return {@code 0} if {@code str} is not
         * null. This method uses {@link String#indexOf(String)} if possible.</p>
         *
         * <pre>
         * StringUtils.indexOfAny(null, *)                      = -1
         * StringUtils.indexOfAny(*, null)                      = -1
         * StringUtils.indexOfAny(*, [])                        = -1
         * StringUtils.indexOfAny("zzabyycdxx", ["ab", "cd"])   = 2
         * StringUtils.indexOfAny("zzabyycdxx", ["cd", "ab"])   = 2
         * StringUtils.indexOfAny("zzabyycdxx", ["mn", "op"])   = -1
         * StringUtils.indexOfAny("zzabyycdxx", ["zab", "aby"]) = 1
         * StringUtils.indexOfAny("zzabyycdxx", [""])           = 0
         * StringUtils.indexOfAny("", [""])                     = 0
         * StringUtils.indexOfAny("", ["a"])                    = -1
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStrs  the CharSequences to search for, may be null
         * @return the first index of any of the searchStrs in str, -1 if no match
         * @since 3.0 Changed signature from indexOfAny(String, String[]) to indexOfAny(CharSequence, CharSequence...)
         */
        int indexOfAny(CharSequence str, CharSequence... searchStrs);

        /**
         * <p>Search a CharSequence to find the first index of any
         * character in the given set of characters.</p>
         *
         * <p>A {@code null} String will return {@code -1}.
         * A {@code null} search string will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.indexOfAny(null, *)            = -1
         * StringUtils.indexOfAny("", *)              = -1
         * StringUtils.indexOfAny(*, null)            = -1
         * StringUtils.indexOfAny(*, "")              = -1
         * StringUtils.indexOfAny("zzabyycdxx", "za") = 0
         * StringUtils.indexOfAny("zzabyycdxx", "by") = 3
         * StringUtils.indexOfAny("aba", "z")         = -1
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param searchChars  the chars to search for, may be null
         * @return the index of any of the chars, -1 if no match or null input
         * @since 2.0
         * @since 3.0 Changed signature from indexOfAny(String, String) to indexOfAny(CharSequence, String)
         */
        int indexOfAny(CharSequence cs, String searchChars);

        /**
         * <p>Searches a CharSequence to find the first index of any
         * character not in the given set of characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A {@code null} or zero length search array will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.indexOfAnyBut(null, *)                              = -1
         * StringUtils.indexOfAnyBut("", *)                                = -1
         * StringUtils.indexOfAnyBut(*, null)                              = -1
         * StringUtils.indexOfAnyBut(*, [])                                = -1
         * StringUtils.indexOfAnyBut("zzabyycdxx", new char[] {'z', 'a'} ) = 3
         * StringUtils.indexOfAnyBut("aba", new char[] {'z'} )             = 0
         * StringUtils.indexOfAnyBut("aba", new char[] {'a', 'b'} )        = -1

         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @param searchChars  the chars to search for, may be null
         * @return the index of any of the chars, -1 if no match or null input
         * @since 2.0
         * @since 3.0 Changed signature from indexOfAnyBut(String, char[]) to indexOfAnyBut(CharSequence, char...)
         */
        int indexOfAnyBut(CharSequence cs, char... searchChars);

        /**
         * <p>Search a CharSequence to find the first index of any
         * character not in the given set of characters.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A {@code null} or empty search string will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.indexOfAnyBut(null, *)            = -1
         * StringUtils.indexOfAnyBut("", *)              = -1
         * StringUtils.indexOfAnyBut(*, null)            = -1
         * StringUtils.indexOfAnyBut(*, "")              = -1
         * StringUtils.indexOfAnyBut("zzabyycdxx", "za") = 3
         * StringUtils.indexOfAnyBut("zzabyycdxx", "")   = -1
         * StringUtils.indexOfAnyBut("aba", "ab")        = -1
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchChars  the chars to search for, may be null
         * @return the index of any of the chars, -1 if no match or null input
         * @since 2.0
         * @since 3.0 Changed signature from indexOfAnyBut(String, String) to indexOfAnyBut(CharSequence, CharSequence)
         */
        int indexOfAnyBut(CharSequence seq, CharSequence searchChars);

        /**
         * <p>Compares all CharSequences in an array and returns the index at which the
         * CharSequences begin to differ.</p>
         *
         * <p>For example,
         * {@code indexOfDifference(new String[] {"i am a machine", "i am a robot"}) -> 7}</p>
         *
         * <pre>
         * StringUtils.indexOfDifference(null) = -1
         * StringUtils.indexOfDifference(new String[] {}) = -1
         * StringUtils.indexOfDifference(new String[] {"abc"}) = -1
         * StringUtils.indexOfDifference(new String[] {null, null}) = -1
         * StringUtils.indexOfDifference(new String[] {"", ""}) = -1
         * StringUtils.indexOfDifference(new String[] {"", null}) = 0
         * StringUtils.indexOfDifference(new String[] {"abc", null, null}) = 0
         * StringUtils.indexOfDifference(new String[] {null, null, "abc"}) = 0
         * StringUtils.indexOfDifference(new String[] {"", "abc"}) = 0
         * StringUtils.indexOfDifference(new String[] {"abc", ""}) = 0
         * StringUtils.indexOfDifference(new String[] {"abc", "abc"}) = -1
         * StringUtils.indexOfDifference(new String[] {"abc", "a"}) = 1
         * StringUtils.indexOfDifference(new String[] {"ab", "abxyz"}) = 2
         * StringUtils.indexOfDifference(new String[] {"abcde", "abxyz"}) = 2
         * StringUtils.indexOfDifference(new String[] {"abcde", "xyz"}) = 0
         * StringUtils.indexOfDifference(new String[] {"xyz", "abcde"}) = 0
         * StringUtils.indexOfDifference(new String[] {"i am a machine", "i am a robot"}) = 7
         * </pre>
         *
         * @param css  array of CharSequences, entries may be null
         * @return the index where the strings begin to differ; -1 if they are all equal
         * @since 2.4
         * @since 3.0 Changed signature from indexOfDifference(String...) to indexOfDifference(CharSequence...)
         */
        int indexOfDifference(CharSequence... css);

        /**
         * <p>Compares two CharSequences, and returns the index at which the
         * CharSequences begin to differ.</p>
         *
         * <p>For example,
         * {@code indexOfDifference("i am a machine", "i am a robot") -> 7}</p>
         *
         * <pre>
         * StringUtils.indexOfDifference(null, null) = -1
         * StringUtils.indexOfDifference("", "") = -1
         * StringUtils.indexOfDifference("", "abc") = 0
         * StringUtils.indexOfDifference("abc", "") = 0
         * StringUtils.indexOfDifference("abc", "abc") = -1
         * StringUtils.indexOfDifference("ab", "abxyz") = 2
         * StringUtils.indexOfDifference("abcde", "abxyz") = 2
         * StringUtils.indexOfDifference("abcde", "xyz") = 0
         * </pre>
         *
         * @param cs1  the first CharSequence, may be null
         * @param cs2  the second CharSequence, may be null
         * @return the index where cs1 and cs2 begin to differ; -1 if they are equal
         * @since 2.0
         * @since 3.0 Changed signature from indexOfDifference(String, String) to
         * indexOfDifference(CharSequence, CharSequence)
         */
        int indexOfDifference(CharSequence cs1, CharSequence cs2);

        /**
         * <p>Case in-sensitive find of the first index within a CharSequence.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A negative start position is treated as zero.
         * An empty ("") search CharSequence always matches.
         * A start position greater than the string length only matches
         * an empty search CharSequence.</p>
         *
         * <pre>
         * StringUtils.indexOfIgnoreCase(null, *)          = -1
         * StringUtils.indexOfIgnoreCase(*, null)          = -1
         * StringUtils.indexOfIgnoreCase("", "")           = 0
         * StringUtils.indexOfIgnoreCase("aabaabaa", "a")  = 0
         * StringUtils.indexOfIgnoreCase("aabaabaa", "b")  = 2
         * StringUtils.indexOfIgnoreCase("aabaabaa", "ab") = 1
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @return the first index of the search CharSequence,
         *  -1 if no match or {@code null} string input
         * @since 2.5
         * @since 3.0 Changed signature from indexOfIgnoreCase(String, String) to indexOfIgnoreCase(CharSequence, CharSequence)
         */
        int indexOfIgnoreCase(CharSequence str, CharSequence searchStr);

        /**
         * <p>Case in-sensitive find of the first index within a CharSequence
         * from the specified position.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A negative start position is treated as zero.
         * An empty ("") search CharSequence always matches.
         * A start position greater than the string length only matches
         * an empty search CharSequence.</p>
         *
         * <pre>
         * StringUtils.indexOfIgnoreCase(null, *, *)          = -1
         * StringUtils.indexOfIgnoreCase(*, null, *)          = -1
         * StringUtils.indexOfIgnoreCase("", "", 0)           = 0
         * StringUtils.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
         * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
         * StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
         * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
         * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
         * StringUtils.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
         * StringUtils.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
         * StringUtils.indexOfIgnoreCase("abc", "", 9)        = -1
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @param startPos  the start position, negative treated as zero
         * @return the first index of the search CharSequence (always &ge; startPos),
         *  -1 if no match or {@code null} string input
         * @since 2.5
         * @since 3.0 Changed signature from indexOfIgnoreCase(String, String, int) to indexOfIgnoreCase(CharSequence, CharSequence, int)
         */
        int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos);

        /**
         * <p>Checks if all of the CharSequences are empty (""), null or whitespace only.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.isAllBlank(null)             = true
         * StringUtils.isAllBlank(null, "foo")      = false
         * StringUtils.isAllBlank(null, null)       = true
         * StringUtils.isAllBlank("", "bar")        = false
         * StringUtils.isAllBlank("bob", "")        = false
         * StringUtils.isAllBlank("  bob  ", null)  = false
         * StringUtils.isAllBlank(" ", "bar")       = false
         * StringUtils.isAllBlank("foo", "bar")     = false
         * StringUtils.isAllBlank(new String[] {})  = true
         * </pre>
         *
         * @param css  the CharSequences to check, may be null or empty
         * @return {@code true} if all of the CharSequences are empty or null or whitespace only
         * @since 3.6
         */
        boolean isAllBlank(CharSequence... css);

        /**
         * <p>Checks if all of the CharSequences are empty ("") or null.</p>
         *
         * <pre>
         * StringUtils.isAllEmpty(null)             = true
         * StringUtils.isAllEmpty(null, "")         = true
         * StringUtils.isAllEmpty(new String[] {})  = true
         * StringUtils.isAllEmpty(null, "foo")      = false
         * StringUtils.isAllEmpty("", "bar")        = false
         * StringUtils.isAllEmpty("bob", "")        = false
         * StringUtils.isAllEmpty("  bob  ", null)  = false
         * StringUtils.isAllEmpty(" ", "bar")       = false
         * StringUtils.isAllEmpty("foo", "bar")     = false
         * </pre>
         *
         * @param css  the CharSequences to check, may be null or empty
         * @return {@code true} if all of the CharSequences are empty or null
         * @since 3.6
         */
        boolean isAllEmpty(CharSequence... css);

        /**
         * <p>Checks if the CharSequence contains only lowercase characters.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.isAllLowerCase(null)   = false
         * StringUtils.isAllLowerCase("")     = false
         * StringUtils.isAllLowerCase("  ")   = false
         * StringUtils.isAllLowerCase("abc")  = true
         * StringUtils.isAllLowerCase("abC")  = false
         * StringUtils.isAllLowerCase("ab c") = false
         * StringUtils.isAllLowerCase("ab1c") = false
         * StringUtils.isAllLowerCase("ab/c") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains lowercase characters, and is non-null
         * @since 2.5
         * @since 3.0 Changed signature from isAllLowerCase(String) to isAllLowerCase(CharSequence)
         */
        boolean isAllLowerCase(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only uppercase characters.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty String (length()=0) will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.isAllUpperCase(null)   = false
         * StringUtils.isAllUpperCase("")     = false
         * StringUtils.isAllUpperCase("  ")   = false
         * StringUtils.isAllUpperCase("ABC")  = true
         * StringUtils.isAllUpperCase("aBC")  = false
         * StringUtils.isAllUpperCase("A C")  = false
         * StringUtils.isAllUpperCase("A1C")  = false
         * StringUtils.isAllUpperCase("A/C")  = false
         * </pre>
         *
         * @param cs the CharSequence to check, may be null
         * @return {@code true} if only contains uppercase characters, and is non-null
         * @since 2.5
         * @since 3.0 Changed signature from isAllUpperCase(String) to isAllUpperCase(CharSequence)
         */
        boolean isAllUpperCase(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only Unicode letters.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.isAlpha(null)   = false
         * StringUtils.isAlpha("")     = false
         * StringUtils.isAlpha("  ")   = false
         * StringUtils.isAlpha("abc")  = true
         * StringUtils.isAlpha("ab2c") = false
         * StringUtils.isAlpha("ab-c") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains letters, and is non-null
         * @since 3.0 Changed signature from isAlpha(String) to isAlpha(CharSequence)
         * @since 3.0 Changed "" to return false and not true
         */
        boolean isAlpha(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only Unicode letters or digits.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code false}.</p>
         *
         * <pre>
         * StringUtils.isAlphanumeric(null)   = false
         * StringUtils.isAlphanumeric("")     = false
         * StringUtils.isAlphanumeric("  ")   = false
         * StringUtils.isAlphanumeric("abc")  = true
         * StringUtils.isAlphanumeric("ab c") = false
         * StringUtils.isAlphanumeric("ab2c") = true
         * StringUtils.isAlphanumeric("ab-c") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains letters or digits,
         *  and is non-null
         * @since 3.0 Changed signature from isAlphanumeric(String) to isAlphanumeric(CharSequence)
         * @since 3.0 Changed "" to return false and not true
         */
        boolean isAlphanumeric(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only Unicode letters, digits
         * or space ({@code ' '}).</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code true}.</p>
         *
         * <pre>
         * StringUtils.isAlphanumericSpace(null)   = false
         * StringUtils.isAlphanumericSpace("")     = true
         * StringUtils.isAlphanumericSpace("  ")   = true
         * StringUtils.isAlphanumericSpace("abc")  = true
         * StringUtils.isAlphanumericSpace("ab c") = true
         * StringUtils.isAlphanumericSpace("ab2c") = true
         * StringUtils.isAlphanumericSpace("ab-c") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains letters, digits or space,
         *  and is non-null
         * @since 3.0 Changed signature from isAlphanumericSpace(String) to isAlphanumericSpace(CharSequence)
         */
        boolean isAlphanumericSpace(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only Unicode letters and
         * space (' ').</p>
         *
         * <p>{@code null} will return {@code false}
         * An empty CharSequence (length()=0) will return {@code true}.</p>
         *
         * <pre>
         * StringUtils.isAlphaSpace(null)   = false
         * StringUtils.isAlphaSpace("")     = true
         * StringUtils.isAlphaSpace("  ")   = true
         * StringUtils.isAlphaSpace("abc")  = true
         * StringUtils.isAlphaSpace("ab c") = true
         * StringUtils.isAlphaSpace("ab2c") = false
         * StringUtils.isAlphaSpace("ab-c") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains letters and space,
         *  and is non-null
         * @since 3.0 Changed signature from isAlphaSpace(String) to isAlphaSpace(CharSequence)
         */
        boolean isAlphaSpace(CharSequence cs);

        /**
         * <p>Checks if any of the CharSequences are empty ("") or null or whitespace only.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.isAnyBlank((String) null)    = true
         * StringUtils.isAnyBlank((String[]) null)  = false
         * StringUtils.isAnyBlank(null, "foo")      = true
         * StringUtils.isAnyBlank(null, null)       = true
         * StringUtils.isAnyBlank("", "bar")        = true
         * StringUtils.isAnyBlank("bob", "")        = true
         * StringUtils.isAnyBlank("  bob  ", null)  = true
         * StringUtils.isAnyBlank(" ", "bar")       = true
         * StringUtils.isAnyBlank(new String[] {})  = false
         * StringUtils.isAnyBlank(new String[]{""}) = true
         * StringUtils.isAnyBlank("foo", "bar")     = false
         * </pre>
         *
         * @param css  the CharSequences to check, may be null or empty
         * @return {@code true} if any of the CharSequences are empty or null or whitespace only
         * @since 3.2
         */
        boolean isAnyBlank(CharSequence... css);

        /**
         * <p>Checks if any of the CharSequences are empty ("") or null.</p>
         *
         * <pre>
         * StringUtils.isAnyEmpty((String) null)    = true
         * StringUtils.isAnyEmpty((String[]) null)  = false
         * StringUtils.isAnyEmpty(null, "foo")      = true
         * StringUtils.isAnyEmpty("", "bar")        = true
         * StringUtils.isAnyEmpty("bob", "")        = true
         * StringUtils.isAnyEmpty("  bob  ", null)  = true
         * StringUtils.isAnyEmpty(" ", "bar")       = false
         * StringUtils.isAnyEmpty("foo", "bar")     = false
         * StringUtils.isAnyEmpty(new String[]{})   = false
         * StringUtils.isAnyEmpty(new String[]{""}) = true
         * </pre>
         *
         * @param css  the CharSequences to check, may be null or empty
         * @return {@code true} if any of the CharSequences are empty or null
         * @since 3.2
         */
        boolean isAnyEmpty(CharSequence... css);

        /**
         * <p>Checks if the CharSequence contains only ASCII printable characters.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code true}.</p>
         *
         * <pre>
         * StringUtils.isAsciiPrintable(null)     = false
         * StringUtils.isAsciiPrintable("")       = true
         * StringUtils.isAsciiPrintable(" ")      = true
         * StringUtils.isAsciiPrintable("Ceki")   = true
         * StringUtils.isAsciiPrintable("ab2c")   = true
         * StringUtils.isAsciiPrintable("!ab-c~") = true
         * StringUtils.isAsciiPrintable("\u0020") = true
         * StringUtils.isAsciiPrintable("\u0021") = true
         * StringUtils.isAsciiPrintable("\u007e") = true
         * StringUtils.isAsciiPrintable("\u007f") = false
         * StringUtils.isAsciiPrintable("Ceki G\u00fclc\u00fc") = false
         * </pre>
         *
         * @param cs the CharSequence to check, may be null
         * @return {@code true} if every character is in the range
         *  32 thru 126
         * @since 2.1
         * @since 3.0 Changed signature from isAsciiPrintable(String) to isAsciiPrintable(CharSequence)
         */
        boolean isAsciiPrintable(CharSequence cs);

        /**
         * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.isBlank(null)      = true
         * StringUtils.isBlank("")        = true
         * StringUtils.isBlank(" ")       = true
         * StringUtils.isBlank("bob")     = false
         * StringUtils.isBlank("  bob  ") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if the CharSequence is null, empty or whitespace only
         * @since 2.0
         * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
         */
        boolean isBlank(CharSequence cs);

        /**
         * <p>Checks if a CharSequence is empty ("") or null.</p>
         *
         * <pre>
         * StringUtils.isEmpty(null)      = true
         * StringUtils.isEmpty("")        = true
         * StringUtils.isEmpty(" ")       = false
         * StringUtils.isEmpty("bob")     = false
         * StringUtils.isEmpty("  bob  ") = false
         * </pre>
         *
         * <p>NOTE: This method changed in Lang version 2.0.
         * It no longer trims the CharSequence.
         * That functionality is available in isBlank().</p>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if the CharSequence is empty or null
         * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
         */
        boolean isEmpty(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains mixed casing of both uppercase and lowercase characters.</p>
         *
         * <p>{@code null} will return {@code false}. An empty CharSequence ({@code length()=0}) will return
         * {@code false}.</p>
         *
         * <pre>
         * StringUtils.isMixedCase(null)    = false
         * StringUtils.isMixedCase("")      = false
         * StringUtils.isMixedCase("ABC")   = false
         * StringUtils.isMixedCase("abc")   = false
         * StringUtils.isMixedCase("aBc")   = true
         * StringUtils.isMixedCase("A c")   = true
         * StringUtils.isMixedCase("A1c")   = true
         * StringUtils.isMixedCase("a/C")   = true
         * StringUtils.isMixedCase("aC\t")  = true
         * </pre>
         *
         * @param cs the CharSequence to check, may be null
         * @return {@code true} if the CharSequence contains both uppercase and lowercase characters
         * @since 3.5
         */
        boolean isMixedCase(CharSequence cs);

        /**
         * <p>Checks if none of the CharSequences are empty (""), null or whitespace only.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.isNoneBlank((String) null)    = false
         * StringUtils.isNoneBlank((String[]) null)  = true
         * StringUtils.isNoneBlank(null, "foo")      = false
         * StringUtils.isNoneBlank(null, null)       = false
         * StringUtils.isNoneBlank("", "bar")        = false
         * StringUtils.isNoneBlank("bob", "")        = false
         * StringUtils.isNoneBlank("  bob  ", null)  = false
         * StringUtils.isNoneBlank(" ", "bar")       = false
         * StringUtils.isNoneBlank(new String[] {})  = true
         * StringUtils.isNoneBlank(new String[]{""}) = false
         * StringUtils.isNoneBlank("foo", "bar")     = true
         * </pre>
         *
         * @param css  the CharSequences to check, may be null or empty
         * @return {@code true} if none of the CharSequences are empty or null or whitespace only
         * @since 3.2
         */
        boolean isNoneBlank(CharSequence... css);

        /**
         * <p>Checks if none of the CharSequences are empty ("") or null.</p>
         *
         * <pre>
         * StringUtils.isNoneEmpty((String) null)    = false
         * StringUtils.isNoneEmpty((String[]) null)  = true
         * StringUtils.isNoneEmpty(null, "foo")      = false
         * StringUtils.isNoneEmpty("", "bar")        = false
         * StringUtils.isNoneEmpty("bob", "")        = false
         * StringUtils.isNoneEmpty("  bob  ", null)  = false
         * StringUtils.isNoneEmpty(new String[] {})  = true
         * StringUtils.isNoneEmpty(new String[]{""}) = false
         * StringUtils.isNoneEmpty(" ", "bar")       = true
         * StringUtils.isNoneEmpty("foo", "bar")     = true
         * </pre>
         *
         * @param css  the CharSequences to check, may be null or empty
         * @return {@code true} if none of the CharSequences are empty or null
         * @since 3.2
         */
        boolean isNoneEmpty(CharSequence... css);

        /**
         * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.isNotBlank(null)      = false
         * StringUtils.isNotBlank("")        = false
         * StringUtils.isNotBlank(" ")       = false
         * StringUtils.isNotBlank("bob")     = true
         * StringUtils.isNotBlank("  bob  ") = true
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if the CharSequence is
         *  not empty and not null and not whitespace only
         * @since 2.0
         * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
         */
        boolean isNotBlank(CharSequence cs);

        /**
         * <p>Checks if a CharSequence is not empty ("") and not null.</p>
         *
         * <pre>
         * StringUtils.isNotEmpty(null)      = false
         * StringUtils.isNotEmpty("")        = false
         * StringUtils.isNotEmpty(" ")       = true
         * StringUtils.isNotEmpty("bob")     = true
         * StringUtils.isNotEmpty("  bob  ") = true
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if the CharSequence is not empty and not null
         * @since 3.0 Changed signature from isNotEmpty(String) to isNotEmpty(CharSequence)
         */
        boolean isNotEmpty(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only Unicode digits.
         * A decimal point is not a Unicode digit and returns false.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code false}.</p>
         *
         * <p>Note that the method does not allow for a leading sign, either positive or negative.
         * Also, if a String passes the numeric test, it may still generate a NumberFormatException
         * when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range
         * for int or long respectively.</p>
         *
         * <pre>
         * StringUtils.isNumeric(null)   = false
         * StringUtils.isNumeric("")     = false
         * StringUtils.isNumeric("  ")   = false
         * StringUtils.isNumeric("123")  = true
         * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
         * StringUtils.isNumeric("12 3") = false
         * StringUtils.isNumeric("ab2c") = false
         * StringUtils.isNumeric("12-3") = false
         * StringUtils.isNumeric("12.3") = false
         * StringUtils.isNumeric("-123") = false
         * StringUtils.isNumeric("+123") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains digits, and is non-null
         * @since 3.0 Changed signature from isNumeric(String) to isNumeric(CharSequence)
         * @since 3.0 Changed "" to return false and not true
         */
        boolean isNumeric(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only Unicode digits or space
         * ({@code ' '}).
         * A decimal point is not a Unicode digit and returns false.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code true}.</p>
         *
         * <pre>
         * StringUtils.isNumericSpace(null)   = false
         * StringUtils.isNumericSpace("")     = true
         * StringUtils.isNumericSpace("  ")   = true
         * StringUtils.isNumericSpace("123")  = true
         * StringUtils.isNumericSpace("12 3") = true
         * StringUtils.isNumeric("\u0967\u0968\u0969")  = true
         * StringUtils.isNumeric("\u0967\u0968 \u0969")  = true
         * StringUtils.isNumericSpace("ab2c") = false
         * StringUtils.isNumericSpace("12-3") = false
         * StringUtils.isNumericSpace("12.3") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains digits or space,
         *  and is non-null
         * @since 3.0 Changed signature from isNumericSpace(String) to isNumericSpace(CharSequence)
         */
        boolean isNumericSpace(CharSequence cs);

        /**
         * <p>Checks if the CharSequence contains only whitespace.</p>
         *
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>{@code null} will return {@code false}.
         * An empty CharSequence (length()=0) will return {@code true}.</p>
         *
         * <pre>
         * StringUtils.isWhitespace(null)   = false
         * StringUtils.isWhitespace("")     = true
         * StringUtils.isWhitespace("  ")   = true
         * StringUtils.isWhitespace("abc")  = false
         * StringUtils.isWhitespace("ab2c") = false
         * StringUtils.isWhitespace("ab-c") = false
         * </pre>
         *
         * @param cs  the CharSequence to check, may be null
         * @return {@code true} if only contains whitespace, and is non-null
         * @since 2.0
         * @since 3.0 Changed signature from isWhitespace(String) to isWhitespace(CharSequence)
         */
        boolean isWhitespace(CharSequence cs);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(byte[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(byte[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(char[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(char[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(double[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(double[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(float[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(float[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(int[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(int[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>Joins the elements of the provided {@code Iterable} into
         * a single String containing the provided elements.</p>
         *
         * <p>No delimiter is added before or after the list. Null objects or empty
         * strings within the iteration are represented by empty strings.</p>
         *
         * <p>See the examples here: {@link #join(Object[],char)}. </p>
         *
         * @param iterable  the {@code Iterable} providing the values to join together, may be null
         * @param separator  the separator character to use
         * @return the joined String, {@code null} if null iterator input
         * @since 2.3
         */
        String join(Iterable<?> iterable, char separator);

        /**
         * <p>Joins the elements of the provided {@code Iterable} into
         * a single String containing the provided elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * A {@code null} separator is the same as an empty String ("").</p>
         *
         * <p>See the examples here: {@link #join(Object[],String)}. </p>
         *
         * @param iterable  the {@code Iterable} providing the values to join together, may be null
         * @param separator  the separator character to use, null treated as ""
         * @return the joined String, {@code null} if null iterator input
         * @since 2.3
         */
        String join(Iterable<?> iterable, String separator);

        /**
         * <p>Joins the elements of the provided {@code Iterator} into
         * a single String containing the provided elements.</p>
         *
         * <p>No delimiter is added before or after the list. Null objects or empty
         * strings within the iteration are represented by empty strings.</p>
         *
         * <p>See the examples here: {@link #join(Object[],char)}. </p>
         *
         * @param iterator  the {@code Iterator} of values to join together, may be null
         * @param separator  the separator character to use
         * @return the joined String, {@code null} if null iterator input
         * @since 2.0
         */
        String join(Iterator<?> iterator, char separator);

        /**
         * <p>Joins the elements of the provided {@code Iterator} into
         * a single String containing the provided elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * A {@code null} separator is the same as an empty String ("").</p>
         *
         * <p>See the examples here: {@link #join(Object[],String)}. </p>
         *
         * @param iterator  the {@code Iterator} of values to join together, may be null
         * @param separator  the separator character to use, null treated as ""
         * @return the joined String, {@code null} if null iterator input
         */
        String join(Iterator<?> iterator, String separator);

        /**
         * <p>Joins the elements of the provided {@code List} into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
         * StringUtils.join(["a", "b", "c"], null) = "abc"
         * StringUtils.join([null, "", "a"], ';')  = ";;a"
         * </pre>
         *
         * @param list  the {@code List} of values to join together, may be null
         * @param separator  the separator character to use
         * @param startIndex the first index to start joining from.  It is
         * an error to pass in a start index past the end of the list
         * @param endIndex the index to stop joining from (exclusive). It is
         * an error to pass in an end index past the end of the list
         * @return the joined String, {@code null} if null list input
         * @since 3.8
         */
        String join(List<?> list, char separator, int startIndex, int endIndex);

        /**
         * <p>Joins the elements of the provided {@code List} into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
         * StringUtils.join(["a", "b", "c"], null) = "abc"
         * StringUtils.join([null, "", "a"], ';')  = ";;a"
         * </pre>
         *
         * @param list  the {@code List} of values to join together, may be null
         * @param separator  the separator character to use
         * @param startIndex the first index to start joining from.  It is
         * an error to pass in a start index past the end of the list
         * @param endIndex the index to stop joining from (exclusive). It is
         * an error to pass in an end index past the end of the list
         * @return the joined String, {@code null} if null list input
         * @since 3.8
         */
        String join(List<?> list, String separator, int startIndex, int endIndex);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(long[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(long[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>Joins the elements of the provided array into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
         * StringUtils.join(["a", "b", "c"], null) = "abc"
         * StringUtils.join([null, "", "a"], ';')  = ";;a"
         * </pre>
         *
         * @param array  the array of values to join together, may be null
         * @param separator  the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 2.0
         */
        String join(Object[] array, char separator);

        /**
         * <p>Joins the elements of the provided array into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
         * StringUtils.join(["a", "b", "c"], null) = "abc"
         * StringUtils.join([null, "", "a"], ';')  = ";;a"
         * </pre>
         *
         * @param array  the array of values to join together, may be null
         * @param separator  the separator character to use
         * @param startIndex the first index to start joining from.  It is
         * an error to pass in a start index past the end of the array
         * @param endIndex the index to stop joining from (exclusive). It is
         * an error to pass in an end index past the end of the array
         * @return the joined String, {@code null} if null array input
         * @since 2.0
         */
        String join(Object[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>Joins the elements of the provided array into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * A {@code null} separator is the same as an empty String ("").
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null, *)                = null
         * StringUtils.join([], *)                  = ""
         * StringUtils.join([null], *)              = ""
         * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
         * StringUtils.join(["a", "b", "c"], null)  = "abc"
         * StringUtils.join(["a", "b", "c"], "")    = "abc"
         * StringUtils.join([null, "", "a"], ',')   = ",,a"
         * </pre>
         *
         * @param array  the array of values to join together, may be null
         * @param separator  the separator character to use, null treated as ""
         * @return the joined String, {@code null} if null array input
         */
        String join(Object[] array, String separator);

        /**
         * <p>Joins the elements of the provided array into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * A {@code null} separator is the same as an empty String ("").
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null, *, *, *)                = null
         * StringUtils.join([], *, *, *)                  = ""
         * StringUtils.join([null], *, *, *)              = ""
         * StringUtils.join(["a", "b", "c"], "--", 0, 3)  = "a--b--c"
         * StringUtils.join(["a", "b", "c"], "--", 1, 3)  = "b--c"
         * StringUtils.join(["a", "b", "c"], "--", 2, 3)  = "c"
         * StringUtils.join(["a", "b", "c"], "--", 2, 2)  = ""
         * StringUtils.join(["a", "b", "c"], null, 0, 3)  = "abc"
         * StringUtils.join(["a", "b", "c"], "", 0, 3)    = "abc"
         * StringUtils.join([null, "", "a"], ',', 0, 3)   = ",,a"
         * </pre>
         *
         * @param array  the array of values to join together, may be null
         * @param separator  the separator character to use, null treated as ""
         * @param startIndex the first index to start joining from.
         * @param endIndex the index to stop joining from (exclusive).
         * @return the joined String, {@code null} if null array input; or the empty string
         * if {@code endIndex - startIndex <= 0}. The number of joined entries is given by
         * {@code endIndex - startIndex}
         * @throws ArrayIndexOutOfBoundsException ife<br>
         * {@code startIndex < 0} or <br>
         * {@code startIndex >= array.length()} or <br>
         * {@code endIndex < 0} or <br>
         * {@code endIndex > array.length()}
         */
        String join(Object[] array, String separator, int startIndex, int endIndex);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(short[] array, char separator);

        /**
         * <p>
         * Joins the elements of the provided array into a single String containing the provided list of elements.
         * </p>
         *
         * <p>
         * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
         * by empty strings.
         * </p>
         *
         * <pre>
         * StringUtils.join(null, *)               = null
         * StringUtils.join([], *)                 = ""
         * StringUtils.join([null], *)             = ""
         * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
         * StringUtils.join([1, 2, 3], null) = "123"
         * </pre>
         *
         * @param array
         *            the array of values to join together, may be null
         * @param separator
         *            the separator character to use
         * @param startIndex
         *            the first index to start joining from. It is an error to pass in a start index past the end of the
         *            array
         * @param endIndex
         *            the index to stop joining from (exclusive). It is an error to pass in an end index past the end of
         *            the array
         * @return the joined String, {@code null} if null array input
         * @since 3.2
         */
        String join(short[] array, char separator, int startIndex, int endIndex);

        /**
         * <p>Joins the elements of the provided array into a single String
         * containing the provided list of elements.</p>
         *
         * <p>No separator is added to the joined String.
         * Null objects or empty strings within the array are represented by
         * empty strings.</p>
         *
         * <pre>
         * StringUtils.join(null)            = null
         * StringUtils.join([])              = ""
         * StringUtils.join([null])          = ""
         * StringUtils.join(["a", "b", "c"]) = "abc"
         * StringUtils.join([null, "", "a"]) = "a"
         * </pre>
         *
         * @param <T> the specific type of values to join together
         * @param elements  the values to join together, may be null
         * @return the joined String, {@code null} if null array input
         * @since 2.0
         * @since 3.0 Changed signature to use varargs
         */
        <T> String join(T... elements);

        /**
         * <p>Joins the elements of the provided varargs into a
         * single String containing the provided elements.</p>
         *
         * <p>No delimiter is added before or after the list.
         * {@code null} elements and separator are treated as empty Strings ("").</p>
         *
         * <pre>
         * StringUtils.joinWith(",", {"a", "b"})        = "a,b"
         * StringUtils.joinWith(",", {"a", "b",""})     = "a,b,"
         * StringUtils.joinWith(",", {"a", null, "b"})  = "a,,b"
         * StringUtils.joinWith(null, {"a", "b"})       = "ab"
         * </pre>
         *
         * @param separator the separator character to use, null treated as ""
         * @param objects the varargs providing the values to join together. {@code null} elements are treated as ""
         * @return the joined String.
         * @throws IllegalArgumentException if a null varargs is provided
         * @since 3.5
         */
        String joinWith(String separator, Object... objects);

        /**
         * <p>Finds the last index within a CharSequence, handling {@code null}.
         * This method uses {@link String#lastIndexOf(String)} if possible.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.lastIndexOf(null, *)          = -1
         * StringUtils.lastIndexOf(*, null)          = -1
         * StringUtils.lastIndexOf("", "")           = 0
         * StringUtils.lastIndexOf("aabaabaa", "a")  = 7
         * StringUtils.lastIndexOf("aabaabaa", "b")  = 5
         * StringUtils.lastIndexOf("aabaabaa", "ab") = 4
         * StringUtils.lastIndexOf("aabaabaa", "")   = 8
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchSeq  the CharSequence to find, may be null
         * @return the last index of the search String,
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from lastIndexOf(String, String) to lastIndexOf(CharSequence, CharSequence)
         */
        int lastIndexOf(CharSequence seq, CharSequence searchSeq);

        /**
         * <p>Finds the last index within a CharSequence, handling {@code null}.
         * This method uses {@link String#lastIndexOf(String, int)} if possible.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A negative start position returns {@code -1}.
         * An empty ("") search CharSequence always matches unless the start position is negative.
         * A start position greater than the string length searches the whole string.
         * The search starts at the startPos and works backwards; matches starting after the start
         * position are ignored.
         * </p>
         *
         * <pre>
         * StringUtils.lastIndexOf(null, *, *)          = -1
         * StringUtils.lastIndexOf(*, null, *)          = -1
         * StringUtils.lastIndexOf("aabaabaa", "a", 8)  = 7
         * StringUtils.lastIndexOf("aabaabaa", "b", 8)  = 5
         * StringUtils.lastIndexOf("aabaabaa", "ab", 8) = 4
         * StringUtils.lastIndexOf("aabaabaa", "b", 9)  = 5
         * StringUtils.lastIndexOf("aabaabaa", "b", -1) = -1
         * StringUtils.lastIndexOf("aabaabaa", "a", 0)  = 0
         * StringUtils.lastIndexOf("aabaabaa", "b", 0)  = -1
         * StringUtils.lastIndexOf("aabaabaa", "b", 1)  = -1
         * StringUtils.lastIndexOf("aabaabaa", "b", 2)  = 2
         * StringUtils.lastIndexOf("aabaabaa", "ba", 2)  = 2
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchSeq  the CharSequence to find, may be null
         * @param startPos  the start position, negative treated as zero
         * @return the last index of the search CharSequence (always &le; startPos),
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from lastIndexOf(String, String, int) to lastIndexOf(CharSequence, CharSequence, int)
         */
        int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos);

        /**
         * Returns the index within {@code seq} of the last occurrence of
         * the specified character. For values of {@code searchChar} in the
         * range from 0 to 0xFFFF (inclusive), the index (in Unicode code
         * units) returned is the largest value <i>k</i> such that:
         * <blockquote><pre>
         * this.charAt(<i>k</i>) == searchChar
         * </pre></blockquote>
         * is true. For other values of {@code searchChar}, it is the
         * largest value <i>k</i> such that:
         * <blockquote><pre>
         * this.codePointAt(<i>k</i>) == searchChar
         * </pre></blockquote>
         * is true.  In either case, if no such character occurs in this
         * string, then {@code -1} is returned. Furthermore, a {@code null} or empty ("")
         * {@code CharSequence} will return {@code -1}. The
         * {@code seq} {@code CharSequence} object is searched backwards
         * starting at the last character.
         *
         * <pre>
         * StringUtils.lastIndexOf(null, *)         = -1
         * StringUtils.lastIndexOf("", *)           = -1
         * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
         * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
         * </pre>
         *
         * @param seq  the {@code CharSequence} to check, may be null
         * @param searchChar  the character to find
         * @return the last index of the search character,
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from lastIndexOf(String, int) to lastIndexOf(CharSequence, int)
         * @since 3.6 Updated {@link CharSequenceUtils} call to behave more like {@code String}
         */
        int lastIndexOf(CharSequence seq, int searchChar);

        /**
         * Returns the index within {@code seq} of the last occurrence of
         * the specified character, searching backward starting at the
         * specified index. For values of {@code searchChar} in the range
         * from 0 to 0xFFFF (inclusive), the index returned is the largest
         * value <i>k</i> such that:
         * <blockquote><pre>
         * (this.charAt(<i>k</i>) == searchChar) &amp;&amp; (<i>k</i> &lt;= startPos)
         * </pre></blockquote>
         * is true. For other values of {@code searchChar}, it is the
         * largest value <i>k</i> such that:
         * <blockquote><pre>
         * (this.codePointAt(<i>k</i>) == searchChar) &amp;&amp; (<i>k</i> &lt;= startPos)
         * </pre></blockquote>
         * is true. In either case, if no such character occurs in {@code seq}
         * at or before position {@code startPos}, then
         * {@code -1} is returned. Furthermore, a {@code null} or empty ("")
         * {@code CharSequence} will return {@code -1}. A start position greater
         * than the string length searches the whole string.
         * The search starts at the {@code startPos} and works backwards;
         * matches starting after the start position are ignored.
         *
         * <p>All indices are specified in {@code char} values
         * (Unicode code units).
         *
         * <pre>
         * StringUtils.lastIndexOf(null, *, *)          = -1
         * StringUtils.lastIndexOf("", *,  *)           = -1
         * StringUtils.lastIndexOf("aabaabaa", 'b', 8)  = 5
         * StringUtils.lastIndexOf("aabaabaa", 'b', 4)  = 2
         * StringUtils.lastIndexOf("aabaabaa", 'b', 0)  = -1
         * StringUtils.lastIndexOf("aabaabaa", 'b', 9)  = 5
         * StringUtils.lastIndexOf("aabaabaa", 'b', -1) = -1
         * StringUtils.lastIndexOf("aabaabaa", 'a', 0)  = 0
         * </pre>
         *
         * @param seq  the CharSequence to check, may be null
         * @param searchChar  the character to find
         * @param startPos  the start position
         * @return the last index of the search character (always &le; startPos),
         *  -1 if no match or {@code null} string input
         * @since 2.0
         * @since 3.0 Changed signature from lastIndexOf(String, int, int) to lastIndexOf(CharSequence, int, int)
         */
        int lastIndexOf(CharSequence seq, int searchChar, int startPos);

        /**
         * <p>Find the latest index of any substring in a set of potential substrings.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A {@code null} search array will return {@code -1}.
         * A {@code null} or zero length search array entry will be ignored,
         * but a search array containing "" will return the length of {@code str}
         * if {@code str} is not null. This method uses {@link String#indexOf(String)} if possible</p>
         *
         * <pre>
         * StringUtils.lastIndexOfAny(null, *)                    = -1
         * StringUtils.lastIndexOfAny(*, null)                    = -1
         * StringUtils.lastIndexOfAny(*, [])                      = -1
         * StringUtils.lastIndexOfAny(*, [null])                  = -1
         * StringUtils.lastIndexOfAny("zzabyycdxx", ["ab", "cd"]) = 6
         * StringUtils.lastIndexOfAny("zzabyycdxx", ["cd", "ab"]) = 6
         * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn", "op"]) = -1
         * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn", "op"]) = -1
         * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn", ""])   = 10
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStrs  the CharSequences to search for, may be null
         * @return the last index of any of the CharSequences, -1 if no match
         * @since 3.0 Changed signature from lastIndexOfAny(String, String[]) to lastIndexOfAny(CharSequence, CharSequence)
         */
        int lastIndexOfAny(CharSequence str, CharSequence... searchStrs);

        /**
         * <p>Case in-sensitive find of the last index within a CharSequence.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A negative start position returns {@code -1}.
         * An empty ("") search CharSequence always matches unless the start position is negative.
         * A start position greater than the string length searches the whole string.</p>
         *
         * <pre>
         * StringUtils.lastIndexOfIgnoreCase(null, *)          = -1
         * StringUtils.lastIndexOfIgnoreCase(*, null)          = -1
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A")  = 7
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B")  = 5
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB") = 4
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @return the first index of the search CharSequence,
         *  -1 if no match or {@code null} string input
         * @since 2.5
         * @since 3.0 Changed signature from lastIndexOfIgnoreCase(String, String) to lastIndexOfIgnoreCase(CharSequence, CharSequence)
         */
        int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr);

        /**
         * <p>Case in-sensitive find of the last index within a CharSequence
         * from the specified position.</p>
         *
         * <p>A {@code null} CharSequence will return {@code -1}.
         * A negative start position returns {@code -1}.
         * An empty ("") search CharSequence always matches unless the start position is negative.
         * A start position greater than the string length searches the whole string.
         * The search starts at the startPos and works backwards; matches starting after the start
         * position are ignored.
         * </p>
         *
         * <pre>
         * StringUtils.lastIndexOfIgnoreCase(null, *, *)          = -1
         * StringUtils.lastIndexOfIgnoreCase(*, null, *)          = -1
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8)  = 7
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8)  = 5
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8) = 4
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9)  = 5
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1) = -1
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0)  = 0
         * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0)  = -1
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @param startPos  the start position
         * @return the last index of the search CharSequence (always &le; startPos),
         *  -1 if no match or {@code null} input
         * @since 2.5
         * @since 3.0 Changed signature from lastIndexOfIgnoreCase(String, String, int) to lastIndexOfIgnoreCase(CharSequence, CharSequence, int)
         */
        int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos);

        /**
         * <p>Finds the n-th last index within a String, handling {@code null}.
         * This method uses {@link String#lastIndexOf(String)}.</p>
         *
         * <p>A {@code null} String will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.lastOrdinalIndexOf(null, *, *)          = -1
         * StringUtils.lastOrdinalIndexOf(*, null, *)          = -1
         * StringUtils.lastOrdinalIndexOf("", "", *)           = 0
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1)  = 7
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2)  = 6
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1)  = 5
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2)  = 2
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1) = 4
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2) = 1
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1)   = 8
         * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2)   = 8
         * </pre>
         *
         * <p>Note that 'tail(CharSequence str, int n)' may be implemented as: </p>
         *
         * <pre>
         *   str.substring(lastOrdinalIndexOf(str, "\n", n) + 1)
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @param ordinal  the n-th last {@code searchStr} to find
         * @return the n-th last index of the search CharSequence,
         *  {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
         * @since 2.5
         * @since 3.0 Changed signature from lastOrdinalIndexOf(String, String, int) to lastOrdinalIndexOf(CharSequence, CharSequence, int)
         */
        int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal);

        /**
         * <p>Gets the leftmost {@code len} characters of a String.</p>
         *
         * <p>If {@code len} characters are not available, or the
         * String is {@code null}, the String will be returned without
         * an exception. An empty String is returned if len is negative.</p>
         *
         * <pre>
         * StringUtils.left(null, *)    = null
         * StringUtils.left(*, -ve)     = ""
         * StringUtils.left("", *)      = ""
         * StringUtils.left("abc", 0)   = ""
         * StringUtils.left("abc", 2)   = "ab"
         * StringUtils.left("abc", 4)   = "abc"
         * </pre>
         *
         * @param str  the String to get the leftmost characters from, may be null
         * @param len  the length of the required String
         * @return the leftmost characters, {@code null} if null String input
         */
        String left(String str, int len);

        /**
         * <p>Left pad a String with spaces (' ').</p>
         *
         * <p>The String is padded to the size of {@code size}.</p>
         *
         * <pre>
         * StringUtils.leftPad(null, *)   = null
         * StringUtils.leftPad("", 3)     = "   "
         * StringUtils.leftPad("bat", 3)  = "bat"
         * StringUtils.leftPad("bat", 5)  = "  bat"
         * StringUtils.leftPad("bat", 1)  = "bat"
         * StringUtils.leftPad("bat", -1) = "bat"
         * </pre>
         *
         * @param str  the String to pad out, may be null
         * @param size  the size to pad to
         * @return left padded String or original String if no padding is necessary,
         *  {@code null} if null String input
         */
        String leftPad(String str, int size);

        /**
         * <p>Left pad a String with a specified character.</p>
         *
         * <p>Pad to a size of {@code size}.</p>
         *
         * <pre>
         * StringUtils.leftPad(null, *, *)     = null
         * StringUtils.leftPad("", 3, 'z')     = "zzz"
         * StringUtils.leftPad("bat", 3, 'z')  = "bat"
         * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
         * StringUtils.leftPad("bat", 1, 'z')  = "bat"
         * StringUtils.leftPad("bat", -1, 'z') = "bat"
         * </pre>
         *
         * @param str  the String to pad out, may be null
         * @param size  the size to pad to
         * @param padChar  the character to pad with
         * @return left padded String or original String if no padding is necessary,
         *  {@code null} if null String input
         * @since 2.0
         */
        String leftPad(String str, int size, char padChar);

        /**
         * <p>Left pad a String with a specified String.</p>
         *
         * <p>Pad to a size of {@code size}.</p>
         *
         * <pre>
         * StringUtils.leftPad(null, *, *)      = null
         * StringUtils.leftPad("", 3, "z")      = "zzz"
         * StringUtils.leftPad("bat", 3, "yz")  = "bat"
         * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
         * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
         * StringUtils.leftPad("bat", 1, "yz")  = "bat"
         * StringUtils.leftPad("bat", -1, "yz") = "bat"
         * StringUtils.leftPad("bat", 5, null)  = "  bat"
         * StringUtils.leftPad("bat", 5, "")    = "  bat"
         * </pre>
         *
         * @param str  the String to pad out, may be null
         * @param size  the size to pad to
         * @param padStr  the String to pad with, null or empty treated as single space
         * @return left padded String or original String if no padding is necessary,
         *  {@code null} if null String input
         */
        String leftPad(String str, int size, String padStr);

        /**
         * Gets a CharSequence length or {@code 0} if the CharSequence is
         * {@code null}.
         *
         * @param cs
         *            a CharSequence or {@code null}
         * @return CharSequence length or {@code 0} if the CharSequence is
         *         {@code null}.
         * @since 2.4
         * @since 3.0 Changed signature from length(String) to length(CharSequence)
         */
        int length(CharSequence cs);

        /**
         * <p>Converts a String to lower case as per {@link String#toLowerCase()}.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.lowerCase(null)  = null
         * StringUtils.lowerCase("")    = ""
         * StringUtils.lowerCase("aBc") = "abc"
         * </pre>
         *
         * <p><strong>Note:</strong> As described in the documentation for {@link String#toLowerCase()},
         * the result of this method is affected by the current locale.
         * For platform-independent case transformations, the method {@link #lowerCase(String, Locale)}
         * should be used with a specific locale (e.g. {@link Locale#ENGLISH}).</p>
         *
         * @param str  the String to lower case, may be null
         * @return the lower cased String, {@code null} if null String input
         */
        String lowerCase(String str);

        /**
         * <p>Converts a String to lower case as per {@link String#toLowerCase(Locale)}.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.lowerCase(null, Locale.ENGLISH)  = null
         * StringUtils.lowerCase("", Locale.ENGLISH)    = ""
         * StringUtils.lowerCase("aBc", Locale.ENGLISH) = "abc"
         * </pre>
         *
         * @param str  the String to lower case, may be null
         * @param locale  the locale that defines the case transformation rules, must not be null
         * @return the lower cased String, {@code null} if null String input
         * @since 2.5
         */
        String lowerCase(String str, Locale locale);

        /**
         * <p>Gets {@code len} characters from the middle of a String.</p>
         *
         * <p>If {@code len} characters are not available, the remainder
         * of the String will be returned without an exception. If the
         * String is {@code null}, {@code null} will be returned.
         * An empty String is returned if len is negative or exceeds the
         * length of {@code str}.</p>
         *
         * <pre>
         * StringUtils.mid(null, *, *)    = null
         * StringUtils.mid(*, *, -ve)     = ""
         * StringUtils.mid("", 0, *)      = ""
         * StringUtils.mid("abc", 0, 2)   = "ab"
         * StringUtils.mid("abc", 0, 4)   = "abc"
         * StringUtils.mid("abc", 2, 4)   = "c"
         * StringUtils.mid("abc", 4, 2)   = ""
         * StringUtils.mid("abc", -2, 2)  = "ab"
         * </pre>
         *
         * @param str  the String to get the characters from, may be null
         * @param pos  the position to start from, negative treated as zero
         * @param len  the length of the required String
         * @return the middle characters, {@code null} if null String input
         */
        String mid(String str, int pos, int len);

        /**
         * <p>
         * Similar to <a
         * href="http://www.w3.org/TR/xpath/#function-normalize-space">http://www.w3.org/TR/xpath/#function-normalize
         * -space</a>
         * </p>
         * <p>
         * The function returns the argument string with whitespace normalized by using
         * {@code {@link #trim(String)}} to remove leading and trailing whitespace
         * and then replacing sequences of whitespace characters by a single space.
         * </p>
         * In XML Whitespace characters are the same as those allowed by the <a
         * href="http://www.w3.org/TR/REC-xml/#NT-S">S</a> production, which is S ::= (#x20 | #x9 | #xD | #xA)+
         * <p>
         * Java's regexp pattern \s defines whitespace as [ \t\n\x0B\f\r]
         *
         * <p>For reference:</p>
         * <ul>
         * <li>\x0B = vertical tab</li>
         * <li>\f = #xC = form feed</li>
         * <li>#x20 = space</li>
         * <li>#x9 = \t</li>
         * <li>#xA = \n</li>
         * <li>#xD = \r</li>
         * </ul>
         *
         * <p>
         * The difference is that Java's whitespace includes vertical tab and form feed, which this functional will also
         * normalize. Additionally {@code {@link #trim(String)}} removes control characters (char &lt;= 32) from both
         * ends of this String.
         * </p>
         *
         * @see Pattern
         * @see #trim(String)
         * @see <a
         *      href="http://www.w3.org/TR/xpath/#function-normalize-space">http://www.w3.org/TR/xpath/#function-normalize-space</a>
         * @param str the source String to normalize whitespaces from, may be null
         * @return the modified string with whitespace normalized, {@code null} if null String input
         *
         * @since 3.0
         */
        String normalizeSpace(String str);

        /**
         * <p>Finds the n-th index within a CharSequence, handling {@code null}.
         * This method uses {@link String#indexOf(String)} if possible.</p>
         * <p><b>Note:</b> The code starts looking for a match at the start of the target,
         * incrementing the starting index by one after each successful match
         * (unless {@code searchStr} is an empty string in which case the position
         * is never incremented and {@code 0} is returned immediately).
         * This means that matches may overlap.</p>
         * <p>A {@code null} CharSequence will return {@code -1}.</p>
         *
         * <pre>
         * StringUtils.ordinalIndexOf(null, *, *)          = -1
         * StringUtils.ordinalIndexOf(*, null, *)          = -1
         * StringUtils.ordinalIndexOf("", "", *)           = 0
         * StringUtils.ordinalIndexOf("aabaabaa", "a", 1)  = 0
         * StringUtils.ordinalIndexOf("aabaabaa", "a", 2)  = 1
         * StringUtils.ordinalIndexOf("aabaabaa", "b", 1)  = 2
         * StringUtils.ordinalIndexOf("aabaabaa", "b", 2)  = 5
         * StringUtils.ordinalIndexOf("aabaabaa", "ab", 1) = 1
         * StringUtils.ordinalIndexOf("aabaabaa", "ab", 2) = 4
         * StringUtils.ordinalIndexOf("aabaabaa", "", 1)   = 0
         * StringUtils.ordinalIndexOf("aabaabaa", "", 2)   = 0
         * </pre>
         *
         * <p>Matches may overlap:</p>
         * <pre>
         * StringUtils.ordinalIndexOf("ababab", "aba", 1)   = 0
         * StringUtils.ordinalIndexOf("ababab", "aba", 2)   = 2
         * StringUtils.ordinalIndexOf("ababab", "aba", 3)   = -1
         *
         * StringUtils.ordinalIndexOf("abababab", "abab", 1) = 0
         * StringUtils.ordinalIndexOf("abababab", "abab", 2) = 2
         * StringUtils.ordinalIndexOf("abababab", "abab", 3) = 4
         * StringUtils.ordinalIndexOf("abababab", "abab", 4) = -1
         * </pre>
         *
         * <p>Note that 'head(CharSequence str, int n)' may be implemented as: </p>
         *
         * <pre>
         *   str.substring(0, lastOrdinalIndexOf(str, "\n", n))
         * </pre>
         *
         * @param str  the CharSequence to check, may be null
         * @param searchStr  the CharSequence to find, may be null
         * @param ordinal  the n-th {@code searchStr} to find
         * @return the n-th index of the search CharSequence,
         *  {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
         * @since 2.1
         * @since 3.0 Changed signature from ordinalIndexOf(String, String, int) to ordinalIndexOf(CharSequence, CharSequence, int)
         */
        int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal);

        /**
         * <p>Overlays part of a String with another String.</p>
         *
         * <p>A {@code null} string input returns {@code null}.
         * A negative index is treated as zero.
         * An index greater than the string length is treated as the string length.
         * The start index is always the smaller of the two indices.</p>
         *
         * <pre>
         * StringUtils.overlay(null, *, *, *)            = null
         * StringUtils.overlay("", "abc", 0, 0)          = "abc"
         * StringUtils.overlay("abcdef", null, 2, 4)     = "abef"
         * StringUtils.overlay("abcdef", "", 2, 4)       = "abef"
         * StringUtils.overlay("abcdef", "", 4, 2)       = "abef"
         * StringUtils.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
         * StringUtils.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
         * StringUtils.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
         * StringUtils.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
         * StringUtils.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
         * StringUtils.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
         * </pre>
         *
         * @param str  the String to do overlaying in, may be null
         * @param overlay  the String to overlay, may be null
         * @param start  the position to start overlaying at
         * @param end  the position to stop overlaying before
         * @return overlayed String, {@code null} if null String input
         * @since 2.0
         */
        String overlay(String str, String overlay, int start, int end);

        /**
         * Prepends the prefix to the start of the string if the string does not
         * already start with any of the prefixes.
         *
         * <pre>
         * StringUtils.prependIfMissing(null, null) = null
         * StringUtils.prependIfMissing("abc", null) = "abc"
         * StringUtils.prependIfMissing("", "xyz") = "xyz"
         * StringUtils.prependIfMissing("abc", "xyz") = "xyzabc"
         * StringUtils.prependIfMissing("xyzabc", "xyz") = "xyzabc"
         * StringUtils.prependIfMissing("XYZabc", "xyz") = "xyzXYZabc"
         * </pre>
         * <p>With additional prefixes,</p>
         * <pre>
         * StringUtils.prependIfMissing(null, null, null) = null
         * StringUtils.prependIfMissing("abc", null, null) = "abc"
         * StringUtils.prependIfMissing("", "xyz", null) = "xyz"
         * StringUtils.prependIfMissing("abc", "xyz", new CharSequence[]{null}) = "xyzabc"
         * StringUtils.prependIfMissing("abc", "xyz", "") = "abc"
         * StringUtils.prependIfMissing("abc", "xyz", "mno") = "xyzabc"
         * StringUtils.prependIfMissing("xyzabc", "xyz", "mno") = "xyzabc"
         * StringUtils.prependIfMissing("mnoabc", "xyz", "mno") = "mnoabc"
         * StringUtils.prependIfMissing("XYZabc", "xyz", "mno") = "xyzXYZabc"
         * StringUtils.prependIfMissing("MNOabc", "xyz", "mno") = "xyzMNOabc"
         * </pre>
         *
         * @param str The string.
         * @param prefix The prefix to prepend to the start of the string.
         * @param prefixes Additional prefixes that are valid.
         *
         * @return A new String if prefix was prepended, the same string otherwise.
         *
         * @since 3.2
         */
        String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes);

        /**
         * Prepends the prefix to the start of the string if the string does not
         * already start, case insensitive, with any of the prefixes.
         *
         * <pre>
         * StringUtils.prependIfMissingIgnoreCase(null, null) = null
         * StringUtils.prependIfMissingIgnoreCase("abc", null) = "abc"
         * StringUtils.prependIfMissingIgnoreCase("", "xyz") = "xyz"
         * StringUtils.prependIfMissingIgnoreCase("abc", "xyz") = "xyzabc"
         * StringUtils.prependIfMissingIgnoreCase("xyzabc", "xyz") = "xyzabc"
         * StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz") = "XYZabc"
         * </pre>
         * <p>With additional prefixes,</p>
         * <pre>
         * StringUtils.prependIfMissingIgnoreCase(null, null, null) = null
         * StringUtils.prependIfMissingIgnoreCase("abc", null, null) = "abc"
         * StringUtils.prependIfMissingIgnoreCase("", "xyz", null) = "xyz"
         * StringUtils.prependIfMissingIgnoreCase("abc", "xyz", new CharSequence[]{null}) = "xyzabc"
         * StringUtils.prependIfMissingIgnoreCase("abc", "xyz", "") = "abc"
         * StringUtils.prependIfMissingIgnoreCase("abc", "xyz", "mno") = "xyzabc"
         * StringUtils.prependIfMissingIgnoreCase("xyzabc", "xyz", "mno") = "xyzabc"
         * StringUtils.prependIfMissingIgnoreCase("mnoabc", "xyz", "mno") = "mnoabc"
         * StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz", "mno") = "XYZabc"
         * StringUtils.prependIfMissingIgnoreCase("MNOabc", "xyz", "mno") = "MNOabc"
         * </pre>
         *
         * @param str The string.
         * @param prefix The prefix to prepend to the start of the string.
         * @param prefixes Additional prefixes that are valid (optional).
         *
         * @return A new String if prefix was prepended, the same string otherwise.
         *
         * @since 3.2
         */
        String prependIfMissingIgnoreCase(String str, CharSequence prefix, CharSequence... prefixes);

        /**
         * <p>Removes all occurrences of a character from within the source string.</p>
         *
         * <p>A {@code null} source string will return {@code null}.
         * An empty ("") source string will return the empty string.</p>
         *
         * <pre>
         * StringUtils.remove(null, *)       = null
         * StringUtils.remove("", *)         = ""
         * StringUtils.remove("queued", 'u') = "qeed"
         * StringUtils.remove("queued", 'z') = "queued"
         * </pre>
         *
         * @param str  the source String to search, may be null
         * @param remove  the char to search for and remove, may be null
         * @return the substring with the char removed if found,
         *  {@code null} if null String input
         * @since 2.1
         */
        String remove(String str, char remove);

        /**
         * <p>Removes all occurrences of a substring from within the source string.</p>
         *
         * <p>A {@code null} source string will return {@code null}.
         * An empty ("") source string will return the empty string.
         * A {@code null} remove string will return the source string.
         * An empty ("") remove string will return the source string.</p>
         *
         * <pre>
         * StringUtils.remove(null, *)        = null
         * StringUtils.remove("", *)          = ""
         * StringUtils.remove(*, null)        = *
         * StringUtils.remove(*, "")          = *
         * StringUtils.remove("queued", "ue") = "qd"
         * StringUtils.remove("queued", "zz") = "queued"
         * </pre>
         *
         * @param str  the source String to search, may be null
         * @param remove  the String to search for and remove, may be null
         * @return the substring with the string removed if found,
         *  {@code null} if null String input
         * @since 2.1
         */
        String remove(String str, String remove);

        /**
         * <p>Removes each substring of the text String that matches the given regular expression.</p>
         *
         * This method is a {@code null} safe equivalent to:
         * <ul>
         *  <li>{@code text.replaceAll(regex, StringUtils.EMPTY)}</li>
         *  <li>{@code Pattern.compile(regex).matcher(text).replaceAll(StringUtils.EMPTY)}</li>
         * </ul>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <p>Unlike in the {@link #removePattern(String, String)} method, the {@link Pattern#DOTALL} option
         * is NOT automatically added.
         * To use the DOTALL option prepend {@code "(?s)"} to the regex.
         * DOTALL is also known as single-line mode in Perl.</p>
         *
         * <pre>
         * StringUtils.removeAll(null, *)      = null
         * StringUtils.removeAll("any", (String) null)  = "any"
         * StringUtils.removeAll("any", "")    = "any"
         * StringUtils.removeAll("any", ".*")  = ""
         * StringUtils.removeAll("any", ".+")  = ""
         * StringUtils.removeAll("abc", ".?")  = ""
         * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\nB"
         * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
         * StringUtils.removeAll("ABCabc123abc", "[a-z]")     = "ABC123"
         * </pre>
         *
         * @param text  text to remove from, may be null
         * @param regex  the regular expression to which this string is to be matched
         * @return  the text with any removes processed,
         *              {@code null} if null String input
         *
         * @throws  java.util.regex.PatternSyntaxException
         *              if the regular expression's syntax is invalid
         *
         * @see #replaceAll(String, String, String)
         * @see #removePattern(String, String)
         * @see String#replaceAll(String, String)
         * @see Pattern
         * @see Pattern#DOTALL
         * @since 3.5
         *
         * @deprecated Moved to RegExUtils.
         */
        @Deprecated
        String removeAll(String text, String regex);

        /**
         * <p>Removes a substring only if it is at the end of a source string,
         * otherwise returns the source string.</p>
         *
         * <p>A {@code null} source string will return {@code null}.
         * An empty ("") source string will return the empty string.
         * A {@code null} search string will return the source string.</p>
         *
         * <pre>
         * StringUtils.removeEnd(null, *)      = null
         * StringUtils.removeEnd("", *)        = ""
         * StringUtils.removeEnd(*, null)      = *
         * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
         * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
         * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
         * StringUtils.removeEnd("abc", "")    = "abc"
         * </pre>
         *
         * @param str  the source String to search, may be null
         * @param remove  the String to search for and remove, may be null
         * @return the substring with the string removed if found,
         *  {@code null} if null String input
         * @since 2.1
         */
        String removeEnd(String str, String remove);

        /**
         * <p>Case insensitive removal of a substring if it is at the end of a source string,
         * otherwise returns the source string.</p>
         *
         * <p>A {@code null} source string will return {@code null}.
         * An empty ("") source string will return the empty string.
         * A {@code null} search string will return the source string.</p>
         *
         * <pre>
         * StringUtils.removeEndIgnoreCase(null, *)      = null
         * StringUtils.removeEndIgnoreCase("", *)        = ""
         * StringUtils.removeEndIgnoreCase(*, null)      = *
         * StringUtils.removeEndIgnoreCase("www.domain.com", ".com.")  = "www.domain.com"
         * StringUtils.removeEndIgnoreCase("www.domain.com", ".com")   = "www.domain"
         * StringUtils.removeEndIgnoreCase("www.domain.com", "domain") = "www.domain.com"
         * StringUtils.removeEndIgnoreCase("abc", "")    = "abc"
         * StringUtils.removeEndIgnoreCase("www.domain.com", ".COM") = "www.domain")
         * StringUtils.removeEndIgnoreCase("www.domain.COM", ".com") = "www.domain")
         * </pre>
         *
         * @param str  the source String to search, may be null
         * @param remove  the String to search for (case insensitive) and remove, may be null
         * @return the substring with the string removed if found,
         *  {@code null} if null String input
         * @since 2.4
         */
        String removeEndIgnoreCase(String str, String remove);

        /**
         * <p>Removes the first substring of the text string that matches the given regular expression.</p>
         *
         * This method is a {@code null} safe equivalent to:
         * <ul>
         *  <li>{@code text.replaceFirst(regex, StringUtils.EMPTY)}</li>
         *  <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(StringUtils.EMPTY)}</li>
         * </ul>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <p>The {@link Pattern#DOTALL} option is NOT automatically added.
         * To use the DOTALL option prepend {@code "(?s)"} to the regex.
         * DOTALL is also known as single-line mode in Perl.</p>
         *
         * <pre>
         * StringUtils.removeFirst(null, *)      = null
         * StringUtils.removeFirst("any", (String) null)  = "any"
         * StringUtils.removeFirst("any", "")    = "any"
         * StringUtils.removeFirst("any", ".*")  = ""
         * StringUtils.removeFirst("any", ".+")  = ""
         * StringUtils.removeFirst("abc", ".?")  = "bc"
         * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\n&lt;__&gt;B"
         * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
         * StringUtils.removeFirst("ABCabc123", "[a-z]")          = "ABCbc123"
         * StringUtils.removeFirst("ABCabc123abc", "[a-z]+")      = "ABC123abc"
         * </pre>
         *
         * @param text  text to remove from, may be null
         * @param regex  the regular expression to which this string is to be matched
         * @return  the text with the first replacement processed,
         *              {@code null} if null String input
         *
         * @throws  java.util.regex.PatternSyntaxException
         *              if the regular expression's syntax is invalid
         *
         * @see #replaceFirst(String, String, String)
         * @see String#replaceFirst(String, String)
         * @see Pattern
         * @see Pattern#DOTALL
         * @since 3.5
         *
         * @deprecated Moved to RegExUtils.
         */
        @Deprecated
        String removeFirst(String text, String regex);

        /**
         * <p>
         * Case insensitive removal of all occurrences of a substring from within
         * the source string.
         * </p>
         *
         * <p>
         * A {@code null} source string will return {@code null}. An empty ("")
         * source string will return the empty string. A {@code null} remove string
         * will return the source string. An empty ("") remove string will return
         * the source string.
         * </p>
         *
         * <pre>
         * StringUtils.removeIgnoreCase(null, *)        = null
         * StringUtils.removeIgnoreCase("", *)          = ""
         * StringUtils.removeIgnoreCase(*, null)        = *
         * StringUtils.removeIgnoreCase(*, "")          = *
         * StringUtils.removeIgnoreCase("queued", "ue") = "qd"
         * StringUtils.removeIgnoreCase("queued", "zz") = "queued"
         * StringUtils.removeIgnoreCase("quEUed", "UE") = "qd"
         * StringUtils.removeIgnoreCase("queued", "zZ") = "queued"
         * </pre>
         *
         * @param str
         *            the source String to search, may be null
         * @param remove
         *            the String to search for (case insensitive) and remove, may be
         *            null
         * @return the substring with the string removed if found, {@code null} if
         *         null String input
         * @since 3.5
         */
        String removeIgnoreCase(String str, String remove);

        /**
         * <p>Removes each substring of the source String that matches the given regular expression using the DOTALL option.
         * </p>
         *
         * This call is a {@code null} safe equivalent to:
         * <ul>
         * <li>{@code source.replaceAll(&quot;(?s)&quot; + regex, StringUtils.EMPTY)}</li>
         * <li>{@code Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(StringUtils.EMPTY)}</li>
         * </ul>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.removePattern(null, *)       = null
         * StringUtils.removePattern("any", (String) null)   = "any"
         * StringUtils.removePattern("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")  = "AB"
         * StringUtils.removePattern("ABCabc123", "[a-z]")    = "ABC123"
         * </pre>
         *
         * @param source
         *            the source string
         * @param regex
         *            the regular expression to which this string is to be matched
         * @return The resulting {@code String}
         * @see #replacePattern(String, String, String)
         * @see String#replaceAll(String, String)
         * @see Pattern#DOTALL
         * @since 3.2
         * @since 3.5 Changed {@code null} reference passed to this method is a no-op.
         *
         * @deprecated Moved to RegExUtils.
         */
        @Deprecated
        String removePattern(String source, String regex);

        /**
         * <p>Removes a substring only if it is at the beginning of a source string,
         * otherwise returns the source string.</p>
         *
         * <p>A {@code null} source string will return {@code null}.
         * An empty ("") source string will return the empty string.
         * A {@code null} search string will return the source string.</p>
         *
         * <pre>
         * StringUtils.removeStart(null, *)      = null
         * StringUtils.removeStart("", *)        = ""
         * StringUtils.removeStart(*, null)      = *
         * StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
         * StringUtils.removeStart("domain.com", "www.")       = "domain.com"
         * StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com"
         * StringUtils.removeStart("abc", "")    = "abc"
         * </pre>
         *
         * @param str  the source String to search, may be null
         * @param remove  the String to search for and remove, may be null
         * @return the substring with the string removed if found,
         *  {@code null} if null String input
         * @since 2.1
         */
        String removeStart(String str, String remove);

        /**
         * <p>Case insensitive removal of a substring if it is at the beginning of a source string,
         * otherwise returns the source string.</p>
         *
         * <p>A {@code null} source string will return {@code null}.
         * An empty ("") source string will return the empty string.
         * A {@code null} search string will return the source string.</p>
         *
         * <pre>
         * StringUtils.removeStartIgnoreCase(null, *)      = null
         * StringUtils.removeStartIgnoreCase("", *)        = ""
         * StringUtils.removeStartIgnoreCase(*, null)      = *
         * StringUtils.removeStartIgnoreCase("www.domain.com", "www.")   = "domain.com"
         * StringUtils.removeStartIgnoreCase("www.domain.com", "WWW.")   = "domain.com"
         * StringUtils.removeStartIgnoreCase("domain.com", "www.")       = "domain.com"
         * StringUtils.removeStartIgnoreCase("www.domain.com", "domain") = "www.domain.com"
         * StringUtils.removeStartIgnoreCase("abc", "")    = "abc"
         * </pre>
         *
         * @param str  the source String to search, may be null
         * @param remove  the String to search for (case insensitive) and remove, may be null
         * @return the substring with the string removed if found,
         *  {@code null} if null String input
         * @since 2.4
         */
        String removeStartIgnoreCase(String str, String remove);

        /**
         * <p>Returns padding using the specified delimiter repeated
         * to a given length.</p>
         *
         * <pre>
         * StringUtils.repeat('e', 0)  = ""
         * StringUtils.repeat('e', 3)  = "eee"
         * StringUtils.repeat('e', -2) = ""
         * </pre>
         *
         * <p>Note: this method does not support padding with
         * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
         * as they require a pair of {@code char}s to be represented.
         * If you are needing to support full I18N of your applications
         * consider using {@link #repeat(String, int)} instead.
         * </p>
         *
         * @param ch  character to repeat
         * @param repeat  number of times to repeat char, negative treated as zero
         * @return String with repeated character
         * @see #repeat(String, int)
         */
        String repeat(char ch, int repeat);

        /**
         * <p>Repeat a String {@code repeat} times to form a
         * new String.</p>
         *
         * <pre>
         * StringUtils.repeat(null, 2) = null
         * StringUtils.repeat("", 0)   = ""
         * StringUtils.repeat("", 2)   = ""
         * StringUtils.repeat("a", 3)  = "aaa"
         * StringUtils.repeat("ab", 2) = "abab"
         * StringUtils.repeat("a", -2) = ""
         * </pre>
         *
         * @param str  the String to repeat, may be null
         * @param repeat  number of times to repeat str, negative treated as zero
         * @return a new String consisting of the original String repeated,
         *  {@code null} if null String input
         */
        String repeat(String str, int repeat);

        /**
         * <p>Repeat a String {@code repeat} times to form a
         * new String, with a String separator injected each time. </p>
         *
         * <pre>
         * StringUtils.repeat(null, null, 2) = null
         * StringUtils.repeat(null, "x", 2)  = null
         * StringUtils.repeat("", null, 0)   = ""
         * StringUtils.repeat("", "", 2)     = ""
         * StringUtils.repeat("", "x", 3)    = "xxx"
         * StringUtils.repeat("?", ", ", 3)  = "?, ?, ?"
         * </pre>
         *
         * @param str        the String to repeat, may be null
         * @param separator  the String to inject, may be null
         * @param repeat     number of times to repeat str, negative treated as zero
         * @return a new String consisting of the original String repeated,
         *  {@code null} if null String input
         * @since 2.5
         */
        String repeat(String str, String separator, int repeat);

        /**
         * <p>Replaces all occurrences of a String within another String.</p>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replace(null, *, *)        = null
         * StringUtils.replace("", *, *)          = ""
         * StringUtils.replace("any", null, *)    = "any"
         * StringUtils.replace("any", *, null)    = "any"
         * StringUtils.replace("any", "", *)      = "any"
         * StringUtils.replace("aba", "a", null)  = "aba"
         * StringUtils.replace("aba", "a", "")    = "b"
         * StringUtils.replace("aba", "a", "z")   = "zbz"
         * </pre>
         *
         * @see #replace(String text, String searchString, String replacement, int max)
         * @param text  text to search and replace in, may be null
         * @param searchString  the String to search for, may be null
         * @param replacement  the String to replace it with, may be null
         * @return the text with any replacements processed,
         *  {@code null} if null String input
         */
        String replace(String text, String searchString, String replacement);

        /**
         * <p>Replaces a String with another String inside a larger String,
         * for the first {@code max} values of the search String.</p>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replace(null, *, *, *)         = null
         * StringUtils.replace("", *, *, *)           = ""
         * StringUtils.replace("any", null, *, *)     = "any"
         * StringUtils.replace("any", *, null, *)     = "any"
         * StringUtils.replace("any", "", *, *)       = "any"
         * StringUtils.replace("any", *, *, 0)        = "any"
         * StringUtils.replace("abaa", "a", null, -1) = "abaa"
         * StringUtils.replace("abaa", "a", "", -1)   = "b"
         * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
         * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
         * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
         * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
         * </pre>
         *
         * @param text  text to search and replace in, may be null
         * @param searchString  the String to search for, may be null
         * @param replacement  the String to replace it with, may be null
         * @param max  maximum number of values to replace, or {@code -1} if no maximum
         * @return the text with any replacements processed,
         *  {@code null} if null String input
         */
        String replace(String text, String searchString, String replacement, int max);

        /**
         * <p>Replaces each substring of the text String that matches the given regular expression
         * with the given replacement.</p>
         *
         * This method is a {@code null} safe equivalent to:
         * <ul>
         *  <li>{@code text.replaceAll(regex, replacement)}</li>
         *  <li>{@code Pattern.compile(regex).matcher(text).replaceAll(replacement)}</li>
         * </ul>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <p>Unlike in the {@link #replacePattern(String, String, String)} method, the {@link Pattern#DOTALL} option
         * is NOT automatically added.
         * To use the DOTALL option prepend {@code "(?s)"} to the regex.
         * DOTALL is also known as single-line mode in Perl.</p>
         *
         * <pre>
         * StringUtils.replaceAll(null, *, *)       = null
         * StringUtils.replaceAll("any", (String) null, *)   = "any"
         * StringUtils.replaceAll("any", *, null)   = "any"
         * StringUtils.replaceAll("", "", "zzz")    = "zzz"
         * StringUtils.replaceAll("", ".*", "zzz")  = "zzz"
         * StringUtils.replaceAll("", ".+", "zzz")  = ""
         * StringUtils.replaceAll("abc", "", "ZZ")  = "ZZaZZbZZcZZ"
         * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\nz"
         * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
         * StringUtils.replaceAll("ABCabc123", "[a-z]", "_")       = "ABC___123"
         * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
         * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
         * StringUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
         * </pre>
         *
         * @param text  text to search and replace in, may be null
         * @param regex  the regular expression to which this string is to be matched
         * @param replacement  the string to be substituted for each match
         * @return  the text with any replacements processed,
         *              {@code null} if null String input
         *
         * @throws  java.util.regex.PatternSyntaxException
         *              if the regular expression's syntax is invalid
         *
         * @see #replacePattern(String, String, String)
         * @see String#replaceAll(String, String)
         * @see Pattern
         * @see Pattern#DOTALL
         * @since 3.5
         *
         * @deprecated Moved to RegExUtils.
         */
        @Deprecated
        String replaceAll(String text, String regex, String replacement);

        /**
         * <p>Replaces all occurrences of a character in a String with another.
         * This is a null-safe version of {@link String#replace(char, char)}.</p>
         *
         * <p>A {@code null} string input returns {@code null}.
         * An empty ("") string input returns an empty string.</p>
         *
         * <pre>
         * StringUtils.replaceChars(null, *, *)        = null
         * StringUtils.replaceChars("", *, *)          = ""
         * StringUtils.replaceChars("abcba", 'b', 'y') = "aycya"
         * StringUtils.replaceChars("abcba", 'z', 'y') = "abcba"
         * </pre>
         *
         * @param str  String to replace characters in, may be null
         * @param searchChar  the character to search for, may be null
         * @param replaceChar  the character to replace, may be null
         * @return modified String, {@code null} if null string input
         * @since 2.0
         */
        String replaceChars(String str, char searchChar, char replaceChar);

        /**
         * <p>Replaces multiple characters in a String in one go.
         * This method can also be used to delete characters.</p>
         *
         * <p>For example:<br>
         * {@code replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly}.</p>
         *
         * <p>A {@code null} string input returns {@code null}.
         * An empty ("") string input returns an empty string.
         * A null or empty set of search characters returns the input string.</p>
         *
         * <p>The length of the search characters should normally equal the length
         * of the replace characters.
         * If the search characters is longer, then the extra search characters
         * are deleted.
         * If the search characters is shorter, then the extra replace characters
         * are ignored.</p>
         *
         * <pre>
         * StringUtils.replaceChars(null, *, *)           = null
         * StringUtils.replaceChars("", *, *)             = ""
         * StringUtils.replaceChars("abc", null, *)       = "abc"
         * StringUtils.replaceChars("abc", "", *)         = "abc"
         * StringUtils.replaceChars("abc", "b", null)     = "ac"
         * StringUtils.replaceChars("abc", "b", "")       = "ac"
         * StringUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
         * StringUtils.replaceChars("abcba", "bc", "y")   = "ayya"
         * StringUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
         * </pre>
         *
         * @param str  String to replace characters in, may be null
         * @param searchChars  a set of characters to search for, may be null
         * @param replaceChars  a set of characters to replace, may be null
         * @return modified String, {@code null} if null string input
         * @since 2.0
         */
        String replaceChars(String str, String searchChars, String replaceChars);

        /**
         * <p>
         * Replaces all occurrences of Strings within another String.
         * </p>
         *
         * <p>
         * A {@code null} reference passed to this method is a no-op, or if
         * any "search string" or "string to replace" is null, that replace will be
         * ignored. This will not repeat. For repeating replaces, call the
         * overloaded method.
         * </p>
         *
         * <pre>
         *  StringUtils.replaceEach(null, *, *)        = null
         *  StringUtils.replaceEach("", *, *)          = ""
         *  StringUtils.replaceEach("aba", null, null) = "aba"
         *  StringUtils.replaceEach("aba", new String[0], null) = "aba"
         *  StringUtils.replaceEach("aba", null, new String[0]) = "aba"
         *  StringUtils.replaceEach("aba", new String[]{"a"}, null)  = "aba"
         *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
         *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
         *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
         *  (example of how it does not repeat)
         *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
         * </pre>
         *
         * @param text
         *            text to search and replace in, no-op if null
         * @param searchList
         *            the Strings to search for, no-op if null
         * @param replacementList
         *            the Strings to replace them with, no-op if null
         * @return the text with any replacements processed, {@code null} if
         *         null String input
         * @throws IllegalArgumentException
         *             if the lengths of the arrays are not the same (null is ok,
         *             and/or size 0)
         * @since 2.4
         */
        String replaceEach(String text, String[] searchList, String[] replacementList);

        /**
         * <p>
         * Replaces all occurrences of Strings within another String.
         * </p>
         *
         * <p>
         * A {@code null} reference passed to this method is a no-op, or if
         * any "search string" or "string to replace" is null, that replace will be
         * ignored.
         * </p>
         *
         * <pre>
         *  StringUtils.replaceEachRepeatedly(null, *, *) = null
         *  StringUtils.replaceEachRepeatedly("", *, *) = ""
         *  StringUtils.replaceEachRepeatedly("aba", null, null) = "aba"
         *  StringUtils.replaceEachRepeatedly("aba", new String[0], null) = "aba"
         *  StringUtils.replaceEachRepeatedly("aba", null, new String[0]) = "aba"
         *  StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, null) = "aba"
         *  StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, new String[]{""}) = "b"
         *  StringUtils.replaceEachRepeatedly("aba", new String[]{null}, new String[]{"a"}) = "aba"
         *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}) = "wcte"
         *  (example of how it repeats)
         *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}) = "tcte"
         *  StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"}) = IllegalStateException
         * </pre>
         *
         * @param text
         *            text to search and replace in, no-op if null
         * @param searchList
         *            the Strings to search for, no-op if null
         * @param replacementList
         *            the Strings to replace them with, no-op if null
         * @return the text with any replacements processed, {@code null} if
         *         null String input
         * @throws IllegalStateException
         *             if the search is repeating and there is an endless loop due
         *             to outputs of one being inputs to another
         * @throws IllegalArgumentException
         *             if the lengths of the arrays are not the same (null is ok,
         *             and/or size 0)
         * @since 2.4
         */
        String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList);

        /**
         * <p>Replaces the first substring of the text string that matches the given regular expression
         * with the given replacement.</p>
         *
         * This method is a {@code null} safe equivalent to:
         * <ul>
         *  <li>{@code text.replaceFirst(regex, replacement)}</li>
         *  <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(replacement)}</li>
         * </ul>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <p>The {@link Pattern#DOTALL} option is NOT automatically added.
         * To use the DOTALL option prepend {@code "(?s)"} to the regex.
         * DOTALL is also known as single-line mode in Perl.</p>
         *
         * <pre>
         * StringUtils.replaceFirst(null, *, *)       = null
         * StringUtils.replaceFirst("any", (String) null, *)   = "any"
         * StringUtils.replaceFirst("any", *, null)   = "any"
         * StringUtils.replaceFirst("", "", "zzz")    = "zzz"
         * StringUtils.replaceFirst("", ".*", "zzz")  = "zzz"
         * StringUtils.replaceFirst("", ".+", "zzz")  = ""
         * StringUtils.replaceFirst("abc", "", "ZZ")  = "ZZabc"
         * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\n&lt;__&gt;"
         * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
         * StringUtils.replaceFirst("ABCabc123", "[a-z]", "_")          = "ABC_bc123"
         * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_")  = "ABC_123abc"
         * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "")   = "ABC123abc"
         * StringUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum  dolor   sit"
         * </pre>
         *
         * @param text  text to search and replace in, may be null
         * @param regex  the regular expression to which this string is to be matched
         * @param replacement  the string to be substituted for the first match
         * @return  the text with the first replacement processed,
         *              {@code null} if null String input
         *
         * @throws  java.util.regex.PatternSyntaxException
         *              if the regular expression's syntax is invalid
         *
         * @see String#replaceFirst(String, String)
         * @see Pattern
         * @see Pattern#DOTALL
         * @since 3.5
         *
         * @deprecated Moved to RegExUtils.
         */
        @Deprecated
        String replaceFirst(String text, String regex, String replacement);

        /**
         * <p>Case insensitively replaces all occurrences of a String within another String.</p>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replaceIgnoreCase(null, *, *)        = null
         * StringUtils.replaceIgnoreCase("", *, *)          = ""
         * StringUtils.replaceIgnoreCase("any", null, *)    = "any"
         * StringUtils.replaceIgnoreCase("any", *, null)    = "any"
         * StringUtils.replaceIgnoreCase("any", "", *)      = "any"
         * StringUtils.replaceIgnoreCase("aba", "a", null)  = "aba"
         * StringUtils.replaceIgnoreCase("abA", "A", "")    = "b"
         * StringUtils.replaceIgnoreCase("aba", "A", "z")   = "zbz"
         * </pre>
         *
         * @see #replaceIgnoreCase(String text, String searchString, String replacement, int max)
         * @param text  text to search and replace in, may be null
         * @param searchString  the String to search for (case insensitive), may be null
         * @param replacement  the String to replace it with, may be null
         * @return the text with any replacements processed,
         *  {@code null} if null String input
         * @since 3.5
         */
        String replaceIgnoreCase(String text, String searchString, String replacement);

        /**
         * <p>Case insensitively replaces a String with another String inside a larger String,
         * for the first {@code max} values of the search String.</p>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replaceIgnoreCase(null, *, *, *)         = null
         * StringUtils.replaceIgnoreCase("", *, *, *)           = ""
         * StringUtils.replaceIgnoreCase("any", null, *, *)     = "any"
         * StringUtils.replaceIgnoreCase("any", *, null, *)     = "any"
         * StringUtils.replaceIgnoreCase("any", "", *, *)       = "any"
         * StringUtils.replaceIgnoreCase("any", *, *, 0)        = "any"
         * StringUtils.replaceIgnoreCase("abaa", "a", null, -1) = "abaa"
         * StringUtils.replaceIgnoreCase("abaa", "a", "", -1)   = "b"
         * StringUtils.replaceIgnoreCase("abaa", "a", "z", 0)   = "abaa"
         * StringUtils.replaceIgnoreCase("abaa", "A", "z", 1)   = "zbaa"
         * StringUtils.replaceIgnoreCase("abAa", "a", "z", 2)   = "zbza"
         * StringUtils.replaceIgnoreCase("abAa", "a", "z", -1)  = "zbzz"
         * </pre>
         *
         * @param text  text to search and replace in, may be null
         * @param searchString  the String to search for (case insensitive), may be null
         * @param replacement  the String to replace it with, may be null
         * @param max  maximum number of values to replace, or {@code -1} if no maximum
         * @return the text with any replacements processed,
         *  {@code null} if null String input
         * @since 3.5
         */
        String replaceIgnoreCase(String text, String searchString, String replacement, int max);

        /**
         * <p>Replaces a String with another String inside a larger String, once.</p>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replaceOnce(null, *, *)        = null
         * StringUtils.replaceOnce("", *, *)          = ""
         * StringUtils.replaceOnce("any", null, *)    = "any"
         * StringUtils.replaceOnce("any", *, null)    = "any"
         * StringUtils.replaceOnce("any", "", *)      = "any"
         * StringUtils.replaceOnce("aba", "a", null)  = "aba"
         * StringUtils.replaceOnce("aba", "a", "")    = "ba"
         * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
         * </pre>
         *
         * @see #replace(String text, String searchString, String replacement, int max)
         * @param text  text to search and replace in, may be null
         * @param searchString  the String to search for, may be null
         * @param replacement  the String to replace with, may be null
         * @return the text with any replacements processed,
         *  {@code null} if null String input
         */
        String replaceOnce(String text, String searchString, String replacement);

        /**
         * <p>Case insensitively replaces a String with another String inside a larger String, once.</p>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replaceOnceIgnoreCase(null, *, *)        = null
         * StringUtils.replaceOnceIgnoreCase("", *, *)          = ""
         * StringUtils.replaceOnceIgnoreCase("any", null, *)    = "any"
         * StringUtils.replaceOnceIgnoreCase("any", *, null)    = "any"
         * StringUtils.replaceOnceIgnoreCase("any", "", *)      = "any"
         * StringUtils.replaceOnceIgnoreCase("aba", "a", null)  = "aba"
         * StringUtils.replaceOnceIgnoreCase("aba", "a", "")    = "ba"
         * StringUtils.replaceOnceIgnoreCase("aba", "a", "z")   = "zba"
         * StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "") = "Foofoo"
         * </pre>
         *
         * @see #replaceIgnoreCase(String text, String searchString, String replacement, int max)
         * @param text  text to search and replace in, may be null
         * @param searchString  the String to search for (case insensitive), may be null
         * @param replacement  the String to replace with, may be null
         * @return the text with any replacements processed,
         *  {@code null} if null String input
         * @since 3.5
         */
        String replaceOnceIgnoreCase(String text, String searchString, String replacement);

        /**
         * <p>Replaces each substring of the source String that matches the given regular expression with the given
         * replacement using the {@link Pattern#DOTALL} option. DOTALL is also known as single-line mode in Perl.</p>
         *
         * This call is a {@code null} safe equivalent to:
         * <ul>
         * <li>{@code source.replaceAll(&quot;(?s)&quot; + regex, replacement)}</li>
         * <li>{@code Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(replacement)}</li>
         * </ul>
         *
         * <p>A {@code null} reference passed to this method is a no-op.</p>
         *
         * <pre>
         * StringUtils.replacePattern(null, *, *)       = null
         * StringUtils.replacePattern("any", (String) null, *)   = "any"
         * StringUtils.replacePattern("any", *, null)   = "any"
         * StringUtils.replacePattern("", "", "zzz")    = "zzz"
         * StringUtils.replacePattern("", ".*", "zzz")  = "zzz"
         * StringUtils.replacePattern("", ".+", "zzz")  = ""
         * StringUtils.replacePattern("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")       = "z"
         * StringUtils.replacePattern("ABCabc123", "[a-z]", "_")       = "ABC___123"
         * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
         * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
         * StringUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
         * </pre>
         *
         * @param source
         *            the source string
         * @param regex
         *            the regular expression to which this string is to be matched
         * @param replacement
         *            the string to be substituted for each match
         * @return The resulting {@code String}
         * @see #replaceAll(String, String, String)
         * @see String#replaceAll(String, String)
         * @see Pattern#DOTALL
         * @since 3.2
         * @since 3.5 Changed {@code null} reference passed to this method is a no-op.
         *
         * @deprecated Moved to RegExUtils.
         */
        @Deprecated
        String replacePattern(String source, String regex, String replacement);

        /**
         * <p>Reverses a String as per {@link StringBuilder#reverse()}.</p>
         *
         * <p>A {@code null} String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.reverse(null)  = null
         * StringUtils.reverse("")    = ""
         * StringUtils.reverse("bat") = "tab"
         * </pre>
         *
         * @param str  the String to reverse, may be null
         * @return the reversed String, {@code null} if null String input
         */
        String reverse(String str);

        /**
         * <p>Reverses a String that is delimited by a specific character.</p>
         *
         * <p>The Strings between the delimiters are not reversed.
         * Thus java.lang.String becomes String.lang.java (if the delimiter
         * is {@code '.'}).</p>
         *
         * <pre>
         * StringUtils.reverseDelimited(null, *)      = null
         * StringUtils.reverseDelimited("", *)        = ""
         * StringUtils.reverseDelimited("a.b.c", 'x') = "a.b.c"
         * StringUtils.reverseDelimited("a.b.c", ".") = "c.b.a"
         * </pre>
         *
         * @param str  the String to reverse, may be null
         * @param separatorChar  the separator character to use
         * @return the reversed String, {@code null} if null String input
         * @since 2.0
         */
        String reverseDelimited(String str, char separatorChar);

        /**
         * <p>Gets the rightmost {@code len} characters of a String.</p>
         *
         * <p>If {@code len} characters are not available, or the String
         * is {@code null}, the String will be returned without an
         * an exception. An empty String is returned if len is negative.</p>
         *
         * <pre>
         * StringUtils.right(null, *)    = null
         * StringUtils.right(*, -ve)     = ""
         * StringUtils.right("", *)      = ""
         * StringUtils.right("abc", 0)   = ""
         * StringUtils.right("abc", 2)   = "bc"
         * StringUtils.right("abc", 4)   = "abc"
         * </pre>
         *
         * @param str  the String to get the rightmost characters from, may be null
         * @param len  the length of the required String
         * @return the rightmost characters, {@code null} if null String input
         */
        String right(String str, int len);

        /**
         * <p>Right pad a String with spaces (' ').</p>
         *
         * <p>The String is padded to the size of {@code size}.</p>
         *
         * <pre>
         * StringUtils.rightPad(null, *)   = null
         * StringUtils.rightPad("", 3)     = "   "
         * StringUtils.rightPad("bat", 3)  = "bat"
         * StringUtils.rightPad("bat", 5)  = "bat  "
         * StringUtils.rightPad("bat", 1)  = "bat"
         * StringUtils.rightPad("bat", -1) = "bat"
         * </pre>
         *
         * @param str  the String to pad out, may be null
         * @param size  the size to pad to
         * @return right padded String or original String if no padding is necessary,
         *  {@code null} if null String input
         */
        String rightPad(String str, int size);

        /**
         * <p>Right pad a String with a specified character.</p>
         *
         * <p>The String is padded to the size of {@code size}.</p>
         *
         * <pre>
         * StringUtils.rightPad(null, *, *)     = null
         * StringUtils.rightPad("", 3, 'z')     = "zzz"
         * StringUtils.rightPad("bat", 3, 'z')  = "bat"
         * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
         * StringUtils.rightPad("bat", 1, 'z')  = "bat"
         * StringUtils.rightPad("bat", -1, 'z') = "bat"
         * </pre>
         *
         * @param str  the String to pad out, may be null
         * @param size  the size to pad to
         * @param padChar  the character to pad with
         * @return right padded String or original String if no padding is necessary,
         *  {@code null} if null String input
         * @since 2.0
         */
        String rightPad(String str, int size, char padChar);

        /**
         * <p>Right pad a String with a specified String.</p>
         *
         * <p>The String is padded to the size of {@code size}.</p>
         *
         * <pre>
         * StringUtils.rightPad(null, *, *)      = null
         * StringUtils.rightPad("", 3, "z")      = "zzz"
         * StringUtils.rightPad("bat", 3, "yz")  = "bat"
         * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
         * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
         * StringUtils.rightPad("bat", 1, "yz")  = "bat"
         * StringUtils.rightPad("bat", -1, "yz") = "bat"
         * StringUtils.rightPad("bat", 5, null)  = "bat  "
         * StringUtils.rightPad("bat", 5, "")    = "bat  "
         * </pre>
         *
         * @param str  the String to pad out, may be null
         * @param size  the size to pad to
         * @param padStr  the String to pad with, null or empty treated as single space
         * @return right padded String or original String if no padding is necessary,
         *  {@code null} if null String input
         */
        String rightPad(String str, int size, String padStr);

        /**
         * <p>Rotate (circular shift) a String of {@code shift} characters.</p>
         * <ul>
         *  <li>If {@code shift > 0}, right circular shift (ex : ABCDEF =&gt; FABCDE)</li>
         *  <li>If {@code shift < 0}, left circular shift (ex : ABCDEF =&gt; BCDEFA)</li>
         * </ul>
         *
         * <pre>
         * StringUtils.rotate(null, *)        = null
         * StringUtils.rotate("", *)          = ""
         * StringUtils.rotate("abcdefg", 0)   = "abcdefg"
         * StringUtils.rotate("abcdefg", 2)   = "fgabcde"
         * StringUtils.rotate("abcdefg", -2)  = "cdefgab"
         * StringUtils.rotate("abcdefg", 7)   = "abcdefg"
         * StringUtils.rotate("abcdefg", -7)  = "abcdefg"
         * StringUtils.rotate("abcdefg", 9)   = "fgabcde"
         * StringUtils.rotate("abcdefg", -9)  = "cdefgab"
         * </pre>
         *
         * @param str  the String to rotate, may be null
         * @param shift  number of time to shift (positive : right shift, negative : left shift)
         * @return the rotated String,
         *          or the original String if {@code shift == 0},
         *          or {@code null} if null String input
         * @since 3.5
         */
        String rotate(String str, int shift);

        /**
         * <p>Splits the provided text into an array, using whitespace as the
         * separator.
         * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as one separator.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.split(null)       = null
         * StringUtils.split("")         = []
         * StringUtils.split("abc def")  = ["abc", "def"]
         * StringUtils.split("abc  def") = ["abc", "def"]
         * StringUtils.split(" abc ")    = ["abc"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @return an array of parsed Strings, {@code null} if null String input
         */
        String[] split(String str);

        /**
         * <p>Splits the provided text into an array, separator specified.
         * This is an alternative to using StringTokenizer.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as one separator.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.split(null, *)         = null
         * StringUtils.split("", *)           = []
         * StringUtils.split("a.b.c", '.')    = ["a", "b", "c"]
         * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
         * StringUtils.split("a:b:c", '.')    = ["a:b:c"]
         * StringUtils.split("a b c", ' ')    = ["a", "b", "c"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separatorChar  the character used as the delimiter
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.0
         */
        String[] split(String str, char separatorChar);

        /**
         * <p>Splits the provided text into an array, separators specified.
         * This is an alternative to using StringTokenizer.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as one separator.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separatorChars splits on whitespace.</p>
         *
         * <pre>
         * StringUtils.split(null, *)         = null
         * StringUtils.split("", *)           = []
         * StringUtils.split("abc def", null) = ["abc", "def"]
         * StringUtils.split("abc def", " ")  = ["abc", "def"]
         * StringUtils.split("abc  def", " ") = ["abc", "def"]
         * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separatorChars  the characters used as the delimiters,
         *  {@code null} splits on whitespace
         * @return an array of parsed Strings, {@code null} if null String input
         */
        String[] split(String str, String separatorChars);

        /**
         * <p>Splits the provided text into an array with a maximum length,
         * separators specified.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as one separator.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separatorChars splits on whitespace.</p>
         *
         * <p>If more than {@code max} delimited substrings are found, the last
         * returned string includes all characters after the first {@code max - 1}
         * returned strings (including separator characters).</p>
         *
         * <pre>
         * StringUtils.split(null, *, *)            = null
         * StringUtils.split("", *, *)              = []
         * StringUtils.split("ab cd ef", null, 0)   = ["ab", "cd", "ef"]
         * StringUtils.split("ab   cd ef", null, 0) = ["ab", "cd", "ef"]
         * StringUtils.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
         * StringUtils.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separatorChars  the characters used as the delimiters,
         *  {@code null} splits on whitespace
         * @param max  the maximum number of elements to include in the
         *  array. A zero or negative value implies no limit
         * @return an array of parsed Strings, {@code null} if null String input
         */
        String[] split(String str, String separatorChars, int max);

        /**
         * <p>Splits a String by Character type as returned by
         * {@code java.lang.Character.getType(char)}. Groups of contiguous
         * characters of the same type are returned as complete tokens.
         * <pre>
         * StringUtils.splitByCharacterType(null)         = null
         * StringUtils.splitByCharacterType("")           = []
         * StringUtils.splitByCharacterType("ab de fg")   = ["ab", " ", "de", " ", "fg"]
         * StringUtils.splitByCharacterType("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
         * StringUtils.splitByCharacterType("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
         * StringUtils.splitByCharacterType("number5")    = ["number", "5"]
         * StringUtils.splitByCharacterType("fooBar")     = ["foo", "B", "ar"]
         * StringUtils.splitByCharacterType("foo200Bar")  = ["foo", "200", "B", "ar"]
         * StringUtils.splitByCharacterType("ASFRules")   = ["ASFR", "ules"]
         * </pre>
         * @param str the String to split, may be {@code null}
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.4
         */
        String[] splitByCharacterType(String str);

        /**
         * <p>Splits a String by Character type as returned by
         * {@code java.lang.Character.getType(char)}. Groups of contiguous
         * characters of the same type are returned as complete tokens, with the
         * following exception: the character of type
         * {@code Character.UPPERCASE_LETTER}, if any, immediately
         * preceding a token of type {@code Character.LOWERCASE_LETTER}
         * will belong to the following token rather than to the preceding, if any,
         * {@code Character.UPPERCASE_LETTER} token.
         * <pre>
         * StringUtils.splitByCharacterTypeCamelCase(null)         = null
         * StringUtils.splitByCharacterTypeCamelCase("")           = []
         * StringUtils.splitByCharacterTypeCamelCase("ab de fg")   = ["ab", " ", "de", " ", "fg"]
         * StringUtils.splitByCharacterTypeCamelCase("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
         * StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
         * StringUtils.splitByCharacterTypeCamelCase("number5")    = ["number", "5"]
         * StringUtils.splitByCharacterTypeCamelCase("fooBar")     = ["foo", "Bar"]
         * StringUtils.splitByCharacterTypeCamelCase("foo200Bar")  = ["foo", "200", "Bar"]
         * StringUtils.splitByCharacterTypeCamelCase("ASFRules")   = ["ASF", "Rules"]
         * </pre>
         * @param str the String to split, may be {@code null}
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.4
         */
        String[] splitByCharacterTypeCamelCase(String str);

        /**
         * <p>Splits the provided text into an array, separator string specified.</p>
         *
         * <p>The separator(s) will not be included in the returned String array.
         * Adjacent separators are treated as one separator.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separator splits on whitespace.</p>
         *
         * <pre>
         * StringUtils.splitByWholeSeparator(null, *)               = null
         * StringUtils.splitByWholeSeparator("", *)                 = []
         * StringUtils.splitByWholeSeparator("ab de fg", null)      = ["ab", "de", "fg"]
         * StringUtils.splitByWholeSeparator("ab   de fg", null)    = ["ab", "de", "fg"]
         * StringUtils.splitByWholeSeparator("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
         * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separator  String containing the String to be used as a delimiter,
         *  {@code null} splits on whitespace
         * @return an array of parsed Strings, {@code null} if null String was input
         */
        String[] splitByWholeSeparator(String str, String separator);

        /**
         * <p>Splits the provided text into an array, separator string specified.
         * Returns a maximum of {@code max} substrings.</p>
         *
         * <p>The separator(s) will not be included in the returned String array.
         * Adjacent separators are treated as one separator.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separator splits on whitespace.</p>
         *
         * <pre>
         * StringUtils.splitByWholeSeparator(null, *, *)               = null
         * StringUtils.splitByWholeSeparator("", *, *)                 = []
         * StringUtils.splitByWholeSeparator("ab de fg", null, 0)      = ["ab", "de", "fg"]
         * StringUtils.splitByWholeSeparator("ab   de fg", null, 0)    = ["ab", "de", "fg"]
         * StringUtils.splitByWholeSeparator("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
         * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
         * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separator  String containing the String to be used as a delimiter,
         *  {@code null} splits on whitespace
         * @param max  the maximum number of elements to include in the returned
         *  array. A zero or negative value implies no limit.
         * @return an array of parsed Strings, {@code null} if null String was input
         */
        String[] splitByWholeSeparator(String str, String separator, int max);

        /**
         * <p>Splits the provided text into an array, separator string specified. </p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as separators for empty tokens.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separator splits on whitespace.</p>
         *
         * <pre>
         * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *)               = null
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *)                 = []
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null)      = ["ab", "de", "fg"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null)    = ["ab", "", "", "de", "fg"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separator  String containing the String to be used as a delimiter,
         *  {@code null} splits on whitespace
         * @return an array of parsed Strings, {@code null} if null String was input
         * @since 2.4
         */
        String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator);

        /**
         * <p>Splits the provided text into an array, separator string specified.
         * Returns a maximum of {@code max} substrings.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as separators for empty tokens.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separator splits on whitespace.</p>
         *
         * <pre>
         * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *, *)               = null
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *, *)                 = []
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null, 0)      = ["ab", "de", "fg"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null, 0)    = ["ab", "", "", "de", "fg"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
         * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
         * </pre>
         *
         * @param str  the String to parse, may be null
         * @param separator  String containing the String to be used as a delimiter,
         *  {@code null} splits on whitespace
         * @param max  the maximum number of elements to include in the returned
         *  array. A zero or negative value implies no limit.
         * @return an array of parsed Strings, {@code null} if null String was input
         * @since 2.4
         */
        String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max);

        /**
         * <p>Splits the provided text into an array, using whitespace as the
         * separator, preserving all tokens, including empty tokens created by
         * adjacent separators. This is an alternative to using StringTokenizer.
         * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as separators for empty tokens.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.splitPreserveAllTokens(null)       = null
         * StringUtils.splitPreserveAllTokens("")         = []
         * StringUtils.splitPreserveAllTokens("abc def")  = ["abc", "def"]
         * StringUtils.splitPreserveAllTokens("abc  def") = ["abc", "", "def"]
         * StringUtils.splitPreserveAllTokens(" abc ")    = ["", "abc", ""]
         * </pre>
         *
         * @param str  the String to parse, may be {@code null}
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.1
         */
        String[] splitPreserveAllTokens(String str);

        /**
         * <p>Splits the provided text into an array, separator specified,
         * preserving all tokens, including empty tokens created by adjacent
         * separators. This is an alternative to using StringTokenizer.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as separators for empty tokens.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.splitPreserveAllTokens(null, *)         = null
         * StringUtils.splitPreserveAllTokens("", *)           = []
         * StringUtils.splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
         * StringUtils.splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
         * StringUtils.splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
         * StringUtils.splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
         * StringUtils.splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
         * StringUtils.splitPreserveAllTokens("a b c ", ' ')   = ["a", "b", "c", ""]
         * StringUtils.splitPreserveAllTokens("a b c  ", ' ')   = ["a", "b", "c", "", ""]
         * StringUtils.splitPreserveAllTokens(" a b c", ' ')   = ["", a", "b", "c"]
         * StringUtils.splitPreserveAllTokens("  a b c", ' ')  = ["", "", a", "b", "c"]
         * StringUtils.splitPreserveAllTokens(" a b c ", ' ')  = ["", a", "b", "c", ""]
         * </pre>
         *
         * @param str  the String to parse, may be {@code null}
         * @param separatorChar  the character used as the delimiter,
         *  {@code null} splits on whitespace
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.1
         */
        String[] splitPreserveAllTokens(String str, char separatorChar);

        /**
         * <p>Splits the provided text into an array, separators specified,
         * preserving all tokens, including empty tokens created by adjacent
         * separators. This is an alternative to using StringTokenizer.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as separators for empty tokens.
         * For more control over the split use the StrTokenizer class.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separatorChars splits on whitespace.</p>
         *
         * <pre>
         * StringUtils.splitPreserveAllTokens(null, *)           = null
         * StringUtils.splitPreserveAllTokens("", *)             = []
         * StringUtils.splitPreserveAllTokens("abc def", null)   = ["abc", "def"]
         * StringUtils.splitPreserveAllTokens("abc def", " ")    = ["abc", "def"]
         * StringUtils.splitPreserveAllTokens("abc  def", " ")   = ["abc", "", def"]
         * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
         * StringUtils.splitPreserveAllTokens("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
         * StringUtils.splitPreserveAllTokens("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
         * StringUtils.splitPreserveAllTokens("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
         * StringUtils.splitPreserveAllTokens(":cd:ef", ":")     = ["", cd", "ef"]
         * StringUtils.splitPreserveAllTokens("::cd:ef", ":")    = ["", "", cd", "ef"]
         * StringUtils.splitPreserveAllTokens(":cd:ef:", ":")    = ["", cd", "ef", ""]
         * </pre>
         *
         * @param str  the String to parse, may be {@code null}
         * @param separatorChars  the characters used as the delimiters,
         *  {@code null} splits on whitespace
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.1
         */
        String[] splitPreserveAllTokens(String str, String separatorChars);

        /**
         * <p>Splits the provided text into an array with a maximum length,
         * separators specified, preserving all tokens, including empty tokens
         * created by adjacent separators.</p>
         *
         * <p>The separator is not included in the returned String array.
         * Adjacent separators are treated as separators for empty tokens.
         * Adjacent separators are treated as one separator.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} separatorChars splits on whitespace.</p>
         *
         * <p>If more than {@code max} delimited substrings are found, the last
         * returned string includes all characters after the first {@code max - 1}
         * returned strings (including separator characters).</p>
         *
         * <pre>
         * StringUtils.splitPreserveAllTokens(null, *, *)            = null
         * StringUtils.splitPreserveAllTokens("", *, *)              = []
         * StringUtils.splitPreserveAllTokens("ab de fg", null, 0)   = ["ab", "de", "fg"]
         * StringUtils.splitPreserveAllTokens("ab   de fg", null, 0) = ["ab", "", "", "de", "fg"]
         * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
         * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
         * StringUtils.splitPreserveAllTokens("ab   de fg", null, 2) = ["ab", "  de fg"]
         * StringUtils.splitPreserveAllTokens("ab   de fg", null, 3) = ["ab", "", " de fg"]
         * StringUtils.splitPreserveAllTokens("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
         * </pre>
         *
         * @param str  the String to parse, may be {@code null}
         * @param separatorChars  the characters used as the delimiters,
         *  {@code null} splits on whitespace
         * @param max  the maximum number of elements to include in the
         *  array. A zero or negative value implies no limit
         * @return an array of parsed Strings, {@code null} if null String input
         * @since 2.1
         */
        String[] splitPreserveAllTokens(String str, String separatorChars, int max);

        /**
         * <p>Check if a CharSequence starts with a specified prefix.</p>
         *
         * <p>{@code null}s are handled without exceptions. Two {@code null}
         * references are considered to be equal. The comparison is case sensitive.</p>
         *
         * <pre>
         * StringUtils.startsWith(null, null)      = true
         * StringUtils.startsWith(null, "abc")     = false
         * StringUtils.startsWith("abcdef", null)  = false
         * StringUtils.startsWith("abcdef", "abc") = true
         * StringUtils.startsWith("ABCDEF", "abc") = false
         * </pre>
         *
         * @see String#startsWith(String)
         * @param str  the CharSequence to check, may be null
         * @param prefix the prefix to find, may be null
         * @return {@code true} if the CharSequence starts with the prefix, case sensitive, or
         *  both {@code null}
         * @since 2.4
         * @since 3.0 Changed signature from startsWith(String, String) to startsWith(CharSequence, CharSequence)
         */
        boolean startsWith(CharSequence str, CharSequence prefix);

        /**
         * <p>Check if a CharSequence starts with any of the provided case-sensitive prefixes.</p>
         *
         * <pre>
         * StringUtils.startsWithAny(null, null)      = false
         * StringUtils.startsWithAny(null, new String[] {"abc"})  = false
         * StringUtils.startsWithAny("abcxyz", null)     = false
         * StringUtils.startsWithAny("abcxyz", new String[] {""}) = true
         * StringUtils.startsWithAny("abcxyz", new String[] {"abc"}) = true
         * StringUtils.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
         * StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX") = false
         * StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc") = false
         * </pre>
         *
         * @param sequence the CharSequence to check, may be null
         * @param searchStrings the case-sensitive CharSequence prefixes, may be empty or contain {@code null}
         * @see org.apache.commons.lang3.StringUtils#startsWith(CharSequence, CharSequence)
         * @return {@code true} if the input {@code sequence} is {@code null} AND no {@code searchStrings} are provided, or
         *   the input {@code sequence} begins with any of the provided case-sensitive {@code searchStrings}.
         * @since 2.5
         * @since 3.0 Changed signature from startsWithAny(String, String[]) to startsWithAny(CharSequence, CharSequence...)
         */
        boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings);

        /**
         * <p>Case insensitive check if a CharSequence starts with a specified prefix.</p>
         *
         * <p>{@code null}s are handled without exceptions. Two {@code null}
         * references are considered to be equal. The comparison is case insensitive.</p>
         *
         * <pre>
         * StringUtils.startsWithIgnoreCase(null, null)      = true
         * StringUtils.startsWithIgnoreCase(null, "abc")     = false
         * StringUtils.startsWithIgnoreCase("abcdef", null)  = false
         * StringUtils.startsWithIgnoreCase("abcdef", "abc") = true
         * StringUtils.startsWithIgnoreCase("ABCDEF", "abc") = true
         * </pre>
         *
         * @see String#startsWith(String)
         * @param str  the CharSequence to check, may be null
         * @param prefix the prefix to find, may be null
         * @return {@code true} if the CharSequence starts with the prefix, case insensitive, or
         *  both {@code null}
         * @since 2.4
         * @since 3.0 Changed signature from startsWithIgnoreCase(String, String) to startsWithIgnoreCase(CharSequence, CharSequence)
         */
        boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix);

        /**
         * <p>Strips whitespace from the start and end of a String.</p>
         *
         * <p>This is similar to {@link #trim(String)} but removes whitespace.
         * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.strip(null)     = null
         * StringUtils.strip("")       = ""
         * StringUtils.strip("   ")    = ""
         * StringUtils.strip("abc")    = "abc"
         * StringUtils.strip("  abc")  = "abc"
         * StringUtils.strip("abc  ")  = "abc"
         * StringUtils.strip(" abc ")  = "abc"
         * StringUtils.strip(" ab c ") = "ab c"
         * </pre>
         *
         * @param str  the String to remove whitespace from, may be null
         * @return the stripped String, {@code null} if null String input
         */
        String strip(String str);

        /**
         * <p>Strips any of a set of characters from the start and end of a String.
         * This is similar to {@link String#trim()} but allows the characters
         * to be stripped to be controlled.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * An empty string ("") input returns the empty string.</p>
         *
         * <p>If the stripChars String is {@code null}, whitespace is
         * stripped as defined by {@link Character#isWhitespace(char)}.
         * Alternatively use {@link #strip(String)}.</p>
         *
         * <pre>
         * StringUtils.strip(null, *)          = null
         * StringUtils.strip("", *)            = ""
         * StringUtils.strip("abc", null)      = "abc"
         * StringUtils.strip("  abc", null)    = "abc"
         * StringUtils.strip("abc  ", null)    = "abc"
         * StringUtils.strip(" abc ", null)    = "abc"
         * StringUtils.strip("  abcyx", "xyz") = "  abc"
         * </pre>
         *
         * @param str  the String to remove characters from, may be null
         * @param stripChars  the characters to remove, null treated as whitespace
         * @return the stripped String, {@code null} if null String input
         */
        String strip(String str, String stripChars);

        /**
         * <p>Removes diacritics (~= accents) from a string. The case will not be altered.</p>
         * <p>For instance, '&agrave;' will be replaced by 'a'.</p>
         * <p>Note that ligatures will be left as is.</p>
         *
         * <pre>
         * StringUtils.stripAccents(null)                = null
         * StringUtils.stripAccents("")                  = ""
         * StringUtils.stripAccents("control")           = "control"
         * StringUtils.stripAccents("&eacute;clair")     = "eclair"
         * </pre>
         *
         * @param input String to be stripped
         * @return input text with diacritics removed
         *
         * @since 3.0
         */
        // See also Lucene's ASCIIFoldingFilter (Lucene 2.9) that replaces accented characters by their unaccented equivalent (and uncommitted bug fix: https://issues.apache.org/jira/browse/LUCENE-1343?focusedCommentId=12858907&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#action_12858907).
        String stripAccents(String input);

        /**
         * <p>Strips whitespace from the start and end of every String in an array.
         * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>A new array is returned each time, except for length zero.
         * A {@code null} array will return {@code null}.
         * An empty array will return itself.
         * A {@code null} array entry will be ignored.</p>
         *
         * <pre>
         * StringUtils.stripAll(null)             = null
         * StringUtils.stripAll([])               = []
         * StringUtils.stripAll(["abc", "  abc"]) = ["abc", "abc"]
         * StringUtils.stripAll(["abc  ", null])  = ["abc", null]
         * </pre>
         *
         * @param strs  the array to remove whitespace from, may be null
         * @return the stripped Strings, {@code null} if null array input
         */
        String[] stripAll(String... strs);

        /**
         * <p>Strips any of a set of characters from the start and end of every
         * String in an array.</p>
         * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <p>A new array is returned each time, except for length zero.
         * A {@code null} array will return {@code null}.
         * An empty array will return itself.
         * A {@code null} array entry will be ignored.
         * A {@code null} stripChars will strip whitespace as defined by
         * {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.stripAll(null, *)                = null
         * StringUtils.stripAll([], *)                  = []
         * StringUtils.stripAll(["abc", "  abc"], null) = ["abc", "abc"]
         * StringUtils.stripAll(["abc  ", null], null)  = ["abc", null]
         * StringUtils.stripAll(["abc  ", null], "yz")  = ["abc  ", null]
         * StringUtils.stripAll(["yabcz", null], "yz")  = ["abc", null]
         * </pre>
         *
         * @param strs  the array to remove characters from, may be null
         * @param stripChars  the characters to remove, null treated as whitespace
         * @return the stripped Strings, {@code null} if null array input
         */
        String[] stripAll(String[] strs, String stripChars);

        /**
         * <p>Strips any of a set of characters from the end of a String.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * An empty string ("") input returns the empty string.</p>
         *
         * <p>If the stripChars String is {@code null}, whitespace is
         * stripped as defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.stripEnd(null, *)          = null
         * StringUtils.stripEnd("", *)            = ""
         * StringUtils.stripEnd("abc", "")        = "abc"
         * StringUtils.stripEnd("abc", null)      = "abc"
         * StringUtils.stripEnd("  abc", null)    = "  abc"
         * StringUtils.stripEnd("abc  ", null)    = "abc"
         * StringUtils.stripEnd(" abc ", null)    = " abc"
         * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
         * StringUtils.stripEnd("120.00", ".0")   = "12"
         * </pre>
         *
         * @param str  the String to remove characters from, may be null
         * @param stripChars  the set of characters to remove, null treated as whitespace
         * @return the stripped String, {@code null} if null String input
         */
        String stripEnd(String str, String stripChars);

        /**
         * <p>Strips any of a set of characters from the start of a String.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * An empty string ("") input returns the empty string.</p>
         *
         * <p>If the stripChars String is {@code null}, whitespace is
         * stripped as defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.stripStart(null, *)          = null
         * StringUtils.stripStart("", *)            = ""
         * StringUtils.stripStart("abc", "")        = "abc"
         * StringUtils.stripStart("abc", null)      = "abc"
         * StringUtils.stripStart("  abc", null)    = "abc"
         * StringUtils.stripStart("abc  ", null)    = "abc  "
         * StringUtils.stripStart(" abc ", null)    = "abc "
         * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
         * </pre>
         *
         * @param str  the String to remove characters from, may be null
         * @param stripChars  the characters to remove, null treated as whitespace
         * @return the stripped String, {@code null} if null String input
         */
        String stripStart(String str, String stripChars);

        /**
         * <p>Strips whitespace from the start and end of a String  returning
         * an empty String if {@code null} input.</p>
         *
         * <p>This is similar to {@link #trimToEmpty(String)} but removes whitespace.
         * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.stripToEmpty(null)     = ""
         * StringUtils.stripToEmpty("")       = ""
         * StringUtils.stripToEmpty("   ")    = ""
         * StringUtils.stripToEmpty("abc")    = "abc"
         * StringUtils.stripToEmpty("  abc")  = "abc"
         * StringUtils.stripToEmpty("abc  ")  = "abc"
         * StringUtils.stripToEmpty(" abc ")  = "abc"
         * StringUtils.stripToEmpty(" ab c ") = "ab c"
         * </pre>
         *
         * @param str  the String to be stripped, may be null
         * @return the trimmed String, or an empty String if {@code null} input
         * @since 2.0
         */
        String stripToEmpty(String str);

        /**
         * <p>Strips whitespace from the start and end of a String  returning
         * {@code null} if the String is empty ("") after the strip.</p>
         *
         * <p>This is similar to {@link #trimToNull(String)} but removes whitespace.
         * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
         *
         * <pre>
         * StringUtils.stripToNull(null)     = null
         * StringUtils.stripToNull("")       = null
         * StringUtils.stripToNull("   ")    = null
         * StringUtils.stripToNull("abc")    = "abc"
         * StringUtils.stripToNull("  abc")  = "abc"
         * StringUtils.stripToNull("abc  ")  = "abc"
         * StringUtils.stripToNull(" abc ")  = "abc"
         * StringUtils.stripToNull(" ab c ") = "ab c"
         * </pre>
         *
         * @param str  the String to be stripped, may be null
         * @return the stripped String,
         *  {@code null} if whitespace, empty or null String input
         * @since 2.0
         */
        String stripToNull(String str);

        /**
         * <p>Gets a substring from the specified String avoiding exceptions.</p>
         *
         * <p>A negative start position can be used to start {@code n}
         * characters from the end of the String.</p>
         *
         * <p>A {@code null} String will return {@code null}.
         * An empty ("") String will return "".</p>
         *
         * <pre>
         * StringUtils.substring(null, *)   = null
         * StringUtils.substring("", *)     = ""
         * StringUtils.substring("abc", 0)  = "abc"
         * StringUtils.substring("abc", 2)  = "c"
         * StringUtils.substring("abc", 4)  = ""
         * StringUtils.substring("abc", -2) = "bc"
         * StringUtils.substring("abc", -4) = "abc"
         * </pre>
         *
         * @param str  the String to get the substring from, may be null
         * @param start  the position to start from, negative means
         *  count back from the end of the String by this many characters
         * @return substring from start position, {@code null} if null String input
         */
        String substring(String str, int start);

        /**
         * <p>Gets a substring from the specified String avoiding exceptions.</p>
         *
         * <p>A negative start position can be used to start/end {@code n}
         * characters from the end of the String.</p>
         *
         * <p>The returned substring starts with the character in the {@code start}
         * position and ends before the {@code end} position. All position counting is
         * zero-based -- i.e., to start at the beginning of the string use
         * {@code start = 0}. Negative start and end positions can be used to
         * specify offsets relative to the end of the String.</p>
         *
         * <p>If {@code start} is not strictly to the left of {@code end}, ""
         * is returned.</p>
         *
         * <pre>
         * StringUtils.substring(null, *, *)    = null
         * StringUtils.substring("", * ,  *)    = "";
         * StringUtils.substring("abc", 0, 2)   = "ab"
         * StringUtils.substring("abc", 2, 0)   = ""
         * StringUtils.substring("abc", 2, 4)   = "c"
         * StringUtils.substring("abc", 4, 6)   = ""
         * StringUtils.substring("abc", 2, 2)   = ""
         * StringUtils.substring("abc", -2, -1) = "b"
         * StringUtils.substring("abc", -4, 2)  = "ab"
         * </pre>
         *
         * @param str  the String to get the substring from, may be null
         * @param start  the position to start from, negative means
         *  count back from the end of the String by this many characters
         * @param end  the position to end at (exclusive), negative means
         *  count back from the end of the String by this many characters
         * @return substring from start position to end position,
         *  {@code null} if null String input
         */
        String substring(String str, int start, int end);

        /**
         * <p>Gets the substring after the first occurrence of a separator.
         * The separator is not returned.</p>
         *
         * <p>A {@code null} string input will return {@code null}.
         * An empty ("") string input will return the empty string.
         *
         * <p>If nothing is found, the empty string is returned.</p>
         *
         * <pre>
         * StringUtils.substringAfter(null, *)      = null
         * StringUtils.substringAfter("", *)        = ""
         * StringUtils.substringAfter("abc", 'a')   = "bc"
         * StringUtils.substringAfter("abcba", 'b') = "cba"
         * StringUtils.substringAfter("abc", 'c')   = ""
         * StringUtils.substringAfter("abc", 'd')   = ""
         * StringUtils.substringAfter(" abc", 32)   = "abc"
         * </pre>
         *
         * @param str  the String to get a substring from, may be null
         * @param separator  the character to search.
         * @return the substring after the first occurrence of the separator,
         *  {@code null} if null String input
         * @since 3.11
         */
        String substringAfter(String str, int separator);

        /**
         * <p>Gets the substring after the first occurrence of a separator.
         * The separator is not returned.</p>
         *
         * <p>A {@code null} string input will return {@code null}.
         * An empty ("") string input will return the empty string.
         * A {@code null} separator will return the empty string if the
         * input string is not {@code null}.</p>
         *
         * <p>If nothing is found, the empty string is returned.</p>
         *
         * <pre>
         * StringUtils.substringAfter(null, *)      = null
         * StringUtils.substringAfter("", *)        = ""
         * StringUtils.substringAfter(*, null)      = ""
         * StringUtils.substringAfter("abc", "a")   = "bc"
         * StringUtils.substringAfter("abcba", "b") = "cba"
         * StringUtils.substringAfter("abc", "c")   = ""
         * StringUtils.substringAfter("abc", "d")   = ""
         * StringUtils.substringAfter("abc", "")    = "abc"
         * </pre>
         *
         * @param str  the String to get a substring from, may be null
         * @param separator  the String to search for, may be null
         * @return the substring after the first occurrence of the separator,
         *  {@code null} if null String input
         * @since 2.0
         */
        String substringAfter(String str, String separator);

        /**
         * <p>Gets the substring after the last occurrence of a separator.
         * The separator is not returned.</p>
         *
         * <p>A {@code null} string input will return {@code null}.
         * An empty ("") string input will return the empty string.
         *
         * <p>If nothing is found, the empty string is returned.</p>
         *
         * <pre>
         * StringUtils.substringAfterLast(null, *)      = null
         * StringUtils.substringAfterLast("", *)        = ""
         * StringUtils.substringAfterLast("abc", 'a')   = "bc"
         * StringUtils.substringAfterLast(" bc", 32)    = "bc"
         * StringUtils.substringAfterLast("abcba", 'b') = "a"
         * StringUtils.substringAfterLast("abc", 'c')   = ""
         * StringUtils.substringAfterLast("a", 'a')     = ""
         * StringUtils.substringAfterLast("a", 'z')     = ""
         * </pre>
         *
         * @param str  the String to get a substring from, may be null
         * @param separator  the String to search for, may be null
         * @return the substring after the last occurrence of the separator,
         *  {@code null} if null String input
         * @since 3.11
         */
        String substringAfterLast(String str, int separator);

        /**
         * <p>Gets the substring after the last occurrence of a separator.
         * The separator is not returned.</p>
         *
         * <p>A {@code null} string input will return {@code null}.
         * An empty ("") string input will return the empty string.
         * An empty or {@code null} separator will return the empty string if
         * the input string is not {@code null}.</p>
         *
         * <p>If nothing is found, the empty string is returned.</p>
         *
         * <pre>
         * StringUtils.substringAfterLast(null, *)      = null
         * StringUtils.substringAfterLast("", *)        = ""
         * StringUtils.substringAfterLast(*, "")        = ""
         * StringUtils.substringAfterLast(*, null)      = ""
         * StringUtils.substringAfterLast("abc", "a")   = "bc"
         * StringUtils.substringAfterLast("abcba", "b") = "a"
         * StringUtils.substringAfterLast("abc", "c")   = ""
         * StringUtils.substringAfterLast("a", "a")     = ""
         * StringUtils.substringAfterLast("a", "z")     = ""
         * </pre>
         *
         * @param str  the String to get a substring from, may be null
         * @param separator  the String to search for, may be null
         * @return the substring after the last occurrence of the separator,
         *  {@code null} if null String input
         * @since 2.0
         */
        String substringAfterLast(String str, String separator);

        /**
         * <p>Gets the substring before the first occurrence of a separator.
         * The separator is not returned.</p>
         *
         * <p>A {@code null} string input will return {@code null}.
         * An empty ("") string input will return the empty string.
         * A {@code null} separator will return the input string.</p>
         *
         * <p>If nothing is found, the string input is returned.</p>
         *
         * <pre>
         * StringUtils.substringBefore(null, *)      = null
         * StringUtils.substringBefore("", *)        = ""
         * StringUtils.substringBefore("abc", "a")   = ""
         * StringUtils.substringBefore("abcba", "b") = "a"
         * StringUtils.substringBefore("abc", "c")   = "ab"
         * StringUtils.substringBefore("abc", "d")   = "abc"
         * StringUtils.substringBefore("abc", "")    = ""
         * StringUtils.substringBefore("abc", null)  = "abc"
         * </pre>
         *
         * @param str  the String to get a substring from, may be null
         * @param separator  the String to search for, may be null
         * @return the substring before the first occurrence of the separator,
         *  {@code null} if null String input
         * @since 2.0
         */
        String substringBefore(String str, String separator);

        /**
         * <p>Gets the substring before the last occurrence of a separator.
         * The separator is not returned.</p>
         *
         * <p>A {@code null} string input will return {@code null}.
         * An empty ("") string input will return the empty string.
         * An empty or {@code null} separator will return the input string.</p>
         *
         * <p>If nothing is found, the string input is returned.</p>
         *
         * <pre>
         * StringUtils.substringBeforeLast(null, *)      = null
         * StringUtils.substringBeforeLast("", *)        = ""
         * StringUtils.substringBeforeLast("abcba", "b") = "abc"
         * StringUtils.substringBeforeLast("abc", "c")   = "ab"
         * StringUtils.substringBeforeLast("a", "a")     = ""
         * StringUtils.substringBeforeLast("a", "z")     = "a"
         * StringUtils.substringBeforeLast("a", null)    = "a"
         * StringUtils.substringBeforeLast("a", "")      = "a"
         * </pre>
         *
         * @param str  the String to get a substring from, may be null
         * @param separator  the String to search for, may be null
         * @return the substring before the last occurrence of the separator,
         *  {@code null} if null String input
         * @since 2.0
         */
        String substringBeforeLast(String str, String separator);

        /**
         * <p>Gets the String that is nested in between two instances of the
         * same String.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} tag returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.substringBetween(null, *)            = null
         * StringUtils.substringBetween("", "")             = ""
         * StringUtils.substringBetween("", "tag")          = null
         * StringUtils.substringBetween("tagabctag", null)  = null
         * StringUtils.substringBetween("tagabctag", "")    = ""
         * StringUtils.substringBetween("tagabctag", "tag") = "abc"
         * </pre>
         *
         * @param str  the String containing the substring, may be null
         * @param tag  the String before and after the substring, may be null
         * @return the substring, {@code null} if no match
         * @since 2.0
         */
        String substringBetween(String str, String tag);

        /**
         * <p>Gets the String that is nested in between two Strings.
         * Only the first match is returned.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} open/close returns {@code null} (no match).
         * An empty ("") open and close returns an empty string.</p>
         *
         * <pre>
         * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
         * StringUtils.substringBetween(null, *, *)          = null
         * StringUtils.substringBetween(*, null, *)          = null
         * StringUtils.substringBetween(*, *, null)          = null
         * StringUtils.substringBetween("", "", "")          = ""
         * StringUtils.substringBetween("", "", "]")         = null
         * StringUtils.substringBetween("", "[", "]")        = null
         * StringUtils.substringBetween("yabcz", "", "")     = ""
         * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
         * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
         * </pre>
         *
         * @param str  the String containing the substring, may be null
         * @param open  the String before the substring, may be null
         * @param close  the String after the substring, may be null
         * @return the substring, {@code null} if no match
         * @since 2.0
         */
        String substringBetween(String str, String open, String close);

        /**
         * <p>Searches a String for substrings delimited by a start and end tag,
         * returning all matching substrings in an array.</p>
         *
         * <p>A {@code null} input String returns {@code null}.
         * A {@code null} open/close returns {@code null} (no match).
         * An empty ("") open/close returns {@code null} (no match).</p>
         *
         * <pre>
         * StringUtils.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
         * StringUtils.substringsBetween(null, *, *)            = null
         * StringUtils.substringsBetween(*, null, *)            = null
         * StringUtils.substringsBetween(*, *, null)            = null
         * StringUtils.substringsBetween("", "[", "]")          = []
         * </pre>
         *
         * @param str  the String containing the substrings, null returns null, empty returns empty
         * @param open  the String identifying the start of the substring, empty returns null
         * @param close  the String identifying the end of the substring, empty returns null
         * @return a String Array of substrings, or {@code null} if no match
         * @since 2.3
         */
        String[] substringsBetween(String str, String open, String close);

        /**
         * <p>Swaps the case of a String changing upper and title case to
         * lower case, and lower case to upper case.</p>
         *
         * <ul>
         *  <li>Upper case character converts to Lower case</li>
         *  <li>Title case character converts to Lower case</li>
         *  <li>Lower case character converts to Upper case</li>
         * </ul>
         *
         * <p>For a word based algorithm, see {@link org.apache.commons.lang3.text.WordUtils#swapCase(String)}.
         * A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.swapCase(null)                 = null
         * StringUtils.swapCase("")                   = ""
         * StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
         * </pre>
         *
         * <p>NOTE: This method changed in Lang version 2.0.
         * It no longer performs a word based algorithm.
         * If you only use ASCII, you will notice no change.
         * That functionality is available in org.apache.commons.lang3.text.WordUtils.</p>
         *
         * @param str  the String to swap case, may be null
         * @return the changed String, {@code null} if null String input
         */
        String swapCase(String str);

        /**
         * <p>Converts a {@code CharSequence} into an array of code points.</p>
         *
         * <p>Valid pairs of surrogate code units will be converted into a single supplementary
         * code point. Isolated surrogate code units (i.e. a high surrogate not followed by a low surrogate or
         * a low surrogate not preceded by a high surrogate) will be returned as-is.</p>
         *
         * <pre>
         * StringUtils.toCodePoints(null)   =  null
         * StringUtils.toCodePoints("")     =  []  // empty array
         * </pre>
         *
         * @param str the character sequence to convert
         * @return an array of code points
         * @since 3.6
         */
        int[] toCodePoints(CharSequence str);

        /**
         * Converts a {@code byte[]} to a String using the specified character encoding.
         *
         * @param bytes
         *            the byte array to read from
         * @param charset
         *            the encoding to use, if null then use the platform default
         * @return a new String
         * @throws NullPointerException
         *             if {@code bytes} is null
         * @since 3.2
         * @since 3.3 No longer throws {@link UnsupportedEncodingException}.
         */
        String toEncodedString(byte[] bytes, Charset charset);

        /**
         * Converts the given source String as a lower-case using the {@link Locale#ROOT} locale in a null-safe manner.
         *
         * @param source A source String or null.
         * @return the given source String as a lower-case using the {@link Locale#ROOT} locale or null.
         * @since 3.10
         */
        String toRootLowerCase(String source);

        /**
         * Converts the given source String as a upper-case using the {@link Locale#ROOT} locale in a null-safe manner.
         *
         * @param source A source String or null.
         * @return the given source String as a upper-case using the {@link Locale#ROOT} locale or null.
         * @since 3.10
         */
        String toRootUpperCase(String source);

        /**
         * Converts a {@code byte[]} to a String using the specified character encoding.
         *
         * @param bytes
         *            the byte array to read from
         * @param charsetName
         *            the encoding to use, if null then use the platform default
         * @return a new String
         * @throws UnsupportedEncodingException
         *             If the named charset is not supported
         * @throws NullPointerException
         *             if the input is null
         * @deprecated use {@link org.apache.commons.lang3.StringUtils#toEncodedString(byte[], Charset)} instead of String constants in your code
         * @since 3.1
         */
        @Deprecated
        String toString(byte[] bytes, String charsetName) throws UnsupportedEncodingException;

        /**
         * <p>Removes control characters (char &lt;= 32) from both
         * ends of this String, handling {@code null} by returning
         * {@code null}.</p>
         *
         * <p>The String is trimmed using {@link String#trim()}.
         * Trim removes start and end characters &lt;= 32.
         * To strip whitespace use {@link #strip(String)}.</p>
         *
         * <p>To trim your choice of characters, use the
         * {@link #strip(String, String)} methods.</p>
         *
         * <pre>
         * StringUtils.trim(null)          = null
         * StringUtils.trim("")            = ""
         * StringUtils.trim("     ")       = ""
         * StringUtils.trim("abc")         = "abc"
         * StringUtils.trim("    abc    ") = "abc"
         * </pre>
         *
         * @param str  the String to be trimmed, may be null
         * @return the trimmed string, {@code null} if null String input
         */
        String trim(String str);

        /**
         * <p>Removes control characters (char &lt;= 32) from both
         * ends of this String returning an empty String ("") if the String
         * is empty ("") after the trim or if it is {@code null}.
         *
         * <p>The String is trimmed using {@link String#trim()}.
         * Trim removes start and end characters &lt;= 32.
         * To strip whitespace use {@link #stripToEmpty(String)}.</p>
         *
         * <pre>
         * StringUtils.trimToEmpty(null)          = ""
         * StringUtils.trimToEmpty("")            = ""
         * StringUtils.trimToEmpty("     ")       = ""
         * StringUtils.trimToEmpty("abc")         = "abc"
         * StringUtils.trimToEmpty("    abc    ") = "abc"
         * </pre>
         *
         * @param str  the String to be trimmed, may be null
         * @return the trimmed String, or an empty String if {@code null} input
         * @since 2.0
         */
        String trimToEmpty(String str);

        /**
         * <p>Removes control characters (char &lt;= 32) from both
         * ends of this String returning {@code null} if the String is
         * empty ("") after the trim or if it is {@code null}.
         *
         * <p>The String is trimmed using {@link String#trim()}.
         * Trim removes start and end characters &lt;= 32.
         * To strip whitespace use {@link #stripToNull(String)}.</p>
         *
         * <pre>
         * StringUtils.trimToNull(null)          = null
         * StringUtils.trimToNull("")            = null
         * StringUtils.trimToNull("     ")       = null
         * StringUtils.trimToNull("abc")         = "abc"
         * StringUtils.trimToNull("    abc    ") = "abc"
         * </pre>
         *
         * @param str  the String to be trimmed, may be null
         * @return the trimmed String,
         *  {@code null} if only chars &lt;= 32, empty or null String input
         * @since 2.0
         */
        String trimToNull(String str);

        /**
         * <p>Truncates a String. This will turn
         * "Now is the time for all good men" into "Now is the time for".</p>
         *
         * <p>Specifically:</p>
         * <ul>
         *   <li>If {@code str} is less than {@code maxWidth} characters
         *       long, return it.</li>
         *   <li>Else truncate it to {@code substring(str, 0, maxWidth)}.</li>
         *   <li>If {@code maxWidth} is less than {@code 0}, throw an
         *       {@code IllegalArgumentException}.</li>
         *   <li>In no case will it return a String of length greater than
         *       {@code maxWidth}.</li>
         * </ul>
         *
         * <pre>
         * StringUtils.truncate(null, 0)       = null
         * StringUtils.truncate(null, 2)       = null
         * StringUtils.truncate("", 4)         = ""
         * StringUtils.truncate("abcdefg", 4)  = "abcd"
         * StringUtils.truncate("abcdefg", 6)  = "abcdef"
         * StringUtils.truncate("abcdefg", 7)  = "abcdefg"
         * StringUtils.truncate("abcdefg", 8)  = "abcdefg"
         * StringUtils.truncate("abcdefg", -1) = throws an IllegalArgumentException
         * </pre>
         *
         * @param str  the String to truncate, may be null
         * @param maxWidth  maximum length of result String, must be positive
         * @return truncated String, {@code null} if null String input
         * @throws IllegalArgumentException If {@code maxWidth} is less than {@code 0}
         * @since 3.5
         */
        String truncate(String str, int maxWidth);

        /**
         * <p>Truncates a String. This will turn
         * "Now is the time for all good men" into "is the time for all".</p>
         *
         * <p>Works like {@code truncate(String, int)}, but allows you to specify
         * a "left edge" offset.
         *
         * <p>Specifically:</p>
         * <ul>
         *   <li>If {@code str} is less than {@code maxWidth} characters
         *       long, return it.</li>
         *   <li>Else truncate it to {@code substring(str, offset, maxWidth)}.</li>
         *   <li>If {@code maxWidth} is less than {@code 0}, throw an
         *       {@code IllegalArgumentException}.</li>
         *   <li>If {@code offset} is less than {@code 0}, throw an
         *       {@code IllegalArgumentException}.</li>
         *   <li>In no case will it return a String of length greater than
         *       {@code maxWidth}.</li>
         * </ul>
         *
         * <pre>
         * StringUtils.truncate(null, 0, 0) = null
         * StringUtils.truncate(null, 2, 4) = null
         * StringUtils.truncate("", 0, 10) = ""
         * StringUtils.truncate("", 2, 10) = ""
         * StringUtils.truncate("abcdefghij", 0, 3) = "abc"
         * StringUtils.truncate("abcdefghij", 5, 6) = "fghij"
         * StringUtils.truncate("raspberry peach", 10, 15) = "peach"
         * StringUtils.truncate("abcdefghijklmno", 0, 10) = "abcdefghij"
         * StringUtils.truncate("abcdefghijklmno", -1, 10) = throws an IllegalArgumentException
         * StringUtils.truncate("abcdefghijklmno", Integer.MIN_VALUE, 10) = throws an IllegalArgumentException
         * StringUtils.truncate("abcdefghijklmno", Integer.MIN_VALUE, Integer.MAX_VALUE) = throws an IllegalArgumentException
         * StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE) = "abcdefghijklmno"
         * StringUtils.truncate("abcdefghijklmno", 1, 10) = "bcdefghijk"
         * StringUtils.truncate("abcdefghijklmno", 2, 10) = "cdefghijkl"
         * StringUtils.truncate("abcdefghijklmno", 3, 10) = "defghijklm"
         * StringUtils.truncate("abcdefghijklmno", 4, 10) = "efghijklmn"
         * StringUtils.truncate("abcdefghijklmno", 5, 10) = "fghijklmno"
         * StringUtils.truncate("abcdefghijklmno", 5, 5) = "fghij"
         * StringUtils.truncate("abcdefghijklmno", 5, 3) = "fgh"
         * StringUtils.truncate("abcdefghijklmno", 10, 3) = "klm"
         * StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE) = "klmno"
         * StringUtils.truncate("abcdefghijklmno", 13, 1) = "n"
         * StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE) = "no"
         * StringUtils.truncate("abcdefghijklmno", 14, 1) = "o"
         * StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE) = "o"
         * StringUtils.truncate("abcdefghijklmno", 15, 1) = ""
         * StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE) = ""
         * StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE) = ""
         * StringUtils.truncate("abcdefghij", 3, -1) = throws an IllegalArgumentException
         * StringUtils.truncate("abcdefghij", -2, 4) = throws an IllegalArgumentException
         * </pre>
         *
         * @param str  the String to truncate, may be null
         * @param offset  left edge of source String
         * @param maxWidth  maximum length of result String, must be positive
         * @return truncated String, {@code null} if null String input
         * @throws IllegalArgumentException If {@code offset} or {@code maxWidth} is less than {@code 0}
         * @since 3.5
         */
        String truncate(String str, int offset, int maxWidth);

        /**
         * <p>Uncapitalizes a String, changing the first character to lower case as
         * per {@link Character#toLowerCase(int)}. No other characters are changed.</p>
         *
         * <p>For a word based algorithm, see {@link org.apache.commons.lang3.text.WordUtils#uncapitalize(String)}.
         * A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.uncapitalize(null)  = null
         * StringUtils.uncapitalize("")    = ""
         * StringUtils.uncapitalize("cat") = "cat"
         * StringUtils.uncapitalize("Cat") = "cat"
         * StringUtils.uncapitalize("CAT") = "cAT"
         * </pre>
         *
         * @param str the String to uncapitalize, may be null
         * @return the uncapitalized String, {@code null} if null String input
         * @see org.apache.commons.lang3.text.WordUtils#uncapitalize(String)
         * @see #capitalize(String)
         * @since 2.0
         */
        String uncapitalize(String str);

        /**
         * <p>
         * Unwraps a given string from a character.
         * </p>
         *
         * <pre>
         * StringUtils.unwrap(null, null)         = null
         * StringUtils.unwrap(null, '\0')         = null
         * StringUtils.unwrap(null, '1')          = null
         * StringUtils.unwrap("a", 'a')           = "a"
         * StringUtils.unwrap("aa", 'a')           = ""
         * StringUtils.unwrap("\'abc\'", '\'')    = "abc"
         * StringUtils.unwrap("AABabcBAA", 'A')   = "ABabcBA"
         * StringUtils.unwrap("A", '#')           = "A"
         * StringUtils.unwrap("#A", '#')          = "#A"
         * StringUtils.unwrap("A#", '#')          = "A#"
         * </pre>
         *
         * @param str
         *          the String to be unwrapped, can be null
         * @param wrapChar
         *          the character used to unwrap
         * @return unwrapped String or the original string
         *          if it is not quoted properly with the wrapChar
         * @since 3.6
         */
        String unwrap(String str, char wrapChar);

        /**
         * <p>
         * Unwraps a given string from anther string.
         * </p>
         *
         * <pre>
         * StringUtils.unwrap(null, null)         = null
         * StringUtils.unwrap(null, "")           = null
         * StringUtils.unwrap(null, "1")          = null
         * StringUtils.unwrap("a", "a")           = "a"
         * StringUtils.unwrap("aa", "a")          = ""
         * StringUtils.unwrap("\'abc\'", "\'")    = "abc"
         * StringUtils.unwrap("\"abc\"", "\"")    = "abc"
         * StringUtils.unwrap("AABabcBAA", "AA")  = "BabcB"
         * StringUtils.unwrap("A", "#")           = "A"
         * StringUtils.unwrap("#A", "#")          = "#A"
         * StringUtils.unwrap("A#", "#")          = "A#"
         * </pre>
         *
         * @param str
         *          the String to be unwrapped, can be null
         * @param wrapToken
         *          the String used to unwrap
         * @return unwrapped String or the original string
         *          if it is not quoted properly with the wrapToken
         * @since 3.6
         */
        String unwrap(String str, String wrapToken);

        /**
         * <p>Converts a String to upper case as per {@link String#toUpperCase()}.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.upperCase(null)  = null
         * StringUtils.upperCase("")    = ""
         * StringUtils.upperCase("aBc") = "ABC"
         * </pre>
         *
         * <p><strong>Note:</strong> As described in the documentation for {@link String#toUpperCase()},
         * the result of this method is affected by the current locale.
         * For platform-independent case transformations, the method {@link #lowerCase(String, Locale)}
         * should be used with a specific locale (e.g. {@link Locale#ENGLISH}).</p>
         *
         * @param str  the String to upper case, may be null
         * @return the upper cased String, {@code null} if null String input
         */
        String upperCase(String str);

        /**
         * <p>Converts a String to upper case as per {@link String#toUpperCase(Locale)}.</p>
         *
         * <p>A {@code null} input String returns {@code null}.</p>
         *
         * <pre>
         * StringUtils.upperCase(null, Locale.ENGLISH)  = null
         * StringUtils.upperCase("", Locale.ENGLISH)    = ""
         * StringUtils.upperCase("aBc", Locale.ENGLISH) = "ABC"
         * </pre>
         *
         * @param str  the String to upper case, may be null
         * @param locale  the locale that defines the case transformation rules, must not be null
         * @return the upper cased String, {@code null} if null String input
         * @since 2.5
         */
        String upperCase(String str, Locale locale);

        /**
         * Returns the string representation of the {@code char} array or null.
         *
         * @param value the character array.
         * @return a String or null
         * @see String#valueOf(char[])
         * @since 3.9
         */
        String valueOf(char[] value);

        /**
         * <p>
         * Wraps a string with a char.
         * </p>
         *
         * <pre>
         * StringUtils.wrap(null, *)        = null
         * StringUtils.wrap("", *)          = ""
         * StringUtils.wrap("ab", '\0')     = "ab"
         * StringUtils.wrap("ab", 'x')      = "xabx"
         * StringUtils.wrap("ab", '\'')     = "'ab'"
         * StringUtils.wrap("\"ab\"", '\"') = "\"\"ab\"\""
         * </pre>
         *
         * @param str
         *            the string to be wrapped, may be {@code null}
         * @param wrapWith
         *            the char that will wrap {@code str}
         * @return the wrapped string, or {@code null} if {@code str==null}
         * @since 3.4
         */
        String wrap(String str, char wrapWith);

        /**
         * <p>
         * Wraps a String with another String.
         * </p>
         *
         * <p>
         * A {@code null} input String returns {@code null}.
         * </p>
         *
         * <pre>
         * StringUtils.wrap(null, *)         = null
         * StringUtils.wrap("", *)           = ""
         * StringUtils.wrap("ab", null)      = "ab"
         * StringUtils.wrap("ab", "x")       = "xabx"
         * StringUtils.wrap("ab", "\"")      = "\"ab\""
         * StringUtils.wrap("\"ab\"", "\"")  = "\"\"ab\"\""
         * StringUtils.wrap("ab", "'")       = "'ab'"
         * StringUtils.wrap("'abcd'", "'")   = "''abcd''"
         * StringUtils.wrap("\"abcd\"", "'") = "'\"abcd\"'"
         * StringUtils.wrap("'abcd'", "\"")  = "\"'abcd'\""
         * </pre>
         *
         * @param str
         *            the String to be wrapper, may be null
         * @param wrapWith
         *            the String that will wrap str
         * @return wrapped String, {@code null} if null String input
         * @since 3.4
         */
        String wrap(String str, String wrapWith);

        /**
         * <p>
         * Wraps a string with a char if that char is missing from the start or end of the given string.
         * </p>
         *
         * <p>A new {@code String} will not be created if {@code str} is already wrapped.</p>
         *
         * <pre>
         * StringUtils.wrapIfMissing(null, *)        = null
         * StringUtils.wrapIfMissing("", *)          = ""
         * StringUtils.wrapIfMissing("ab", '\0')     = "ab"
         * StringUtils.wrapIfMissing("ab", 'x')      = "xabx"
         * StringUtils.wrapIfMissing("ab", '\'')     = "'ab'"
         * StringUtils.wrapIfMissing("\"ab\"", '\"') = "\"ab\""
         * StringUtils.wrapIfMissing("/", '/')  = "/"
         * StringUtils.wrapIfMissing("a/b/c", '/')  = "/a/b/c/"
         * StringUtils.wrapIfMissing("/a/b/c", '/')  = "/a/b/c/"
         * StringUtils.wrapIfMissing("a/b/c/", '/')  = "/a/b/c/"
         * </pre>
         *
         * @param str
         *            the string to be wrapped, may be {@code null}
         * @param wrapWith
         *            the char that will wrap {@code str}
         * @return the wrapped string, or {@code null} if {@code str==null}
         * @since 3.5
         */
        String wrapIfMissing(String str, char wrapWith);

        /**
         * <p>
         * Wraps a string with a string if that string is missing from the start or end of the given string.
         * </p>
         *
         * <p>A new {@code String} will not be created if {@code str} is already wrapped.</p>
         *
         * <pre>
         * StringUtils.wrapIfMissing(null, *)         = null
         * StringUtils.wrapIfMissing("", *)           = ""
         * StringUtils.wrapIfMissing("ab", null)      = "ab"
         * StringUtils.wrapIfMissing("ab", "x")       = "xabx"
         * StringUtils.wrapIfMissing("ab", "\"")      = "\"ab\""
         * StringUtils.wrapIfMissing("\"ab\"", "\"")  = "\"ab\""
         * StringUtils.wrapIfMissing("ab", "'")       = "'ab'"
         * StringUtils.wrapIfMissing("'abcd'", "'")   = "'abcd'"
         * StringUtils.wrapIfMissing("\"abcd\"", "'") = "'\"abcd\"'"
         * StringUtils.wrapIfMissing("'abcd'", "\"")  = "\"'abcd'\""
         * StringUtils.wrapIfMissing("/", "/")  = "/"
         * StringUtils.wrapIfMissing("a/b/c", "/")  = "/a/b/c/"
         * StringUtils.wrapIfMissing("/a/b/c", "/")  = "/a/b/c/"
         * StringUtils.wrapIfMissing("a/b/c/", "/")  = "/a/b/c/"
         * </pre>
         *
         * @param str
         *            the string to be wrapped, may be {@code null}
         * @param wrapWith
         *            the string that will wrap {@code str}
         * @return the wrapped string, or {@code null} if {@code str==null}
         * @since 3.5
         */
        String wrapIfMissing(String str, String wrapWith);
    }
}
