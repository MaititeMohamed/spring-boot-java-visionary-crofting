package com.youcode.visionarycrofting.service;


import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients()
    {
        return  clientRepository.findAll();
    }

    public Optional<Client> getOneById(Long id){
        return clientRepository.findById(id);
    }

    public void addClient(Client client)
    {
        Optional<Client> clientOptional=clientRepository.findClientByEmail(client.getEmail());

        if (clientOptional.isPresent())
        {
            throw new IllegalStateException("email déja exist");
        }

        clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
       Boolean exists=clientRepository.existsById(clientId);
       if(!exists)
       {
            throw new IllegalStateException("this client number:"+clientId+" does not exist");
       }

       clientRepository.deleteById(clientId);
    }





    public Client updateClient(Client client)
    {
        Client clientUpdated=clientRepository.findById(client.getId()).
                orElseThrow(()->new IllegalStateException("this client number:"+client.getId()+" does not exist"));


       if(client.getName()!=null) clientUpdated.setName(client.getName());
       if (client.getEmail()!=null)clientUpdated.setEmail(client.getEmail());
       if (client.getPassword()!=null)  clientUpdated.setPassword(client.getPassword());
       if (client.getPassword()!=null) clientUpdated.setPhone(client.getPhone());
       if (client.getAddress()!=null) clientUpdated.setAddress(client.getAddress());
        clientRepository.save(clientUpdated);

return clientUpdated;
    }
}
