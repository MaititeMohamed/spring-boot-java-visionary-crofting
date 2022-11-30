package com.youcode.visionarycrofting.service;


import com.youcode.visionarycrofting.classes.AppelDoffre;
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
    public Invoice addAppelDoffre(AppelDoffre appelDoffre){
        Optional<Product> product = Optional.ofNullable(productRepository.getProductByProductReference(appelDoffre.getRefproduct()));
        Optional<Provider> provider =  providerRepository.findProviderByEmail(appelDoffre.getProvideremail());
        Optional<Stock> stock =  stockRepository.findByEmail(appelDoffre.getStockemail());
//        if (provider.isPresent() && stock.isPresent()) {
            Invoice invoice = new Invoice(product.get().getProductReference(), "ref-" + product.get().getId() + random(999, 10000000), provider.get(), stock.get(), appelDoffre.getQuantity());
            return invoiceRepository.save(invoice);
//        }
//        return null;
    }
    public Integer random(Integer min , Integer max){

        return (int)Math.floor(Math.random()*(max-min+1)+min);

    }


}
