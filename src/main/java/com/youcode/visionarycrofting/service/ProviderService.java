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
    public void updateProvider(Long providerId, String firstName, String lastName, String email, String password, String phone, String address)
    {
        Provider provider=providerRepository.findById(providerId).
                orElseThrow(()->new IllegalStateException("this provider number:"+providerId+" does not exist"));

        if (firstName!=null && firstName.length()>0 && !Objects.equals(provider.getFirstName(),firstName))
        {
            provider.setFirstName(firstName);
        }



        if (lastName!=null && lastName.length()>0 && !Objects.equals(provider.getLastName(),lastName))
        {
            provider.setLastName(lastName);
        }

        if (email!=null && email.length()>0 && !Objects.equals(provider.getEmail(),email))
        {
            provider.setEmail(email);
        }


        if (password!=null && password.length()>0 && !Objects.equals(provider.getPassword(),password))
        {
            provider.setPassword(password);
        }

        if (phone!=null && phone.length()>0 && !Objects.equals(provider.getPhone(),phone))
        {
            provider.setPhone(phone);
        }


        if (address!=null && address.length()>0 && !Objects.equals(provider.getAddress(),address))
        {
            provider.setAddress(address);
        }

    }
}
