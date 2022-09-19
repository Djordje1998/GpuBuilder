/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import rs.fon.silab.application.pcbuilder.dto.impl.GradeDto;
import rs.fon.silab.application.pcbuilder.dto.impl.StressTestDto;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;
import rs.fon.silab.application.pcbuilder.validatorui.GradeDtoValidator;

/**
 *
 * @author LightPower
 */
@Controller
public class GradeController {

    private final ServiceOperations<GradeDto> serviceGrade;
    private final ServiceOperations<ComponentDto> serviceComponent;
    private final ServiceOperations<StressTestDto> serviceStressTest;

    @Autowired
    public GradeController(@Qualifier(value = "gradeService") ServiceOperations<GradeDto> serviceGrade,
            @Qualifier(value = "componentService") ServiceOperations<ComponentDto> serviceComponent,
            @Qualifier(value = "stressTestService") ServiceOperations<StressTestDto> serviceStressTest) {
        this.serviceGrade = serviceGrade;
        this.serviceComponent = serviceComponent;
        this.serviceStressTest = serviceStressTest;
    }

    @RequestMapping(path = "/grade", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("/grade/grade-list");
        try {
            modelAndView.addObject("grades", serviceGrade.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/grade/search", method = RequestMethod.GET)
    public ModelAndView search(String search) {
        ModelAndView modelAndView = new ModelAndView("/grade/grade-list");
        try {
            List<ComponentDto> foundComp = serviceComponent.search(search);
            if (foundComp.isEmpty()) {
                modelAndView.addObject("info", "There is no component with name, that contain <b>" + search + "</b>!");
            } else {
                List<GradeDto> foundGrade = new ArrayList<>();
                for (ComponentDto componentDto : foundComp) {
                    List<GradeDto> grades = serviceGrade.search(componentDto.getComponentId().toString());
                    for (GradeDto grade : grades) {
                        foundGrade.add(grade);
                    }
                }
                if (!foundGrade.isEmpty()) {
                    modelAndView.addObject("grades", foundGrade);
                } else {
                    modelAndView.addObject("info", "There is no grades for component with name, that contain <b>" + search + "</b>!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/grade/add", method = RequestMethod.GET)
    public ModelAndView getAddPage(@ModelAttribute GradeDto gradeDto) {
        ModelAndView modelAndView = new ModelAndView("/grade/grade-add");
        try {
            gradeDto.setComponent(new ComponentDto());
            gradeDto.setStressTest(new StressTestDto());
            gradeDto.setGradeValue(new BigDecimal(0.0));
            modelAndView.addObject("components", serviceComponent.getAll());
            modelAndView.addObject("tests", serviceStressTest.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/grade/save", method = RequestMethod.POST)
    public ModelAndView saveGrade(@ModelAttribute GradeDto gradeDto,
            RedirectAttributes redirectAttributes, Errors errors) {
        ModelAndView modelAndView = new ModelAndView("redirect:/grade");
        if (errors.hasErrors()) {
            modelAndView.setViewName("/grade/grade-add");
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
        } else {
            try {
                gradeDto.setComponent(serviceComponent.find(gradeDto.getComponent()));
                gradeDto.setStressTest(serviceStressTest.find(gradeDto.getStressTest()));
                gradeDto.setGradeValue(GradeDto.calculate(gradeDto.getStressTest(), gradeDto.getComponent()));
              
                GradeDto save = serviceGrade.save(gradeDto);
                
                redirectAttributes.addFlashAttribute("successMessage", "Successfuly saved component grade!");
                redirectAttributes.addFlashAttribute("latest", save);
            } catch (PersistenceException pex) {
                pex.printStackTrace();
                redirectAttributes.addFlashAttribute("successMessage", "Grade for choosen component already exists!");
                redirectAttributes.addFlashAttribute("latest", gradeDto);
            } catch (Exception ex) {
                ex.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage", "Saving component grade was not successful!");
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "/admin/grade/delete/{idComponnent}/{idTest}", method = RequestMethod.GET)
    public ModelAndView deleteObject(@PathVariable Long idComponnent,
            @PathVariable Long idTest,
            RedirectAttributes redirectAttributes
    ) {
        ModelAndView modelAndView = new ModelAndView("redirect:/grade");
        try {
            GradeDto gradeDto = new GradeDto();
            gradeDto.setComponent(new ComponentDto());
            gradeDto.getComponent().setComponentId(idComponnent);
            gradeDto.setStressTest(new StressTestDto());
            gradeDto.getStressTest().setTestId(idTest);
            serviceGrade.delete(gradeDto);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly deleted grade!");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Deleting grade was not successful");
        }
        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder
    ) {
        if (binder.getTarget() instanceof GradeDto) {
            binder.addValidators(new GradeDtoValidator());
        }
    }

}
