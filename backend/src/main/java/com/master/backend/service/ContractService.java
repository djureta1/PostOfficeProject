package com.master.backend.service;

import com.master.backend.domain.Contract;
import com.master.backend.domain.User;
import com.master.backend.model.ContractEntity;
import com.master.backend.model.SupplierEntity;
import com.master.backend.model.UserEntity;
import com.master.backend.repository.ContractRepository;
import com.master.backend.repository.SupplierRepository;
import com.master.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public List<Contract> getAll(){
        return contractRepository.findAll().stream()
                .map(entity->entity.toDomain())
                .collect(Collectors.toList());
    }

    public Contract getById(Integer id) throws Exception{
        if(!contractRepository.existsById(id)){
            throw new Exception("Contract with id:"+id+" doesn't exist!");
        }
        return contractRepository.getById(id).toDomain();
    }

    public Contract add(Contract contract){
        UserEntity userEntity=userRepository.findById(contract.getUser().getId()).get();
        SupplierEntity supplierEntity=supplierRepository.findById(contract.getSupplier().getId()).get();
        ContractEntity contractEntity=new ContractEntity(contract.getCreationDate(),contract.getExpirationDate(),userEntity,supplierEntity);
        return contractRepository.save(contractEntity).toDomain();
    }

    public void deleteById(Integer id) throws Exception{
        if(!contractRepository.existsById(id)){
            throw new Exception("Contract with id:"+id+" doesn't exist!");
        }
        contractRepository.deleteById(id);
    }

}
