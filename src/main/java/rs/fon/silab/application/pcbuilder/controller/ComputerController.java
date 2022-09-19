/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.dto.impl.UserDto;
import rs.fon.silab.application.pcbuilder.exporter.ExcelFileExporter;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;
import rs.fon.silab.application.pcbuilder.validatorui.ComputerDtoValidator;
import rs.fon.silab.application.pcbuilder.validatorui.ComputerItemDtoValidator;

/**
 *
 * @author LightPower
 */
@Controller
public class ComputerController {

    private final ServiceOperations<ComputerDto> serviceComputer;
    private final ServiceOperations<ComponentDto> serviceComponent;
    private final ServiceOperations<ComputerItemDto> serviceComputerItem;

    @Autowired
    public ComputerController(@Qualifier(value = "computerService") ServiceOperations<ComputerDto> serviceComputer,
            @Qualifier(value = "componentService") ServiceOperations<ComponentDto> serviceComponent,
            @Qualifier(value = "computerItemService") ServiceOperations<ComputerItemDto> serviceComputerItem) {
        this.serviceComputer = serviceComputer;
        this.serviceComponent = serviceComponent;
        this.serviceComputerItem = serviceComputerItem;
    }

    @RequestMapping(path = "/computer", method = RequestMethod.GET)
    public ModelAndView getAllPage() {
        ModelAndView modelAndView = new ModelAndView("/computer/computer-list");
        try {
            modelAndView.addObject("computers", serviceComputer.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/search", method = RequestMethod.GET)
    public ModelAndView searchComputer(String search) {
        ModelAndView modelAndView = new ModelAndView("/computer/computer-list");
        try {
            List<ComputerDto> found = serviceComputer.search(search);
            modelAndView.addObject("computers", found);
            if (found.isEmpty()) {
                modelAndView.addObject("info", "There is no computer with name, that contain <b>" + search + "</b>!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/add", method = RequestMethod.GET)
    public ModelAndView getAddPage(@ModelAttribute ComputerDto computerDto) {
        ModelAndView modelAndView = new ModelAndView("/computer/computer-add");
        computerDto.setTotalPrice(new BigDecimal("0.0"));
        return modelAndView;
    }

    @RequestMapping(path = "/computer/save", method = RequestMethod.POST)
    public ModelAndView saveComputer(@Valid @ModelAttribute ComputerDto computerDto, Errors errors,
            RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("/computer/computer-add");
        if (errors.hasErrors()) {
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
        }
        try {
            computerDto.setComputerName(makeCapitalFirstLetter(computerDto.getComputerName()));

            ComputerDto saved = serviceComputer.save(computerDto);
            modelAndView.setViewName("redirect:/computer/details/" + saved.getComputerId());
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly saved computer!");
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Saving computer was not successful!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/details/{id}", method = RequestMethod.GET)
    public ModelAndView getDetailsPage(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("/computer/computer-details");
        try {
            ComputerDto computerDto = new ComputerDto();
            computerDto.setComputerId(id);
            ComputerDto find = serviceComputer.find(computerDto);
            modelAndView.addObject("computerDto", find);
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.setViewName("redirect:/computer");
            redirectAttributes.addFlashAttribute("errorMessage", "Detailed view of compouter failed to load!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getEditPage(@PathVariable Long id, @ModelAttribute ComputerDto computerDto,
            RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("/computer/computer-edit");
        try {
            computerDto.setComputerId(id);
            ComputerDto find = serviceComputer.find(computerDto);
            valueTransfer(computerDto, find);
            modelAndView.addObject("computer", serviceComputer.find(computerDto));
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.setViewName("redirect:/computer");
            redirectAttributes.addFlashAttribute("errorMessage", "Edit view of compoute rfailed to load!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/admin/computer/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteComputer(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("redirect:/computer");
        try {
            ComputerDto computerDto = new ComputerDto();
            computerDto.setComputerId(id);
            serviceComputer.delete(computerDto);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly deleted computer with ID <b>" + id + "</b>!");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Deleting computer was not successful!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/update", method = RequestMethod.POST)
    public ModelAndView updateComputer(@Valid @ModelAttribute ComputerDto computerDto, Errors errors,
            RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("/computer/computer-edit");
        if (errors.hasErrors()) {
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
        }
        try {
            computerDto.setComputerName(makeCapitalFirstLetter(computerDto.getComputerName()));

            serviceComputer.update(computerDto);
            modelAndView.setViewName("redirect:/computer");
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly updated computer!");
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Updating computer was not successful!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/{id}/addItem", method = RequestMethod.GET)
    public ModelAndView getAddItemPage(@PathVariable Long id, @ModelAttribute ComputerItemDto computerItemDto,
            RedirectAttributes redirectAttributes, String search) {

        ModelAndView modelAndView = new ModelAndView("/computer/item/computer-item-add-search");
        try {
            ComputerDto computerDto = new ComputerDto();
            computerDto.setComputerId(id);
            ComputerDto find = serviceComputer.find(computerDto);
            modelAndView.addObject("computerDto", find);

            List<ComponentDto> components = serviceComponent.search(search);
            modelAndView.addObject("components", components);
            if (components.isEmpty()) {
                modelAndView.addObject("info", "There is no computer with name, that contain <b>" + search + "</b>!");
            }
            computerItemDto.setComputerId(id);
            computerItemDto.setSerialNumber(getSerialNumber(find.getItems()));
            computerItemDto.setAmount(1);
            computerItemDto.setComponent(new ComponentDto());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.setViewName("redirect:/computer/details/{" + id + "}");
            redirectAttributes.addFlashAttribute("errorMessage", "Adding computer item was not successful!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/addItem", method = RequestMethod.POST)
    public ModelAndView addItem(@Valid @ModelAttribute ComputerItemDto computerItemDto,
            Errors errors, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Long computerId = computerItemDto.getComputerId();
        ModelAndView modelAndView = new ModelAndView("/computer/item/computer-item-add-search");
        if (errors.hasErrors()) {
            modelAndView.addObject("errorMessage", "Validation failed, check data!!");
        }
        try {
            BigDecimal itemTotalPrice = computerItemDto.getItemPrice()
                    .multiply(BigDecimal.valueOf(computerItemDto.getAmount()));
            computerItemDto.setTotalItemPrice(itemTotalPrice);
            computerItemDto.setCreatedDate(new Date());

            computerItemDto.setCreatedBy((UserDto) request.getSession().getAttribute("principal"));

            computerItemDto.setComponent(serviceComponent.find(computerItemDto.getComponent()));

            ComputerDto computerDto = new ComputerDto();
            computerDto.setComputerId(computerId);
            ComputerDto foundComputer = serviceComputer.find(computerDto);
            foundComputer.setTotalPrice(foundComputer.getTotalPrice().add(itemTotalPrice));
            foundComputer.getItems().add(computerItemDto);

            foundComputer.setComputerName(makeCapitalFirstLetter(foundComputer.getComputerName()));

            serviceComputer.update(foundComputer);
            modelAndView.setViewName("redirect:/computer/details/" + computerId);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly add new component into computer!");
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.setViewName("redirect:/computer/" + computerId + "/addItem");
            redirectAttributes.addFlashAttribute("errorMessage", "Adding computer item was not successful!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/admin/computer/deleteItem/{id}/{num}", method = RequestMethod.GET)
    public ModelAndView deleteItem(@PathVariable Long id, @PathVariable Long num,
            RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("redirect:/computer/details/" + id);
        List<ComputerItemDto> newItems = new ArrayList<>();
        try {
            ComputerDto compid = new ComputerDto();
            compid.setComputerId(id);
            ComputerDto found = serviceComputer.find(compid);

            if (found.getItems().size() != num) {
                ComputerItemDto last = found.getItems().get(found.getItems().size() - 1);
                serviceComputerItem.delete(last);
            }
            ComputerItemDto removed = removeOneItem(found.getItems(), newItems, num);
            found.setTotalPrice(found.getTotalPrice().subtract(removed.getTotalItemPrice()));
            found.setItems(newItems);
            serviceComputerItem.delete(removed);
            serviceComputer.update(found);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly deleted computer item!");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Deleting computer item was not successful!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/computer/download/excel/{id}", method = RequestMethod.GET)
    public void downloadExcel(@PathVariable Long id, HttpServletResponse response) {
        ComputerDto dto = new ComputerDto();
        dto.setComputerId(id);
        try {
            dto = serviceComputer.find(dto);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=Computer - " + dto.getComputerName() + ".xlsx");
            ByteArrayInputStream inputStream = ExcelFileExporter.exportToExcel(dto);
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void valueTransfer(ComputerDto to, ComputerDto from) {
        to.setComputerName(from.getComputerName());
        to.setItems(from.getItems());
        to.setTotalPrice(from.getTotalPrice());
        to.setUsage(from.getUsage());
        to.setWarranty(from.getWarranty());
    }

    private Long getSerialNumber(List<ComputerItemDto> items) {
        if (items != null) {
            return new Long("" + (items.size() + 1));
        }
        return 1L;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof ComputerDto) {
            binder.addValidators(new ComputerDtoValidator());
        }
        if (binder.getTarget() instanceof ComputerItemDto) {
            binder.addValidators(new ComputerItemDtoValidator());
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    private String makeCapitalFirstLetter(String word) {
        String firstLetStr = word.substring(0, 1);
        String remLetStr = word.substring(1);
        firstLetStr = firstLetStr.toUpperCase();
        return firstLetStr + remLetStr;
    }

    // vraca izbaceni item, parametri: 1. lista sa uljezem 2. litas bez uljeza 3. pozicija uljeza
    private ComputerItemDto removeOneItem(List<ComputerItemDto> items, List<ComputerItemDto> newItems, Long serialNum) {
        Long i = 1L;
        ComputerItemDto removed = null;
        for (ComputerItemDto item : items) {
            if (!item.getSerialNumber().equals(serialNum)) {
                item.setSerialNumber(i++);
                newItems.add(item);
            } else {
                removed = item;
            }
        }
        return removed;
    }

}
