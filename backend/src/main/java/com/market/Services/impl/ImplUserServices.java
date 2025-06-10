package com.market.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Services.IUserServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Service
public class ImplUserServices implements IUserServices {

	 @Autowired
	    private UserRepository userRepository;

	    @Override
	    public User save(User user) {
	        return userRepository.save(user);

	    }

	@Override
	public List<User> getALList() {
		
		 return userRepository.findAll();
	}
}