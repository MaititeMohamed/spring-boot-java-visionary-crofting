package com.youcode.visionarycrofting.classes;

public class PasserCommande {

    private String ref;
    private Integer quantity;

    public PasserCommande() {
    }

    public PasserCommande(String ref, Integer quantity) {
        this.ref = ref;
        this.quantity = quantity;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PasserCommande{" +
                "ref='" + ref + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
