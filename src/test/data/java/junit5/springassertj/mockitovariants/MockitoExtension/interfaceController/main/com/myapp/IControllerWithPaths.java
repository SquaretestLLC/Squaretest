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

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Locale;

import static com.myapp.ControllerPaths.*;

@Controller
@RequestMapping("/controllerWithPaths")
public interface IControllerWithPaths {
    @GetMapping
    String indexWithNoPath(@RequestParam(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping("/")
    String indexWithSlashPath(@RequestParam(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping("/greeting")
    String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping("/greeting1")
    String greeting1(Locale locale, @RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping("/authorizedGreeting")
    String authorizedGreeting(Principal principal, @RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping("/authorizedGreeting1")
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    String authorizedGreeting1(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping("/authorizedGreeting2")
    @PreAuthorize("#username == authentication.principal.username")
    String authorizedGreeting2(String username, Model model);

    @GetMapping(value = "/greetingWithValue")
    String greetingWithValueParam(@RequestParam(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping(path = "/greetingWithPath")
    String greetingWithPathParam(@RequestParam(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping(path = "/greetingWithPathAndValue", value = "/greetingWithPathAndValue1")
    String greetingWithPathAndValue(@RequestParam(name = "name", required = false, defaultValue = "World") String theName, Model model);

    @GetMapping({"/hola", "bonjour"})
    String greetingWithMultiplePaths(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping(path = {"/hola", "bonjour"})
    String greetingWithPathSetToMultiplePaths(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping(value = {"/hola", "bonjour"})
    String greetingWithValueSetToMultiplePaths(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping(GreetingPath)
    String greetingWithConstant(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping({GreetingPath1, GreetingPath2})
    String greetingWithMultipleConstant(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping("/${greet_path}/other/stuff")
    String greetingWithPlaceholder(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model);

    @GetMapping("greetingForUser/{userId}")
    String greetingWithPathVar(@PathVariable(name = "userId") String userId, Model model);

    @GetMapping("greetingForUserRegex/{numericId:[\\d]+}")
    String greetingWithPathVarRegex(@PathVariable(name = "userId") String userId, Model model);

    // Test path matrix from https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-matrix-variables.
    @GetMapping("/owners/{ownerId}/pets/{petId}")
    void findPet(
            @MatrixVariable(name = "q", pathVar = "ownerId") int q1,
            @MatrixVariable(name = "q", pathVar = "petId") int q2);
}
