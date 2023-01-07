package com.svalero.ERM.exception;

public class ReportNotFoundException extends Exception {

    public ReportNotFoundException() {
        super("Report doesn't seem to exist");
    }
}
