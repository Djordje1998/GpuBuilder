/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import rs.fon.silab.application.pcbuilder.model.EntityModel;

/**
 *
 * @author LightPower
 */
@Entity
@Table(name = "user_profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserProfileEntity.findAll", query = "SELECT u FROM UserProfileEntity u")
    , @NamedQuery(name = "UserProfileEntity.findById", query = "SELECT u FROM UserProfileEntity u WHERE u.id = :id")
    , @NamedQuery(name = "UserProfileEntity.findByProfileName", query = "SELECT u FROM UserProfileEntity u WHERE u.profileName = :profileName")})
public class UserProfileEntity implements EntityModel {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "profile_name")
    private String profileName;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profile")
    private List<UserEntity> userEntityList;

    public UserProfileEntity() {
    }

    public UserProfileEntity(Long id) {
        this.id = id;
    }

    public UserProfileEntity(Long id, String profileName) {
        this.id = id;
        this.profileName = profileName;
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

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @XmlTransient
    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProfileEntity)) {
            return false;
        }
        UserProfileEntity other = (UserProfileEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.UserProfileEntity[ id=" + id + " ]";
    }
    
}
