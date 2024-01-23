package com.jsp.ExpenseTracker.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="expense_table")
public class Expenses 
{
	@Id
	//to generate pk value automatically
	@GeneratedValue
	(strategy = GenerationType.AUTO)
	private int expenseId;
	
	private String expenseCategory;
	private double amount;
	
	@Column(columnDefinition = "DATE")
	private LocalDate date;
	private String Description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User userInfo;	
}