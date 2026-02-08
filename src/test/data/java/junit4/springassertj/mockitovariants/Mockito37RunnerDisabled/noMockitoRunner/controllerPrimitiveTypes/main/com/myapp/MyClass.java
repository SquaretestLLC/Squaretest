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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyClass {

    @GetMapping("/greetingWithPrimitiveParams")
    String greeting(
            @RequestParam(name = "name") String theName,
            @RequestParam(name = "longId") long longId,
            @RequestParam(name = "intId") int intId,
            @RequestParam(name = "floatId") float floatId,
            @RequestParam(name = "doubleId") double doubleId,
            @RequestParam(name = "char") char charId,
            @RequestParam(name = "byte") byte byteId,
            @RequestParam(name = "numberId") Number numberId,
            @RequestParam(name = "bigDouble") Double bigDoubleId,
            @RequestParam(name = "characterId") Character characterId,
            @RequestParam(name = "isVal") boolean isVal,
            @RequestParam(name = "isBigVal") Boolean isBigVal,
            Model model) {
        return "greeting";
    }

    @PostMapping("/submitFormWithPrimitives")
    String submitFormWithPrimitives(
            FormDataWithPrimitives formDataWithPrimitives,
            Model model) {
        return "submitFormWithPrimitives";
    }

    @PostMapping("/submitFormWithPrimitives2")
    String submitFormWithPrimitives2(
            FormDataWithPrimitives2 formDataWithPrimitives,
            Model model) {
        return "submitFormWithPrimitives2";
    }

}
