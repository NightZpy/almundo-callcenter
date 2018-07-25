package com.almundo.callcenter.util;

import com.almundo.callcenter.domain.Employee;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee, Employee employee2) {
        return employee.getPriority().compareTo(employee2.getPriority());
    }
}
