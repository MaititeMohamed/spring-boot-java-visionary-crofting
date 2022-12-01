package com.youcode.visionarycrofting.service;


import com.youcode.visionarycrofting.classes.AppelDoffre;
import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.InvoiceRepository;
import com.youcode.visionarycrofting.repository.ProductRepository;
import com.youcode.visionarycrofting.repository.ProviderRepository;
import com.youcode.visionarycrofting.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }

    public Stock insertStock(Stock stock) {
        if(stock.equals(null)){
            throw new IllegalStateException("stock does not be null");
        } else if (stock.getName().isEmpty()) {
            throw new IllegalStateException("name does not be empty");
        }
        else if (stock.getEmail().isEmpty()) {
            throw new IllegalStateException("email does not be empty");
        }
        else if (stock.getAddress().isEmpty()) {
            throw new IllegalStateException("address does not be empty");
        }
        else if (stock.getPassword().isEmpty()) {
            throw new IllegalStateException("password does not be empty");
        }
        else if (stock.getPhone().isEmpty()) {
            throw new IllegalStateException("phone does not be empty");
        }
        try {
            return stockRepository.save(stock);
        }
        catch (Exception e){
            return null;
        }
    }
    public void deleteStockById(Long id) {
        boolean exist = stockRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("stock with id "+ id + " does not exist");
        }
        stockRepository.deleteById(id);
    }

    public Optional<Stock> findById(Long id){
        return stockRepository.findById(id);
    }

    public Stock updateStock(Long id ,Stock stock) {
        Optional<Stock> stockFromDB ;
        if (!stock.equals(new Stock()) && stock != null && id>= 0 ){
         stockFromDB = stockRepository.findById(id);
        if(!stock.getName().isEmpty() && stock.getName() != null){stockFromDB.get().setName(stock.getName());}
        if(!stock.getPassword().isEmpty() && stock.getPassword() != null){stockFromDB.get().setPassword(stock.getPassword());}
        if(!stock.getPhone().isEmpty() && stock.getPhone() != null){stockFromDB.get().setPhone(stock.getPhone());}
        if(!stock.getEmail().isEmpty() && stock.getEmail() != null){stockFromDB.get().setEmail(stock.getEmail());}
        if(!stock.getAddress().isEmpty() && stock.getAddress() != null){stockFromDB.get().setAddress(stock.getAddress());}

        stockRepository.save(stockFromDB.get());
        return stockFromDB.get();
        }
        return null;
    }

    @Transactional
    public Invoice addAppelDoffre(AppelDoffre appelDoffre){
        Invoice invoice = new Invoice (  );
        Message message = new Message (  );
        Optional<Product> optionalProduct = productRepository.findProductByProductReference ( appelDoffre.getRefproduct () );
        if (optionalProduct.isPresent ()){
            Optional<Stock> optionalStock = stockRepository.findByEmail ( appelDoffre.getStockemail () );
                    if (optionalStock.isPresent ()){
                        Optional<Provider> optionalProvider = providerRepository.findProviderByEmail ( appelDoffre.getProvideremail () );
                                if (optionalProvider.isPresent ()){
                                    String invoidReference = optionalProvider.get ().getFirstName () +
                                            "-" + optionalStock.get ().getName () +
                                            "-" + optionalProduct.get ( ).getProductReference ( ) +
                                            "-" + LocalDate.now ();
                                    invoice.setStock ( optionalStock.get () );
                                    invoice.setProvider ( optionalProvider.get () );
                                    invoice.setRefproduct (optionalProduct.get ().getProductReference ());
                                    invoice.setQuantity ( appelDoffre.getQuantity () );
                                    invoice.setRef (invoidReference );
                                    message.setMessage ( "Invoice has ben created" );
                                    message.setState ( "Success" );
                                    invoice.setMessage ( message );
                                    return invoiceRepository.save ( invoice );
                                }
                        message.setMessage ( "Provider is not exists" );
                        message.setState ( "Error" );
                        invoice.setMessage ( message );
                        return invoice;
                    }
            message.setMessage ( "Stock is not exists" );
            message.setState ( "Error" );
            invoice.setMessage ( message );
            return invoice;
        } else {
            message.setMessage ( "Product is not exists" );
            message.setState ( "Error" );
            invoice.setMessage ( message );
            return invoice;
        }
    }
}
