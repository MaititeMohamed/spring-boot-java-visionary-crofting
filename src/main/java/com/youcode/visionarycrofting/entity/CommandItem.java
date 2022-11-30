package com.youcode.visionarycrofting.entity;

import javax.persistence.*;

@Entity
@Table
public class CommandItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private double price;
    private Integer quantity;

    @ManyToOne
    private Command command;
    @ManyToOne
    private Product product;

    public CommandItem ( ) {
    }

    public CommandItem ( Long id , String ref , double price , Integer quantity , Command command , Product product ) {
        this.id = id;
        this.ref = ref;
        this.price = price;
        this.quantity = quantity;
        this.command = command;
        this.product = product;
    }

    public CommandItem ( String ref , double price , Integer quantity , Command command , Product product ) {
        this.ref = ref;
        this.price = price;
        this.quantity = quantity;
        this.command = command;
        this.product = product;
    }

    public CommandItem ( String itemReference ,
                         double price ,
                         Integer quantity ,
                         Product product ) {
        this.ref = itemReference;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    public Long getId ( ) {
        return id;
    }

    public String getRef ( ) {
        return ref;
    }

    public double getPrice ( ) {
        return price;
    }

    public Integer getQuantity ( ) {
        return quantity;
    }

    public Command getCommand ( ) {
        return command;
    }

    public Product getProduct ( ) {
        return product;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public void setRef ( String ref ) {
        this.ref = ref;
    }

    public void setPrice ( double price ) {
        this.price = price;
    }

    public void setQuantity ( Integer quantity ) {
        this.quantity = quantity;
    }

    public void setCommand ( Command command ) {
        this.command = command;
    }

    public void setProduct ( Product product ) {
        this.product = product;
    }

    @Override
    public String toString ( ) {
        return "CommandItem{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", command=" + command +
                ", product=" + product +
                '}';
    }
}
