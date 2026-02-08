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

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MyClass {

    private final Bucket bucket;

    public MyClass(final Bucket bucket) {
        this.bucket = bucket;
    }

    public void tryExists() {
        final Bucket.BucketSourceOption options = Bucket.BucketSourceOption.userProject("userProject");
        final boolean result = bucket.exists(options);
    }

    public void tryReload() {
        final Bucket.BucketSourceOption options = Bucket.BucketSourceOption.userProject("userProject");
        final Bucket result = bucket.reload(options);
    }

    public void tryUpdate() {
        final Storage.BucketTargetOption options = Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Bucket result = bucket.update(options);
    }

    public void tryDelete() {
        final Bucket.BucketSourceOption options = Bucket.BucketSourceOption.userProject("userProject");
        final boolean result = bucket.delete(options);
    }

    public void tryList() {
        final Page<Blob> result = bucket.list(Storage.BlobListOption.prefix("prefix"));
    }

    public void tryGet1() {
        final Blob result = bucket.get("blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK));
    }

    public void tryGet2() {
        final List<Blob> result = bucket.get("blobName1", "blobName2", "blobNames");
    }

    public void tryGet3() {
        final Iterable<String> blobNames = Arrays.asList("value");
        final List<Blob> result = bucket.get(blobNames);
    }

    public void tryCreate1() {
        final Bucket.BlobTargetOption options = Bucket.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = bucket.create("blob", "content".getBytes(), "contentType", options);
    }

    public void tryCreate2() {
        final InputStream content = new ByteArrayInputStream("content".getBytes());
        final Bucket.BlobWriteOption options = Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = bucket.create("blob", content, "contentType", options);
    }

    public void tryCreate3() {
        final Bucket.BlobTargetOption options = Bucket.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = bucket.create("blob", "content".getBytes(), options);
    }

    public void tryCreate4() {
        final InputStream content = new ByteArrayInputStream("content".getBytes());
        final Bucket.BlobWriteOption options = Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = bucket.create("blob", content, options);
    }

    public void tryGetAcl() {
        final Acl.Entity entity = null;
        final Acl result = bucket.getAcl(entity);
    }

    public void tryDeleteAcl() {
        final Acl.Entity entity = null;
        final boolean result = bucket.deleteAcl(entity);
    }

    public void tryCreateAcl() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = bucket.createAcl(acl);
    }

    public void tryUpdateAcl() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = bucket.updateAcl(acl);
    }

    public void tryListAcls() {
        final List<Acl> result = bucket.listAcls();
    }

    public void tryGetDefaultAcl() {
        final Acl.Entity entity = null;
        final Acl result = bucket.getDefaultAcl(entity);
    }

    public void tryDeleteDefaultAcl() {
        final Acl.Entity entity = null;
        final boolean result = bucket.deleteDefaultAcl(entity);
    }

    public void tryCreateDefaultAcl() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = bucket.createDefaultAcl(acl);
    }

    public void tryUpdateDefaultAcl() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = bucket.updateDefaultAcl(acl);
    }

    public void tryListDefaultAcls() {
        final List<Acl> result = bucket.listDefaultAcls();
    }

    public void tryLockRetentionPolicy() {
        final Storage.BucketTargetOption options = Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Bucket result = bucket.lockRetentionPolicy(options);
    }

    public void tryGetStorage() {
        final Storage result = bucket.getStorage();
    }

    public void tryToBuilder() {
        final Bucket.Builder result = bucket.toBuilder();
    }

    public void tryEquals() {
        final boolean result = bucket.equals("obj");
    }

    public void tryHashCode() {
        final int result = bucket.hashCode();
    }
}
