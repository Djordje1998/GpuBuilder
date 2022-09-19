/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentTypeDto;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;
import rs.fon.silab.application.pcbuilder.validatorui.ComponentDtoValidator;

/**
 *
 * @author LightPower
 */
@Controller
public class ComponentController {

    private final ServiceOperations<ComponentDto> componentService;
    private final ServiceOperations<ComponentTypeDto> componentTypeService;

    @Autowired
    public ComponentController(@Qualifier(value = "componentService") ServiceOperations<ComponentDto> componentService,
            @Qualifier(value = "componentTypeService") ServiceOperations<ComponentTypeDto> typeService) {
        this.componentService = componentService;
        this.componentTypeService = typeService;
    }

    @RequestMapping(path = "/component", method = RequestMethod.GET)
    public ModelAndView getAllPage() {
        ModelAndView modelAndView = new ModelAndView("/component/component-list");
        try {
            List<ComponentDto> components = componentService.getAll();
            modelAndView.addObject("components", components);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(path = "/component/search", method = RequestMethod.GET)
    public ModelAndView searchComponent(String search) {
        ModelAndView modelAndView = new ModelAndView("/component/component-list");
        try {
            List<ComponentDto> found = componentService.search(search);
            modelAndView.addObject("components", found);
            if (found.isEmpty()) {
                modelAndView.addObject("info", "There is no component with name, that contain <b>" + search + "</b>!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(path = "/component/add", method = RequestMethod.GET)
    public ModelAndView getAddPage(@ModelAttribute ComponentDto componentDto) {
        ModelAndView modelAndView = new ModelAndView("/component/component-add");
        try {
            componentDto.setComponentType(new ComponentTypeDto());
            modelAndView.addObject("types", componentTypeService.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Error loading component types from databse!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/component/save", method = RequestMethod.POST)
    public ModelAndView saveComponent(@Valid @ModelAttribute ComponentDto componentDto,
            Errors errors, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("/component/component-add");
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
            try {
                modelAndView.addObject("types", componentTypeService.getAll());
            } catch (Exception ex) {
                ex.printStackTrace();
                modelAndView.addObject("errorMessage", "Error loading component types from database!");
            }
        } else {
            try {
                componentDto.setComponentType(componentTypeService.find(componentDto.getComponentType()));
                componentDto.setComponentName(makeCapitalFirstLetter(componentDto.getComponentName()));

                ComponentDto saved = componentService.save(componentDto);
                modelAndView.setViewName("redirect:/component");
                redirectAttributes.addFlashAttribute("successMessage", "Successfuly saved component!");
                redirectAttributes.addFlashAttribute("latest", saved);
            } catch (Exception ex) {
                ex.printStackTrace();
                modelAndView.setViewName("redirect:/component/add");
                redirectAttributes.addFlashAttribute("errorMessage", "Saving component was not successful!");
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "/component/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getEditPage(@PathVariable Long id, @ModelAttribute ComponentDto componentDto,
            RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("/component/component-edit");
        try {
            componentDto.setComponentId(id);
            ComponentDto find = componentService.find(componentDto);
            valueTransfer(componentDto, find);
            modelAndView.addObject("type", componentDto.getComponentType());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.setViewName("redirect:/component");
            redirectAttributes.addFlashAttribute("errorMessage", "Can not load form with component");
        }
        return modelAndView;
    }
    
        @RequestMapping(path = "/component/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getDetailsPage(@PathVariable Long id, @ModelAttribute ComponentDto componentDto,
            RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("/component/component-details");
        try {
            componentDto.setComponentId(id);
            ComponentDto find = componentService.find(componentDto);
            valueTransfer(componentDto, find);
            modelAndView.addObject("type", componentDto.getComponentType());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.setViewName("redirect:/component");
            redirectAttributes.addFlashAttribute("errorMessage", "Can not load form with component");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/component/update", method = RequestMethod.POST)
    public ModelAndView updateComponent(@Valid @ModelAttribute ComponentDto componentDto,
            Errors errors, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("/component/component-edit");
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
        } else {
            try {
                componentDto.setComponentType(componentTypeService.find(componentDto.getComponentType()));
                componentDto.setComponentName(makeCapitalFirstLetter(componentDto.getComponentName()));

                componentService.update(componentDto);
                modelAndView.setViewName("redirect:/component");
                redirectAttributes.addFlashAttribute("successMessage", "Successfuly updated component!");
            } catch (Exception ex) {
                ex.printStackTrace();
                modelAndView.setViewName("/component/component-edit");
                modelAndView.addObject("errorMessage", "Updating component was not successful!");
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "/admin/component/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteComponente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/component");
        try {
            ComponentDto componentDto = new ComponentDto();
            componentDto.setComponentId(id);
            componentService.delete(componentDto);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly deleted component with ID <b>" + id + "</b>!");
        } catch (DataIntegrityViolationException divex) {
            divex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "You can not delete component "
                    + "if it is item of existing computer, delete computer item first!");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Deleting component was not successful");

        }
        return modelAndView;
    }

    public void valueTransfer(ComponentDto to, ComponentDto from) {
        to.setComponentName(from.getComponentName());
        to.setComponentType(from.getComponentType());
        to.setDescription(from.getDescription());
        to.setFrequency(from.getFrequency());
        to.setPrice(from.getPrice());
    }

    private String makeCapitalFirstLetter(String word) {
        String firstLetStr = word.substring(0, 1);
        String remLetStr = word.substring(1);
        firstLetStr = firstLetStr.toUpperCase();
        return firstLetStr + remLetStr;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof ComponentDto) {
            binder.addValidators(new ComponentDtoValidator());
        }
    }

}
