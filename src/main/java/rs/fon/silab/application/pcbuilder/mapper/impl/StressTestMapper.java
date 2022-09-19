/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.StressTestDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.StressTestEntity;

/**
 *
 * @author LightPower
 */
@Component
public class StressTestMapper implements EntityDtoMapper<StressTestDto, StressTestEntity> {

    @Override
    public StressTestDto toDto(StressTestEntity entity) {
        return new StressTestDto(entity.getStressTestID(), entity.getNazivTesta(), entity.getOpisTesta());
    }

    @Override
    public StressTestEntity toEntity(StressTestDto dto) {
        if (dto == null) {
            return null;
        }
        return new StressTestEntity(dto.getTestId(), dto.getTestName(), dto.getTestDescription());
    }

}
