package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "visionarycrofting/Provider")
public class providerController {

    private final ProviderService providerService;

    public providerController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/Providers")
    public List<Provider> getProviders()
    {
       return providerService.getProviders();
    }

    @PostMapping("/addProvider")
    public void addProvider(@RequestBody Provider provider)
    {
        providerService.addProvider(provider);
    }

    @DeleteMapping("/deleteProvider/{providerId}")
    public Message deleteProvider( @PathVariable("providerId") Long providerId )
    {
        return providerService.deleteProvider(providerId);
    }

    @PutMapping("/updateProvider")
    @ResponseBody
    public Provider updateProvider(@RequestBody Provider provider)
    {
        providerService.updateProvider(provider);
        return provider;
    }

    @PutMapping ("/validateInvoice/{id}")
    public Optional < Product > validateInvoice( @PathVariable("id") Long id)
    {
        return providerService.validateInvoice(id);
    }
}
