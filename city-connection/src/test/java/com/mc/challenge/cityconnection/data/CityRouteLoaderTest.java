package com.mc.challenge.cityconnection.data;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.mc.challenge.cityconnection.model.CityGraph;
import com.mc.challenge.cityconnection.model.CityRoute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class CityRouteLoaderTest {
    private CityGraph cityGraph;
    @Autowired
    private CityRoutesLoader cityRoutesLoader;

    @BeforeEach
    public void setUp(){
        cityGraph = new CityGraph();
    }

    @Test
    public void readCityRoutesFromCsvFileTest(){
        Set<CityRoute> cityRoutes = cityRoutesLoader.loadCityRoutesFromCsvFile(CityRoute.class, "city.txt");
        assertNotNull(cityRoutes);
        assertTrue(!cityRoutes.isEmpty());
    }

    @Test
    public void populateCityGraphTest(){
        CityGraph cityGraph = cityRoutesLoader.populateCityGraph();
        assertNotNull(cityGraph);
    }
}
