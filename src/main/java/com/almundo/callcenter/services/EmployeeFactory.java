package com.almundo.callcenter.services;

import com.almundo.callcenter.domain.Director;
import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.domain.Operator;
import com.almundo.callcenter.domain.Supervisor;

public class EmployeeFactory {
    public static Employee get(String type, String name) {
        Employee employee = null;

        switch (type) {
            case "supervisor":
                employee = new Supervisor(name);
                break;
            case "director":
                employee = new Director(name);
                break;
            default:
                employee = new Operator(name);
                break;
        }

        return employee;
    }
}
