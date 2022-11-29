package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
