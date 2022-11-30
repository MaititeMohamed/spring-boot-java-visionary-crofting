package com.youcode.visionarycrofting.service;


import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class ProviderService {

    private final ProviderRepository providerRepository;
    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }
    public List<Provider> getProviders()
    {
        return  providerRepository.findAll();
    }

    public void addProvider(Provider provider)
    {
        Optional<Provider> clientOptional=providerRepository.findProviderByEmail(provider.getEmail());
        if (provider.getAddress()==null || provider.getEmail()==null || provider.getPassword()==null ||  provider.getFirstName()==null || provider.getPhone()==null ||  provider.getLastName()==null )
        {
            throw new IllegalStateException("merci de remplir tous les informations du fournisseur  ");
        }

        if (clientOptional.isPresent())
        {
            throw new IllegalStateException("email déja exist");
        }

        providerRepository.save(provider);
    }


    public void deleteProvider(Long providerId)
    {
        Boolean exists=providerRepository.existsById(providerId);
        if(!exists)
        {
            throw new IllegalStateException("this provider number:"+providerId+" does not exist");
        }

        providerRepository.deleteById(providerId);
    }


    @Transactional
    public void updateProvider(Provider provider)
    {
        Provider providerUpdated=providerRepository.findById(provider.getId()).
                orElseThrow(()->new IllegalStateException("this provider number:"+provider.getId()+" does not exist"));

        if (provider.getFirstName()!=null) providerUpdated.setFirstName(provider.getFirstName());
        if (provider.getLastName()!=null) providerUpdated.setLastName(provider.getLastName());
        if (provider.getEmail()!=null) providerUpdated.setEmail(provider.getEmail());
        if (provider.getPassword()!=null) providerUpdated.setPassword(provider.getPassword());
        if (provider.getPhone()!=null) providerUpdated.setPhone(provider.getPhone());
        if (provider.getAddress()!=null) providerUpdated.setAddress(provider.getAddress());


    }
}
