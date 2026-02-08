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

import java.util.Optional;
import java.util.Set;

public class MyClass {
    private final FooService fooService;
    private final MyValidator validator;
    private final MetricsAdapter metrics;
    private final UnmockableMetrics unmockableMetrics;

    public MyClass(
            final FooService fooService, final MyValidator validator,
            final MetricsAdapter metrics, final UnmockableMetrics unmockableMetrics) {
        this.fooService = fooService;
        this.validator = validator;
        this.metrics = metrics;
        this.unmockableMetrics = unmockableMetrics;
    }

    public void storeFoo1(final FooData fooData) throws FooServiceException {
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        if (validateResult.isEmpty()) {
            fooService.storeFoo1(fooData);
            return;
        }
        throw new FooDataValidationException(validateResult);
    }

    public void storeFoo2(final FooData fooData) throws FooServiceException {
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        if (!validateResult.isEmpty()) {
            throw new FooDataValidationException(validateResult);
        }
        fooService.storeFoo1(fooData);
    }

    public void storeFoo3(final FooData fooData) throws FooServiceException {
        metrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        metrics.recordAfterValidatorCalled();
        if (validateResult.isEmpty()) {
            metrics.recordValidatorResultIsEmpty();
            fooService.storeFoo1(fooData);
            metrics.recordAfterStoreFoo1Called();
            return;
        }
        metrics.recordValidatorResultNotEmpty();
        throw new FooDataValidationException(validateResult);
    }

    public void storeFoo4(final FooData fooData) throws FooServiceException {
        metrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        metrics.recordAfterValidatorCalled();
        if (!validateResult.isEmpty()) {
            metrics.recordValidatorResultNotEmpty();
            throw new FooDataValidationException(validateResult);
        }
        metrics.recordValidatorResultIsEmpty();
        fooService.storeFoo1(fooData);
        metrics.recordAfterStoreFoo1Called();
    }
    public void storeFoo5(final FooData fooData) throws FooServiceException {
        unmockableMetrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        unmockableMetrics.recordAfterValidatorCalled();
        if (validateResult.isEmpty()) {
            unmockableMetrics.recordValidatorResultIsEmpty();
            fooService.storeFoo1(fooData);
            unmockableMetrics.recordAfterStoreFoo1Called();
            return;
        }
        unmockableMetrics.recordValidatorResultNotEmpty();
        throw new FooDataValidationException(validateResult);
    }

    public void storeFoo6(final FooData fooData) throws FooServiceException {
        unmockableMetrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        unmockableMetrics.recordAfterValidatorCalled();
        if (!validateResult.isEmpty()) {
            unmockableMetrics.recordValidatorResultNotEmpty();
            throw new FooDataValidationException(validateResult);
        }
        unmockableMetrics.recordValidatorResultIsEmpty();
        fooService.storeFoo1(fooData);
        unmockableMetrics.recordAfterStoreFoo1Called();
    }
    public void storeFoo7(final FooData fooData) throws FooServiceException, GetFooException, FooAlreadyExistsException {
        metrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        metrics.recordAfterValidatorCalled();
        if (validateResult.isEmpty()) {
            metrics.recordValidatorResultIsEmpty();
            final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
            if(existingFoo.isPresent()) {
                throw new FooAlreadyExistsException();
            }
            fooService.storeFoo1(fooData);
            metrics.recordAfterStoreFoo1Called();
            return;
        }
        metrics.recordValidatorResultNotEmpty();
        throw new FooDataValidationException(validateResult);
    }

    public void storeFoo8(final FooData fooData) throws FooServiceException, GetFooException, FooAlreadyExistsException {
        metrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        metrics.recordAfterValidatorCalled();
        if (!validateResult.isEmpty()) {
            metrics.recordValidatorResultNotEmpty();
            throw new FooDataValidationException(validateResult);
        }
        metrics.recordValidatorResultIsEmpty();
        final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
        if(existingFoo.isPresent()) {
            throw new FooAlreadyExistsException();
        }
        fooService.storeFoo1(fooData);
        metrics.recordAfterStoreFoo1Called();
    }
    public void storeFoo9(final FooData fooData) throws FooServiceException, GetFooException, FooDoesNotExistException {
        metrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        metrics.recordAfterValidatorCalled();
        if (validateResult.isEmpty()) {
            metrics.recordValidatorResultIsEmpty();
            final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
            if(existingFoo.isEmpty()) {
                throw new FooDoesNotExistException();
            }
            fooService.storeFoo1(fooData);
            metrics.recordAfterStoreFoo1Called();
            return;
        }
        metrics.recordValidatorResultNotEmpty();
        throw new FooDataValidationException(validateResult);
    }

