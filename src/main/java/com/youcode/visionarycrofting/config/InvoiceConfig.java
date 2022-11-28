package com.youcode.visionarycrofting.config;

import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.InvoiceRepository;
import com.youcode.visionarycrofting.repository.ProviderRepository;
import com.youcode.visionarycrofting.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;


public class InvoiceConfig {

//    ProviderRepository providerRepository;
//    StockRepository stockRepository;
//    @Bean
//    CommandLineRunner commandLineRunnerInvoice (InvoiceRepository invoiceRepository){
//        return args -> {
////            public Stock(String name, String email, String password, String phone, String address, List<Object> productList, List<Object> providerList) {
////(String firstName, String lastName, String email, String password, String phone, String address)
//            Invoice f = new Invoice("refproduct1234" ,"ref12234",providerRepository.findById(Long.parseLong("1")) ,
//                    new Stock("ali","ali@gamal.com","1234","2723838","safi") , 2334);
//            Invoice j = new Invoice("refproduct8248" ,"ref3743",providerRepository.findById(Long.parseLong("2"),stockRepository.findById(Long.parseLong("1"))) , 2334);
//            Invoice a = new Invoice("refproduct8237" ,"ref8347",providerRepository.findById(Long.parseLong("1"),
//                    new Stock("ali","ali@gamal.com","1234","92374","safi") , 2334);
//            Invoice g = new Invoice("refproduct823" ,"ref9295",providerRepository.findById(Long.parseLong("1"),
//                    new Stock("ali","ali@gamal.com","1234","735838","safi") , 2334);
//
//            invoiceRepository.saveAll(List.of(f,j,a,g));
//        };
//    }

}