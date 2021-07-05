package com.d2c.webapp.Service.impl;



import com.d2c.webapp.Service.DrinksService;

import com.d2c.webapp.repository.DrinksRepository;
import com.infoshareademy.domain.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DrinksServiceImpl implements DrinksService {;

    private final DrinksRepository drinksRepository;

    public DrinksServiceImpl(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    @Override
    public Page<Drink> findAllPageable(Pageable pageable) {
        return drinksRepository.findAll(pageable);
    }
}