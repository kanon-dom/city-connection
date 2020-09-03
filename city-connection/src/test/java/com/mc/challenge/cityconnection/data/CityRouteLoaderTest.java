package com.mc.challenge.cityconnection.data;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import com.mc.challenge.cityconnection.model.CityGraph;
import com.mc.challenge.cityconnection.model.CityRoute;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRouteLoaderTest {
    private CityGraph cityGraph;
    @Autowired
    private CityRoutesLoader cityRoutesLoader;

    @Before
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
