package com.servix.springangularscaffold.dto;

public class ObjectWrapper {
    private Object value;

    public ObjectWrapper(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
