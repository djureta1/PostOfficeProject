package com.master.backend.model;

import com.master.backend.domain.Supplier;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String jobDescription;

    @NotBlank
    private String identificationNumber;

    public SupplierEntity() {
    }

    public SupplierEntity(String name, String jobDescription, String identificationNumber) {
        this.name = name;
        this.jobDescription = jobDescription;
        this.identificationNumber = identificationNumber;
    }

    public Supplier toDomain(){
        return new Supplier(this.getId(),this.getName(),this.getJobDescription(),this.getIdentificationNumber());
    }
}
