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
import rs.fon.silab.application.pcbuilder.dto.impl.BenchmarkDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.BenchmarkEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class BenchmarkService implements ServiceOperations<BenchmarkDto> {

    private final Dao<BenchmarkEntity> repository;
    private final EntityDtoMapper<BenchmarkDto, BenchmarkEntity> mapper;

    @Autowired
    public BenchmarkService(@Qualifier(value = "benchmarkDao") Dao<BenchmarkEntity> repository,
            @Qualifier(value = "benchmarkMapper") EntityDtoMapper<BenchmarkDto, BenchmarkEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BenchmarkDto save(BenchmarkDto dto) throws Exception {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public List<BenchmarkDto> getAll() throws Exception {
        List<BenchmarkEntity> entityList = repository.getAll();
        return entityList.stream().map(entity -> {
            return mapper.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(BenchmarkDto dto) throws Exception {
        BenchmarkEntity find = repository.find(mapper.toEntity(dto));
        if (find != null) {
            repository.delete(find);
        }
    }

    @Override
    public BenchmarkDto update(BenchmarkDto dto) throws Exception {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }

    @Override
    public BenchmarkDto find(BenchmarkDto dto) throws Exception {
        BenchmarkEntity entity = new BenchmarkEntity();
        entity.setBenchmarkID(dto.getBenchmarkId());
        return mapper.toDto(repository.find(entity));
    }

    @Override
    public List<BenchmarkDto> search(String find) throws Exception {
        List<BenchmarkEntity> entityList = repository.search(find);
        return entityList.stream().map(entity -> {
            return mapper.toDto(entity);
        }).collect(Collectors.toList());
    }

}
