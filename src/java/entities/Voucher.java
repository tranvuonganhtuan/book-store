/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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

/**
 *
 * @author SE151470 Tran Vuong Anh Tuan
 */
@Entity
@Table(name = "Voucher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v")
    , @NamedQuery(name = "Voucher.findByVoucherID", query = "SELECT v FROM Voucher v WHERE v.voucherID = :voucherID")
    , @NamedQuery(name = "Voucher.findByVoucherName", query = "SELECT v FROM Voucher v WHERE v.voucherName = :voucherName")
    , @NamedQuery(name = "Voucher.findByVoucherValue", query = "SELECT v FROM Voucher v WHERE v.voucherValue = :voucherValue")
    , @NamedQuery(name = "Voucher.findByVoucherSummary", query = "SELECT v FROM Voucher v WHERE v.voucherSummary = :voucherSummary")
    , @NamedQuery(name = "Voucher.findByStartDate", query = "SELECT v FROM Voucher v WHERE v.startDate = :startDate")
    , @NamedQuery(name = "Voucher.findByEndDate", query = "SELECT v FROM Voucher v WHERE v.endDate = :endDate")})
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VoucherID")
    private Integer voucherID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "VoucherName")
    private String voucherName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VoucherValue")
    private int voucherValue;
    @Size(max = 1000)
    @Column(name = "VoucherSummary")
    private String voucherSummary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voucherID")
    private List<Payment> paymentList;

    public Voucher() {
    }

    public Voucher(Integer voucherID) {
        this.voucherID = voucherID;
    }

    public Voucher(Integer voucherID, String voucherName, int voucherValue, Date startDate, Date endDate) {
        this.voucherID = voucherID;
        this.voucherName = voucherName;
        this.voucherValue = voucherValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Voucher(String voucherName, String summary, int voucherValue, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Integer voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public int getVoucherValue() {
        return voucherValue;
    }

    public void setVoucherValue(int voucherValue) {
        this.voucherValue = voucherValue;
    }

    public String getVoucherSummary() {
        return voucherSummary;
    }

    public void setVoucherSummary(String voucherSummary) {
        this.voucherSummary = voucherSummary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlTransient
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voucherID != null ? voucherID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.voucherID == null && other.voucherID != null) || (this.voucherID != null && !this.voucherID.equals(other.voucherID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Voucher[ voucherID=" + voucherID + " ]";
    }
    
}
