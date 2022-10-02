package com.master.backend.controller;

import com.master.backend.domain.FirstTab;
import com.master.backend.model.FirstTabEntity;
import com.master.backend.repository.FirstTabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/firstTab")
public class FirstTabContoller {
    @Autowired
    FirstTabRepository firstTabRepository;

    @GetMapping
    public List<FirstTab> getAllModels(){
        //TODO move this logic to service class
        List<FirstTab> list= firstTabRepository.findAll().stream()
                .map(entity -> new FirstTab(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
        return list;
    }

}
