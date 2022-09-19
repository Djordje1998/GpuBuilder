/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.dto.impl.GradeDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.GradeEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class GradeService implements ServiceOperations<GradeDto> {

    private final Dao<GradeEntity> repository;
    private final EntityDtoMapper<GradeDto, GradeEntity> mapper;

    @Autowired
    public GradeService(@Qualifier(value = "gradeDao") Dao<GradeEntity> repository,
            @Qualifier(value = "gradeMapper") EntityDtoMapper<GradeDto, GradeEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GradeDto save(GradeDto dto) throws Exception {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public List<GradeDto> getAll() throws Exception {
        List<GradeEntity> entityList = repository.getAll();
        return entityList.stream().map(entity -> {
            return mapper.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(GradeDto dto) throws Exception {
        GradeEntity find = repository.find(mapper.toEntity(dto));
        if (find != null) {
            repository.delete(find);
        }
    }

    @Override
    public GradeDto update(GradeDto dto) throws Exception {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }

    @Override
    public GradeDto find(GradeDto dto) throws Exception {
        return mapper.toDto(repository.find(mapper.toEntity(dto)));
    }

    @Override
    public List<GradeDto> search(String find) throws Exception {
        List<GradeEntity> entityList = repository.search(find);
        return entityList.stream().map(entity -> {
            return mapper.toDto(entity);
        }).collect(Collectors.toList());
    }

}
