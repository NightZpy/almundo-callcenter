package com.almundo.callcenter.domain;

import com.almundo.callcenter.util.Priority;

public class Operator extends Employee {
    public Operator(String name) { super(name, Priority.OPERATOR); }
}
