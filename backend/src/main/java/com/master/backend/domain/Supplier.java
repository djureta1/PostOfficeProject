package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Supplier {
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String jobDescription;

    @NotBlank
    private String identificationNumber;

    public Supplier() {
    }

    public Supplier(Integer id, String name, String jobDescription, String identificationNumber) {
        this.id = id;
        this.name = name;
        this.jobDescription = jobDescription;
        this.identificationNumber = identificationNumber;
    }

}
