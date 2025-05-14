package repository;

import model.User;
import util.CSVUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
	private static final String FILE_PATH = "data/users.csv";

    public List<User> getAllUsers() {
        return CSVUtil.readUsers(FILE_PATH);
    }

    public void saveUsers(List<User> users) {
        CSVUtil.writeUsers(FILE_PATH, users);
    }

    public void addUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        saveUsers(users); 
    }

    public boolean deleteUser(String username) {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(u -> u.getUsername().equalsIgnoreCase(username));
        saveUsers(users);  
        return removed;
    }

    public void updateUser(User updatedUser) {
        List<User> users = getAllUsers().stream()
                .map(u -> u.getUsername().equalsIgnoreCase(updatedUser.getUsername()) ? updatedUser : u)
                .collect(Collectors.toList());
        saveUsers(users); 
    }

    public User getUserByUsername(String username) {
        Optional<User> user = getAllUsers().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
        return user.orElse(null);
    }
}
