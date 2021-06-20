package com.example.demo.service;

import com.example.demo.controller.dto.ExpenseResponse;
import com.example.demo.exception.ExpenseError;
import com.example.demo.exception.ExpenseException;
import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<ExpenseResponse> findAll() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ExpenseResponse> response = new ArrayList<>();
        for(Expense e :expenses){
            response.add(ExpenseResponse
                    .builder()
                    .id(e.getId())
                    .title(e.getTitle())
                    .description(e.getDescription())
                    .sum(e.getSum()).build());
        }
        return response;
    }

    @Override
    public ExpenseResponse findById(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseException(ExpenseError.EXPENSE_NOT_FOUND));
       return  ExpenseResponse
               .builder()
               .id(expense.getId())
               .title(expense.getTitle())
               .description(expense.getDescription())
               .sum(expense.getSum()).build();
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        expenseRepository.deleteAll();
    }

    @Override
    public void addExpense(Expense expense) {
        expenseRepository.findByTitle(expense.getTitle())
                .map(expenseDb -> {
                    expenseDb.setSum(expenseDb.getSum() + expense.getSum());
                    return expenseRepository.save(expenseDb);
                }).orElseGet(() -> expenseRepository.save(expense));
    }
}
