/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "komponente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponentEntity.findAll", query = "SELECT c FROM ComponentEntity c")
    , @NamedQuery(name = "ComponentEntity.findByKomponentaID", query = "SELECT c FROM ComponentEntity c WHERE c.komponentaID = :komponentaID")
    , @NamedQuery(name = "ComponentEntity.findByNazivKomponente", query = "SELECT c FROM ComponentEntity c WHERE c.nazivKomponente = :nazivKomponente")
    , @NamedQuery(name = "ComponentEntity.findByTakt", query = "SELECT c FROM ComponentEntity c WHERE c.takt = :takt")
    , @NamedQuery(name = "ComponentEntity.findByOpis", query = "SELECT c FROM ComponentEntity c WHERE c.opis = :opis")
    , @NamedQuery(name = "ComponentEntity.findByCena", query = "SELECT c FROM ComponentEntity c WHERE c.cena = :cena")})
public class ComponentEntity implements EntityModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KomponentaID")
    private Long komponentaID;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NazivKomponente")
    private String nazivKomponente;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Takt")
    private BigDecimal takt;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Opis")
    private String opis;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private BigDecimal cena;

    @JoinColumn(name = "TipKomponenteID", referencedColumnName = "TipKomponenteID")
    @ManyToOne(optional = false)
    private ComponentTypeEntity componentTypeEntity;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "componentEntity")
    private List<ComputerItemEntity> computerItemEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentEntity")
    private List<GradeEntity> gradeEntityList;

    public ComponentEntity() {
    }

    public ComponentEntity(Long komponentaID) {
        this.komponentaID = komponentaID;
    }

    public ComponentEntity(Long komponentaID, String nazivKomponente, BigDecimal takt, String opis, BigDecimal cena, ComponentTypeEntity componentTypeEntity) {
        this.komponentaID = komponentaID;
        this.nazivKomponente = nazivKomponente;
        this.takt = takt;
        this.opis = opis;
        this.cena = cena;
        this.componentTypeEntity = componentTypeEntity;
    }

    public Long getKomponentaID() {
        return komponentaID;
    }

    public void setKomponentaID(Long komponentaID) {
        this.komponentaID = komponentaID;
    }

    public String getNazivKomponente() {
        return nazivKomponente;
    }

    public void setNazivKomponente(String nazivKomponente) {
        this.nazivKomponente = nazivKomponente;
    }

    public BigDecimal getTakt() {
        return takt;
    }

    public void setTakt(BigDecimal takt) {
        this.takt = takt;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public ComponentTypeEntity getComponentTypeEntity() {
        return componentTypeEntity;
    }

    public void setComponentTypeEntity(ComponentTypeEntity componentTypeEntity) {
        this.componentTypeEntity = componentTypeEntity;
    }

    @XmlTransient
    public List<ComputerItemEntity> getComputerItemEntityList() {
        return computerItemEntityList;
    }

    public void setComputerItemEntityList(List<ComputerItemEntity> computerItemEntityList) {
        this.computerItemEntityList = computerItemEntityList;
    }

    @XmlTransient
    public List<GradeEntity> getGradeEntityList() {
        return gradeEntityList;
    }

    public void setGradeEntityList(List<GradeEntity> gradeEntityList) {
        this.gradeEntityList = gradeEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (komponentaID != null ? komponentaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComponentEntity)) {
            return false;
        }
        ComponentEntity other = (ComponentEntity) object;
        if ((this.komponentaID == null && other.komponentaID != null) || (this.komponentaID != null && !this.komponentaID.equals(other.komponentaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.ComponentEntity[ komponentaID=" + komponentaID + " ]";
    }

}
