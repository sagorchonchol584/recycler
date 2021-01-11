package com.example.recyclertest;

import android.widget.ListView;

public class Produst_Volley {

    public int id;
    public String producttitle;
    public int productid;
    public String productdescription;
    public double productrating;
    public String productcategory;

    public Produst_Volley(int productid, String producttitle, String productdescription, double productrating, String productcategory, double productprice, String productimage) {

        this.id = id;
        this.producttitle = producttitle;
        this.productid = productid;
        this.productdescription = productdescription;
        this.productrating = productrating;
        this.productcategory = productcategory;
        this.productprice = productprice;
        this.productimage = productimage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public double getProductrating() {
        return productrating;
    }

    public void setProductrating(double productrating) {
        this.productrating = productrating;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public double productprice;
    public String productimage;
}
