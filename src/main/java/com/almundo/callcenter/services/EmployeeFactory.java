package com.almundo.callcenter.services;

import com.almundo.callcenter.domain.Director;
import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.domain.Operator;
import com.almundo.callcenter.domain.Supervisor;
import com.almundo.callcenter.util.Priority;

import static com.almundo.callcenter.util.Priority.*;

public class EmployeeFactory {
    public static Employee get(Priority priority, String name) {
        Employee employee = null;

        switch (priority) {
            case SUPERVISOR:
                employee = new Supervisor(name);
                break;
            case DIRECTOR:
                employee = new Director(name);
                break;
            default:
                employee = new Operator(name);
                break;
        }

        return employee;
    }
}
