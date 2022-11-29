package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }
    public void addInvoice(Invoice invoice) {
        Invoice invoiceOptional=invoiceRepository.save(invoice);

    }
    public void deleteInvoice(Long invoiceId) {
        Boolean exists=invoiceRepository.existsById(invoiceId);
        if(!exists)
        {
            throw new IllegalStateException("this provider number:"+invoiceId+" does not exist");
        }
    else {
            invoiceRepository.deleteById(invoiceId);
    }
    }



    @Transactional
    public void updateInvoice(Long id, Invoice invoice)
    {
        Invoice invoiceFromDB = invoiceRepository.findById(id)
                        .orElseThrow(()->new IllegalStateException("this Invoice does not exist"));
        invoiceFromDB.setRefproduct(invoice.getRefproduct());
        invoiceFromDB.setProvider(invoice.getProvider());
        invoiceFromDB.setStock(invoice.getStock());
        invoiceFromDB.setQuantity(invoice.getQuantity());
        invoiceFromDB.setRef(invoice.getRef());
        //full code ;
        invoiceRepository.save(invoice);

    }





}
