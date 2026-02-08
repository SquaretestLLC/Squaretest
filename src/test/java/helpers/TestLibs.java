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
package helpers;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestLibs {

    public static TestLib AndroidSDK = new TestLib("AndroidSDKPlatform", "TestLibs/android.jar");
    public static TestLib SupportV7 = new TestLib("SupportV7", "TestLibs/appcompat-v7-25.3.1-sources.jar");
    public static TestLib Robolectric3 = new TestLib("Robolectric3", "TestLibs/robolectric-3.8.jar");
    public static TestLib Guava = new TestLib("Guava", "TestLibs/guava-22.0.jar");
    public static TestLib ReactorCore = new TestLib("RxJava", "TestLibs/reactor-core-3.3.5.RELEASE.jar");
    public static TestLib RxJava2 = new TestLib("RxJava2", "TestLibs/rxjava-2.2.19.jar");
    public static TestLib RxJava3 = new TestLib("RxJava3", "TestLibs/rxjava-3.0.13.jar", "TestLibs/rxjava-3.0.13-sources.jar");
    public static TestLib ReactiveStreams = new TestLib("ReactiveStreams", "TestLibs/reactive-streams-1.0.3.jar");
    public static TestLib Mockito3_7 = new TestLib("Mockito", "TestLibs/mockito-core-3.7.0.jar");
    public static TestLib Mockito3_0 = new TestLib("Mockito", "TestLibs/mockito-core-3.0.0.jar");
    public static TestLib Mockito1_10 = new TestLib("Mockito", "TestLibs/mockito-core-1.10.19.jar");
    public static TestLib CommonsIO2_8 = new TestLib("CommonsIO28", "TestLibs/commons-io-2.8.0.jar");
    public static TestLib CommonsIO2_6 = new TestLib("CommonsIO26", "TestLibs/commons-io-2.6.jar");
    public static TestLib OkHttp3 = new TestLib("OkHttp3", "TestLibs/okhttp-4.9.1.jar");
    public static TestLib BeansLib = new TestLib("Beans", "TestLibs/BeansModule.jar");
    public static TestLib LombokLib = new TestLib("Lombok", "TestLibs/lombok-1.18.8.jar");
    public static TestLib CommonsLang3 = new TestLib("CommonsLang3", "TestLibs/commons-lang3-3.5.jar");
    public static TestLib CommonsLang2 = new TestLib("CommonsLang2", "TestLibs/commons-lang-2.6.jar");
    public static TestLib CommonsCollections4 = new TestLib("CommonsCollections4", "TestLibs/commons-collections4-4.1.jar");
    public static TestLib SpringData = new TestLib("SpringData", "TestLibs/spring-data-commons-2.1.11.RELEASE.jar");
    public static TestLib SpringMock = new TestLib("SpringData", "TestLibs/spring-mock-2.0.8.jar");
    public static TestLib JavaxServlet = new TestLib("Servlet", "TestLibs/javax.servlet-api-3.1.0.jar");
    public static TestLib JakartaServlet = new TestLib("Servlet", "TestLibs/jakarta.servlet-api-6.0.0.jar");
    public static TestLib AssertJ = new TestLib("AssertJ", "TestLibs/assertj-core-3.16.1.jar");
    public static TestLib JUnit4_12 = new TestLib("JUnit4_12", "TestLibs/junit-4.12.jar");
    public static TestLib JUnit4_13 = new TestLib("JUnit4_13", "TestLibs/junit-4.13.1.jar");
    public static TestLib JUnit5_7 = new TestLib("JUnit5", "TestLibs/junit-jupiter-api-5.7.0.jar");
    public static TestLib JUnit5_3 = new TestLib("JUnit5", "TestLibs/junit-jupiter-api-5.3.2.jar");
    public static TestLib JUnit5MockitoExtension = new TestLib("JUnit5MockitoExtension", "TestLibs/mockito-junit-jupiter-3.7.0.jar");
    public static TestLib TestNGMockitoExtension = new TestLib("TestNGMockitoExtension", "TestLibs/mockito-testng-0.5.2.jar");
    public static TestLib TestNG6_8 = new TestLib("TestNG73", "TestLibs/testng-6.8.21.jar");
    public static TestLib TestNG7_3 = new TestLib("TestNG73", "TestLibs/testng-7.3.0.jar");
    public static TestLib TestNG7_7 = new TestLib("TestNG77", "TestLibs/testng-7.7.1.jar");
    public static TestLib Retrofit2 = new TestLib("Retrofit2", "TestLibs/retrofit-2.9.0.jar");
    public static TestLib JavaXPersistence = new TestLib("JavaXPersistence", "TestLibs/javax.persistence-api-2.2.jar");
    public static TestLib JakartaPersistence = new TestLib("JakartaPersistence", "TestLibs/jakarta.persistence-api-3.2.0-M2.jar");
    public static TestLib Retrofit2Mock = new TestLib("Retrofit2", "TestLibs/retrofit-mock-2.2.0.jar");
    public static TestLib JavaxValidation = new TestLib("JavaxValidation", "TestLibs/validation-api-2.0.1.Final.jar", "TestLibs/validation-api-2.0.1.Final-sources.jar");
    public static TestLib SquaretestSuperTypes = new TestLib("SquaretestSuperTypes", "TestLibs/SquaretestSupertypes-3.0-SNAPSHOT.jar", "TestLibs/SquaretestSupertypes-3.0-SNAPSHOT-sources.jar");
    public static TestLib SquaretestSuperTypesNoSources = new TestLib("SquaretestSuperTypes", "TestLibs/SquaretestSupertypes-3.0-SNAPSHOT.jar");
    public static TestLib SwaggerAnnotationsV1 = new TestLib("SwaggerAnnotationsV1", "TestLibs/swagger-annotations-1.5.24.jar");
    public static TestLib JodaTime = new TestLib("JodaTime", "TestLibs/jodaTime/joda-time-2.10.6.jar", "TestLibs/jodaTime/joda-time-2.10.6-sources.jar");

    // All AWS V1 Clients
    public static List<TestLib> AWSV1Libs = loadAllFromDir("TestLibs/aws");
    public static List<TestLib> AWSV1LambdaLibs = loadAllFromDir("TestLibs/awslambda");
    public static List<TestLib> GRPCApi = loadAllFromDir("TestLibs/grpc");
    public static List<TestLib> AWSV1NoSourcesLibs = loadAllFromDirWithoutSources("TestLibs/aws");
    public static List<TestLib> AndroidLibs = Arrays.asList(AndroidSDK, SupportV7);

    // Initialize the common lib groups.
    public static List<TestLib> CommonLibs = Arrays.asList(BeansLib, CommonsCollections4, CommonsLang3, JavaxValidation, LombokLib, ReactiveStreams, ReactorCore, Retrofit2, Retrofit2Mock, RxJava2, JavaxServlet, JakartaServlet, SpringData);

    public static List<TestLib> AWSV2Libs = loadAllFromDir("TestLibs/aws-v2");
    public static List<TestLib> AWSV2NoSourcesLibs = loadAllFromDirWithoutSources("TestLibs/aws-v2");
    public static List<TestLib> TencentLibs = loadAllFromDir("TestLibs/tencent");
    public static List<TestLib> SpringFrameworkLibs = loadAllFromDir("TestLibs/spring");
    public static List<TestLib> SpringFramework202Libs = loadAllFromDir("TestLibs/springBoot202");
    public static List<TestLib> SpringFrameworkWithoutSpringMockLibs = loadAllFromDir("TestLibs/springWithoutSpringMock");
    public static List<TestLib> SpringFrameworkLibsWithSources = loadAllFromDir("TestLibs/springWithSources");
    public static List<TestLib> JakartaWSAPILibs = loadAllFromDir("TestLibs/jakartaws");
    public static List<TestLib> JavaXWSAPILibs = loadAllFromDir("TestLibs/javaxws");
    public static List<TestLib> JacksonLibs = loadAllFromDir("TestLibs/jackson");
    public static List<TestLib> JakartaValidationLibs = loadAllFromDir("TestLibs/jakarta-validation");
    public static List<TestLib> GoogleCloudLibs = loadAllFromDir("TestLibs/googlecloud");
    public static List<TestLib> GoogleCloudNoSourcesLibs = loadAllFromDirWithoutSources("TestLibs/googlecloud");
    public static List<TestLib> ApacheHttpLibs = loadAllFromDir("TestLibs/apachehttp");
    public static List<TestLib> JJWT = loadAllFromDir("TestLibs/jjwt");
    public static List<TestLib> JJWTWithoutImpl = loadAllFromDir("TestLibs/jjwtNoImpl");
    public static List<TestLib> ElasticSearchLibs = loadAllFromDir("TestLibs/elasticsearch");

    private static List<TestLib> loadAllFromDir(final String dir) {
        return loadAllFromDir(dir, true);
    }

    private static List<TestLib> loadAllFromDirWithoutSources(final String dir) {
        return loadAllFromDir(dir, false);
    }

    private static List<TestLib> loadAllFromDir(final String dir, final boolean includeSources) {
        final List<TestLib> ret = new ArrayList<>();
        for(final File file : Objects.requireNonNull(new File(dir).listFiles())) {
            final String libName = file.getName();
            if(libName.endsWith("-sources.jar")) {
                continue;
            }
            final String libPath = dir + "/" + libName;
            final String sourcePath = dir + "/" + StringUtils.removeEnd(libName, ".jar") + "-sources.jar";
            if(includeSources && new File(sourcePath).exists()) {
                ret.add(new TestLib(libName, libPath, sourcePath));
            } else {
                ret.add(new TestLib(libName, libPath));
            }
        }
        return ret;
    }
}