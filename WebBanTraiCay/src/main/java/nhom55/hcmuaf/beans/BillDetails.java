package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class BillDetails implements Serializable {
    private int id;
    private int quantity;
    private double totalPrice;
    private Products products;
    private Bills bills;

    public BillDetails(int quantity, int id, double totalPrice, Products products, Bills bills) {
        this.quantity = quantity;
        this.id = id;
        this.totalPrice = totalPrice;
        this.products = products;
        this.bills = bills;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Bills getBills() {
        return bills;
    }

    public void setBills(Bills bills) {
        this.bills = bills;
    }

    public BillDetails() {
    }

    @Override
    public String toString() {
        return "BillsDetails{" +
                "quantity=" + quantity +
                ", id=" + id +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                ", bills=" + bills +
                '}';
    }
}
