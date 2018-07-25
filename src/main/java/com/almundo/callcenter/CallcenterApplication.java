package com.almundo.callcenter;

import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.services.Dispatcher;
import com.almundo.callcenter.services.EmployeeFactory;
import com.almundo.callcenter.util.Priority;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@SpringBootApplication
public class CallcenterApplication {

    public static void main(String[] args) {
        ExecutorService pool = new Executors.newFixedThreadPool(10);
        pool.invokeAll(this.generateCalls());
        pool.shutdown();
    }

    private void generateCalls() {
        Dispatcher dispatcher = new Dispatcher(this.generateEmployees());
        List<Callable<Employee>> pendingCalls = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            pendingCalls.add();
        }

        return pendingCalls;
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
