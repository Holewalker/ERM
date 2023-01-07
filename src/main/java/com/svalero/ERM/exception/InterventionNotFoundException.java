package com.svalero.ERM.exception;

public class InterventionNotFoundException extends Exception {

    public InterventionNotFoundException() {
        super("This Intervention doesn't seem to exist");
    }
}
