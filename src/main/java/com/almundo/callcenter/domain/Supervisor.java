package com.almundo.callcenter.domain;

import com.almundo.callcenter.util.Priority;

public class Supervisor extends Employee {
    public Supervisor(String name) {
        super(name, Priority.SUPERVISOR);
    }
}
