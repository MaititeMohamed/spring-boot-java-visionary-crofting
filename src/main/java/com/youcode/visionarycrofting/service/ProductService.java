package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService ( ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }
}
