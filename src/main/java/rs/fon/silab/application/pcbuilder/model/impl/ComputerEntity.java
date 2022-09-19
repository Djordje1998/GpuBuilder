/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "racunar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComputerEntity.findAll", query = "SELECT c FROM ComputerEntity c")
    , @NamedQuery(name = "ComputerEntity.findByRacunarID", query = "SELECT c FROM ComputerEntity c WHERE c.racunarID = :racunarID")
    , @NamedQuery(name = "ComputerEntity.findByNazivRacunara", query = "SELECT c FROM ComputerEntity c WHERE c.nazivRacunara = :nazivRacunara")
    , @NamedQuery(name = "ComputerEntity.findByNamena", query = "SELECT c FROM ComputerEntity c WHERE c.namena = :namena")
    , @NamedQuery(name = "ComputerEntity.findByGarancija", query = "SELECT c FROM ComputerEntity c WHERE c.garancija = :garancija")
    , @NamedQuery(name = "ComputerEntity.findByUkupnaCena", query = "SELECT c FROM ComputerEntity c WHERE c.ukupnaCena = :ukupnaCena")})
public class ComputerEntity implements EntityModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RacunarID")
    private Long racunarID;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NazivRacunara")
    private String nazivRacunara;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Namena")
    private String namena;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Garancija")
    @Temporal(TemporalType.DATE)
    private Date garancija;

    @Basic(optional = false)
    @NotNull
    @Column(name = "UkupnaCena")
    private BigDecimal ukupnaCena;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "computerEntity")
    private List<ComputerItemEntity> computerItemEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "racunarID1")
    private List<BenchmarkEntity> benchmarkEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "racunarID2")
    private List<BenchmarkEntity> benchmarkEntityList1;

    public ComputerEntity() {
    }

    public ComputerEntity(Long racunarID) {
        this.racunarID = racunarID;
    }

    public ComputerEntity(Long racunarID, String nazivRacunara, String namena, Date garancija, BigDecimal ukupnaCena) {
        this.racunarID = racunarID;
        this.nazivRacunara = nazivRacunara;
        this.namena = namena;
        this.garancija = garancija;
        this.ukupnaCena = ukupnaCena;
    }

    public Long getRacunarID() {
        return racunarID;
    }

    public void setRacunarID(Long racunarID) {
        this.racunarID = racunarID;
    }

    public String getNazivRacunara() {
        return nazivRacunara;
    }

    public void setNazivRacunara(String nazivRacunara) {
        this.nazivRacunara = nazivRacunara;
    }

    public String getNamena() {
        return namena;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Date getGarancija() {
        return garancija;
    }

    public void setGarancija(Date garancija) {
        this.garancija = garancija;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    @XmlTransient
    public List<ComputerItemEntity> getComputerItemEntityList() {
        return computerItemEntityList;
    }

    public void setComputerItemEntityList(List<ComputerItemEntity> computerItemEntityList) {
        this.computerItemEntityList = computerItemEntityList;
    }

    @XmlTransient
    public List<BenchmarkEntity> getBenchmarkEntityList() {
        return benchmarkEntityList;
    }

    public void setBenchmarkEntityList(List<BenchmarkEntity> benchmarkEntityList) {
        this.benchmarkEntityList = benchmarkEntityList;
    }

    @XmlTransient
    public List<BenchmarkEntity> getBenchmarkEntityList1() {
        return benchmarkEntityList1;
    }

    public void setBenchmarkEntityList1(List<BenchmarkEntity> benchmarkEntityList1) {
        this.benchmarkEntityList1 = benchmarkEntityList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (racunarID != null ? racunarID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputerEntity)) {
            return false;
        }
        ComputerEntity other = (ComputerEntity) object;
        if ((this.racunarID == null && other.racunarID != null) || (this.racunarID != null && !this.racunarID.equals(other.racunarID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.ComputerEntity[ racunarID=" + racunarID + " ]";
    }

}
