/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerEntity;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class ComputerService implements ServiceOperations<ComputerDto> {

    private final Dao<ComputerEntity> repositoryComputer;
    private final EntityDtoMapper<ComputerDto, ComputerEntity> mapperComputer;
    private final EntityDtoMapper<ComputerItemDto, ComputerItemEntity> mapperItem;

    @Autowired
    public ComputerService(@Qualifier(value = "computerDao") Dao<ComputerEntity> repositoryComputer,
            @Qualifier(value = "computerMapper") EntityDtoMapper<ComputerDto, ComputerEntity> mapperComputer,
            @Qualifier(value = "computerItemDao") Dao<ComputerItemEntity> repositoryItem,
            @Qualifier(value = "computerItemMapper") EntityDtoMapper<ComputerItemDto, ComputerItemEntity> mapperItem) {
        this.repositoryComputer = repositoryComputer;
        this.mapperComputer = mapperComputer;
        this.mapperItem = mapperItem;
    }

    @Override
    public ComputerDto save(ComputerDto computerDto) throws Exception {
        return mapperComputer.toDto(repositoryComputer.save(mapperComputer.toEntity(computerDto)));
    }

    @Override
    public List<ComputerDto> getAll() throws Exception {
        List<ComputerEntity> computers = repositoryComputer.getAll();
        return computers.stream().map(computer -> {
            return mapperComputer.toDto(computer);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(ComputerDto dto) throws Exception {
        ComputerEntity foundEntity = repositoryComputer.find(mapperComputer.toEntity(dto));
        if (foundEntity != null) {
            repositoryComputer.delete(foundEntity);
        }
    }

    @Override
    public ComputerDto update(ComputerDto dto) throws Exception {
        return mapperComputer.toDto(repositoryComputer.update(mapperComputer.toEntity(dto)));
    }

    @Override
    public ComputerDto find(ComputerDto dto) throws Exception {
        ComputerDto found = mapperComputer.toDto(repositoryComputer.find(mapperComputer.toEntity(dto)));
        found.setItems(found.getItems().stream()
                .sorted(Comparator.comparingLong(ComputerItemDto::getSerialNumber)).collect(Collectors.toList()));
        return found;
    }

    @Override
    public List<ComputerDto> search(String find) throws Exception {
        List<ComputerEntity> computers = repositoryComputer.search(find);
        return computers.stream().map(computer -> {
            return mapperComputer.toDto(computer);
        }).collect(Collectors.toList());
    }
}
