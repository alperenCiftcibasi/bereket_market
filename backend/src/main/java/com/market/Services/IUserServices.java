package com.market.Services;

import java.util.List;

import com.market.Entities.User;

public interface IUserServices {
	User save(User user);
    List<User> getALList();
}
