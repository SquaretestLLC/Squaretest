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


import java.util.List;

public class AutoScalingSettingsDescriptionDTO {
    private Long theMinimumUnits;
    private Long theMaximumUnits;
    private Boolean theAutoScalingDisabled;
    private String theAutoScalingRoleArn;
    private List<AutoScalingPolicyDescriptionDTO> theScalingPolicies;

    public Long getTheMinimumUnits() {
        return theMinimumUnits;
    }

    public void setTheMinimumUnits(final Long theMinimumUnits) {
        this.theMinimumUnits = theMinimumUnits;
    }

    public Long getTheMaximumUnits() {
        return theMaximumUnits;
    }

    public void setTheMaximumUnits(final Long theMaximumUnits) {
        this.theMaximumUnits = theMaximumUnits;
    }

    public Boolean getTheAutoScalingDisabled() {
        return theAutoScalingDisabled;
    }

    public void setTheAutoScalingDisabled(final Boolean theAutoScalingDisabled) {
        this.theAutoScalingDisabled = theAutoScalingDisabled;
    }

    public String getTheAutoScalingRoleArn() {
        return theAutoScalingRoleArn;
    }

    public void setTheAutoScalingRoleArn(final String theAutoScalingRoleArn) {
        this.theAutoScalingRoleArn = theAutoScalingRoleArn;
    }

    public List<AutoScalingPolicyDescriptionDTO> getTheScalingPolicies() {
        return theScalingPolicies;
    }

    public void setTheScalingPolicies(final List<AutoScalingPolicyDescriptionDTO> theScalingPolicies) {
        this.theScalingPolicies = theScalingPolicies;
    }
}
