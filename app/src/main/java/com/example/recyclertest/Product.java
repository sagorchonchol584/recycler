package com.example.recyclertest;

public class Product  {
    public String product_id;
    public String brand_name;
    public String genaric_name;
    public String streges;
    public String tablet_option;
    public String catagury;
    public String product_name;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Product(String product_id, String brand_name, String genaric_name, String streges, String tablet_option, String catagury, String product_name) {
        this.product_id = product_id;
        this.brand_name = brand_name;
        this.genaric_name = genaric_name;
        this.streges = streges;
        this.tablet_option = tablet_option;
        this.catagury = catagury;
        this.product_name = product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getGenaric_name() {
        return genaric_name;
    }

    public void setGenaric_name(String genaric_name) {
        this.genaric_name = genaric_name;
    }

    public String getStreges() {
        return streges;
    }

    public void setStreges(String streges) {
        this.streges = streges;
    }

    public String getTablet_option() {
        return tablet_option;
    }

    public void setTablet_option(String tablet_option) {
        this.tablet_option = tablet_option;
    }

    public String getCatagury() {
        return catagury;
    }

    public void setCatagury(String catagury) {
        this.catagury = catagury;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


}