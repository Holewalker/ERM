package com.svalero.ERM.exception;

public class EmgServiceNotFoundException extends Exception {

    public EmgServiceNotFoundException() {
        super("Emergency Service doesn't seem to exist");
    }
}
