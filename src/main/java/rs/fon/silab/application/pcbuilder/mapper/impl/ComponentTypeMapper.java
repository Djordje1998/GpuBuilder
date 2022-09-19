/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentTypeDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComponentTypeEntity;

/**
 *
 * @author LightPower
 */
@Component
public class ComponentTypeMapper implements EntityDtoMapper<ComponentTypeDto, ComponentTypeEntity> {

    @Override
    public ComponentTypeDto toDto(ComponentTypeEntity entity) {
        return new ComponentTypeDto(entity.getTipKomponenteID(), entity.getNazivTipa());
    }

    @Override
    public ComponentTypeEntity toEntity(ComponentTypeDto dto) {
        if (dto == null) {
            return null;
        }
        return new ComponentTypeEntity(dto.getTypeId(), dto.getTypeName());
    }

}
