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
import com.google.cloud.Policy;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyClass {

    private final Storage storage;

    public MyClass(final Storage storage) {
        this.storage = storage;
    }

    public void tryCreate1() {
        final BucketInfo bucketInfo = BucketInfo.newBuilder("bucketName").build();
        final Storage.BucketTargetOption options = Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Bucket result = storage.create(bucketInfo, options);
    }

    public void tryCreate2() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobTargetOption options = Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.create(blobInfo, options);
    }

    public void tryCreate3() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobTargetOption options = Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.create(blobInfo, "content".getBytes(), options);
    }

    public void tryCreate4() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobTargetOption options = Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.create(blobInfo, "content".getBytes(), 0, 0, options);
    }

    public void tryCreate5() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final InputStream content = new ByteArrayInputStream("content".getBytes());
        final Storage.BlobWriteOption options = Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.create(blobInfo, content, options);
    }

    public void tryCreateFrom1() throws Exception {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobWriteOption options = Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.createFrom(blobInfo, Paths.get("filename.txt"), options);
    }

    public void tryCreateFrom2() throws Exception {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobWriteOption options = Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.createFrom(blobInfo, Paths.get("filename.txt"), 0, options);
    }

    public void tryCreateFrom3() throws Exception {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final InputStream content = new ByteArrayInputStream("content".getBytes());
        final Storage.BlobWriteOption options = Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.createFrom(blobInfo, content, options);
    }

    public void tryCreateFrom4() throws Exception {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final InputStream content = new ByteArrayInputStream("content".getBytes());
        final Storage.BlobWriteOption options = Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.createFrom(blobInfo, content, 0, options);
    }

    public void tryGet1() {
        final Storage.BucketGetOption options = Storage.BucketGetOption.metagenerationMatch(0L);
        final Bucket result = storage.get("bucket", options);
    }

    public void tryGet2() {
        final Blob result = storage.get("bucket", "blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK));
    }

    public void tryGet3() {
        final Blob result = storage.get(BlobId.of("bucket", "name"), Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK));
    }

    public void tryGet4() {
        final Blob result = storage.get(BlobId.of("bucket", "name"));
    }

    public void tryList1() {
        final Storage.BucketListOption options = Storage.BucketListOption.pageSize(0L);
        final Page<Bucket> result = storage.list(options);
    }

    public void tryList2() {
        final Page<Blob> result = storage.list("bucket", Storage.BlobListOption.prefix("prefix"));
    }

    public void tryUpdate1() {
        final BucketInfo bucketInfo = BucketInfo.newBuilder("bucketName").build();
        final Storage.BucketTargetOption options = Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Bucket result = storage.update(bucketInfo, options);
    }

    public void tryUpdate2() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobTargetOption options = Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Blob result = storage.update(blobInfo, options);
    }

    public void tryUpdate3() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Blob result = storage.update(blobInfo);
    }

    public void tryDelete1() {
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final boolean result = storage.delete("bucket", options);
    }

    public void tryDelete2() {
        final Storage.BlobSourceOption options = Storage.BlobSourceOption.generationMatch(0L);
        final boolean result = storage.delete("bucket", "blob", options);
    }

    public void tryDelete3() {
        final Storage.BlobSourceOption options = Storage.BlobSourceOption.generationMatch(0L);
        final boolean result = storage.delete(BlobId.of("bucket", "name"), options);
    }

    public void tryDelete4() {
        final boolean result = storage.delete(BlobId.of("bucket", "name"));
    }

    public void tryCompose() {
        final Storage.ComposeRequest composeRequest = Storage.ComposeRequest.newBuilder()
                .addSource("firstObjectName", "secondObjectName")
                .setTarget(BlobInfo.newBuilder("bucketName", "targetObjectName").build())
                .build();
        final Blob result = storage.compose(composeRequest);
    }

    public void tryCopy() {
        final Storage.CopyRequest copyRequest = Storage.CopyRequest.of(BlobId.of("sourceBucket", "sourceObjectName"), BlobInfo.newBuilder(BlobId.of("destinationBucket", "destinationObjectName")).build());
        final CopyWriter result = storage.copy(copyRequest);
    }

    public void tryReadAllBytes1() {
        final Storage.BlobSourceOption options = Storage.BlobSourceOption.generationMatch(0L);
        final byte[] result = storage.readAllBytes("bucket", "blob", options);
    }

    public void tryReadAllBytes2() {
        final Storage.BlobSourceOption options = Storage.BlobSourceOption.generationMatch(0L);
        final byte[] result = storage.readAllBytes(BlobId.of("bucket", "name"), options);
    }

    public void tryBatch() {
        final StorageBatch result = storage.batch();
    }

    public void tryReader1() {
        final Storage.BlobSourceOption options = Storage.BlobSourceOption.generationMatch(0L);
        final ReadChannel result = storage.reader("bucket", "blob", options);
    }

    public void tryReader2() {
        final Storage.BlobSourceOption options = Storage.BlobSourceOption.generationMatch(0L);
        final ReadChannel result = storage.reader(BlobId.of("bucket", "name"), options);
    }

    public void tryWriter1() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.BlobWriteOption options = Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final WriteChannel result = storage.writer(blobInfo, options);
    }

    public void tryWriter2() throws Exception {
        final WriteChannel result = storage.writer(new URL("https://example.com/"));
    }

    public void trySignUrl() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.SignUrlOption options = Storage.SignUrlOption.withExtHeaders(new HashMap<>());
        final URL result = storage.signUrl(blobInfo, 0L, TimeUnit.MILLISECONDS, options);
    }

    public void tryGenerateSignedPostPolicyV41() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final PostPolicyV4.PostFieldsV4 fields = PostPolicyV4.PostFieldsV4.of(new HashMap<>());
        final PostPolicyV4.PostConditionsV4 conditions = new PostPolicyV4.PostConditionsV4(PostPolicyV4.PostConditionsV4.Builder.newBuilder());
        final Storage.PostPolicyV4Option options = Storage.PostPolicyV4Option.withBucketBoundHostname("bucketBoundHostname");
        final PostPolicyV4 result = storage.generateSignedPostPolicyV4(blobInfo, 0L, TimeUnit.MILLISECONDS, fields, conditions, options);
    }

    public void tryGenerateSignedPostPolicyV42() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final PostPolicyV4.PostFieldsV4 fields = PostPolicyV4.PostFieldsV4.of(new HashMap<>());
        final Storage.PostPolicyV4Option options = Storage.PostPolicyV4Option.withBucketBoundHostname("bucketBoundHostname");
        final PostPolicyV4 result = storage.generateSignedPostPolicyV4(blobInfo, 0L, TimeUnit.MILLISECONDS, fields, options);
    }

    public void tryGenerateSignedPostPolicyV43() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final PostPolicyV4.PostConditionsV4 conditions = new PostPolicyV4.PostConditionsV4(PostPolicyV4.PostConditionsV4.Builder.newBuilder());
        final Storage.PostPolicyV4Option options = Storage.PostPolicyV4Option.withBucketBoundHostname("bucketBoundHostname");
        final PostPolicyV4 result = storage.generateSignedPostPolicyV4(blobInfo, 0L, TimeUnit.MILLISECONDS, conditions, options);
    }

    public void tryGenerateSignedPostPolicyV44() {
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Storage.PostPolicyV4Option options = Storage.PostPolicyV4Option.withBucketBoundHostname("bucketBoundHostname");
        final PostPolicyV4 result = storage.generateSignedPostPolicyV4(blobInfo, 0L, TimeUnit.MILLISECONDS, options);
    }

    public void tryGet5() {
        final Blob result = storage.get(BlobId.of("bucket", "name"));
    }

    public void tryGet6() {
        final Iterable<BlobId> blobIds = Arrays.asList(BlobId.of("bucket", "name"));
        final List<Blob> result = storage.get(blobIds);
    }

    public void tryUpdate4() {
        final BlobInfo blobInfos = BlobInfo.newBuilder(BlobId.of("bucket", "name")).build();
        final Blob result = storage.update(blobInfos);
    }

    public void tryUpdate5() {
        final Iterable<BlobInfo> blobInfos = Arrays.asList(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build());
        final List<Blob> result = storage.update(blobInfos);
    }

    public void tryDelete5() {
        final Boolean result = storage.delete(BlobId.of("bucket", "name"));
    }

    public void tryDelete6() {
        final Iterable<BlobId> blobIds = Arrays.asList(BlobId.of("bucket", "name"));
        final List<Boolean> result = storage.delete(blobIds);
    }

    public void tryGetAcl1() {
        final Acl.Entity entity = null;
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final Acl result = storage.getAcl("bucket", entity, options);
    }

    public void tryGetAcl2() {
        final Acl.Entity entity = null;
        final Acl result = storage.getAcl("bucket", entity);
    }

    public void tryDeleteAcl1() {
        final Acl.Entity entity = null;
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final boolean result = storage.deleteAcl("bucket", entity, options);
    }

    public void tryDeleteAcl2() {
        final Acl.Entity entity = null;
        final boolean result = storage.deleteAcl("bucket", entity);
    }

    public void tryCreateAcl1() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final Acl result = storage.createAcl("bucket", acl, options);
    }

    public void tryCreateAcl2() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = storage.createAcl("bucket", acl);
    }

    public void tryUpdateAcl1() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final Acl result = storage.updateAcl("bucket", acl, options);
    }

    public void tryUpdateAcl2() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = storage.updateAcl("bucket", acl);
    }

    public void tryListAcls1() {
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final List<Acl> result = storage.listAcls("bucket", options);
    }

    public void tryListAcls2() {
        final List<Acl> result = storage.listAcls("bucket");
    }

    public void tryGetDefaultAcl() {
        final Acl.Entity entity = null;
        final Acl result = storage.getDefaultAcl("bucket", entity);
    }

    public void tryDeleteDefaultAcl() {
        final Acl.Entity entity = null;
        final boolean result = storage.deleteDefaultAcl("bucket", entity);
    }

    public void tryCreateDefaultAcl() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = storage.createDefaultAcl("bucket", acl);
    }

    public void tryUpdateDefaultAcl() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = storage.updateDefaultAcl("bucket", acl);
    }

    public void tryListDefaultAcls() {
        final List<Acl> result = storage.listDefaultAcls("bucket");
    }

    public void tryGetAcl3() {
        final Acl.Entity entity = null;
        final Acl result = storage.getAcl(BlobId.of("bucket", "name"), entity);
    }

    public void tryDeleteAcl3() {
        final Acl.Entity entity = null;
        final boolean result = storage.deleteAcl(BlobId.of("bucket", "name"), entity);
    }

    public void tryCreateAcl3() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = storage.createAcl(BlobId.of("bucket", "name"), acl);
    }

    public void tryUpdateAcl3() {
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        final Acl result = storage.updateAcl(BlobId.of("bucket", "name"), acl);
    }

    public void tryListAcls3() {
        final List<Acl> result = storage.listAcls(BlobId.of("bucket", "name"));
    }

    public void tryCreateHmacKey() {
        final ServiceAccount serviceAccount = ServiceAccount.of("email");
        final Storage.CreateHmacKeyOption options = Storage.CreateHmacKeyOption.userProject("userProject");
        final HmacKey result = storage.createHmacKey(serviceAccount, options);
    }

    public void tryListHmacKeys1() {
        final Storage.ListHmacKeysOption options = Storage.ListHmacKeysOption.maxResults(0L);
        final Page<HmacKey.HmacKeyMetadata> result = storage.listHmacKeys(options);
    }

    public void tryGetHmacKey() {
        final Storage.GetHmacKeyOption options = Storage.GetHmacKeyOption.userProject("userProject");
        final HmacKey.HmacKeyMetadata result = storage.getHmacKey("accessId", options);
    }

    public void tryUpdateHmacKeyState() {
        final HmacKey.HmacKeyMetadata hmacKeyMetadata = HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"), "accessId", "projectId");
        final Storage.UpdateHmacKeyOption options = Storage.UpdateHmacKeyOption.userProject("userProject");
        final HmacKey.HmacKeyMetadata result = storage.updateHmacKeyState(hmacKeyMetadata, HmacKey.HmacKeyState.ACTIVE, options);
    }

    public void tryDeleteHmacKey() {
        final HmacKey.HmacKeyMetadata metadata = HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"), "accessId", "projectId");
        final Storage.DeleteHmacKeyOption options = Storage.DeleteHmacKeyOption.userProject("userProject");
        storage.deleteHmacKey(metadata, options);
    }

    public void tryGetIamPolicy() {
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final Policy result = storage.getIamPolicy("bucket", options);
    }

    public void trySetIamPolicy() {
        final Policy policy = null;
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final Policy result = storage.setIamPolicy("bucket", policy, options);
    }

    public void tryTestIamPermissions() {
        final Storage.BucketSourceOption options = Storage.BucketSourceOption.metagenerationMatch(0L);
        final List<Boolean> result = storage.testIamPermissions("bucket", Arrays.asList("value"), options);
    }

    public void tryLockRetentionPolicy() {
        final BucketInfo bucketInfo = BucketInfo.newBuilder("bucketName").build();
        final Storage.BucketTargetOption options = Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ);
        final Bucket result = storage.lockRetentionPolicy(bucketInfo, options);
    }

    public void tryGetServiceAccount() {
        final ServiceAccount result = storage.getServiceAccount("projectId");
    }
}
