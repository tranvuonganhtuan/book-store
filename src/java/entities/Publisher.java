/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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

/**
 *
 * @author SE151470 Tran Vuong Anh Tuan
 */
@Entity
@Table(name = "Publisher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publisher.findAll", query = "SELECT p FROM Publisher p")
    , @NamedQuery(name = "Publisher.findByPublisherID", query = "SELECT p FROM Publisher p WHERE p.publisherID = :publisherID")
    , @NamedQuery(name = "Publisher.findByPublisherName", query = "SELECT p FROM Publisher p WHERE p.publisherName = :publisherName")
    , @NamedQuery(name = "Publisher.findByPublisherSummary", query = "SELECT p FROM Publisher p WHERE p.publisherSummary = :publisherSummary")})
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PublisherID")
    private Integer publisherID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PublisherName")
    private String publisherName;
    @Size(max = 1000)
    @Column(name = "PublisherSummary")
    private String publisherSummary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisherID")
    private List<Book> bookList;

    public Publisher() {
    }

    public Publisher(Integer publisherID) {
        this.publisherID = publisherID;
    }

    public Publisher(Integer publisherID, String publisherName) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
    }

    public Publisher(String publisherName, String summary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Integer publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherSummary() {
        return publisherSummary;
    }

    public void setPublisherSummary(String publisherSummary) {
        this.publisherSummary = publisherSummary;
    }

    @XmlTransient
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (publisherID != null ? publisherID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publisher)) {
            return false;
        }
        Publisher other = (Publisher) object;
        if ((this.publisherID == null && other.publisherID != null) || (this.publisherID != null && !this.publisherID.equals(other.publisherID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Publisher[ publisherID=" + publisherID + " ]";
    }
    
}
