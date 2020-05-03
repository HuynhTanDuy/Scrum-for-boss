package auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import auth.entity.User;

import auth.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	private final static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}
}
