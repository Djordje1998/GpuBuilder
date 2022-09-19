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
@Table(name = "stresstest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StressTestEntity.findAll", query = "SELECT s FROM StressTestEntity s")
    , @NamedQuery(name = "StressTestEntity.findByStressTestID", query = "SELECT s FROM StressTestEntity s WHERE s.stressTestID = :stressTestID")
    , @NamedQuery(name = "StressTestEntity.findByNazivTesta", query = "SELECT s FROM StressTestEntity s WHERE s.nazivTesta = :nazivTesta")
    , @NamedQuery(name = "StressTestEntity.findByOpisTesta", query = "SELECT s FROM StressTestEntity s WHERE s.opisTesta = :opisTesta")})
public class StressTestEntity implements EntityModel {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "StressTestID")
    private Long stressTestID;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NazivTesta")
    private String nazivTesta;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OpisTesta")
    private String opisTesta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stressTestID")
    private List<BenchmarkEntity> benchmarkEntityList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stressTestEntity")
    private List<GradeEntity> gradeEntityList;

    public StressTestEntity() {
    }

    public StressTestEntity(Long stressTestID) {
        this.stressTestID = stressTestID;
    }

    public StressTestEntity(Long stressTestID, String nazivTesta, String opisTesta) {
        this.stressTestID = stressTestID;
        this.nazivTesta = nazivTesta;
        this.opisTesta = opisTesta;
    }

    public Long getStressTestID() {
        return stressTestID;
    }

    public void setStressTestID(Long stressTestID) {
        this.stressTestID = stressTestID;
    }

    public String getNazivTesta() {
        return nazivTesta;
    }

    public void setNazivTesta(String nazivTesta) {
        this.nazivTesta = nazivTesta;
    }

    public String getOpisTesta() {
        return opisTesta;
    }

    public void setOpisTesta(String opisTesta) {
        this.opisTesta = opisTesta;
    }

    @XmlTransient
    public List<BenchmarkEntity> getBenchmarkEntityList() {
        return benchmarkEntityList;
    }

    public void setBenchmarkEntityList(List<BenchmarkEntity> benchmarkEntityList) {
        this.benchmarkEntityList = benchmarkEntityList;
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
        hash += (stressTestID != null ? stressTestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StressTestEntity)) {
            return false;
        }
        StressTestEntity other = (StressTestEntity) object;
        if ((this.stressTestID == null && other.stressTestID != null) || (this.stressTestID != null && !this.stressTestID.equals(other.stressTestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.StressTestEntity[ stressTestID=" + stressTestID + " ]";
    }
    
}
