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

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObject;
import java.io.IOException;
import org.apache.commons.lang3.Validate;
import org.apache.pdfbox.pdmodel.PDDocument;

public class MyClass {

    private static final String PathFormat = "%s/purchaseOrder.pdf";

    private final COSClient cosClient;
    private final MetricAdapter metricAdapter;
    private final String bucketName;

    public MyClass(
            final COSClient cosClient,
            final MetricAdapter metricAdapter,
            final String bucketName) {
        this.cosClient = cosClient;
        this.metricAdapter = metricAdapter;
        this.bucketName = bucketName;
    }

    public PDDocument getPurchaseOrder(final String orderId) throws PurchaseOrderStoreException {
        Validate.notBlank(orderId, "Orderid cannot be blank");
        final String path = String.format(PathFormat, orderId);
        try (final COSObject cosObject = cosClient.getObject(bucketName, path)) {
            final PDDocument purchaseOrderDoc = PDDocument.load(cosObject.getObjectContent());
            metricAdapter.recordEvent(MetricEvent.Success);
            return purchaseOrderDoc;
        } catch (final CosServiceException e) {
            metricAdapter.recordEvent(MetricEvent.COSException);
            throw new PurchaseOrderStoreException(
                    String.format("CosServiceException while downloading object with path: %s", path), e);
        } catch (final CosClientException e) {
            metricAdapter.recordEvent(MetricEvent.COSClientException);
            throw new PurchaseOrderStoreException(
                    String.format("CosClientException while downloading object with path: %s", path), e);
        } catch (final IOException e) {
            metricAdapter.recordEvent(MetricEvent.IOException);
            throw new PurchaseOrderStoreException(
                    String.format("IOException while downloading object with path: %s", path), e);
        }
    }
}
