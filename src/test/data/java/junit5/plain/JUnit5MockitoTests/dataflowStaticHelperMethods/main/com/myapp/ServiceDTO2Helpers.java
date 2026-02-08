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
import java.util.stream.Collectors;

public class ServiceDTO2Helpers {
    public static List<ServiceDTO2.ServiceDTO2Part> createDtoPartList2(List<ServiceResponse2.ServiceResponse2Part> serviceResponse2Parts) {
        return serviceResponse2Parts.stream().map(x -> {
            final ServiceDTO2.ServiceDTO2Part ret = new ServiceDTO2.ServiceDTO2Part();
            ret.setServiceDTO2PartId(x.getServiceResponse2PartId());
            ret.setServiceDTO2PartName(x.getServiceResponse2PartName());
            return ret;
        }).collect(Collectors.toList());
    }
}
