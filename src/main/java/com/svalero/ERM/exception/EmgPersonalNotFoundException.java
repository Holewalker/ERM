package com.svalero.ERM.exception;

public class EmgPersonalNotFoundException extends Exception {

    public EmgPersonalNotFoundException() {
        super("Emergency Personal doesn't seem to exist");
    }
}
