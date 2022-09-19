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
public class ComponentDto implements Dto {

    private Long componentId;
    private String componentName;
    private ComponentTypeDto componentType;
    private BigDecimal frequency;
    private String description;
    private BigDecimal price;

    public ComponentDto() {
    }

    public ComponentDto(Long componentId, String componentName, ComponentTypeDto componentType, BigDecimal frequency, String description, BigDecimal price) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.componentType = componentType;
        this.frequency = frequency;
        this.description = description;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public ComponentTypeDto getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentTypeDto componentType) {
        this.componentType = componentType;
    }

    public BigDecimal getFrequency() {
        return frequency;
    }

    public void setFrequency(BigDecimal frequency) {
        this.frequency = frequency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.componentId);
        hash = 53 * hash + Objects.hashCode(this.componentName);
        hash = 53 * hash + Objects.hashCode(this.componentType);
        hash = 53 * hash + Objects.hashCode(this.frequency);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.price);
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
        final ComponentDto other = (ComponentDto) obj;
        if (!Objects.equals(this.componentName, other.componentName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.componentId, other.componentId)) {
            return false;
        }
        if (!Objects.equals(this.componentType, other.componentType)) {
            return false;
        }
        if (!Objects.equals(this.frequency, other.frequency)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ComponentDto{" + "componentId=" + componentId + ", componentName=" + componentName + ", componentType=" + componentType + ", frequency=" + frequency + ", description=" + description + ", price=" + price + '}';
    }

}
