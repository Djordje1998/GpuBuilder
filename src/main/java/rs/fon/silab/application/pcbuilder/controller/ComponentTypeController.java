/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rs.fon.silab.application.pcbuilder.dto.impl.ComponentTypeDto;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Controller
public class ComponentTypeController {

    private final ServiceOperations<ComponentTypeDto> service;

    @Autowired
    public ComponentTypeController(@Qualifier(value = "componentTypeService") ServiceOperations<ComponentTypeDto> service) {
        this.service = service;
    }

    @RequestMapping(path = "/admin/type", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("/type/component-type-list");
        try {
            List<ComponentTypeDto> types = service.getAll();
            modelAndView.addObject("types", types);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }

}
