package com.example.aiscollector.infrastracture;

import java.util.List;

public interface DataLoadObserver<T> {
    void onDataLoadComplete(List<T> data);
}
