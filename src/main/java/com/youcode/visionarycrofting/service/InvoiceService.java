package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Invoice;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Provider;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.InvoiceRepository;
import com.youcode.visionarycrofting.repository.ProductRepository;
import com.youcode.visionarycrofting.repository.ProviderRepository;
import com.youcode.visionarycrofting.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private StockRepository stockRepository;

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice addInvoice(Long idstock, Long idprovider, Invoice invoice) {
        Message message = new Message();
        if (!invoice.equals(new Invoice())) {
            if (invoice.getQuantity() >= 1) {
                Optional<Stock> stock = stockRepository.findById(idstock);
                if (stock.isPresent()) {
                    Optional<Provider> provider = providerRepository.findById(idprovider);
                    if (provider.isPresent()) {
                        invoice.setStock(stock.get());
                        invoice.setProvider(provider.get());
                        invoiceRepository.save(invoice);

                    } else {
                        message.setMessage("provider does not exist");
                        message.setState("ERROR");
                    }
                } else {
                    message.setMessage("stock does not exist");
                    message.setState("ERROR");
                }

            } else {
                message.setMessage("this invoice is null");
                message.setState("ERROR");
            }

        }
        invoice.setMessage(message);
        return invoice;
    }

    public Message deleteInvoice(Long invoiceId) {

        Boolean exists = invoiceRepository.existsById(invoiceId);
        Message message = new Message();
        if (!exists) {
            message.setMessage("sorry this invoice does not exist");
            message.setState("ERROR");
        } else {
            message.setMessage("Success");
            message.setState("Success");
            invoiceRepository.deleteById(invoiceId);
        }
        return message;
    }


    @Transactional
    public Invoice updateInvoice(Long id, Invoice invoice) {
        Message message = new Message();
        if (!invoice.equals(new Invoice())) {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);

            if (invoiceOptional.isPresent()) {
                if (invoice.getQuantity() > 0) {

                    invoiceOptional.get().setQuantity(invoice.getQuantity());
                    if (invoice.getStock() != null) {
                        invoiceOptional.get().setStock(invoice.getStock());
                    }
                    if (invoice.getProvider() != null && !invoice.getProvider().equals(new Provider())) {
                        invoiceOptional.get().setProvider(invoice.getProvider());
                    }
                    if (invoice.getRefproduct() != null && invoice.getRefproduct() == "") {
                        invoiceOptional.get().setRefproduct(invoice.getRefproduct());
                    }
                    invoiceRepository.save(invoiceOptional.get());
                } else {
                    message.setMessage("invoice is not exist");
                    message.setState("ERROR");
                }
            } else {
                message.setMessage("invoice is null");
                message.setState("ERROR");
            }

        }

        return invoice;
    }
}
