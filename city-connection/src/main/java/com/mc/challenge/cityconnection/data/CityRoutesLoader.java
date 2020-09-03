package com.mc.challenge.cityconnection.data;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mc.challenge.cityconnection.model.CityGraph;
import com.mc.challenge.cityconnection.model.CityRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class CityRoutesLoader {

    @Value("${com.cm.challenge.cityconnection.fileName}")
    private String cityRoutesFileName;

    private static Logger LOGGER = LoggerFactory.getLogger(CityRoutesLoader.class);
    public Set<CityRoute> loadCityRoutesFromCsvFile(Class<CityRoute> type, String fileName){
        try {
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.enable(CsvParser.Feature.TRIM_SPACES);
            csvMapper.enable(CsvParser.Feature.ALLOW_TRAILING_COMMA);
            csvMapper.enable(CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS);
            csvMapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
            csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
            CsvSchema schema = csvMapper
                    .schemaFor(type)
                    .withColumnReordering(true)
                    .withoutHeader()
                    .withColumnSeparator(',');
            ObjectReader objectReader = csvMapper.readerFor(type).with(schema);
            return new HashSet<> (objectReader.<CityRoute>readValues(new ClassPathResource(fileName).getInputStream()).readAll());
        } catch(Exception e){
          LOGGER.error("Error occurred while loading object list from file {}", fileName, e);
          return Collections.emptySet();
        }
    }

    public CityGraph populateCityGraph (){
        CityGraph citygraph = new CityGraph();
        Set<CityRoute> cityRoutes = loadCityRoutesFromCsvFile(CityRoute.class, cityRoutesFileName);
        citygraph.LoadCities(cityRoutes);
        citygraph.loadCityRoutes(cityRoutes);
        return citygraph;
    }

}
