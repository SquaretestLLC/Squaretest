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
package com.myapp.otherbeans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * This is a bean, because it has @JsonIgnore on a field and also has no static creator method that uses the type.
 */
public class ResponseWrapperBeanAnnotationsNoStaticCreator<T> {

    private boolean success;

    private String code;

    private String errorMessage;

    private T payload;

    @JsonIgnore
    private Map<String, String[]> httpHeaders;

    public boolean isSuccess() {
        return success;
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Map<String, String[]> getHttpHeaders() {
        return httpHeaders;
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<T> setHttpHeaders(Map<String, String[]> httpHeaders) {
        this.httpHeaders = httpHeaders;
        return this;
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<T> addHeaders(String key, String[] value) {
        if (httpHeaders == null) {
            httpHeaders = new HashMap<>();
        }
        httpHeaders.put(key, value);
        return this;
    }

    public void ifSuccess(Consumer<? super T> action) {
        if (success) {
            action.accept(payload);
        }
    }
}
