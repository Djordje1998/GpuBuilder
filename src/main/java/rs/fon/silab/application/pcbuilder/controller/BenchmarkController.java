/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import rs.fon.silab.application.pcbuilder.dto.impl.BenchmarkDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerDto;
import rs.fon.silab.application.pcbuilder.dto.impl.ComputerItemDto;
import rs.fon.silab.application.pcbuilder.dto.impl.GradeDto;
import rs.fon.silab.application.pcbuilder.dto.impl.StressTestDto;
import rs.fon.silab.application.pcbuilder.service.ServiceOperations;
import rs.fon.silab.application.pcbuilder.validatorui.BenchmarkDtoValidatior;

/**
 *
 * @author LightPower
 */
@Controller
public class BenchmarkController {

    private final ServiceOperations<BenchmarkDto> serviceBenchmark;
    private final ServiceOperations<ComputerDto> serviceComputer;
    private final ServiceOperations<StressTestDto> serviceTest;

    @Autowired
    public BenchmarkController(@Qualifier(value = "benchmarkService") ServiceOperations<BenchmarkDto> serviceBenchmark,
            @Qualifier(value = "computerService") ServiceOperations<ComputerDto> serviceComputer,
            @Qualifier(value = "stressTestService") ServiceOperations<StressTestDto> serviceTest) {
        this.serviceBenchmark = serviceBenchmark;
        this.serviceComputer = serviceComputer;
        this.serviceTest = serviceTest;
    }

