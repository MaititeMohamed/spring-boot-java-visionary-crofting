package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider,Long>
{

    Optional<Provider> findProviderByEmail(String email);


}
