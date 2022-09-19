/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.application.pcbuilder.model.impl;

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
public class ComputerItemEntityPK implements EntityModel {

    @Basic(optional = false)
    @Column(name = "RacunarID")
    private long racunarID;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "RB")
    private long rb;

    public ComputerItemEntityPK() {
    }

    public ComputerItemEntityPK(long racunarID, long rb) {
        this.racunarID = racunarID;
        this.rb = rb;
    }

    public long getRacunarID() {
        return racunarID;
    }

    public void setRacunarID(long racunarID) {
        this.racunarID = racunarID;
    }

    public long getRb() {
        return rb;
    }

    public void setRb(long rb) {
        this.rb = rb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) racunarID;
        hash += (int) rb;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputerItemEntityPK)) {
            return false;
        }
        ComputerItemEntityPK other = (ComputerItemEntityPK) object;
        if (this.racunarID != other.racunarID) {
            return false;
        }
        if (this.rb != other.rb) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.ComputerItemEntityPK[ racunarID=" + racunarID + ", rb=" + rb + " ]";
    }
    
}
