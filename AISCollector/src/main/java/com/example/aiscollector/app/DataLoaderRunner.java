package com.example.aiscollector.app;

import com.example.aiscollector.infrastracture.DataLoadObserver;
import com.example.aiscollector.infrastracture.broker.kafka.model.Route;
import com.example.aiscollector.infrastracture.loaders.Loadable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DataLoaderRunner implements CommandLineRunner {

    private final List<Loadable> loaders;
    private final DataLoadObserver observer;

    public DataLoaderRunner(List<Loadable> loaders, DataLoadObserver observer) {
        this.loaders = loaders;
        this.observer = observer;
    }

    @Override
    public void run(String... args) throws Exception {
        for (Loadable loader : loaders) {
            List<Route> routes = loader.load();
            observer.onDataLoadComplete(routes);
        }
    }
}