/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import rs.fon.silab.application.pcbuilder.model.EntityModel;

/**
 *
 * @author LightPower
 */
@Entity
@Table(name = "stavkeracunara")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComputerItemEntity.findAll", query = "SELECT c FROM ComputerItemEntity c")
    , @NamedQuery(name = "ComputerItemEntity.findByRacunarID", query = "SELECT c FROM ComputerItemEntity c WHERE c.computerItemEntityPK.racunarID = :racunarID")
    , @NamedQuery(name = "ComputerItemEntity.findByRb", query = "SELECT c FROM ComputerItemEntity c WHERE c.computerItemEntityPK.rb = :rb")
    , @NamedQuery(name = "ComputerItemEntity.findByCenaKomada", query = "SELECT c FROM ComputerItemEntity c WHERE c.cenaKomada = :cenaKomada")
    , @NamedQuery(name = "ComputerItemEntity.findByKolicina", query = "SELECT c FROM ComputerItemEntity c WHERE c.kolicina = :kolicina")
    , @NamedQuery(name = "ComputerItemEntity.findByUkupnaCena", query = "SELECT c FROM ComputerItemEntity c WHERE c.ukupnaCena = :ukupnaCena")
    , @NamedQuery(name = "ComputerItemEntity.findByDatumKreiranja", query = "SELECT c FROM ComputerItemEntity c WHERE c.datumKreiranja = :datumKreiranja")})
public class ComputerItemEntity implements EntityModel {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ComputerItemEntityPK computerItemEntityPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CenaKomada")
    private BigDecimal cenaKomada;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Kolicina")
    private int kolicina;

    @Basic(optional = false)
    @NotNull
    @Column(name = "UkupnaCena")
    private BigDecimal ukupnaCena;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumKreiranja")
    @Temporal(TemporalType.DATE)
    private Date datumKreiranja;

    @JoinColumn(name = "KomponentaID", referencedColumnName = "KomponentaID")
    @ManyToOne(optional = false)
    private ComponentEntity componentEntity;

    @JoinColumn(name = "Kreirao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity creator;

    @JoinColumn(name = "RacunarID", referencedColumnName = "RacunarID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ComputerEntity computerEntity;

    public ComputerItemEntity() {
    }

    public ComputerItemEntity(ComputerItemEntityPK computerItemEntityPK) {
        this.computerItemEntityPK = computerItemEntityPK;
    }

    public ComputerItemEntity(ComputerItemEntityPK computerItemEntityPK, BigDecimal cenaKomada, int kolicina, BigDecimal ukupnaCena, Date datumKreiranja) {
        this.computerItemEntityPK = computerItemEntityPK;
        this.cenaKomada = cenaKomada;
        this.kolicina = kolicina;
        this.ukupnaCena = ukupnaCena;
        this.datumKreiranja = datumKreiranja;
    }

    public ComputerItemEntity(long racunarID, long rb) {
        this.computerItemEntityPK = new ComputerItemEntityPK(racunarID, rb);
    }

    public ComputerItemEntityPK getComputerItemEntityPK() {
        return computerItemEntityPK;
    }

    public void setComputerItemEntityPK(ComputerItemEntityPK computerItemEntityPK) {
        this.computerItemEntityPK = computerItemEntityPK;
    }

    public BigDecimal getCenaKomada() {
        return cenaKomada;
    }

    public void setCenaKomada(BigDecimal cenaKomada) {
        this.cenaKomada = cenaKomada;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public ComponentEntity getComponentEntity() {
        return componentEntity;
    }

    public void setComponentEntity(ComponentEntity componentEntity) {
        this.componentEntity = componentEntity;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public ComputerEntity getComputerEntity() {
        return computerEntity;
    }

    public void setComputerEntity(ComputerEntity computerEntity) {
        this.computerEntity = computerEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (computerItemEntityPK != null ? computerItemEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputerItemEntity)) {
            return false;
        }
        ComputerItemEntity other = (ComputerItemEntity) object;
        if ((this.computerItemEntityPK == null && other.computerItemEntityPK != null) || (this.computerItemEntityPK != null && !this.computerItemEntityPK.equals(other.computerItemEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntity[ computerItemEntityPK=" + computerItemEntityPK + " ]";
    }

}
