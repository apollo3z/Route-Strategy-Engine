package com.example.aiscollector.infrastracture.loaders;

import java.text.ParseException;

public interface Parser<T, S> {
    T parse(S data) throws ParseException;
}
