/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.UserProfileDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.UserProfileEntity;

/**
 *
 * @author LightPower
 */
@Component
public class UserProfileMapper implements EntityDtoMapper<UserProfileDto, UserProfileEntity>{

    @Override
    public UserProfileDto toDto(UserProfileEntity entity) {
        return new UserProfileDto(entity.getId(), entity.getProfileName());
    }

    @Override
    public UserProfileEntity toEntity(UserProfileDto dto) {
        if (dto == null) {
            return null;
        }
        return new UserProfileEntity(dto.getId(), dto.getProfileName());
    }
    
}
