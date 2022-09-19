/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.silab.application.pcbuilder.dto.impl.UserDto;
import rs.fon.silab.application.pcbuilder.dto.impl.UserProfileDto;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;
import rs.fon.silab.application.pcbuilder.validatorui.UserDtoValidator;

/**
 *
 * @author LightPower
 */
@Controller
public class UserController {

    private final ServiceOperations<UserDto> serviceUser;
    private final ServiceOperations<UserProfileDto> servoceProfile;

    @Autowired
    public UserController(@Qualifier(value = "userService") ServiceOperations<UserDto> serviceUser,
            @Qualifier(value = "userProfileService") ServiceOperations<UserProfileDto> servoceProfile) {
        this.serviceUser = serviceUser;
        this.servoceProfile = servoceProfile;
    }

    @RequestMapping(path = "/admin/user",method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("/user/user-list");
        try {
            modelAndView.addObject("users", serviceUser.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }
    
      @RequestMapping(path = "/admin/user/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteObject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/user");
        try {
            UserDto userDto = new UserDto();
            userDto.setUserId(id);
            serviceUser.delete(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly deleted user!");
        } catch (DataIntegrityViolationException divex) {
            divex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "You can not delete user if item he created still exist, delete his items first!");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Deleting user was not successful");
        }
        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof UserDto) {
            binder.addValidators(new UserDtoValidator());
        }
    }

}
