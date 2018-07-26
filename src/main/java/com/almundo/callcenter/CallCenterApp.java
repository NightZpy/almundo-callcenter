package com.almundo.callcenter;

import com.almundo.callcenter.domain.Employee;
import com.almundo.callcenter.services.Dispatcher;
import com.almundo.callcenter.services.EmployeeFactory;
import com.almundo.callcenter.services.TakeCall;
import com.almundo.callcenter.util.EmployeeComparator;
import com.almundo.callcenter.util.Priority;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
public class CallCenterApp {

    @Getter
    @Setter
    private int poolSize;

    @Getter
    @Setter
    private int callQuantity;

    @Getter
    @Setter
    private int operators;

    @Getter
    @Setter
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
        ExecutorService pool = Executors.newFixedThreadPool(app.getPoolSize());

        try {
            pool.invokeAll(app.generateCalls(app.getCallQuantity()));
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        pool.shutdown();
        log.info("END:CallCenterApp::main");
    }

    public List<Callable<Employee>> generateCalls(int callQuantity) {
        log.info("START:CallCenterApp::generateCalls");
        Dispatcher dispatcher = new Dispatcher(this.generateEmployees());
        List<Callable<Employee>> pendingCalls = new ArrayList<>();

        for (int i = 0; i < callQuantity; i++)
            pendingCalls.add(new TakeCall(dispatcher));

        return pendingCalls;
    }

    public PriorityBlockingQueue<Employee> generateEmployees() {
        log.info("START:CallCenterApp::generateEmployees");
        PriorityBlockingQueue<Employee> employees = new PriorityBlockingQueue<Employee>(6, new EmployeeComparator());

        employees.add(EmployeeFactory.get(Priority.DIRECTOR, "Director"));

        for (int i = 0; i < this.getSupervisors(); i++)
            employees.add(EmployeeFactory.get(Priority.SUPERVISOR, i + ".- Supervisor"));

        for (int i = 0; i < this.getOperators(); i++)
            employees.add(EmployeeFactory.get(Priority.OPERATOR, i + ".- Operator"));

        return employees;
    }
}
