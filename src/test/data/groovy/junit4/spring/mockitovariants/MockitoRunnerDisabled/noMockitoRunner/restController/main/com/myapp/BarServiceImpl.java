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

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BarServiceImpl implements BarService {
    @Override
    public BarBean getBarDataById(final long id) throws InvalidBarDataIdException {
        return null;
    }

    @Override
    public List<BarBean> getAllBars() {
        return Collections.emptyList();
    }

    @Override
    public void saveBar(BarBean barBean) {

    }

    @Override
    public void deleteBarWithId(int id) throws InvalidBarDataIdException, NetworkException {

    }
}
