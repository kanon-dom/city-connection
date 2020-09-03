package com.mc.challenge.cityconnection.service;

import com.mc.challenge.cityconnection.data.CityRoutesLoader;
import com.mc.challenge.cityconnection.model.CityGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityConnectionServiceImpl implements CityConnectionService{

    CityGraph cityGraph;
    @Autowired
    private CityRoutesLoader cityRoutesLoader;

    @Override
    public boolean connected(String origin, String destination) {
        if(cityGraph == null){
            cityGraph = cityRoutesLoader.populateCityGraph();
        }
        return cityGraph.connected(origin, destination);
    }
}
