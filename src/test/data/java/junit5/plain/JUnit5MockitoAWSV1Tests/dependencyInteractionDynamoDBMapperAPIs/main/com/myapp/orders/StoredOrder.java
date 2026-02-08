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
package com.myapp.orders;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

@DynamoDBTable(tableName = "Orders")
public class StoredOrder {

    private static final String PURCHASE_ID = "purchase_id";
    private static final String LICENSE_NAME = "license_name";
    private static final String LICENSE_TYPE = "license_type";
    private static final String NUMBER_OF_USERS = "number_of_users";
    private static final String DELIVERY_EMAIL_ADDRESS = "delivery_email";

    private static final String LICENSE_TEXT = "license_text";
    private static final String CREATOR_NAME = "creator";
    private static final String NOTES = "notes";

    private static final String LOAN_DOCUMENT_S3_Path = "loan_document_s3_path";

    private String purchaseId;
    private String nameOnTheLicense;
    private LicenseType licenseType;
    private int numberOfUsers;
    private String deliveryEmailAddress;
    private String creator;
    private String notes;

    private String loanDocumentS3Path;
    private String loanDocumentText;

    private @Nonnull String licenseText;

    /**
     * Provided for DynamoDB Mapper to use.
     */
    public StoredOrder() {
    }

    public StoredOrder(
            final String purchaseId,
            final String licenseName,
            final LicenseType licenseType,
            final int numberOfUsers,
            final String deliveryEmailAddress,
            @Nonnull final String licenseText,
            @Nullable final String creator,
            @Nullable final String notes) {
        this.purchaseId = purchaseId;
        this.nameOnTheLicense = licenseName;
        this.licenseType = licenseType;
        this.numberOfUsers = numberOfUsers;
        this.deliveryEmailAddress = deliveryEmailAddress;
        this.licenseText = licenseText;
        this.creator = creator;
        this.notes = notes;
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

    @DynamoDBAttribute(attributeName = LICENSE_TYPE)
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    public LicenseType getLicenseType() {
        return licenseType;
    }
    public void setLicenseType(final LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    @DynamoDBAttribute(attributeName = NUMBER_OF_USERS)
    public int getNumberOfUsers() {
        return numberOfUsers;
    }
    public void setNumberOfUsers(final int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    @DynamoDBAttribute(attributeName = DELIVERY_EMAIL_ADDRESS)
    public String getDeliveryEmailAddress() {
        return deliveryEmailAddress;
    }
    public void setDeliveryEmailAddress(final String deliveryEmailAddress) {
        this.deliveryEmailAddress = deliveryEmailAddress;
    }

    @DynamoDBAttribute(attributeName = LICENSE_TEXT)
    public String getLicenseText() {
        return licenseText;
    }
    public void setLicenseText(@Nonnull final String licenseText) {
        this.licenseText = licenseText;
    }

    @DynamoDBAttribute(attributeName = CREATOR_NAME)
    public String getCreator() {
        return creator;
    }
    public void setCreator(final String creator) {
        this.creator = creator;
    }

    @DynamoDBAttribute(attributeName = NOTES)
    public String getNotes() {
        return notes;
    }
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    @DynamoDBAttribute(attributeName = LOAN_DOCUMENT_S3_Path)
    public String getLoanDocumentS3Path() {
        return loanDocumentS3Path;
    }
    public void setLoanDocumentS3Path(String loanDocumentS3Path) {
        this.loanDocumentS3Path = loanDocumentS3Path;
    }

    public String getLoanDocumentText() {
        return loanDocumentText;
    }

    public void setLoanDocumentText(final String loanDocumentText) {
        this.loanDocumentText = loanDocumentText;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StoredOrder that = (StoredOrder) o;
        return numberOfUsers == that.numberOfUsers && Objects.equals(purchaseId, that.purchaseId) && Objects.equals(nameOnTheLicense, that.nameOnTheLicense) && licenseType == that.licenseType && Objects.equals(deliveryEmailAddress, that.deliveryEmailAddress) && Objects.equals(licenseText, that.licenseText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, nameOnTheLicense, licenseType, numberOfUsers, deliveryEmailAddress, licenseText);
    }
}
