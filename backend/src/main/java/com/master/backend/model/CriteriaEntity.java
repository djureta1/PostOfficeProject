package com.master.backend.model;

import com.master.backend.domain.Criteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="criteria")
public class CriteriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Double coefficient;

    public CriteriaEntity() {
    }

    public CriteriaEntity(String name, Double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public Criteria toDomain(){
        Criteria criteria=new Criteria();
        criteria.setId(this.getId());
        criteria.setName(this.getName());
        criteria.setCoefficient(this.getCoefficient());
        return criteria;
    }
}
