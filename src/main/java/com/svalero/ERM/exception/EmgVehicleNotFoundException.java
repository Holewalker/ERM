package com.svalero.ERM.exception;

public class EmgVehicleNotFoundException extends Exception {

    public EmgVehicleNotFoundException() {
        super("Emergency Vehicle doesn't seem to exist");
    }
}
