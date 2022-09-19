/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.validatorui;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rs.fon.silab.application.pcbuilder.dto.impl.UserDto;

/**
 *
 * @author LightPower
 */
public class UserDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if (userDto.getName() == null) {
            errors.rejectValue("name", "UserDto.name.null", "Name can not be null!");
        } else if (userDto.getName().isEmpty()) {
            errors.rejectValue("name", "UserDto.name.isEmpty", "Name can not be empty!");
        }

        if (userDto.getSurname() == null) {
            errors.rejectValue("surname", "UserDto.surname.null", "Surname can not be null!");
        } else if (userDto.getSurname().isEmpty()) {
            errors.rejectValue("surname", "UserDto.surname.isEmpty", "Surname can not be empty!");
        }

        if (userDto.getEmail() == null) {
            errors.rejectValue("email", "UserDto.email.null", "E-mail can not be null!");
        } else if (userDto.getEmail().isEmpty()) {
            errors.rejectValue("email", "UserDto.email.isEmpty", "E-mail can not be empty!");
        } else if (!(userDto.getEmail().contains("@") && userDto.getEmail().contains("."))) {
            errors.rejectValue("email", "UserDto.email.badFormat", "E-mail format is not valid!");
        }

        if (userDto.getUsername() == null) {
            errors.rejectValue("username", "UserDto.username.null", "Username can not be null!");
        } else if (userDto.getUsername().isEmpty()) {
            errors.rejectValue("username", "UserDto.username.isEmpty", "Username can not be empty!");
        }

        if (userDto.getPassword() == null) {
            errors.rejectValue("password", "UserDto.password.null", "Password can not be null!");
        } else if (userDto.getPassword().isEmpty()) {
            errors.rejectValue("password", "UserDto.password.isEmpty", "Password can not be empty!");
        }

        if (userDto.getRepeatPassword() == null) {
            errors.rejectValue("repeatPassword", "UserDto.repeatPassword.null", "Repeat Password can not be null!");
        } else if (userDto.getRepeatPassword().isEmpty()) {
            errors.rejectValue("repeatPassword", "UserDto.repeatPassword.isEmpty", "Repeat Password can not be empty!");
        } else if (!userDto.getRepeatPassword().equals(userDto.getPassword())) {
            errors.rejectValue("repeatPassword", "UserDto.repeatPassword.different", "Passwords are not identical!");
        }
    }

}
