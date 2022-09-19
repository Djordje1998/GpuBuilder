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
import rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class ComputerItemDao implements Dao<ComputerItemEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ComputerItemEntity save(ComputerItemEntity computerItem) throws Exception {
        entityManager.persist(computerItem);
        entityManager.flush();
        return computerItem;
    }

    @Override
    public List<ComputerItemEntity> getAll() throws Exception {
        return entityManager.createQuery("select i from ComputerItemEntity i order by i.computerItemEntityPK.racunarID,i.computerItemEntityPK.racunarID", ComputerItemEntity.class).getResultList();
    }

    @Override
    public void delete(ComputerItemEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public ComputerItemEntity update(ComputerItemEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public ComputerItemEntity find(ComputerItemEntity entity) throws Exception {
        return entityManager.find(ComputerItemEntity.class, entity.getComputerItemEntityPK());
    }

    @Override
    public List<ComputerItemEntity> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
