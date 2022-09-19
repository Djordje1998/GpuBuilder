/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.validatorui;

import java.math.BigDecimal;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerDto;

/**
 *
 * @author LightPower
 */
public class ComputerDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ComputerDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerDto computerDto = (ComputerDto) target;

        if (computerDto.getComputerName() == null) {
            errors.rejectValue("computerName", "ComputerDto.computerName.null", "Name can not be null!");
        } else if (computerDto.getComputerName().isEmpty()) {
            errors.rejectValue("computerName", "ComputerDto.computerName.isEmpty", "Name can not be empty!");
        }

        if (computerDto.getWarranty() == null) {
            errors.rejectValue("warranty", "ComputerDto.warranty.null", "Warranty can not be null!");
        }

        if (computerDto.getUsage() == null) {
            errors.rejectValue("usage", "ComputerDto.usage.null", "Usage can not be null!");
        } else if (computerDto.getUsage().isEmpty()) {
            errors.rejectValue("usage", "ComputerDto.usage.isEmpty", "Usage can not be empty!");
        }

        if (computerDto.getTotalPrice() == null) {
            errors.rejectValue("totalPrice", "ComputerDto.totalPrice.null", "Total price can not be null!");
        } else if (isNumber(computerDto.getTotalPrice())) {
            errors.rejectValue("totalPrice", "ComputerDto.totalPrice.notNumber", "Total price must be number!");
        } else if ((computerDto.getTotalPrice().compareTo(BigDecimal.ZERO) < 0)) {
            errors.rejectValue("totalPrice", "ComputerDto.totalPrice.zero", "Total price can not be negative value!");
        }
    }

    private boolean isNumber(BigDecimal totalPrice) {
        return false;
    }

}
