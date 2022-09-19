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
import rs.fon.silab.application.pcbuilder.model.impl.GradeEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class GradeDao implements Dao<GradeEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public GradeEntity save(GradeEntity entity) throws Exception {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public List<GradeEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT g FROM GradeEntity g", GradeEntity.class).getResultList();
    }

    @Override
    public void delete(GradeEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public GradeEntity update(GradeEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public GradeEntity find(GradeEntity entity) throws Exception {
        return entityManager.find(GradeEntity.class, entity.getGradeEntityPK());
    }

    @Override
    public List<GradeEntity> search(String find) throws Exception {
        return entityManager.createQuery("SELECT g FROM GradeEntity g WHERE g.gradeEntityPK.komponentaID =: id"
                , GradeEntity.class).setParameter("id", Long.parseLong(find)).getResultList();
    }

}
