package com.svalero.ERM.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorUtil {
    private int code;
    private String message;
    private Map<String, String> errors;

    public ErrorUtil(int code, String message) {
        this.code = code;
        this.message = message;
        errors = new HashMap<>();
    }

    public static ResponseEntity<ErrorUtil> getErrorExceptionResponseEntity(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String name = error.getDefaultMessage();
            errors.put(fieldName, name);
        });
        ErrorUtil errorUtil = new ErrorUtil(403, "Forbidden", errors);
        return new ResponseEntity<>(errorUtil, HttpStatus.FORBIDDEN);
    }


}
