package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class CommandService {


    private  final CommandRepository commandRepository;

    @Autowired
    public CommandService(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
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

        if (address !=null && address.length()>0 && !Objects.equals(command.getAddress(),address)){
            command.setAddress(address);
        }

        if(totalPrice >0 && !Objects.equals(command.getTotalPrice(),totalPrice) ){
            command.setTotalPrice(totalPrice);
        }
    }

}
