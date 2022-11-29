package com.youcode.visionarycrofting.config;

import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.InvoiceRepository;
import com.youcode.visionarycrofting.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StockConfig {

    @Bean
    CommandLineRunner commandLineRunner (StockRepository stockRepository ){
        return args -> {

//            public Stock(String name, String email, String password, String phone, String address, List<Object> productList, List<Object> providerList) {
//
                Stock ali = new Stock("ali", "ali@gamal.com", "1234", "2723838", "safi");
                Stock youssef = new Stock("youssef", "youssef@gamal.com", "1234", "2723838", "safi");
                Stock anas = new Stock("anas", "anas@gamal.com", "1234", "2723838", "safi");
                Stock abde = new Stock("abde", "abde@gamal.com", "1234", "2723838", "safi");
                stockRepository.saveAll(List.of(ali, youssef, anas, abde));


        };
    }

}
