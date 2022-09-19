/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dao;

import java.util.List;
import rs.fon.silab.application.pcbuilder.model.EntityModel;

/**
 *
 * @author LightPower
 */
public interface Dao<E extends EntityModel> {

    E save(E entity) throws Exception;

    List<E> getAll() throws Exception;

    void delete(E entity) throws Exception;

    E update(E entity) throws Exception;

    E find(E entity) throws Exception;
    
    List<E> search(String find) throws Exception;
}
