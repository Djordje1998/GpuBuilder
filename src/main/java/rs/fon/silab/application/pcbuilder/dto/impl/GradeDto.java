/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dto.impl;

import java.math.BigDecimal;
import java.util.Objects;
import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public class GradeDto implements Dto {

    private ComponentDto component;
    private StressTestDto stressTest;
    private BigDecimal gradeValue;

    public GradeDto() {
    }

    public GradeDto(ComponentDto component, StressTestDto stressTest, BigDecimal gradeValue) {
        this.component = component;
        this.stressTest = stressTest;
        this.gradeValue = gradeValue;
    }

    public static BigDecimal calculate(StressTestDto t, ComponentDto c) {
        int testScore = t.getTestName().length();
        int componentScore = c.getComponentName().length();
        int resultScore = testScore * componentScore;
        return new BigDecimal(resultScore).multiply(new BigDecimal(1.37));
    }

    public BigDecimal getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(BigDecimal gradeValue) {
        this.gradeValue = gradeValue;
    }

    public ComponentDto getComponent() {
        return component;
    }

    public void setComponent(ComponentDto component) {
        this.component = component;
    }

    public StressTestDto getStressTest() {
        return stressTest;
    }

    public void setStressTest(StressTestDto stressTest) {
        this.stressTest = stressTest;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.component);
        hash = 71 * hash + Objects.hashCode(this.stressTest);
        hash = 71 * hash + Objects.hashCode(this.gradeValue);
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
        final GradeDto other = (GradeDto) obj;
        if (!Objects.equals(this.component, other.component)) {
            return false;
        }
        if (!Objects.equals(this.stressTest, other.stressTest)) {
            return false;
        }
        if (!Objects.equals(this.gradeValue, other.gradeValue)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GradeDto{" + "component=" + component + ", stressTest=" + stressTest + ", gradeValue=" + gradeValue + '}';
    }

}
