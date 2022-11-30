package com.youcode.visionarycrofting.classes;

public class AppelDoffre {
    private String refproduct;
    private Integer quantity;
    private String stockemail;
    private String provideremail;

    public AppelDoffre(String refproduct, Integer quantity, String stockemail, String provideremail) {
        this.refproduct = refproduct;
        this.quantity = quantity;
        this.stockemail = stockemail;
        this.provideremail = provideremail;
    }

    public String getRefproduct() {
        return refproduct;
    }

    public void setRefproduct(String refproduct) {
        this.refproduct = refproduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStockemail() {
        return stockemail;
    }

    public void setStockemail(String stockemail) {
        this.stockemail = stockemail;
    }

    public String getProvideremail() {
        return provideremail;
    }

    public void setProvideremail(String provideremail) {
        this.provideremail = provideremail;
    }
}
