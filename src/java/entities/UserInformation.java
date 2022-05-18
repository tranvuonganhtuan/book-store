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
@Table(name = "UserInformation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserInformation.findAll", query = "SELECT u FROM UserInformation u")
    , @NamedQuery(name = "UserInformation.findByUserName", query = "SELECT u FROM UserInformation u WHERE u.userName = :userName")
    , @NamedQuery(name = "UserInformation.findByUserPassword", query = "SELECT u FROM UserInformation u WHERE u.userPassword = :userPassword")
    , @NamedQuery(name = "UserInformation.findByEmail", query = "SELECT u FROM UserInformation u WHERE u.email = :email")
    , @NamedQuery(name = "UserInformation.findByUserAddress", query = "SELECT u FROM UserInformation u WHERE u.userAddress = :userAddress")
    , @NamedQuery(name = "UserInformation.findByGender", query = "SELECT u FROM UserInformation u WHERE u.gender = :gender")
    , @NamedQuery(name = "UserInformation.findByPhoneNumber", query = "SELECT u FROM UserInformation u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "UserInformation.findByUserRole", query = "SELECT u FROM UserInformation u WHERE u.userRole = :userRole")
    , @NamedQuery(name = "UserInformation.findByFullName", query = "SELECT u FROM UserInformation u WHERE u.fullName = :fullName")
    , @NamedQuery(name = "UserInformation.findByDateOfBirth", query = "SELECT u FROM UserInformation u WHERE u.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "UserInformation.findByAge", query = "SELECT u FROM UserInformation u WHERE u.age = :age")
    , @NamedQuery(name = "UserInformation.findByUserImage", query = "SELECT u FROM UserInformation u WHERE u.userImage = :userImage")})
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "UserName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "UserPassword")
    private String userPassword;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "UserAddress")
    private String userAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Size(max = 20)
    @Column(name = "UserRole")
    private String userRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FullName")
    private String fullName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "Age")
    private Integer age;
    @Size(max = 30)
    @Column(name = "UserImage")
    private String userImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userName")
    private List<Cart> cartList;

    public UserInformation() {
    }

    public UserInformation(String userName) {
        this.userName = userName;
    }

    public UserInformation(String userName, String userPassword, String email, String userAddress, String gender, String phoneNumber, String fullName, Date dateOfBirth) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.userAddress = userAddress;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @XmlTransient
    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInformation)) {
            return false;
        }
        UserInformation other = (UserInformation) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserInformation[ userName=" + userName + " ]";
    }
    
}
