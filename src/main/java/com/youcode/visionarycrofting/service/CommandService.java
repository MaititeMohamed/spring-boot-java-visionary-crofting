package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.repository.ClientRepository;
import com.youcode.visionarycrofting.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class CommandService {


    private  final CommandRepository commandRepository;
    private final CommandItemService commandItemService;
    private final ClientRepository clientRepository;

    @Autowired
    public CommandService(CommandRepository commandRepository, CommandItemService commandItemService, ClientRepository clientRepository) {
        this.commandRepository = commandRepository;
        this.commandItemService = commandItemService;

        this.clientRepository = clientRepository;
    }

    public  void addNewCommand(Command command){

        Optional<Command> commandOptional = commandRepository.findCommandById(command.getId());
         if(commandOptional.isPresent()){
             try {
                 throw  new IllegalAccessException("command  already exist");
             } catch (IllegalAccessException e) {
                 throw new RuntimeException(e);
             }
         }
        commandRepository.save(command);
    }



    public List<Command> getCommand(){
        return  commandRepository.findAll();
    }
    public void  deleteCommand(Long id){
      boolean exists = commandRepository.existsById(id);
       if(!exists){
           try {
               throw  new IllegalAccessException(
                       "this command dos not exists");
           } catch (IllegalAccessException e) {
               throw new RuntimeException(e);
           }
       }
         commandRepository.deleteById(id);
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


    public Command createCommand(Collection<PasserCommande> productList , Optional<Client> client) {
        Command command = new Command();

        command.setAddress(client.get().getAddress());
        productList.stream().forEach((product) -> {
            command.setItem(commandItemService.createCommandItem(product.getRef(), product.getQuantity()));
        });
        commandRepository.save ( command );
        return command;
    }
}
