/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dto.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public class ComputerDto implements Dto {

    private Long computerId;
    private String computerName;
    private String usage;
    private Date warranty;
    private BigDecimal totalPrice;
    private List<ComputerItemDto> items;

    public ComputerDto() {
    }

    public ComputerDto(Long computerId, String computerName, String usage, Date warranty, BigDecimal totalPrice, List<ComputerItemDto> items) {
        this.computerId = computerId;
        this.computerName = computerName;
        this.usage = usage;
        this.warranty = warranty;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getComputerId() {
        return computerId;
    }

    public void setComputerId(Long computerId) {
        this.computerId = computerId;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Date getWarranty() {
        return warranty;
    }

    public void setWarranty(Date warranty) {
        this.warranty = warranty;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.computerId);
        hash = 67 * hash + Objects.hashCode(this.computerName);
        hash = 67 * hash + Objects.hashCode(this.usage);
        hash = 67 * hash + Objects.hashCode(this.warranty);
        hash = 67 * hash + Objects.hashCode(this.totalPrice);
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
        final ComputerDto other = (ComputerDto) obj;
        if (!Objects.equals(this.computerName, other.computerName)) {
            return false;
        }
        if (!Objects.equals(this.usage, other.usage)) {
            return false;
        }
        if (!Objects.equals(this.computerId, other.computerId)) {
            return false;
        }
        if (!Objects.equals(this.warranty, other.warranty)) {
            return false;
        }
        if (!Objects.equals(this.totalPrice, other.totalPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComputerDto{" + "computerId=" + computerId + ", computerName=" + computerName + ", usage=" + usage + ", warranty=" + warranty + ", totalPrice=" + totalPrice + '}';
    }

    public List<ComputerItemDto> getItems() {
        return items;
    }

    public void setItems(List<ComputerItemDto> items) {
        this.items = items;
    }

}
