package com.youcode.visionarycrofting.controller;

import com.google.gson.Gson;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/stock")
public class StockController {
    @Autowired
    StockService stockService;
    @GetMapping
    public List<Stock> findAll(){
        return stockService.findAll();
    }


    //    @RequestMapping(value = "/add",method = RequestMethod.POST,consumes =MediaType.APPLICATION_JSON_VALUE ,
//            headers = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(path = "/insert")
    public Stock insertStock(@RequestBody Stock stock){

//        Stock stockObj = new Gson().fromJson(String.valueOf(stock), Stock.class);
         stockService.insertStock(stock);
        return stock;
    }

    @DeleteMapping(path = "/delete/{stockid}")
    public void deleteStockById(@PathVariable("stockid") Long id){
        stockService.deleteStockById(id);
    }

    @PutMapping(path = "/update/{stockid}")
    public void updateStock(
            @PathVariable("stockid") Long id , @RequestBody Stock stock){
        Optional<Stock> stockFromDB = stockService.findById(id);
        stockFromDB.get().setPhone(stock.getPhone());
        stockFromDB.get().setPassword(stock.getPassword());
        stockFromDB.get().setAddress(stock.getAddress());
        stockFromDB.get().setEmail(stock.getEmail());
        stockFromDB.get().setName(stock.getName());
        stockService.updateStock(stockFromDB.get());
    }

}
