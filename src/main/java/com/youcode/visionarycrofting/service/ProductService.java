package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    //public ProductService ( ProductRepository productRepository ) {
    //    this.productRepository = productRepository;
    //}

    public List< Product> getProducts ( ) {
        return productRepository.findAll ();
    }

    public Product addProduct ( Product product ) {
        Message message = new Message (  );
        Optional<Product> productOptional = productRepository.findProductByProductReference ( product.getProductReference () );
        if (productOptional.isPresent ()){
            message.setState ( "Info" );
            message.setMessage ( "Product is aready with this reference, please change a reference or update this product" );
            product.setMessage ( message );
            return product;
        } else {
            message.setState ( "Success" );
            message.setMessage ( "Product has ben created" );
            product.setMessage ( message );
            productRepository.save ( product );
            return product;
        }

    }

    public Product updateProduct ( Product product ) {
        Optional<Product> productOptional = productRepository.findById ( product.getId () );

        if (productOptional.isPresent () ){
            if (!product.getName ().isEmpty () || product.getName () != null) productOptional.get ().setName ( product.getName ());
            if (!product.getDescription ().isEmpty () || product.getDescription () != null) productOptional.get ().setDescription ( product.getDescription ());
            if (!product.getCategory ().isEmpty () || product.getCategory () != null) productOptional.get ().setCategory ( product.getCategory ());
            if (product.getQuantity () != null && productOptional.get ().getQuantity () != null && product.getQuantity () >= 0 ) productOptional.get ().setQuantity ( productOptional.get().getQuantity () + product.getQuantity ());
            if (productOptional.get().getQuantity () == null  && product.getQuantity () != null && product.getQuantity () >= 0) productOptional.get ().setQuantity ( product.getQuantity ());
            if (productOptional.get ().getQuantity () >= 0 && product.getQuantity () > 0 ) productOptional.get ().setQuantity ( productOptional.get().getQuantity () + product.getQuantity ());
            if (product.getMinQuantity () >= 0  || product.getName () != null) productOptional.get ().setMinQuantity ( product.getMinQuantity ());

            productRepository.save ( productOptional.get() );
            Message message = new Message ();
            message.setState ( "Success" );
            message.setMessage ( "Product up to date" );
            productOptional.get ().setMessage ( message );
            return productOptional.get ();
        } else {
            Message message = new Message ();
            message.setMessage ( "Product is not exists" );
            message.setState ( "Error" );
            product.setMessage ( message );
            return product;
        }

    }

    public Integer deleteProduct ( Long id ) {
        Boolean exists = productRepository.existsById(id);
        if(!exists)
        {
            return -1;
            //throw new IllegalStateException("this Product number:"+ id +" does not exist");
        } else {
            try {
                productRepository.deleteById(id);
                return 1;
            } catch (Exception e){
                return 0;
            }
        }
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

    public Product getProductByReference(String reference){
        Product product =  productRepository.getProductByProductReference(reference);
        return product;
    }

    public Product addProductInStock(Product product, Stock stock) {
        product.setStockList ( stock );
        System.out.println ( product.toString () );
        return addProduct ( product );
    }

}