    @RequestMapping(path = "/benchmark", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("/benchmark/benchmark-list");
        try {
            modelAndView.addObject("benchmarks", serviceBenchmark.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/benchmark/search", method = RequestMethod.GET)
    public ModelAndView getAll(String search) {
        ModelAndView modelAndView = new ModelAndView("/benchmark/benchmark-list");
        try {
            List<ComputerDto> foundComp = serviceComputer.search(search);
            if (foundComp.isEmpty()) {
                modelAndView.addObject("info", "There is no component with name, that contain <b>" + search + "</b>!");
            } else {
                List<BenchmarkDto> foundBenchmark = new ArrayList<>();
                for (ComputerDto computerDto : foundComp) {
                    List<BenchmarkDto> benchmarks = serviceBenchmark.search(computerDto.getComputerId().toString());
                    for (BenchmarkDto benchmark : benchmarks) {
                        foundBenchmark.add(benchmark);
                    }
                }
                if (!foundBenchmark.isEmpty()) {
                    modelAndView.addObject("benchmarks", foundBenchmark);
                } else {
                    modelAndView.addObject("info", "There is no benchmarks for computer with name, that contain <b>" + search + "</b>!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/benchmark/add", method = RequestMethod.GET)
    public ModelAndView getAddPage(@ModelAttribute BenchmarkDto benchmarkDto) {
        ModelAndView modelAndView = new ModelAndView("/benchmark/benchmark-add");
        try {
            modelAndView.addObject("computers", serviceComputer.getAll());
            modelAndView.addObject("tests", serviceTest.getAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/benchmark/save", method = RequestMethod.POST)
    public ModelAndView saveBenchmark(@ModelAttribute BenchmarkDto benchmarkDto,
            Errors errors, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("/benchmark/benchmark-add");
            modelAndView.addObject("errorMessage", "Validation failed, check data!");
        } else {
            try {
                if (benchmarkDto.getFirstPc().equals(benchmarkDto.getSecondPc())) {
                    modelAndView.setViewName("redirect:/benchmark/add");
                    redirectAttributes.addFlashAttribute("errorMessage", "There is no point in comparing two same computers, please select two different!");
                    return modelAndView;
                }
                benchmarkDto.setCreatedDate(new Date());
                benchmarkDto.setStressTest(serviceTest.find(benchmarkDto.getStressTest()));
                benchmarkDto.setScoreFirstPc(calculatePcScore(serviceComputer.find(benchmarkDto.getFirstPc()),
                        benchmarkDto.getStressTest()));
                benchmarkDto.setScoreSecondPc(calculatePcScore(serviceComputer.find(benchmarkDto.getSecondPc()),
                        benchmarkDto.getStressTest()));
                benchmarkDto.setFirstPc(serviceComputer.find(benchmarkDto.getFirstPc()));
                benchmarkDto.setSecondPc(serviceComputer.find(benchmarkDto.getSecondPc()));
                
                BenchmarkDto save = serviceBenchmark.save(benchmarkDto);
                modelAndView.setViewName("redirect:/benchmark/result/" + save.getBenchmarkId());
                redirectAttributes.addFlashAttribute("successMessage", "Successfuly saved bencmark!");
            } catch (Exception ex) {
                ex.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage", "Saving benchmark was not successful!");
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "/benchmark/result/{id}", method = RequestMethod.GET)
    public ModelAndView getResult(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/benchmark/benchmark-result");
        try {
            BenchmarkDto dto = new BenchmarkDto();
            dto.setBenchmarkId(id);
            BenchmarkDto found = serviceBenchmark.find(dto);
            modelAndView.addObject("benchmark", found);
            modelAndView.addObject("conclusion", makeConclusion(found));
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView.addObject("errorMessage", "Failure during loading page!");
        }
        return modelAndView;
    }

    @RequestMapping(path = "/admin/benchmark/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteObject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/benchmark");
        try {
            BenchmarkDto benchmarkDto = new BenchmarkDto();
            benchmarkDto.setBenchmarkId(id);
            serviceBenchmark.delete(benchmarkDto);
            redirectAttributes.addFlashAttribute("successMessage", "Successfuly deleted benchmark with ID <b>" + id + "</b>!");
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Deleting benchmark was not successful");
        }
        return modelAndView;
    }

    private BigDecimal calculatePcScore(ComputerDto find, StressTestDto test) {
        BigDecimal score = new BigDecimal(0.0);
        List<ComputerItemDto> items = find.getItems();
        for (ComputerItemDto item : items) {
            score = score.add(score.add(GradeDto.calculate(test, item.getComponent())));
        }
        return score;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof BenchmarkDto) {
            binder.addValidators(new BenchmarkDtoValidatior());
        }
    }

    private String makeConclusion(BenchmarkDto found) {
        if (found.getScoreFirstPc().compareTo(found.getScoreSecondPc()) == 0) {
            return "Computer <b>" + found.getFirstPc().getComputerName()
                    + "</b> and computer <b>" + found.getSecondPc().getComputerName()
                    + "</b> have identical performance score <b>" + found.getScoreFirstPc()
                    + "</b>, what means they are equally good!";
        } else if (found.getScoreFirstPc().compareTo(found.getScoreSecondPc()) < 0) {
            return "Computer <b>" + found.getSecondPc().getComputerName()
                    + "</b> have better performance compare to computer <b>" + found.getFirstPc().getComputerName()
                    + "</b> by <b>" + found.getScoreSecondPc().subtract(found.getScoreFirstPc())
                    + "</b> points, what is <b>"
                    + (!(found.getScoreFirstPc().equals(new BigDecimal(0)) || found.getScoreSecondPc().equals(new BigDecimal(0)))
                    ? found.getScoreSecondPc()
                            .divide(found.getScoreFirstPc(), 2, RoundingMode.HALF_UP)
                            .multiply(new BigDecimal(100))
                    : "N/A") + "%</b> performance difference!";
        } else {
            return "Computer <b>" + found.getFirstPc().getComputerName()
                    + "</b> have better performance compare to computer <b>" + found.getSecondPc().getComputerName()
                    + "</b> by <b>" + found.getScoreFirstPc().subtract(found.getScoreSecondPc())
                    + "</b> points, what is <b>"
                    + (!(found.getScoreFirstPc().equals(new BigDecimal(0)) || found.getScoreSecondPc().equals(new BigDecimal(0)))
                    ? found.getScoreFirstPc()
                            .divide(found.getScoreSecondPc(), 2, RoundingMode.HALF_UP)
                            .multiply(new BigDecimal(100))
                    : "N/A") + "%</b> performance difference!";
        }
    }

}
