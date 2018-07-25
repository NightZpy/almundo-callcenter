package com.almundo.callcenter.domain;

import lombok.Data;

@Data
public class Employee {
    private String name;
    private Integer priority;

    public Employee(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }
}
