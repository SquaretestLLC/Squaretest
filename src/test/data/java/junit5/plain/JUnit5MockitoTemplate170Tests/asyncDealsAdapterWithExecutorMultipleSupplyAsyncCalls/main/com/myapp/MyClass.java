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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClass {
    private static final Logger Log = Logger.getLogger(MyClass.class.getName());

    private final DealsServiceClient dealsService;
    private final Executor executor;

    public MyClass(
            final DealsServiceClient dealsService, final Executor executor) {
        this.dealsService = dealsService;
        this.executor = executor;
    }

    public CompletableFuture<List<DisplayDeal>> getDeals(final String userId) {
        CompletableFuture.supplyAsync(() -> {
            try {
                return getDealsInternal(userId);
            } catch (final ServiceException | NetworkException e) {
                Log.log(Level.WARNING, "Exception querying deals service.", e);
                throw new CompletionException(e);
            }
        }, executor);
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getDealsInternal(userId);
            } catch (final ServiceException | NetworkException e) {
                Log.log(Level.WARNING, "Exception querying deals service.", e);
                throw new CompletionException(e);
            }
        }, executor);
    }

    private List<DisplayDeal> getDealsInternal(final String userId) throws ServiceException, NetworkException {
        final GetDealsRequest request = new GetDealsRequest();
        request.setUserId(userId);
        return convertToDisplayDeals(dealsService.getDeals(request).getActiveDeals());
    }

    private List<DisplayDeal> convertToDisplayDeals(final List<Deal> deals) {
        final List<DisplayDeal> displayDeals = new ArrayList<>(deals.size());
        for (final Deal deal : deals) {
            try {
                displayDeals.add(new DisplayDeal(deal.getId(), deal.getProductName(), deal.getPrice(),
                        new URI(deal.getImageUrl())));
            } catch (final URISyntaxException e) {
                // Skip deals with invalid URIs.
                Log.log(Level.WARNING, "Deal with invalid uri.", e);
            }
        }
        return displayDeals;
    }
}
