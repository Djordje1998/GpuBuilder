/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerEntity;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntity;

/**
 *
 * @author LightPower
 */
@Component
public class ComputerMapper implements EntityDtoMapper<ComputerDto, ComputerEntity> {

    private final ComputerItemMapper computerItemMapper;

    @Autowired
    public ComputerMapper(ComputerItemMapper computerItemMapper) {
        this.computerItemMapper = computerItemMapper;
    }

    @Override
    public ComputerDto toDto(ComputerEntity entity) {
        List<ComputerItemEntity> itemsEntity = entity.getComputerItemEntityList();
        List<ComputerItemDto> itemsDto = null;
        if (itemsEntity != null) {
            itemsDto = itemsEntity.stream().map(itemEntity -> {
                return computerItemMapper.toDto(itemEntity);
            }).collect(Collectors.toList());
        }

        return new ComputerDto(entity.getRacunarID(), entity.getNazivRacunara(), entity.getNamena(),
                entity.getGarancija(), entity.getUkupnaCena(), itemsDto);
    }

    @Override
    public ComputerEntity toEntity(ComputerDto dto) {
        if (dto == null) {
            return null;
        }
        ComputerEntity computerEntity = new ComputerEntity(dto.getComputerId(), dto.getComputerName(),
                dto.getUsage(), dto.getWarranty(), dto.getTotalPrice());
        List<ComputerItemEntity> itemEntityList = null;
        if (dto.getItems() != null) {
            itemEntityList = dto.getItems().stream().map(itemDto -> {
                ComputerItemEntity itemEntity = computerItemMapper.toEntity(itemDto);
                itemEntity.setComputerEntity(computerEntity);
                return itemEntity;
            }).collect(Collectors.toList());
        }
        computerEntity.setComputerItemEntityList(itemEntityList);
        return computerEntity;
    }

}
