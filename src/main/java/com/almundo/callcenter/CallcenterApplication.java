package com.almundo.callcenter;

import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.services.EmployeeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.PriorityBlockingQueue;

@SpringBootApplication
public class CallcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallcenterApplication.class, args);
    }

    private void performCalls() {
        PriorityBlockingQueue<Employee> employees = this.generateEmployes();
    }

    private PriorityBlockingQueue<Employee> generateEmployes() {
        PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<>();

        employees.add(EmployeeFactory.get("director", "Director"));

        for (int i = 0; i < 2; i++)
            employees.add(EmployeeFactory.get("supervisor", i + ".- Supervisor"));

        for (int i = 0; i < 3; i++)
            employees.add(EmployeeFactory.get("operator", i + ".- Operator"));

        return employees;
    }
}
