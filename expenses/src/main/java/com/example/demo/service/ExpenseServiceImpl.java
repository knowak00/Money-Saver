package com.example.demo.service;

import com.example.demo.exception.ExpenseError;
import com.example.demo.exception.ExpenseException;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.model.Expense;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenseException(ExpenseError.EXPENSE_NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteAll() {
        expenseRepository.deleteAll();
    }

    @Override
    public ResponseEntity<?> addExpense(Expense expense) {
        expenseRepository.findByTitle(expense.getTitle())
                .map(expenseDb -> {
                    expenseDb.setSum(expenseDb.getSum() + expense.getSum());
                    return ResponseEntity.ok().body(expenseRepository.save(expenseDb));
                }).orElseGet(() -> {
            expenseRepository.save(expense);
            return ResponseEntity.ok().build();
        });
        return ResponseEntity.ok().build();
    }
}
