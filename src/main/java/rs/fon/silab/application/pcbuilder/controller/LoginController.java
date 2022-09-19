/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@Scope("session")
public class LoginController {

    private final ServiceOperations<UserDto> serviceUser;
    private final ServiceOperations<UserProfileDto> serviceProfile;

    @Autowired
    public LoginController(@Qualifier(value = "userService") ServiceOperations<UserDto> serviceUser,
            @Qualifier(value = "userProfileService") ServiceOperations<UserProfileDto> servoceProfile) {
        this.serviceUser = serviceUser;
        this.serviceProfile = servoceProfile;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/home/home");
        try {
            UserDto user = new UserDto();
            user.setUsername(getPrincipal());
            user = serviceUser.find(user);

            request.getSession().setAttribute("principal", user);
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("surname", user.getSurname());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("role", user.getProfile().getProfileName());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", ex.getMessage());
        }

        return modelAndView;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(String error, String logout,
            RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("/home/login");
        if (error != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Your username and/or password are invalid.");
            modelAndView.setViewName("redirect:/login");
        }

        if (logout != null) {
//            redirectAttributes.addFlashAttribute("successMessage", "You have been logged out.");
            modelAndView.setViewName("redirect:/home");
//            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage(@ModelAttribute UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView("/home/register");
        try {
            modelAndView.addObject("roles", serviceProfile.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Form error, failed to load roles!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/registerDo", method = RequestMethod.POST)
    public ModelAndView registerDo(@Valid @ModelAttribute UserDto userDto,
            Errors errors, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("/home/register");
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
            try {
                modelAndView.addObject("roles", serviceProfile.getAll());
            } catch (Exception ex) {
                ex.printStackTrace();
                modelAndView.addObject("errorMessage", "Form error, failed to load roles!");
            }
        } else {
            try {
                userDto.setName(makeCapitalFirstLetter(userDto.getName()));
                userDto.setSurname(makeCapitalFirstLetter(userDto.getSurname()));
                if (userDto.getProfile() == null) {
                    userDto.setProfile(new UserProfileDto(1L, "USER"));
                }
                serviceUser.save(userDto);
                modelAndView.setViewName("redirect:/register");
                redirectAttributes.addFlashAttribute("successMessage", "Successfuly registered new user!");
            } catch (Exception ex) {
                ex.printStackTrace();
                modelAndView.setViewName("/home/register");
                modelAndView.addObject("errorMessage", "Saving user was not successful! </br>" + ex.getMessage());
                try {
                    modelAndView.addObject("roles", serviceProfile.getAll());
                } catch (Exception e) {
                    e.printStackTrace();
                    modelAndView.addObject("errorMessage", "Form error, failed to load roles! </br>" + ex.getMessage());
                }
            }
        }
        return modelAndView;
    }

    private String makeCapitalFirstLetter(String word) {
        String firstLetStr = word.substring(0, 1);
        String remLetStr = word.substring(1);
        firstLetStr = firstLetStr.toUpperCase();
        return firstLetStr + remLetStr;
    }

    private String getPrincipal() throws Exception {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            throw new Exception("Error retriving current user!");
        }
        return username;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof UserDto) {
            binder.addValidators(new UserDtoValidator());
        }
    }
}
