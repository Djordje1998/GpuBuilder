/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.dto.impl.UserProfileDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.UserProfileEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class UserProfileService implements ServiceOperations<UserProfileDto> {

    private final Dao<UserProfileEntity> repository;
    private final EntityDtoMapper<UserProfileDto, UserProfileEntity> mapper;

    @Autowired
    public UserProfileService(@Qualifier(value = "userProfileDao") Dao<UserProfileEntity> repository,
            @Qualifier(value = "userProfileMapper") EntityDtoMapper<UserProfileDto, UserProfileEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserProfileDto save(UserProfileDto dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserProfileDto> getAll() throws Exception {
        return repository.getAll().stream().map(entity -> {
            return mapper.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(UserProfileDto dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserProfileDto update(UserProfileDto dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserProfileDto find(UserProfileDto dto) throws Exception {
        return mapper.toDto(repository.find(mapper.toEntity(dto)));
    }

    @Override
    public List<UserProfileDto> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
