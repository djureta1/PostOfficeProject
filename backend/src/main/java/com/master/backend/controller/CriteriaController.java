package com.master.backend.controller;

import com.master.backend.domain.Criteria;
import com.master.backend.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CriteriaController {
    @Autowired
    CriteriaService criteriaService;

    @GetMapping("/api/criteria")
    List<Criteria> getAll(){
        return criteriaService.getAll();
    }

    @GetMapping("/api/criteria/{id}")
    Criteria getById(@PathVariable Integer id) throws Exception{
        return criteriaService.findById(id);
    }

    @PostMapping("/api/criteria")
    @ResponseStatus(HttpStatus.CREATED)
    Criteria addCriteria(@RequestBody Criteria criteria){
        return criteriaService.addCriteria(criteria);
    }

    @DeleteMapping("/api/criteria/{id}")
    void deleteById(@PathVariable Integer id) throws Exception{
        criteriaService.deleteById(id);
    }
}
