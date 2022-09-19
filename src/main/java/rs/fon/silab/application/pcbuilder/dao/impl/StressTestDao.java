/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.model.impl.StressTestEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class StressTestDao implements Dao<StressTestEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StressTestEntity save(StressTestEntity entity) throws Exception {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public List<StressTestEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT st FROM StressTestEntity st ORDER BY st.stressTestID", StressTestEntity.class).getResultList();
    }

    @Override
    public void delete(StressTestEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public StressTestEntity update(StressTestEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public StressTestEntity find(StressTestEntity entity) throws Exception {
        return entityManager.find(StressTestEntity.class, entity.getStressTestID());
    }

    @Override
    public List<StressTestEntity> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
