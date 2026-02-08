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

import java.util.logging.Logger;

public class MyClass {
    private static final Logger Log = Logger.getLogger(MyClass.class.getName());
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(final FooService fooService, final MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public boolean isValid1(final String data) {
        return fooService.isValid(data);
    }
    public boolean isValid2(final String data) {
        final boolean isValid = fooService.isValid(data);
        Log.info("IsValid = " + isValid + " for foo:" + data);
        return isValid;
    }

    public boolean isValid3(final String data) {
        final boolean isValid = fooService.isValid(data);
        return isValid;
    }

    public boolean exists(final String data) {
        return fooService.exists(data);
    }

    public void validate1(final String data) {
        if (!fooService.isValid(data)) {
            throw new FooNotValidException(data);
        }
    }
    public void validate2(final String data) {
        // Same as validate1.
        // Ensure validate3 calling this does not impact the test.
        if (!fooService.isValid(data)) {
            throw new FooNotValidException(data);
        }
    }
    public void validate3(final String data) {
        // Call validate2 and invert the result.
        try {
            validate2(data);
            throw new FooIsValidException(data);
        } catch (final FooNotValidException e) {
        }
    }

    public void createFoo1(final String data) {
        if(!fooService.isValid(data)) {
            throw new FooNotValidException(data);
        }
        fooService.createFoo1(data);
    }

    public void createFoo2(final String data) {
        // This case is broken. The first call's return value does not impact the control flow.
        // Squaretest won't populate the failure value. Instead, it will just set the defaultInitExpression to false,
        // and won't create an alt-flow test.
        fooService.isValid(data);
        if(!fooService.isValid(data)) {
            throw new FooNotValidException(data);
        }
        fooService.createFoo1(data);
    }

    public void createFoo3(final String data) {
        if(!fooService.isValid(data)) {
            if(fooService.shouldThrowInvalidFoo(data)) {
                throw new FooNotValidException(data);
            }
            throw new GenericException(data);
        }
        fooService.createFoo1(data);
    }

    public void createFoo4(final String data) {
        if(fooService.exists(data)) {
            throw new FooAlreadyExistsException(data);
        }
        fooService.createFoo1(data);
    }

    public void createFoo5(final String data) {
        if(fooService.exists(data)) {
            return;
        }
        fooService.createFoo1(data);
    }

    public void createFoo6(final String data) {
        if(fooService.shouldRecordCreate(data)) {
            metricService.recordCreateFoo(data);
        }
        fooService.createFoo1(data);
    }

    public String getFoo1(final String id) {
        final String foo = getFooImpl1(id);
        if(fooService.isValid(foo)) {
            return foo;
        }
        throw new FooNotValidException(foo);
    }

    public void checkCanCreate1(final String id) {
        final String foo = getFooImpl1(id);
        if(fooService.exists(foo)) {
            throw new FooAlreadyExistsException(foo);
        }
    }

    public String getFoo2(final String id) {
        try {
            return fooService.getFoo1(id);
        } catch (FooNotFoundException e) {
            if(fooService.shouldThrow(id, e)) {
                throw e;
            }
        }
        return null;
    }

    public String getFoo3(final String id) {
        final String foo = fooService.getFoo1(id);
        if(foo.startsWith("OLD-")) {
            if(fooService.shouldRecordOldFoo(id)) {
                metricService.recordOldFoo(id);
            }
            throw new OldFooFoundException(foo);
        }
        return foo;
    }

    public String getFoo4(final String id) {
        // This is the broken case.
        // Squaretest looks at the first DI: fooService.isValidId(id), and sees that it can't tell the difference between the true and the false case.
        // Both cases have the same returnOutcome and the same set of disHitAfter.
        // When we compute the alt-flow tests for fooService.getFooWithValidId1(id), Squaretest knows internally that
        // isValidId(..) must return true. However, it uses the default value (false), because that is the only value
        // populated in the template layer. The failure value is not populated in the template layer, because
        // we don't want the template to create alt-flow tests for the first DI, because we can't tell the difference
        // between its outcomes.
        if(id.startsWith("OLD-")) {
            Log.info("Found old id:" + id + fooService.isValidId(id));
            return null;
        }

        if(fooService.isValidId(id)) {
            return fooService.getFooWithValidId1(id);
        }
        final String foo = fooService.getFoo1(id);
        if(foo.startsWith("OLD-")) {
            if(fooService.shouldRecordOldFoo(id)) {
                metricService.recordOldFoo(id);
            }
            throw new OldFooFoundException(foo);
        }
        return foo;
    }

    public String getFoo5(final String id) {
        // Also a broken case. This is the same as getFoo4(..) but with the
        // "if(fooService.isValidId(id)) {" inverted.
        if(id.startsWith("OLD-")) {
            Log.info("Found old id:" + id + fooService.isValidId(id));
            return null;
        }

        if(!fooService.isValidId(id)) {
            return fooService.getFooWithValidId1(id);
        }
        final String foo = fooService.getFoo1(id);
        if(foo.startsWith("OLD-")) {
            if(fooService.shouldRecordOldFoo(id)) {
                metricService.recordOldFoo(id);
            }
            throw new OldFooFoundException(foo);
        }
        return foo;
    }

    public String getFoo6(final String id) {
        if(id.startsWith("OLD")) {
            Log.info("Found old id:" + id + fooService.isValidId(id));
            throw new OldFooFoundException(id);
        }
        return fooService.getFoo1(id);
    }

    public String getFoo7(final String id) {
        final String foo = fooService.getFoo1(id);
        if(foo.startsWith("OLD")) {
            Log.info("Found old id:" + id + fooService.isValidId(id));
            throw new OldFooFoundException(id);
        }
        return foo;
    }

    public String getFoo8(final String id) {
        // Also a broken case. This is the same as getFoo4(..) but with the
        // "if(fooService.isValidId(id)) {" inverted.
        // The first DI: fooService.isValidId(id) does not have the property: allPathsReturnAltValue (null).
        if(id.startsWith("OLD-")) {
            Log.info("Found old id:" + id + fooService.isValidId(id));
            return "hello";
        }

        if(!fooService.isValidId(id)) {
            return fooService.getFooWithValidId1(id);
        }
        final String foo = fooService.getFoo1(id);
        if(foo.startsWith("OLD-")) {
            if(fooService.shouldRecordOldFoo(id)) {
                metricService.recordOldFoo(id);
            }
            throw new OldFooFoundException(foo);
        }
        return foo;
    }

    private String getFooImpl1(final String id) {
        try {
            return fooService.getFoo1(id);
        } catch (FooNotFoundException e) {
            return fooService.getFoo2(id);
        }
    }
}
