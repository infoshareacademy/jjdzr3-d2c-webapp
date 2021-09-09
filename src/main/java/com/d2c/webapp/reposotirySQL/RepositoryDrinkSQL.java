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

    @Transactional
    public void save(DrinkEntity drinkEntity) {
       entityManager.persist(drinkEntity);

    }

    @Transactional
    public void update(DrinkEntity drinkEntity){
        entityManager.merge(drinkEntity);
    }


    public List<DrinkEntity> findAll(){
        final Query query = entityManager.createQuery("SELECT o FROM DrinkEntity o ", DrinkEntity.class);
        return query.getResultList();
    }

    public List<DrinkEntity> findByName(String name){
        String test = "SELECT o FROM DrinkEntity o where o.drink_name= " + "'"+name+"'"  ;
        final Query query = entityManager.createQuery(test, DrinkEntity.class);
        return query.getResultList();
    }

    public List<DrinkEntity> findLast(){
        final Query query = entityManager.createQuery("SELECT  o FROM DrinkEntity o order by o.drinkid desc", DrinkEntity.class).setFirstResult(1).setMaxResults(1);
        return query.getResultList();
    }

    public void delete(DrinkEntity drinkEntity){
        this.entityManager.remove(drinkEntity);
    }

}
