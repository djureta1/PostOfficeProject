package com.master.backend.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstTab {
    private Integer id;

    private String name;

    public FirstTab() {
    }

    public FirstTab(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
