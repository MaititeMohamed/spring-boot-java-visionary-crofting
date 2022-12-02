package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.entity.CommandItem;
import com.youcode.visionarycrofting.repository.ClientRepository;
import com.youcode.visionarycrofting.repository.CommandItemRepository;
import com.youcode.visionarycrofting.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class CommandService {

    @Autowired
    CommandRepository commandRepository;
    @Autowired
    CommandItemService commandItemService;
    @Autowired
    CommandItemRepository commandItemRepository;
    @Autowired
    ClientRepository clientRepository;

    public Command addNewCommand(Command command)
    {
        boolean exists = commandRepository.existsByRef ( command.getRef () );
        Message message = new Message (  );
        if (exists){
            message.setState ( "Info" );
            message.setMessage ( "Reference Exists" );
            command.setMessage ( message );
            return command;
        } else {
            commandRepository.save(command);
            message.setState ( "Success" );
            message.setMessage ( "Command has ben created" );
            command.setMessage ( message );
            return command;
        }
    }

    public List<Command> getCommand(){
        List<Command> commandList = new ArrayList <> (  );
        Message message = new Message (  );
        try {
            commandList =  commandRepository.findAll();

            if (commandList.size () == 0){
                message.setState ( "Info" );
                message.setMessage ( "Dont have a command in your data base" );
                Command command = new Command (  );
                command.setMessage ( message );
                commandList.add ( command );
            }

        } catch (Exception e){

            message.setState ( "Exception" );
            message.setMessage ( "Exception : " + e.toString () );
            Command command = new Command (  );
            command.setMessage ( message );
            commandList.add ( command );
        } finally {
            return commandList;
        }
    }

    public Integer  deleteCommand(Long id){
      boolean exists = commandRepository.existsById(id);
       if(!exists){
          return  -1;
       }else {

           try {
               commandRepository.deleteById(id);
               return 1;
           } catch (Exception e){
               return 0;
           }
       }
    }

    @Transactional
    public  void updateCommand(Long id,
                               String ref,
                               String dateTime,
                               String address,
                               int totalPrice
                               ){
        Command command =commandRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(
                  "Command withe id "+id+" dos not exists"
                ));

        if (ref !=null && ref.length()>0 && !Objects.equals(command.getRef(),ref)){
            command.setRef(ref);
        }
        if(dateTime!=null && dateTime.length()>0 && !Objects.equals(command.getDateTime(),dateTime)){
            command.setDateTime(dateTime);
        }

        if (address !=null && address.length()>0 && !Objects.equals(command.getAddress(),address)){
            command.setAddress(address);
        }

        if(totalPrice >0 && !Objects.equals(command.getTotalPrice(),totalPrice) ){
            command.setTotalPrice(totalPrice);
        }
    }
    @Transactional
    public  Command updateCommand(Command command){

    Command  commandUpdated=commandRepository.findById(command.getId())
    .orElseThrow(()->new IllegalArgumentException("Command withe id "+command.getId()+" dos not exists")) ;
    commandUpdated.setRef(command.getRef());
    commandUpdated.setDateTime(command.getDateTime());
    commandUpdated.setTotalPrice(command.getTotalPrice());
    commandUpdated.setAddress(command.getAddress());

        return  commandUpdated;
    }


public Command createCommand(Collection<PasserCommande> productList ,Client client) {
    Command command = new Command();
    Random rand = new Random();
    int n = rand.nextInt(1000);
    command.setAddress(client.getAddress());
    String commandRef="Ref"+client.getName()+"-"+n;
    command.setRef(commandRef);
    command.setDateTime(LocalDate.now().toString());
    command.setClient ( client );
    // fr
    addNewCommand ( command );
    productList.stream().forEach((product) -> {
        command.setItem(commandItemService.createCommandItem(product.getRef(), product.getQuantity(), command));

    });
    command.setTotalPrice(0.0);
    command.getListItem().stream().forEach((item) -> {
        command.setTotalPrice( command.getTotalPrice () + item.getPrice ());
        System.out.println(item.getPrice());
    });


    commandRepository.save (command);
    return command;
}
}
