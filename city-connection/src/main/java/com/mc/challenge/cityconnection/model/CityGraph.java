package com.mc.challenge.cityconnection.model;


import com.mc.challenge.cityconnection.data.CityRoutesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;

import java.util.*;

public class CityGraph {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityGraph.class);

    Map<City, Set<City>> adjacentCities;

    @Autowired
    private CityRoutesLoader cityRoutesLoader;

    public CityGraph(){
        adjacentCities = new HashMap<City, Set<City>>();
    }

    public void addCity(String cityName){
        if(adjacentCities.get(new City(cityName)) == null) {
            adjacentCities.put(new City(cityName), new HashSet<City>());
        }
    }

    public void LoadCities(Set<CityRoute> routes){
        for(CityRoute cr: routes){
            this.addCity(cr.getOrigin());
            this.addCity(cr.getDestination());
        }
    }

    public void loadCityRoutes(Set<CityRoute> routes){
        for(CityRoute cr: routes){
           this.addAdjacentCitiesRoute(cr.getOrigin(), cr.getDestination());
        }
    }
    public Set<City> getCities(){
        return adjacentCities.keySet();
    }

    public Set<City> getAdjacentCities(String cityName){
        return adjacentCities.get(new City(cityName));
    }

    public void addAdjacentCitiesRoute(String cityName1, String cityName2){
        if(!isRegisteredCity(cityName1)){
            LOGGER.debug("city: {} is not registered", cityName1);
            return;
        }
        City city1 = new City(cityName1);

        if(!isRegisteredCity(cityName2)){
            LOGGER.debug("city: {} is not registered", cityName2);
            return;
        }

        City city2 = new City(cityName2);

        adjacentCities.get(city1).add(city2);
        adjacentCities.get(city2).add(city1);
    }

    public boolean isRegisteredCity(String cityName){
        return adjacentCities.containsKey(new City(cityName));
    }

    public boolean connected(String cityName1, String cityName2){
        if(StringUtils.isBlank(cityName1) || StringUtils.isBlank(cityName2)){
            return false;
        }

        if(!isRegisteredCity(cityName1) || !isRegisteredCity(cityName2)){
            return false;
        }

        return depthFirstTraversal(this, cityName1).contains(cityName2);
    }

    public Set<String> depthFirstTraversal(CityGraph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (City city : graph.getAdjacentCities(vertex)) {
                    stack.push(city.getName());
                }
            }
        }
        return visited;
    }
}
