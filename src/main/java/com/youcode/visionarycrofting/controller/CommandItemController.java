package com.youcode.visionarycrofting.controller;


import com.youcode.visionarycrofting.entity.CommandItem;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.service.CommandItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/commanditem")
public class CommandItemController {


    private final CommandItemService commandItemService;

    @Autowired
    public CommandItemController ( CommandItemService commandItemService ) {
        this.commandItemService = commandItemService;
    }

    @GetMapping("/allcommanditem")
    @ResponseBody
    public List < CommandItem > getCommandItemList()
    {
        return commandItemService.getCommandItemList();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Integer deleteCommandItem(@RequestBody CommandItem commandItem) {
        return commandItemService.deleteCommandItem( commandItem.getId ( ) );
    }
}
