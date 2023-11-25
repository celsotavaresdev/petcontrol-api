package com.ongpatinhasquebrilham.petcontrol.domain.service;

import com.ongpatinhasquebrilham.petcontrol.domain.exception.UserNotFoundException;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import com.ongpatinhasquebrilham.petcontrol.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository repository;

	@Transactional
	public User save(User user) {
		return repository.save(user);
	}

	@Transactional
	public void delete(Long userId) {
		repository.deleteById(userId);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User find(Long userId) {
		return repository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
	}

}