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

public class Pair<T, K> {
    private final T left;
    private final Wrapper<K> right;
    private final String otherValue;

    public Pair(final T left) {
        this(left, null);
    }

    public  Pair(final T left, final Wrapper<K> right) {
        this(left, right, "default");
    }

    public Pair(final T left, final Wrapper<K> right, final String otherValue) {
        this.left = left;
        this.right = right;
        this.otherValue = otherValue;
    }

    public T getLeft() {
        return this.left;
    }

    public Wrapper<K> getRight() {
        return this.right;
    }

    public String getOtherValue() {
        return this.otherValue;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Pair)) return false;
        final Pair<?, ?> other = (Pair<?, ?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$left = this.getLeft();
        final Object other$left = other.getLeft();
        if (this$left == null ? other$left != null : !this$left.equals(other$left)) return false;
        final Object this$right = this.getRight();
        final Object other$right = other.getRight();
        if (this$right == null ? other$right != null : !this$right.equals(other$right)) return false;
        final Object this$otherValue = this.getOtherValue();
        final Object other$otherValue = other.getOtherValue();
        if (this$otherValue == null ? other$otherValue != null : !this$otherValue.equals(other$otherValue))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pair;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $left = this.getLeft();
        result = result * PRIME + ($left == null ? 43 : $left.hashCode());
        final Object $right = this.getRight();
        result = result * PRIME + ($right == null ? 43 : $right.hashCode());
        final Object $otherValue = this.getOtherValue();
        result = result * PRIME + ($otherValue == null ? 43 : $otherValue.hashCode());
        return result;
    }
}
