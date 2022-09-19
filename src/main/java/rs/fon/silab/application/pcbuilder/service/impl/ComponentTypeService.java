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
import rs.fon.silab.application.pcbuilder.dao.impl.ComponentTypeDao;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentTypeDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComponentTypeEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class ComponentTypeService implements ServiceOperations<ComponentTypeDto> {

    private final Dao<ComponentTypeEntity> repository;
    private final EntityDtoMapper<ComponentTypeDto, ComponentTypeEntity> mapper;

    @Autowired
    public ComponentTypeService(@Qualifier(value = "componentTypeDao") Dao<ComponentTypeEntity> repository,
            @Qualifier(value = "componentTypeMapper") EntityDtoMapper<ComponentTypeDto, ComponentTypeEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ComponentTypeDto save(ComponentTypeDto typeDto) throws Exception {
        return mapper.toDto(repository.save(mapper.toEntity(typeDto)));
    }

    @Override
    public List<ComponentTypeDto> getAll() throws Exception {
        List<ComponentTypeEntity> types = repository.getAll();
        return types.stream().map(type -> {
            return mapper.toDto(type);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(ComponentTypeDto dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComponentTypeDto update(ComponentTypeDto dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComponentTypeDto find(ComponentTypeDto dto) throws Exception {
        return mapper.toDto(repository.find(mapper.toEntity(dto)));
    }

    @Override
    public List<ComponentTypeDto> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
