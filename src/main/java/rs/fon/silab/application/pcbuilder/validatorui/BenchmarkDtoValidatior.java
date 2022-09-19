/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.validatorui;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rs.fon.silab.application.pcbuilder.dto.impl.BenchmarkDto;

/**
 *
 * @author LightPower
 */
public class BenchmarkDtoValidatior implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return BenchmarkDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BenchmarkDto dto = (BenchmarkDto) target;
        if(dto.getFirstPc()==null){
            errors.rejectValue("firstPc", "BenchmarkDto.firstPc.null", "First Pc can not be null!");
        } else if(dto.getFirstPc().getComputerId().equals(dto.getSecondPc().getComputerId())){
            errors.rejectValue("firstPc", "BenchmarkDto.firstPc.duplicate", "First Pc can not be same as Second Pc!");
        }
        
        if(dto.getSecondPc()==null){
            errors.rejectValue("secondPc", "BenchmarkDto.secondPc.null", "Second Pc can not be null!");
        }
        
        if(dto.getStressTest()==null){
            errors.rejectValue("stressTest", "BenchmarkDto.stressTest.null", "Stress Test can not be null!");
        }
    }
    
}
