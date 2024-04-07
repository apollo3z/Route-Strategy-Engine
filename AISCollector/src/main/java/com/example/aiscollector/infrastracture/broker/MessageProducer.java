package com.example.aiscollector.infrastracture.broker;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageProducer<T> {
    void send(String topic, String key, T message) throws JsonProcessingException;
}