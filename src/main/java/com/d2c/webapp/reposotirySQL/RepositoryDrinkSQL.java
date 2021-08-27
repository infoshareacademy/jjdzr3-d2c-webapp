package com.d2c.webapp.reposotirySQL;


import com.d2c.webapp.entities.DrinkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositoryDrinkSQL {

    @Autowired
    private EntityManager entityManager;

    public void save(DrinkEntity drinkEntity) {
        entityManager.persist(drinkEntity);

    }


}
