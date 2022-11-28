package com.youcode.visionarycrofting.controller;

import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void deleteProvider(@PathVariable("providerId") Long providerId )
    {
        providerService.deleteProvider(providerId);
    }

    @PutMapping("/updateProvider/{providerId}")
    public void updateProvider(@PathVariable("providerId") Long providerId,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) String password,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) String address)
    {

        providerService.updateProvider(providerId, firstName,lastName,email,password,phone,address);
    }
}
