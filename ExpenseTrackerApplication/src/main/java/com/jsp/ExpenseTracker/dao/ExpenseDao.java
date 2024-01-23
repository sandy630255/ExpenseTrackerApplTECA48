package com.jsp.ExpenseTracker.dao;

import java.time.LocalDate;
import java.util.List;

import com.jsp.ExpenseTracker.entity.Expenses;

public interface ExpenseDao 
{
	Expenses addExpenses(Expenses expenses, int userId);
	
	List<Expenses> viewExpenses(int userId);
	
	Expenses updateExpenses(Expenses expenses, int expenseId);
	
	boolean deleteExpenses(int expenseId);
	
	Expenses findByExpenseId(int expenseId);
	
	List<Expenses> getExpenseBasedOnStartAndEndDate(LocalDate start, LocalDate end, int userId);

	List<Expenses> filterBasedOnDate(LocalDate date, int userId);

	List<Expenses> filterBasedOnCategory(String category, int userId);

	List<Expenses> filterBasedOnAmount(String range, int userId);
}