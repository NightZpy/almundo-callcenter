package com.almundo.callcenter;

import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.services.EmployeeFactory;
import com.almundo.callcenter.util.Priority;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.PriorityBlockingQueue;

@SpringBootApplication
public class CallcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallcenterApplication.class, args);
    }

    private void performCalls() {
        PriorityBlockingQueue<Employee> employees = this.generateEmployees();
    }

    private PriorityBlockingQueue<Employee> generateEmployees() {
        PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<>();

        employees.add(EmployeeFactory.get(Priority.DIRECTOR, "Director"));

        for (int i = 0; i < 2; i++)
            employees.add(EmployeeFactory.get(Priority.SUPERVISOR i + ".- Supervisor"));

        for (int i = 0; i < 3; i++)
            employees.add(EmployeeFactory.get(Priority.OPERATOR, i + ".- Operator"));

        return employees;
    }
}
