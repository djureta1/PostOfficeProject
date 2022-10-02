package com.master.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatisticsObjectDTO {
    private List<StatisticsDTO> suplierGradeDtoList;
    private List<Integer> supliers;
    private  List<Integer> years;

    public StatisticsObjectDTO() {
    }

    public StatisticsObjectDTO(List<StatisticsDTO> suplierGradeDtoList, List<Integer> supliers, List<Integer> years) {
        this.suplierGradeDtoList = suplierGradeDtoList;
        this.supliers = supliers;
        this.years = years;
    }
}
