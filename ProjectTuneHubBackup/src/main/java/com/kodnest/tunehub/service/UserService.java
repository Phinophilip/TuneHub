package com.kodnest.tunehub.service;


import com.kodnest.tunehub.entity.User;

public interface UserService {
	public String addUser(User user);
	public boolean emailExists(String email);
	public String validateUser(String email, String password);
	public User getUser(String email);
	public void updateUser(User user);
}
