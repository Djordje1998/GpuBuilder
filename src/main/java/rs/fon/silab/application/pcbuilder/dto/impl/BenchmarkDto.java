/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dto.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public class BenchmarkDto implements Dto {

    private Long benchmarkId;
    private Date createdDate;
    private BigDecimal scoreFirstPc;
    private BigDecimal scoreSecondPc;
    private ComputerDto firstPc;
    private ComputerDto secondPc;
    private StressTestDto stressTest;

    public BenchmarkDto() {
    }

    public BenchmarkDto(Long benchmarkId, Date createdDate, BigDecimal scoreFirstPc, BigDecimal scoreSecondPc, ComputerDto firstPc, ComputerDto secondPc, StressTestDto stressTest) {
        this.benchmarkId = benchmarkId;
        this.createdDate = createdDate;
        this.scoreFirstPc = scoreFirstPc;
        this.scoreSecondPc = scoreSecondPc;
        this.firstPc = firstPc;
        this.secondPc = secondPc;
        this.stressTest = stressTest;
    }

    public StressTestDto getStressTest() {
        return stressTest;
    }

    public void setStressTest(StressTestDto stressTest) {
        this.stressTest = stressTest;
    }

    public Long getBenchmarkId() {
        return benchmarkId;
    }

    public void setBenchmarkId(Long benchmarkId) {
        this.benchmarkId = benchmarkId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getScoreFirstPc() {
        return scoreFirstPc;
    }

    public void setScoreFirstPc(BigDecimal scoreFirstPc) {
        this.scoreFirstPc = scoreFirstPc;
    }

    public BigDecimal getScoreSecondPc() {
        return scoreSecondPc;
    }

    public void setScoreSecondPc(BigDecimal scoreSecondPc) {
        this.scoreSecondPc = scoreSecondPc;
    }

    public ComputerDto getFirstPc() {
        return firstPc;
    }

    public void setFirstPc(ComputerDto firstPc) {
        this.firstPc = firstPc;
    }

    public ComputerDto getSecondPc() {
        return secondPc;
    }

    public void setSecondPc(ComputerDto secondPc) {
        this.secondPc = secondPc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.benchmarkId);
        hash = 23 * hash + Objects.hashCode(this.createdDate);
        hash = 23 * hash + Objects.hashCode(this.scoreFirstPc);
        hash = 23 * hash + Objects.hashCode(this.scoreSecondPc);
        hash = 23 * hash + Objects.hashCode(this.firstPc);
        hash = 23 * hash + Objects.hashCode(this.secondPc);
        hash = 23 * hash + Objects.hashCode(this.stressTest);
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
        final BenchmarkDto other = (BenchmarkDto) obj;
        if (!Objects.equals(this.benchmarkId, other.benchmarkId)) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.scoreFirstPc, other.scoreFirstPc)) {
            return false;
        }
        if (!Objects.equals(this.scoreSecondPc, other.scoreSecondPc)) {
            return false;
        }
        if (!Objects.equals(this.firstPc, other.firstPc)) {
            return false;
        }
        if (!Objects.equals(this.secondPc, other.secondPc)) {
            return false;
        }
        if (!Objects.equals(this.stressTest, other.stressTest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BenchmarkDto{" + "benchmarkId=" + benchmarkId + ", createdDate=" + createdDate + ", scoreFirstPc=" + scoreFirstPc + ", scoreSecondPc=" + scoreSecondPc + ", firstPc=" + firstPc + ", secondPc=" + secondPc + ", stressTest=" + stressTest + '}';
    }

}
