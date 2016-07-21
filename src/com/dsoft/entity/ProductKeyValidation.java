package com.dsoft.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Md. Habibur Rahman on 25/07/15.
 */

@Entity
@Table(name = "product_key_validation")
public class ProductKeyValidation extends AbstractBaseEntity{


    @Column(name = "user_name")
    private String userName;

    @Column(name = "private_key")
    private String privateKey;

    @Lob
    @Column(name = "product_key")
    private String productKey;


    @Column(name = "valid_up_to")
    private Integer validUpTo;

    @Column(name = "validation_start_from")
    private Date validationStartFrom;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "active")
    private Boolean active;


    @Transient
    private Boolean productKeyValid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public Integer getValidUpTo() {
        return validUpTo;
    }

    public void setValidUpTo(Integer validUpTo) {
        this.validUpTo = validUpTo;
    }

    public Date getValidationStartFrom() {
        return validationStartFrom;
    }

    public void setValidationStartFrom(Date validationStartFrom) {
        this.validationStartFrom = validationStartFrom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getProductKeyValid() {
        return productKeyValid;
    }

    public void setProductKeyValid(Boolean productKeyValid) {
        this.productKeyValid = productKeyValid;
    }

    @Override
    public String toString() {
        return "ProductKeyValidation{" +
                "userName='" + userName + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", productKey='" + productKey + '\'' +
                ", validUpTo=" + validUpTo +
                ", validationStartFrom=" + validationStartFrom +
                ", user=" + user +
                ", active=" + active +
                '}';
    }
}
