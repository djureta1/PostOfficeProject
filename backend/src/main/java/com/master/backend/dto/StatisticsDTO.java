package com.master.backend.dto;

import com.master.backend.domain.Grade;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatisticsDTO {
    private Integer suplierId;
    private String suplierName;
    private List<Grade> gradesByCriteria;
    private Double finalGrade;
    private Integer year;

    public StatisticsDTO() {
    }

    public StatisticsDTO(Integer suplierId, String suplierName, List<Grade> gradesByCriteria, Double finalGrade, Integer year) {
        this.suplierId = suplierId;
        this.suplierName = suplierName;
        this.gradesByCriteria = gradesByCriteria;
        this.finalGrade = finalGrade;
        this.year = year;
    }
}
