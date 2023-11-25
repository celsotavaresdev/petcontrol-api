package com.ongpatinhasquebrilham.petcontrol.api.controller;

import com.ongpatinhasquebrilham.petcontrol.api.assembler.UserRequestDisassembler;
import com.ongpatinhasquebrilham.petcontrol.api.assembler.UserResponseAssembler;
import com.ongpatinhasquebrilham.petcontrol.api.model.UserRequest;
import com.ongpatinhasquebrilham.petcontrol.api.model.UserResponse;
import com.ongpatinhasquebrilham.petcontrol.domain.exception.UserAlreadyExistsException;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import com.ongpatinhasquebrilham.petcontrol.domain.repository.UserRepository;
import com.ongpatinhasquebrilham.petcontrol.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private UserResponseAssembler userResponseAssembler;
	private UserRequestDisassembler userRequestDisassembler;
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok(userResponseAssembler.toCollectionModel(users));
	}

	@PostMapping
	public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
		if (Objects.nonNull(this.userRepository.findByUsername(userRequest.getUsername()))) {
			throw new UserAlreadyExistsException(String.format("Já existe um cadastro de usuário com o nome %s", userRequest.getUsername()));
		}

		User convertedUser = userRequestDisassembler.toDomainObject(userRequest);
		convertedUser.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));

		User newUser = this.userService.save(convertedUser);

		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseAssembler.toModel(newUser));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
		User currentUser = userService.find(userId);
		return ResponseEntity.ok(userResponseAssembler.toModel(currentUser));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
		User currentUser = userService.find(userId);

		User foundUser = (User) this.userRepository.findByUsername(userRequest.getUsername());
		if (Objects.nonNull(foundUser)) {
			if (!Objects.equals(foundUser.getId(), userId)) {
				throw new UserAlreadyExistsException(String.format("Já existe um cadastro de usuário com o nome %s", userRequest.getUsername()));
			}
		}

		if(Objects.nonNull(userRequest.getPassword())) {
			userRequest.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
		}

		userRequestDisassembler.copyToDomainObject(userRequest, currentUser);
		User updatedUser = userService.save(currentUser);

		return ResponseEntity.ok(userResponseAssembler.toModel(updatedUser));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
		return ResponseEntity.noContent().build();
	}

}