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
package com.myapp.other;

import java.util.UUID;

public class Constants {
    public static final UUID ConstantFooId1 = UUID.fromString("d7802996-e4b9-41b8-a41d-6f0a5a3148cf");
    public static final UUID ConstantFooId2 = ConstantFooId1;

    public static final UUID ConstantCircular1 = ConstantCircular2;
    public static final UUID ConstantCircular2 = ConstantCircular1;

    public static final String UUIDStringCircular1 = UUIDStringCircular2;
    public static final String UUIDStringCircular2 = UUIDStringCircular1;
    public static final UUID UUIDThatUsesStringCircular2 = UUID.fromString(UUIDStringCircular2);
}
