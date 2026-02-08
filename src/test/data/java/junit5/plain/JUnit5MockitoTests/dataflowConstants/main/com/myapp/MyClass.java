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

import javax.annotation.Nonnull;

public class MyClass {

    private static final String ConstantParam = "FooParamValue";
    private static final String ConstantParamXml = "<?xml version=\"1.0\"?>";
    private static final String AltParamConstant = String.format("%s/%s", ConstantParam, "path");
    private static final String AltFormatString = "base/%s/%s/";
    private final String fooBucketName;
    private final FooService fooService;

    public MyClass(@Nonnull final FooService fooService) {
        this(fooService, "bucketName");
    }

    public MyClass(@Nonnull final FooService fooService, final String fooBucketName) {
        this.fooService = fooService;
        this.fooBucketName = fooBucketName;
    }

    public Foo getFooById() {
        return fooService.getFooById("literalId");
    }

    public Foo getFooById2() {
        return fooService.getFooById2(ConstantParam);
    }

    public Foo getFooById3(final String id) {
        return fooService.getFooById3(id + ConstantParam);
    }

    public Foo getFooById4() {
        return fooService.getFooById4(ConstantParam + 4 + "other");
    }

    public Foo getFooById5() {
        return fooService.getFooById5(AltParamConstant);
    }

    public Foo getFooById6() {
        return fooService.getFooById6(AltParamConstant + "/other");
    }

    public Foo getFooById7(final String id) {
        return fooService.getFooById7(ConstantParam + "/" + id + "/other");
    }

    public Foo getFooById8(final String id) {
        return fooService.getFooById8(String.format("%s/%s", ConstantParam, "path"));
    }

    public Foo getFooById9(final String id) {
        return fooService.getFooById9(String.format("%s/%s", id, "path"));
    }

    public Foo getFooById10(final String id) {
        return fooService.getFooById10(String.format(AltFormatString, id, "path"));
    }

    public Foo getFooById11(final String id) {
        return fooService.getFooById11("/base/" + new Foo("id", "name"));
    }

    public Foo getFooById12(final String id) {
        return fooService.getFooById12(String.format("%s/%s", id, "path/") + 4);
    }

    public Foo getFooById13(final String id) {
        return fooService.getFooById13(String.format("%s/%s", id, "path") + getPath(id));
    }

    public Foo getFooById14(final String id) {
        final String path = getPath(id);
        return fooService.getFooById14(path);
    }

    public Foo getFooById16(final String id) {
        final String temp = "path/" + id;
        final String temp2 = temp;
        return fooService.getFooById16(helper(temp2));
    }

    public Foo getFooById17(final String id) {
        return fooService.getFooById17(fooBucketName);
    }

    public Foo getFooById18(final String id) {
        final String name = id != null ? id : fooBucketName;
        return fooService.getFooById18(name);
    }

    public Foo getFooById19() {
        final String localVar = Constants.OtherClassConstant1;
        return fooService.getFooById19(localVar);
    }

    public Foo getFooById20() {
        final String localVar = Constants.getConstant();
        return fooService.getFooById20(localVar);
    }

    public Foo getFooById21() {
        final String localVar = Constants.OtherClassConstant3;
        return fooService.getFooById21(localVar);
    }

    public Foo getFooById22() {
        final String localVar = Constants.OtherClassCircular2;
        return fooService.getFooById22(localVar);
    }
    public Foo getFooById23() {
        return fooService.getFooById23(ConstantParamXml);
    }
    public Foo getFooById24() {
        return fooService.getFooById24("<?xml version=\"1.0\"?>");
    }
    private String helper(final String id) {
        try {
            if(id.length() > 10) {
                return helper(id.substring(0, 10));
            }
            return fooService.getFooById13(id).getId();
        } catch(final Exception e) {
            return "default";
        }
    }

    private String getPath(final String id) {
        return "/" + id + "/";
    }
}
