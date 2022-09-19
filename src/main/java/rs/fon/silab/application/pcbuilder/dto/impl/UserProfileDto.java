/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.dto.impl;

import java.util.Objects;
import rs.fon.silab.application.pcbuilder.dto.Dto;

/**
 *
 * @author LightPower
 */
public class UserProfileDto implements Dto {

    Long id;
    String profileName;

    public UserProfileDto() {
    }

    public UserProfileDto(Long id, String profileType) {
        this.id = id;
        this.profileName = profileType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileType) {
        this.profileName = profileType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.profileName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserProfileDto other = (UserProfileDto) obj;
        if (!Objects.equals(this.profileName, other.profileName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserProfileDto{" + "id=" + id + ", profileName=" + profileName + '}';
    }

}
