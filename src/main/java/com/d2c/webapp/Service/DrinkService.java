package com.d2c.webapp.Service;


import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DrinkService {

    private List<Drink> drinkList;

    public List<Drink> getDrinkList() {
        this.drinkList = new ArrayList<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        drinkList.addAll(drinks);
        return drinkList;
    }


    public Page<Drink> findPaginated(Pageable pageable) {
        this.drinkList = getDrinkList();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Drink>list;

        if (drinkList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, drinkList.size());
            list = drinkList.subList(startItem, toIndex);
        }
        Page<Drink> drinkPage =
                new PageImpl<Drink>(list, PageRequest.of(currentPage, pageSize), drinkList.size());


        return drinkPage;
    }


}
