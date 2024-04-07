package com.example.aiscollector.infrastracture.broker.kafka;

import com.example.aiscollector.infrastracture.broker.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer<T> implements MessageProducer<T> {

    private static final Logger logger = LogManager.getLogger("KafkaProducer");

    private final ObjectMapper mapper = new ObjectMapper();

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topic, String key, T message) {
        try {
            String jsonMessage = mapper.writeValueAsString(message);
            kafkaTemplate.send(topic, key, jsonMessage);
        } catch (JsonProcessingException e) {
            logger.info("Serialization error " + e.getMessage());
        }
    }
}