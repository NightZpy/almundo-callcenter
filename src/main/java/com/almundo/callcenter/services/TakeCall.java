package com.almundo.callcenter.services;

import com.almundo.callcenter.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@AllArgsConstructor
@Slf4j
public class TakeCall implements Callable<Employee> {
    private Dispatcher dispatcher;

    @Override
    public Employee call() throws Exception {
        log.info("START:TakeCall::call");
        Employee employee = this.dispatcher.dispatchCall();
        log.debug("TakeCall:call::employee=" + employee);

        return employee;
    }
}
