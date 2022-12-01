package com.youcode.visionarycrofting.entity;


import com.youcode.visionarycrofting.classes.Message;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String productReference;
    private String name;
    private Double unitaryPrice;
    private String description;
    private String category;
    private Integer quantity;
    private Integer minQuantity;

    @Transient
    private Message message;

    public Message getMessage ( ) {
        return message;
    }

    public void setMessage ( Message message ) {
        this.message = message;
    }

    public Product ( ) {    }

    public Product ( Long id ,
                     String productReference ,
                     String name ,
                     Double unitaryPrice ,
                     String description ,
                     String category ,
                     Integer quantity ,
                     Integer minQuantity ) {
        this.id = id;
        this.productReference = productReference;
        this.name = name;
        this.unitaryPrice = unitaryPrice;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

    public Product ( String productReference ,
                     String name ,
                     Double unitaryPrice ,
                     String description ,
                     String category ,
                     Integer quantity ,
                     Integer minQuantity ) {
        this.productReference = productReference;
        this.name = name;
        this.unitaryPrice = unitaryPrice;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

    public Long getId ( ) { return id; }

    public String getProductReference ( ) {
        return productReference;
    }

    public String getName ( ) {
        return name;
    }

    public Double getUnitaryPrice ( ) {
        return unitaryPrice;
    }

    public String getDescription ( ) {
        return description;
    }

    public String getCategory ( ) {
        return category;
    }

    public Integer getQuantity ( ) {
        return quantity;
    }

    public Integer getMinQuantity ( ) {
        return minQuantity;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public void setProductReference ( String productReference ) {
        this.productReference = productReference;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public void setUnitaryPrice ( Double unitaryPrice ) {
        this.unitaryPrice = unitaryPrice;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public void setCategory ( String category ) {
        this.category = category;
    }

    public void setQuantity ( Integer quantity ) { this.quantity = quantity;}

    public void setMinQuantity ( Integer minQuantity ) {
        this.minQuantity = minQuantity;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_stock",
            joinColumns = { @JoinColumn(name = "stock_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private List<Stock> stockList = new ArrayList <> (  );

    @OneToMany(mappedBy = "product")
    private List<CommandItem> commandItemList = new ArrayList <> (  );


    @Override
    public String toString ( ) {
        return "Product{" +
                "id=" + id +
                ", productReference='" + productReference + '\'' +
                ", name='" + name + '\'' +
                ", unitaryPrice=" + unitaryPrice +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
