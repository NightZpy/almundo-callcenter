package com.almundo.callcenter.util;

import com.almundo.callcenter.domain.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

@Slf4j
public class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee, Employee employee2) {
        log.info("START:EmployeeComparator::compare:::employee=" + employee + ":::employee2=" + employee2);
        return employee.getPriority().compareTo(employee2.getPriority());
    }
}
