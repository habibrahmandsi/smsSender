package com.dsoft.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: Md. Habibur Rahman
 * Date: 25/07/2015
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Role {

    ROLE_SUPER_ADMIN( 1, "superAdmin", "ROLE_SUPER_ADMIN"),
    ROLE_ADMIN( 2, "Admin", "ROLE_ADMIN"),
    ROLE_EMPLOYEE( 3, "Employee", "ROLE_EMPLOYEE"),

    ROLE_USER( 4, "UserInfo", "ROLE_USER"),
    ROLE_COMPANY( 5, "CompanyInfo", "ROLE_COMPANY"),
    ROLE_PRODUCT( 6, "ProductInfo", "ROLE_PRODUCT"),
    ROLE_PRODUCT_GROUP( 7, "ProductGroupInfo", "ROLE_PRODUCT_GROUP"),
    ROLE_PRODUCT_TYPE( 8, "ProductTypeInfo", "ROLE_PRODUCT_TYPE"),
    ROLE_UOM_INFO( 9, "UomInfo", "ROLE_UOM_INFO"),

    ROLE_CREATE_SALES( 10, "CreateSales", "ROLE_CREATE_SALES"),
    ROLE_SALES_INFO( 11, "SalesInfo", "ROLE_SALES_INFO"),
    ROLE_DELETE_SALES( 12, "DeleteSales", "ROLE_DELETE_SALES"),
    ROLE_SALES_RETURN( 13, "SalesReturn", "ROLE_SALES_RETURN"),
    ROLE_DELETE_SALES_RETURN( 14, "DeleteSalesReturn", "ROLE_DELETE_SALES_RETURN"),

    ROLE_CREATE_PURCHASE( 15, "CreatePurchase", "ROLE_CREATE_PURCHASE"),
    ROLE_PURCHASE_INFO( 16, "PurchaseInfo", "ROLE_PURCHASE_INFO"),
    ROLE_DELETE_PURCHASE( 17, "DeletePurchase", "ROLE_DELETE_PURCHASE"),
    ROLE_PURCHASE_RETURN_INFO( 18, "PurchaseReturnInfo", "ROLE_PURCHASE_RETURN_INFO"),
    ROLE_DELETE_PURCHASE_RETURN( 19, "DeletePurchaseReturn", "ROLE_DELETE_PURCHASE_RETURN");


    private String label;
    private String value;
    private int code;

    private Role(int code, String value, String label) {
        this.code = code;
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static String getLabelForValue(String value) {
        for (Role type : values()) {
            if (type.getValue().equals(value)) {
                return type.getLabel();
            }
        }
        return value;
    }

    public static List getRoles() {
        List<Role> rules = new ArrayList();
        rules.add(ROLE_ADMIN);
        rules.add(ROLE_EMPLOYEE);
        return rules;
    }
}