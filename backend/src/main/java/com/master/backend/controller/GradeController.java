package com.master.backend.controller;

import com.master.backend.domain.Grade;
import com.master.backend.dto.StatisticsObjectDTO;
import com.master.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {
    @Autowired
    GradeService gradeService;

    @GetMapping("/api/grade")
    List<Grade> getAll(){
        return gradeService.getAll();
    }

    @GetMapping("/api/grade/supplier/{id}")
    List<Grade> getBySupplierId(@PathVariable Integer id)throws Exception{
        return gradeService.getBySupplierId(id);
    }

    @PostMapping("/api/grade")
    @ResponseStatus(HttpStatus.CREATED)
    Grade add(@RequestBody Grade grade){
        return gradeService.addGrade(grade);
    }

    @GetMapping("/api/grade/final/{suplierId}")
    Double getFinalGradeForSuplier(@PathVariable Integer suplierId) throws Exception{
        return gradeService.getFinalGradeForSuplier(suplierId);
    }

    @GetMapping("/api/grade/statistic")
    StatisticsObjectDTO getStatistic() throws Exception {
        return gradeService.getStatistic();
    }
}
