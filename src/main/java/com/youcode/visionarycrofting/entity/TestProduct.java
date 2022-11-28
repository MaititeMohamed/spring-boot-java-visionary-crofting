package com.youcode.visionarycrofting.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class TestProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
