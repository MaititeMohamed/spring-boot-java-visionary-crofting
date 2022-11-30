package com.youcode.visionarycrofting.config;

import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.repository.ClientRepository;
import com.youcode.visionarycrofting.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class ClientConfi {
    @Bean
    CommandLineRunner commandLineRunner1 (ClientRepository clientRepository){
        return args -> {
            Client client1=new Client("jamal","jamal@gmail.com","PASS132","0634483769","YOUSOUFIA");

            Client client=new Client("Mohamed","MOhamed@gmail.com","PASS132","0634483769","YOUSOUFIA");
           clientRepository.saveAll(List.of(client1,client));
        };
    }
}
