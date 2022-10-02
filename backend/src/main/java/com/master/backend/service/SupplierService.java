package com.master.backend.service;

import com.master.backend.domain.Supplier;
import com.master.backend.model.SupplierEntity;
import com.master.backend.repository.ContractRepository;
import com.master.backend.repository.GradeRepository;
import com.master.backend.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    ContractRepository contractRepository;

    public List<Supplier> getAll(){
        return supplierRepository.findAll().stream()
                .map(entity->entity.toDomain())
                .collect(Collectors.toList());
    }

    public Supplier getById(Integer id) throws Exception{
        if(!supplierRepository.existsById(id)){
            throw new Exception("Supplier with id: "+id+" doesn't exist");
        }
        return supplierRepository.findById(id).get().toDomain();
    }

    public Supplier addSupplier(Supplier supplier){
        SupplierEntity supplierEntity=new SupplierEntity(supplier.getName(),supplier.getJobDescription(),supplier.getIdentificationNumber());
        return supplierRepository.save(supplierEntity).toDomain();
    }

    public Supplier update(Supplier newSupplier, Integer id) throws Exception{
        if (!supplierRepository.existsById(id)){
            throw new Exception("Supplier with id: "+id+" doesn't exist!");
        }
        supplierRepository.findById(id).map(
                supplier->{
                    supplier.setName(newSupplier.getName());
                    supplier.setJobDescription(newSupplier.getJobDescription());
                    supplier.setIdentificationNumber(newSupplier.getIdentificationNumber());
                    return supplierRepository.save(supplier).toDomain();
                }
        );
        return supplierRepository.findById(id).get().toDomain();
    }

    public void delete(Integer id)throws Exception{
        if (!supplierRepository.existsById(id)){
            throw new Exception("Supplier with id: "+id+" doesn't exist!");
        }
        gradeRepository.deleteAll(gradeRepository.findBySupplierId(id));
        contractRepository.deleteAll(contractRepository.findBySupplierId(id));
        supplierRepository.deleteById(id);
    }


}
