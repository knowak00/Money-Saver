package com.example.demo.service;

import com.example.demo.controller.dto.ExpenseResponse;
import com.example.demo.model.Expense;

import java.util.List;

public interface ExpenseService {
   List<ExpenseResponse> findAll();
   ExpenseResponse findById(Long id);
   void deleteById(Long id);
   void deleteAll();
   void addExpense(Expense expense);
}
