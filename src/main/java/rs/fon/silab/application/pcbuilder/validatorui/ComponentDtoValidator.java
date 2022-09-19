/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.validatorui;

import java.math.BigDecimal;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentDto;

/**
 *
 * @author LightPower
 */
public class ComponentDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ComponentDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComponentDto componentDto = (ComponentDto) target;
        if (componentDto.getComponentName() == null) {
            errors.rejectValue("componentName", "ComponentDto.componentName.null", "Name can not be null!");
        } else if (componentDto.getComponentName().isEmpty()) {
            errors.rejectValue("componentName", "ComponentDto.componentName.isEmpty", "Name can not be empty!");
        }

        if (componentDto.getComponentType() == null) {
            errors.rejectValue("componentType", "ComponentDto.componentType.null", "ComponentType can not be null!");
        }

        if (componentDto.getDescription() == null) {
            errors.rejectValue("description", "ComponentDto.description.null", "Description can not be null!");
        } else if (componentDto.getDescription().isEmpty()) {
            errors.rejectValue("description", "ComponentDto.description.isEmpty", "Description can not be empty!");
        }

        if (componentDto.getFrequency() == null) {
            errors.rejectValue("frequency", "ComponentDto.frequency.null", "Frequency can not be null!");
        } else if ((componentDto.getFrequency().compareTo(new BigDecimal(0.0)) == 0)) {
            errors.rejectValue("frequency", "ComponentDto.frequency.zero", "Frequency can not be zero!");
        }

        if (componentDto.getPrice() == null) {
            errors.rejectValue("price", "ComponentDto.price.null", "Price can not be null!");
        } else if ((componentDto.getPrice().compareTo(new BigDecimal(0.0)) == 0)) {
            errors.rejectValue("price", "ComponentDto.price.zero", "Price can not be zero!");
        }

    }

}
