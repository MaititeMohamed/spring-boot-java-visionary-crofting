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

    @Transactional
    public void updateClient(Long clientId, String name, String email, String password, String phone, String address)
    {
        Client client=clientRepository.findById(clientId).
                orElseThrow(()->new IllegalStateException("this client number:"+clientId+" does not exist"));

        if (name!=null && name.length()>0 && !Objects.equals(client.getName(),name))
        {
            client.setName(name);
        }

        if (email!=null && email.length()>0 && !Objects.equals(client.getEmail(),email))
        {
            client.setEmail(email);
        }


        if (password!=null && password.length()>0 && !Objects.equals(client.getPassword(),password))
        {
            client.setPassword(password);
        }

        if (phone!=null && phone.length()>0 && !Objects.equals(client.getPhone(),phone))
        {
            client.setPhone(phone);
        }


        if (address!=null && address.length()>0 && !Objects.equals(client.getAddress(),address))
        {
            client.setAddress(address);
        }

    }
}
