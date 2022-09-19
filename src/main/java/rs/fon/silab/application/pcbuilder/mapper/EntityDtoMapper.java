/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.mapper;

import rs.fon.silab.application.pcbuilder.dto.Dto;
import rs.fon.silab.application.pcbuilder.model.EntityModel;
import rs.fon.silab.application.pcbuilder.model.impl.ComputerEntity;

/**
 *
 * @author korisnik
 */
public interface EntityDtoMapper<D extends Dto, E extends EntityModel> {

    D toDto(E entity);

    E toEntity(D dto);

}
