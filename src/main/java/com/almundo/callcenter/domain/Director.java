package com.almundo.callcenter.domain;

import com.almundo.callcenter.util.Priority;

public class Director extends Employee {
    public Director(String name) {
        super(name, Priority.DIRECTOR);
    }
}