    public void storeFoo10(final FooData fooData) throws FooServiceException, GetFooException, FooDoesNotExistException {
        metrics.recordMethodEnter();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        metrics.recordAfterValidatorCalled();
        if (!validateResult.isEmpty()) {
            metrics.recordValidatorResultNotEmpty();
            throw new FooDataValidationException(validateResult);
        }
        metrics.recordValidatorResultIsEmpty();
        final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
        if(existingFoo.isEmpty()) {
            throw new FooDoesNotExistException();
        }
        fooService.storeFoo1(fooData);
        metrics.recordAfterStoreFoo1Called();
    }

    public void storeFoo11(final FooData fooData) throws FooServiceException, GetFooException, FooAlreadyExistsException {
        final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
        metrics.recordGetFooByIdCalled();
        if(existingFoo.isPresent()) {
            metrics.recordExistingFooPresent();
            throw new FooAlreadyExistsException();
        }
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        if (validateResult.isEmpty()) {
            fooService.storeFoo1(fooData);
            return;
        }
        throw new FooDataValidationException(validateResult);
    }

    public void storeFoo12(final FooData fooData) throws FooServiceException, GetFooException, FooDoesNotExistException {
        final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
        metrics.recordGetFooByIdCalled();
        if(existingFoo.isEmpty()) {
            metrics.recordExistingFooAbsent();
            throw new FooDoesNotExistException();
        }
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        if (validateResult.isEmpty()) {
            fooService.storeFoo1(fooData);
            return;
        }
        throw new FooDataValidationException(validateResult);
    }
    public void storeFoo13(final FooData fooData) throws FooServiceException, GetFooException {
        fooService.getFooById(fooData.getId());
        metrics.recordGetFooByIdCalled();
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        if (validateResult.isEmpty()) {
            fooService.storeFoo1(fooData);
            return;
        }
        throw new FooDataValidationException(validateResult);
    }
    public void storeFoo14(final FooData fooData) throws FooServiceException, GetFooException, FooDoesNotExistException {
        final Optional<FooData> existingFoo = fooService.getFooById(fooData.getId());
        metrics.recordGetFooByIdCalled();
        if(existingFoo.isEmpty()) {
            throwFooDoesNotExistException();
        }
        final Set<MyConstraintViolation<FooData>> validateResult = validator.validate(fooData);
        if (validateResult.isEmpty()) {
            fooService.storeFoo1(fooData);
            return;
        }
        throwFooDataValidationException(validateResult);
    }

    public FooData getFooById1(final String id) throws GetFooException, FooDoesNotExistException {
        final Optional<FooData> theFoo = fooService.getFooById(id);
        if(theFoo.isPresent()) {
            return theFoo.get();
        }
        throwFooDoesNotExistException();
        return null;
    }
    public FooData getFooById2(final String id) throws GetFooException, FooDoesNotExistException {
        final Optional<FooData> theFoo = fooService.getFooById(id);
        if(theFoo.isEmpty()) {
            throwFooDoesNotExistException();
        }
        return theFoo.get();
    }
    public FooData getFooById3(final String id) throws GetFooException, FooDoesNotExistException {
        final Optional<FooData> theFoo = fooService.getFooById(id);
        if(theFoo.isEmpty()) {
            throwFooDoesNotExistException();
            return null;
        }
        return theFoo.get();
    }

    public void storeFoo15(final FooData fooData) throws FooServiceException {
        validator.validate(fooData);
        fooService.storeFoo1(fooData);
    }

    private void throwFooDoesNotExistException() throws FooDoesNotExistException {
        metrics.recordExistingFooAbsent();
        throw new FooDoesNotExistException();
    }
    private void throwFooDataValidationException(final Set<MyConstraintViolation<FooData>> validateResult) throws FooDataValidationException {
        metrics.recordValidationFailure();
        throw new FooDataValidationException(validateResult);
    }
}
