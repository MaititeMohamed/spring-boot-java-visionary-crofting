package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;


    //public ProductController ( ProductService productService ) {
    //    this.productService = productService;
    //}

    @GetMapping("/products")
    @ResponseBody
    public List < Product > getClients()
    {
        return productService.getProducts();
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        boolean isValide = true;
        Message message = new Message (  );

        if (product.getProductReference () == null) {
            isValide = !isValide;
            message.setState ( "Error" );
            message.setMessage ( "Reference does not null value" );
            product.setMessage ( message );
        } else if (product.getQuantity () == null){
            isValide = !isValide;
            message.setState ( "Error" );
            message.setMessage ( "Quantity does not null value" );
            product.setMessage ( message );
        }else if (product.getQuantity () < 1){
            isValide = !isValide;
            message.setState ( "Error" );
            message.setMessage ( "Quantity does not negative or equal zero value" );
            product.setMessage ( message );
        } else if (product.getMinQuantity () < 0){
            isValide = !isValide;
            message.setState ( "Error" );
            message.setMessage ( "Quantity Min does not negative value" );
            product.setMessage ( message );
        } else if (product.getCategory () == null){
            isValide = !isValide;
            message.setState ( "Error" );
            message.setMessage ( "Category does not null value" );
            product.setMessage ( message );
        } else if (product.getName () == null) {
            isValide = !isValide;
            message.setState ( "Error" );
            message.setMessage ( "Name does not negative value" );
            product.setMessage ( message );
        }

        if (isValide) {
            message.setState ( "Success" );
            message.setMessage ( "your product has been successfully created" );
            product.setMessage ( message );
            return productService.addProduct(product);
        } else return product;
    }

    @PutMapping("/updateproduct")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Integer deleteProduct(@PathVariable Long id){ return productService.deleteProduct(id);}
}
