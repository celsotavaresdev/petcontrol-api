package com.ongpatinhasquebrilham.petcontrol.api.controller;

import com.ongpatinhasquebrilham.petcontrol.api.assembler.UserResponseAssembler;
import com.ongpatinhasquebrilham.petcontrol.api.model.UserResponse;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import com.ongpatinhasquebrilham.petcontrol.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private UserResponseAssembler userResponseAssembler;

	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok(userResponseAssembler.toCollectionModel(users));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
		User currentUser = userService.find(userId);
		return ResponseEntity.ok(userResponseAssembler.toModel(currentUser));
	}

}