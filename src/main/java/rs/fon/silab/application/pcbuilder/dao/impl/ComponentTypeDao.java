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
import rs.fon.silab.application.pcbuilder.model.impl.ComponentTypeEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class ComponentTypeDao implements Dao<ComponentTypeEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ComponentTypeEntity save(ComponentTypeEntity type) throws Exception {
        entityManager.persist(type);
        entityManager.flush();
        return type;
    }

    @Override
    public List<ComponentTypeEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT tk FROM ComponentTypeEntity tk ORDER BY tk.tipKomponenteID", ComponentTypeEntity.class).getResultList();
    }

    @Override
    public void delete(ComponentTypeEntity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComponentTypeEntity update(ComponentTypeEntity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComponentTypeEntity find(ComponentTypeEntity entity) throws Exception {
        return entityManager.createQuery("SELECT ct FROM ComponentTypeEntity ct WHERE ct.tipKomponenteID =: id", ComponentTypeEntity.class)
                .setParameter("id", entity.getTipKomponenteID()).getSingleResult();

    }

    @Override
    public List<ComponentTypeEntity> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
