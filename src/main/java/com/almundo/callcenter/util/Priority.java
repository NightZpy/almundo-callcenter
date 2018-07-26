package com.almundo.callcenter.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum  Priority {
    DIRECTOR(3), SUPERVISOR(2), OPERATOR(1);

    @Getter
    @Setter
    private int value;
}
