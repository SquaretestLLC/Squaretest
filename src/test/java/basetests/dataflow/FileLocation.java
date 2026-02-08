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
package basetests.dataflow;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record FileLocation(int line, int col, String lineText) implements Comparable<FileLocation> {

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final FileLocation that = (FileLocation) o;
        return line == that.line && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, col);
    }

    @Override
    public int compareTo(@NotNull final FileLocation o) {
        if(this.line < o.line) {
            return -1;
        } else if(this.line == o.line) {
            return Integer.compare(this.col, o.col);
        } else {
            return 1;
        }
    }
}
