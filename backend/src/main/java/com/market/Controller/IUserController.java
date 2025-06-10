package com.market.Controller;

import java.util.List;

import com.market.Entities.User;

public interface IUserController {
	User createUser(User user);
    List<User> getAllUsers();
}
