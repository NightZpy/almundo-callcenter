package com.almundo.callcenter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.almundo.callcenter.domain.*;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

public class CallCenterAppTests {

    private void turnOffLogger() {
        Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.ERROR);
    }

    @Test
    public void tenConcurrent() throws InterruptedException {
        this.turnOffLogger();
        CallCenterApp app = new CallCenterApp();
        List<Callable<Employee>> pendingCalls = app.generateCalls(10);
        ExecutorService callPool = Executors.newFixedThreadPool(10);
        List<Future<Employee>> futureEmployees = callPool.invokeAll(pendingCalls);

        assert futureEmployees.size() == 10;
    }

    @Test
    public void nineOperatorsThreeSupervisorsOneDirector() throws ExecutionException, InterruptedException {
        this.turnOffLogger();
        CallCenterApp app = new CallCenterApp();
        app.setOperators(9);
        app.setSupervisors(3);

        List<Callable<Employee>> pendingCalls = app.generateCalls(13);
        ExecutorService callPool = Executors.newFixedThreadPool(13);
        List<Future<Employee>> futureEmployees = callPool.invokeAll(pendingCalls);
        int operators = 0;
        int supervisors = 0;
        int directors = 0;

        for (Future<Employee> futureEmployee: futureEmployees) {
            if (futureEmployee.get() instanceof Operator) operators++;
            if (futureEmployee.get() instanceof Supervisor) supervisors++;
            if (futureEmployee.get() instanceof Director) directors++;
        }

        assert operators == 9 && supervisors == 3 && directors == 1;
    }

}
