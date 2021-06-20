package com.example.demo.controller;

import com.example.demo.controller.dto.ExpenseResponse;
import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping
    List<ExpenseResponse> getExpenses(){
        return expenseService.findAll();
    }
    @GetMapping("/{id}")
    ExpenseResponse getExpense(@RequestParam Long id){
        return expenseService.findById(id);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteExpense(@RequestParam Long id){
        expenseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    ResponseEntity<?> addExpense(@RequestBody @Valid Expense expense){
        expenseService.addExpense(expense);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    ResponseEntity<?> deleteAll(){
        expenseService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
