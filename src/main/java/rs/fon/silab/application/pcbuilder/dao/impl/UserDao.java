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
import rs.fon.silab.application.pcbuilder.model.impl.UserEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class UserDao implements Dao<UserEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserEntity save(UserEntity user) throws Exception {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        return entityManager.createQuery("SELECT k FROM UserEntity k ORDER BY k.id", UserEntity.class).getResultList();
    }

    @Override
    public void delete(UserEntity entity) throws Exception {
        entityManager.remove(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) throws Exception {
        return entityManager.merge(entity);
    }

    @Override
    public UserEntity find(UserEntity entity) throws Exception {
        if (entity.getUsername() != null) {
            return entityManager.
                    createQuery("SELECT u FROM UserEntity u WHERE u.username = :ki", UserEntity.class)
                    .setParameter("ki", entity.getUsername()).getSingleResult();
        }

        if (entity.getId() != null) {
            return entityManager.
                    createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class)
                    .setParameter("id", entity.getId()).getSingleResult();
        }
        throw new Exception("Error finding user!");
    }

    @Override
    public List<UserEntity> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
