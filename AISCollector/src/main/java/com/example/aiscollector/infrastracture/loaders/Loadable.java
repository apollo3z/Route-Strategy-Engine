package com.example.aiscollector.infrastracture.loaders;

import java.util.List;

public interface Loadable<T> {
    List<T> load() throws Exception;
}
