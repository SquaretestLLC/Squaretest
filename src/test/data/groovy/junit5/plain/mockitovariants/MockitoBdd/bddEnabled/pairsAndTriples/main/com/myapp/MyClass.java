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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.net.Socket;

public class MyClass {

    public Pair<String, Integer> makePair() {
        return Pair.of("test", 1);
    }

    public ImmutablePair<String, Integer> makeImmutablePair() {
        return new ImmutablePair<>("test", 1);
    }

    public MutablePair<String, Integer> makeMutablePair() {
        return new MutablePair<>("test", 1);
    }

    public Triple<String, Integer, Socket> makeTripleWithoutOverridesEquals() {
        return Triple.of("test", 1, null);
    }

    public Triple<String, Integer, Double> makeTripleWithOverridesEquals() {
        return Triple.of("test", 1, 0.0);
    }
}
