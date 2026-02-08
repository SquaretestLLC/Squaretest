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

import com.myapp.other.Constants;

import java.util.UUID;

public class MyClass {

    private static final UUID MasterFooId = UUID.fromString("387c2cac-df36-41a6-9591-a46ea4a4df22");

    private final FooService fooService;

    public MyClass(FooService fooService) {
        this.fooService = fooService;
    }

    public Foo getFooWithUUID1(final UUID fooId) {
        return fooService.getFooById1(fooId.toString());
    }

    public Foo getFooWithUUID2(final UUID fooId) {
        return fooService.getFooByUUID2(fooId);
    }

    public Foo getFooWithUUID3(final String fooId) {
        return fooService.getFooByUUID3(UUID.fromString(fooId));
    }

    public Foo getFooWithUUID4() {
        return fooService.getFooByUUID4(MasterFooId);
    }

    public Foo getFooWithUUID5() {
        return fooService.getFooByUUID5(MasterFooId.toString());
    }

    public Foo getFooWithUUID6(final String fooId) {
        final UUID tempId = UUID.fromString(fooId);
        return fooService.getFooByUUID6(tempId);
    }

    public Foo getFooWithUUID7(final Object fooId) {
        final UUID tempId = UUID.fromString((String) fooId);
        return fooService.getFooByUUID7(tempId);
    }

    public Foo getFooWithUUID8(final Object fooId) {
        String strFooId = (String) fooId;
        final UUID tempId = UUID.fromString(strFooId);
        return fooService.getFooByUUID8(tempId);
    }

    public Foo getFooWithUUID9(final Foo foo) {
        // Test invalid cast to ensure we avoid infinite loop.
        return fooService.getFooByUUID9((UUID)((Object)foo));
    }

    public Foo getFooWithUUID10(final Foo foo) {
        // Test invalid cast to ensure we avoid infinite loop.
        return fooService.getFooByUUID10((String)((Object)foo));
    }

    public Foo getFooWithUUID11(final Foo foo) {
        // Test invalid cast to ensure we avoid infinite loop.
        return fooService.getFooByUUID11((String)((Object)foo).toString());
    }

    public Foo getFooWithUUID12(final UUID fooId) {
        return fooService.getFooByUUID12(fooId, fooId);
    }
    public Foo getFooWithUUID13(final Object fooId) {
        // Test cast to UUID.
        if(UUID.class.isInstance(fooId)) {
            return fooService.getFooByUUID13(UUID.class.cast(fooId));
        }
        return null;
    }

    public Foo getFooWithUUID14(final Object fooId) {
        // Test cast to UUID.
        if(String.class.isInstance(fooId)) {
            return fooService.getFooByUUID14(String.class.cast(fooId));
        }
        return null;
    }
    public Foo getFooWithUUID15(final Object fooId) {
        // Test cast to UUID.
        if(fooId instanceof UUID fooAsType) {
            return fooService.getFooByUUID15(fooAsType);
        }
        return null;
    }

    public Foo getFooWithUUID16() {
        // Test cast to UUID.
        if(getUUID() instanceof UUID fooAsType) {
            return fooService.getFooByUUID16(fooAsType);
        }
        return null;
    }

    public Foo getFooWithUUID17() {
        // Test cast to UUID.
        if(getUUID() instanceof UUID fooAsType) {
            return fooService.getFooByUUID17(fooAsType.toString());
        }
        return null;
    }

    public Foo getFooWithUUID18(final Object fooId) {
        // Test cast to UUID.
        if(fooId instanceof UUID fooAsType) {
            return fooService.getFooByUUID18(fooAsType.toString());
        }
        return null;
    }

    public Foo getFooWithUUID19() {
        return fooService.getFooByUUID19(Constants.ConstantFooId2);
    }

    public Foo getFooWithUUID20() {
        return fooService.getFooByUUID20(Constants.ConstantCircular2);
    }

    public Foo getFooWithUUID21() {
        return fooService.getFooByUUID21(Constants.UUIDThatUsesStringCircular2);
    }

    private Object getUUID() {
        return MasterFooId;
    }
}
