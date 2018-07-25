package com.almundo.callcenter.services;

import com.almundo.callcenter.domain.Employee;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Dispatcher {

    private static final int MAX_CALL_TIME = 10;
    private static final int MIN_CALL_TIME = 5;

    private PriorityBlockingQueue<Employee> availableEmployeesQueue;

    public Dispatcher(PriorityBlockingQueue<Employee> availableEmployeesQueue) {
        this.availableEmployeesQueue = availableEmployeesQueue;
    }

    public void dispatchCall() throws InterruptedException {
        this.calling(this.availableEmployeesQueue.take());
    }

    private void calling(Employee assignedEmployee) throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(this.callTime()));
        this.availableEmployeesQueue.add(assignedEmployee);
    }

    private int callTime() {
        return new Random().nextInt(MAX_CALL_TIME - MIN_CALL_TIME) + MIN_CALL_TIME;
    }
}
