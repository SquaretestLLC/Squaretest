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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * This is NOT a bean it has non-getter/setter-methods as well as a static creator method that uses the type param.
 */
public class ResponseWrapper<T> {

    private boolean success;

    private String code;

    private String errorMessage;

    private T payload;

    private Map<String, String[]> httpHeaders;

    public boolean isSuccess() {
        return success;
    }

    public ResponseWrapper<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseWrapper<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseWrapper<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public ResponseWrapper<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Map<String, String[]> getHttpHeaders() {
        return httpHeaders;
    }

    public ResponseWrapper<T> setHttpHeaders(Map<String, String[]> httpHeaders) {
        this.httpHeaders = httpHeaders;
        return this;
    }

    public ResponseWrapper<T> addHeaders(String key, String[] value) {
        if (httpHeaders == null) {
            httpHeaders = new HashMap<>();
        }
        httpHeaders.put(key, value);
        return this;
    }

    public static <T> ResponseWrapper<T> success() {
        return ResponseWrapper.success(null);
    }

    public static <T> ResponseWrapper<T> success(T obj) {
        return new ResponseWrapper()
                .setPayload(obj)
                .setCode("200")
                .setSuccess(true);
    }

    public static <T> ResponseWrapper<T> failure() {
        return new ResponseWrapper()
                .setSuccess(false)
                .setCode("500")
                .setErrorMessage("Internal Server Error");
    }

    public static <T> ResponseWrapper<T> failure(String code, String errorMessage) {
        return new ResponseWrapper()
                .setSuccess(false)
                .setCode(code)
                .setErrorMessage(errorMessage);
    }

    public void ifSuccess(Consumer<? super T> action) {
        if (success) {
            action.accept(payload);
        }
    }
}
