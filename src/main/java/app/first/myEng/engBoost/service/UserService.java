package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user, int id) {
        var userDB = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setAge(user.getAge());
        userDB.setUsername(user.getUsername());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());

        return userRepository.save(userDB);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
