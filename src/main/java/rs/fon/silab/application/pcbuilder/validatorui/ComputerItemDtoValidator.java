/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.validatorui;

import java.math.BigDecimal;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;

/**
 *
 * @author LightPower
 */
public class ComputerItemDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ComputerItemDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerItemDto computerItemDto = (ComputerItemDto) target;
        
        if (computerItemDto.getItemPrice()== null) {
            errors.rejectValue("itemPrice", "ComputerItemDto.itemPrice.null", "Item price can not be null!");
        } else if ((computerItemDto.getItemPrice().compareTo(BigDecimal.ZERO) < 0)) {
            errors.rejectValue("itemPrice", "ComputerItemDto.itemPrice.zero", "Item price can not be negative value!");
        }
        
    }

}
