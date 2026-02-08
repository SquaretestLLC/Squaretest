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

import java.util.List;

public class FooService {
    public ServiceResponse1 getFoo1(final String id) {
        return null;
    }

    public ServiceResponse2 getFoo2(final String id) {
        return null;
    }

    public ServiceResponse3 getFoo3(final String id) {
        return null;
    }

    public List<ServiceResponseDataItem> getFoo4(final String id) {
        return null;
    }

    public boolean fooExists1(final ServiceRequest1 serviceRequest1) {
        return true;
    }

    public boolean fooExists2(final ServiceRequest2 serviceRequest2) {
        return true;
    }

    public boolean fooExists3(final ServiceRequest3 serviceRequest3) {
        return true;
    }

}
