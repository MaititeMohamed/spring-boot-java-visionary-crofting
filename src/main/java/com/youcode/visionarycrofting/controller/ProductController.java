package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController ( ProductService productService ) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @ResponseBody
    public List < Product > getClients()
    {
        return productService.getProducts();
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {return productService.addProduct(product);}

    @PutMapping("/updateproduct")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
}
