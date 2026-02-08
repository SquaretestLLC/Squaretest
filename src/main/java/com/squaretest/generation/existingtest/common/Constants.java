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
package com.squaretest.generation.existingtest.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    // This covers @Mock from mockito, @Mocked from JMockit and @MockBean from Spring.
    public static final String[] MockAnnotationNames = new String[]{"Mock", "Mocked", "MockBean"};
    public static final List<String> SetupMethodNames = Collections.singletonList("setUp");
    public static final List<String> SetupMethodAnnotations = Arrays.asList("Before", "BeforeEach", "BeforeTest", "BeforeSuite", "BeforeMethod");
    public static final String MockMethodName = "mock";
    public static final String InjectMocksCanonicalName = "org.mockito.InjectMocks";
}
