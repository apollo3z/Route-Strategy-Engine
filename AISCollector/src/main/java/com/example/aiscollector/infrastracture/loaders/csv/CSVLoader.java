package com.example.aiscollector.infrastracture.loaders.csv;

import com.example.aiscollector.infrastracture.broker.kafka.model.Route;
import com.example.aiscollector.infrastracture.loaders.Loadable;
import com.example.aiscollector.infrastracture.loaders.csv.model.CSVRoute;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CSVLoader implements Loadable<Route> {
    private static final Logger logger = LogManager.getLogger("CSVLoader");

    private final CSVParser parser;
    private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    public CSVLoader(CSVParser parser) {
        this.parser = parser;
    }

    // should have possibility to scan and retrigger the loads.
    @Override
    public List<Route> load() {
        logger.info("Proceeding with data load...");
        try {
            Resource[] resources = resolver.getResources("classpath:input/*.csv");
            logger.info("Input data found in resources: ");
            for (Resource r : resources){
                logger.info("***** " + r.getFilename() + " *****");
            }
            return Arrays.stream(resources)
                    .map(this::processCsvResource)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error loading CSV resources", e);
            return Collections.emptyList();
        }
    }

    private List<Route> processCsvResource(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            return new CsvToBeanBuilder<CSVRoute>(reader)
                    .withType(CSVRoute.class)
                    .build()
                    .parse()
                    .stream()
                    .map(parser::parse)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }
}
