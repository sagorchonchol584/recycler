package com.example.recyclertest;

public class EditModel {



    private boolean isSelected;

    public int units;
    public Double net;
    public Double total;


    public String strength;
    public  String namedx;
    public String product_id;
    public int previous_stock;



    public int getPrevious_stock() {
        return previous_stock;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setPrevious_stock(int previous_stock) {
        this.previous_stock = previous_stock;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getNamedx() {
        return namedx;
    }

    public void setNamedx(String namedx) {
        this.namedx = namedx;
    }
}
