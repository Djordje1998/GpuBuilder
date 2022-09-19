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
import rs.fon.silab.application.pcbuilder.model.impl.UserProfileEntity;

/**
 *
 * @author LightPower
 */
@Repository
public class UserProfileDao implements Dao<UserProfileEntity> {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public UserProfileEntity save(UserProfileEntity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<UserProfileEntity> getAll() throws Exception {
        return entityManager.createQuery("select upe from UserProfileEntity upe order by upe.id", UserProfileEntity.class).getResultList();
    }
    
    @Override
    public void delete(UserProfileEntity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public UserProfileEntity update(UserProfileEntity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public UserProfileEntity find(UserProfileEntity entity) throws Exception {
        return entityManager.find(UserProfileEntity.class, entity.getId());
    }

    @Override
    public List<UserProfileEntity> search(String find) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
