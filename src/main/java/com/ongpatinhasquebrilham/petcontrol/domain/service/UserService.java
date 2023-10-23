package com.ongpatinhasquebrilham.petcontrol.domain.service;

import com.ongpatinhasquebrilham.petcontrol.domain.exception.PetNotFoundException;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import com.ongpatinhasquebrilham.petcontrol.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User find(Long userId) {
		return repository.findById(userId)
				.orElseThrow(() -> new PetNotFoundException(userId));
	}

}