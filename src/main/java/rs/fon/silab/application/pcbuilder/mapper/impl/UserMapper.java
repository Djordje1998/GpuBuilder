/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.UserDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.UserEntity;

/**
 *
 * @author LightPower
 */
@Component
public class UserMapper implements EntityDtoMapper<UserDto, UserEntity> {

    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserMapper(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getUsername(),
                entity.getUserPassword(), entity.getUserPassword(), entity.getEmail(),
                userProfileMapper.toDto(entity.getProfile()));
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        UserEntity entity = new UserEntity(dto.getUserId(), dto.getUsername(), dto.getPassword(),
                dto.getName(), dto.getSurname(), dto.getEmail());
        entity.setProfile(userProfileMapper.toEntity(dto.getProfile()));
        return entity;
    }

}
