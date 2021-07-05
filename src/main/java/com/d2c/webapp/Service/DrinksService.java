package com.d2c.webapp.Service;



import com.infoshareademy.domain.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DrinksService {


    Page<Drink> findAllPageable (Pageable pageable);

}
