package com.almundo.callcenter;

import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.services.Dispatcher;
import com.almundo.callcenter.services.EmployeeFactory;
import com.almundo.callcenter.services.TakeCall;
import com.almundo.callcenter.util.EmployeeComparator;
import com.almundo.callcenter.util.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
public class CallCenterApp {

    public static void main(String[] args) {
        log.info("START:CallCenterApp::main");
        CallCenterApp app = new CallCenterApp();
        ExecutorService pool = Executors.newFixedThreadPool(100);

        try {
            pool.invokeAll(app.generateCalls());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        log.info("END:CallCenterApp::main");
    }

    private List<Callable<Employee>> generateCalls() {
        log.info("START:CallCenterApp::generateCalls");
        Dispatcher dispatcher = new Dispatcher(this.generateEmployees());
        List<Callable<Employee>> pendingCalls = new ArrayList<>();

        for (int i = 0; i < 50; i++)
            pendingCalls.add(new TakeCall(dispatcher));

        return pendingCalls;
    }

    private PriorityBlockingQueue<Employee> generateEmployees() {
        log.info("START:CallCenterApp::generateEmployees");
        PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>(6, new EmployeeComparator());

        employees.add(EmployeeFactory.get(Priority.DIRECTOR, "Director"));

        for (int i = 0; i < 2; i++)
            employees.add(EmployeeFactory.get(Priority.SUPERVISOR, i + ".- Supervisor"));

        for (int i = 0; i < 3; i++)
            employees.add(EmployeeFactory.get(Priority.OPERATOR, i + ".- Operator"));

        return employees;
    }
}
