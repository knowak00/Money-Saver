package com.example.demo.repository;

import com.example.demo.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository <Expense, Long> {
    Optional<Expense> findByTitle(String title);
}
