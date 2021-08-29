package com.d2c.webapp.reposotirySQL;


import com.d2c.webapp.entities.DrinkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RepositoryDrinkSQL {

    @Autowired
    private EntityManager entityManager;

    public void save(DrinkEntity drinkEntity) {
        entityManager.persist(drinkEntity);

    }
  //  @SuppressWarnings("unchecked")
    public List<DrinkEntity> findAll(){
        final Query query = entityManager.createQuery("SELECT o FROM DrinkEntity o ", DrinkEntity.class);
        return query.getResultList();
    }

    public void delete(DrinkEntity drinkEntity){
        entityManager.remove(drinkEntity);
    }

}
