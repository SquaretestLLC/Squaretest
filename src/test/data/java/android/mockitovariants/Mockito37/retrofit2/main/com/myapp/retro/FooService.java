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
package com.myapp.retro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
import java.util.Optional;

public interface FooService {
    @GET("foos/prefix/{prefix_arg}")
    Call<List<Foo>> listFoos(@Path("prefix") String prefix);

    @GET("foos/byid/{foo_id}")
    Call<Foo> getFooWithId(@Path("fooId") String fooId);

    @GET("foos/byid/{foo_id}")
    Call<Optional<Foo>> getOptionalFooWithId(@Path("fooId") String fooId);

}