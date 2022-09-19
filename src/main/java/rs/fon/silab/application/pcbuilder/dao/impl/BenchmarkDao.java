/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.model.impl.BenchmarkEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class BenchmarkDao implements Dao<BenchmarkEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BenchmarkEntity save(BenchmarkEntity entity) throws Exception {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public List<BenchmarkEntity> getAll() throws Exception {
        return entityManager.createQuery("select b from BenchmarkEntity b", BenchmarkEntity.class).getResultList();
    }

    @Override
    public void delete(BenchmarkEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public BenchmarkEntity update(BenchmarkEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public BenchmarkEntity find(BenchmarkEntity entity) throws Exception {
        return entityManager.find(BenchmarkEntity.class, entity.getBenchmarkID());
    }

    @Override
    public List<BenchmarkEntity> search(String find) throws Exception {
        String sql = "SELECT * FROM benchmark WHERE RacunarID1 = " + find + " OR RacunarID2 =  " + find;
        return entityManager.createNativeQuery(sql, BenchmarkEntity.class).getResultList();
    }

}
