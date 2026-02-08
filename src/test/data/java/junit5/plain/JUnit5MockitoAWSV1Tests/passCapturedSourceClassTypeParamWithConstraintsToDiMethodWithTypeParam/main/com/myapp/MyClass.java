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

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Optional;

public class MyClass<T extends FooData, ID> {
    private final CrudRepository<T, ID> fooDataRepo;
    private final DynamoDBMapper dynamoDBMapper;

    public MyClass(
            final CrudRepository<T, ID> fooDataRepo,
            final DynamoDBMapper dynamoDBMapper) {
        this.fooDataRepo = fooDataRepo;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public T trySave1(final T theData) {
        return Optional.of(theData).map(fooDataRepo::save).get();
    }

    public T trySave2(final T theData) {
        return Optional.of(theData).map(entity -> fooDataRepo.save(entity)).get();
    }

    public void trySave3(final T theData) {
        Optional.of(theData).ifPresent(fooDataRepo::save);
    }

    public void trySave4(final T theData) {
        Optional.of(theData).ifPresent(entity -> {
            fooDataRepo.save(entity);
        });
    }

    public void trySave5(final T theData) {
        Optional.of(theData).ifPresent(dynamoDBMapper::save);
    }

    public void trySave6(final T theData) {
        Optional.of(theData).ifPresent(object -> {
            dynamoDBMapper.save(object);
        });
    }

    public T tryGet1(final T theData) {
        return Optional.of(theData).map(dynamoDBMapper::load).get();
    }

    public T tryGet2(final T theData) {
        return Optional.of(theData).map(keyObject -> dynamoDBMapper.load(keyObject)).get();
    }

    public T tryGet3(final T theData) {
        return Optional.of(theData).map(keyObject -> {
            return dynamoDBMapper.load(keyObject);
        }).get();
    }

}
