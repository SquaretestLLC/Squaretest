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
package com.myapp.metrics;

public class MetricAdapter {
    public void recordEvent(final MetricEvent e) { }

    public void recordAmazonServiceException(final String orderId) { }
    public void recordSDKClientException(final String orderId) { }
    public void recordIOException(final String orderId) { }
    public void recordFinallyBlock(final String orderId) { }

}
