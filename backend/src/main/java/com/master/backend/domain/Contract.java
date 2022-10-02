package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Contract {
    private Integer id;

    private Date creationDate;
    private Date expirationDate;
    private User user;
    private Supplier supplier;

    public Contract() {
    }

    public Contract(Integer id, Date creationDate, Date expirationDate, User user, Supplier supplier) {
        this.id = id;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.user = user;
        this.supplier = supplier;
    }
}
