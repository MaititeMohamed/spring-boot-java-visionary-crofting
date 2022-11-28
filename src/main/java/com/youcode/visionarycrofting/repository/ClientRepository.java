package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long>
{


Optional<Client> findClientByEmail(String email);



}


