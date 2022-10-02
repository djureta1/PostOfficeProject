package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Criteria {
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Double coefficient;

    public Criteria(Integer id,String name, Double coefficient) {
        this.id = id;
        this.name = name;
        this.coefficient = coefficient;
    }

    public Criteria(){

    }

}
