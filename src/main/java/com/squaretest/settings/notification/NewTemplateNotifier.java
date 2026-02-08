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
package com.squaretest.settings.notification;

import com.squaretest.settings.store.Template;
import com.squaretest.settings.store.TemplateLanguage;

import java.util.LinkedList;
import java.util.List;

/**
 * Tracks and notifies listeners whenever a new template is created.
 * This allows {@link com.squaretest.settings.TemplateConfigComponent TemplateConfigComponents} to add the new template
 * to their dropdown lists.
 */
public class NewTemplateNotifier {

    private final List<NewTemplateListener> listeners;

    public NewTemplateNotifier() {
        listeners = new LinkedList<>();
    }

    public void addListener(final NewTemplateListener newTemplateListener) {
        listeners.add(newTemplateListener);
    }

    public void notifyNewTemplateAdded(final TemplateLanguage templateLanguage, final Template template) {
        for(final NewTemplateListener listener : listeners) {
            listener.onNewTemplateCreated(templateLanguage, template);
        }
    }
}
