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
public class ComputerItemDto implements Dto {

    private Long computerId;
    private Long serialNumber;
    private ComponentDto component;
    private BigDecimal itemPrice;
    private int amount;
    private BigDecimal totalItemPrice;
    private Date createdDate;
    private UserDto createdBy;

    public ComputerItemDto() {
    }

    public ComputerItemDto(Long computerId, Long serialNumber, ComponentDto component, BigDecimal itemPrice, int amount, BigDecimal totalItemPrice, Date createdDate, UserDto createdBy) {
        this.computerId = computerId;
        this.serialNumber = serialNumber;
        this.component = component;
        this.itemPrice = itemPrice;
        this.amount = amount;
        this.totalItemPrice = totalItemPrice;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public Long getComputerId() {
        return computerId;
    }

    public void setComputerId(Long computerId) {
        this.computerId = computerId;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ComponentDto getComponent() {
        return component;
    }

    public void setComponent(ComponentDto component) {
        this.component = component;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(BigDecimal totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.computerId);
        hash = 67 * hash + Objects.hashCode(this.serialNumber);
        hash = 67 * hash + Objects.hashCode(this.component);
        hash = 67 * hash + Objects.hashCode(this.itemPrice);
        hash = 67 * hash + this.amount;
        hash = 67 * hash + Objects.hashCode(this.totalItemPrice);
        hash = 67 * hash + Objects.hashCode(this.createdDate);
        hash = 67 * hash + Objects.hashCode(this.createdBy);
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
        final ComputerItemDto other = (ComputerItemDto) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.computerId, other.computerId)) {
            return false;
        }
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        if (!Objects.equals(this.component, other.component)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        if (!Objects.equals(this.totalItemPrice, other.totalItemPrice)) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComputerItemDto{" + "computerId=" + computerId + ", serialNumber=" + serialNumber + ", component=" + component + ", itemPrice=" + itemPrice + ", amount=" + amount + ", totalItemPrice=" + totalItemPrice + ", createdDate=" + createdDate + ", createdBy=" + createdBy + '}';
    }

}
