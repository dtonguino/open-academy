package com.academy.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.academy.model.security.User;
import com.academy.repository.IUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findOneByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		String role = user.getRole().getName();
		roles.add(new SimpleGrantedAuthority(role));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
	}
}
