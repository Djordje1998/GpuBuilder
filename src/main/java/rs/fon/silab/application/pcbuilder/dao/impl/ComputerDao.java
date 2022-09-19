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
import rs.fon.silab.application.pcbuilder.model.impl.ComputerEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class ComputerDao implements Dao<ComputerEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ComputerEntity save(ComputerEntity computer) throws Exception {
        entityManager.persist(computer);
        entityManager.flush();
        return computer;
    }

    @Override
    public List<ComputerEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT c FROM ComputerEntity c ORDER BY c.racunarID", ComputerEntity.class).getResultList();
    }

    @Override
    public void delete(ComputerEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public ComputerEntity update(ComputerEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public ComputerEntity find(ComputerEntity entity) throws Exception {
        return entityManager.find(ComputerEntity.class, entity.getRacunarID());
    }

    @Override
    public List<ComputerEntity> search(String find) throws Exception {
        String sql = "SELECT * FROM racunar WHERE NazivRacunara LIKE '%" + find + "%'";
        return entityManager.createNativeQuery(sql, ComputerEntity.class).getResultList();
    }

}
