package com.jsp.ExpenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ExpenseTracker.entity.Expenses;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer>
{

}