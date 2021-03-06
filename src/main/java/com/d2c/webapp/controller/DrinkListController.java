package com.d2c.webapp.controller;

import com.d2c.webapp.domain.Pager;
import com.d2c.webapp.service.DrinkService;
import com.infoshareademy.Filter;
import com.infoshareademy.domain.Category;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.GlassType;
import com.infoshareademy.domain.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private static final Logger LOGGER = LogManager.getLogger(DrinkListController.class);

    @GetMapping(value = "/showAllDrinks")
    public ModelAndView getShowAllDrinks(
            Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        var modelAndView = new ModelAndView("subSites/showAllDrinks");
        LOGGER.info("Getting list of all drinks");
        drinkService.findAll();
        drinkService.getDrinkListfromDB();
        int currentPage = page.orElse(1);
        int pageSize5 = size.orElse(5);
        GetDrinkPage(model, page, size, drinkService, modelAndView, currentPage, pageSize5);
        return modelAndView;
    }

    @RequestMapping("d2c/search")
    public ModelAndView getSearch(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                                  @RequestParam(name = "input", required = false) String item,
                                  @RequestParam(name = "type", required = false) Type type,
                                  @RequestParam(name = "glassType", required = false) GlassType glassType,
                                  @RequestParam(name = "category", required = false) Category category
    ) {
        LOGGER.info("input: '{}', type: '{}', glassType: '{}', category: '{}'", item, type, glassType, category);

        var modelAndView = new ModelAndView();
        int currentPage = page.orElse(1);
        int pageSize5 = size.orElse(5);
        List<Drink> drinks = drinkService.getDrinkListfromDB();
        if (item != null) {
            drinks = drinkService.searchItemsForQuery(item);
        }
        if (type != null) {
            drinks = Filter.filterByType(drinks, type);
        }
        if (glassType != null) {
            drinks = Filter.filterByGlassType(drinks, glassType);
        }
        if (category != null) {
            drinks = Filter.filterByCategory(drinks, category);
        }
        if (drinks.isEmpty()) {
            modelAndView.setViewName("managements/noResult");
            modelAndView.addObject("noDrinksFound", "No drinks found for given criteria: input too short or no drink available");

        } else {
            modelAndView.setViewName("search");
            model.addAttribute("listOfDrinks", drinks);
            drinkService.setDrinkList(drinks);
            GetDrinkPage(model, page, size, drinkService, modelAndView, currentPage, pageSize5);
            modelAndView.addObject("type", type);
            modelAndView.addObject("glassType", glassType);
            modelAndView.addObject("category", category);

        }
        return modelAndView;
    }

    private void GetDrinkPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, DrinkService drinkService, ModelAndView modelAndView, int currentPage, int pageSize5) {
        Page<Drink> drinkPage = drinkService.findPaginated(PageRequest.of(currentPage - 1, pageSize5));
        int evalPageSize = size.orElse(INITIAL_PAGE_SIZE);
        int evalPage = page.filter(p -> p >= 1)
                .map(p -> p - 1)
                .orElse(INITIAL_PAGE);
        model.addAttribute("drinkPage", drinkPage);
        int totalPages = drinkPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        var persons = drinkService.findPaginated(PageRequest.of(evalPage, evalPageSize));
        var pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        modelAndView.addObject("persons", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
    }
}
