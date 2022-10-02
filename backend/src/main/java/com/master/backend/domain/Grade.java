package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Grade {
    private Integer id;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer year;

    private Criteria criteria;

    private Supplier supplier;

    public Grade() {
    }

    public Grade(Integer id, Integer grade, Integer year, Criteria criteria, Supplier supplier) {
        this.id = id;
        this.grade = grade;
        this.year = year;
        this.criteria = criteria;
        this.supplier = supplier;
    }
}
