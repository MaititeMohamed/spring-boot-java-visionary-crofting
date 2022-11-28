package com.youcode.visionarycrofting.controller;


import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.repository.CommandRepository;
import com.youcode.visionarycrofting.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(path = "api/command")
public class CommandController {


    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public  Command  addNewCommand(@RequestBody Command command){
        commandService.addNewCommand(command);
        return command;
    }
    @GetMapping
    public List<Command> getAllCommand(){
       return commandService.getCommand();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCommand(@PathVariable("id")Long id){commandService.deleteCommand(id);};

 @PutMapping(path = "/{id}")
   public  void updateCommand(
           @PathVariable("id")Long id,
           @RequestParam(required = false) String ref,
           @RequestParam(required = false) String dateTime,
           @RequestParam(required = false) String address,
           @RequestParam(required = false) int totalPrice
     ){

       commandService.updateCommand(id,ref,dateTime,address,totalPrice);

   }
    @PutMapping(path = "/update")
    public Command updateCommand(@RequestBody  Command command){
        commandService.updateCommand(command);
        return command;
    }
}
