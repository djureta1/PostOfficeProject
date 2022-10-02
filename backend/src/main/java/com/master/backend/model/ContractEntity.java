package com.master.backend.model;

import com.master.backend.domain.Contract;
import com.master.backend.domain.Supplier;
import com.master.backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "contract")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date creationDate;
    private Date expirationDate;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private SupplierEntity supplier;

    public ContractEntity() {
    }

    public ContractEntity(Date creationDate, Date expirationDate, UserEntity user, SupplierEntity supplier) {
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.user = user;
        this.supplier = supplier;
    }

    public Contract toDomain(){
        User user=this.getUser().toDomain();
        Supplier supplier=this.getSupplier().toDomain();
        return new Contract(this.getId(),this.getCreationDate(),this.getExpirationDate(),user,supplier);
    }
}
