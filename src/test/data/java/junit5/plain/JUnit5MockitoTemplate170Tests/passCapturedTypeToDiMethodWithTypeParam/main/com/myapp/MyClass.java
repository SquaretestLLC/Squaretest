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

public class MyClass {
    private final FooDataRepo fooDataRepo;
    private final DynamoDBMapper dynamoDBMapper;

    public MyClass(
            final FooDataRepo fooDataRepo,
            final DynamoDBMapper dynamoDBMapper) {
        this.fooDataRepo = fooDataRepo;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public FooData trySave1(final FooData fooData) {
        return Optional.of(fooData).map(fooDataRepo::save).get();
    }

    public FooData trySave2(final FooData fooData) {
        return Optional.of(fooData).map(entity -> fooDataRepo.save(entity)).get();
    }

    public void trySave3(final FooData fooData) {
        Optional.of(fooData).ifPresent(fooDataRepo::save);
    }

    public void trySave4(final FooData fooData) {
        Optional.of(fooData).ifPresent(entity -> {
            fooDataRepo.save(entity);
        });
    }

    public void trySave5(final FooData fooData) {
        Optional.of(fooData).ifPresent(dynamoDBMapper::save);
    }

    public void trySave6(final FooData fooData) {
        Optional.of(fooData).ifPresent(object -> {
            dynamoDBMapper.save(object);
        });
    }

    public FooData tryGet1(final FooData fooData) {
        return Optional.of(fooData).map(dynamoDBMapper::load).get();
    }

    public FooData tryGet2(final FooData fooData) {
        return Optional.of(fooData).map(keyObject -> dynamoDBMapper.load(keyObject)).get();
    }

    public FooData tryGet3(final FooData fooData) {
        return Optional.of(fooData).map(keyObject -> {
            return dynamoDBMapper.load(keyObject);
        }).get();
    }

}
