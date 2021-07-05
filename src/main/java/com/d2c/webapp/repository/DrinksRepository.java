package com.d2c.webapp.repository;

import com.infoshareademy.domain.Drink;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends PagingAndSortingRepository<Drink, Long> {
}
