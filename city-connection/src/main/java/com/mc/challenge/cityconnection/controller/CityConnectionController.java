package com.mc.challenge.cityconnection.controller;

import com.mc.challenge.cityconnection.service.CityConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityConnectionController {

    @Autowired
    CityConnectionService cityConnectionService;

   @GetMapping("connected")
   @ResponseStatus(HttpStatus.OK)
    public String isConnectedTo(@RequestParam("origin") String origin, @RequestParam("destination") String destination){
        return cityConnectionService.connected(origin, destination)?"yes":"no";
   }
}
