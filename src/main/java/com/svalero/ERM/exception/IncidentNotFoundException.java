package com.svalero.ERM.exception;

public class IncidentNotFoundException extends Exception {

    public IncidentNotFoundException() {
        super("This incident doesn't seem to exist");
    }
}
