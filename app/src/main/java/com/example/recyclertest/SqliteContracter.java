package com.example.recyclertest;

import java.util.ArrayList;

public class SqliteContracter {


//    public SqliteContracter(String ids, String brand_name, String product_name, String generic, String strength, String form_eg_table, String catalogs) {
//        this.ids = ids;
//        this.brand_name = brand_name;
//        this.product_name = product_name;
//        this.generic = generic;
//        this.strength = strength;
//        this.form_eg_table = form_eg_table;
//        this.catalogs = catalogs;
//    }



//    public SqliteContracter(MainActivity mainActivity) {
//        this.ids = ids;
//        this.brand_name = brand_name;
//        this.product_name = product_name;
//        this.generic = generic;
//        this.strength = strength;
//        this.form_eg_table = form_eg_table;
//        this.catalogs = catalogs;
//    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getForm_eg_table() {
        return form_eg_table;
    }

    public void setForm_eg_table(String form_eg_table) {
        this.form_eg_table = form_eg_table;
    }

    public String getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(String catalogs) {
        this.catalogs = catalogs;
    }

    public String  ids;
    public String brand_name;
    public String product_name;
    public String generic;
    public String strength ;
    public String form_eg_table;
    public String catalogs ;


}
