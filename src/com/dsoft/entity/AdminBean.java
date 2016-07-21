package com.dsoft.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by habib on 7/28/15.
 */
public class AdminBean {

    private MultipartFile productGroupFile;


    public MultipartFile getProductGroupFile() {
        return productGroupFile;
    }

    public void setProductGroupFile(MultipartFile productGroupFile) {
        this.productGroupFile = productGroupFile;
    }
}
