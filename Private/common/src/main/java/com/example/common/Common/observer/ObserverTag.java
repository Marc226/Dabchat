package com.example.common.Common.observer;

public enum ObserverTag {
    DEFAULT(0),
    LOGIN(1),
    REGISTER(2);

    private int intvalue;

    ObserverTag(int i) {
        intvalue = i;
    }

    public int getIntvalue() {
        return intvalue;
    }
}
