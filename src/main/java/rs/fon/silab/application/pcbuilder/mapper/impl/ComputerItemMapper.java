/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntity;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntityPK;

/**
 *
 * @author LightPower
 */
@Component
public class ComputerItemMapper implements EntityDtoMapper<ComputerItemDto, ComputerItemEntity> {

    private final ComponentMapper componentMapper;
    private final UserMapper userMapper;

    @Autowired
    public ComputerItemMapper(ComponentMapper componentMapper, UserMapper userMapper) {
        this.componentMapper = componentMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ComputerItemDto toDto(ComputerItemEntity entity) {
        return new ComputerItemDto(entity.getComputerItemEntityPK().getRacunarID(),
                entity.getComputerItemEntityPK().getRb(),
                componentMapper.toDto(entity.getComponentEntity()),
                entity.getCenaKomada(),
                entity.getKolicina(),
                entity.getUkupnaCena(),
                entity.getDatumKreiranja(),
                userMapper.toDto(entity.getCreator()));
    }

    @Override
    public ComputerItemEntity toEntity(ComputerItemDto dto) {
        if (dto == null) {
            return null;
        }
        ComputerItemEntity itemEntity = new ComputerItemEntity(new ComputerItemEntityPK(dto.getComputerId(),dto.getSerialNumber()),
                dto.getItemPrice(), dto.getAmount(), dto.getTotalItemPrice(), dto.getCreatedDate());
        itemEntity.setComponentEntity(componentMapper.toEntity(dto.getComponent()));
        itemEntity.setCreator(userMapper.toEntity(dto.getCreatedBy()));
        return itemEntity;
    }

}
