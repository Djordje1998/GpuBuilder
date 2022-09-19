/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dto.impl;

import java.util.Objects;
import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public class StressTestDto implements Dto {

    private Long testId;
    private String testName;
    private String testDescription;

    public StressTestDto() {
    }

    public StressTestDto(Long testId, String testName, String testDescription) {
        this.testId = testId;
        this.testName = testName;
        this.testDescription = testDescription;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.testId);
        hash = 29 * hash + Objects.hashCode(this.testName);
        hash = 29 * hash + Objects.hashCode(this.testDescription);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StressTestDto other = (StressTestDto) obj;
        if (!Objects.equals(this.testName, other.testName)) {
            return false;
        }
        if (!Objects.equals(this.testDescription, other.testDescription)) {
            return false;
        }
        if (!Objects.equals(this.testId, other.testId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StressTestDto{" + "testId=" + testId + ", testName=" + testName + ", testDescription=" + testDescription + '}';
    }

}
