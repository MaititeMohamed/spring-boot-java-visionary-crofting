package com.youcode.visionarycrofting.controller;


import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/addClient")
    @ResponseBody
    public void registerNewClient(@RequestBody Client client)
    {
        clientService.addClient(client);
    }

    @DeleteMapping(path = "deleteClient/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId)
    {
        clientService.deleteClient(clientId);
    }


    @PutMapping(path = "/updateClient/{clientId}")
    public void updateClient(@PathVariable("clientId") Long clientId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String address)

    {
        clientService.updateClient(clientId, name, email,password,phone,address);
    }
}
