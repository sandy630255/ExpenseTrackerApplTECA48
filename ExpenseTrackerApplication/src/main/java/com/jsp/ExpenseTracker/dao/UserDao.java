package com.jsp.ExpenseTracker.dao;

import com.jsp.ExpenseTracker.entity.User;

public interface UserDao 
{
    User registerUser(User user);
    
    User loginUser(String username, String password);
    
    boolean deleteUser(int userId);
    
    User updateUserData(User user,int userId);
    
    User forgotPassword(String username,String newPassword);
    
    User findById(int userId);
}