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

import android.content.Context;
import android.content.Intent;
import android.app.ListActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class MyClass extends ListActivity {

    private static final String INTENT_EXTRA_NAME = "NAME_EXTRA";

    @Inject
    ExecutorService mExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent createLaunchIntent(final Context context, final String name) {
        final Intent launchIntent = new Intent(context, MainActivity.class);
        launchIntent.putExtra(INTENT_EXTRA_NAME, name);
        return launchIntent;
    }
}
