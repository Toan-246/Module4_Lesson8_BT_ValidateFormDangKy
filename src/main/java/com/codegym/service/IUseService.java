package com.codegym.service;

import com.codegym.model.User;

public interface IUseService {
	Iterable <User> findAll();
	User save (User user);
}
