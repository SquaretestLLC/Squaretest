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
package com.myapp.foos;

import com.google.common.base.Objects;

import javax.annotation.Nullable;

public final class BooleanBuilder implements Predicate, Cloneable {
    private static final long serialVersionUID = -4129485177345542519L;
    @Nullable
    private Predicate predicate;

    public BooleanBuilder() {
    }

    public BooleanBuilder(Predicate initial) {
        this.predicate = (Predicate)ExpressionUtils.extract(initial);
    }

    public <R, C> R accept(Visitor<R, C> v, C context) {
        return this.predicate != null ? this.predicate.accept(v, context) : null;
    }

    public BooleanBuilder and(@Nullable Predicate right) {
        if (right != null) {
            if (this.predicate == null) {
                this.predicate = right;
            } else {
                this.predicate = ExpressionUtils.and(this.predicate, right);
            }
        }

        return this;
    }

    public BooleanBuilder andAnyOf(Predicate... args) {
        if (args.length > 0) {
            this.and(ExpressionUtils.anyOf(args));
        }

        return this;
    }

    public BooleanBuilder andNot(Predicate right) {
        return this.and(right.not());
    }

    public BooleanBuilder clone() throws CloneNotSupportedException {
        return (BooleanBuilder)super.clone();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else {
            return o instanceof BooleanBuilder ? Objects.equal(((BooleanBuilder)o).getValue(), this.predicate) : false;
        }
    }

    @Nullable
    public Predicate getValue() {
        return this.predicate;
    }

    public int hashCode() {
        return this.predicate != null ? this.predicate.hashCode() : 0;
    }

    public boolean hasValue() {
        return this.predicate != null;
    }

    public BooleanBuilder not() {
        if (this.predicate != null) {
            this.predicate = this.predicate.not();
        }

        return this;
    }

    public BooleanBuilder or(@Nullable Predicate right) {
        if (right != null) {
            if (this.predicate == null) {
                this.predicate = right;
            } else {
                this.predicate = ExpressionUtils.or(this.predicate, right);
            }
        }

        return this;
    }

    public BooleanBuilder orAllOf(Predicate... args) {
        if (args.length > 0) {
            this.or(ExpressionUtils.allOf(args));
        }

        return this;
    }

    public BooleanBuilder orNot(Predicate right) {
        return this.or(right.not());
    }

    public Class<? extends Boolean> getType() {
        return Boolean.class;
    }

    public String toString() {
        return this.predicate != null ? this.predicate.toString() : super.toString();
    }
}

