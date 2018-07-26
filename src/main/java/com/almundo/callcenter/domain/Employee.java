package com.almundo.callcenter.domain;

import com.almundo.callcenter.util.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    protected Priority priority;
}
