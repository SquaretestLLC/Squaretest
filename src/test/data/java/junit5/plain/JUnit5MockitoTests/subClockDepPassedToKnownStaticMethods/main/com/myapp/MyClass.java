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

import java.time.*;

public class MyClass {
    private final SubClock clock;

    public MyClass(SubClock clock) {
        this.clock = clock;
    }

    public Instant getInstant() {
        return Instant.now(clock);
    }

    public LocalDate getDate() {
        return LocalDate.now(clock);
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.now(clock);
    }

    public LocalTime getLocalTime() {
        return LocalTime.now(clock);
    }

    public OffsetDateTime getOffsetDateTime() {
        return OffsetDateTime.now(clock);
    }

    public OffsetTime getOffsetTime() {
        return OffsetTime.now(clock);
    }

    public ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.now(clock);
    }
}
