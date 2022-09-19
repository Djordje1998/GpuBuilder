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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "benchmark")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BenchmarkEntity.findAll", query = "SELECT b FROM BenchmarkEntity b")
    , @NamedQuery(name = "BenchmarkEntity.findByBenchmarkID", query = "SELECT b FROM BenchmarkEntity b WHERE b.benchmarkID = :benchmarkID")
    , @NamedQuery(name = "BenchmarkEntity.findByDatumBenchmarka", query = "SELECT b FROM BenchmarkEntity b WHERE b.datumBenchmarka = :datumBenchmarka")
    , @NamedQuery(name = "BenchmarkEntity.findByBodoviPrvogRacunara", query = "SELECT b FROM BenchmarkEntity b WHERE b.bodoviPrvogRacunara = :bodoviPrvogRacunara")
    , @NamedQuery(name = "BenchmarkEntity.findByBodoviDrugogRacunara", query = "SELECT b FROM BenchmarkEntity b WHERE b.bodoviDrugogRacunara = :bodoviDrugogRacunara")})
public class BenchmarkEntity implements EntityModel{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BenchmarkID")
    private Long benchmarkID;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumBenchmarka")
    @Temporal(TemporalType.DATE)
    private Date datumBenchmarka;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BodoviPrvogRacunara")
    private BigDecimal bodoviPrvogRacunara;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "BodoviDrugogRacunara")
    private BigDecimal bodoviDrugogRacunara;
    
    @JoinColumn(name = "RacunarID1", referencedColumnName = "RacunarID")
    @ManyToOne(optional = false)
    private ComputerEntity racunarID1;
    
    @JoinColumn(name = "RacunarID2", referencedColumnName = "RacunarID")
    @ManyToOne(optional = false)
    private ComputerEntity racunarID2;
    
    @JoinColumn(name = "StressTestID", referencedColumnName = "StressTestID")
    @ManyToOne(optional = false)
    private StressTestEntity stressTestID;

    public BenchmarkEntity() {
    }

    public BenchmarkEntity(Long benchmarkID) {
        this.benchmarkID = benchmarkID;
    }

    public BenchmarkEntity(Long benchmarkID, Date datumBenchmarka, BigDecimal bodoviPrvogRacunara, BigDecimal bodoviDrugogRacunara) {
        this.benchmarkID = benchmarkID;
        this.datumBenchmarka = datumBenchmarka;
        this.bodoviPrvogRacunara = bodoviPrvogRacunara;
        this.bodoviDrugogRacunara = bodoviDrugogRacunara;
    }

    public Long getBenchmarkID() {
        return benchmarkID;
    }

    public void setBenchmarkID(Long benchmarkID) {
        this.benchmarkID = benchmarkID;
    }

    public Date getDatumBenchmarka() {
        return datumBenchmarka;
    }

    public void setDatumBenchmarka(Date datumBenchmarka) {
        this.datumBenchmarka = datumBenchmarka;
    }

    public BigDecimal getBodoviPrvogRacunara() {
        return bodoviPrvogRacunara;
    }

    public void setBodoviPrvogRacunara(BigDecimal bodoviPrvogRacunara) {
        this.bodoviPrvogRacunara = bodoviPrvogRacunara;
    }

    public BigDecimal getBodoviDrugogRacunara() {
        return bodoviDrugogRacunara;
    }

    public void setBodoviDrugogRacunara(BigDecimal bodoviDrugogRacunara) {
        this.bodoviDrugogRacunara = bodoviDrugogRacunara;
    }

    public ComputerEntity getRacunarID1() {
        return racunarID1;
    }

    public void setRacunarID1(ComputerEntity racunarID1) {
        this.racunarID1 = racunarID1;
    }

    public ComputerEntity getRacunarID2() {
        return racunarID2;
    }

    public void setRacunarID2(ComputerEntity racunarID2) {
        this.racunarID2 = racunarID2;
    }

    public StressTestEntity getStressTestID() {
        return stressTestID;
    }

    public void setStressTestID(StressTestEntity stressTestID) {
        this.stressTestID = stressTestID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (benchmarkID != null ? benchmarkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenchmarkEntity)) {
            return false;
        }
        BenchmarkEntity other = (BenchmarkEntity) object;
        if ((this.benchmarkID == null && other.benchmarkID != null) || (this.benchmarkID != null && !this.benchmarkID.equals(other.benchmarkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.fon.silab.application.pcbuilder.model.impl.BenchmarkEntity[ benchmarkID=" + benchmarkID + " ]";
    }
    
}
