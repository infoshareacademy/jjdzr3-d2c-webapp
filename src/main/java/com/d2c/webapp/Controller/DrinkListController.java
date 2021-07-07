package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
import com.d2c.webapp.domain.Pager;
import com.infoshareademy.domain.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class DrinkListController {
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

     @Autowired
    DrinkService drinkService;

    @GetMapping(value = "/showAllDrinks")
    public String getShowAllDrinks (Model model) {
        List<Drink> drinkList = drinkService.getDrinkList();
        model.addAttribute("Drinks", drinkList);

        return "subSites/showAllDrinks";
    }


    @GetMapping(value = "/showAllDrinks2")
    public String getShowAllDrinks2(
            Model model, @RequestParam("page")Optional<Integer>page, @RequestParam("size") Optional<Integer>size) {
        int currentPage = page.orElse(1);
        int pageSize5 = size.orElse(5);
        Page<Drink> drinkPage = drinkService.findPaginated(PageRequest.of(currentPage-1,pageSize5));

        model.addAttribute("drinkPage", drinkPage);

        int totalPages = drinkPage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "subSites/showAllDrinks2";
    }

    @GetMapping(value = "/showAllDrinks3")
    public ModelAndView getShowAllDrinks3(
            Model model, @RequestParam("page")Optional<Integer>page, @RequestParam("size") Optional<Integer>size) {
        var modelAndView = new ModelAndView("subSites/showAllDrinks3");
        int currentPage = page.orElse(1);
        int pageSize5 = size.orElse(5);
        Page<Drink> drinkPage = drinkService.findPaginated(PageRequest.of(currentPage-1,pageSize5));
        int evalPageSize = size.orElse(INITIAL_PAGE_SIZE);
        // If a requested parameter is null or less than 1,
        // return the initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = page.filter(p -> p >= 1)
                .map(p -> p - 1)
                .orElse(INITIAL_PAGE);
        model.addAttribute("drinkPage", drinkPage);

        int totalPages = drinkPage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        var persons = drinkService.findPaginated(PageRequest.of(evalPage, evalPageSize));
        var pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("persons", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }


    // TODO Paginacja i ostylizowanie jej  https://frontbackend.com/thymeleaf/spring-boot-bootstrap-thymeleaf-pagination-jpa-liquibase-h2
    // TODO Ostylizowanie wyniku Search
    // TODO Próba podłączenia przyciksów filtracyjnych do filtrowania

}
