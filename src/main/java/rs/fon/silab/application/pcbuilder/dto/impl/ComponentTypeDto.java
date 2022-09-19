/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dto.impl;

import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public class ComponentTypeDto implements Dto {

    private Long typeId;
    private String typeName;

    public ComponentTypeDto() {
    }

    public ComponentTypeDto(Long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return typeName;
    }

}
