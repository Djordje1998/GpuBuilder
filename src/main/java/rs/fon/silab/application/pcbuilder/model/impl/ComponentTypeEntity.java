/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.io.Serializable;
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
@Table(name = "tipkomponente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponentTypeEntity.findAll", query = "SELECT c FROM ComponentTypeEntity c")
    , @NamedQuery(name = "ComponentTypeEntity.findByTipKomponenteID", query = "SELECT c FROM ComponentTypeEntity c WHERE c.tipKomponenteID = :tipKomponenteID")
    , @NamedQuery(name = "ComponentTypeEntity.findByNazivTipa", query = "SELECT c FROM ComponentTypeEntity c WHERE c.nazivTipa = :nazivTipa")})
public class ComponentTypeEntity implements EntityModel {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TipKomponenteID")
    private Long tipKomponenteID;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NazivTipa")
    private String nazivTipa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentTypeEntity")
    private List<ComponentEntity> componentEntityList;

    public ComponentTypeEntity() {
    }

    public ComponentTypeEntity(Long tipKomponenteID) {
        this.tipKomponenteID = tipKomponenteID;
    }

    public ComponentTypeEntity(Long tipKomponenteID, String nazivTipa) {
        this.tipKomponenteID = tipKomponenteID;
        this.nazivTipa = nazivTipa;
    }

    public Long getTipKomponenteID() {
        return tipKomponenteID;
    }

    public void setTipKomponenteID(Long tipKomponenteID) {
        this.tipKomponenteID = tipKomponenteID;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    @XmlTransient
    public List<ComponentEntity> getComponentEntityList() {
        return componentEntityList;
    }

    public void setComponentEntityList(List<ComponentEntity> componentEntityList) {
        this.componentEntityList = componentEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipKomponenteID != null ? tipKomponenteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponentTypeEntity)) {
            return false;
        }
        ComponentTypeEntity other = (ComponentTypeEntity) object;
        if ((this.tipKomponenteID == null && other.tipKomponenteID != null) || (this.tipKomponenteID != null && !this.tipKomponenteID.equals(other.tipKomponenteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.ComponentTypeEntity[ tipKomponenteID=" + tipKomponenteID + " ]";
    }
    
}
