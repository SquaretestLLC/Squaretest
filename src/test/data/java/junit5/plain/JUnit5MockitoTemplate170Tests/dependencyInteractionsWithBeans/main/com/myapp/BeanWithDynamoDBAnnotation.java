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

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Orders")
public class BeanWithDynamoDBAnnotation {

    private static final String PURCHASE_ID = "purchase_id";
    private static final String LICENSE_NAME = "license_name";

    private String purchaseId;
    private String nameOnTheLicense;

    /**
     * Provided for DynamoDB Mapper to use.
     */
    public BeanWithDynamoDBAnnotation() {
    }

    public void doSomething() {
        // This would normally disqualify BeanWithDynamoDBAnnotation from being considered to be a DTO bean.
        // The DynamoDB annotation and no-args constructor override this.
    }

    @DynamoDBHashKey(attributeName = PURCHASE_ID)
    public String getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(final String purchaseId) {
        this.purchaseId = purchaseId;
    }

    @DynamoDBAttribute(attributeName = LICENSE_NAME)
    public String getNameOnTheLicense() {
        return nameOnTheLicense;
    }
    public void setNameOnTheLicense(final String nameOnTheLicense) {
        this.nameOnTheLicense = nameOnTheLicense;
    }
}