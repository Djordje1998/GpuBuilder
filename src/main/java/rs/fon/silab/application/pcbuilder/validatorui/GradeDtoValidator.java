/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.validatorui;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rs.fon.silab.application.pcbuilder.dto.impl.GradeDto;

/**
 *
 * @author LightPower
 */
public class GradeDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GradeDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GradeDto dto = (GradeDto) target;

        if (dto.getComponent() == null) {
            errors.rejectValue("component", "GradeDto.component.null", "Component can not be null!");
        }

        if (dto.getStressTest() == null) {
            errors.rejectValue("stressTest", "GradeDto.stressTest.null", "Stress Test can not be null!");
        } else if(dto.getStressTest().getTestId()==null){
            errors.rejectValue("stressTest.testId", "GradeDto.stressTest.testId.null", "Stress Test ID can not be null!");
        }

    }

}
