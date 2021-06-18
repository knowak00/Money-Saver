package com.example.demo.exception;

public enum ExpenseError {
    EXPENSE_NOT_FOUND("Expense does not exist");
    private String message;

    ExpenseError(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
