package com.itmuch.cloud.user.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.user.dao.UserRepository;
import com.itmuch.cloud.user.model.User;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public User findById(@PathVariable Long id) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			UserDetails user = (UserDetails)principal;
			
			Collection<? extends GrantedAuthority> collection = user.getAuthorities();
			
			for(GrantedAuthority c : collection) {
				logger.info("当前用户是：{},角色是：{}", user.getUsername(), c.getAuthority());
			}
		} else {
			logger.info("principal instanceof UserDetails的结果是false");
		}
		
		User findOne = this.userRepository.findOne(id);
		
		return findOne;
	}
	
}
