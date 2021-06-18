package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExpenseExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(ExpenseException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ExpenseError.EXPENSE_NOT_FOUND.equals(e.getExpenseError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
    return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getExpenseError().getMessage()));
    }
}
