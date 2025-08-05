package com.trigon.service;

import com.trigon.entity.User;
import com.trigon.exception.UserException;

public interface UserService {

	public User findUserById(Long id) throws UserException;
	public User findUserProfileByjwt(String jwt) throws UserException;
}
