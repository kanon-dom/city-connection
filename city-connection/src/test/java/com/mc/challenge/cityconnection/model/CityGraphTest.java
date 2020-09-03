package com.mc.challenge.cityconnection.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityGraphTest {

    private CityGraph cityGraph;

    @Before
    public void setContext(){
        cityGraph = new CityGraph();
    }

    @Test
    public void addCityTest(){
        final String cityName = "Roswell";
        cityGraph.addCity(cityName);
        assertTrue(cityGraph.adjacentCities.containsKey(new City(cityName)));
        assertTrue(cityGraph.getAdjacentCities(cityName).isEmpty());
    }

    @Test
    public void addAdjacentCitiesRouteWithUnregisteredCity1Test(){
        final String city1Name = "Roswell";
        final String city2Name = "Savana";
        cityGraph.addCity(city2Name);
        cityGraph.addAdjacentCitiesRoute(city1Name, city2Name);
        assertFalse(cityGraph.connected(city1Name, city2Name));
    }

    @Test
    public void addAdjacentCitiesRouteWithUnregisteredCity2Test(){
        final String city1Name = "Roswell";
        final String city2Name = "Savana";
        cityGraph.addCity(city1Name);
        cityGraph.addAdjacentCitiesRoute(city1Name, city1Name);
        assertFalse(cityGraph.connected (city1Name, city2Name));
    }

    @Test
    public void addAdjacentCitiesRouteTest(){
        final String city1Name = "Roswell";
        final String city2Name = "Savana";
        cityGraph.addCity(city1Name);
        cityGraph.addCity(city2Name);
        cityGraph.addAdjacentCitiesRoute(city1Name, city2Name);
        assertTrue(cityGraph.adjacentCities.containsKey(new City(city1Name)));
        assertTrue(cityGraph.getAdjacentCities(city1Name).contains(new City(city2Name)));
    }
}
