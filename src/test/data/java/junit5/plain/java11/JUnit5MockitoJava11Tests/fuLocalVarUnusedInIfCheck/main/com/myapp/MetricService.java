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

public interface MetricService {
    void recordNullOrder(final String orderId);

    void recordGetDescriptionHelper(Order order);

    void recordCallingGetOrderHelper2(String orderId);

    void recordOrElseThrowBlock(String orderId);

    void recordCallingGetOrderHelper3(String orderId);

    void recordCreateFailed(String orderId);

    void recordCreateSuccess(String orderId);

    void recordUpdateFail(String orderId);

    void recordUpdateSuccess(String orderId);

    void recordIncludeFullOrder(String orderId);

    void recordFirstGetOrderCallSuccess(String orderId);

    void recordFirstCallNull(String orderId);

    void recordOrderIdEndsWithFu(String orderId);
}
