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

import com.myapp.nullables.annotations.Nonnull;

import java.util.Objects;

public class ServiceRequest3 {
    @Nonnull
    private final String serviceRequest3BucketName;
    @Nonnull
    private final String serviceRequest3Path;

    public ServiceRequest3(final String serviceRequest3BucketName, final String serviceRequest3Path) {
        this.serviceRequest3BucketName = serviceRequest3BucketName;
        this.serviceRequest3Path = serviceRequest3Path;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ServiceRequest3 that = (ServiceRequest3) o;
        return Objects.equals(serviceRequest3BucketName, that.serviceRequest3BucketName) && Objects.equals(serviceRequest3Path, that.serviceRequest3Path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceRequest3BucketName, serviceRequest3Path);
    }
}
