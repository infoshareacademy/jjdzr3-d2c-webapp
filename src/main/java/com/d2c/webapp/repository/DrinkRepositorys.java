package com.d2c.webapp.repository;

import com.infoshareademy.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepositorys extends JpaRepository<Drink, Long> {

// // >
}
