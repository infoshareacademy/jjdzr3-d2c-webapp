package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
import com.infoshareademy.domain.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class DrinkListController {


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
        int lastVisablePageFromCurrentPage = page.get()+10;
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
    public String getShowAllDrinks3(
            Model model, @RequestParam("page")Optional<Integer>page, @RequestParam("size") Optional<Integer>size) {
        int currentPage = page.orElse(1);
       /* int lastVisablePageFromCurrentPage = page.get()+10;*/
        int pageSize1 = size.orElse(1);
        int sizePagination = 10;
        Page<Drink> drinkPage = drinkService.findPaginated(PageRequest.of(currentPage-1,pageSize1));

        model.addAttribute("drinkPage", drinkPage);

        int totalPages = drinkPage.getTotalPages();
        if (totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("customers", drinkService.findPaginated(PageRequest.of(currentPage, sizePagination)));
        return "subSites/showAllDrinks3";
    }


    // TODO Paginacja i ostylizowanie jej  https://frontbackend.com/thymeleaf/spring-boot-bootstrap-thymeleaf-pagination-jpa-liquibase-h2
    // TODO Ostylizowanie wyniku Search
    // TODO Próba podłączenia przyciksów filtracyjnych do filtrowania

}
