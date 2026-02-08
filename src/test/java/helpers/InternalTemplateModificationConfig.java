/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Contains configuration information describing how to construct the template required to run a group of quick settings
 * tests.
 */
public record InternalTemplateModificationConfig(@NotNull String internalTemplateName,
                                                 @NotNull List<String> replacementLines) {
    @JsonCreator
    public InternalTemplateModificationConfig(
            @NotNull @JsonProperty("internalTemplateName") final String internalTemplateName,
            @NotNull @JsonProperty("replacementLines") final List<String> replacementLines) {
        this.internalTemplateName = internalTemplateName;
        this.replacementLines = replacementLines;
    }
}
