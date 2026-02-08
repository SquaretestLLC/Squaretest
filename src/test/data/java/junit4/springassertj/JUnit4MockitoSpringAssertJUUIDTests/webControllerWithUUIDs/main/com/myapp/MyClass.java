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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/admin/dealsWithUUID")
public class MyClass {

    private static final Logger Log = Logger.getLogger(MyClass.class.getName());

    private final DealsStore dealsStore;

    public MyClass(
            final DealsStore dealsStore) {
        this.dealsStore = dealsStore;
    }

    @PostMapping("/new")
    public String createNewDeal(@Valid DealForm dealForm, Model model) throws NetworkException, ServiceException {
        final Deal deal = convertDeal(dealForm);
        try {
            this.dealsStore.save(deal);
        } catch (NetworkException | ServiceException e) {
            Log.log(Level.SEVERE, "Exception saving deal", e);
            throw e;
        }
        return "redirect:/admin/dealsWithUUID";
    }

    private static Deal convertDeal(final DealForm dealForm) {
        return new Deal(dealForm.getId(), dealForm.getProductName(), dealForm.getPrice(),
                dealForm.getQuantityRemaining(), dealForm.getImageUrl());
    }
}
