package com.youcode.visionarycrofting.config;

import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.InvoiceRepository;
import com.youcode.visionarycrofting.repository.ProviderRepository;
import com.youcode.visionarycrofting.repository.StockRepository;
import com.youcode.visionarycrofting.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
public class InvoiceConfig{


    @Bean
    CommandLineRunner commandLineRunnerInvoice9 (InvoiceRepository invoiceRepository){
        return args -> {

            Invoice c = new Invoice();
            c.setRef("refproduct83839");
            c.setRefproduct("prref389");
            c.setQuantity(034);
            Invoice d = new Invoice();
            d.setRef("refproduct5347");
            d.setRefproduct("prref526");
            d.setQuantity(934);
//            d.setStock(s2);
            Invoice k = new Invoice();
            k.setRef("refproduct0923");
            k.setRefproduct("prref024");
            k.setQuantity(532);
//            k.setStock(s3);
            invoiceRepository.saveAll(List.of(c,d,k));
        };
    }

}