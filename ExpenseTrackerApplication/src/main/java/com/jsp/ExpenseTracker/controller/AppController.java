package com.jsp.ExpenseTracker.controller;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.ExpenseTracker.dao.ExpenseDao;
import com.jsp.ExpenseTracker.dao.UserDao;
import com.jsp.ExpenseTracker.entity.Expenses;
import com.jsp.ExpenseTracker.entity.User;
@Controller
public class AppController 
{
	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	@RequestMapping("login")
	public String login()
	{
		return "Login";	
	}
	@RequestMapping("register")
	public String register()
	{
		return "Register";
	}
	@RequestMapping("home")
	public String home()
	{
		return "Home";
	}
	@RequestMapping("addExpense")
	public String addExpense()
	{
		return "AddExpense";
	}
	@RequestMapping("viewExpense")
	public String viewExpense()
	{
		return "ViewExpense";
	}
	@RequestMapping("filterExpense")
	public String filterExpense()
	{
		return "FilterExpense";
	}
	@RequestMapping("totalExpense")
	public String totalExpense()
	{
		return "TotalExpense";
	}
	@Autowired
	private ExpenseDao expenseDao;
	
	@RequestMapping("updateExpense")
	public String updateExpense(@RequestParam("eId") int expenseId, Model model) {
		Expenses expenses = expenseDao.findByExpenseId(expenseId);
		model.addAttribute("expense", expenses);
		return "UpdateExpense";		
	}
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("UpdateProfile")
	public String showUpdateProfile(Model model, HttpServletRequest request) {
		User user =(User)request.getSession().getAttribute("userData");
		User finalData = userDao.findById(user.getUserId());
		model.addAttribute("user", finalData);
		model.addAttribute("image", Base64.getMimeEncoder().encodeToString(finalData.getData()));
		return "UpdateProfile";
	}
	
	
	
	
}