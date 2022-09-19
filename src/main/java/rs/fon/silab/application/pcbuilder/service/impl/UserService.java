/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.dto.impl.UserDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.UserEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class UserService implements ServiceOperations<UserDto> {

    private final Dao<UserEntity> repository;
    private final EntityDtoMapper<UserDto, UserEntity> mapper;

    @Autowired
    public UserService(@Qualifier(value = "userDao") Dao<UserEntity> repository,
            @Qualifier(value = "userMapper") EntityDtoMapper<UserDto, UserEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto save(UserDto userDto) throws Exception {
        try {
            if (repository.find(mapper.toEntity(userDto)).getUsername().equals(userDto.getUsername())) {
                throw new Exception("Username already exist!");
            }
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return mapper.toDto(repository.save(mapper.toEntity(userDto)));
    }

    @Override
    public List<UserDto> getAll() throws Exception {
        List<UserEntity> users = repository.getAll();
        return users.stream().map(user -> {
            return mapper.toDto(user);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(UserDto dto) throws Exception {
        UserEntity find = repository.find(mapper.toEntity(dto));
        if (find != null) {
            repository.delete(find);
        }
    }

    @Override
    public UserDto update(UserDto dto) throws Exception {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }

    @Override
    public UserDto find(UserDto dto) throws Exception {
        UserEntity find = repository.find(mapper.toEntity(dto));
        return mapper.toDto(find);
    }

    @Override
    public List<UserDto> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
