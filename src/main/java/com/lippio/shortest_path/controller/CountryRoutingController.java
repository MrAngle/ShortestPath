package com.lippio.shortest_path.controller;

import com.lippio.shortest_path.errors.Errors;
import com.lippio.shortest_path.errors.RestException;
import com.lippio.shortest_path.service.DijkstraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routing")
@AllArgsConstructor
public class CountryRoutingController {

    private DijkstraService dijkstraService;

    @GetMapping(path = "/{from}/{to}")
    public ResponseEntity<List<String>> calculatePath(@PathVariable("from") String from,
                                                      @PathVariable("to") String to) {
        List<String> shortestPath = dijkstraService.getShortestPath(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(shortestPath);


    }
}