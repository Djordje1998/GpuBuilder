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
import rs.fon.silab.application.pcbuilder.model.impl.ComponentEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class ComponentDao implements Dao<ComponentEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ComponentEntity save(ComponentEntity component) throws Exception {
        entityManager.persist(component);
        entityManager.flush();
        return component;
    }

    @Override
    public List<ComponentEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT c FROM ComponentEntity c ORDER BY c.komponentaID",
                ComponentEntity.class).getResultList();

    }

    @Override
    public void delete(ComponentEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public ComponentEntity update(ComponentEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public ComponentEntity find(ComponentEntity entity) throws Exception {
        return entityManager.find(ComponentEntity.class, entity.getKomponentaID());
    }

    @Override
    public List<ComponentEntity> search(String find) throws Exception {
        String sql = "SELECT * FROM komponente WHERE NazivKomponente LIKE '%" + find + "%'";
        return entityManager.createNativeQuery(sql, ComponentEntity.class).getResultList();
    }

}
