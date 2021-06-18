package com.example.demo.service;

import com.example.demo.model.Expense;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExpenseService {
   List<Expense> findAll();
   Expense findById(Long id);
   ResponseEntity<?> deleteById(Long id);
   void deleteAll();
   ResponseEntity<?> addExpense(Expense expense);
}
