package com.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.model.security.User;
import com.academy.repository.IGenericRepository;
import com.academy.repository.IUserRepository;
import com.academy.service.IUserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements IUserService {

	@Autowired
	private IUserRepository repository;

	@Override
	protected IGenericRepository<User, Integer> getRepository() {
		return this.repository;
	}

}
