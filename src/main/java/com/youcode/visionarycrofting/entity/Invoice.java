package com.youcode.visionarycrofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.visionarycrofting.classes.Message;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refproduct;
    private String ref;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    @JsonIgnore
    private Stock stock;
    private Integer quantity;

    public Invoice() {}



    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefproduct() {
        return refproduct;
    }

    public void setRefproduct(String refproduct) {
        this.refproduct = refproduct;
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

    public Invoice(Long id, String refproduct, String ref, Provider provider, Stock stock, Integer quantity) {
        this.id = id;
        this.refproduct = refproduct;
        this.ref = ref;
        this.provider = provider;
        this.stock = stock;
        this.quantity = quantity;
    }
    public Invoice( String refproduct, String ref, Provider provider, Stock stock, Integer quantity) {
        this.refproduct = refproduct;
        this.ref = ref;
        this.provider = provider;
        this.stock = stock;
        this.quantity = quantity;
    }

    @Transient
    private Message message;

    public Message getMessage ( ) {
        return message;
    }

    public void setMessage ( Message message ) {
        this.message = message;
    }
}
