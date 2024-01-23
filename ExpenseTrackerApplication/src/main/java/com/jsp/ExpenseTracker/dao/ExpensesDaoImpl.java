package com.jsp.ExpenseTracker.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.ExpenseTracker.entity.Expenses;
import com.jsp.ExpenseTracker.entity.User;
import com.jsp.ExpenseTracker.repository.ExpensesRepository;
import com.jsp.ExpenseTracker.repository.UserRepository;
@Component
public class ExpensesDaoImpl implements ExpenseDao 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExpensesRepository expensesRepository;
	
	@Override
	public Expenses addExpenses(Expenses expenses, int userId) {
		User user=userRepository.findById(userId).orElse(null);
		if(user != null)
		{
			expenses.setUserInfo(user);
			return expensesRepository.save(expenses);
		}
		return null;
	}

	@Override
	public List<Expenses> viewExpenses(int userId) {
		User user = userRepository.findById(userId).orElse(null);
		if(user !=null) {
			return user.getExpenses();
		}
		return null;
	}

	@Override
	public Expenses updateExpenses(Expenses expenses, int expenseId) 
	{
		Expenses updateexpense=expensesRepository.findById(expenseId).orElse(null);
		if(updateexpense!=null)
		{
			updateexpense.setExpenseId(expenses.getExpenseId());
			updateexpense.setDescription(expenses.getDescription());
			updateexpense.setAmount(expenses.getAmount());
			updateexpense.setDate(expenses.getDate());
			updateexpense.setExpenseCategory(expenses.getExpenseCategory());
	        return expensesRepository.save(updateexpense);
		}
		return null;
	}

	@Override
	public boolean deleteExpenses(int expenseId) {
		try {
			expensesRepository.deleteById(expenseId);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Expenses findByExpenseId(int expenseId) {
		
		Expenses expenses = expensesRepository.findById(expenseId).orElse(null);
		return expenses;
	}
	
	@Override
	public List<Expenses> getExpenseBasedOnStartAndEndDate(LocalDate start, LocalDate end, int userId) {
		List<Expenses> listOfExpenses = viewExpenses(userId);
		
		//This is stream API
		List<Expenses> finalOutput = listOfExpenses.stream()
					  .filter(t -> {
							return !t.getDate().isBefore(start) && !t.getDate().isAfter(end);
						}).collect(Collectors.toList());
		System.out.println("demo"+ finalOutput);
		return finalOutput;
	}

	@Override
	public List<Expenses> filterBasedOnDate(LocalDate date, int userId) 
	{
		List<Expenses> expenses = viewExpenses(userId);
		List<Expenses> finalOutput = expenses.stream()
				.filter(t -> t.getDate().equals(date)).collect(Collectors.toList());
		return finalOutput;
	}

	@Override
	public List<Expenses> filterBasedOnCategory(String category, int userId) 
	{
		List<Expenses> expenses = viewExpenses(userId);
		List<Expenses> finalOutput = expenses.stream()
				.filter(t -> t.getExpenseCategory().equals(category)).collect(Collectors.toList());
		return finalOutput;
	}

	@Override
	public List<Expenses> filterBasedOnAmount(String range, int userId) 
	{
		String[] arr = range.split("-");
		  List<Expenses> expenses = viewExpenses(userId);
		  
		  List<Expenses> finalOutput = expenses.stream()
		  		  .filter(t -> {
					int start = Integer.parseInt(arr[0]);
					int end = Integer.parseInt(arr[1]);
					return start <= t.getAmount() && end >= t.getAmount();
		  		  }).collect(Collectors.toList());
		  return finalOutput;
	}
}