package com.master.backend.model;

import com.master.backend.domain.Criteria;
import com.master.backend.domain.Grade;
import com.master.backend.domain.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "grade")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer year;

    @ManyToOne
    private CriteriaEntity criteria;

    @ManyToOne
    private SupplierEntity supplier;

    public GradeEntity() {
    }

    public GradeEntity(Integer grade, Integer year, CriteriaEntity criteria, SupplierEntity supplier) {
        this.grade = grade;
        this.year = year;
        this.criteria = criteria;
        this.supplier = supplier;
    }

    public Grade toDomain(){
        Criteria criteria=this.getCriteria().toDomain();
        Supplier supplier=this.getSupplier().toDomain();
        return new Grade(this.getId(),this.getGrade(),this.getYear(), criteria, supplier);
    }
}
