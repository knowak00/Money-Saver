package com.example.demo.controller;

import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping
    List<Expense> getExpenses(){
        return expenseService.findAll();
    }
    @GetMapping("/{id}")
    Expense getExpense(@RequestParam Long id){
        return expenseService.findById(id);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteExpense(@RequestParam Long id){
        return expenseService.deleteById(id);
    }
    @PostMapping
    ResponseEntity<?> addExpense(@RequestBody @Valid Expense expense){
        return expenseService.addExpense(expense);
    }
    @DeleteMapping
    ResponseEntity<?> deleteAll(){
        expenseService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
