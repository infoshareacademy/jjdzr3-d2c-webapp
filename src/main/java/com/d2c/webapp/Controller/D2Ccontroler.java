package com.d2c.webapp.Controller;

import com.d2c.webapp.mapper.DrinkMapper;
import com.d2c.webapp.repository.DrinkRepositorys;
import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.Search;
import com.infoshareademy.data.DrinkDAO;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/d2c")
public class D2Ccontroler {

    private static Logger logger = LoggerFactory.getLogger(D2Ccontroler.class);
    private final DrinkRepositorys drinkRepositorys;
    private final DrinkMapper drinkMapper;

    public D2Ccontroler(DrinkRepositorys drinkRepositorys, DrinkMapper drinkMapper) {
        this.drinkRepositorys = drinkRepositorys;
        this.drinkMapper = drinkMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<DrinkDAO> getProduct(@PathVariable Long id) {
        Optional<Drink> opt = drinkRepositorys.findById(id);
        if (opt.isEmpty()) {
            logger.info("Not Found");
            return ResponseEntity.notFound().build();
        }
        DrinkDAO dto = DrinkMapper.toDao(opt.get());
        logger.info(dto.toString());

        return ResponseEntity.ok(dto);
    }


    @GetMapping("/menu")
    @ResponseBody
    public String getMenu() {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = (DrinkRepository) drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        Menu menu = new Menu();
        menu.menu(drinkParser, (com.infoshareademy.domain.DrinkRepository) drinkRepository, drinks);
        return "Menu";
    }

    @GetMapping("/search")
    @ResponseBody
    public String getSearch() {
        Search search;
        return "Search";
    }

    @GetMapping("/filters")
    @ResponseBody
    public String getFilters() {
        Filter filter;
        return "filters";
    }
}
