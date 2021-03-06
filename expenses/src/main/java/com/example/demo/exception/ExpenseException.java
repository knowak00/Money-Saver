package com.example.demo.exception;

public class ExpenseException extends RuntimeException{
    private final ExpenseError expenseError;

    public ExpenseException(ExpenseError expenseError) {
        this.expenseError = expenseError;
    }

    public ExpenseError getExpenseError() {
        return expenseError;
    }
}
