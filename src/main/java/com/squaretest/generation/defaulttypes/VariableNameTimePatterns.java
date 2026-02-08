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
package com.squaretest.generation.defaulttypes;

import java.util.regex.Pattern;

public class VariableNameTimePatterns {
    // DayPattern regex.
    // Positive matches:
    // day
    // dayOfWeek
    // day_to_use
    // dayToUse
    // Day
    // startDay
    // theDayToUse
    // theDay
    // Negative matches:
    // days
    // today
    // birthday
    // daysUntil
    // days_until
    // theDays
    // startDays
    // theDays
    // numberOfDecreasesToday
    // expirationInDays
    static final Pattern DayPattern = Pattern.compile("((^day)($|[A-Z0-9_]).*)|((.*Day)($|[A-Z0-9_]).*)(?<!s)$");
    static final Pattern WeekPattern = Pattern.compile("((^week)($|[A-Z0-9_]).*)|((.*Week)($|[A-Z0-9_]).*)(?<!s)$");
    static final Pattern MonthPattern = Pattern.compile("((^month)($|[A-Z0-9_]).*)|((.*Month)($|[A-Z0-9_]).*)(?<!s)$");
    static final Pattern YearPattern = Pattern.compile("((^year)($|[A-Z0-9_]).*)|((.*Year)($|[A-Z0-9_]).*)(?<!s)$");

    private VariableNameTimePatterns() {
    }
}
