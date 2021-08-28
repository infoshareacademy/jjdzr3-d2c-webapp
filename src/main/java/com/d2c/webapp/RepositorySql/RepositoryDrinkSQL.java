package com.d2c.webapp.RepositorySql;


import com.d2c.webapp.Entities.DrinkEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RepositoryDrinkSQL {

    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(RepositoryDrinkSQL.class);

    public void save(DrinkEntity drinkEntity) {
        LOGGER.info(drinkEntity);
        entityManager.persist(drinkEntity);

    }


}
