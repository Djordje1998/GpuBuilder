/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.GradeDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.GradeEntity;
import rs.fon.silab.application.pcbuilder.model.impl.GradeEntityPK;

/**
 *
 * @author LightPower
 */
@Component
public class GradeMapper implements EntityDtoMapper<GradeDto, GradeEntity> {

    private final ComponentMapper componentMapper;
    private final StressTestMapper stressTestMapper;

    @Autowired
    public GradeMapper(ComponentMapper componentMapper, StressTestMapper stressTestMapper) {
        this.componentMapper = componentMapper;
        this.stressTestMapper = stressTestMapper;
    }

    @Override
    public GradeDto toDto(GradeEntity entity) {
        return new GradeDto(componentMapper.toDto(entity.getComponentEntity()),
                stressTestMapper.toDto(entity.getStressTestEntity()), entity.getVrednostOcene());
    }

    @Override
    public GradeEntity toEntity(GradeDto dto) {
        if (dto == null) {
            return null;
        }
        GradeEntity entity = new GradeEntity(new GradeEntityPK(dto.getComponent().getComponentId(), dto.getStressTest().getTestId()),
                dto.getGradeValue());
        entity.setStressTestEntity(stressTestMapper.toEntity(dto.getStressTest()));
        entity.setComponentEntity(componentMapper.toEntity(dto.getComponent()));
        return entity;
    }

}
