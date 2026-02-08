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
import java.util.Optional;

import reactor.core.publisher.Mono;

import static com.myapp.ControllerPaths.*;

@Controller
@RequestMapping("/")
public class MyClass {

    @GetMapping
    public String indexWithNoPath(@RequestParam(name="name", required=false, defaultValue="World") String theName, Model model) {
        return "indexWithNoPath";
    }

    @GetMapping("/")
    public String indexWithSlashPath(@RequestParam(name="name", required=false, defaultValue="World") String theName, Model model) {
        return "indexWithSlashPath";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greeting1")
    public String greeting1(final Locale locale, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/authorizedGreeting")
    public String authorizedGreeting(final Principal principal, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/authorizedGreeting1")
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    public String authorizedGreeting1(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/authorizedGreeting2")
    @PreAuthorize("#username == authentication.principal.username")
    public String authorizedGreeting2(String username, Model model) {
        model.addAttribute("name", username);
        return "greeting";
    }

    @GetMapping(value = "/greetingWithValue")
    public String greetingWithValueParam(@RequestParam(name="name", required=false, defaultValue="World") String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping(path = "/greetingWithPath")
    public String greetingWithPathParam(@RequestParam(name="name", required=false, defaultValue="World") String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping(path = "/greetingWithPathAndValue", value = "/greetingWithPathAndValue1")
    public String greetingWithPathAndValue(@RequestParam(name="name", required=false, defaultValue="World") String theName, Model model) {
        model.addAttribute("name", theName);
        return "greeting";
    }

    @GetMapping({"/hola", "bonjour"})
    public String greetingWithMultiplePaths(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping(path = {"/hola", "bonjour"})
    public String greetingWithPathSetToMultiplePaths(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping(value = {"/hola", "bonjour"})
    public String greetingWithValueSetToMultiplePaths(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping(GreetingPath)
    public String greetingWithConstant(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping({GreetingPath1, GreetingPath2})
    public String greetingWithMultipleConstant(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/${greet_path}/other/stuff")
    public String greetingWithPlaceholder(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/${}/other/stuff")
    public String greetingWithPlaceholderEmpty(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("greetingForUser/{userId}")
    public String greetingWithPathVar(@PathVariable(name="userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        return "greetingForUser";
    }

    @GetMapping("greetingForUserRegex/{numericId:[\\d]+}")
    public String greetingWithPathVarRegex(@PathVariable(name="userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        return "greetingForUserRegex";
    }

    // Test path matrix from https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-matrix-variables.
    @GetMapping("/owners/{ownerId}/pets/{petId}")
    public void findPet(
            @MatrixVariable(name="q", pathVar="ownerId") int q1,
            @MatrixVariable(name="q", pathVar="petId") int q2) {
    }

    // Test extra slashes and leaving off leading slash. Also leave off slash between path var and rest of path.
    // Squaretest won't add this one, though.
    @GetMapping("owners1////{ownerId}pets/{petId}")
    public void findPet1(
            @PathVariable(name="ownerId") int ownerId,
            @PathVariable(name="petId") int petId) {
    }

    public static final String GreetingWithConstantPath = "greetingWithConstantRef/{userId}";
    @GetMapping(GreetingWithConstantPath)
    public String greetingWithConstantRefPath(@PathVariable(name="userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        return "greetingWithConstantRef";
    }

    public static final String GreetingWithConstantPathAndPathName = "greetingWithConstantRefPathAndPath/{userId}";
    public static final String UserIdPathVarName = "userId";
    @GetMapping(GreetingWithConstantPathAndPathName)
    public String greetingWithConstantRefPathAndPath(@PathVariable(name= UserIdPathVarName) String userId, Model model) {
        model.addAttribute(UserIdPathVarName, userId);
        return "greetingWithConstantRefPathAndPath";
    }

    public static final String GreetingWithConstantPathAndParam = "greetingWithConstantRefPathAndParam/{userId}";
    public static final String UserIdParamName = "userId";
    @GetMapping(GreetingWithConstantPathAndParam)
    public String greetingWithConstantRefPathAndParam(@RequestParam(name= UserIdParamName) String userId, Model model) {
        model.addAttribute(UserIdParamName, userId);
        return "greetingWithConstantRefPathAndParam";
    }

    @GetMapping("/greetingWithParamWithNoName")
    public String greetingWithParamWithNoName(final String name, final int userId, Model model) {
        model.addAttribute("name", name);
        return "greetingWithParamWithNoName";
    }

    @GetMapping("/greetingWithOptionalParams")
    public String greetingWithOptionalParams(
            @RequestParam(value = "id", required = false) Optional<Long> theId,
            @RequestParam(value = "name", required = false) Optional<String> theName) {
        return "greetingWithOptionalParams";
    }

    @GetMapping("/greetingWithOptionalParamsWithNoAnnotation")
    public String greetingWithOptionalParamsWithNoAnnotation(
            Optional<Long> theId, Optional<String> theName) {
        return "greetingWithOptionalParamsWithNoAnnotation";
    }

    @GetMapping("/greetingWithOptionalParamsWithNoAnnotationAsync")
    public String greetingWithOptionalParamsWithNoAnnotationAsync(
            Mono<Optional<Long>> theId, Mono<Optional<String>> theName) {
        return "greetingWithOptionalParamsWithNoAnnotation";
    }
}
