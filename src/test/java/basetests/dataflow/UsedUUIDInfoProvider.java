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

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsedUUIDInfoProvider {

    private static final Pattern UUIDPattern = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

    public List<List<FileLocation>> getUUIDsWithSameValues(final String fileText) {
        final MultiValuedMap<String, FileLocation> uuidToLocationsMap = new HashSetValuedHashMap<>();
        final String[] lines = fileText.split("\n");
        for(int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
            final String currentLine = lines[lineNumber];
            final Matcher matcher = UUIDPattern.matcher(currentLine);
            while(matcher.find()) {
                final int col = matcher.start();
                final String uuid = matcher.group();
                uuidToLocationsMap.put(uuid, new FileLocation(lineNumber, col, currentLine));
            }
        }

        final List<List<FileLocation>> ret = new ArrayList<>();
        for(final String key : uuidToLocationsMap.keySet()) {
            final List<FileLocation> fileLocations = new ArrayList<>(uuidToLocationsMap.get(key));
            fileLocations.sort(null);
            ret.add(fileLocations);
        }

        ret.sort(Comparator.comparing(o -> o.get(0)));
        return ret;
    }
}
