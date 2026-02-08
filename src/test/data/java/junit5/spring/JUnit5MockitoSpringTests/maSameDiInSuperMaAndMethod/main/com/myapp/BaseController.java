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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BaseController {
    private final BarService barService;
    private final OtherService otherService;

    public BaseController(final BarService barService, final OtherService otherService) {
        this.barService = barService;
        this.otherService = otherService;
    }

    @ModelAttribute
    public BarBean loadBarForSession(@PathVariable long barId) throws InvalidBarDataIdException {
        return loadBarBean(barId);
    }

    public BarBean loadBarBean(final long barId) throws InvalidBarDataIdException {
        final Optional<BarBean> barDataOpt = barService.getBarDataById1(barId);
        if(barDataOpt.isPresent()) {
            final BarBean barData = barDataOpt.get();
            barData.setOtherData(otherService.getOtherData1(barData.getOtherId()));
            return barData;
        }
        return barService.getBarDataById2(barId);
    }
}
