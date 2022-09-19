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
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class ComputerItemService implements ServiceOperations<ComputerItemDto> {

    private final Dao<ComputerItemEntity> repository;
    private final EntityDtoMapper<ComputerItemDto, ComputerItemEntity> mapper;

    @Autowired
    public ComputerItemService(@Qualifier(value = "computerItemDao") Dao<ComputerItemEntity> repository,
            @Qualifier(value = "computerItemMapper") EntityDtoMapper<ComputerItemDto, ComputerItemEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ComputerItemDto save(ComputerItemDto computerItemDto) throws Exception {
        return mapper.toDto(repository.save(mapper.toEntity(computerItemDto)));
    }

    @Override
    public List<ComputerItemDto> getAll() throws Exception {
        List<ComputerItemEntity> items = repository.getAll();
        return items.stream().map(item -> {
            return mapper.toDto(item);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(ComputerItemDto dto) throws Exception {
//        repository.delete(mapper.toEntity(dto));
        ComputerItemEntity foundEntity = repository.find(mapper.toEntity(dto));
        if (foundEntity != null) {
            repository.delete(foundEntity);
        }
    }

    @Override
    public ComputerItemDto update(ComputerItemDto dto) throws Exception {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }

    @Override
    public ComputerItemDto find(ComputerItemDto dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComputerItemDto> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
