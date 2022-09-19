/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.service;

import java.util.List;
import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public interface ServiceOperations<D extends Dto> {

    D save(D dto) throws Exception;

    List<D> getAll() throws Exception;

    void delete(D dto) throws Exception;

    D update(D dto) throws Exception;

    D find(D dto) throws Exception;
    
    List<D> search(String find) throws Exception;
    
}
