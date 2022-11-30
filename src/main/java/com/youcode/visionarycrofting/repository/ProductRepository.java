package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository< Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productReference = ?1")
    Product getProductByProductReference ( String reference );

    Optional<Product> findProductByProductReference(String reference);
}
