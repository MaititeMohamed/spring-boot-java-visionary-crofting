package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void passerCommand(){
        //PasserCommande passerCommande = new PasserCommande (  );
        //Collection <PasserCommande> passerCommandeCollection = new ArrayList <> (  );
        //passerCommandeCollection.add ( passerCommande );
        // passer command is null && id client is null
        //Client client = clientService.passerCommande ( null,  passerCommandeCollection);
        //assertEquals ( null, client );
    }

    @Test void getClient(){
        Optional<Client> clientOptional = clientService.getOneById ( null );

        assertFalse ( clientOptional.isPresent () );
    }
}