package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.entity.CommandItem;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.repository.CommandItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandItemService {
    private final CommandItemRepository commandItemRepository;
    private final ProductService productService;

    @Autowired
    public CommandItemService ( CommandItemRepository commandItemRepository , ProductService productService ) {
        this.commandItemRepository = commandItemRepository;
        this.productService = productService;
    }

    public List< CommandItem> getCommandItemList ( ) {
        return  commandItemRepository.findAll ();
    }

    public CommandItem createCommandItem ( String ref , Integer quantity ) {
        Product product = productService.getProductByReference ( ref );

        if (product.getQuantity () > 0 &&
                product.getQuantity () != null &&
                product.getQuantity () >= quantity)
        {
            String itemReference = product.getName () + "-" + product.getProductReference ();
            CommandItem commandItem = new CommandItem ( itemReference , (quantity * product.getUnitaryPrice ()), quantity, product);
            commandItemRepository.save ( commandItem );
            return commandItem;
        }
        return null;
    }
}
