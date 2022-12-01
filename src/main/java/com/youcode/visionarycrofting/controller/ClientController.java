package com.youcode.visionarycrofting.controller;


import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "visionarycrofting/Client")
public class ClientController {
private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/Clients")
    @ResponseBody
    public List<Client> getClients()
    {
        return clientService.getClients();
    }

    @GetMapping("/{client_id}")
    public Optional<Client> getOne(@PathVariable("client_id") Long clientId){
        return clientService.getOneById(clientId);
    }

    @PostMapping("/addClient")
    public Client registerNewClient(@RequestBody Client client)
    {
        return clientService.addClient(client);
    }

    @DeleteMapping(path = "deleteClient/{clientId}")
    public Message deleteClient( @PathVariable("clientId") Long clientId)
    {
        return clientService.deleteClient(clientId);
    }


    @PutMapping(path = "/updateClient")
    public void updateClient(@RequestBody Client client)

    {
        clientService.updateClient(client);
    }


    @PostMapping("/passer_commande/{id}")
    @ResponseBody
    public Client passerCommande(@PathVariable Long id,@RequestBody Collection<PasserCommande> productList)
    {
        return clientService.passerCommande(id, productList);
    }

}
