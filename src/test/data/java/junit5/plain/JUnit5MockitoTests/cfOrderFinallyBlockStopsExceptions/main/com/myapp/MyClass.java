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

public class MyClass {
    private final OrderService orderService;
    private final MetricService metricService;

    public MyClass(final OrderService orderService, final MetricService metricService) {
        this.orderService = orderService;
        this.metricService = metricService;
    }

    public List<Order> getOrdersWithId1(final String customerId) {
        return orderService.getOrdersWithCustomerId(customerId);
    }

    public List<Order> safeGetOrdersWithId1(final String customerId) {
        List<Order> ret = null;
        try {
            ret = getOrdersWithId1(customerId);
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
        } catch (final Exception e) {
            metricService.recordOrderException(e);
        } finally {
            return ret;
        }
    }

    public List<Order> safeGetOrdersWithId2(final String customerId) {
        List<Order> ret = null;
        try {
            ret = getOrdersWithId1(customerId);
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
        } catch (final Exception e) {
            metricService.recordOrderException(e);
        }
        return ret;
    }

    public List<Order> safeGetOrdersWithId3(final String customerId) {
        try {
            return getOrdersWithId1(customerId);
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
        } catch (final Exception e) {
            metricService.recordOrderException(e);
        }
        return null;
    }

    public List<Order> safeGetOrdersWithId4(final String customerId) {
        try {
            return getOrdersWithId1(customerId);
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
        } catch (final Exception e) {
            metricService.recordOrderException(e);
        }
        return Collections.emptyList();
    }

    public List<Order> safeGetOrdersWithId5(final String customerId) {
        List<Order> ret = null;
        try {
            ret = getOrdersWithId1(customerId);
        } finally {
            return ret;
        }
    }

    public List<Order> safeGetOrdersWithId6(final String customerId) {
        try {
            return getOrdersWithId1(customerId);
        } catch (final NetworkException e) {
            metricService.recordOrderNetworkException(e);
            return null;
        } catch (final ServiceException e) {
            metricService.recordOrderServiceException(e);
            return Arrays.asList(new Order("defaultOrderId", "defaultDescription"));
        } catch (final Exception e) {
            metricService.recordOrderException(e);
        }
        return Collections.emptyList();
    }
}
