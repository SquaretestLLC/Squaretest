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

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * This models the abstract sealed class pattern with public static create methods
 * using android.net.Uri as an example.
 */
public abstract class MyClass {

    private static class MyClassImpl extends MyClass {

        @Override
        public boolean isRelative() {
            return false;
        }

        @Override
        public boolean isHierarchical() {
            return false;
        }

        @Override
        public String getSchemeSpecificPart() {
            return null;
        }
    }

    private MyClass() {
    }

    /**
     * Returns true if this URI is relative, i.e.&nbsp;if it doesn't contain an
     * explicit scheme.
     *
     * @return true if this URI is relative, false if it's absolute
     */
    public abstract boolean isRelative();

    /**
     * Returns true if this URI is hierarchical like "http://google.com".
     * Absolute URIs are hierarchical if the scheme-specific part starts with
     * a '/'. Relative URIs are always hierarchical.
     */
    public abstract boolean isHierarchical();

    /**
     * Gets the scheme-specific part of this URI, i.e.&nbsp;everything between
     * the scheme separator ':' and the fragment separator '#'. If this is a
     * relative URI, this method returns the entire URI. Decodes escaped octets.
     *
     * <p>Example: "//www.google.com/search?q=android"
     *
     * @return the decoded scheme-specific-part
     */
    public abstract String getSchemeSpecificPart();

    public boolean isAbsolute() {
        return true;
    }

    public static MyClass fromString(final String uriString) {
        return new MyClassImpl();
    }

    public static MyClass fromFile(final File file) {
        return new MyClassImpl();
    }

    public static MyClass fromParts(String scheme, String ssp,
                                String fragment) {
        return new MyClassImpl();
    }
}
