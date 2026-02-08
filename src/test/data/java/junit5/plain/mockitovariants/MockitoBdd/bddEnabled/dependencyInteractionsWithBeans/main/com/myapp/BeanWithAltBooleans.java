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

public class BeanWithAltBooleans {

    // Test case where field name has no is;
    private boolean alpha;
    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

    // Test case where field name and method name have "is".
    private boolean isBravo;
    public boolean isBravo() {
        return isBravo;
    }
    public void setBravo(boolean bravo) {
        isBravo = bravo;
    }

    // Test case where field has is, but method does not.
    private boolean isCharlie;
    public boolean charlie() {
        return isCharlie;
    }
    public void charlie(boolean charlie) {
        isCharlie = charlie;
    }

    // Test case where neither field nor method have is.
    public boolean delta;
    public boolean delta() {
        return delta;
    }
    public void delta(final boolean delta) {
        this.delta = delta;
    }

    // Test case where method is get* and field does not start with is.
    private boolean echo;
    public boolean getEcho() {
        return echo;
    }
    public void setEcho(boolean echo) {
        this.echo = echo;
    }

    // Test case where method is get* and field does start with is.
    private boolean isFoxtrot;
    public boolean getFoxtrot() {
        return isFoxtrot;
    }
    public void setFoxtrot(final boolean foxtrot) {
        isFoxtrot = foxtrot;
    }

    // Test case where method is getIs* and field does not start with is*.
    private boolean golf;
    public boolean getIsGolf() {
        return golf;
    }
    public void setIsGolf(final boolean golf) {
        this.golf = golf;
    }

    // Test case with boxed boolean type.
    private Boolean hotel;
    public Boolean getIsHotel() {
        return hotel;
    }
    public void setIsHotel(final Boolean hotel) {
        this.hotel = hotel;
    }

}
