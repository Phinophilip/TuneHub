package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository ur;

	public String addUser(User user) {
		ur.save(user);
		return "User added Successfully";

	}

	public boolean emailExists(String email) {
		if (ur.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	public String validateUser(String email, String password) {
		User a = ur.findByEmail(email);
		if(a == null) {
		    System.out.println("User didn't Exist");
		    return "Registration";
		}
		if (a.getPassword().equals(password)) {
		    System.out.println("Login Successful");
		    if (a.getRole().equals("admin")) {
		        return "AdminHome";
		    } else if(a.isIspremium()) {
		        return "CustomerHome";
		    }
		    else {
		    	return "NonPremium";
		    }
		}
		else {
		    System.out.println("Invalid Email and Password");
		    return "Login";
		}
	}

	@Override
	public User getUser(String email) {
		return ur.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		ur.save(user);
	}

}
