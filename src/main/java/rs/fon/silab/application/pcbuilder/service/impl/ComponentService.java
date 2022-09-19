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
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComponentEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class ComponentService implements ServiceOperations<ComponentDto> {

    private final Dao<ComponentEntity> repository;
    private final EntityDtoMapper<ComponentDto, ComponentEntity> mapper;

    @Autowired
    public ComponentService(@Qualifier(value = "componentDao") Dao<ComponentEntity> component,
            @Qualifier(value = "componentMapper") EntityDtoMapper<ComponentDto, ComponentEntity> componentMapper) {
        this.repository = component;
        this.mapper = componentMapper;
    }

    @Override
    public ComponentDto save(ComponentDto componentDto) throws Exception {
        return mapper.toDto(repository.save(mapper.toEntity(componentDto)));
    }

    @Override
    public List<ComponentDto> getAll() throws Exception {
        List<ComponentEntity> components = repository.getAll();
        return components.stream().map(component -> {
            return mapper.toDto(component);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(ComponentDto dto) throws Exception {
        ComponentEntity foundEntity = repository.find(mapper.toEntity(dto));
        if (foundEntity != null) {
            repository.delete(foundEntity);
        }
    }

    @Override
    public ComponentDto update(ComponentDto dto) throws Exception {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }

    @Override
    public ComponentDto find(ComponentDto dto) throws Exception {
        return mapper.toDto(repository.find(mapper.toEntity(dto)));
    }

    @Override
    public List<ComponentDto> search(String find) throws Exception {
        List<ComponentEntity> components = null;
        if (find == null || find.isEmpty()) {
            components = repository.getAll();
        } else {
            components = repository.search(find);
        }
        return components.stream().map(component -> {
            return mapper.toDto(component);
        }).collect(Collectors.toList());
    }

}
