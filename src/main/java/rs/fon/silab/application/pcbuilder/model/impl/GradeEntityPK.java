/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import rs.fon.silab.application.pcbuilder.model.EntityModel;

/**
 *
 * @author LightPower
 */
@Embeddable
public class GradeEntityPK implements EntityModel {

    @Basic(optional = false)
    @NotNull
    @Column(name = "KomponentaID")
    private long komponentaID;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "StressTestID")
    private long stressTestID;

    public GradeEntityPK() {
    }

    public GradeEntityPK(long komponentaID, long stressTestID) {
        this.komponentaID = komponentaID;
        this.stressTestID = stressTestID;
    }

    public long getKomponentaID() {
        return komponentaID;
    }

    public void setKomponentaID(long komponentaID) {
        this.komponentaID = komponentaID;
    }

    public long getStressTestID() {
        return stressTestID;
    }

    public void setStressTestID(long stressTestID) {
        this.stressTestID = stressTestID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) komponentaID;
        hash += (int) stressTestID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradeEntityPK)) {
            return false;
        }
        GradeEntityPK other = (GradeEntityPK) object;
        if (this.komponentaID != other.komponentaID) {
            return false;
        }
        if (this.stressTestID != other.stressTestID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.GradeEntityPK[ komponentaID=" + komponentaID + ", stressTestID=" + stressTestID + " ]";
    }
    
}
