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
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;

/**
 *
 * @author LightPower
 */
@Controller
public class ComputerItemController {

    private final ServiceOperations<ComputerItemDto> service;

    @Autowired
    public ComputerItemController(@Qualifier(value = "computerItemService") ServiceOperations<ComputerItemDto> service) {
        this.service = service;
    }

    @RequestMapping(path = "/admin/item", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("/computer/item/computer-item-list");
        try {
            List<ComputerItemDto> items = service.getAll();
            modelAndView.addObject("items", items);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }
    

}
