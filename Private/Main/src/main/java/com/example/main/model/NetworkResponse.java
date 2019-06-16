package com.example.main.model;

public enum NetworkResponse {
    SUCCESS(0),
    FAIL(1),
    UNAVAILABLE(2);

    private int intvalue;

    NetworkResponse(int i) {
        intvalue = i;
    }

    public int getIntvalue() {
        return intvalue;
    }
}
