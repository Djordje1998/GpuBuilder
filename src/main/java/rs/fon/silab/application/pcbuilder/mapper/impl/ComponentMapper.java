/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentTypeDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComponentEntity;

/**
 *
 * @author LightPower
 */
@Component
public class ComponentMapper implements EntityDtoMapper<ComponentDto, ComponentEntity> {

    private final ComponentTypeMapper componentTypeMapper;

    @Autowired
    public ComponentMapper(ComponentTypeMapper componentTypeMapper) {
        this.componentTypeMapper = componentTypeMapper;
    }

    @Override
    public ComponentDto toDto(ComponentEntity entity) {
        ComponentTypeDto componentTypeDto = componentTypeMapper.toDto(entity.getComponentTypeEntity());
        return new ComponentDto(entity.getKomponentaID(), entity.getNazivKomponente(), componentTypeDto,
                entity.getTakt(), entity.getOpis(), entity.getCena());
    }

    @Override
    public ComponentEntity toEntity(ComponentDto dto) {
        if (dto == null) {
            return null;
        }
        return new ComponentEntity(dto.getComponentId(), dto.getComponentName(), dto.getFrequency(),
                dto.getDescription(), dto.getPrice(), componentTypeMapper.toEntity(dto.getComponentType()));
    }

}
