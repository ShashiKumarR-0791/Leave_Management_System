package service;

import exception.InvalidCredentialsException;
import model.User;
import repository.UserRepository;

public class AuthService {
	private final UserRepository repo = new UserRepository();

	public User authenticate(String username, String password) throws InvalidCredentialsException {
		return repo.getAllUsers().stream()
				.filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst()
				.orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
	}
}