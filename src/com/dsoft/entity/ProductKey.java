package com.dsoft.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Md. Habibur Rahman on 25/07/15.
 */

@Entity
@Table(name = "product_key")
public class ProductKey extends AbstractBaseEntity{

    public ProductKey(){
        this.validUpTo = 7;
    }

    @Column(name = "user_name")
    private String userName;

    @Column(name = "private_key")
    private String privateKey;

    @Lob
    @Column(name = "product_key")
    private String productKey;

    @Column(name = "valid_up_to")
    private Integer validUpTo;

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

    @Override
    public String toString() {
        return "ProductKey{" +
                "userName='" + userName + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", productKey='" + productKey + '\'' +
                ", validUpTo=" + validUpTo +
                '}';
    }
}
