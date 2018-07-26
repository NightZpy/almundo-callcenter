package com.almundo.callcenter.services;

import com.almundo.callcenter.domain.Employee;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Dispatcher {

    private static final int MAX_CALL_TIME = 10;
    private static final int MIN_CALL_TIME = 5;

    private PriorityBlockingQueue<Employee> availableEmployeesQueue;

    public Dispatcher(PriorityBlockingQueue<Employee> availableEmployeesQueue) {
        log.info("START:Dispatcher::availableEmployeesQueue=" + availableEmployeesQueue);
        this.availableEmployeesQueue = availableEmployeesQueue;
    }

    public Employee dispatchCall() throws InterruptedException {
        log.info("START:Dispatcher::dispatchCall");
        Employee assignedEmployee = this.availableEmployeesQueue.take();
        log.debug("Dispatcher:dispatchCall::assignedEmployee=" + assignedEmployee);
        this.calling(assignedEmployee);

        return assignedEmployee;
    }

    private void calling(Employee assignedEmployee) throws InterruptedException {
        long callTime = this.callTime();
        log.debug("START:Dispatcher::calling:::assignedEmployee=" + assignedEmployee + ":::callTime=" + callTime);
        Thread.sleep(callTime);
        this.availableEmployeesQueue.add(assignedEmployee);
    }

    private long callTime() {
        return TimeUnit.SECONDS.toMillis(new Random().nextInt(MAX_CALL_TIME - MIN_CALL_TIME) + MIN_CALL_TIME);
    }
}
