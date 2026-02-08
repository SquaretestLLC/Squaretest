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

import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Locale;

public class MyClass implements IControllerWithPaths {

    @Override
    public String indexWithNoPath(String theName, Model model) {
        return null;
    }

    @Override
    public String indexWithSlashPath(String theName, Model model) {
        return null;
    }

    @Override
    public String greeting(String name, Model model) {
        return null;
    }

    @Override
    public String greeting1(Locale locale, String name, Model model) {
        return null;
    }

    @Override
    public String authorizedGreeting(Principal principal, String name, Model model) {
        return null;
    }

    @Override
    public String authorizedGreeting1(String name, Model model) {
        return null;
    }

    @Override
    public String authorizedGreeting2(String username, Model model) {
        return null;
    }

    @Override
    public String greetingWithValueParam(String theName, Model model) {
        return null;
    }

    @Override
    public String greetingWithPathParam(String theName, Model model) {
        return null;
    }

    @Override
    public String greetingWithPathAndValue(String theName, Model model) {
        return null;
    }

    @Override
    public String greetingWithMultiplePaths(String name, Model model) {
        return null;
    }

    @Override
    public String greetingWithPathSetToMultiplePaths(String name, Model model) {
        return null;
    }

    @Override
    public String greetingWithValueSetToMultiplePaths(String name, Model model) {
        return null;
    }

    @Override
    public String greetingWithConstant(String name, Model model) {
        return null;
    }

    @Override
    public String greetingWithMultipleConstant(String name, Model model) {
        return null;
    }

    @Override
    public String greetingWithPlaceholder(String name, Model model) {
        return null;
    }

    @Override
    public String greetingWithPathVar(String userId, Model model) {
        return null;
    }

    @Override
    public String greetingWithPathVarRegex(String userId, Model model) {
        return null;
    }

    @Override
    public void findPet(int q1, int q2) {

    }
}
