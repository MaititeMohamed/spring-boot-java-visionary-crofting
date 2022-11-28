package com.youcode.visionarycrofting.entity;

import javax.persistence.*;

@Entity
public class CommendItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refproduct;
    private String ref;
    private Integer quantity;


}
