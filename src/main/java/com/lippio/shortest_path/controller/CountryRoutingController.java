package com.lippio.shortest_path.controller;

import lombok.AllArgsConstructor;

import com.lippio.shortest_path.service.DataLoaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/routing")
@AllArgsConstructor
public class CountryRoutingController {

    DataLoaderService dataLoaderService;

    @GetMapping
    public String test(@PathVariable("") Long id, @RequestBody Class name) {
        return "";
    }
}