package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService ( ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }

    public List< Product> getProducts ( ) {
        return productRepository.findAll ();
    }

    public Product addProduct ( Product product ) {
        return productRepository.save ( product );
    }

    public Product updateProduct ( Product product ) {
        Optional<Product> p = productRepository.findById ( product.getId () );

        if ( !product.getName ().isEmpty ())
            p.get ().setName ( "Foo" );

        productRepository.save ( p.get() );
        return p.get ();
    }
    /*
    public Product updateProduct ( Product product ) {
        Optional<Product> productOptional = productRepository.findById ( product.getId () );

        productOptional.ifPresentOrElse ( (product1 -> {
            product1.setId ( product.getId ( ) );

            if (!product.getName ().isEmpty ()) product1.setName ( product.getName () );
            if (!product.getDescription ().isEmpty ()) product1.setDescription ( product.getDescription () );
            if (!product.getCategory ().isEmpty ()) product1.setCategory ( product.getCategory () );

            if (product.getQuantity () > 0 && product.getQuantity () != null ) {
                if (product1.getQuantity () != null) {
                    product1.setQuantity ( product.getQuantity () );
                } else {
                    product1.setQuantity ( product1.getQuantity ( ) + product.getQuantity ( ) );
                }
            }

            if (product.getUnitaryPrice () > 0) product1.setUnitaryPrice ( product.getUnitaryPrice () );
            if (!product.getProductReference ().isEmpty ()) product1.setProductReference ( product.getProductReference () );
            if (product.getMinQuantity () > 0) product1.setMinQuantity ( product.getMinQuantity () );

            productRepository.save ( product1  );
        }) ,
                () -> {
                    System.out.println ("ERROR Product not exists" );
                });


        return product;
    }
    */
}
