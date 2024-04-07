package com.marcura.routestrategyengine.service;

import com.marcura.routestrategyengine.service.model.Route;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "aiscollector", url = "${aiscollector.url}")
public interface AisCollectorClient {

    @GetMapping("/routes")
    ResponseEntity<List<Route>> getMostRepresentativeRoutes();
}
