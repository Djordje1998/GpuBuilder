/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import rs.fon.silab.application.pcbuilder.model.EntityModel;

/**
 *
 * @author LightPower
 */
@Entity
@Table(name = "ocena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradeEntity.findAll", query = "SELECT g FROM GradeEntity g")
    , @NamedQuery(name = "GradeEntity.findByKomponentaID", query = "SELECT g FROM GradeEntity g WHERE g.gradeEntityPK.komponentaID = :komponentaID")
    , @NamedQuery(name = "GradeEntity.findByStressTestID", query = "SELECT g FROM GradeEntity g WHERE g.gradeEntityPK.stressTestID = :stressTestID")
    , @NamedQuery(name = "GradeEntity.findByVrednostOcene", query = "SELECT g FROM GradeEntity g WHERE g.vrednostOcene = :vrednostOcene")})
public class GradeEntity implements EntityModel {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected GradeEntityPK gradeEntityPK;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VrednostOcene")
    private BigDecimal vrednostOcene;
    
    @JoinColumn(name = "KomponentaID", referencedColumnName = "KomponentaID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ComponentEntity componentEntity;
    
    @JoinColumn(name = "StressTestID", referencedColumnName = "StressTestID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StressTestEntity stressTestEntity;

    public GradeEntity() {
    }

    public GradeEntity(GradeEntityPK gradeEntityPK) {
        this.gradeEntityPK = gradeEntityPK;
    }

    public GradeEntity(GradeEntityPK gradeEntityPK, BigDecimal vrednostOcene) {
        this.gradeEntityPK = gradeEntityPK;
        this.vrednostOcene = vrednostOcene;
    }

    public GradeEntity(long komponentaID, long stressTestID) {
        this.gradeEntityPK = new GradeEntityPK(komponentaID, stressTestID);
    }

    public GradeEntityPK getGradeEntityPK() {
        return gradeEntityPK;
    }

    public void setGradeEntityPK(GradeEntityPK gradeEntityPK) {
        this.gradeEntityPK = gradeEntityPK;
    }

    public BigDecimal getVrednostOcene() {
        return vrednostOcene;
    }

    public void setVrednostOcene(BigDecimal vrednostOcene) {
        this.vrednostOcene = vrednostOcene;
    }

    public ComponentEntity getComponentEntity() {
        return componentEntity;
    }

    public void setComponentEntity(ComponentEntity componentEntity) {
        this.componentEntity = componentEntity;
    }

    public StressTestEntity getStressTestEntity() {
        return stressTestEntity;
    }

    public void setStressTestEntity(StressTestEntity stressTestEntity) {
        this.stressTestEntity = stressTestEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradeEntityPK != null ? gradeEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradeEntity)) {
            return false;
        }
        GradeEntity other = (GradeEntity) object;
        if ((this.gradeEntityPK == null && other.gradeEntityPK != null) || (this.gradeEntityPK != null && !this.gradeEntityPK.equals(other.gradeEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.GradeEntity[ gradeEntityPK=" + gradeEntityPK + " ]";
    }
    
}
