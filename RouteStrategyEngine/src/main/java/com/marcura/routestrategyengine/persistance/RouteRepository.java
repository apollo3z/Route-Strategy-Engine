package com.marcura.routestrategyengine.persistance;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends MongoRepository<RouteDocument, String> {
    List<RouteDocument> findByFromPortAndToPort(String from, String to);
}
