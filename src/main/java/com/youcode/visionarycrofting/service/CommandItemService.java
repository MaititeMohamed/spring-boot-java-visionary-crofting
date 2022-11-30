package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.entity.CommandItem;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.repository.CommandItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public CommandItem createCommandItem ( String ref , Integer quantity, Command command ) {
        Product product = productService.getProductByReference ( ref );

        if (product.getQuantity () > 0 &&
                product.getQuantity () != null &&
                product.getQuantity () >= quantity)
        {
            String itemReference = product.getName () + "-" + product.getProductReference () + "_" + LocalDate.now ().getYear ();
            CommandItem commandItem = new CommandItem ( itemReference , (quantity * product.getUnitaryPrice ()), quantity, product);
            commandItem.setCommand ( command );
            commandItemRepository.save ( commandItem );
            return commandItem;
        }
        return null;
    }

    public Integer deleteCommandItem ( Long id ) {
        Optional<CommandItem> commandItemOptional = commandItemRepository.findById ( id );

        if (commandItemOptional.isPresent ()){
            try {
                commandItemRepository.delete ( commandItemOptional.get () );
                return 1;
            }catch (Exception e){
                System.out.println (e.toString () );
                return 0;
            }
        } else {
            return -1;
        }
    }
}
