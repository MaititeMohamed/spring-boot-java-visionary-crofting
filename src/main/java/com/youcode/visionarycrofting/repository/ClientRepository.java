package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long>
{


Optional<Client> findClientByEmail(String email);

    @Query("SELECT c FROM Command c WHERE c.client.id = ?1")
    List < Command > findCommandByClient(Long id);

}


