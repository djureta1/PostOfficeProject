package com.master.backend.controller;

import com.master.backend.domain.Contract;
import com.master.backend.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {
    @Autowired
    ContractService contractService;

    @GetMapping("/api/contract")
    public List<Contract> getAll(){
        return contractService.getAll();
    }

    @GetMapping("/api/contract/{id}")
    public Contract getById(@PathVariable Integer id) throws Exception{
        return contractService.getById(id);
    }

    @PostMapping("/api/contract")
    @ResponseStatus(HttpStatus.CREATED)
    public Contract addSupplier(@RequestBody Contract contract){
        return contractService.add(contract);
    }

    @DeleteMapping("/api/contract/{id}")
    public void deleteById(@PathVariable Integer id) throws Exception{
        contractService.deleteById(id);
    }
}
