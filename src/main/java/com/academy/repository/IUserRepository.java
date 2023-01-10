package com.academy.repository;

import com.academy.model.security.User;

public interface IUserRepository extends IGenericRepository<User, Integer> {
	User findOneByUsername(String username);
}
