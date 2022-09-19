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
import rs.fon.silab.application.pcbuilder.dto.impl.StressTestDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.StressTestEntity;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Service
@Transactional
public class StressTestService implements ServiceOperations<StressTestDto> {

    private final Dao<StressTestEntity> repository;
    private final EntityDtoMapper<StressTestDto, StressTestEntity> mapper;

    @Autowired
    public StressTestService(@Qualifier(value = "stressTestDao") Dao<StressTestEntity> repository,
            @Qualifier(value = "stressTestMapper") EntityDtoMapper<StressTestDto, StressTestEntity> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StressTestDto save(StressTestDto dto) throws Exception {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public List<StressTestDto> getAll() throws Exception {
        List<StressTestEntity> entityList = repository.getAll();
        return entityList.stream().map(entity -> {
            return mapper.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(StressTestDto dto) throws Exception {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public StressTestDto update(StressTestDto dto) throws Exception {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }

    @Override
    public StressTestDto find(StressTestDto dto) throws Exception {
        return mapper.toDto(repository.find(mapper.toEntity(dto)));
    }

    @Override
    public List<StressTestDto> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
