package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController ( ProductService productService ) {
        this.productService = productService;
    }
}
