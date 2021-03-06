package com.itmuch.cloud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.user.dao.UserRepository;
import com.itmuch.cloud.user.model.User;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		User findOne = this.userRepository.findOne(id);

		return findOne;
	}

	@GetMapping("/get")
	public User get(User user) {
		return user;
	}

	@PostMapping("/post")
	public User post(@RequestBody User user) {
		return user;
	}
}
