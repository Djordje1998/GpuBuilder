/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.pcbuilder.dto.impl.BenchmarkDto;
import rs.fon.silab.application.pcbuilder.mapper.EntityDtoMapper;
import rs.fon.silab.application.pcbuilder.model.impl.BenchmarkEntity;

/**
 *
 * @author LightPower
 */
@Component
public class BenchmarkMapper implements EntityDtoMapper<BenchmarkDto, BenchmarkEntity> {
    
    private final ComputerMapper computerMapper;
    private final StressTestMapper stressTestMapper;
    
    @Autowired
    public BenchmarkMapper(ComputerMapper computerMapper, StressTestMapper stressTestMapper) {
        this.computerMapper = computerMapper;
        this.stressTestMapper = stressTestMapper;
    }
    
    @Override
    public BenchmarkDto toDto(BenchmarkEntity entity) {
        return new BenchmarkDto(entity.getBenchmarkID(), entity.getDatumBenchmarka(), entity.getBodoviPrvogRacunara(),
                entity.getBodoviDrugogRacunara(), computerMapper.toDto(entity.getRacunarID1()),
                computerMapper.toDto(entity.getRacunarID2()), stressTestMapper.toDto(entity.getStressTestID()));
    }
    
    @Override
    public BenchmarkEntity toEntity(BenchmarkDto dto) {
        if (dto == null) {
            return null;
        }
        BenchmarkEntity benchmarkEntity = new BenchmarkEntity(dto.getBenchmarkId(), dto.getCreatedDate(), dto.getScoreFirstPc(), dto.getScoreSecondPc());
        benchmarkEntity.setRacunarID1(computerMapper.toEntity(dto.getFirstPc()));
        benchmarkEntity.setRacunarID2(computerMapper.toEntity(dto.getSecondPc()));
        benchmarkEntity.setStressTestID(stressTestMapper.toEntity(dto.getStressTest()));
        return benchmarkEntity;
    }
    
}
