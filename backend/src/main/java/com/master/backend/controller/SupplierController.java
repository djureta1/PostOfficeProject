package com.master.backend.controller;

import com.master.backend.domain.Supplier;
import com.master.backend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping("/api/supplier")
    public List<Supplier> getAll(){
        return supplierService.getAll();
    }

    @GetMapping("/api/supplier/{id}")
    public Supplier getById(@PathVariable Integer id) throws Exception{
        return supplierService.getById(id);
    }

    @PostMapping("/api/supplier")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/api/supplier/{id}")
    public Supplier update(@RequestBody Supplier supplier,@PathVariable Integer id) throws Exception{
        return supplierService.update(supplier,id);
    }

    @DeleteMapping("/api/supplier/{id}")
    public void deleteById(@PathVariable Integer id) throws Exception{
        supplierService.delete(id);
    }
}
