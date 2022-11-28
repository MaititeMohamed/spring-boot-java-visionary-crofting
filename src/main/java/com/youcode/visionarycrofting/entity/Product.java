package com.youcode.visionarycrofting.entity;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({ @NamedQuery(name = "Product.existsBy", query = "select (count(p) > 0) from Product p") })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String productReference;
    @Column(unique = true)
    private String name;
    private Double unitaryPrice;
    private String description;
    private String category;
    private Integer quantity;
    private Integer minQuantity;

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

    //private List <Object> invoiceList;
    //private List<Object> itemList;

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass ( this ) != Hibernate.getClass ( o ) )
            return false;
        Product product = (Product) o;
        return id != null && Objects.equals ( id , product.id );
    }

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
