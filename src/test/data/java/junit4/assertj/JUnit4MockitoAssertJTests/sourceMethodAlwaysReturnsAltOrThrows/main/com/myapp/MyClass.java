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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class MyClass {

    private final MetricsAdapter metricsAdapter;
    private final UnmockableMetrics unmockableMetrics;

    public MyClass(final MetricsAdapter metricsAdapter, final UnmockableMetrics unmockableMetrics) {
        this.metricsAdapter = metricsAdapter;
        this.unmockableMetrics = unmockableMetrics;
    }

    public void doSomething1() {
        metricsAdapter.recordMethodCall();
        throw new NotImplementedUncheckedException();
    }
    public void doSomething2() throws NotImplementedUncheckedException {
        metricsAdapter.recordMethodCall();
        throw new NotImplementedUncheckedException();
    }
    public void doSomething3() throws NotImplementedCheckedException {
        metricsAdapter.recordMethodCall();
        throw new NotImplementedCheckedException();
    }
    public void doSomething4() {
        unmockableMetrics.recordMethodCall();
        throw new NotImplementedUncheckedException();
    }
    public void doSomething5() throws NotImplementedUncheckedException {
        unmockableMetrics.recordMethodCall();
        throw new NotImplementedUncheckedException();
    }
    public void doSomething6() throws NotImplementedCheckedException {
        unmockableMetrics.recordMethodCall();
        throw new NotImplementedCheckedException();
    }
    public void doSomething7(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            metricsAdapter.recordOldOrderMethodCall(orderId);
        } else {
            metricsAdapter.recordNewOrderMethodCall(orderId);
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething8(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            unmockableMetrics.recordOldOrderMethodCall(orderId);
        } else {
            unmockableMetrics.recordNewOrderMethodCall(orderId);
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething9(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            metricsAdapter.recordOldOrderMethodCall(orderId);
            return;
        } else {
            metricsAdapter.recordNewOrderMethodCall(orderId);
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething10(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            metricsAdapter.recordOldOrderMethodCall(orderId);
            return;
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething11(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            unmockableMetrics.recordOldOrderMethodCall(orderId);
            return;
        } else {
            unmockableMetrics.recordNewOrderMethodCall(orderId);
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething12(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            unmockableMetrics.recordOldOrderMethodCall(orderId);
            return;
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething13(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            metricsAdapter.recordOldOrderMethodCall(orderId);
        } else {
            metricsAdapter.recordNewOrderMethodCall(orderId);
            return;
        }
        throw new NotImplementedCheckedException();
    }
    public void doSomething14(final String orderId) throws NotImplementedCheckedException {
        if(orderId.startsWith("OLD")) {
            unmockableMetrics.recordOldOrderMethodCall(orderId);
        } else {
            unmockableMetrics.recordNewOrderMethodCall(orderId);
            return;
        }
        throw new NotImplementedCheckedException();
    }
    public List<String> getSomething1() {
        metricsAdapter.recordMethodCall();
        return Collections.emptyList();
    }
    public List<FooData> getSomething2() {
        metricsAdapter.recordMethodCall();
        return Collections.emptyList();
    }
    public List<String> getSomething3() {
        unmockableMetrics.recordMethodCall();
        return Collections.emptyList();
    }
    public List<FooData> getSomething4() {
        unmockableMetrics.recordMethodCall();
        return Collections.emptyList();
    }
    public List<String> getSomething5() {
        return Collections.emptyList();
    }
    public List<FooData> getSomething6() {
        return Collections.emptyList();
    }
    public List<FooData> getSomething7(final String orderId) {
        if(orderId.startsWith("OLD")) {
            metricsAdapter.recordOldOrderMethodCall(orderId);
        } else {
            metricsAdapter.recordNewOrderMethodCall(orderId);
        }
        return Collections.emptyList();
    }
    public List<FooData> getSomething8(final String orderId) {
        if(orderId.startsWith("OLD")) {
            unmockableMetrics.recordOldOrderMethodCall(orderId);
        } else {
            unmockableMetrics.recordNewOrderMethodCall(orderId);
        }
        return Collections.emptyList();
    }
    public List<FooData> getSomething9(final String orderId) {
        if(orderId.startsWith("OLD")) {
            metricsAdapter.recordOldOrderMethodCall(orderId);
            return Arrays.asList(new FooData(orderId, "name"));
        } else {
            metricsAdapter.recordNewOrderMethodCall(orderId);
        }
        return Collections.emptyList();
    }
    public List<FooData> getSomething10(final String orderId) {
        if(orderId.startsWith("OLD")) {
            unmockableMetrics.recordOldOrderMethodCall(orderId);
            return Arrays.asList(new FooData(orderId, "name"));
        } else {
            unmockableMetrics.recordNewOrderMethodCall(orderId);
        }
        return Collections.emptyList();
    }
    public List<String> getSomething11() {
        metricsAdapter.recordMethodCall();
        final String val = new Callable<String>() {
            @Override
            public String call() {
                metricsAdapter.recordInsideCallableCall();
                return "test";
            }
        }.call();
        return Collections.emptyList();
    }
    public List<String> getSomething12() {
        unmockableMetrics.recordMethodCall();
        final String val = new Callable<String>() {
            @Override
            public String call() {
                unmockableMetrics.recordInsideCallableCall();
                return "test";
            }
        }.call();
        return Collections.emptyList();
    }
}
