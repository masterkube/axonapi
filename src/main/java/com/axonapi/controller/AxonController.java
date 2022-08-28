package com.axonapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axonapi.Model.edge;
import com.axonapi.Model.node;
import com.axonapi.repository.AxonRepository;

@RestController
public class AxonController {
    
    @Autowired
    private AxonRepository repo;

    @GetMapping(path = "/test",produces =  MediaType.APPLICATION_JSON_VALUE)
    public String testcall(){
        return repo.test();
    }

    @GetMapping("/getnodes")
    public List<node> getallnodes(){
        return repo.getnodes();
    }

    @GetMapping("/relation")
    public List<edge> getrelation(){
        return repo.getallrelation();
    }
    
}
