package com.almundo.callcenter;

import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.services.Dispatcher;
import com.almundo.callcenter.services.EmployeeFactory;
import com.almundo.callcenter.services.TakeCall;
import com.almundo.callcenter.util.EmployeeComparator;
import com.almundo.callcenter.util.Priority;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
public class CallCenterApp {

    public int poolSize;
    private int callQuantity;
    private int operators;
    private int supervisors;

    public CallCenterApp() {
        Dotenv env = Dotenv.configure()
                .directory("./")
                .load();
        this.poolSize = Integer.parseInt(env.get("POOL_SIZE"));
        this.callQuantity = Integer.parseInt(env.get("CALLS"));
        this.operators = Integer.parseInt(env.get("OPERATORS"));
        this.supervisors = Integer.parseInt(env.get("SUPERVISORS"));
    }

    public static void main(String[] args) {
        log.info("START:CallCenterApp::main");
        CallCenterApp app = new CallCenterApp();
        ExecutorService pool = Executors.newFixedThreadPool(app.poolSize);

        try {
            pool.invokeAll(app.generateCalls());
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        pool.shutdown();
        log.info("END:CallCenterApp::main");
    }

    private List<Callable<Employee>> generateCalls() {
        log.info("START:CallCenterApp::generateCalls");
        Dispatcher dispatcher = new Dispatcher(this.generateEmployees());
        List<Callable<Employee>> pendingCalls = new ArrayList<>();

        for (int i = 0; i < this.callQuantity; i++)
            pendingCalls.add(new TakeCall(dispatcher));

        return pendingCalls;
    }

    private PriorityBlockingQueue<Employee> generateEmployees() {
        log.info("START:CallCenterApp::generateEmployees");
        PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>(6, new EmployeeComparator());

        employees.add(EmployeeFactory.get(Priority.DIRECTOR, "Director"));

        for (int i = 0; i < this.supervisors; i++)
            employees.add(EmployeeFactory.get(Priority.SUPERVISOR, i + ".- Supervisor"));

        for (int i = 0; i < this.operators; i++)
            employees.add(EmployeeFactory.get(Priority.OPERATOR, i + ".- Operator"));

        return employees;
    }
}
